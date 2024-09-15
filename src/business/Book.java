package src.business;

import java.time.LocalDate;

    public class Book extends Document {

        private int number;
        

        public Book() {
        }

        public Book(long id, String name, String author, LocalDate publicationDate, int pageNumbers) {
            super(id, name, author, publicationDate, pageNumbers);
        }

        public Book(String name, String author, LocalDate publicationDate, int pageNumbers, int number) {
            super(name, author, publicationDate, pageNumbers);
            this.number = number;
        }

        public Book(long id, String name, String author, LocalDate publicationDate, int pageNumbers, int number) {
            super(id, name, author, publicationDate, pageNumbers);
            this.number = number;
        }

        @Override
        public void showDetails() {
            System.out.println("\n Book Details:");
            System.out.println("\n\t ID: " + getId());
            System.out.println("\n\t Title: " + getTitle());
            System.out.println("\n\t Author: " + getAuthor());
            System.out.println("\n\t Publication Date: " + getPublicationDate());
            System.out.println("\n\t Number of Pages: " + getPageNumbers());
            System.out.println("\n\t Book Number: " + this.number);
        }

    // --------------- Getters / Setters -----------------------

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int value) {
        this.number = value;
    }

}
