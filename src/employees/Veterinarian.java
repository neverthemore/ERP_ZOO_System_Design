package employees;

import animals.Animal;
import medical.MedicalExamination;

// Принцип SRP: отвечает только за ветеринарные обязанности
public class Veterinarian extends ZooEmployee {

    public Veterinarian(String name, String employeeId) {
        super(name, "Ветеринар", employeeId);
    }

    @Override
    public void performDuties() {
        System.out.println("Проведение медицинских осмотров и процедур");
    }

    public MedicalExamination performCheckup(Animal animal, String notes) {
        System.out.println("Ветеринар " + name + " изучает " + animal.getName());
        return new MedicalExamination(animal, this, notes);
    }

    public void prescribeTreatment(Animal animal, String treatment) {
        System.out.println("Ветеринар " + treatment + " для " + animal.getName());
        animal.setHealthStatus("Находящийся на лечении: " + treatment);
    }
}