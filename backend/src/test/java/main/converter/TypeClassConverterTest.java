package main.converter;

import main.dao.TypeClassDAO;
import main.dto.TypeClassDtoForAdmin;
import main.model.TypeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TypeClassConverterTest {
    @Autowired
    private TypeClassDAO typeClassDAO;
    @Autowired
    private TypeClassConverter typeClassConverter;

    @Test
    public void givenATypeClassWithNotTeam_whenTransformToAdminFromEntity_returnAObjectDto() {
        TypeClass typeClass = new TypeClass();
        typeClass.setName("test type");
        typeClass.setDescription("Test description");
        typeClass.setDuration(45);
        TypeClass typeClassDB = typeClassDAO.save(typeClass);

        TypeClassDtoForAdmin typeClassDtoForAdmin = typeClassConverter.transformToAdminFromEntity(typeClassDB);

        assertEquals(typeClassDB.getId(), typeClassDtoForAdmin.getId());
        assertEquals(typeClassDB.getName(), typeClassDtoForAdmin.getName());
        assertEquals(0, typeClassDtoForAdmin.getnClassesDirected());
        assertNull(typeClassDtoForAdmin.getTeam());
    }

    @Test
    public void givenATypeClassList_whenTransformToAdminFromEntityList_returnAListObjectDto() {
        TypeClass typeClass = new TypeClass();
        typeClass.setName("test type");
        typeClass.setDescription("Test description");
        typeClass.setDuration(45);
        typeClassDAO.save(typeClass);
        List<TypeClass> typeClassList = (List<TypeClass>) typeClassDAO.findAll();

        List<TypeClassDtoForAdmin> typeClassDtoForAdminList = typeClassConverter.transformToAdminFromEntityList(typeClassList);

        assertEquals(1, typeClassDtoForAdminList.size());
    }

    @After
    public void tearDown() {
        typeClassDAO.deleteAll();
    }
}
