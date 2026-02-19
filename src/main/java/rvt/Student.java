package rvt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Student {
    private String name;
    private String surname;
    private String email;
    private String personalCode;
    private LocalDateTime registrationTime;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Student(String name, String surname, String email, String personalCode, LocalDateTime registrationTime) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.personalCode = personalCode;
        this.registrationTime = registrationTime;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPersonalCode() { return personalCode; }
    public void setPersonalCode(String personalCode) { this.personalCode = personalCode; }
    public LocalDateTime getRegistrationTime() { return registrationTime; }
    public String getFormattedDate() { return registrationTime.format(formatter); }

    // Datu sagatavošana CSV formātam
    public String toCSV() {
        return name + "," + surname + "," + email + "," + personalCode + "," + getFormattedDate();
    }

    // Objekta izveide no CSV rindas
    public static Student fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length == 5) {
            return new Student(parts[0], parts[1], parts[2], parts[3], LocalDateTime.parse(parts[4], formatter));
        }
        return null;
    }
}