import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

// manages the two queues - emergency always goes first
public class ClinicQueue implements Serializable {

    private Queue<Patient> emergencyQ;
    private Queue<Patient> normalQ;
    private int nextToken;  // auto increments every time someone is added

    public ClinicQueue() {
        emergencyQ = new LinkedList<>();
        normalQ = new LinkedList<>();
        nextToken = 0;
    }

    // adds patient to whichever queue is right for them
    public Patient addPatient(String name, Patient.Type type) {
        nextToken++;
        Patient p = new Patient(nextToken, name, type);

        if (type == Patient.Type.EMERGENCY) {
            emergencyQ.offer(p);
        } else {
            normalQ.offer(p);
        }
        return p;
    }

    // gets the next patient - emergency first, then normal
    public Patient getNextPatient() {
        if (!emergencyQ.isEmpty()) {
            return emergencyQ.poll();
        }
        if (!normalQ.isEmpty()) {
            return normalQ.poll();
        }
        return null; // queue is empty
    }

    // just looks without removing
    public Patient peekNext() {
        if (!emergencyQ.isEmpty()) return emergencyQ.peek();
        if (!normalQ.isEmpty()) return normalQ.peek();
        return null;
    }

    public void displayQueue() {
        System.out.println("\n========================================");
        System.out.println("           CURRENT QUEUE STATUS");
        System.out.println("========================================");

        System.out.println("\n[EMERGENCY PATIENTS] - " + emergencyQ.size() + " waiting");
        if (emergencyQ.isEmpty()) {
            System.out.println("  (no emergency patients)");
        } else {
            printTableHeader();
            int i = 1;
            for (Patient p : emergencyQ) {
                System.out.println("  " + i + ". " + p.getDisplayRow().trim());
                i++;
            }
        }

        System.out.println("\n[NORMAL PATIENTS] - " + normalQ.size() + " waiting");
        if (normalQ.isEmpty()) {
            System.out.println("  (no normal patients)");
        } else {
            printTableHeader();
            int i = 1;
            for (Patient p : normalQ) {
                System.out.println("  " + i + ". " + p.getDisplayRow().trim());
                i++;
            }
        }

        System.out.println("\n----------------------------------------");
        System.out.println("Total in queue: " + getTotalCount() + "  |  Tokens issued: " + nextToken);
        System.out.println("========================================\n");
    }

    private void printTableHeader() {
        System.out.printf("  %-10s %-20s %-12s %s%n", "Token", "Name", "Type", "Time");
        System.out.println("  " + "-".repeat(52));
    }

    public int getTotalCount() {
        return emergencyQ.size() + normalQ.size();
    }

    public boolean isEmpty() {
        return emergencyQ.isEmpty() && normalQ.isEmpty();
    }

    public int getEmergencyCount() { return emergencyQ.size(); }
    public int getNormalCount() { return normalQ.size(); }

    // ---- file saving/loading ----

    public boolean saveQueue() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("queue_data.dat"));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
            return false;
        }
    }

    public static ClinicQueue loadQueue() {
        File f = new File("queue_data.dat");
        if (!f.exists()) {
            System.out.println("No saved data found, starting fresh.");
            return new ClinicQueue();
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
            ClinicQueue loaded = (ClinicQueue) in.readObject();
            in.close();
            System.out.println("Loaded " + loaded.getTotalCount() + " patient(s) from file.");
            return loaded;
        } catch (Exception e) {
            System.out.println("Could not load file: " + e.getMessage());
            return new ClinicQueue();
        }
    }

    public static void deleteSaveFile() {
        File f = new File("queue_data.dat");
        if (f.exists()) {
            f.delete();
            System.out.println("Save file deleted.");
        } else {
            System.out.println("No file to delete.");
        }
    }
}
