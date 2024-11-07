package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    public void addClient(Client client) {
        String sql = "INSERT INTO Clients (Client_Name, Address, Contact_Info, Membership_Details, Assigned_Trainer_ID) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, client.getClientName());
            preparedStatement.setString(2, client.getAddress());
            preparedStatement.setString(3, client.getContactInfo());
            preparedStatement.setString(4, client.getMembershipDetails());
            preparedStatement.setInt(5, client.getAssignedTrainer().getTrainerId());
            preparedStatement.executeUpdate();
            System.out.println("Client added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Clients";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Client client = new Client.ClientBuilder()
                        .setClientId(resultSet.getInt("Client_ID"))
                        .setClientName(resultSet.getString("Client_Name"))
                        .setAddress(resultSet.getString("Address"))
                        .setContactInfo(resultSet.getString("Contact_Info"))
                        .setMembershipDetails(resultSet.getString("Membership_Details"))
                        // Припустимо, що Assigned_Trainer_ID вже є в базі даних
                        .setAssignedTrainer(new Trainer(resultSet.getInt("Assigned_Trainer_ID"), null, null, null, null, null, null))
                        .build();
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public void updateClient(Client client) {
        String sql = "UPDATE Clients SET Client_Name = ?, Address = ?, Contact_Info = ?, Membership_Details = ?, Assigned_Trainer_ID = ? WHERE Client_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, client.getClientName());
            preparedStatement.setString(2, client.getAddress());
            preparedStatement.setString(3, client.getContactInfo());
            preparedStatement.setString(4, client.getMembershipDetails());
            preparedStatement.setInt(5, client.getAssignedTrainer().getTrainerId());
            preparedStatement.setInt(6, client.getClientId());
            preparedStatement.executeUpdate();
            System.out.println("Client updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(int clientId) {
        String sql = "DELETE FROM Clients WHERE Client_ID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, clientId);
            preparedStatement.executeUpdate();
            System.out.println("Client deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
