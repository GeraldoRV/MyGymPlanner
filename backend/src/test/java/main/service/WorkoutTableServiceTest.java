package main.service;

import main.dao.WorkoutTableDao;
import main.model.WorkoutTable;
import org.junit.After;
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
public class WorkoutTableServiceTest {
    @Autowired
    private WorkoutTableDao wtDao;
    @Autowired
    private WorkoutTableService wtService;

    @Before
    public void setUp() throws Exception {
        WorkoutTable workoutTable = new WorkoutTable();
        workoutTable.setName("WKT");
        wtDao.save(workoutTable);
    }

    @After
    public void tearDown() throws Exception {
        wtDao.deleteAll();
    }

    @Test
    public void when_getAllTables_returnAllWorkoutTables() {
        List<WorkoutTable> tables = wtService.getAllTables();
        assertEquals(1, tables.size());
    }
}
