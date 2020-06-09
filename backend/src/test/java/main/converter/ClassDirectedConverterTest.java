package main.converter;

import main.dto.ClassDirectedDtoToAssign;
import main.model.ClassDirected;
import main.model.ClassSchedule;
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
public class ClassDirectedConverterTest {
    @Autowired
    private ClassDirectedConverter classDirectedConverter;

    @Test
    public void givenAClassDirectedEntity_whenTransformToClassDtoToAssignFromEntity_thenReturnADto() {
        ClassDirected classDirected = new ClassDirected();
        classDirected.setId(1);
        classDirected.setClassSchedule(new ClassSchedule());

        ClassDirectedDtoToAssign classDirectedDtoToAssign = classDirectedConverter.transformToClassDtoToAssignFromEntity(classDirected);

        assertNotNull(classDirectedDtoToAssign);
        assertEquals(classDirected.getId(),classDirectedDtoToAssign.getId());
        assertNotNull(classDirectedDtoToAssign.getClassSchedule());
        assertNull(classDirectedDtoToAssign.getAssignedMonitor());
    }
}
