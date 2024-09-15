package src.services.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.business.Document;
import src.business.Reservation;
import src.business.User;
import src.dao.interfaces.ReservationDAO;
import src.db.DatabaseConnection;
import src.enums.Status;

public class ReservationDAOImpl implements ReservationDAO {
    private static final String SQL_FIND_BY_ID = "SELECT * FROM public.reservation WHERE id = ?";
    private static final String SQL_LIST = "SELECT * FROM public.reservation WHERE isBorrowed = false AND return_date = NULL ORDER BY id DESC";
    private static final String SQL_RESERVE_DOCUMENT = "INSERT INTO public.reservation(reservation_date,document_id, user_id) VALUES (?, ?, ?)";
    private static final String SQL_CANCEL_RESERVATION = "UPDATE public.reservation SET reservation_status = 'Canceled' WHERE id = ?";
    private static final String SQL_BORROW_DOCUMENT = "UPDATE public.reservation SET is_borrowed = true WHERE id = ?";
    private static final String SQL_RETURN_DOCUMENT = "UPDATE public.reservation SET is_borrowed = false, reservation_status = 'Canceled', return_date = ? WHERE id = ?";
    private static final String SQL_IS_RESERVED = "SELECT EXISTS (SELECT 1 FROM public.reservation WHERE reservation_status = 'Active' AND document_id = ?)";

    @Override
    public Optional<Reservation> get(long id) {
        Reservation reservation = null;

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                reservation = new Reservation(
                        resultSet.getLong("id"),
                        resultSet.getDate("reservation_date").toLocalDate(),
                        Status.valueOf(resultSet.getString("reservation_status")),
                        resultSet.getBoolean("is_borrowed"),
                        resultSet.getDate("return_date") != null ? resultSet.getDate("return_date").toLocalDate()
                                : null,
                        resultSet.getInt("document_id"),
                        resultSet.getInt("user_id"));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(reservation);
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_LIST);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                LocalDate reservationDate = resultSet.getDate("reservation_date").toLocalDate();
                Status reservationStatus = Status.valueOf(resultSet.getString("reservation_status"));
                Boolean isBorrowed = resultSet.getBoolean("is_borrowed");
                LocalDate returnDate = resultSet.getDate("return_date") != null
                        ? resultSet.getDate("return_date").toLocalDate()
                        : null;
                int documentId = resultSet.getInt("document_id");
                int userId = resultSet.getInt("user_id");

                Reservation reservation = new Reservation(id, reservationDate, reservationStatus, isBorrowed,
                        returnDate,
                        documentId, userId);

                reservations.add(reservation);

                connection.close();

            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
        return reservations;
    }

    @Override
    public void saveReservation(Document document, User user) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_RESERVE_DOCUMENT);
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setLong(2, document.getId());
            statement.setLong(3, user.getId());

            statement.executeUpdate();
            connection.close();

            System.out.println("Magazine added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelReservation(Reservation reservation) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_CANCEL_RESERVATION);

            statement.setLong(1, reservation.getId());

            statement.executeUpdate();
            connection.close();

            System.out.println("Reservation has been canceled successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void borrowDocument(Reservation reservation) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_BORROW_DOCUMENT);

            statement.setLong(1, reservation.getId());

            statement.executeUpdate();
            connection.close();

            System.out.println("Reservation has been canceled successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnDocument(Reservation reservation) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_RETURN_DOCUMENT);

            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setLong(2, reservation.getId());

            statement.executeUpdate();
            connection.close();

            System.out.println("Reservation has been canceled successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isReserved(Document document) {
        boolean isReserved = false;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_IS_RESERVED);
            statement.setLong(1, document.getId());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isReserved = resultSet.getBoolean(1);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isReserved;
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
