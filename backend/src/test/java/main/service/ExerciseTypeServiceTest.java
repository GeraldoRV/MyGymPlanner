package main.service;

import main.dao.ExerciseTypeDao;
import main.model.ExerciseType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ExerciseTypeServiceTest {
    @Autowired
    private ExerciseTypeDao exerciseTypeDao;
    @Autowired
    private ExerciseTypeService exerciseTypeService;

    @Before
    public void setUp() {
        ExerciseType exerciseType = new ExerciseType();
        exerciseType.setName("Type1");
        exerciseTypeDao.save(exerciseType);
    }

    @Test
    public void whenGetAll_returnAList() {
        List<ExerciseType> all = exerciseTypeService.getAll();
        assertFalse(all.isEmpty());
    }
}
