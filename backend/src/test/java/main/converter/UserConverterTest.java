package main.converter;

import main.dto.UserTypeMonitorDto;
import main.model.User;
import main.model.WorkingHours;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserConverterTest {
    @Autowired
    private UserConverter userConverter;

    @Test
    public void givenAUserEntity_whenTransformToMonitorTypeFromEntity_thenReturnADto() {
        User user = new User();
        user.setId(1);
        user.setName("Monitor");
        user.setUserName("Monitor");
        user.setWorkingHours(new WorkingHours());
        user.setRole("monitor");

        UserTypeMonitorDto userTypeMonitorDto = userConverter.transformToMonitorTypeFromEntity(user);

        assertNotNull(userTypeMonitorDto);
        assertEquals(user.getId(), userTypeMonitorDto.getId());
        assertEquals(user.getName(), userTypeMonitorDto.getName());
        assertEquals(user.getUserName(), userTypeMonitorDto.getUserName());
        assertNotNull(userTypeMonitorDto.getWorkingHours());
        assertNotNull(userTypeMonitorDto.getnClassAssigned());
    }
}
