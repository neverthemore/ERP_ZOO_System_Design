package enclosures;

import animals.Animal;
import java.util.ArrayList;
import java.util.List;

// Принцип SRP: управление только вольером и его животными
public class Enclosure {
    private String id;
    private String type; // Savannah, Aquarium, Aviary, etc.
    private double area;
    private double temperature;
    private List<Animal> animals;
    private int maxCapacity;

    public Enclosure(String id, String type, double area, double temperature, int maxCapacity) {
        this.id = id;
        this.type = type;
        this.area = area;
        this.temperature = temperature;
        this.maxCapacity = maxCapacity;
        this.animals = new ArrayList<>();
    }

    public boolean addAnimal(Animal animal) {
        if (animals.size() < maxCapacity) {
            animals.add(animal);
            System.out.println(animal.getName() + " добавлен в вольер " + id);
            return true;
        }
        System.out.println("Вольер " + id + " заполнен");
        return false;
    }

    public boolean removeAnimal(Animal animal) {
        return animals.remove(animal);
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public double getArea() { return area; }
    public double getTemperature() { return temperature; }
    public void setTemperature(double temp) { this.temperature = temp; }
    public int getAnimalCount() { return animals.size(); }
    public int getMaxCapacity() { return maxCapacity; }

    public void displayEnclosureInfo() {
        System.out.println("=== Вольер " + id + " ===");
        System.out.println("Тип: " + type);
        System.out.println("Площадь: " + area + " m²");
        System.out.println("Температура: " + temperature + "°C");
        System.out.println("Животные: " + animals.size() + "/" + maxCapacity);
        for (Animal animal : animals) {
            System.out.println("  - " + animal.getName() + " (" + animal.getSpecies() + ")");
        }
    }
}