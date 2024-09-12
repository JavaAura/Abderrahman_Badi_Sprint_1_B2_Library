package src.dao.interfaces;

import src.business.Document;
import src.business.Reservation;
import src.business.User;

public interface ReservationDAO extends DAO<Reservation>{

    void saveReservation(Document document, User user);

    void cancelReservation(long id);

    void borrowDocument(long id);

    void returnDocument(long id);

}
