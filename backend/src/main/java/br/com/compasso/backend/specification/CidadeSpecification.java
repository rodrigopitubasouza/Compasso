package br.com.compasso.backend.specification;

import br.com.compasso.backend.entity.Cidade;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Or({
    @Spec(path = "nome", spec = Equal.class),
    @Spec(path = "estado", spec = Equal.class)
})
public interface CidadeSpecification extends Specification<Cidade> {
}
