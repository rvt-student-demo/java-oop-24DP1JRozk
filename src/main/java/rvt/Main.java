package rvt;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentUI ui = new StudentUI();
            ui.show();
        });
    }
}