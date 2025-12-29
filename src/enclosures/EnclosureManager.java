package enclosures;

import java.util.HashMap;
import java.util.Map;

// Принцип SRP: управление всеми вольерами
public class EnclosureManager {
    private Map<String, Enclosure> enclosures;

    public EnclosureManager() {
        this.enclosures = new HashMap<>();
    }

    public void addEnclosure(Enclosure enclosure) {
        enclosures.put(enclosure.getId(), enclosure);
    }

    public Enclosure getEnclosure(String id) {
        return enclosures.get(id);
    }

    public void removeEnclosure(String id) {
        enclosures.remove(id);
    }

    public void displayAllEnclosures() {
        System.out.println("\n=== Все вольеры ===");
        for (Enclosure enclosure : enclosures.values()) {
            enclosure.displayEnclosureInfo();
            System.out.println();
        }
    }
}