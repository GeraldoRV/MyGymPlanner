package main.controller;

import main.converter.TypeClassConverter;
import main.dto.TypeClassDtoForAdmin;
import main.dto.TypeClassDtoForLeader;
import main.model.TypeClass;
import main.service.TypeClassService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("type-class")
@CrossOrigin(origins = "*")
public class TypeClassController {
    private final TypeClassService typeClassService;
    private final TypeClassConverter typeClassConverter;

    public TypeClassController(TypeClassService typeClassService, TypeClassConverter typeClassConverter) {
        this.typeClassService = typeClassService;
        this.typeClassConverter = typeClassConverter;
    }

    @GetMapping("admin")
    public List<TypeClassDtoForAdmin> getAllTypeClassForAdmin() {
        return typeClassConverter.transformToAdminFromEntityList(typeClassService.getAll());
    }

    @GetMapping
    public List<TypeClass> getAll() {
        return typeClassService.getAll();
    }

    @GetMapping("team/{id}")
    public List<TypeClassDtoForLeader> getAllByTeam(@PathVariable Integer id) {
        return typeClassConverter.transformToLeaderFromEntity(typeClassService.getAllByTeam(id));
    }

    @PutMapping("add-team/{teamId}")
    public TypeClassDtoForAdmin addTeam(@Valid @RequestBody TypeClassDtoForAdmin typeClassDtoForAdmin, @PathVariable Integer teamId) {
        return typeClassConverter.transformToAdminFromEntity(typeClassService.addTeam(typeClassDtoForAdmin, teamId));
    }
}
