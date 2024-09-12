package src.dao.interfaces;

import src.business.Reservation;

public interface ReservationDAO extends DAO<Reservation>{

    void cancelReservation(long id);

    void borrowDocument(long id);

    void returnDocument(long id);

}
