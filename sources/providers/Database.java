package providers;


import java.sql.*;

public class Database {
    private static Database instance;

    private Logger logger;
    private Connection connection;

    private Database(Logger logger) {
        this.logger = logger;
    }

    public static Database getInstance() {
        if (Database.instance == null) {
            try {
                Database.instance = new Database(new Logger(Database.class.getName()));

                Database.instance.connection = DriverManager.getConnection(
                        Dependency.getInstance().getDatabaseURL(),
                        Dependency.getInstance().getDatabaseUsername(),
                        Dependency.getInstance().getDatabasePassword());

            }
            catch (Exception e) {
                Database.instance.logger.error("Failed to initialize Database instance: " + e.getMessage());

                throw new RuntimeException("Failed to initialize Database instance");
            }
        }

        Database.instance.logger.debug("Get Instance");

        return Database.instance;
    }

    public void close() {
        this.logger.debug("Close");

        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        }
        catch (Exception e) {
            this.logger.error("Failed to close database connection: " + e.getMessage());

            throw new RuntimeException("Failed to close database connection");
        }
    }

    public void disableForeignKey() {
        this.logger.debug("Disable Foreign Key");

        try {
            this.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
        }
        catch (Exception e) {
            this.logger.error("Failed to disable foreign key checks: " + e.getMessage());

            throw new RuntimeException("Failed to disable foreign key checks");
        }
    }

    @SuppressWarnings("unused")
    private boolean tableExist(String table) {
        this.logger.debug("Table Exist");

        try {
            return this.connection.getMetaData().getTables(null, null, table, null).next();
        }
        catch (Exception e) {
            this.logger.error("Failed to check if table exist: " + e.getMessage());
        }

        return false;
    }

    public int tableTotal(String table) {
        this.logger.debug("Table Total");

        try {
            final ResultSet result = this.executeQuery("SELECT COUNT(*) AS `total` FROM " + table + ";");
            result.next();

            return result.getInt("total");
        }
        catch (Exception e) {
            this.logger.error("Failed to get table total: " + e.getMessage());
        }

        return -1;
    }

    public int tableTotal(String table, String where) {
        this.logger.debug("Table Total");

        try {
            final ResultSet result = this.executeQuery("SELECT COUNT(*) AS `total` FROM " + table + " WHERE " + where + ";");
            result.next();

            return result.getInt("total");
        }
        catch (Exception e) {
            this.logger.error("Failed to get table total: " + e.getMessage());
        }

        return -1;
    }

    public ResultSet executeQuery(String query) {
        this.logger.debug("Execute Query");

        try {
            return this.connection.prepareStatement(query).executeQuery();
        }
        catch (Exception e) {
            this.logger.error("Failed to execute query: " + e.getMessage());
        }

        return null;
    }

    public void executeUpdate(String query) {
        this.logger.debug("Execute Update");

        try {
            this.connection.prepareStatement(query).executeUpdate();
        }
        catch (Exception e) {
            this.logger.error("Failed to execute update: " + e.getMessage());
        }
    }
}
