package main.service;

import main.dao.TeamDAO;
import main.dao.UserDao;
import main.exception.NotValidTeam;
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

    @Before
    public void setUp()  {

    }

    @After
    public void tearDown()  {
        teamDAO.deleteAll();
        userDao.deleteAll();
    }


    @Test
    public void givenAValidNewTeam_whenCreate_returnTheNewTeam() throws NotValidTeam {
        Team newTeam = new Team();
        newTeam.setName("Team Test");
        User newLeader = new User();
        newLeader.setName("leader");
        newLeader.setRole("monitor");
        User leader = userDao.save(newLeader);
        newTeam.setLeader(leader);

        Team team = teamService.create(newTeam);

        assertNotNull(team);
        assertEquals(newTeam.getName(), team.getName());

    }

    @Test(expected = NotValidTeam.class)
    public void givenANewTeamWithNotNameAndLeader_whenCreate_thenExpectationsSatisfied() throws NotValidTeam {
        Team newTeam = new Team();
        User newLeader = new User();
        newLeader.setName("leader");
        newLeader.setRole("monitor");
        User leader = userDao.save(newLeader);
        newTeam.setLeader(leader);

        teamService.create(newTeam);

    }

    @Test(expected = NotValidTeam.class)
    public void givenANewTeamWithNameAndNotLeader_whenCreate_thenExpectationsSatisfied() throws NotValidTeam {
        Team newTeam = new Team();
        newTeam.setName("Team Test");

        teamService.create(newTeam);

    }

    @Test(expected = NotValidTeam.class)
    public void givenANotValidTeam_whenCreate_thenExpectationsSatisfied() throws NotValidTeam {
        Team newTeam = new Team();

        teamService.create(newTeam);

    }

    @Test
    public void givenADBWithTeams_whenGetAll_ReturnAListNotEmpty() {
        Team team = new Team();
        team.setName("Team T");
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
    public void getTeamOfLeader() {
    }
}
