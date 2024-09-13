package src.services.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import src.business.Document;
import src.business.Reservation;
import src.business.User;
import src.dao.interfaces.ReservationDAO;

public class ReservationDAOImpl implements ReservationDAO {
    private List<Reservation> reservations = new ArrayList<>();

    @Override
    public Optional<Reservation> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Reservation> getAll() {

        return reservations;
    }

    @Override
    public void saveReservation(Document document, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelReservation'");
    }

    @Override
    public void cancelReservation(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelReservation'");
    }

    @Override
    public void borrowDocument(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrowDocument'");
    }

    @Override
    public void returnDocument(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'returnDocument'");
    }

    /* ----------------- UNSEUPPORTED METHODS ----------------- */

    @Override
    public void save(Reservation reservation) {
        throw new UnsupportedOperationException("Save operation is not supported in ReservationDAOImpl.");
    }

    @Override
    public void update(Reservation reservation, String[] params) {
        throw new UnsupportedOperationException("Update operation is not supported in ReservationDAOImpl.");
    }

    @Override
    public void delete(Reservation reservation) {
        throw new UnsupportedOperationException("Delete operation is not supported in ReservationDAOImpl.");
    }
}
