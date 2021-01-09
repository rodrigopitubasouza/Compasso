package br.com.compasso.backend.service;

import br.com.compasso.backend.dto.CidadeDto;
import br.com.compasso.backend.entity.Cidade;
import br.com.compasso.backend.specification.CidadeSpecification;
import org.junit.Assert;
import org.mockito.InjectMocks;

public class CidadeServiceTest extends AbstractServiceTest<Cidade, CidadeDto, CidadeDto, CidadeDto, CidadeDto, CidadeSpecification>{

    @InjectMocks
    private CidadeService cidadeService;


    @Override
    protected CidadeDto generateDtoForInsert() {
        CidadeDto cidade = new CidadeDto();
        cidade.setNome("nome");
        cidade.setEstado("estado");
        return cidade;
    }

    @Override
    protected Cidade generateEntityMockForInsert() {
        Cidade mock = new Cidade();
        mock.setNome("nome");
        mock.setEstado("estado");

        return mock;
    }

    @Override
    protected void assertsInInsert(CidadeDto dtoEntrada, CidadeDto dtoRetorno) {
        Assert.assertEquals(dtoEntrada.getNome(), dtoRetorno.getNome());
        Assert.assertEquals(dtoEntrada.getEstado(), dtoRetorno.getEstado());
    }


    @Override
    protected CidadeDto generateDtoForPatch() {
        return null;
    }

    @Override
    protected Cidade generateEntityMockForPatch() {
        return null;
    }

    @Override
    protected void mockJoinColumnsPatch(CidadeDto clientePatchDto) {
    }

    @Override
    protected void assertsInPatch(CidadeDto dtoEntrada, CidadeDto dtoRetorno) {
    }

    @Override
    protected CidadeDto generateDtoForUpdate() {
        return null;
    }

    @Override
    protected Cidade generateEntityMockForUpdate() {
        return null;
    }

    @Override
    protected void mockJoinColumnsUpdate(CidadeDto clienteDto) {
    }

    @Override
    protected void assertsInUpdate(CidadeDto dtoEntrada, CidadeDto dtoRetorno) {
    }

    @Override
    protected boolean isTestUpdate() {
        return false;
    }

    @Override
    protected boolean isTestPatch() {
        return false;
    }

    @Override
    protected void mockJoinColumnsInsert(CidadeDto dto) {
    }


    @Override
    protected AbstractService<Cidade, CidadeDto, CidadeDto, CidadeDto, CidadeDto, CidadeSpecification> getInstance() {
        return cidadeService;
    }
}