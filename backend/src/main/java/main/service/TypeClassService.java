package main.service;

import main.dao.TypeClassDAO;
import main.model.TypeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeClassService {
    @Autowired
    private TypeClassDAO typeClassDAO;

    public List<TypeClass> getAllTypeClass() {
        return (List<TypeClass>) typeClassDAO.findAll();
    }
}
