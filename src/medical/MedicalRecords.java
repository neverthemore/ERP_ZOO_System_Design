package medical;

import animals.Animal;
import java.util.ArrayList;
import java.util.List;

// Принцип SRP: управление медицинскими записями
public class MedicalRecords {
    private List<MedicalExamination> examinations;

    public MedicalRecords() {
        this.examinations = new ArrayList<>();
    }

    public void addExamination(MedicalExamination examination) {
        examinations.add(examination);
    }

    public List<MedicalExamination> getExaminationsForAnimal(Animal animal) {
        List<MedicalExamination> result = new ArrayList<>();
        for (MedicalExamination exam : examinations) {
            if (exam.getAnimal().equals(animal)) {
                result.add(exam);
            }
        }
        return result;
    }

    public void displayAllExaminations() {
        System.out.println("\n=== Медицинские осмотры ===");
        for (MedicalExamination exam : examinations) {
            System.out.println(exam.getExaminationReport());
            System.out.println("---");
        }
    }
}