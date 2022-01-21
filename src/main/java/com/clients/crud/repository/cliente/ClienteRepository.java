package com.clients.crud.repository.cliente;

import com.clients.crud.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>,ClienteRepositoryCustom{
}
