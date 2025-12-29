import animals.Animal;
import enclosures.Enclosure;
import medical.MedicalExamination;
import java.util.List;

// Принцип OCP: можно добавлять новые типы отчетов
public class ReportGenerator {

    public void generateHealthReport(List<MedicalExamination> examinations) {
        System.out.println("\n=== ОТЧЕТ О СОСТОЯНИИ ЗДОРОВЬЯ ===");
        for (MedicalExamination exam : examinations) {
            Animal animal = exam.getAnimal();
            System.out.println(animal.getName() + " (" + animal.getSpecies() + "): " +
                    animal.getHealthStatus());
        }
    }

    public void generateEnclosureReport(Enclosure enclosure) {
        System.out.println("\n=== ОТЧЕТ ПО ВОЛЬЕРУ: " + enclosure.getId() + " ===");
        System.out.println("Тип: " + enclosure.getType());
        System.out.println("Заполненность: " + enclosure.getAnimalCount() + "/" +
                enclosure.getMaxCapacity());
        System.out.println("Животные:");
        for (Animal animal : enclosure.getAnimals()) {
            System.out.println("  - " + animal.getName() +
                    " (Рацион: " + animal.getDietType() +
                    ", Состояние здоровья: " + animal.getHealthStatus() + ")");
        }
    }

    public void generateFeedingReport(List<Animal> animals) {
        System.out.println("\n=== ОТЧЕТ ПО ПИТАНИЮ ===");
        for (Animal animal : animals) {
            System.out.println(animal.getName() + ": " + animal.getDietType() + " рацион");
        }
    }
}