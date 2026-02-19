package rvt;

import java.util.List;

public class StudentManager {
    private List<Student> students;

    public StudentManager() {
        this.students = FileHandler.loadStudents();
    }

    public void addStudent(Student student) throws IllegalArgumentException {
        if (isEmailTaken(student.getEmail())) {
            throw new IllegalArgumentException("Kļūda: Šāds e-pasts jau ir reģistrēts!");
        }
        if (isPkTaken(student.getPersonalCode())) {
            throw new IllegalArgumentException("Kļūda: Šāds personas kods jau eksistē!");
        }
        students.add(student);
        saveChanges();
    }

    public boolean removeStudent(String personalCode) {
        boolean removed = students.removeIf(s -> s.getPersonalCode().equals(personalCode));
        if (removed) saveChanges();
        return removed;
    }

    public Student getStudentByPk(String personalCode) {
        return students.stream()
            .filter(s -> s.getPersonalCode().equals(personalCode))
            .findFirst()
            .orElse(null);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void saveChanges() {
        FileHandler.saveStudents(students);
    }

    private boolean isEmailTaken(String email) {
        return students.stream().anyMatch(s -> s.getEmail().equalsIgnoreCase(email));
    }

    private boolean isPkTaken(String pk) {
        return students.stream().anyMatch(s -> s.getPersonalCode().equals(pk));
    }
}