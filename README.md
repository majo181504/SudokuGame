# Sudoku (JavaFX)

A simple Sudoku game implemented in Java using JavaFX and Maven. This project provides a graphical interface with a welcome screen and an interactive Sudoku grid.

## Requirements

- Java Development Kit (JDK) 17 or newer
- Apache Maven 3.6+
- JavaFX (OpenJFX) if not provided via Maven dependencies
- Windows OS (development and basic testing performed on Windows)

## How to Run

From the project root directory:

1. Run directly with Maven (recommended if `javafx-maven-plugin` is configured):
2. Build a runnable jar:
   Then run the jar (replace `C:\path\to\javafx-sdk\lib` with your JavaFX SDK location and `target\sudoku-1.0.jar` with the actual artifact name):
3. Run from IntelliJ IDEA:
- Import the Maven project.
- Ensure Project SDK is set to JDK 17+.
- If JavaFX is not managed by Maven, add VM options:
- Run the main application class `com.example.sudoku.Main`.

## Features

- JavaFX graphical user interface with a welcome stage.
- Start a new Sudoku game from the welcome screen.
- Interactive Sudoku grid with cell input and basic validation.
- Visual feedback for invalid entries.
- Completion detection for solved puzzles.
- Maven project structure for easy build and dependency management.

## Project Structure

- `src/main/java` — Java source code
    - `com.example.sudoku` — application package
    - `com.example.sudoku.view` — JavaFX stages and UI classes (e.g., welcome stage)
- `src/main/resources` — FXML, CSS and other resources
- `pom.xml` — Maven configuration

Main entry point: `src/main/java/com/example/sudoku/Main.java` (class `com.example.sudoku.Main`).

## Running Tests

If tests are present, run:
## Troubleshooting

- JavaFX runtime errors: ensure JavaFX SDK version matches your JDK and `--module-path` / `--add-modules` VM options are set correctly.
- Verify `mvn -v` and `java -version` to confirm correct Maven and JDK installations.
- If FXML resources fail to load, confirm resource paths and that files are located under `src/main/resources`.

## Contributing

- Fork the repository and create feature branches.
- Open pull requests with a clear description of changes.
- Keep code style consistent and update tests where applicable.

## License

Specify license information here (e.g., MIT). Replace this section with the chosen license text or a short license notice.

## Contact / Maintainers

List maintainers or contact information (email, GitHub handle) here if applicable.
