package rvt;

import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class TodoList {
    private ArrayList<String> tasks;
    private final String filePath = "data/todo.csv"; 

    public TodoList() {
        this.tasks = new ArrayList<>();
        this.loadFromFile(); // #2: Ielādē datus pie objekta izveides
    }

    // #2: Nolasa datus no todo.csv
    private void loadFromFile() {
        try (Scanner fileScanner = new Scanner(Paths.get(filePath))) {
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine(); // Izlaiž virsrakstu (id,task)
            }
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    this.tasks.add(parts[1]);
                }
            }
        } catch (Exception e) {
            // Ja fails neeksistē, tas tiks izveidots pie pirmā add()
        }
    }

    // #3: Atgriež pēdējo ID
    private int getLastId() {
        return this.tasks.size();
    }

    // #4: Pievieno uzdevumu un izmanto getLastId()
    public void add(String task) {
        this.tasks.add(task);
        int currentId = this.getLastId(); 
        System.out.println("Pievienots uzdevums nr. " + currentId);
        this.updateFile();
    }

    // #5: Pārraksta .csv failu
    private boolean updateFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("id,task\n");
            for (int i = 0; i < this.tasks.size(); i++) {
                writer.write((i + 1) + "," + this.tasks.get(i) + "\n");
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void print() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ": " + this.tasks.get(i));
        }
    }

    // #5: Dzēš uzdevumu pēc ID un atjaunina failu
    public void remove(int id) {
        if (id > 0 && id <= this.tasks.size()) {
            this.tasks.remove(id - 1);
            this.updateFile();
        }
    }
}