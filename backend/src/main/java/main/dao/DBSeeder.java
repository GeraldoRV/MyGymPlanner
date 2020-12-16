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

        Gym gym = gymDao.save(createGym());
        userDao.saveAll(createUsers(gym));
        createWorkoutTables();
        createClassesDirected(gym);

    }

    private void createClassesDirected(Gym gym) {
        TypeClass gap = newTypeClass("GAP", 45, "GAP es una actividad de mantenimiento que intenta reafirmar y endurecer nuestros glúteos, abdominales y piernas.");
        TypeClass cross_fit = newTypeClass("Cross Fit", 60, "CrossFit se define como un sistema de entrenamiento de fuerza y acondicionamiento basado en ejercicios funcionales constantemente variados realizados a alta intensidad.");
        TypeClass pilates = newTypeClass("Pilates", 45, "Los ejercicios de Pilates forman parte de un método integral que ayuda a mover los músculos más profundos del cuerpo, y así lograr una buena postura. ... Pilates propone una técnica \"inteligente\" que permite fortalecer los músculos de los hombros, el pecho y la espalda.");
        TypeClass cycling = newTypeClass("Indoor Cycle", 45, "Esta actividad física es muy similar al spinning, nos permiten adelgazar y quemar calorías, mejorar la resistencia cardíaca y pulmonar y tonificar nuestras piernas.");
        TypeClass boxing = newTypeClass("Boxeo", 60, "El boxeo es un deporte de combate en el que dos personas, por lo general con guantes protectores, se lanzan golpes durante un tiempo predeterminado en un ring de boxeo.");
        TypeClass zumba = newClassDirected(gym, "09:00", "09:45", "Lunes", "monitor", null);
        newClassDirected(gym, "09:45", "10:30", "Lunes", "noOne", pilates);
        newClassDirected(gym, "10:30", "11:30", "Martes", "noOne", cross_fit);
        newClassDirected(gym, "10:30", "11:30", "Martes", "noOne", boxing);
        newClassDirected(gym, "12:15", "13:00", "Miercoles", "noOne", gap);
        newClassDirected(gym, "17:00", "17:45", "Miercoles", "noOne", zumba);
        newClassDirected(gym, "09:45", "10:00", "Jueves", "noOne", cycling);
        newClassDirected(gym, "10:00", "10:45", "Jueves", "noOne", zumba);
        newClassDirected(gym, "09:00", "09:45", "Viernes", "noOne", zumba);
        newClassDirected(gym, "09:45", "10:00", "Viernes", "noOne", gap);
        newClassDirected(gym, "10:00", "10:45", "Sábado", "noOne", boxing);
        newClassDirected(gym, "17:00", "17:45", "Sábado", "noOne", boxing);
        newClassDirected(gym, "10:00", "10:45", "Domingo", "noOne", cycling);
        newClassDirected(gym, "13:15", "14:00", "Domingo", "noOne", cycling);
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
        workoutTable.setName("Entrenamiento de 2 días- Primer día");
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
        workoutTable.setName("Entrenamiento de 2 días- Segundo Día");
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
        workoutTable.setName("Entrenamiento de intenso- Un Día");
        Optional<User> userNameAndPassword = userDao.findByUserNameAndPassword("admin", "admin");
        if (userNameAndPassword.isPresent()) {
            User byUserNameAndPassword = userNameAndPassword.get();
            workoutTable.setUser(byUserNameAndPassword);
        }
        workoutTable.setLevel('A');
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
        bodyWeightCrunchXBody.setName("BodyWeight Crunch - Cross Body");
        bodyWeightCrunchXBody.setDescription("Doble el cuerpo hacia " +
                "arriba llevando el codo y el hombro de un lado del cuerpo a la rodilla opuesta");
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
        bicepsCurlDumbbell.setDescription("Levántese y sostenga una mancuerna con cada mano por el costado de su cuerpo," +
                " las palmas una frente a la otra. Levante ambas mancuernas hasta que alcancen la altura de sus hombros " +
                "y bájelas lentamente hacia abajo después de una breve pausa. Trate de NO sacudir la parte superior del " +
                "cuerpo en un esfuerzo por ayudarte a levantar pesas ");
        bicepsCurlDumbbell.setCategory(armCategory);
        ExerciseType bicepsCurlDumbbellSave = exerciseTypeDao.save(bicepsCurlDumbbell);
        exerciseToArm2.setExerciseType(bicepsCurlDumbbellSave);
        exerciseToArm2.setSets(3);
        exerciseToArm2.setRepetitions(12);

        Exercise exerciseToBack3 = new Exercise();
        ExerciseType superman = new ExerciseType();
        superman.setCategory(backCategory);
        superman.setDescription("Acuéstese boca abajo sobre una colchoneta, con las piernas estiradas y los brazos extendidos frente a usted. Levante los brazos y las piernas al mismo tiempo para que estén a 10-15 cm del suelo, formando un cuenco con su cuerpo. ");
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
        legs.setName("Piernas");
        seatedLegCurl.setCategory(legs);
        seatedLegCurl.setName("Flexión de piernas sentado (palanca)");
        seatedLegCurl.setDescription("Ajuste la palanca de la máquina para que se adapte a su altura y siéntese en la máquina con la espalda contra la almohadilla de soporte de la espalda. Mientras exhala, tire de la palanca de la máquina lo más posible hacia la parte posterior de los muslos flexionando las rodillas. Mantenga el torso inmóvil en todo momento ");
        ExerciseType seatedLegCurlSave = exerciseTypeDao.save(seatedLegCurl);
        ExerciseCategory legsCategory = seatedLegCurlSave.getCategory();

        exerciseToLegs1.setExerciseType(seatedLegCurlSave);
        exerciseToLegs1.setSets(3);
        exerciseToLegs1.setRepetitions(12);

        Exercise exerciseToLegs2 = new Exercise();
        ExerciseCategory arm = new ExerciseCategory();
        ExerciseType legExtension = new ExerciseType();
        arm.setName("Brazos");
        legExtension.setCategory(legsCategory);
        legExtension.setName("Extensión de pierna (palanca)");
        legExtension.setDescription("Coloque las manos en las barras de las manos. Levante el peso mientras exhala hasta que sus piernas estén casi rectas. No bloquee las rodillas. Mantenga la espalda contra el respaldo y no arquee la espalda. Exhale y baje el peso a la posición inicial. ");
        ExerciseType legExtensionSave = exerciseTypeDao.save(legExtension);

        exerciseToLegs2.setExerciseType(legExtensionSave);
        exerciseToLegs2.setSets(3);
        exerciseToLegs2.setRepetitions(12);

        Exercise exerciseToLegs3 = new Exercise();
        ExerciseType legPress = new ExerciseType();
        legPress.setCategory(legsCategory);
        legPress.setName("Prensa de piernas (palanca)");
        legPress.setDescription("Extienda las rodillas y las caderas hasta que las rodillas estén completamente extendidas. Regrese y repita");
        ExerciseType legPressSave = exerciseTypeDao.save(legPress);
        exerciseToLegs3.setSets(3);
        exerciseToLegs3.setRepetitions(12);
        exerciseToLegs3.setExerciseType(legPressSave);

        Exercise exerciseToArm1 = new Exercise();
        ExerciseType militaryPress = new ExerciseType();
        militaryPress.setName("Prensa militar (palanca)");
        militaryPress.setDescription("Empuje la palanca hacia arriba hasta que los brazos estén extendidos. Baje y repita");
        militaryPress.setCategory(armCategory);
        ExerciseType militaryPressSave = exerciseTypeDao.save(militaryPress);
        exerciseToArm1.setExerciseType(militaryPressSave);
        exerciseToArm1.setSets(3);
        exerciseToArm1.setRepetitions(12);

        Exercise exerciseToArm2 = new Exercise();
        ExerciseType frontRaise = new ExerciseType();
        frontRaise.setCategory(armCategory);
        frontRaise.setDescription("Levante las mancuernas hacia adelante y hacia arriba hasta que los brazos estén por encima de la horizontal. Baje y repita");
        frontRaise.setName("Levantamiento frontal (mancuernas)");
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
        client.setName("Fernado Peréz");
        client.setUserName("ferna");
        client.setPassword("ferna");
        client.setRole("socio");
        client.setGym(gym);

        User monitor = new User();
        monitor.setName("Pedro Peña");
        monitor.setUserName("monitorA");
        monitor.setPassword("monitorA");
        monitor.setRole("monitor");
        monitor.setGym(gym);
        WorkingHours workingHours = new WorkingHours();
        workingHours.setMondayToFriday("08:00 a 15:00");
        workingHours.setSaturday("Libre");
        workingHours.setSunday("08:00 a 15:00");
        monitor.setWorkingHours(workingHours);
        return Arrays.asList(admin, monitor, client);
    }

    private Gym createGym() {
        Gym gym = new Gym();
        gym.setName("MyGymFit");
        gym.setDirection("Las Palmas de Gran Canaria");
        OpeningHours openingHours = new OpeningHours();
        openingHours.setMondaysToFridays("08:00 a 23:00");
        openingHours.setSaturdays("09:00 a 21:00");
        openingHours.setSundaysAndHolidays("08:00 a 15:00");
        gym.setOpeningHours(openingHours);
        return gym;
    }
}
