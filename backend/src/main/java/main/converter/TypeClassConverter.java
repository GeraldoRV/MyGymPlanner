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

    public TypeClassConverter(ClassDirectedDao classDirectedDao) {
        this.classDirectedDao = classDirectedDao;
    }

    public TypeClassDtoForAdmin transformToAdminFromEntity(TypeClass typeClass) {
        TypeClassDtoForAdmin typeClassDtoForAdmin = new TypeClassDtoForAdmin();
        typeClassDtoForAdmin.setId(typeClass.getId());
        typeClassDtoForAdmin.setName(typeClass.getName());
        int allByTypeClass = classDirectedDao.countAllByTypeClass(typeClass);
        typeClassDtoForAdmin.setnClassesDirected(allByTypeClass);
        typeClassDtoForAdmin.setnClassesAssigned(classDirectedDao.countAllByTypeClassAndAssignedMonitorIsNotNull(typeClass));
        return typeClassDtoForAdmin;
    }

    public List<TypeClassDtoForAdmin> transformToAdminFromEntityList(List<TypeClass> typeClassList) {
        List<TypeClassDtoForAdmin> typesClassDtoForAdmin = new ArrayList<>();
        for (TypeClass typeClass : typeClassList) {
            typesClassDtoForAdmin.add(transformToAdminFromEntity(typeClass));
        }
        return typesClassDtoForAdmin;
    }
}
