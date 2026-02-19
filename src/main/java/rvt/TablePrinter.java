package rvt;

import java.util.List;

public class TablePrinter {
    public static void printTable(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("Nav reģistrētu studentu.");
            return;
        }

        String format = "| %-12s | %-12s | %-20s | %-12s | %-20s |%n";
        String separator = "+--------------+--------------+----------------------+--------------+----------------------+%n";

        System.out.printf(separator);
        System.out.printf(format, "Vārds", "Uzvārds", "E-pasts", "P.Kods", "Reģ. Datums");
        System.out.printf(separator);

        for (Student s : students) {
            System.out.printf(format, 
                s.getName(), 
                s.getSurname(), 
                s.getEmail(), 
                s.getPersonalCode(), 
                s.getFormattedDate());
        }
        System.out.printf(separator);
    }
}