package utilities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DatabaseUtils {
    private static final Logger logger = Logger.getLogger(DatabaseUtils.class.getName());
    private static Connection connection;

    public static Connection connectToDatabase(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            logger.info("Connected to the database successfully.");
        } catch (SQLException e) {
            logger.severe("Failed to connect to the database: " + e.getMessage());
        }
        return connection;
    }

    public static ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            logger.info("Query executed successfully.");
        } catch (SQLException e) {
            logger.severe("Query execution failed: " + e.getMessage());
        }
        return resultSet;
    }

    public static int executeUpdate(String query) {
        int result = 0;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query);
            logger.info("Update executed successfully.");
        } catch (SQLException e) {
            logger.severe("Update execution failed: " + e.getMessage());
        }
        return result;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                logger.info("Database connection closed.");
            }
        } catch (SQLException e) {
            logger.severe("Failed to close the database connection: " + e.getMessage());
        }
    }

    public static void beginTransaction() {
        try {
            connection.setAutoCommit(false);
            logger.info("Transaction started.");
        } catch (SQLException e) {
            logger.severe("Failed to start transaction: " + e.getMessage());
        }
    }

    public static void commitTransaction() {
        try {
            connection.commit();
            logger.info("Transaction committed.");
        } catch (SQLException e) {
            logger.severe("Failed to commit transaction: " + e.getMessage());
        }
    }

    public static void rollbackTransaction() {
        try {
            connection.rollback();
            logger.info("Transaction rolled back.");
        } catch (SQLException e) {
            logger.severe("Failed to rollback transaction: " + e.getMessage());
        }
    }

    public static PreparedStatement prepareStatement(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            logger.info("Prepared statement created.");
        } catch (SQLException e) {
            logger.severe("Failed to create prepared statement: " + e.getMessage());
        }
        return preparedStatement;
    }

    public static void executeBatchUpdate(String[] queries) {
        try {
            Statement statement = connection.createStatement();
            for (String query : queries) {
                statement.addBatch(query);
            }
            statement.executeBatch();
            logger.info("Batch update executed successfully.");
        } catch (SQLException e) {
            logger.severe("Batch update execution failed: " + e.getMessage());
        }
    }

    public static void executeStoredProcedure(String procedureName) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(procedureName);
            logger.info("Stored procedure executed successfully.");
        } catch (SQLException e) {
            logger.severe("Stored procedure execution failed: " + e.getMessage());
        }
    }

    public static DatabaseMetaData getDatabaseMetaData() {
        DatabaseMetaData metaData = null;
        try {
            metaData = connection.getMetaData();
            logger.info("Retrieved database metadata successfully.");
        } catch (SQLException e) {
            logger.severe("Failed to retrieve database metadata: " + e.getMessage());
        }
        return metaData;
    }
}