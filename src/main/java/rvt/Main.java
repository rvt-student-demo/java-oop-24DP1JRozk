package rvt;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("--- Studentu Reģistrācijas Sistēma ---");

        while (running) {
            System.out.println("\nIzvēlieties darbību:");
            System.out.println("  register - reģistrēt jauno lietotāju");
            System.out.println("  show     - rādīt visus lietotājus");
            System.out.println("  remove   - dzēst lietotāju");
            System.out.println("  edit     - rediģēt lietotāju");
            System.out.println("  exit     - apturēt programmu");
            System.out.print("Vada ievade: ");
            
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "register": handleRegister(); break;
                case "show": handleShow(); break;
                case "remove": handleRemove(); break;
                case "edit": handleEdit(); break;
                case "exit": 
                    running = false; 
                    System.out.println("Programma apturēta.");
                    break;
                default:
                    System.out.println("Nezināma komanda. Lūdzu mēģiniet vēlreiz.");
            }
        }
    }

    private static void handleRegister() {
        System.out.println("\n-- Reģistrācija --");
        String name = promptInput("Ievadiet vārdu (min 3 burti): ", InputValidator::isValidName);
        String surname = promptInput("Ievadiet uzvārdu (min 3 burti): ", InputValidator::isValidName);
        String email = promptInput("Ievadiet e-pastu: ", InputValidator::isValidEmail);
        String pk = promptInput("Ievadiet personas kodu (DDMMYY-XXXXX): ", InputValidator::isValidPersonalCode);

        Student s = new Student(name, surname, email, pk, LocalDateTime.now());
        
        try {
            manager.addStudent(s);
            System.out.println("Izdevās! Students veiksmīgi reģistrēts.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleShow() {
        System.out.println("\n-- Visu Studentu Saraksts --");
        TablePrinter.printTable(manager.getStudents());
    }

    private static void handleRemove() {
        System.out.print("Ievadiet personas kodu, kuru dzēst: ");
        String pk = scanner.nextLine().trim();
        if (manager.removeStudent(pk)) {
            System.out.println("Students ar personas kodu " + pk + " ir dzēsts.");
        } else {
            System.out.println("Students ar šādu personas kodu netika atrasts.");
        }
    }

    private static void handleEdit() {
        System.out.print("Ievadiet personas kodu studentam, kuru rediģēt: ");
        String pk = scanner.nextLine().trim();
        Student student = manager.getStudentByPk(pk);

        if (student == null) {
            System.out.println("Kļūda: Students ar šādu personas kodu netika atrasts.");
            return;
        }

        System.out.println("Ievadiet jaunos datus (atstājiet tukšu, lai saglabātu veco):");
        System.out.print("Jauns vārds [" + student.getName() + "]: ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty() && InputValidator.isValidName(name)) student.setName(name);

        System.out.print("Jauns uzvārds [" + student.getSurname() + "]: ");
        String surname = scanner.nextLine().trim();
        if (!surname.isEmpty() && InputValidator.isValidName(surname)) student.setSurname(surname);

        manager.saveChanges();
        System.out.println("Dati veiksmīgi atjaunināti!");
    }

    // Palīgmetode ievades pārbaudei un atkārtošanai, kamēr nav ievadīts pareizi
    private static String promptInput(String message, java.util.function.Predicate<String> validator) {
        String input;
        while (true) {
            System.out.print(message);
            input = scanner.nextLine().trim();
            if (validator.test(input)) {
                return input;
            } else {
                System.out.println("Kļūda: Neatbilstošs formāts, mēģiniet vēlreiz!");
            }
        }
    }
}