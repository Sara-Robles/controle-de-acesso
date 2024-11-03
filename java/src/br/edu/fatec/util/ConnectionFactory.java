package br.edu.fatec.util;

import java.sql.*;

public class ConnectionFactory {

    public static Connection getConnection() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost/cadastroaluno";
            String username = "root";
            String password = "";

            return DriverManager.getConnection(url, username, password);

        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {
        close(conn, stmt, rs);
    }

    public static void closeConnection(Connection conn, Statement stmt) throws Exception {
        close(conn, stmt, null);
    }

    public static void closeConnection(Connection conn) throws Exception {
        close(conn, null, null);
    }

    private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            Connection conn = ConnectionFactory.getConnection();
            System.out.println("Conexão bem sucedida.");
        } catch (Exception e1) {
            // TODO: handle exception
            System.out.println("Conexão falhou.");
            throw new Exception(e1.getMessage());

        }
    }
}

