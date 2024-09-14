package src.business.interfaces;

import src.business.Reservation;

public interface Borrowable {
    void borrowDocument(Reservation reservation);

    void returnDocument(Reservation reservation);
}
