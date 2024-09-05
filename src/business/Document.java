package src.business;

import java.time.LocalDate;

abstract public class Document {
    private static int nextId = 1;
    private int id;
    private String title;
    private String author;
    private LocalDate publicationDate;
    private int pageNumbers;
    private boolean isBorrowed = false;

    public Document() {
        this.id = nextId++;
    }

    public Document(String title, String author, LocalDate publicationDate, int pageNumbers) {
        this.id = nextId++;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.pageNumbers = pageNumbers;
    }

    public abstract void borrowDoc();

    public abstract void returnDoc();

    public abstract void showDetails();

    // --------------- Getters / Setters -----------------------

    public int getId() {
        return this.id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String value) {
        this.author = value;
    }

    public LocalDate getPublicationDate() {
        return this.publicationDate;
    }

    public void setPublicationDate(LocalDate value) {
        this.publicationDate = value;
    }

    public int getPageNumbers() {
        return this.pageNumbers;
    }

    public void setPageNumbers(int value) {
        this.pageNumbers = value;
    }

    public boolean getIsBorrowed() {
        return this.isBorrowed;
    }

    public void setIsBorrowed(boolean value) {
        this.isBorrowed = value;
    }
}
