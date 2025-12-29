package animals;

public abstract class Bird extends AbstractAnimal {
    protected double wingspan;
    protected boolean canFly;

    public Bird(String name, String species, int age, double weight,
                double wingspan, boolean canFly) {
        super(name, species, age, weight);
        this.wingspan = wingspan;
        this.canFly = canFly;
    }

    public double getWingspan() { return wingspan; }

    public boolean canFly() { return canFly; }

    public abstract String getFlightPattern();
}