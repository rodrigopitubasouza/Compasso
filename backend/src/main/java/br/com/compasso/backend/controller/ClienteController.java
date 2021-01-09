package br.com.compasso.backend.controller;

import br.com.compasso.backend.dto.ClienteDto;
import br.com.compasso.backend.dto.ClientePatchDto;
import br.com.compasso.backend.exception.MethodNotImplemented;
import br.com.compasso.backend.service.ClienteService;
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
@RequestMapping(value = "/cliente")
public class ClienteController implements IController<ClienteDto, ClienteDto, ClienteDto, ClientePatchDto> {

    @Autowired
    private ClienteService clienteService;

    @Override
    @GetMapping
    public ResponseEntity<Page<ClienteDto>> getObjectList(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) final Pageable page) {
        Page<ClienteDto> dados = clienteService.findAll(page);
        if (dados == null || dados.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(dados);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getObject(@PathVariable("id") Long id) {
        ClienteDto clienteDto = clienteService.findByIdOrException(id);
        if (clienteDto == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clienteDto);
    }

    @Override
    @PostMapping
    public ResponseEntity<ClienteDto> postObject(@Valid @RequestBody ClienteDto dto) {
        ClienteDto clienteDto = clienteService.insert(dto);
        return ResponseEntity.created(null).body(clienteDto);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateObject(@PathVariable("id") Long id, @RequestBody ClienteDto dto) {
        throw new MethodNotImplemented("PUT");
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObject(@PathVariable("id") Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<ClienteDto> patchObject(@PathVariable("id") Long id, @Valid @RequestBody ClientePatchDto dto) {
        clienteService.patch(dto, id);
        return ResponseEntity.noContent().build();
    }
}
