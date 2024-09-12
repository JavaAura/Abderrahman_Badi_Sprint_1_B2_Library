package src.business.interfaces;

import src.business.Document;
import src.business.User;

public interface Reservable {
    void reserveDocument(Document document, User user);

    void cancelReservation(long id);
}
