package br.com.compasso.backend.specification;

import br.com.compasso.backend.entity.Cliente;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@Spec(path = "nome", spec = Equal.class)
public interface ClienteSpecification extends Specification<Cliente> {
}
