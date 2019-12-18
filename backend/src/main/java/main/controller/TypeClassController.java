package main.controller;

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

    public TypeClassController(TypeClassService typeClassService) {
        this.typeClassService = typeClassService;
    }

    @GetMapping
    public List<TypeClass> getAll() {
        return typeClassService.getAllTypeClass();
    }
}
