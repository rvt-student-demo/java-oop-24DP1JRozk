package rvt;

public class Cars {
    
    public String name;
    public String model;
    public int year;


    Cars(String name, String model, int year) {
        this.name = name;
        this.model = model;
        this.year = year;
    }

    public void printInfo() {
        System.out.println("Car name: " + name);
        System.out.println("Car model: " + model);
        System.out.println("Car year: " + year);
    }
}