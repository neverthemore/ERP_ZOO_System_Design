package animals;

public class Penguin extends Bird {
    public Penguin(String name, int age, double weight) {
        super(name, "Пингвин", age, weight, 0.6, false);
        setDietType("Плотоядная");
    }

    @Override
    public String getFlightPattern() {
        return "Плавает под водой вместо того, чтобы летать";
    }
}