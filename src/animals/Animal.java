package animals;

// Принцип OCP: открыто для расширения (новых типов животных)
public interface Animal {
    String getName();
    String getSpecies();
    int getAge();
    double getWeight();
    String getHealthStatus();
    void setHealthStatus(String status);
    String getDietType();
}