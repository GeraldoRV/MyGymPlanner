package main.dao;

import main.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class DBSeeder implements CommandLineRunner {
    @Autowired
    private GymDao gymDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ExerciseTypeDao exerciseTypeDao;
    @Autowired
    private WorkoutTableDao workoutTableDao;
    @Autowired
    private ClassDirectedDao classDirectedDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void run(String... args) {
        /*Gym gym = gymDao.save(createGym());
        userDao.saveAll(createUsers(gym));
        createWorkoutTable();
        createClassesDirected(gym);*/

    }

    private void createClassesDirected(Gym gym) {
        TypeClass typeClass = newClassDirected(gym, "09:00", "09:45", "Friday", "monitor", null);
        newClassDirected(gym, "10:00", "10:45", "Monday", "monitor", typeClass);
        newClassDirected(gym, "19:15", "20:00", "Wednesday", "noOne", typeClass);
    }

    private TypeClass newClassDirected(Gym gym, String start, String end, String day, String monitor, TypeClass typeClass) {
        ClassDirected classDirected = new ClassDirected();
        classDirected.setGym(gym);

        ClassSchedule classSchedule = new ClassSchedule();
        classSchedule.setStartTime(start);
        classSchedule.setEndTime(end);
        classSchedule.setDayOfWeek(day);
        classDirected.setClassSchedule(classSchedule);
        if (typeClass == null) {
            TypeClass zumba = new TypeClass();
            zumba.setName("Zumba");
            zumba.setDescription("Zumba dancing is best described as a fun, " +
                    "joyful Latin inspired fitness class that combines all the rhythms in Latin music. " +
                    "Zumba applies dance moves from Salsa, Cumbia, Merengue, Rumba, Mambo, Belly Dance, " +
                    "Flamenco, Samba, Tango, Reggaeton and of course Hip Hop.");
            zumba.setDuration(45);
            classDirected.setTypeClass(zumba);
        } else {
            classDirected.setTypeClass(typeClass);
        }
        User user = userDao.findByUserNameAndPassword(monitor, monitor);
        if (user != null) classDirected.setAssignedMonitor(user);
        classDirected.setCapacity(30);
        ClassDirected classDirectedSave = classDirectedDao.save(classDirected);
        return classDirectedSave.getTypeClass();
    }

    private void createWorkoutTable() {
        List<Exercise> exercises = createExercise();
        WorkoutTable workoutTable = new WorkoutTable();
        workoutTable.setExerciseList(exercises);
        workoutTable.setName("Three days workout- First Day");
        User byUserNameAndPassword = userDao.findByUserNameAndPassword("admin", "admin");
        if (byUserNameAndPassword != null) workoutTable.setUser(byUserNameAndPassword);
        workoutTable.setLevel('C');
        workoutTableDao.save(workoutTable);
    }

    private List<Exercise> createExercise() {
        Exercise exerciseToChest1 = new Exercise();
        ExerciseType benchPress = new ExerciseType();
        ExerciseCategory chest = new ExerciseCategory();
        chest.setName("Chest");
        benchPress.setCategory(chest);
        benchPress.setName("Bench Press");
        benchPress.setDescription("Push dumbbells straight up until arms are fully extended " +
                "and dumbbells meet above chest. Return dumbbells until slight stretch is felt in" +
                " chest or shoulder. Repeat");
        ExerciseType benchPressSave = exerciseTypeDao.save(benchPress);
        ExerciseCategory chestCategory = benchPressSave.getCategory();

        exerciseToChest1.setExerciseType(benchPressSave);
        exerciseToChest1.setSets(3);
        exerciseToChest1.setRepetitions(15);

        Exercise exerciseToArm1 = new Exercise();
        ExerciseCategory arm = new ExerciseCategory();
        ExerciseType chestPressCloseGrip = new ExerciseType();
        arm.setName("Arm");
        chestPressCloseGrip.setCategory(arm);
        chestPressCloseGrip.setName("Chest Press - Close Grip");
        chestPressCloseGrip.setDescription("Lie flat on the bench using a close grip " +
                "(about shoulder width). Lift the bar with assistance from the rack, arms locked," +
                " and holding bar straight over you. Inhale and slowly bring the bar down " +
                "toward your chest keeping elbows close to your body for the entire exercise.");
        ExerciseType chestPressCloseGripSave = exerciseTypeDao.save(chestPressCloseGrip);
        ExerciseCategory armCategory = chestPressCloseGripSave.getCategory();

        exerciseToArm1.setExerciseType(chestPressCloseGripSave);
        exerciseToArm1.setSets(3);
        exerciseToArm1.setRepetitions(15);

        Exercise exerciseToChest2 = new Exercise();
        ExerciseType pushUp = new ExerciseType();
        pushUp.setCategory(chestCategory);
        pushUp.setName("Push Up");
        pushUp.setDescription("Get down on all fours, placing your hands " +
                "slightly wider than your shoulders. Straighten your arms and legs. " +
                "Lower your body until your chest nearly touches the floor. " +
                "Pause, then push yourself back up. Repeat.");
        ExerciseType pushUpSave = exerciseTypeDao.save(pushUp);
        exerciseToChest2.setSets(3);
        exerciseToChest2.setRepetitions(12);
        exerciseToChest2.setExerciseType(pushUpSave);

        Exercise exerciseToArm2 = new Exercise();
        ExerciseType tricepsExtensionOverhead = new ExerciseType();
        tricepsExtensionOverhead.setName("Triceps Extension - Overhead (Cable)");
        tricepsExtensionOverhead.setDescription("Extends elbow until arms are straight. Return and repeat");
        tricepsExtensionOverhead.setCategory(armCategory);
        ExerciseType tricepsExtensionOverheadSave = exerciseTypeDao.save(tricepsExtensionOverhead);
        exerciseToArm2.setExerciseType(tricepsExtensionOverheadSave);
        exerciseToArm2.setSets(3);
        exerciseToArm2.setRepetitions(15);

        Exercise exerciseToArm3 = new Exercise();
        ExerciseType benchDip = new ExerciseType();
        benchDip.setCategory(armCategory);
        benchDip.setDescription("Using arms, lower body until slight stretch is felt or" +
                " buttocks touch floor. Return and repeat.");
        benchDip.setName("Bench Dip");
        ExerciseType benchDipSave = exerciseTypeDao.save(benchDip);
        exerciseToArm3.setExerciseType(benchDipSave);
        exerciseToArm3.setSets(3);
        exerciseToArm3.setRepetitions(15);

        Exercise exerciseToAbs = new Exercise();
        ExerciseCategory abs = new ExerciseCategory();
        abs.setName("Abdominal");
        ExerciseType bodyWeightCrunchXBody = new ExerciseType();
        bodyWeightCrunchXBody.setCategory(abs);
        bodyWeightCrunchXBody.setName("BodyWeight Crunch, Cross Body");
        bodyWeightCrunchXBody.setDescription("Curl your body up by bringing your elbow" +
                " and shoulder on one side of your body to your opposite knee.");
        ExerciseType bodyWeightCrunchXBodySave = exerciseTypeDao.save(bodyWeightCrunchXBody);
        exerciseToAbs.setExerciseType(bodyWeightCrunchXBodySave);
        exerciseToAbs.setSets(3);
        exerciseToAbs.setRepetitions(25);
        return Arrays.asList(exerciseToChest1, exerciseToArm1, exerciseToArm2,
                exerciseToChest2, exerciseToArm3, exerciseToAbs);

    }

    private List<User> createUsers(Gym gym) {
        User admin = new User();
        admin.setName("Administrator");
        admin.setUserName("admin");
        admin.setPassword("admin");
        admin.setRole("admin");
        admin.setGym(gym);

        User client = new User();
        client.setName("Client of the gym");
        client.setUserName("client");
        client.setPassword("client");
        client.setRole("client");
        client.setGym(gym);

        User monitor = new User();
        monitor.setName("Monitor of the gym");
        monitor.setUserName("monitor");
        monitor.setPassword("monitor");
        monitor.setRole("monitor");
        monitor.setGym(gym);
        WorkingHours workingHours = new WorkingHours();
        workingHours.setMondayToFriday("08:00 to 15:00");
        workingHours.setSaturday("Free");
        workingHours.setSunday("08:00 to 15:00");
        monitor.setWorkingHours(workingHours);
        return Arrays.asList(admin, monitor, client);
    }

    private Gym createGym() {
        Gym gym = new Gym();
        gym.setName("FalseFit");
        gym.setDirection("Fake Street, nº 14");
        OpeningHours openingHours = new OpeningHours();
        openingHours.setMondaysToFridays("08:00 to 23:00");
        openingHours.setSaturdays("09:00 to 21:00");
        openingHours.setSundaysAndHolidays("08:00 to 15:00");
        gym.setOpeningHours(openingHours);
        return gym;
    }
}
