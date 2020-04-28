package main.service;

import main.dao.TeamDAO;
import main.dao.TypeClassDAO;
import main.dto.TypeClassDtoForAdmin;
import main.exception.TeamNotFoundException;
import main.exception.TypeClassNotFound;
import main.model.TypeClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeClassService {
    private final TypeClassDAO typeClassDAO;
    private final TeamDAO teamDAO;

    public TypeClassService(TypeClassDAO typeClassDAO, TeamDAO teamDAO) {
        this.typeClassDAO = typeClassDAO;
        this.teamDAO = teamDAO;
    }

    public List<TypeClass> getAll() {
        return (List<TypeClass>) typeClassDAO.findAll();
    }

    public void addTeam(TypeClassDtoForAdmin typeClassDtoForAdmin, Integer teamId) {
        TypeClass typeClass = typeClassDAO.findById(typeClassDtoForAdmin.getId())
                .orElseThrow(() ->
                        new TypeClassNotFound("The type class " + typeClassDtoForAdmin.getName() + "doesn't exist"));
        typeClass.setTeam(teamDAO.findById(teamId).orElseThrow(TeamNotFoundException::new));
        typeClassDAO.save(typeClass);
    }
}

