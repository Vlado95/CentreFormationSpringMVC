package com.cefisi.modeles;

import java.sql.*;

public class Database {

    protected static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    protected static final String URL = "jdbc:mysql://localhost/centre_formation";
    protected static final String USER = "cefisi";
    protected static final String PASSWORD = "MyPassword!";

    public enum SortOrder {
        ASC, DESC;
    }

    static {
        // Chargement du pilote
        // Ne doit avoir lieu qu'une seule fois
        try {
            Class.forName(DRIVER_NAME).newInstance();
            System.out.println("*** Driver loaded.");
        } catch (ClassNotFoundException e) {
            System.err.println("*** ERROR: Driver " + DRIVER_NAME + " not found");
        } catch (InstantiationException e) {
            System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
            System.err.println(e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println("*** ERROR: Impossible to create an instance of " + DRIVER_NAME);
            System.err.println(e.getMessage());
        }
    }

    /**
     * Fournit une connexion à la base de données. Ne fait pas appel à un pool
     * de connexion, mâme si cela est envisageable plus tard (ne changerait rien
     * à l'appel de la méthode)
     *
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
