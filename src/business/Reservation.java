package src.business;

import java.time.LocalDate;

import src.enums.Status;

public class Reservation {
    private long id;
    private LocalDate reservationDate;
    private Status reservationStatus;
    private Boolean isBorrowed;
    private LocalDate returnDate;
    private User user;
    private Document document;
    private long documentId;
    private long userId;

    public Reservation(long id, LocalDate reservationDate, Status reservationStatus, boolean isBorrowed,
            LocalDate returnDate, User user, Document document) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.reservationStatus = reservationStatus;
        this.document = document;
        this.user = user;
        this.isBorrowed = isBorrowed;
        this.returnDate = returnDate;
    }

    public Reservation(boolean isBorrowed, LocalDate returnDate, long documentId, long userId) {
        this.documentId = documentId;
        this.userId = userId;
        this.isBorrowed = isBorrowed;
        this.returnDate = returnDate;
    }

    public void showReservationDetails() {
        System.out.println("\n Reservation Details:");
        System.out.println("\n\t ID: " + getId());
        System.out.println("\n\t Document ID: " + document.getId());
        System.out.println("\n\t Book title: " + document.getTitle());
        System.out.println("\n\t Reservor: " + user.getName() + " " + user.getLastName());
        System.out.println("\n\t User registration number: " + user.getRegistrationNumber());
        System.out.println("\n\t Reservation date: " + getReservationDate());
    }

    public LocalDate getReservationDate() {
        return this.reservationDate;
    }

    public void setReservationDate(LocalDate value) {
        this.reservationDate = value;
    }

    public Status getReservationStatus() {
        return this.reservationStatus;
    }

    public void setReservationStatus(Status value) {
        this.reservationStatus = value;
    }

    public Boolean getIsBorrowed() {
        return this.isBorrowed;
    }

    public void setIsBorrowed(Boolean value) {
        this.isBorrowed = value;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(LocalDate value) {
        this.returnDate = value;
    }

    public long getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(long value) {
        this.documentId = value;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long value) {
        this.userId = value;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User value) {
        this.user = value;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document value) {
        this.document = value;
    }
}
