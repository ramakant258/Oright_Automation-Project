package Databasetesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class databaseconnection {

    public static void main(String[] args) {
        // Database connection details
        String host = "localhost";
        String port = "3306";
        String database = "qatesting"; // Ensure the database exists
        String user = "root"; // Ensure the user has privileges
        String password = "123456"; // Ensure the password is correct

        // Connection string
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

        // Using try-with-resources to automatically close resources
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM EmployeeInfo WHERE FirstName = 'Emily'")) {

            // Process the result set
            while (rs.next()) {
                // Retrieve data from the result set
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");

                // Print the retrieved data
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
            }

        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        }
    }
    
}
