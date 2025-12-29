package medical;

import animals.Animal;
import java.util.ArrayList;
import java.util.List;

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
        for (MedicalExamination examination : examinations) {
            if (examination.getAnimal() != null && examination.getAnimal().equals(animal)) {
                result.add(examination);
            }
        }
        return result;
    }

    public void displayAllExaminations() {
        System.out.println("\n=== МЕДИЦИНСКИЕ ОСМОТРЫ ===");
        for (MedicalExamination examination : examinations) {
            System.out.println(examination.getExaminationReport());
            System.out.println("---");
        }
    }
}