import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    // Singleton instance
    private static DatabaseConnection instance;
    private Connection connection;

    private static String url = System.getenv("DB_URL");
    private static String user = System.getenv("DB_USER");
    private static String password = System.getenv("DB_PASSWORD");

    private DatabaseConnection() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connection established successfully.");

        } catch (Exception e) {
            System.out.println("PostgreSQL JDBC Driver not found: " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        try {
            if (instance == null) {

                instance = new DatabaseConnection();

            } else if (instance.getConnection().isClosed()) {
                instance = new DatabaseConnection();
            }

        } catch (

        Exception e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        DatabaseConnection cn = DatabaseConnection.getInstance();
    }
}