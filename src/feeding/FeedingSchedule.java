package feeding;

import animals.Animal;
import employees.Zookeeper;
import java.time.LocalDateTime;

// Принцип SRP: управление расписанием кормления
public class FeedingSchedule {
    private Animal animal;
    private String foodType;
    private double portionSize;
    private int timesPerDay;
    private LocalDateTime lastFeeding;

    public FeedingSchedule(Animal animal, String foodType, double portionSize, int timesPerDay) {
        this.animal = animal;
        this.foodType = foodType;
        this.portionSize = portionSize;
        this.timesPerDay = timesPerDay;
    }

    public void recordFeeding(Animal animal, Zookeeper zookeeper) {
        this.lastFeeding = LocalDateTime.now();
        System.out.println("Кормление зафиксировано: " + animal.getName() +
                " покормлено(-а) смотрителем " + zookeeper.getName() +
                " в " + lastFeeding);
    }

    public boolean isFeedingDue() {
        if (lastFeeding == null) return true;
        LocalDateTime nextFeeding = lastFeeding.plusHours(24 / timesPerDay);
        return LocalDateTime.now().isAfter(nextFeeding);
    }

    public String getFeedingInstructions() {
        return "Инструкция по кормлению для " + animal.getName() + ":\n" +
                "Тип корма: " + foodType + "\n" +
                "Размер порции: " + portionSize + " кг\n" +
                "Частота: " + timesPerDay + " раз(а) в день\n" +
                "Последнее кормление: " + (lastFeeding != null ? lastFeeding : "Никогда");
    }
}
