import animals.*;
import employees.*;
import enclosures.*;
import medical.*;
import feeding.*;
import exhibitions.*;
import java.time.LocalDateTime;

// Принцип DIP: зависимости от абстракций, а не конкретных классов
public class ZooERP {
    private EnclosureManager enclosureManager;
    private MedicalRecords medicalRecords;
    private FeedingManager feedingManager;

    public ZooERP() {
        this.enclosureManager = new EnclosureManager();
        this.medicalRecords = new MedicalRecords();
        this.feedingManager = new FeedingManager();
    }

    public void initializeSampleData() {
        // Создаем животных
        Lion simba = new Lion("Симба", 5, 190.5);
        Elephant dumbo = new Elephant("Дамбо", 10, 5000, 1.2);
        Penguin kowalski = new Penguin("Ковальски", 3, 15.2);

        // Создаем вольеры
        Enclosure savannah = new Enclosure("SAV-001", "Саванна", 500, 25, 5);
        Enclosure aquatic = new Enclosure("AQU-001", "Водный вольер", 200, 15, 10);

        // Добавляем животных в вольеры
        savannah.addAnimal(simba);
        savannah.addAnimal(dumbo);
        aquatic.addAnimal(kowalski);

        // Добавляем вольеры в менеджер
        enclosureManager.addEnclosure(savannah);
        enclosureManager.addEnclosure(aquatic);

        // Создаем сотрудников
        Veterinarian drSmith = new Veterinarian("Доктор Смит", "VET-001");
        Zookeeper john = new Zookeeper("Джон Cноу", "ZK-001");

        // Создаем расписания кормления
        FeedingSchedule lionFeeding = new FeedingSchedule(simba, "Сырое мясо", 5, 2);
        FeedingSchedule elephantFeeding = new FeedingSchedule(dumbo, "Овощи", 50, 3);

        feedingManager.addFeedingSchedule(simba, lionFeeding);
        feedingManager.addFeedingSchedule(dumbo, elephantFeeding);

        // Проводим медосмотр
        MedicalExamination exam = drSmith.performCheckup(simba, "Плановый осмотр");
        exam.completeExamination("Здоров, вес оптимальный");
        medicalRecords.addExamination(exam);

        // Кормим животных
        john.feedAnimal(simba, lionFeeding);

        // Создаем выставку
        Exhibition safariNight = new Exhibition(
                "Ночь в саванне",
                "Выставка ночных животных",
                LocalDateTime.now().plusDays(7),
                100
        );
        safariNight.addAnimal(simba);
        safariNight.assignStaff(drSmith);
        safariNight.assignStaff(john);

        // Выводим информацию
        System.out.println("=== ERP-СИСТЕМА МОСКОВСКОГО ЗООПАРКА ===");
        System.out.println("\nИнициализированы примеры данных:");
        System.out.println("- " + savannah.getAnimalCount() + " животных в вольере Саванна");
        System.out.println("- " + aquatic.getAnimalCount() + " животных в водном вольере");
        System.out.println("- " + medicalRecords.getExaminationsForAnimal(simba).size() + " медицинских осмотров записано");

        // Проверяем кормление
        feedingManager.checkFeedingStatus();

        // Показываем информацию о выставке
        System.out.println("\n" + safariNight.getExhibitionInfo());
    }

    public EnclosureManager getEnclosureManager() {
        return enclosureManager;
    }

    public MedicalRecords getMedicalRecords() {
        return medicalRecords;
    }

    public FeedingManager getFeedingManager() {
        return feedingManager;
    }

    public static void main(String[] args) {
        ZooERP zooSystem = new ZooERP();
        zooSystem.initializeSampleData();

        // Демонстрация возможностей
        System.out.println("\n=== ДЕМОНСТРАЦИЯ ВОЗМОЖНОСТЕЙ СИСТЕМЫ ===");

        // Показываем все вольеры
        zooSystem.getEnclosureManager().displayAllEnclosures();

        // Показываем все медосмотры
        zooSystem.getMedicalRecords().displayAllExaminations();
    }
}