package main.converter;

import main.dto.ClassDirectedDtoToAssign;
import main.model.ClassDirected;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassDirectedConverter {
    private final UserConverter userConverter;
    private final TypeClassConverter typeClassConverter;

    public ClassDirectedConverter(UserConverter userConverter, TypeClassConverter typeClassConverter) {
        this.userConverter = userConverter;
        this.typeClassConverter = typeClassConverter;
    }

    public ClassDirectedDtoToAssign transformToClassDtoToAssignFromEntity(ClassDirected classDirected) {
        ClassDirectedDtoToAssign classDirectedDtoToAssign = new ClassDirectedDtoToAssign();

        classDirectedDtoToAssign.setId(classDirected.getId());
        classDirectedDtoToAssign.setClassSchedule(classDirected.getClassSchedule());

        if (classDirected.getAssignedMonitor() != null) {
            classDirectedDtoToAssign
                    .setAssignedMonitor(userConverter
                            .transformToMonitorTypeFromEntity(classDirected.getAssignedMonitor()));
        }

        if (classDirected.getTypeClass() != null) {
            classDirectedDtoToAssign.setTypeClass(typeClassConverter
                    .transformToLeaderFromEntity(classDirected.getTypeClass()));
        }

        return classDirectedDtoToAssign;
    }

    public List<ClassDirectedDtoToAssign> transformToClassDtoToAssignFromEntity(List<ClassDirected> classDirectedList) {
        List<ClassDirectedDtoToAssign> classDirectedDtoToAssignList = new ArrayList<>();
        classDirectedList.forEach(classDirected ->
                classDirectedDtoToAssignList.add(transformToClassDtoToAssignFromEntity(classDirected)));
        return classDirectedDtoToAssignList;
    }
}
