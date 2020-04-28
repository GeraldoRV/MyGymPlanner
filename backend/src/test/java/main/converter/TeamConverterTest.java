package main.converter;

import main.dto.TeamDtoForAdmin;
import main.model.Team;
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
public class TeamConverterTest {
    @Autowired
    private TeamConverter teamConverter;

    @After
    public void tearDown() {
    }

    @Test
    public void givenATeamEntity_whenTransformToTeamDtoAdminFromEntity_returnADtoOfTheEntity() {
        Team team = new Team();
        team.setId(1);
        team.setName("Team T");

        TeamDtoForAdmin teamDtoForAdmin = teamConverter.transformToTeamDtoAdminFromEntity(team);

        assertEquals(team.getId(), teamDtoForAdmin.getId());
        assertEquals(team.getName(), teamDtoForAdmin.getName());
    }
}
