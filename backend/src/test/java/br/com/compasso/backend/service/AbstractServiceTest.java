package br.com.compasso.backend.service;

import br.com.compasso.backend.dto.IdDto;
import br.com.compasso.backend.entity.IdEntity;
import br.com.compasso.backend.repository.IRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public abstract class AbstractServiceTest<T extends IdEntity, G extends IdDto,  P extends IdDto,  U extends IdDto, A extends  IdDto, S extends Specification<T>> {

    private AbstractService<T, G, P, U, A, S> abstractService;

    @Spy
    private IRepository<T> iRepository;

    @Spy
    private ModelMapper modelMapper;

    @Before
    public void setup() {
        abstractService = getInstance();
    }

    @Test
    public void insertEntity_shouldReturnObjectDto() {
        if (isTestInsert()) {
            P param = generateDtoForInsert();

            T entity = generateEntityMockForInsert();

            Mockito.when(iRepository.save(Mockito.any())).thenReturn(entity);
            mockJoinColumnsInsert(param);

            G dtoReturn = abstractService.insert(param);

            assertsInInsert(param, dtoReturn);
        }
    }

    @Test
    public void updateEntity_shouldReturnObjectDto() {
        if (isTestUpdate()) {
            U param = generateDtoForUpdate();

            T entity = generateEntityMockForUpdate();

            Mockito.when(iRepository.save(Mockito.any())).thenReturn(entity);
            mockJoinColumnsUpdate(param);
            Mockito.when(iRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));

            G dtoReturn = abstractService.update(param, 1L);

            assertsInUpdate(param, dtoReturn);
        }
    }

    @Test
    public void patchEntity_shouldReturnObjectDto() {
        if (isTestPatch()) {
            A param = generateDtoForPatch();

            T entity = generateEntityMockForPatch();

            Mockito.when(iRepository.save(Mockito.any())).thenReturn(entity);
            mockJoinColumnsPatch(param);
            Mockito.when(iRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));

            G dtoReturn = abstractService.patch(param, 1L);

            assertsInPatch(param, dtoReturn);
        }
    }

    protected abstract P generateDtoForInsert();

    protected abstract T generateEntityMockForInsert();

    protected abstract void mockJoinColumnsInsert(P dto);

    protected abstract void assertsInInsert(P dtoEntrada, G dtoRetorno);

    protected abstract U generateDtoForUpdate();

    protected abstract T generateEntityMockForUpdate();

    protected abstract void mockJoinColumnsUpdate(U dto);

    protected abstract void assertsInUpdate(U dtoEntrada, G dtoRetorno);

    protected abstract A generateDtoForPatch();

    protected abstract T generateEntityMockForPatch();

    protected abstract void mockJoinColumnsPatch(A dto);

    protected abstract void assertsInPatch(A dtoEntrada, G dtoRetorno);

    protected boolean isTestInsert() {
        return true;
    }

    protected boolean isTestUpdate() {
        return true;
    }

    protected boolean isTestPatch() {
        return true;
    }

    protected abstract AbstractService<T, G, P, U, A, S> getInstance();
}