# Coffee Kiosk Simulation

## brief

This is a console program based on Java SE that simulates the complete functionality and operational flow of an automatic coffee vending machine. This program applies a variety of core design patterns to achieve a clear and extensible architecture.

### Core Features

* **State Machine**: The machine switches smoothly between `standby`, `ordering`, and `making` states.
* **Payment and refund**: Supports coin insertion, real-time balance display, and coin refunds.
* **Inventory check**: Automatically checks that the required ingredients are available.
* **Command Parsers**: Automatically parses and executes commands through the chain of responsibility.

## Core Design Patterns

The following four design patterns are applied in this project:

* **State Pattern**: Managing the behavior of machines under different life cycles.
* **Command Pattern**: Decouple user input commands from business logic.
* **Chain of responsibilities**: Validating commands through the chain of responsibility.
* **Factory Pattern**: Encapsulates the creation logic and recipes for different coffee types (latte, cappuccino, decaf).

## How to run

### Environmental requirements

* **Java Development Kit (JDK) 11** or higher.
* The project needs to be imported in an IDE such as **IntelliJ IDEA** and supports **Maven/Gradle** dependency synchronization (for `Jackson` libraries).

### Startup Steps 

1.  **Import Project**: Open IDEA, select **File -> Open** and import this project folder.
2.  **Synchronize Dependencies**: Wait for the IDE to automatically synchronize dependencies (based on `pom.xml`).
3.  **Run Main Class**: Run `com.jackson.coffee_kiosk.Main`.

### User Commands

After the program starts, the user can enter the following commands at the console:

| Operation | cmd(example) | description |
| :--- | :--- | :--- |
| **Start order** | `order` | Entering ordering mode from standby. |
| **Insert coin** | `c100` | `c` followed by the amount (in cents). |
| **select coffee** | `1` | Enter the menu number to select. |
| **refound** | `q` | Get a refund of any balance. |
| **pass** | `enter` | Used to advance/refresh the view on the console. |
