package main.converter;

import main.dto.TeamDtoForAdmin;
import main.model.Team;
import org.springframework.stereotype.Service;

@Service
public class TeamConverter {
    public TeamDtoForAdmin transformToTeamDtoAdminFromEntity(Team team) {
        TeamDtoForAdmin teamDtoForAdmin = new TeamDtoForAdmin();
        teamDtoForAdmin.setId(team.getId());
        teamDtoForAdmin.setName(team.getName());
        return teamDtoForAdmin;
    }
}
