package employees;

import animals.Animal;
import feeding.FeedingSchedule;

// Принцип ISP: конкретные обязанности
public class Zookeeper extends ZooEmployee {

    public Zookeeper(String name, String employeeId) {
        super(name, "Cмотритель зоопарка", employeeId);
    }

    @Override
    public void performDuties() {
        System.out.println("Кормление животных и уборка вольеров");
    }

    public void feedAnimal(Animal animal, FeedingSchedule schedule) {
        System.out.println(name + " кормит " + animal.getName() +
                " с " + animal.getDietType() + " диета");
        schedule.recordFeeding(animal, this);
    }

    public void cleanEnclosure(String enclosureId) {
        System.out.println(name + " выполняется очистка вольера " + enclosureId);
    }
}