package src.business;

import java.time.LocalDate;

import src.business.interfaces.Borrowable;
import src.business.interfaces.Reservable;

abstract public class Document implements Borrowable, Reservable {
    private long id;
    private String title;
    private String author;
    private LocalDate publicationDate;
    private int pageNumbers;
    private boolean isBorrowed = false;

    public Document() {
    }

    public Document(long id, String title, String author, LocalDate publicationDate, int pageNumbers) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.pageNumbers = pageNumbers;
    }

    public abstract void showDetails();
    

    // --------------- Getters / Setters -----------------------

    public long getId() {
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
