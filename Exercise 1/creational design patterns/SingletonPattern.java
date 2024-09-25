// Singleton Class (Database Connection)
class DatabaseConnection {
    // Static instance and lock object
    private static DatabaseConnection instance = null;
    private static final Object lock = new Object();

    // Private constructor to prevent instantiation
    private DatabaseConnection() {
        System.out.println("Establishing Database Connection...");
    }

    // Static method to provide the single instance
    public static DatabaseConnection getInstance() {
        synchronized (lock) {
            if (instance == null) {
                instance = new DatabaseConnection();
            }
        }
        return instance;
    }

    // Method to execute a query
    public void query(String sql) {
        System.out.println("Executing query: " + sql);
    }
}

// Test the Singleton Pattern
public class SingletonPattern{
    public static void main(String[] args) {
        DatabaseConnection connection1 = DatabaseConnection.getInstance();
        connection1.query("SELECT * FROM Users");

        DatabaseConnection connection2 = DatabaseConnection.getInstance();
        connection2.query("SELECT * FROM Orders");

        // Checking if both connections are the same instance
        System.out.println(connection1 == connection2 ? "Same instance" : "Different instances");
    }
}
