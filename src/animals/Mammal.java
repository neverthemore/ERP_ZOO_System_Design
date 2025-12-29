package animals;

// Принцип LSP: может заменять AbstractAnimal
public abstract class Mammal extends AbstractAnimal {
    protected boolean hasFur;

    public Mammal(String name, String species, int age, double weight, boolean hasFur) {
        super(name, species, age, weight);
        this.hasFur = hasFur;
    }

    public boolean hasFur() { return hasFur; }

    public abstract String getSound();
}