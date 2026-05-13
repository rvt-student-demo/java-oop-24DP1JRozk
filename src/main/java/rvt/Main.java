package rvt;

class Book1 {
    String name;
    String author;
    int year;

    public void registerBook(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public void callBook() {
        System.out.println("| " + this.name + " | " + this.author + " | " + this.year + " |");
    }
}

class Book2 extends Book1 {
    
    Book2(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }
}

public class Main {
    public static void main(String[] args) {
        Book1 joelBook = new Book1();
        joelBook.registerBook("Lord of the rings", "L.K.Arghu", 1939);

        joelBook.callBook();


        Book2 argutBook = new Book2("Harry Potter", "J.K.Rowlin", 1689);

        argutBook.callBook();
    }
}