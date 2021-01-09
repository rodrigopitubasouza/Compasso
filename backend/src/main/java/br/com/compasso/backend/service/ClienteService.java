package br.com.compasso.backend.service;

import br.com.compasso.backend.dto.ClienteDto;
import br.com.compasso.backend.dto.ClientePatchDto;
import br.com.compasso.backend.entity.Cidade;
import br.com.compasso.backend.entity.Cliente;
import br.com.compasso.backend.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends AbstractService<Cliente, ClienteDto, ClienteDto, ClienteDto, ClientePatchDto> {

    @Autowired
    private CidadeService cidadeService;

    public ClienteService(IRepository<Cliente> repository) {
        super(repository);
    }

    @Override
    public void treatJoinColumnsInsert(Cliente entity, ClienteDto dto) {
        Cidade cidade = cidadeService.findByIdOrExceptionEntity(dto.getCidade());
        entity.setCidade(cidade);
        configModelMapper();
    }

    private void configModelMapper() {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.typeMap(Cliente.class, ClienteDto.class).addMappings(mp -> {
            mp.<Long>map(src -> src.getCidade().getId(), (dst, value) -> dst.setCidade(value));
        });
    }

    @Override
    public void treatJoinColumnsPatch(Cliente entity, ClientePatchDto dto) {
        configModelMapper();
    }
}
