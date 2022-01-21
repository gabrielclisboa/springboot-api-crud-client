package com.clients.crud.controller;

import com.clients.crud.model.Cliente;
import com.clients.crud.model.dto.ClienteResponseDTO;
import com.clients.crud.repository.cliente.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/crud")
@AllArgsConstructor
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping(value = "/listAll")
    public ResponseEntity<?> list(Pageable pageable){
        return new ResponseEntity<>(clienteRepository.findAll(pageable),HttpStatus.OK);
    }

    @PostMapping("/save")
    public void save(@RequestBody Cliente cliente){
        clienteRepository.salvar(cliente);
    }
    @GetMapping("/getClient/{id}")
    public Optional<ClienteResponseDTO> getClient(@PathVariable("id")  Long id){
        return clienteRepository.createClienteDTO(id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){ clienteRepository.deletar(id);}
}
