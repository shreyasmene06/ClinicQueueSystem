import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ClinicQueue clinic = new ClinicQueue();

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("  Clinic Queue Management System");
        System.out.println("====================================");

        int choice = 0;
        while (choice != 9) {
            showMenu();
            choice = getIntInput("Enter choice: ");

            if (choice == 1) {
                addPatient(Patient.Type.NORMAL);
            } else if (choice == 2) {
                addPatient(Patient.Type.EMERGENCY);
            } else if (choice == 3) {
                serveNext();
            } else if (choice == 4) {
                peekNext();
            } else if (choice == 5) {
                clinic.displayQueue();
            } else if (choice == 6) {
                if (clinic.saveQueue()) {
                    System.out.println("Queue saved successfully!\n");
                }
            } else if (choice == 7) {
                System.out.print("This will replace current queue. Continue? (y/n): ");
                String ans = sc.nextLine().trim();
                if (ans.equals("y") || ans.equals("Y")) {
                    clinic = ClinicQueue.loadQueue();
                } else {
                    System.out.println("Cancelled.\n");
                }
            } else if (choice == 8) {
                ClinicQueue.deleteSaveFile();
            } else if (choice == 9) {
                // ask to save before leaving if theres still patients
                if (!clinic.isEmpty()) {
                    System.out.print("Queue still has patients. Save before exit? (y/n): ");
                    String ans = sc.nextLine().trim();
                    if (ans.equals("y") || ans.equals("Y")) {
                        clinic.saveQueue();
                    }
                }
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid option, try again.\n");
            }
        }

        sc.close();
    }

    static void showMenu() {
        System.out.println("--- MENU ---");
        System.out.println("1. Add Normal Patient");
        System.out.println("2. Add Emergency Patient");
        System.out.println("3. Serve Next Patient");
        System.out.println("4. Peek Next Patient");
        System.out.println("5. View Queue");
        System.out.println("6. Save Queue");
        System.out.println("7. Load Queue");
        System.out.println("8. Delete Save File");
        System.out.println("9. Exit");
        System.out.println("Emergency: " + clinic.getEmergencyCount() +
                " | Normal: " + clinic.getNormalCount());
        System.out.println("------------");
    }

    static void addPatient(Patient.Type type) {
        System.out.print("Enter patient name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.\n");
            return;
        }

        Patient p = clinic.addPatient(name, type);
        System.out.println("Patient added!");
        System.out.println("Token: " + p.getTokenTag());
        System.out.println("Name: " + p.getName());
        System.out.println("Type: " + p.getType() + "\n");
    }

    static void serveNext() {
        if (clinic.isEmpty()) {
            System.out.println("No patients in queue.\n");
            return;
        }

        Patient p = clinic.getNextPatient();
        System.out.println("\n--- NOW SERVING ---");
        System.out.println("Token: " + p.getTokenTag());
        System.out.println("Name:  " + p.getName());
        System.out.println("Type:  " + p.getType());
        System.out.println("Time added: " + p.getTimeAdded());

        if (clinic.isEmpty()) {
            System.out.println("Queue is now empty.\n");
        } else {
            Patient next = clinic.peekNext();
            System.out.println("Next up: " + next.getTokenTag() + " - " + next.getName() + "\n");
        }
    }

    static void peekNext() {
        Patient p = clinic.peekNext();
        if (p == null) {
            System.out.println("Queue is empty.\n");
            return;
        }
        System.out.println("Next patient: " + p.getTokenTag() + " - " + p.getName() +
                " (" + p.getType() + ")\n");
    }

    // reads an int from user, returns -1 if something goes wrong
    static int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            int val = Integer.parseInt(sc.nextLine().trim());
            return val;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
