package animals;

public class Elephant extends Mammal {
    private double tuskLength;

    public Elephant(String name, int age, double weight, double tuskLength) {
        super(name, "Слоняра", age, weight, false);
        this.tuskLength = tuskLength;
        setDietType("Травоядная");
    }

    public double getTuskLength() { return tuskLength; }

    @Override
    public String getSound() {
        return "Трубление";
    }
}