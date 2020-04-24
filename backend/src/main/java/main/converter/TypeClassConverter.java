package main.converter;

import main.dao.ClassDirectedDao;
import main.dto.TypeClassDtoForAdmin;
import main.model.TypeClass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeClassConverter {
    private final ClassDirectedDao classDirectedDao;
    private final TeamConverter teamConverter;

    public TypeClassConverter(ClassDirectedDao classDirectedDao, TeamConverter teamConverter) {
        this.classDirectedDao = classDirectedDao;
        this.teamConverter = teamConverter;
    }

    public TypeClassDtoForAdmin transformToAdminFromEntity(TypeClass typeClass) {
        TypeClassDtoForAdmin typeClassDtoForAdmin = new TypeClassDtoForAdmin();
        typeClassDtoForAdmin.setId(typeClass.getId());
        typeClassDtoForAdmin.setName(typeClass.getName());
        typeClassDtoForAdmin.setnClassesDirected(classDirectedDao.countAllByTypeClass(typeClass));
        if (typeClass.getTeam() != null) {
            typeClassDtoForAdmin.setTeam(teamConverter.transformToTeamDtoAdminFromEntity(typeClass.getTeam()));
        }
        return typeClassDtoForAdmin;
    }

    public List<TypeClassDtoForAdmin> transformToAdminFromEntityList(List<TypeClass> typeClassList) {
        List<TypeClassDtoForAdmin> typeClassDtoForAdminList = new ArrayList<>();
        typeClassList.forEach(
                typeClass ->
                        typeClassDtoForAdminList.add(transformToAdminFromEntity(typeClass)));
        return typeClassDtoForAdminList;
    }
}
