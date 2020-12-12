package main.dao;

import main.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        TypeClass gap = newTypeClass("GAP", 45, "GAP es una actividad de mantenimiento que intenta reafirmar y endurecer nuestros glúteos, abdominales y piernas.");
        TypeClass cross_fit = newTypeClass("Cross Fit", 60, "CrossFit se define como un sistema de entrenamiento de fuerza y acondicionamiento basado en ejercicios funcionales constantemente variados realizados a alta intensidad.");
        TypeClass pilates = newTypeClass("Pilates", 45, "Los ejercicios de Pilates forman parte de un método integral que ayuda a mover los músculos más profundos del cuerpo, y así lograr una buena postura. ... Pilates propone una técnica \"inteligente\" que permite fortalecer los músculos de los hombros, el pecho y la espalda.");
        TypeClass cycling = newTypeClass("Indoor Cycle", 45, "Esta actividad física es muy similar al spinning, nos permiten adelgazar y quemar calorías, mejorar la resistencia cardíaca y pulmonar y tonificar nuestras piernas.");
        TypeClass boxing = newTypeClass("Boxeo", 60, "El boxeo es un deporte de combate en el que dos personas, por lo general con guantes protectores, se lanzan golpes durante un tiempo predeterminado en un ring de boxeo.");
        TypeClass zumba = newClassDirected(gym, "09:00", "09:45", "Lunes", "monitor", null);
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
            zumba.setDescription("El baile de zumba se describe mejor como divertido alegre clase de fitness de inspiración latina que combina todos los ritmos de la música latina. Zumba aplica movimientos de baile de salsa, cumbia, merengue, rumba, mambo, danza del vientre, Flamenco, Samba, Tango, Reggaeton y por supuesto Hip Hop.");
            zumba.setDuration(45);
            classDirected.setTypeClass(zumba);
        } else {
            classDirected.setTypeClass(typeClass);
        }
        Optional<User> byUserNameAndPassword = userDao.findByUserNameAndPassword(monitor, monitor);
        if (byUserNameAndPassword.isPresent()) {
            User user = byUserNameAndPassword.get();
            classDirected.setAssignedMonitor(user);
        }
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
        workoutTable.setName("Entrenamiento de 3 días- Primer día");
        Optional<User> userNameAndPassword = userDao.findByUserNameAndPassword("admin", "admin");
        if (userNameAndPassword.isPresent()) {
            User byUserNameAndPassword = userNameAndPassword.get();
            workoutTable.setUser(byUserNameAndPassword);

        }
        workoutTable.setLevel('C');
        workoutTableDao.save(workoutTable);
    }

    private void createWorkoutTableSecond() {
        List<Exercise> exercises = createExerciseSecond();
        WorkoutTable workoutTable = new WorkoutTable();
        workoutTable.setExerciseList(exercises);
        workoutTable.setName("Entrenamiento de 3 días- Segundo Día");
        Optional<User> userNameAndPassword = userDao.findByUserNameAndPassword("admin", "admin");
        if (userNameAndPassword.isPresent()) {

            User byUserNameAndPassword = userNameAndPassword.get();
            workoutTable.setUser(byUserNameAndPassword);
        }
        workoutTable.setLevel('C');
        workoutTableDao.save(workoutTable);
    }

    private void createWorkoutTableThird() {
        List<Exercise> exercises = createExerciseThird();
        WorkoutTable workoutTable = new WorkoutTable();
        workoutTable.setExerciseList(exercises);
        workoutTable.setName("Entrenamiento de 3 días- Tercer Día");
        Optional<User> userNameAndPassword = userDao.findByUserNameAndPassword("admin", "admin");
        if (userNameAndPassword.isPresent()) {
            User byUserNameAndPassword = userNameAndPassword.get();
            workoutTable.setUser(byUserNameAndPassword);
        }
        workoutTable.setLevel('C');
        workoutTableDao.save(workoutTable);
    }

    private List<Exercise> createExerciseFirst() {
        Exercise exerciseToChest1 = new Exercise();
        ExerciseType benchPress = new ExerciseType();
        ExerciseCategory chest = new ExerciseCategory();
        chest.setName("Pecho");
        benchPress.setCategory(chest);
        benchPress.setName("Press de banca");
        benchPress.setDescription("Empuje las mancuernas hacia arriba hasta que los brazos estén completamente extendidos y las mancuernas se encuentren por encima del pecho. Regrese las mancuernas hasta que sienta un ligero estiramiento en el pecho o el hombro. Repetir");
        ExerciseType benchPressSave = exerciseTypeDao.save(benchPress);
        ExerciseCategory chestCategory = benchPressSave.getCategory();

        exerciseToChest1.setExerciseType(benchPressSave);
        exerciseToChest1.setSets(3);
        exerciseToChest1.setRepetitions(15);

        Exercise exerciseToArm1 = new Exercise();
        ExerciseCategory arm = new ExerciseCategory();
        ExerciseType chestPressCloseGrip = new ExerciseType();
        arm.setName("Brazos");
        chestPressCloseGrip.setCategory(arm);
        chestPressCloseGrip.setName("Prensa de pecho: agarre cerrado");
        chestPressCloseGrip.setDescription("Acuéstese en el banco con un agarre estrecho. Levante la barra con la ayuda de la rejilla, los brazos bloqueados y sujete la barra directamente sobre usted. Inhale y lleve lentamente la barra hacia su pecho manteniendo los codos cerca de su cuerpo durante todo el ejercicio.");
        ExerciseType chestPressCloseGripSave = exerciseTypeDao.save(chestPressCloseGrip);
        armCategory = chestPressCloseGripSave.getCategory();

        exerciseToArm1.setExerciseType(chestPressCloseGripSave);
        exerciseToArm1.setSets(3);
        exerciseToArm1.setRepetitions(15);

        Exercise exerciseToChest2 = new Exercise();
        ExerciseType pushUp = new ExerciseType();
        pushUp.setCategory(chestCategory);
        pushUp.setName("Flexiones");
        pushUp.setDescription("Póngase a cuatro patas, colocando las manos un poco más anchas que los hombros. Estire los brazos y las piernas. Baje su cuerpo hasta que su pecho casi toque el piso. Haz una pausa y luego vuelve a subir. Repetir.");
        ExerciseType pushUpSave = exerciseTypeDao.save(pushUp);
        exerciseToChest2.setSets(3);
        exerciseToChest2.setRepetitions(12);
        exerciseToChest2.setExerciseType(pushUpSave);

        Exercise exerciseToArm2 = new Exercise();
        ExerciseType tricepsExtensionOverhead = new ExerciseType();
        tricepsExtensionOverhead.setName("Extensión de tríceps: aérea (cable)");
        tricepsExtensionOverhead.setDescription("Extienda el codo hasta que los brazos estén rectos. Vuelve y repite");
        tricepsExtensionOverhead.setCategory(armCategory);
        ExerciseType tricepsExtensionOverheadSave = exerciseTypeDao.save(tricepsExtensionOverhead);
        exerciseToArm2.setExerciseType(tricepsExtensionOverheadSave);
        exerciseToArm2.setSets(3);
        exerciseToArm2.setRepetitions(15);

        Exercise exerciseToArm3 = new Exercise();
        ExerciseType benchDip = new ExerciseType();
        benchDip.setCategory(armCategory);
        benchDip.setDescription("Usando los brazos, baje el cuerpo hasta que sienta un ligero estiramiento o las nalgas toquen el suelo. Vuelve y repite.");
        benchDip.setName("Triceps en banco");
        ExerciseType benchDipSave = exerciseTypeDao.save(benchDip);
        exerciseToArm3.setExerciseType(benchDipSave);
        exerciseToArm3.setSets(3);
        exerciseToArm3.setRepetitions(15);

        Exercise exerciseToAbs = new Exercise();
        ExerciseCategory abs = new ExerciseCategory();
        abs.setName("Abdominales");
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
        back.setName("Espalda");
        seatedRow.setCategory(back);
        seatedRow.setName("Remo sentado (cable)");
        seatedRow.setDescription("Sentado con la espalda recta, las rodillas ligeramente flexionadas y los brazos completamente extendidos. Sujete el asa con las palmas una frente a la otra. Extienda su cuerpo hacia adelante, doblando las caderas. Mantenga la espalda plana. Lleve los brazos hacia usted manteniendo los codos juntos a tus lados ");
        ExerciseType seatedRowSave = exerciseTypeDao.save(seatedRow);
        ExerciseCategory backCategory = seatedRowSave.getCategory();

        exerciseToBack1.setExerciseType(seatedRowSave);
        exerciseToBack1.setSets(3);
        exerciseToBack1.setRepetitions(12);

        Exercise exerciseToBack2 = new Exercise();
        ExerciseType proLatBarPullDown = new ExerciseType();
        proLatBarPullDown.setCategory(backCategory);
        proLatBarPullDown.setName("Pro-Lat-Bar Pull down(cable)");
        proLatBarPullDown.setDescription("Sujete la barra lateral profesional con un agarre ancho paralelo. Siéntese con los muslos debajo de los soportes. Baje la barra de cables hacia la parte superior del pecho. Regrese hasta que los brazos y hombros estén completamente extendidos");
        ExerciseType proLatBarPullDownSave = exerciseTypeDao.save(proLatBarPullDown);

        exerciseToBack2.setExerciseType(proLatBarPullDownSave);
        exerciseToBack2.setSets(3);
        exerciseToBack2.setRepetitions(12);

        Exercise exerciseToArm1 = new Exercise();
        ExerciseType bicepsCurl = new ExerciseType();
        bicepsCurl.setCategory(armCategory);
        bicepsCurl.setName("Curl de bíceps (cable)");
        bicepsCurl.setDescription("Agarre la barra a la altura de los hombros y mantenga los codos cerca del torso. La palma de sus manos debe estar hacia arriba (agarre supinado). Esta será su posición inicial. Mientras sostiene la parte superior de los brazos estacionaria, doble las pesas mientras contrae bíceps mientras exhala ");
        ExerciseType bicepsCurlSave = exerciseTypeDao.save(bicepsCurl);
        exerciseToArm1.setSets(3);
        exerciseToArm1.setRepetitions(15);
        exerciseToArm1.setExerciseType(bicepsCurlSave);

        Exercise exerciseToArm2 = new Exercise();
        ExerciseType bicepsCurlDumbbell = new ExerciseType();
        bicepsCurlDumbbell.setName("Curl de bíceps (mancuernas)");
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
        workingHours.setMondayToFriday("08:00 a 15:00");
        workingHours.setSaturday("Free");
        workingHours.setSunday("08:00 a 15:00");
        monitor.setWorkingHours(workingHours);
        return Arrays.asList(admin, monitor, client);
    }

    private Gym createGym() {
        Gym gym = new Gym();
        gym.setName("FalseFit");
        gym.setDirection("Fake Street, nº 14");
        OpeningHours openingHours = new OpeningHours();
        openingHours.setMondaysToFridays("08:00 a 23:00");
        openingHours.setSaturdays("09:00 a 21:00");
        openingHours.setSundaysAndHolidays("08:00 a 15:00");
        gym.setOpeningHours(openingHours);
        return gym;
    }
}
