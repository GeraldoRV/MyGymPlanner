package main.converter;

import main.dao.ClassDirectedDao;
import main.dto.UserTypeMonitorDto;
import main.exception.UserNotFoundException;
import main.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserConverter {
    private final ClassDirectedDao classDirectedDao;

    public UserConverter(ClassDirectedDao classDirectedDao) {
        this.classDirectedDao = classDirectedDao;
    }

    public UserTypeMonitorDto transformToMonitorTypeFromEntity(User user) {
        UserTypeMonitorDto userTypeMonitorDto = new UserTypeMonitorDto();
        if (!user.getRole().equals("monitor"))
            throw new UserNotFoundException("The user with userName "+user.getUserName() + " is not a monitor");

        userTypeMonitorDto.setId(user.getId());
        userTypeMonitorDto.setName(user.getName());
        userTypeMonitorDto.setUserName(user.getUserName());
        userTypeMonitorDto.setLeader(user.isLeader());
        userTypeMonitorDto.setWorkingHours(user.getWorkingHours());
        userTypeMonitorDto.setnClassAssigned(classDirectedDao.countAllByAssignedMonitor_Id(user.getId()));

        return userTypeMonitorDto;
    }

    public List<UserTypeMonitorDto> transformToMonitorTypeFromEntity(List<User> users) {
        List<UserTypeMonitorDto> userTypeMonitorDtoList = new ArrayList<>();
        users.forEach(
                user ->
                        userTypeMonitorDtoList.add(transformToMonitorTypeFromEntity(user)));
        return userTypeMonitorDtoList;
    }
}
