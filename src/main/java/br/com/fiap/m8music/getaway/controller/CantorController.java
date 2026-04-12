package br.com.fiap.m8music.getaway.controller;

import br.com.fiap.m8music.domain.Cantor;
import br.com.fiap.m8music.getaway.dto.cantor.CantorCreateDTO;
import br.com.fiap.m8music.getaway.dto.cantor.CantorDTO;
import br.com.fiap.m8music.service.CantorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/cantores")
@RequiredArgsConstructor
public class CantorController {
    private final CantorService service;

    @PostMapping
    public ResponseEntity<EntityModel<CantorDTO>> create(@RequestBody @Valid CantorCreateDTO body) {

        CantorDTO cantor = service.create(body);

        EntityModel<CantorDTO> entityModel = EntityModel.of(cantor,
                linkTo(methodOn(CantorController.class).get(cantor.id())).withSelfRel());

        return ResponseEntity.created(entityModel.getRequiredLink("self").toUri())
                .body(entityModel);
    }

    @GetMapping
    public ResponseEntity<EntityModel<List<CantorDTO>>> list() {
        EntityModel<List<CantorDTO>> response = EntityModel.of(service.list());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Cantor>> get(@PathVariable Long id) {
        Cantor cantor = service.get(id);

        EntityModel<Cantor> cantorModel = EntityModel.of(cantor,
                linkTo(methodOn(CantorController.class).get(id)).withSelfRel(),
                linkTo(methodOn(CantorController.class).list()).withRel("lista-cantores"));

        return ResponseEntity.ok(cantorModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Cantor>> update(@PathVariable Long id, @RequestBody @Valid CantorDTO body) {
        Cantor cantor = service.update(id, body);

        EntityModel<Cantor> cantorModel = EntityModel.of(cantor,
                linkTo(methodOn(CantorController.class).update(id, null)).withSelfRel(),
                linkTo(methodOn(CantorController.class).list()).withRel("lista-cantores")
        );

        return ResponseEntity.ok(cantorModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
