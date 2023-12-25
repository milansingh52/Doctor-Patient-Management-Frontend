# Overview
- The Doctor-Patient Management System developed using Spring Boot for the backend and JavaFX for the frontend.
- This system facilitates efficient management of patient and doctor records through RESTful APIs, allowing users to seamlessly list, add, and update information.
- With a user-friendly JavaFX interface featuring screens for patient and doctor management, the system prioritizes simplicity and effectiveness.
- This documentation outlines the tools, dependencies, and the project's file structure.

## Project Output
- Doctor Management
![output1](https://github.com/milansingh52/Doctor-Patient-Management-Frontend/assets/111845982/279ed4c3-6068-4eae-aebe-b2032b353101)

- Patient Management
![output2](https://github.com/milansingh52/Doctor-Patient-Management-Frontend/assets/111845982/6ed6a4cd-430d-4666-91d6-5cfa143f4820)

## Tools
1. Eclipse: Integrated Development Environment (IDE) for Java development.
2. JavaFX SceneBuilder: Visual layout tool for JavaFX applications.
3. JavaFX SDK: The official software development kit for JavaFX.

## Dependencies
1. JavaFX Controls: Core controls for building JavaFX applications.
2. JavaFX Graphics: Graphics-related libraries for JavaFX.
3. <a>org.json:json</a>: Library for handling JSON data in Java applications.

# UI Design
## Screens:
- Separate screens for patients and doctors including listing doctor, listing patient, adding a new patient, and adding a new doctor.
- Utilized JavaFX controls like TableView, TextField, and Button.
- Integrated JavaFX Ribbon Bar for enhanced UI design.

## Controllers
- Implemented controllers for each screen, ensuring proper separation of concerns.

  1. MainController.java: Manage the Main/Home window
  2. DoctorController.java : Manages the screen for listing and adding a new doctors
  3. PatientController.java: Manages the screen for listing and adding a new patients.


## Data Binding and Interaction
- Used JavaFX properties to bind UI elements to data, facilitating dynamic updates.
- Handled user interactions to trigger API calls to the backend using JavaFX HttpClient for seamless communication with the Spring Boot backend.

## Integration with Backend
- Integrated JavaFX HttpClient to connect to the Spring Boot backend, implementing methods to make HTTP requests to the backend RESTful APIs.
