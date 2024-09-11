package src.business;

import java.time.LocalDate;

public class Magazine extends Document {

    private int isbn;

    public Magazine() {
    }

    public Magazine(int id, String name, String author, LocalDate publicationDate, int pageNumbers, int isbn) {
        super(id ,name, author, publicationDate, pageNumbers);
        this.isbn = isbn;
    }

    @Override
    public void showDetails() {
        System.out.println("\n Magazine Details:");
        System.out.println("\n\t ID: " + getId());
        System.out.println("\n\t Title: " + getTitle());
        System.out.println("\n\t Author: " + getAuthor());
        System.out.println("\n\t Publication Date: " + getPublicationDate());
        System.out.println("\n\t Number of Pages: " + getPageNumbers());
        System.out.println("\n\t ISBN: " + this.isbn);

    }

    // --------------- Getters / Setters -----------------------

    public int getIsbn() {
        return this.isbn;
    }

    public void setIsbn(int value) {
        this.isbn = value;
    }
}
