package enclosures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Enclosure> getAllEnclosures() {
        return new ArrayList<>(enclosures.values());
    }

    public void displayAllEnclosures() {
        System.out.println("\n=== ВСЕ ВОЛЬЕРЫ ===");
        if (enclosures.isEmpty()) {
            System.out.println("Вольеров пока нет.");
        } else {
            for (Enclosure enclosure : enclosures.values()) {
                enclosure.displayEnclosureInfo();
                System.out.println();
            }
        }
    }
}