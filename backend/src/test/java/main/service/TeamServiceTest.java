package main.service;

import main.dao.TeamDAO;
import main.dao.UserDao;
import main.exception.NotValidTeamException;
import main.exception.TeamNotFoundException;
import main.model.Team;
import main.model.User;
import org.junit.After;
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
        Team newTeam = initNewTeam("Team T", "Leader T", null);

        Team team = teamService.create(newTeam);

        assertNotNull(team);
        assertEquals(newTeam.getName(), team.getName());

    }

    @Test(expected = NotValidTeamException.class)
    public void givenANewTeamWithNotNameAndLeader_whenCreate_thenExpectationsSatisfied() throws NotValidTeamException {
        Team newTeam = initNewTeam(null, "Leader T", null);
        newTeam.setName(null);

        teamService.create(newTeam);

    }

    @Test(expected = NotValidTeamException.class)
    public void givenANewTeamWithNameAndNotLeader_whenCreate_thenExpectationsSatisfied() throws NotValidTeamException {
        Team newTeam = initNewTeam("Team T", null, null);

        teamService.create(newTeam);

    }

    @Test(expected = NotValidTeamException.class)
    public void givenANotValidTeam_whenCreate_thenExpectationsSatisfied() throws NotValidTeamException {
        Team newTeam = new Team();

        teamService.create(newTeam);
    }

    @Test(expected = NotValidTeamException.class)
    public void givenANewTeamWithLeaderOfOtherTeam_whenCreate_thenExpectationSatisfied() throws NotValidTeamException {
        Team newTeam = initNewTeam("Team T", "Leader T", null);
        Team newTeam1 = initNewTeam("Team T2", null, null);
        Team team = teamService.create(newTeam);
        newTeam1.setLeader(team.getLeader());

        teamService.create(newTeam1);
    }

    @Test(expected = NotValidTeamException.class)
    public void givenANewTeamWithMembersOfOtherTeam_whenCreate_thenExpectationSatisfied()
            throws NotValidTeamException {
        Team newTeam = initNewTeam("Team T", "Leader T", "Member 1");
        Team newTeam1 = initNewTeam("Team T2", "Leader T2", null);
        Team team = teamService.create(newTeam);
        newTeam1.getMembers().add(team.getMembers().get(0));

        teamService.create(newTeam1);
    }

    @Test
    public void givenADBWithTeams_whenGetAll_ReturnAListNotEmpty() {
        Team team = initNewTeam("Team T", "Leader T", null);
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
    public void givenAUserIDOfALeader_whenGetTeamOfLeader_returnTheTeam() {
        Team newTeam = initNewTeam("Team T", "Leader T", null);
        teamDAO.save(newTeam);
        User leader = newTeam.getLeader();

        Team team = teamService.getTeamOfLeader(leader.getId());

        assertNotNull(team);
        assertEquals(newTeam.getName(), team.getName());
    }

    @Test(expected = TeamNotFoundException.class)
    public void givenAUserIDOfNotALeader_whenGetTeamOfLeader_thenExpectationsSatisfied() {
        Team newTeam = initNewTeam("Team T", "Leader T", null);
        teamDAO.save(newTeam);
        User someUser = createUser("Fran");

        teamService.getTeamOfLeader(someUser.getId());
    }

    @Test(expected = TeamNotFoundException.class)
    public void givenAUserIDOfAMemberNotLeader_whenGetTeamOfLeader_thenExpectationsSatisfied() {
        Team newTeam = initNewTeam("Team T", "Leader T", "Member");
        Team team = teamDAO.save(newTeam);
        User member = team.getMembers().get(0);

        teamService.getTeamOfLeader(member.getId());
    }

    private Team initNewTeam(String teamName, String leaderName, String memberName) {
        Team newTeam = new Team();
        newTeam.setName(teamName);
        if (leaderName != null) {
            User leader = createUser(leaderName);
            newTeam.setLeader(leader);
        }
        if (memberName != null) {
            User newMember = createUser(memberName);
            newTeam.getMembers().add(newMember);
        }
        return newTeam;
    }

    private User createUser(String userName) {
        User user = new User();
        user.setName(userName);
        user.setRole("monitor");
        return userDao.save(user);

    }
}
