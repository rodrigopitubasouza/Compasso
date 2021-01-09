package br.com.compasso.backend.service;

import br.com.compasso.backend.dto.ClienteDto;
import br.com.compasso.backend.dto.ClientePatchDto;
import br.com.compasso.backend.entity.Cidade;
import br.com.compasso.backend.entity.Cliente;
import br.com.compasso.backend.specification.ClienteSpecification;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;

public class ClienteServiceTest extends AbstractServiceTest<Cliente, ClienteDto, ClienteDto, ClienteDto, ClientePatchDto, ClienteSpecification>{

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private CidadeService cidadeService;

    @Override
    protected ClienteDto generateDtoForInsert() {
        ClienteDto cliente = new ClienteDto();
        cliente.setNome("nome");
        cliente.setSexo("sexo");
        cliente.setNascimento(LocalDate.now());
        cliente.setIdade(1);
        cliente.setCidade(1L);

        return cliente;
    }

    @Override
    protected Cliente generateEntityMockForInsert() {
        Cliente mock = new Cliente();
        mock.setNome("nome");
        mock.setSexo("sexo");
        mock.setNascimento(LocalDate.now());
        mock.setIdade(1);
        Cidade cidade = new Cidade();
        cidade.setId(1L);
        mock.setCidade(cidade);

        return mock;
    }

    @Override
    protected void mockJoinColumnsInsert(ClienteDto dto) {
        Cidade cidade = new Cidade();
        cidade.setId(1L);
        Mockito.when(cidadeService.findByIdOrExceptionEntity(dto.getCidade())).thenReturn(cidade);
    }

    @Override
    protected void assertsInInsert(ClienteDto dtoEntrada, ClienteDto dtoRetorno) {
        Assert.assertEquals(dtoEntrada.getNome(), dtoRetorno.getNome());
        Assert.assertEquals(dtoEntrada.getSexo(), dtoRetorno.getSexo());
        Assert.assertEquals(dtoEntrada.getNascimento(), dtoRetorno.getNascimento());
        Assert.assertEquals(dtoEntrada.getIdade(), dtoRetorno.getIdade());
        Assert.assertEquals(dtoEntrada.getCidade(), dtoRetorno.getCidade());
    }


    @Override
    protected ClientePatchDto generateDtoForPatch() {
        ClientePatchDto clientePatchDto = new ClientePatchDto();
        clientePatchDto.setNome("Nome Patch");
        return clientePatchDto;
    }

    @Override
    protected Cliente generateEntityMockForPatch() {
        Cliente cliente = generateEntityMockForInsert();
        cliente.setNome("Nome Patch");
        return cliente;
    }

    @Override
    protected void mockJoinColumnsPatch(ClientePatchDto clientePatchDto) {
    }

    @Override
    protected void assertsInPatch(ClientePatchDto dtoEntrada, ClienteDto dtoRetorno) {
        Assert.assertEquals(dtoEntrada.getNome(), dtoRetorno.getNome());
        Assert.assertNotNull(dtoRetorno.getSexo());
        Assert.assertNotNull(dtoRetorno.getNascimento());
        Assert.assertNotNull(dtoRetorno.getIdade());
        Assert.assertNotNull(dtoRetorno.getCidade());
    }

    @Override
    protected ClienteDto generateDtoForUpdate() {
        return null;
    }

    @Override
    protected Cliente generateEntityMockForUpdate() {
        return null;
    }

    @Override
    protected void mockJoinColumnsUpdate(ClienteDto clienteDto) {

    }

    @Override
    protected void assertsInUpdate(ClienteDto dtoEntrada, ClienteDto dtoRetorno) {

    }

    @Override
    protected boolean isTestUpdate() {
        return false;
    }

    @Override
    protected AbstractService<Cliente, ClienteDto, ClienteDto, ClienteDto, ClientePatchDto, ClienteSpecification> getInstance() {
        return clienteService;
    }
}