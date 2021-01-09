package br.com.compasso.backend.controller;

import br.com.compasso.backend.dto.CidadeDto;
import br.com.compasso.backend.exception.MethodNotImplemented;
import br.com.compasso.backend.service.CidadeService;
import br.com.compasso.backend.specification.CidadeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cidade")
public class CidadeController implements IController<CidadeDto, CidadeDto, CidadeDto, CidadeDto, CidadeSpecification> {

    @Autowired
    private CidadeService cidadeService;

    @Override
    @GetMapping
    public ResponseEntity<Page<CidadeDto>> getObjectList(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) final Pageable page,
                                                         CidadeSpecification search) {
        Page<CidadeDto> dados = cidadeService.findAll(search, page);
        if (dados == null || dados.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dados);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CidadeDto> getObject(@PathVariable("id") Long id) {
        CidadeDto CidadeDto = cidadeService.findByIdOrException(id);
        if (CidadeDto == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(CidadeDto);
    }

    @Override
    @PostMapping
    public ResponseEntity<CidadeDto> postObject(@Valid @RequestBody CidadeDto dto) {
        CidadeDto CidadeDto = cidadeService.insert(dto);
        return ResponseEntity.created(null).body(CidadeDto);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CidadeDto> updateObject(@PathVariable("id") Long id, @RequestBody CidadeDto dto) {
        throw new MethodNotImplemented("PUT");
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObject(@PathVariable("id") Long id) {
        throw new MethodNotImplemented("DELETE");
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<CidadeDto> patchObject(@PathVariable("id") Long id, @Valid @RequestBody CidadeDto dto) {
        throw new MethodNotImplemented("PATCH");
    }
}
