package src.dao.interfaces;

import src.business.Document;
import src.business.Reservation;
import src.business.User;

public interface ReservationDAO extends DAO<Reservation>{

    void saveReservation(Document document, User user);

    void cancelReservation(Reservation reservation);

    void borrowDocument(Reservation reservation);

    void returnDocument(Reservation reservation);

}
