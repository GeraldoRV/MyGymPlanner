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
    @Autowired
    private TypeClassDAO typeClassDAO;
    private ExerciseCategory armCategory;
    private ExerciseType bodyWeightCrunchXBodySave;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void run(String... args) {
/*
        Gym gym = gymDao.save(createGym());
        userDao.saveAll(createUsers(gym));
        createWorkoutTables();
        createClassesDirected(gym);
*/
    }

    private void createClassesDirected(Gym gym) {
        TypeClass gap = newTypeClass("GAP", 45, "The GAP is a maintenance activity that tries to firm and harden our Buttocks, Abdominals and Legs");
        TypeClass cross_fit = newTypeClass("Cross Fit", 60, "CrossFit is defined as a system of strength training and conditioning based on constantly varied functional exercises performed at a high intensity.");
        TypeClass pilates = newTypeClass("Pilates", 45, "Pilates exercises are part of an integral method that helps move the deeper muscles of the body, and thus achieve good posture. ... Pilates proposes an \"intelligent\" technique that allows strengthening the muscles of the shoulders, chest and back.");
        TypeClass cycling = newTypeClass("Indoor Cycle", 45, "The indoor cycle, as the name implies, is cycling indoors. This physical activity is very similar to spinning, they allow us to lose weight and burn calories, improve heart and lung resistance and tone our legs.");
        TypeClass boxing = newTypeClass("Boxing", 60, "Boxing is a combat sport in which two people, usually wearing protective gloves, throw punches at each other for a predetermined amount of time in a boxing ring");
        TypeClass zumba = newClassDirected(gym, "09:00", "09:45", "Monday", "monitor", null);
        newClassDirected(gym, "09:45", "10:30", "Monday", "monitor", pilates);
        newClassDirected(gym, "10:30", "11:30", "Tuesday", "noOne", cross_fit);
        newClassDirected(gym, "10:30", "11:30", "Tuesday", "monitor", boxing);
        newClassDirected(gym, "12:15", "13:00", "Wednesday", "monitor", gap);
        newClassDirected(gym, "17:00", "17:45", "Wednesday", "noOne", zumba);
        newClassDirected(gym, "09:45", "10:00", "Thursday", "noOne", cycling);
        newClassDirected(gym, "10:00", "10:45", "Thursday", "monitor", zumba);
        newClassDirected(gym, "09:00", "09:45", "Friday", "monitor", zumba);
        newClassDirected(gym, "09:45", "10:00", "Friday", "monitor", gap);
        newClassDirected(gym, "10:00", "10:45", "Saturday", "noOne", boxing);
        newClassDirected(gym, "17:00", "17:45", "Saturday", "noOne", boxing);
        newClassDirected(gym, "10:00", "10:45", "Sunday", "monitor", cycling);
        newClassDirected(gym, "13:15", "14:00", "Sunday", "noOne", cycling);
    }


    private TypeClass newTypeClass(String name, Integer duration, String description) {
        TypeClass typeClass = new TypeClass();
        typeClass.setName(name);
        typeClass.setDuration(duration);
        typeClass.setDescription(description);
        return typeClassDAO.save(typeClass);
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

    private void createWorkoutTables() {
        createWorkoutTableFirst();
        createWorkoutTableSecond();
        createWorkoutTableThird();
    }

    private void createWorkoutTableFirst() {
        List<Exercise> exercises = createExerciseFirst();
        WorkoutTable workoutTable = new WorkoutTable();
        workoutTable.setExerciseList(exercises);
        workoutTable.setName("Three days workout- First Day");
        User byUserNameAndPassword = userDao.findByUserNameAndPassword("admin", "admin");
        if (byUserNameAndPassword != null) workoutTable.setUser(byUserNameAndPassword);
        workoutTable.setLevel('C');
        workoutTableDao.save(workoutTable);
    }

    private void createWorkoutTableSecond() {
        List<Exercise> exercises = createExerciseSecond();
        WorkoutTable workoutTable = new WorkoutTable();
        workoutTable.setExerciseList(exercises);
        workoutTable.setName("Three days workout- Second Day");
        User byUserNameAndPassword = userDao.findByUserNameAndPassword("admin", "admin");
        if (byUserNameAndPassword != null) workoutTable.setUser(byUserNameAndPassword);
        workoutTable.setLevel('C');
        workoutTableDao.save(workoutTable);
    }

    private void createWorkoutTableThird() {
        List<Exercise> exercises = createExerciseThird();
        WorkoutTable workoutTable = new WorkoutTable();
        workoutTable.setExerciseList(exercises);
        workoutTable.setName("Three days workout- Third Day");
        User byUserNameAndPassword = userDao.findByUserNameAndPassword("admin", "admin");
        if (byUserNameAndPassword != null) workoutTable.setUser(byUserNameAndPassword);
        workoutTable.setLevel('C');
        workoutTableDao.save(workoutTable);
    }

    private List<Exercise> createExerciseFirst() {
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
        armCategory = chestPressCloseGripSave.getCategory();

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
        tricepsExtensionOverhead.setDescription("Extends elbow until arms are straight." +
                " Return and repeat");
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
        bodyWeightCrunchXBodySave = exerciseTypeDao.save(bodyWeightCrunchXBody);
        exerciseToAbs.setExerciseType(bodyWeightCrunchXBodySave);
        exerciseToAbs.setSets(3);
        exerciseToAbs.setRepetitions(25);
        return Arrays.asList(exerciseToChest1, exerciseToArm1, exerciseToArm2,
                exerciseToChest2, exerciseToArm3, exerciseToAbs);

    }

    private List<Exercise> createExerciseSecond() {
        Exercise exerciseToBack1 = new Exercise();
        ExerciseType seatedRow = new ExerciseType();
        ExerciseCategory back = new ExerciseCategory();
        back.setName("Back");
        seatedRow.setCategory(back);
        seatedRow.setName("Seated Row (Cable)");
        seatedRow.setDescription("Seated with back straight, " +
                "knees slightly bent and arms fully extended. Grasp the close grip" +
                " handle with palms facing each other. Extend your body forward, " +
                "bending at the hips. Keep your back flat. Bring your arms in towards you" +
                " keeping your elbows close to your sides.");
        ExerciseType seatedRowSave = exerciseTypeDao.save(seatedRow);
        ExerciseCategory backCategory = seatedRowSave.getCategory();

        exerciseToBack1.setExerciseType(seatedRowSave);
        exerciseToBack1.setSets(3);
        exerciseToBack1.setRepetitions(12);

        Exercise exerciseToBack2 = new Exercise();
        ExerciseType proLatBarPullDown = new ExerciseType();
        proLatBarPullDown.setCategory(backCategory);
        proLatBarPullDown.setName("Pro-Lat-Bar Pull down(cable)");
        proLatBarPullDown.setDescription("Grasp pro lat bar with wide parallel grip. " +
                "Sit with thighs under supports. Pull down cable bar to upper chest. " +
                "Return until arms and shoulders are fully extended.");
        ExerciseType proLatBarPullDownSave = exerciseTypeDao.save(proLatBarPullDown);

        exerciseToBack2.setExerciseType(proLatBarPullDownSave);
        exerciseToBack2.setSets(3);
        exerciseToBack2.setRepetitions(12);

        Exercise exerciseToArm1 = new Exercise();
        ExerciseType bicepsCurl = new ExerciseType();
        bicepsCurl.setCategory(armCategory);
        bicepsCurl.setName("Biceps Curl (Cable)");
        bicepsCurl.setDescription("Grab the cable bar at shoulder width and keep the " +
                "elbows close to the torso. The palm of your hands should be facing up" +
                " (supinated grip). This will be your starting position. While holding the " +
                "upper arms stationary, curl the weights while contracting the biceps as you" +
                " breathe out.");
        ExerciseType bicepsCurlSave = exerciseTypeDao.save(bicepsCurl);
        exerciseToArm1.setSets(3);
        exerciseToArm1.setRepetitions(15);
        exerciseToArm1.setExerciseType(bicepsCurlSave);

        Exercise exerciseToArm2 = new Exercise();
        ExerciseType bicepsCurlDumbbell = new ExerciseType();
        bicepsCurlDumbbell.setName("Biceps Curl (Dumbbell)");
        bicepsCurlDumbbell.setDescription("Stand up and hold one dumbbell with " +
                "each hand down the side of your body, palms facing each other." +
                " Raise both dumbbells until they reach your shoulders' height and" +
                " slowly lower them back down after a short pause." +
                " Try NOT to jerk your upper body in an effort to help you lift the weights.");
        bicepsCurlDumbbell.setCategory(armCategory);
        ExerciseType bicepsCurlDumbbellSave = exerciseTypeDao.save(bicepsCurlDumbbell);
        exerciseToArm2.setExerciseType(bicepsCurlDumbbellSave);
        exerciseToArm2.setSets(3);
        exerciseToArm2.setRepetitions(12);

        Exercise exerciseToBack3 = new Exercise();
        ExerciseType superman = new ExerciseType();
        superman.setCategory(backCategory);
        superman.setDescription("Lie face down on a mat, with your legs" +
                " straight and your arms outstretched in front of you. Raise" +
                " both your arms and legs at the same time so that they" +
                " are 10-15cm off the floor, forming a bowl shape with your body..");
        superman.setName("BodyWeight Superman");
        ExerciseType supermanSave = exerciseTypeDao.save(superman);
        exerciseToBack3.setExerciseType(supermanSave);
        exerciseToBack3.setSets(3);
        exerciseToBack3.setRepetitions(25);


        return Arrays.asList(exerciseToBack1, exerciseToBack2, exerciseToArm2,
                exerciseToArm1, exerciseToBack3);

    }

    private List<Exercise> createExerciseThird() {
        Exercise exerciseToLegs1 = new Exercise();
        ExerciseType seatedLegCurl = new ExerciseType();
        ExerciseCategory legs = new ExerciseCategory();
        legs.setName("Legs");
        seatedLegCurl.setCategory(legs);
        seatedLegCurl.setName("Seated Leg Curl (Lever)");
        seatedLegCurl.setDescription("Adjust the machine lever to fit your height" +
                " and sit on the machine with your back against the back" +
                " support pad. As you exhale, pull the machine lever as far as" +
                " possible to the back of your thighs by flexing at the knees." +
                " Keep your torso stationary at all times.");
        ExerciseType seatedLegCurlSave = exerciseTypeDao.save(seatedLegCurl);
        ExerciseCategory legsCategory = seatedLegCurlSave.getCategory();

        exerciseToLegs1.setExerciseType(seatedLegCurlSave);
        exerciseToLegs1.setSets(3);
        exerciseToLegs1.setRepetitions(12);

        Exercise exerciseToLegs2 = new Exercise();
        ExerciseCategory arm = new ExerciseCategory();
        ExerciseType legExtension = new ExerciseType();
        arm.setName("Arm");
        legExtension.setCategory(legsCategory);
        legExtension.setName("Leg Extension (Lever)");
        legExtension.setDescription("Place your hands on the hand bars." +
                "Lift the weight while exhaling until your legs are almost straight." +
                " Do not lock your knees. Keep your back against the backrest" +
                " and do not arch your back. " +
                "Exhale and lower the weight back to starting position.");
        ExerciseType legExtensionSave = exerciseTypeDao.save(legExtension);

        exerciseToLegs2.setExerciseType(legExtensionSave);
        exerciseToLegs2.setSets(3);
        exerciseToLegs2.setRepetitions(12);

        Exercise exerciseToLegs3 = new Exercise();
        ExerciseType legPress = new ExerciseType();
        legPress.setCategory(legsCategory);
        legPress.setName("Leg Press (Lever)");
        legPress.setDescription("Extend knees and hips until knees are fully" +
                " extended. Return and repeat.");
        ExerciseType legPressSave = exerciseTypeDao.save(legPress);
        exerciseToLegs3.setSets(3);
        exerciseToLegs3.setRepetitions(12);
        exerciseToLegs3.setExerciseType(legPressSave);

        Exercise exerciseToArm1 = new Exercise();
        ExerciseType militaryPress = new ExerciseType();
        militaryPress.setName("Military Press (Lever)");
        militaryPress.setDescription("Push lever upwards until arms are" +
                " extended. Lower and repeat.");
        militaryPress.setCategory(armCategory);
        ExerciseType militaryPressSave = exerciseTypeDao.save(militaryPress);
        exerciseToArm1.setExerciseType(militaryPressSave);
        exerciseToArm1.setSets(3);
        exerciseToArm1.setRepetitions(12);

        Exercise exerciseToArm2 = new Exercise();
        ExerciseType frontRaise = new ExerciseType();
        frontRaise.setCategory(armCategory);
        frontRaise.setDescription("Raise dumbbells forward and upward until " +
                "upper arms are above horizontal. Lower and repeat.");
        frontRaise.setName("Front Raise (Dumbbells)");
        ExerciseType frontRaiseSave = exerciseTypeDao.save(frontRaise);
        exerciseToArm2.setExerciseType(frontRaiseSave);
        exerciseToArm2.setSets(3);
        exerciseToArm2.setRepetitions(12);

        Exercise exerciseToAbs = new Exercise();
        exerciseToAbs.setExerciseType(bodyWeightCrunchXBodySave);
        exerciseToAbs.setSets(3);
        exerciseToAbs.setRepetitions(25);
        return Arrays.asList(exerciseToLegs1, exerciseToLegs2, exerciseToArm1,
                exerciseToLegs3, exerciseToArm2, exerciseToAbs);

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
