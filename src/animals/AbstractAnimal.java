package animals;

// Принцип SRP: отвечает только за базовую реализацию животного
public abstract class AbstractAnimal implements Animal {
    protected String name;
    protected String species;
    protected int age;
    protected double weight;
    protected String healthStatus;
    protected String dietType;

    public AbstractAnimal(String name, String species, int age, double weight) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.weight = weight;
        this.healthStatus = "Здоров";
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getSpecies() { return species; }

    @Override
    public int getAge() { return age; }

    @Override
    public double getWeight() { return weight; }

    @Override
    public String getHealthStatus() { return healthStatus; }

    @Override
    public void setHealthStatus(String status) {
        this.healthStatus = status;
    }

    @Override
    public String getDietType() { return dietType; }

    protected void setDietType(String dietType) {
        this.dietType = dietType;
    }
}