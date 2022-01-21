package com.clients.crud.repository.cliente;

import com.clients.crud.model.Cliente;
import com.clients.crud.model.Email;
import com.clients.crud.model.dto.ClienteResponseDTO;
import com.clients.crud.repository.email.EmailRepository;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class ClienteRepositoryImpl{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Lazy
    private ClienteRepository clienteRepository;

    @Autowired
    @Lazy
    private EmailRepository emailRepository;

    public ClienteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<ClienteResponseDTO> createClienteDTO(Long id){
        String urlImage= "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/768px-User_icon_2.svg.png";

        ClienteResponseDTO responseDTO = new ClienteResponseDTO();
        responseDTO.setCliente(clienteRepository.findById(id));
        responseDTO.setUrlImage(urlImage);

        return Optional.of(responseDTO);
    }

    public void salvar(Cliente entity) {
        List<Email> emailList  = entity.getEmail();

        entity.setEmail(null);

        entity = clienteRepository.save(entity);


        if (entity.getId() != 0) {
            if (entity != null) {
                removeOldEmails(entity, emailList);
            }

        }

        for (Email email : emailList) {
            email.setCliente(entity);
            email.setId(0L);
            emailRepository.save(email);
        }
    }

    public void deletar(Long id) {
        Cliente entity = new Cliente();
        entity = clienteRepository.getById(id);

        removeOldEmails(entity, entity.getEmail());
        clienteRepository.deleteById(entity.getId());
    }

    /**
     * Remove todos os emails atrelados ao cliente "entity"
     * @param entity
     * @param emailList
     * return
     * */
    private void removeOldEmails(Cliente entity, List<Email> emailList) {
        List<Long> ids = new ArrayList<>();

        emailList.forEach(item -> {
            if (item.getId() != null) {
                ids.add(item.getId());
            } else {
                ids.add(0L);
            }
        });

        if (emailList.isEmpty()) {
            ids.add(0L);
        }

        List<Email> odlEmailList = entityManager.createNamedQuery(Email.RECUPERAR_ANTERIOR_BY_REGRA_ID, Email.class).setParameter("ids", ids).setParameter("id", entity.getId()).getResultList();

        if (odlEmailList.size() > 0) {
            odlEmailList.forEach(t -> {
                try {
                    emailRepository.delete(t);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
