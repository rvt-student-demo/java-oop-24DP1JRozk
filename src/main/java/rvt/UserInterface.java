package rvt; // Pārliecinies, ka šī rinda atbilst tavas mapes nosaukumam

import java.util.Scanner;

public class UserInterface {
    private TodoList list;
    private Scanner scanner;

    // Konstruktors
    public UserInterface(TodoList list, Scanner scanner) {
        this.list = list;
        this.scanner = scanner;
    }

    // Metode, kas palaiž lietotāja saskarni
    public void start() {
        while (true) {
            System.out.print("Command: ");
            String command = scanner.nextLine();

            if (command.equals("stop")) {
                break;
            }

            if (command.equals("add")) {
                System.out.print("To add: ");
                String task = scanner.nextLine();
                this.list.add(task);
            } else if (command.equals("list")) {
                this.list.print();
            } else if (command.equals("remove")) {
                System.out.print("Which one is removed? ");
                int number = Integer.valueOf(scanner.nextLine());
                this.list.remove(number);
            }
        }
    }
}