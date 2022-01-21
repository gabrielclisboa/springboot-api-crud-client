package com.clients.crud.repository.cliente;

import com.clients.crud.model.Cliente;
import com.clients.crud.model.dto.ClienteResponseDTO;

import java.util.Optional;

public interface ClienteRepositoryCustom {
    public void salvar(Cliente product);
    public void deletar(Long entity);
    public Optional<ClienteResponseDTO> createClienteDTO(Long id);
}
