package com.clients.crud.model.dto;

import com.clients.crud.model.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class ClienteResponseDTO {

    private  String urlImage;
    private Optional<Cliente> cliente;
}
