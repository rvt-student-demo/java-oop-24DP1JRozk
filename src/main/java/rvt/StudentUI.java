package rvt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class StudentUI {
    private final JFrame window;
    private final StudentManager manager;
    private final DefaultTableModel tableModel;

    public StudentUI() {
        manager = new StudentManager();
        window = new JFrame("Studentu Reģistrācijas Sistēma");
        tableModel = new DefaultTableModel(new String[]{"Vārds", "Uzvārds", "E-pasts", "Pers. kods", "Reģistrēts"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        initialize();
    }

    private void initialize() {
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(1024, 768);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        JTable studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScroll = new JScrollPane(studentTable);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        JButton registerButton = new JButton("Reģistrēt");
        JButton removeButton = new JButton("Dzēst");
        JButton editButton = new JButton("Rediģēt");
        JButton refreshButton = new JButton("Atjaunot");

        registerButton.addActionListener(e -> handleRegister());
        removeButton.addActionListener(e -> handleRemove());
        editButton.addActionListener(e -> handleEdit());
        refreshButton.addActionListener(e -> refreshStudentTable());

        buttonPanel.add(registerButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        buttonPanel.add(refreshButton);

        JPanel content = new JPanel(new BorderLayout(12, 12));
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        content.add(buttonPanel, BorderLayout.NORTH);
        content.add(tableScroll, BorderLayout.CENTER);

        window.setContentPane(content);
        refreshStudentTable();
    }

    public void show() {
        window.setVisible(true);
    }

    private void handleRegister() {
        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField pkField = new JTextField();

        form.add(new JLabel("Vārds:"));
        form.add(nameField);
        form.add(new JLabel("Uzvārds:"));
        form.add(surnameField);
        form.add(new JLabel("E-pasts:"));
        form.add(emailField);
        form.add(new JLabel("Personas kods:"));
        form.add(pkField);

        int result = JOptionPane.showConfirmDialog(window, form, "Reģistrēt studentu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String email = emailField.getText().trim();
        String pk = pkField.getText().trim();

        if (!InputValidator.isValidName(name) || !InputValidator.isValidName(surname)) {
            showError("Vārds un uzvārds jābūt vismaz 3 burtiem.");
            return;
        }
        if (!InputValidator.isValidEmail(email)) {
            showError("Lūdzu ievadiet derīgu e-pasta adresi.");
            return;
        }
        if (!InputValidator.isValidPersonalCode(pk)) {
            showError("Personas kodam jābūt formātā DDMMYY-XXXXX.");
            return;
        }

        Student student = new Student(name, surname, email, pk, LocalDateTime.now());
        try {
            manager.addStudent(student);
            refreshStudentTable();
            showMessage("Students tika veiksmīgi reģistrēts.", "Veiksme");
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }

    private void handleRemove() {
        String pk = JOptionPane.showInputDialog(window, "Ievadiet personas kodu, kuru dzēst:", "Dzēst studentu", JOptionPane.QUESTION_MESSAGE);
        if (pk == null || pk.trim().isEmpty()) {
            return;
        }
        pk = pk.trim();
        if (!manager.removeStudent(pk)) {
            showError("Students ar šādu personas kodu netika atrasts.");
            return;
        }
        refreshStudentTable();
        showMessage("Students ar personas kodu " + pk + " tika dzēsts.", "Veiksme");
    }

    private void handleEdit() {
        String pk = JOptionPane.showInputDialog(window, "Ievadiet personas kodu rediģēšanai:", "Rediģēt studentu", JOptionPane.QUESTION_MESSAGE);
        if (pk == null || pk.trim().isEmpty()) {
            return;
        }
        pk = pk.trim();

        Student student = manager.getStudentByPk(pk);
        if (student == null) {
            showError("Students ar šādu personas kodu netika atrasts.");
            return;
        }

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        JTextField nameField = new JTextField(student.getName());
        JTextField surnameField = new JTextField(student.getSurname());
        JTextField emailField = new JTextField(student.getEmail());

        form.add(new JLabel("Vārds:"));
        form.add(nameField);
        form.add(new JLabel("Uzvārds:"));
        form.add(surnameField);
        form.add(new JLabel("E-pasts:"));
        form.add(emailField);

        int result = JOptionPane.showConfirmDialog(window, form, "Rediģēt studentu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String email = emailField.getText().trim();

        if (!InputValidator.isValidName(name) || !InputValidator.isValidName(surname)) {
            showError("Vārds un uzvārds jābūt vismaz 3 burtiem.");
            return;
        }
        if (!InputValidator.isValidEmail(email)) {
            showError("Lūdzu ievadiet derīgu e-pasta adresi.");
            return;
        }

        student.setName(name);
        student.setSurname(surname);
        student.setEmail(email);
        manager.saveChanges();
        refreshStudentTable();
        showMessage("Studentu dati ir atjaunināti.", "Veiksme");
    }

    private void refreshStudentTable() {
        List<Student> students = manager.getStudents();
        tableModel.setRowCount(0);
        for (Student student : students) {
            tableModel.addRow(new Object[]{
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getPersonalCode(),
                student.getFormattedDate()
            });
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(window, message, "Kļūda", JOptionPane.ERROR_MESSAGE);
    }

    private void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(window, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
