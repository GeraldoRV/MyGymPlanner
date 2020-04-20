package main.controller;

import main.converter.TypeClassConverter;
import main.dto.TypeClassDtoForAdmin;
import main.model.TypeClass;
import main.service.TypeClassService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/admin")
    public List<TypeClassDtoForAdmin> getAllTypeClassForAdmin() {
        return typeClassConverter.transformToAdminFromEntityList(typeClassService.getAllTypeClass());
    }

    @GetMapping
    public List<TypeClass> getAll() {
        return typeClassService.getAllTypeClass();
    }
}
