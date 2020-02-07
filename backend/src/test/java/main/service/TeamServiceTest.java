package main.service;

import main.dao.TeamDAO;
import main.dao.UserDao;
import main.exception.NotValidTeamException;
import main.exception.TeamNotFoundException;
import main.model.Team;
import main.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TeamServiceTest {
    @Autowired
    private TeamDAO teamDAO;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TeamService teamService;

    @After
    public void tearDown() {
        teamDAO.deleteAll();
        userDao.deleteAll();
    }


    @Test
    public void givenAValidNewTeam_whenCreate_returnTheNewTeam() throws NotValidTeamException {
        Team newTeam = initNewTeam();

        Team team = teamService.create(newTeam);

        assertNotNull(team);
        assertEquals(newTeam.getName(), team.getName());

    }

    @Test(expected = NotValidTeamException.class)
    public void givenANewTeamWithNotNameAndLeader_whenCreate_thenExpectationsSatisfied() throws NotValidTeamException {
        Team newTeam = initNewTeam();
        newTeam.setName(null);

        teamService.create(newTeam);

    }

    @Test(expected = NotValidTeamException.class)
    public void givenANewTeamWithNameAndNotLeader_whenCreate_thenExpectationsSatisfied() throws NotValidTeamException {
        Team newTeam = initNewTeam();
        newTeam.setLeader(null);

        teamService.create(newTeam);

    }

    @Test(expected = NotValidTeamException.class)
    public void givenANotValidTeam_whenCreate_thenExpectationsSatisfied() throws NotValidTeamException {
        Team newTeam = new Team();

        teamService.create(newTeam);
    }

    @Test
    public void givenADBWithTeams_whenGetAll_ReturnAListNotEmpty() {
        Team team = initNewTeam();
        teamDAO.save(team);

        List<Team> all = teamService.getAll();

        assertFalse(all.isEmpty());
    }

    @Test
    public void givenADBWithNotTeams_whenGetAll_ReturnAListNotEmpty() {
        List<Team> all = teamService.getAll();

        assertTrue(all.isEmpty());
    }

    @Test
    public void givenAUserIDOfALeader_whenGetTeamOfLeader_returnTheTeam() throws TeamNotFoundException {
        Team newTeam = initNewTeam();
        teamDAO.save(newTeam);
        User leader = newTeam.getLeader();

        Team team = teamService.getTeamOfLeader(leader.getId());

        assertNotNull(team);
        assertEquals(newTeam.getName(), team.getName());
    }

    @Test(expected = TeamNotFoundException.class)
    public void givenAUserIDOfNotALeader_whenGetTeamOfLeader_thenExpectationsSatisfied() throws TeamNotFoundException {
        Team newTeam = initNewTeam();
        teamDAO.save(newTeam);
        User user = new User();
        user.setName("Fran");
        user.setRole("monitor");
        User someUser = userDao.save(user);

        teamService.getTeamOfLeader(someUser.getId());
    }

    @Test(expected = TeamNotFoundException.class)
    public void givenAUserIDOfAMemberNotLeader_whenGetTeamOfLeader_thenExpectationsSatisfied() throws TeamNotFoundException {
        Team newTeam = initNewTeam();
        User user = new User();
        user.setName("Fran");
        user.setRole("monitor");
        User newMember = userDao.save(user);
        newTeam.getMembers().add(newMember);
        teamDAO.save(newTeam);

        teamService.getTeamOfLeader(newMember.getId());
    }

    private Team initNewTeam() {
        Team newTeam = new Team();
        newTeam.setName("Team Test");
        User newLeader = new User();
        newLeader.setName("leader");
        newLeader.setRole("monitor");
        User leader = userDao.save(newLeader);
        newTeam.setLeader(leader);
        return newTeam;
    }
}
