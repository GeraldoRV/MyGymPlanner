package main.converter;

import main.dto.TeamDto;
import main.dto.TeamDtoForAdmin;
import main.model.Team;
import org.springframework.stereotype.Service;

@Service
public class TeamConverter {
    private final UserConverter userConverter;

    public TeamConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    public TeamDtoForAdmin transformToTeamDtoAdminFromEntity(Team team) {
        TeamDtoForAdmin teamDtoForAdmin = new TeamDtoForAdmin();
        teamDtoForAdmin.setId(team.getId());
        teamDtoForAdmin.setName(team.getName());
        return teamDtoForAdmin;
    }

    public TeamDto transformToTeamDtoFromEntity(Team team) {
        TeamDto teamDto = new TeamDto();
        teamDto.setId(team.getId());
        teamDto.setName(team.getName());
        if (team.getLeader() != null)
            teamDto.setLeader(userConverter.transformToMonitorTypeFromEntity(team.getLeader()));
        if (team.getMembers() != null)
            teamDto.setMembers(userConverter.transformToMonitorTypeFromEntity(team.getMembers()));
        return teamDto;
    }
}
