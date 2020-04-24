package main.service;

import main.dao.TypeClassDAO;
import main.model.TypeClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeClassService {
    private final TypeClassDAO typeClassDAO;

    public TypeClassService(TypeClassDAO typeClassDAO) {
        this.typeClassDAO = typeClassDAO;
    }

    public List<TypeClass> getAll() {
        return (List<TypeClass>) typeClassDAO.findAll();
    }
}
