package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true) // To ignore any unexpected JSON properties
public class Client implements Comparable<Client> {
    private int clientId;
    private String clientName;
    private String address;
    private String contactInfo;
    private String membershipDetails;
    private Trainer assignedTrainer;

    // Default constructor for Jackson
    public Client() {
    }

    // Constructor using builder
    private Client(ClientBuilder builder) {
        this.clientId = builder.clientId;
        this.clientName = builder.clientName;
        this.address = builder.address;
        this.contactInfo = builder.contactInfo;
        this.membershipDetails = builder.membershipDetails;
        this.assignedTrainer = builder.assignedTrainer;
    }

    // Getters
    public int getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getAddress() {
        return address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getMembershipDetails() {
        return membershipDetails;
    }

    public Trainer getAssignedTrainer() {
        return assignedTrainer;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", address='" + address + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", membershipDetails='" + membershipDetails + '\'' +
                ", assignedTrainer=" + assignedTrainer +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Client client = (Client) obj;
        return clientId == client.clientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId);
    }

    /**
     * Порівняння клієнтів за ім'ям.
     */
    @Override
    public int compareTo(Client other) {
        return this.clientName.compareTo(other.clientName);
    }

    // Builder class
    public static class ClientBuilder {
        private int clientId;
        private String clientName;
        private String address;
        private String contactInfo;
        private String membershipDetails;
        private Trainer assignedTrainer;

        public ClientBuilder setClientId(int clientId) {
            if (clientId <= 0) {
                throw new IllegalArgumentException("Client ID must be greater than 0");
            }
            this.clientId = clientId;
            return this;
        }

        public ClientBuilder setClientName(String clientName) {
            if (clientName == null || clientName.trim().isEmpty()) {
                throw new IllegalArgumentException("Client name cannot be null or empty");
            }
            this.clientName = clientName;
            return this;
        }

        public ClientBuilder setAddress(String address) {
            if (address == null || address.trim().isEmpty()) {
                throw new IllegalArgumentException("Address cannot be null or empty");
            }
            this.address = address;
            return this;
        }

        public ClientBuilder setContactInfo(String contactInfo) {
            if (contactInfo == null || contactInfo.trim().isEmpty()) {
                throw new IllegalArgumentException("Contact info cannot be null or empty");
            }
            this.contactInfo = contactInfo;
            return this;
        }

        public ClientBuilder setMembershipDetails(String membershipDetails) {
            if (membershipDetails == null || membershipDetails.trim().isEmpty()) {
                throw new IllegalArgumentException("Membership details cannot be null or empty");
            }
            this.membershipDetails = membershipDetails;
            return this;
        }

        public ClientBuilder setAssignedTrainer(Trainer assignedTrainer) {
            if (assignedTrainer == null) {
                throw new IllegalArgumentException("Assigned trainer cannot be null");
            }
            this.assignedTrainer = assignedTrainer;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
