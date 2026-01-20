package rvt;

import java.util.ArrayList;

public class TodoList {
    private ArrayList<String> tasks;

    public TodoList() {
        this.tasks = new ArrayList<>();
    }

    public void add(String task) {
        this.tasks.add(task);
    }

    public void print() {
        for (int i = 0; i < this.tasks.size(); i++) {
            // Indekss sarakstā sākas no 0, bet izvadē vajag sākt no 1, tāpēc (i + 1)
            System.out.println((i + 1) + ": " + this.tasks.get(i));
        }
    }

    public void remove(int number) {
        // Lietotājs ievada skaitli, kas ir par 1 lielāks nekā indekss (jo saraksts sākas ar 1)
        // Tāpēc mēs atņemam 1, lai iegūtu pareizo indeksu ArrayList-ā.
        this.tasks.remove(number - 1);
    }
}