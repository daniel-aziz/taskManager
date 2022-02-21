package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {
    private static ConnectionPool instance = null;
    private static final int NUM_OF_CONNECTIONS = 10;
    private Stack<Connection> connections = new Stack<>();

    private ConnectionPool() throws SQLException {
        openAllConnections();
    }

    public void openAllConnections() throws SQLException {
        for (int index = 0; index < NUM_OF_CONNECTIONS; index += 1) {
            Connection connection = DriverManager.getConnection(DataBaseManager.URL, DataBaseManager.USER_NAME, DataBaseManager.PASSWORD);
            connections.push(connection);
        }
    }

    public static ConnectionPool getInstance() throws SQLException {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        synchronized (connections) {
            if (connections.isEmpty()) {
                connections.wait();
            }
            return connections.pop();
        }
    }

    public void returnConnection(Connection connection) {
        synchronized (connections) {
            connections.push(connection);
            connections.notify();
        }


    }

    public void closeAllConnections() throws InterruptedException {
        synchronized (connections) {
            while (connections.size() < NUM_OF_CONNECTIONS) {
                connections.wait();
            }
            connections.removeAllElements();
        }
    }

}
