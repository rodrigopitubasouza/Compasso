package br.com.compasso.backend.service;

import br.com.compasso.backend.dto.CidadeDto;
import br.com.compasso.backend.entity.Cidade;
import br.com.compasso.backend.repository.IRepository;
import org.springframework.stereotype.Service;

@Service
public class CidadeService extends AbstractService<Cidade, CidadeDto, CidadeDto, CidadeDto, CidadeDto> {
    public CidadeService(IRepository<Cidade> repository) {
        super(repository);
    }
}
