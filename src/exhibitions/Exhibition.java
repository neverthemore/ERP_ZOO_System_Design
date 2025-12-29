package exhibitions;

import animals.Animal;
import employees.Employee;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Exhibition {
    private String name;
    private String theme;
    private LocalDateTime dateTime;
    private List<Animal> participatingAnimals;
    private List<Employee> assignedStaff;
    private int maxVisitors;

    public Exhibition(String name, String theme, LocalDateTime dateTime, int maxVisitors) {
        this.name = name;
        this.theme = theme;
        this.dateTime = dateTime;
        this.maxVisitors = maxVisitors;
        this.participatingAnimals = new ArrayList<>();
        this.assignedStaff = new ArrayList<>();
    }

    public boolean addAnimal(Animal animal) {
        if (participatingAnimals.size() < 10) { // Максимум 10 животных на выставке
            participatingAnimals.add(animal);
            return true;
        }
        return false;
    }

    public void assignStaff(Employee employee) {
        assignedStaff.add(employee);
    }

    public String getExhibitionInfo() {
        StringBuilder info = new StringBuilder();
        info.append("=== ВЫСТАВКА: ").append(name).append(" ===\n");
        info.append("Тема: ").append(theme).append("\n");
        info.append("Дата и время: ").append(dateTime).append("\n");
        info.append("Максимум посетителей: ").append(maxVisitors).append("\n");
        info.append("Животных участвует: ").append(participatingAnimals.size()).append("\n");

        info.append("Животные:\n");
        for (Animal animal : participatingAnimals) {
            info.append("  - ").append(animal.getName()).append(" (").append(animal.getSpecies()).append(")\n");
        }

        info.append("Назначенный персонал:\n");
        for (Employee employee : assignedStaff) {
            info.append("  - ").append(employee.getName()).append(" (").append(employee.getPosition()).append(")\n");
        }

        return info.toString();
    }

    public String getName() {
        return name;
    }

    public String getTheme() {
        return theme;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getMaxVisitors() {
        return maxVisitors;
    }

    public List<Animal> getParticipatingAnimals() {
        return new ArrayList<>(participatingAnimals);
    }

    public List<Employee> getAssignedStaff() {
        return new ArrayList<>(assignedStaff);
    }
}