# Clinic Queue Management System 

A Java-based command-line interface (CLI) application developed to manage patient registration and queueing in a healthcare context.  The system incorporates a priority-based queueing method, guaranteeing that emergency cases are serviced before routine consultations, and has persistent data storage capabilities. 

## Key Features 

- **Dual-Queue Architecture**: Categorizes patients into Emergency and Normal streams. 
- **Priority-Based Dispatch**: Guarantees that emergency patients are served first, regardless of their position in the typical queue. 
- **Automated Token Assignment**: Assigns a unique, formatted identifier (e.g., T-001) to each patient upon registration. 
- **Persistent Data Management**: Utilizes Java Serialization to save and load queue states to a local file (`queue_data.dat`), preventing data loss between sessions. 
- **Real-time Queue Monitoring**: Provides a comprehensive dashboard showing current wait times, patient counts, and registration timestamps. 

## Project Structure 
- **Main.java**: Serves as the application entry point, coordinating user interaction and menu navigation. 
- **ClinicQueue.java**: Contains the basic business logic for queue management, prioritising, and data persistence. 
- **Patient.java**: Defines the patient data model, including properties for token numbers, classification categories, and arrival timestamps. 

## Technical Requirements 
- **Java Development Kit (JDK)**: Version 8 or higher. 
- A conventional terminal or command prompt environment. 

## Installation and Execution 

1. **Clone the repository**: 
```bash git clone https://github.com/shreyasmene06/ClinicQueueSystem.git cd ClinicQueueSystem ``` 

2. **Compile the source code**: 
```bash javac *.java ``` 

3. **Execute the application**: 
```bash java Main ``` 

## Functional Overview 

The application is operated using a menu-driven interface with the following options: 

1. **Add Normal Patient**: Registers a patient for routine medical services. 
2. **Add Emergency Patient**: Registers a high-priority patient for quick attention. 
3. **Serve Next Patient**: Processes the next patient in the priority order. 
4. **Peek Next Patient**: Displays the details of the next patient without modifying the queue state. 
5. **View Queue**: Renders a structured table of all presently waiting patients. 
6. **Save Queue**: Commits the current queue status to permanent storage. 
7. **Load Queue**: Restores the queue status from the saved data file. 
8. **Delete Save File**: Removes the persistent data file from the disk. 
9. **Exit**: Terminates the application (including a data-save prompt if patients remain in the queue). 

## Data Persistence Strategy 

The system leverages the `java.io.Serializable` interface to persist the `ClinicQueue` state.  Data is kept in a binary format within `queue_data.dat`, ensuring the integrity of token sequences and patient information between application restarts. 
--- 
*Optimizing clinical workflows through methodical queue management.*
