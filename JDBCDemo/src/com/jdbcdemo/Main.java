package com.jdbcdemo;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        String user = sc.nextLine();
        user = user.equals("") ? "root" : user;
        System.out.println();

        System.out.print("Enter password default (empty):");
        String password = sc.nextLine().trim();
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/soft_uni", props);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT * FROM employees WHERE salary > ?"); // ? е параметър, като %d %s

        //String salary = sc.nextLine();
        //  stmt.setDouble(1, Double.parseDouble(salary)); // на първият ? set Double
       // ResultSet rs = stmt.executeQuery(); // отива до базата и извиква тази заявка

// Second example
        String name = sc.nextLine();
        PreparedStatement stmt2 =
                connection.prepareStatement("SELECT first_name, department_id FROM employees WHERE first_name  = ?");

        stmt2.setString(1, name);
        ResultSet resultSet = stmt2.executeQuery();


        while (resultSet.next()) {
            System.out.println(resultSet.getString("first_name") + " " + resultSet.getInt("department_id"));
        }
        connection.close();
    }
}
