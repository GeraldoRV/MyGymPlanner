package main.service;

import main.dao.TeamDAO;
import main.dao.TypeClassDAO;
import main.dto.TypeClassDtoForAdmin;
import main.exception.TeamNotFoundException;
import main.exception.TypeClassNotFound;
import main.model.Team;
import main.model.TypeClass;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TypeClassServiceTest {
    @Autowired
    private TypeClassService typeClassService;
    @Autowired
    private TypeClassDAO typeClassDAO;
    @Autowired
    private TeamDAO teamDAO;

    @After
    public void tearDown() {
        typeClassDAO.deleteAll();
        teamDAO.deleteAll();
    }

    @Test(expected = TypeClassNotFound.class)
    public void givenANotExistTypeClass_whenAddTeam_thenExpectationsSatisfied() {
        TypeClassDtoForAdmin typeClassDtoForAdmin = new TypeClassDtoForAdmin();
        typeClassDtoForAdmin.setId(1);
        typeClassDtoForAdmin.setName("GAP");

        typeClassService.addTeam(typeClassDtoForAdmin, 1);
    }

    @Test(expected = TeamNotFoundException.class)
    public void givenAExistTypeClassAndNotValidTeamId_whenAddTeam_thenExpectationsSatisfied() {
        TypeClass typeClass = new TypeClass();
        typeClass.setName("GAP");
        typeClassDAO.save(typeClass);
        TypeClassDtoForAdmin typeClassDtoForAdmin = new TypeClassDtoForAdmin();
        typeClassDtoForAdmin.setId(typeClass.getId());
        typeClassDtoForAdmin.setName("GAP");

        typeClassService.addTeam(typeClassDtoForAdmin, 1);
    }

    @Test
    public void givenAExistTypeClassAndAValidTeamId_whenAddTeam_returnTheTypeClassWithTheTeamAdded() {
        TypeClass typeClass = new TypeClass();
        typeClass.setName("GAP");
        typeClassDAO.save(typeClass);
        TypeClassDtoForAdmin typeClassDtoForAdmin = new TypeClassDtoForAdmin();
        typeClassDtoForAdmin.setId(typeClass.getId());
        typeClassDtoForAdmin.setName("GAP");
        Team team = new Team();
        team.setName("A");
        teamDAO.save(team);
        assertNull(typeClass.getTeam());

        TypeClass typeClassWithTeam = typeClassService.addTeam(typeClassDtoForAdmin, team.getId());

        assertNotNull(typeClassWithTeam);
        assertNotNull(typeClassWithTeam.getTeam());

    }
}
