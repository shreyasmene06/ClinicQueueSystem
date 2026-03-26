import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Patient class - stores info about each patient in the clinic
public class Patient implements Serializable {

    public enum Type {
        NORMAL, EMERGENCY
    }

    private int tokenNumber;
    private String name;
    private Type type;
    private String timeAdded;

    public Patient(int tokenNumber, String name, Type type) {
        this.tokenNumber = tokenNumber;
        this.name = name;
        this.type = type;
        // record what time they registered
        this.timeAdded = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public int getTokenNumber() { return tokenNumber; }
    public String getName() { return name; }
    public Type getType() { return type; }
    public String getTimeAdded() { return timeAdded; }

    // returns token like T-001
    public String getTokenTag() {
        return String.format("T-%03d", tokenNumber);
    }

    // used to print one row in the queue display
    public String getDisplayRow() {
        String typeLabel = (type == Type.EMERGENCY) ? "Emergency" : "Normal";
        return String.format("  %-10s %-20s %-12s %s",
                getTokenTag(), name, typeLabel, timeAdded);
    }

    @Override
    public String toString() {
        return getTokenTag() + " - " + name + " (" + type + ")";
    }
}
