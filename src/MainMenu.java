import animals.*;
import employees.*;
import enclosures.*;
import feeding.*;
import medical.*;
import exhibitions.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainMenu {
    private Scanner scanner;
    private EnclosureManager enclosureManager;
    private MedicalRecords medicalRecords;
    private FeedingManager feedingManager;
    private Map<String, Animal> allAnimals;
    private Map<String, ZooEmployee> allEmployees;
    private List<Exhibition> exhibitions;

    public MainMenu() {
        scanner = new Scanner(System.in);
        enclosureManager = new EnclosureManager();
        medicalRecords = new MedicalRecords();
        feedingManager = new FeedingManager();
        allAnimals = new HashMap<>();
        allEmployees = new HashMap<>();
        exhibitions = new ArrayList<>();
    }

    public void start() {
        System.out.println("=== ERP-СИСТЕМА МОСКОВСКОГО ЗООПАРКА ===");
        System.out.println("Добро пожаловать в систему управления зоопарком!\n");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = getIntInput("Выберите опцию: ");

            switch (choice) {
                case 1:
                    animalManagementMenu();
                    break;
                case 2:
                    enclosureManagementMenu();
                    break;
                case 3:
                    employeeManagementMenu();
                    break;
                case 4:
                    feedingManagementMenu();
                    break;
                case 5:
                    medicalManagementMenu();
                    break;
                case 6:
                    exhibitionManagementMenu();
                    break;
                case 7:
                    showReports();
                    break;
                case 8:
                    running = false;
                    System.out.println("Выход из системы. До свидания!");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
        scanner.close();
    }

    private void printMainMenu() {
        System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
        System.out.println("1. Управление животными");
        System.out.println("2. Управление вольерами");
        System.out.println("3. Управление сотрудниками");
        System.out.println("4. Управление кормлением");
        System.out.println("5. Управление медицинскими осмотрами");
        System.out.println("6. Управление выставками");
        System.out.println("7. Отчеты и информация");
        System.out.println("8. Выход");
    }

    // ========== ЖИВОТНЫЕ ==========
    private void animalManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== УПРАВЛЕНИЕ ЖИВОТНЫМИ ===");
            System.out.println("1. Добавить новое животное");
            System.out.println("2. Показать всех животных");
            System.out.println("3. Найти животное");
            System.out.println("4. Поместить животное в вольер");
            System.out.println("5. Вернуться в главное меню");

            int choice = getIntInput("Выберите опцию: ");

            switch (choice) {
                case 1:
                    createAnimal();
                    break;
                case 2:
                    displayAllAnimals();
                    break;
                case 3:
                    findAnimal();
                    break;
                case 4:
                    placeAnimalInEnclosure();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private void createAnimal() {
        System.out.println("\n=== СОЗДАНИЕ ЖИВОТНОГО ===");
        System.out.println("Выберите вид животного:");
        System.out.println("1. Лев");
        System.out.println("2. Слон");
        System.out.println("3. Пингвин");
        System.out.println("4. Другое млекопитающее");
        System.out.println("5. Другая птица");

        int type = getIntInput("Ваш выбор: ");

        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        // Проверка на уникальность имени
        if (allAnimals.containsKey(name)) {
            System.out.println("Животное с таким именем уже существует!");
            return;
        }

        int age = getIntInput("Введите возраст: ");
        double weight = getDoubleInput("Введите вес (кг): ");

        Animal animal = null;

        switch (type) {
            case 1:
                animal = new Lion(name, age, weight);
                break;
            case 2:
                double tuskLength = getDoubleInput("Введите длину бивней (м): ");
                animal = new Elephant(name, age, weight, tuskLength);
                break;
            case 3:
                animal = new Penguin(name, age, weight);
                break;
            case 4:
                System.out.print("Введите вид животного: ");
                String species = scanner.nextLine();
                System.out.print("Есть ли мех? (1 - Да, 2 - Нет): ");
                boolean hasFur = getIntInput("") == 1;
                System.out.print("Введите тип питания: ");
                String diet = scanner.nextLine();
                animal = createCustomMammal(name, species, age, weight, hasFur, diet);
                break;
            case 5:
                System.out.print("Введите вид птицы: ");
                String birdSpecies = scanner.nextLine();
                double wingspan = getDoubleInput("Введите размах крыльев (м): ");
                System.out.print("Умеет летать? (1 - Да, 2 - Нет): ");
                boolean canFly = getIntInput("") == 1;
                System.out.print("Введите тип питания: ");
                String birdDiet = scanner.nextLine();
                animal = createCustomBird(name, birdSpecies, age, weight, wingspan, canFly, birdDiet);
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        allAnimals.put(name, animal);
        System.out.println("Животное " + name + " успешно создано!");

        // Сразу предлагаем поместить в вольер
        System.out.print("Хотите поместить животное в вольер сейчас? (1 - Да, 2 - Нет): ");
        if (getIntInput("") == 1) {
            placeAnimalInEnclosure(name);
        }
    }

    private Animal createCustomMammal(String name, String species, int age, double weight, boolean hasFur, String diet) {
        return new AbstractAnimal(name, species, age, weight) {
            @Override
            public String getDietType() {
                return diet;
            }
        };
    }

    private Animal createCustomBird(String name, String species, int age, double weight,
                                    double wingspan, boolean canFly, String diet) {
        return new Bird(name, species, age, weight, wingspan, canFly) {
            @Override
            public String getFlightPattern() {
                return canFly ? "Летает" : "Не летает";
            }

            @Override
            public String getDietType() {
                return diet;
            }
        };
    }

    private void displayAllAnimals() {
        System.out.println("\n=== ВСЕ ЖИВОТНЫЕ ===");
        if (allAnimals.isEmpty()) {
            System.out.println("Животных пока нет.");
            return;
        }

        for (Animal animal : allAnimals.values()) {
            System.out.println("Имя: " + animal.getName() +
                    ", Вид: " + animal.getSpecies() +
                    ", Возраст: " + animal.getAge() +
                    ", Вес: " + animal.getWeight() + " кг" +
                    ", Диета: " + animal.getDietType() +
                    ", Здоровье: " + animal.getHealthStatus());
        }
    }

    private void findAnimal() {
        System.out.print("Введите имя животного для поиска: ");
        String name = scanner.nextLine();

        Animal animal = allAnimals.get(name);
        if (animal != null) {
            System.out.println("\n=== НАЙДЕНО ЖИВОТНОЕ ===");
            System.out.println("Имя: " + animal.getName());
            System.out.println("Вид: " + animal.getSpecies());
            System.out.println("Возраст: " + animal.getAge() + " лет");
            System.out.println("Вес: " + animal.getWeight() + " кг");
            System.out.println("Тип питания: " + animal.getDietType());
            System.out.println("Состояние здоровья: " + animal.getHealthStatus());

            // Проверяем, есть ли расписание кормления
            if (feedingManager.getSchedule(animal) != null) {
                System.out.println("Расписание кормления: Есть");
            } else {
                System.out.println("Расписание кормления: Не установлено");
            }
        } else {
            System.out.println("Животное с именем '" + name + "' не найдено.");
        }
    }

    private void placeAnimalInEnclosure() {
        System.out.print("Введите имя животного: ");
        String animalName = scanner.nextLine();
        placeAnimalInEnclosure(animalName);
    }

    private void placeAnimalInEnclosure(String animalName) {
        Animal animal = allAnimals.get(animalName);
        if (animal == null) {
            System.out.println("Животное не найдено!");
            return;
        }

        // Показать все вольеры
        System.out.println("\n=== ДОСТУПНЫЕ ВОЛЬЕРЫ ===");
        enclosureManager.displayAllEnclosures();

        System.out.print("Введите ID вольера: ");
        String enclosureId = scanner.nextLine();

        Enclosure enclosure = enclosureManager.getEnclosure(enclosureId);
        if (enclosure == null) {
            System.out.println("Вольер не найден!");
            return;
        }

        if (enclosure.addAnimal(animal)) {
            System.out.println("Животное " + animalName + " успешно помещено в вольер " + enclosureId);
        }
    }

    // ========== ВОЛЬЕРЫ ==========
    private void enclosureManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== УПРАВЛЕНИЕ ВОЛЬЕРАМИ ===");
            System.out.println("1. Создать новый вольер");
            System.out.println("2. Показать все вольеры");
            System.out.println("3. Найти вольер");
            System.out.println("4. Изменить температуру вольера");
            System.out.println("5. Показать животных в вольере");
            System.out.println("6. Вернуться в главное меню");

            int choice = getIntInput("Выберите опцию: ");

            switch (choice) {
                case 1:
                    createEnclosure();
                    break;
                case 2:
                    enclosureManager.displayAllEnclosures();
                    break;
                case 3:
                    findEnclosure();
                    break;
                case 4:
                    changeEnclosureTemperature();
                    break;
                case 5:
                    showAnimalsInEnclosure();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private void createEnclosure() {
        System.out.println("\n=== СОЗДАНИЕ ВОЛЬЕРА ===");
        System.out.print("Введите ID вольера: ");
        String id = scanner.nextLine();

        System.out.print("Введите тип вольера (например, Саванна, Тропики, Арктика и т.д.): ");
        String type = scanner.nextLine();

        double area = getDoubleInput("Введите площадь вольера (м²): ");
        double temperature = getDoubleInput("Введите температуру вольера (°C): ");
        int capacity = getIntInput("Введите максимальную вместимость: ");

        Enclosure enclosure = new Enclosure(id, type, area, temperature, capacity);
        enclosureManager.addEnclosure(enclosure);
        System.out.println("Вольер " + id + " успешно создан!");
    }

    private void findEnclosure() {
        System.out.print("Введите ID вольера: ");
        String id = scanner.nextLine();

        Enclosure enclosure = enclosureManager.getEnclosure(id);
        if (enclosure != null) {
            enclosure.displayEnclosureInfo();
        } else {
            System.out.println("Вольер не найден!");
        }
    }

    private void changeEnclosureTemperature() {
        System.out.print("Введите ID вольера: ");
        String id = scanner.nextLine();

        Enclosure enclosure = enclosureManager.getEnclosure(id);
        if (enclosure != null) {
            double newTemp = getDoubleInput("Введите новую температуру (°C): ");
            enclosure.setTemperature(newTemp);
            System.out.println("Температура вольера " + id + " изменена на " + newTemp + "°C");
        } else {
            System.out.println("Вольер не найден!");
        }
    }

    private void showAnimalsInEnclosure() {
        System.out.print("Введите ID вольера: ");
        String id = scanner.nextLine();

        Enclosure enclosure = enclosureManager.getEnclosure(id);
        if (enclosure != null) {
            List<Animal> animals = enclosure.getAnimals();
            System.out.println("\n=== ЖИВОТНЫЕ В ВОЛЬЕРЕ " + id + " ===");
            if (animals.isEmpty()) {
                System.out.println("Вольер пуст.");
            } else {
                for (Animal animal : animals) {
                    System.out.println("- " + animal.getName() + " (" + animal.getSpecies() + ")");
                }
            }
        } else {
            System.out.println("Вольер не найден!");
        }
    }

    // ========== СОТРУДНИКИ ==========
    private void employeeManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== УПРАВЛЕНИЕ СОТРУДНИКАМИ ===");
            System.out.println("1. Нанять нового сотрудника");
            System.out.println("2. Показать всех сотрудников");
            System.out.println("3. Найти сотрудника");
            System.out.println("4. Выполнить обязанности сотрудника");
            System.out.println("5. Вернуться в главное меню");

            int choice = getIntInput("Выберите опцию: ");

            switch (choice) {
                case 1:
                    hireEmployee();
                    break;
                case 2:
                    displayAllEmployees();
                    break;
                case 3:
                    findEmployee();
                    break;
                case 4:
                    performEmployeeDuties();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private void hireEmployee() {
        System.out.println("\n=== НАНЯТИЕ СОТРУДНИКА ===");
        System.out.println("Выберите должность:");
        System.out.println("1. Ветеринар");
        System.out.println("2. Смотритель");

        int position = getIntInput("Ваш выбор: ");

        System.out.print("Введите имя сотрудника: ");
        String name = scanner.nextLine();

        System.out.print("Введите ID сотрудника: ");
        String id = scanner.nextLine();

        ZooEmployee employee = null;

        switch (position) {
            case 1:
                employee = new Veterinarian(name, id);
                break;
            case 2:
                employee = new Zookeeper(name, id);
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        allEmployees.put(id, employee);
        System.out.println("Сотрудник " + name + " успешно нанят!");
    }

    private void displayAllEmployees() {
        System.out.println("\n=== ВСЕ СОТРУДНИКИ ===");
        if (allEmployees.isEmpty()) {
            System.out.println("Сотрудников пока нет.");
            return;
        }

        for (ZooEmployee employee : allEmployees.values()) {
            System.out.println("ID: " + employee.getEmployeeId() +
                    ", Имя: " + employee.getName() +
                    ", Должность: " + employee.getPosition());
        }
    }

    private void findEmployee() {
        System.out.print("Введите ID сотрудника: ");
        String id = scanner.nextLine();

        ZooEmployee employee = allEmployees.get(id);
        if (employee != null) {
            System.out.println("\n=== НАЙДЕН СОТРУДНИК ===");
            System.out.println("ID: " + employee.getEmployeeId());
            System.out.println("Имя: " + employee.getName());
            System.out.println("Должность: " + employee.getPosition());
        } else {
            System.out.println("Сотрудник не найден!");
        }
    }

    private void performEmployeeDuties() {
        System.out.print("Введите ID сотрудника: ");
        String id = scanner.nextLine();

        ZooEmployee employee = allEmployees.get(id);
        if (employee != null) {
            employee.performDuties();
        } else {
            System.out.println("Сотрудник не найден!");
        }
    }

    // ========== КОРМЛЕНИЕ ==========
    private void feedingManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== УПРАВЛЕНИЕ КОРМЛЕНИЕМ ===");
            System.out.println("1. Создать расписание кормления");
            System.out.println("2. Покормить животное");
            System.out.println("3. Проверить статус кормления");
            System.out.println("4. Показать все расписания");
            System.out.println("5. Вернуться в главное меню");

            int choice = getIntInput("Выберите опцию: ");

            switch (choice) {
                case 1:
                    createFeedingSchedule();
                    break;
                case 2:
                    feedAnimal();
                    break;
                case 3:
                    feedingManager.checkFeedingStatus();
                    break;
                case 4:
                    showAllFeedingSchedules();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private void createFeedingSchedule() {
        System.out.println("\n=== СОЗДАНИЕ РАСПИСАНИЯ КОРМЛЕНИЯ ===");

        Animal animal = selectAnimal();
        if (animal == null) return;

        System.out.print("Введите тип корма: ");
        String foodType = scanner.nextLine();

        double portion = getDoubleInput("Введите размер порции (кг): ");
        int frequency = getIntInput("Введите количество кормлений в день: ");

        FeedingSchedule schedule = new FeedingSchedule(animal, foodType, portion, frequency);
        feedingManager.addFeedingSchedule(animal, schedule);
        System.out.println("Расписание кормления для " + animal.getName() + " создано!");
    }

    private void feedAnimal() {
        System.out.println("\n=== КОРМЛЕНИЕ ЖИВОТНОГО ===");

        Animal animal = selectAnimal();
        if (animal == null) return;

        FeedingSchedule schedule = feedingManager.getSchedule(animal);
        if (schedule == null) {
            System.out.println("Для этого животного нет расписания кормления!");
            return;
        }

        ZooEmployee keeper = selectEmployee("Смотритель");
        if (keeper == null || !(keeper instanceof Zookeeper)) {
            System.out.println("Не найден смотритель!");
            return;
        }

        Zookeeper zookeeper = (Zookeeper) keeper;
        zookeeper.feedAnimal(animal, schedule);
    }

    private void showAllFeedingSchedules() {
        System.out.println("\n=== ВСЕ РАСПИСАНИЯ КОРМЛЕНИЯ ===");
        // В реальной реализации здесь нужно получить все расписания из feedingManager
        System.out.println("Эта функция будет реализована в следующей версии.");
    }

    // ========== МЕДИЦИНСКИЕ ОСМОТРЫ ==========
    private void medicalManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== МЕДИЦИНСКИЕ ОСМОТРЫ ===");
            System.out.println("1. Провести новый осмотр");
            System.out.println("2. Показать все осмотры");
            System.out.println("3. Найти осмотры животного");
            System.out.println("4. Назначить лечение");
            System.out.println("5. Вернуться в главное меню");

            int choice = getIntInput("Выберите опцию: ");

            switch (choice) {
                case 1:
                    performMedicalExamination();
                    break;
                case 2:
                    medicalRecords.displayAllExaminations();
                    break;
                case 3:
                    findAnimalExaminations();
                    break;
                case 4:
                    prescribeTreatment();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private void performMedicalExamination() {
        System.out.println("\n=== МЕДИЦИНСКИЙ ОСМОТР ===");

        Animal animal = selectAnimal();
        if (animal == null) return;

        ZooEmployee vet = selectEmployee("Ветеринар");
        if (vet == null || !(vet instanceof Veterinarian)) {
            System.out.println("Не найден ветеринар!");
            return;
        }

        System.out.print("Введите заметки к осмотру: ");
        String notes = scanner.nextLine();

        Veterinarian veterinarian = (Veterinarian) vet;
        MedicalExamination exam = veterinarian.performCheckup(animal, notes);

        System.out.print("Введите результаты осмотра: ");
        String results = scanner.nextLine();
        exam.completeExamination(results);

        medicalRecords.addExamination(exam);
        System.out.println("Медицинский осмотр успешно проведен!");
    }

    private void findAnimalExaminations() {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        Animal animal = allAnimals.get(name);
        if (animal == null) {
            System.out.println("Животное не найдено!");
            return;
        }

        List<MedicalExamination> exams = medicalRecords.getExaminationsForAnimal(animal);
        System.out.println("\n=== МЕДОСМОТРЫ ЖИВОТНОГО " + name + " ===");
        if (exams.isEmpty()) {
            System.out.println("Медосмотров не найдено.");
        } else {
            for (MedicalExamination exam : exams) {
                System.out.println(exam.getExaminationReport());
                System.out.println("---");
            }
        }
    }

    private void prescribeTreatment() {
        System.out.println("\n=== НАЗНАЧЕНИЕ ЛЕЧЕНИЯ ===");

        Animal animal = selectAnimal();
        if (animal == null) return;

        ZooEmployee vet = selectEmployee("Ветеринар");
        if (vet == null || !(vet instanceof Veterinarian)) {
            System.out.println("Не найден ветеринар!");
            return;
        }

        System.out.print("Введите назначенное лечение: ");
        String treatment = scanner.nextLine();

        Veterinarian veterinarian = (Veterinarian) vet;
        veterinarian.prescribeTreatment(animal, treatment);
    }

    // ========== ВЫСТАВКИ ==========
    private void exhibitionManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== УПРАВЛЕНИЕ ВЫСТАВКАМИ ===");
            System.out.println("1. Создать новую выставку");
            System.out.println("2. Показать все выставки");
            System.out.println("3. Добавить животное на выставку");
            System.out.println("4. Добавить сотрудника на выставку");
            System.out.println("5. Вернуться в главное меню");

            int choice = getIntInput("Выберите опцию: ");

            switch (choice) {
                case 1:
                    createExhibition();
                    break;
                case 2:
                    showAllExhibitions();
                    break;
                case 3:
                    addAnimalToExhibition();
                    break;
                case 4:
                    addEmployeeToExhibition();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private void createExhibition() {
        System.out.println("\n=== СОЗДАНИЕ ВЫСТАВКИ ===");

        System.out.print("Введите название выставки: ");
        String name = scanner.nextLine();

        System.out.print("Введите тему выставки: ");
        String theme = scanner.nextLine();

        int days = getIntInput("Через сколько дней состоится выставка? ");
        int maxVisitors = getIntInput("Введите максимальное количество посетителей: ");

        LocalDateTime dateTime = LocalDateTime.now().plusDays(days);
        Exhibition exhibition = new Exhibition(name, theme, dateTime, maxVisitors);
        exhibitions.add(exhibition);

        System.out.println("Выставка '" + name + "' успешно создана!");
    }

    private void showAllExhibitions() {
        System.out.println("\n=== ВСЕ ВЫСТАВКИ ===");
        if (exhibitions.isEmpty()) {
            System.out.println("Выставок пока нет.");
        } else {
            for (Exhibition exhibition : exhibitions) {
                System.out.println(exhibition.getExhibitionInfo());
                System.out.println("---");
            }
        }
    }

    private void addAnimalToExhibition() {
        if (exhibitions.isEmpty()) {
            System.out.println("Сначала создайте выставку!");
            return;
        }

        System.out.println("\n=== ДОБАВЛЕНИЕ ЖИВОТНОГО НА ВЫСТАВКУ ===");

        // Выбор выставки
        System.out.println("Выберите выставку:");
        for (int i = 0; i < exhibitions.size(); i++) {
            System.out.println((i + 1) + ". " + exhibitions.get(i).getName());
        }

        int exIndex = getIntInput("Ваш выбор: ") - 1;
        if (exIndex < 0 || exIndex >= exhibitions.size()) {
            System.out.println("Неверный выбор!");
            return;
        }

        // Выбор животного
        Animal animal = selectAnimal();
        if (animal == null) return;

        Exhibition exhibition = exhibitions.get(exIndex);
        if (exhibition.addAnimal(animal)) {
            System.out.println(animal.getName() + " добавлен на выставку '" + exhibition.getName() + "'");
        } else {
            System.out.println("Не удалось добавить животное. Возможно, достигнут лимит.");
        }
    }

    private void addEmployeeToExhibition() {
        if (exhibitions.isEmpty()) {
            System.out.println("Сначала создайте выставку!");
            return;
        }

        System.out.println("\n=== ДОБАВЛЕНИЕ СОТРУДНИКА НА ВЫСТАВКУ ===");

        // Выбор выставки
        System.out.println("Выберите выставку:");
        for (int i = 0; i < exhibitions.size(); i++) {
            System.out.println((i + 1) + ". " + exhibitions.get(i).getName());
        }

        int exIndex = getIntInput("Ваш выбор: ") - 1;
        if (exIndex < 0 || exIndex >= exhibitions.size()) {
            System.out.println("Неверный выбор!");
            return;
        }

        // Выбор сотрудника
        ZooEmployee employee = selectEmployee("любой");
        if (employee == null) return;

        Exhibition exhibition = exhibitions.get(exIndex);
        exhibition.assignStaff(employee);
        System.out.println(employee.getName() + " назначен на выставку '" + exhibition.getName() + "'");
    }

    // ========== ОТЧЕТЫ ==========
    private void showReports() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== ОТЧЕТЫ И ИНФОРМАЦИЯ ===");
            System.out.println("1. Общая статистика зоопарка");
            System.out.println("2. Отчет по здоровью животных");
            System.out.println("3. Отчет по питанию");
            System.out.println("4. Отчет по вольерам");
            System.out.println("5. Вернуться в главное меню");

            int choice = getIntInput("Выберите опцию: ");

            switch (choice) {
                case 1:
                    showStatistics();
                    break;
                case 2:
                    showHealthReport();
                    break;
                case 3:
                    showFeedingReport();
                    break;
                case 4:
                    showEnclosuresReport();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private void showStatistics() {
        System.out.println("\n=== СТАТИСТИКА ЗООПАРКА ===");
        System.out.println("Всего животных: " + allAnimals.size());
        System.out.println("Всего сотрудников: " + allEmployees.size());
        System.out.println("Всего вольеров: " + enclosureManager.getAllEnclosures().size());
        System.out.println("Всего выставок: " + exhibitions.size());
        System.out.println("Всего медосмотров: " + medicalRecords.getExaminationsForAnimal(null).size());

        // Подсчет животных по типам
        int lions = 0, elephants = 0, penguins = 0, others = 0;
        for (Animal animal : allAnimals.values()) {
            String species = animal.getSpecies();
            if (species.equals("Лев")) lions++;
            else if (species.equals("Слон")) elephants++;
            else if (species.equals("Пингвин")) penguins++;
            else others++;
        }

        System.out.println("\nЖивотные по видам:");
        System.out.println("Львы: " + lions);
        System.out.println("Слоны: " + elephants);
        System.out.println("Пингвины: " + penguins);
        System.out.println("Другие: " + others);
    }

    private void showHealthReport() {
        System.out.println("\n=== ОТЧЕТ ПО ЗДОРОВЬЮ ЖИВОТНЫХ ===");

        int healthy = 0, sick = 0, underTreatment = 0;
        for (Animal animal : allAnimals.values()) {
            String status = animal.getHealthStatus();
            if (status.contains("Здоров")) healthy++;
            else if (status.contains("Болен")) sick++;
            else if (status.contains("лечени")) underTreatment++;
        }

        System.out.println("Здоровые: " + healthy);
        System.out.println("Больные: " + sick);
        System.out.println("На лечении: " + underTreatment);
        System.out.println("Всего: " + (healthy + sick + underTreatment));
    }

    private void showFeedingReport() {
        System.out.println("\n=== ОТЧЕТ ПО ПИТАНИЮ ===");

        int carnivores = 0, herbivores = 0, omnivores = 0;
        for (Animal animal : allAnimals.values()) {
            String diet = animal.getDietType();
            if (diet.contains("Хищник") || diet.contains("Carnivore")) carnivores++;
            else if (diet.contains("Травояд") || diet.contains("Herbivore")) herbivores++;
            else omnivores++;
        }

        System.out.println("Хищники: " + carnivores);
        System.out.println("Травоядные: " + herbivores);
        System.out.println("Всеядные: " + omnivores);
    }

    private void showEnclosuresReport() {
        System.out.println("\n=== ОТЧЕТ ПО ВОЛЬЕРАМ ===");
        enclosureManager.displayAllEnclosures();
    }

    // ========== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ==========
    private Animal selectAnimal() {
        if (allAnimals.isEmpty()) {
            System.out.println("Животных пока нет!");
            return null;
        }

        System.out.println("\nВыберите животное:");
        List<Animal> animals = new ArrayList<>(allAnimals.values());
        for (int i = 0; i < animals.size(); i++) {
            System.out.println((i + 1) + ". " + animals.get(i).getName() +
                    " (" + animals.get(i).getSpecies() + ")");
        }

        int index = getIntInput("Ваш выбор: ") - 1;
        if (index < 0 || index >= animals.size()) {
            System.out.println("Неверный выбор!");
            return null;
        }

        return animals.get(index);
    }

    private ZooEmployee selectEmployee(String type) {
        if (allEmployees.isEmpty()) {
            System.out.println("Сотрудников пока нет!");
            return null;
        }

        System.out.println("\nВыберите сотрудника:");
        List<ZooEmployee> employees = new ArrayList<>();
        for (ZooEmployee emp : allEmployees.values()) {
            if (type.equals("любой") || emp.getPosition().equals(type)) {
                employees.add(emp);
            }
        }

        if (employees.isEmpty()) {
            System.out.println("Подходящих сотрудников не найдено!");
            return null;
        }

        for (int i = 0; i < employees.size(); i++) {
            System.out.println((i + 1) + ". " + employees.get(i).getName() +
                    " (" + employees.get(i).getPosition() + ")");
        }

        int index = getIntInput("Ваш выбор: ") - 1;
        if (index < 0 || index >= employees.size()) {
            System.out.println("Неверный выбор!");
            return null;
        }

        return employees.get(index);
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Пожалуйста, введите целое число!");
            scanner.next();
            System.out.print(prompt);
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        return input;
    }

    private double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Пожалуйста, введите число!");
            scanner.next();
            System.out.print(prompt);
        }
        double input = scanner.nextDouble();
        scanner.nextLine(); // очистка буфера
        return input;
    }

    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        menu.start();
    }
}