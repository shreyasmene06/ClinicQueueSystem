# Clinic Queue Management System

A Java-based command-line interface (CLI) application designed to manage patient registration and queueing in a clinical environment. The system implements a priority-based queueing mechanism, ensuring that emergency cases are addressed before routine consultations, and includes persistent data storage capabilities.

## Key Features

- **Dual-Queue Architecture**: Categorizes patients into Emergency and Normal streams.
- **Priority-Based Dispatch**: Guarantees that emergency patients are served first, regardless of their position in the normal queue.
- **Automated Token Assignment**: Assigns a unique, formatted identifier (e.g., T-001) to each patient upon registration.
- **Persistent Data Management**: Utilizes Java Serialization to save and load queue states to a local file (`queue_data.dat`), preventing data loss between sessions.
- **Real-time Queue Monitoring**: Provides a comprehensive dashboard showing current wait times, patient counts, and registration timestamps.

## Project Structure

- **Main.java**: Serves as the application entry point, managing user interaction and menu navigation.
- **ClinicQueue.java**: Contains the core business logic for queue management, prioritization, and data persistence.
- **Patient.java**: Defines the patient data model, including attributes for token numbers, classification types, and arrival timestamps.

## Technical Requirements

- **Java Development Kit (JDK)**: Version 8 or higher.
- A standard terminal or command prompt environment.

## Installation and Execution

1. **Clone the repository**:
   ```bash
   git clone https://github.com/shreyasmene06/ClinicQueueSystem.git
   cd ClinicQueueSystem
   ```

2. **Compile the source code**:
   ```bash
   javac *.java
   ```

3. **Execute the application**:
   ```bash
   java Main
   ```

## Functional Overview

The application is controlled via a menu-driven interface with the following options:

1. **Add Normal Patient**: Registers a patient for standard medical services.
2. **Add Emergency Patient**: Registers a high-priority patient for immediate attention.
3. **Serve Next Patient**: Processes the next patient in the priority sequence.
4. **Peek Next Patient**: Displays the details of the next patient without altering the queue state.
5. **View Queue**: Renders a formatted table of all currently waiting patients.
6. **Save Queue**: Commits the current queue status to persistent storage.
7. **Load Queue**: Restores the queue state from the saved data file.
8. **Delete Save File**: Removes the persistent data file from the disk.
9. **Exit**: Terminates the application (includes a data-save prompt if patients remain in the queue).

## Data Persistence Strategy

The system utilizes the `java.io.Serializable` interface to persist the `ClinicQueue` state. Data is stored in a binary format within `queue_data.dat`, preserving the integrity of token sequences and patient records across application restarts.

---
*Optimizing clinical workflows through systematic queue management.*
