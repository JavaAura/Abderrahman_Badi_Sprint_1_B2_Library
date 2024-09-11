package src.business;

import java.time.LocalDate;

public class UniversityThesis extends Document {
    private String field;

    public UniversityThesis() {

    }

    public UniversityThesis(String name, String author, LocalDate publicationDate, int pageNumbers, String field) {
        super(name, author, publicationDate, pageNumbers);
        this.field = field;
    }

    @Override
    public void showDetails() {
        System.out.println("\n Book Details:");
        System.out.println("\n\t ID: " + getId());
        System.out.println("\n\t Title: " + getTitle());
        System.out.println("\n\t Author: " + getAuthor());
        System.out.println("\n\t Publication Date: " + getPublicationDate());
        System.out.println("\n\t Number of Pages: " + getPageNumbers());
        System.out.println("\n\t Book Number: " + this.field);
    }

    public String getField() {
        return this.field;
    }

    public void setField(String value) {
        this.field = value;
    }
}
