# Software Modeling – Map Visualization Application

This project is an academic software engineering case study developed as part
of the course **INF1163 – Object-Oriented Modeling and Design** at the
Université du Québec en Outaouais.

The application focuses on the design and implementation of a **multi-perspective
map visualization system**, inspired by Google Maps, with a strong emphasis on
software modeling, UML-driven design, and the rigorous application of
object-oriented design patterns.

---

## Objectives

The main objectives of this project are to:

- Design a robust and extensible map visualization system
- Apply a strict **Model–View–Controller (MVC)** architecture
- Support multiple synchronized views of the same image (main view, secondary view, thumbnail)
- Enable interactive zoom and translation using mouse events
- Implement **undo and redo** mechanisms for user actions
- Provide copy/paste and persistence of visualization parameters
- Demonstrate mastery of **GoF and GRASP design patterns**
- Ensure low coupling and high cohesion across all components

---

## Functional Overview

The system allows the user to:

- Load an image from the file system
- Display multiple perspectives of the same image simultaneously
- Zoom and translate each perspective independently
- Undo and redo zoom and translation operations
- Copy visualization parameters (zoom and offsets) from one perspective and paste them into another
- Save the state of a perspective to a JSON file
- Reload a saved perspective after restarting the application

All user actions are encapsulated as commands and managed through a centralized history.

---

## Architecture Overview

The application follows a **strict MVC architecture**.

### Model
- `ImageModel`  
  Manages image loading, dimensions, and file path.
- `Perspective`  
  Encapsulates visualization parameters (zoom, offsetX, offsetY) and acts as the
  observable subject in the Observer pattern.

### View
- `PerspectiveView`  
  Renders a logical view of the image based on the associated `Perspective`.
- `ThumbnailView`  
  Displays a reduced global view of the image.
- `MainWindow` and `ToolbarView`  
  Compose the graphical interface and user controls.

### Controller
- `AppController`  
  Centralizes application coordination, command execution, history management,
  and persistence.
- `MouseController`  
  Interprets mouse events (drag, wheel) and creates corresponding commands.
- `MenuController`  
  Handles menu and toolbar actions and delegates to the application controller.

The model is completely decoupled from the views, which observe state changes
via an Observer mechanism.

---

## Design Patterns Applied

### Model–View–Controller (MVC)
Ensures a clear separation of concerns between data, presentation, and control logic.

### Command
Encapsulates user actions (zoom, translation, copy, paste, save, load) as objects,
enabling flexible execution and full undo/redo support.

### Observer
Automatically synchronizes multiple views with the underlying model whenever
the visualization state changes.

### Singleton
Applied to `AppController` to provide a unique point of coordination for the application.

### Memento / Snapshot
Used to capture and restore the state of a perspective for undo/redo operations,
copy/paste, and persistence.

### Mediator
`AppController` acts as a mediator between views, controllers, model components,
and the command history, reducing direct coupling.

### GRASP Principles
- **Information Expert**: responsibilities assigned to the classes that own the data
- **Low Coupling**: minimal dependencies between components
- **High Cohesion**: each class has a focused and well-defined responsibility
- **Controller**: explicit controllers manage system events

---

## UML Diagrams

The design and dynamic behavior of the system are documented using UML diagrams
located in the `docs/uml` directory:

- Class Diagram
- Sequence Diagram – Zoom Command Execution
- Sequence Diagram – Undo / Redo Execution

These diagrams ensure traceability between the conceptual design and the implementation.

---

## Technologies

- Language: Java
- GUI Framework: Swing / JavaFX
- Modeling: UML
- Persistence: JSON
- Version Control: Git / GitHub

---

## Testing

The project includes a set of unit and functional tests developed using **JUnit 5**.
These tests were implemented as part of an academic laboratory (TP2) and aim to
validate the core behaviors of the system.

The test suite covers in particular:
- Zoom and translation commands
- Undo / Redo mechanisms managed by the command history
- Copy and paste of visualization parameters
- Saving and loading of a perspective state

The presence of these tests ensures that the implementation is consistent with
the designed architecture and that key user interactions behave as expected.


## Conclusion

This project demonstrates a rigorous application of object-oriented modeling
principles, design patterns, and architectural best practices. The resulting
system is modular, extensible, and well-aligned with the requirements of
professional software engineering and advanced academic work.

The inclusion of a dedicated test suite further strengthens the reliability of
the implementation by validating key user interactions and behavioral
scenarios. This combination of design, implementation, and testing reflects a
methodical and disciplined software engineering approach.

The architecture naturally supports future extensions such as additional view
types, new commands, enhanced persistence strategies, and improved user
interaction mechanisms.
