package org.example;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void createTables() {
        String createClientsTable = "CREATE TABLE IF NOT EXISTS Clients (" +
                "Client_ID INT PRIMARY KEY AUTO_INCREMENT, " +
                "Client_Name VARCHAR(255), " +
                "Address VARCHAR(255), " +
                "Contact_Info VARCHAR(255), " +
                "Membership_Details VARCHAR(255), " +
                "Assigned_Trainer_ID INT)";

        String createTrainersTable = "CREATE TABLE IF NOT EXISTS Trainers (" +
                "Trainer_ID INT PRIMARY KEY AUTO_INCREMENT, " +
                "Trainer_Name VARCHAR(255), " +
                "Address VARCHAR(255), " +
                "Contact_Info VARCHAR(255), " +
                "Training_Schedule VARCHAR(255), " +
                "Specialization VARCHAR(255))";

        String createGymsTable = "CREATE TABLE IF NOT EXISTS Gyms (" +
                "Gym_ID INT PRIMARY KEY AUTO_INCREMENT, " +
                "Address VARCHAR(255), " +
                "Schedule VARCHAR(255))";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createClientsTable);
            statement.execute(createTrainersTable);
            statement.execute(createGymsTable);
            System.out.println("Tables created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
