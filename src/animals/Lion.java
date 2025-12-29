package animals;

public class Lion extends Mammal {
    public Lion(String name, int age, double weight) {
        super(name, "Лев", age, weight, true);
        setDietType("Плотоядная");
    }

    @Override
    public String getSound() {
        return "рык";
    }
}