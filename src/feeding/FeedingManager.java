package feeding;

import animals.Animal;
import java.util.HashMap;
import java.util.Map;

// Принцип OCP: можно добавлять новые типы кормления
public class FeedingManager {
    private Map<Animal, FeedingSchedule> schedules;

    public FeedingManager() {
        this.schedules = new HashMap<>();
    }

    public void addFeedingSchedule(Animal animal, FeedingSchedule schedule) {
        schedules.put(animal, schedule);
    }

    public FeedingSchedule getSchedule(Animal animal) {
        return schedules.get(animal);
    }

    public void checkFeedingStatus() {
        System.out.println("\n=== СТАТУС КОРМЛЕНИЯ ===");
        for (Map.Entry<Animal, FeedingSchedule> entry : schedules.entrySet()) {
            Animal animal = entry.getKey();
            FeedingSchedule schedule = entry.getValue();

            if (schedule.isFeedingDue()) {
                System.out.println("⚠️  " + animal.getName() + " нуждается в кормлении!");
            } else {
                System.out.println("✓ " + animal.getName() + " накормлено(-а) по расписанию");
            }
        }
    }
}