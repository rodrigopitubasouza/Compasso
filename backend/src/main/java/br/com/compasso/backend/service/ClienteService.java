package br.com.compasso.backend.service;

import br.com.compasso.backend.dto.ClienteDto;
import br.com.compasso.backend.dto.ClientePatchDto;
import br.com.compasso.backend.entity.Cliente;
import br.com.compasso.backend.repository.IRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends AbstractService<Cliente, ClienteDto, ClienteDto, ClienteDto, ClientePatchDto> {
    public ClienteService(IRepository<Cliente> repository) {
        super(repository);
    }
}
