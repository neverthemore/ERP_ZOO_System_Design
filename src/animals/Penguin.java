package animals;

public class Penguin extends Bird {
    private String dietType;

    public Penguin(String имя, int возраст, double вес) {
        super(имя, "Пингвин", возраст, вес, 0.6, false);
        this.dietType = "Хищник";
        setDietType("Хищник");
    }

    @Override
    public String getFlightPattern() {
        return "Плавает под водой вместо полёта";
    }

    // Переопределяем абстрактный метод getDietType
    @Override
    public String getDietType() {
        return dietType;
    }
}