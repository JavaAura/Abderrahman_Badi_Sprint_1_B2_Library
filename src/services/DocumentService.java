package src.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.business.Document;
import src.business.Reservation;
import src.business.User;
import src.business.interfaces.Borrowable;
import src.business.interfaces.Reservable;
import src.dao.interfaces.ReservationDAO;

public class DocumentService implements Borrowable, Reservable {
    private ReservationDAO reservationDAO;

     public DocumentService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    @Override
    public void reserveDocument(Document document, User user) {
        reservationDAO.saveReservation(document, user);
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        reservationDAO.cancelReservation(reservation);
    }

    @Override
    public void borrowDocument(Reservation reservation) {
        reservationDAO.borrowDocument(reservation);
    }

    @Override
    public void returnDocument(Reservation reservation) {
        reservationDAO.returnDocument(reservation);
    }
    
}
