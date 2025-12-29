package medical;

import animals.Animal;
import employees.Veterinarian;
import java.time.LocalDate;

public class MedicalExamination {
    private Animal animal;
    private Veterinarian veterinarian;
    private LocalDate date;
    private String notes;
    private String results;

    public MedicalExamination(Animal animal, Veterinarian veterinarian, String notes) {
        this.animal = animal;
        this.veterinarian = veterinarian;
        this.date = LocalDate.now();
        this.notes = notes;
        this.results = "В процессе";
    }

    public void completeExamination(String results) {
        this.results = results;
        animal.setHealthStatus(results);
        System.out.println("Медицинский осмотр завершен для " + animal.getName());
    }

    public String getExaminationReport() {
        return "Отчёт о медицинском осмотре:\n" +
                "Дата: " + date + "\n" +
                "Животное: " + animal.getName() + " (" + animal.getSpecies() + ")\n" +
                "Ветеринар: " + veterinarian.getName() + "\n" +
                "Заметки: " + notes + "\n" +
                "Результаты: " + results + "\n" +
                "Статус здоровья: " + animal.getHealthStatus();
    }

    public Animal getAnimal() {
        return animal;
    }
}