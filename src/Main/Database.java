package Main;

import repositories.ActorsRepo;
import repositories.DirectorsRepo;
import repositories.GenresRepo;
import repositories.MoviesRepo;

import java.sql.*;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mano
 */
public class Database {
    private static String url = "jdbc:mysql://localhost:3306/ProiectPA?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "12345678";
    private static Connection connection = null;
    public static boolean Singleton=false;
    public Database() {
        Singleton=true;
    }
    public static Connection getConnection() {
        if(connection==null)
            createConnection();
        return connection;
    }
    static void createConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.err.println("Cannot connect to DB: " + e);
        }
    }
    static void closeConnection() throws SQLException {
        connection.close();
    }
    public static void commit() throws SQLException {
        connection.commit();
    }
    public static void rollback() throws  SQLException {
        connection.rollback();
    }
}


