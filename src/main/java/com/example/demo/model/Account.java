package com.example.demo.model;

import java.sql.*;
import java.util.UUID;

public class Account {

    private String accountHolder;
    private double balance;
    String url = "jdbc:sqlserver://DESKTOP-AR58F5R;databaseName=Bank;user=d;password=d;encrypt=true;trustServerCertificate=true;";
    Connection connection = DriverManager.getConnection(url);


    public Account() throws SQLException {

    }


    public Account(String accountHolder, double initialBalance) throws SQLException {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " deposited. New balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(amount + " withdrawn. New balance: " + balance);
        } else {
            System.out.println("Withdrawal amount must be positive and less than or equal to balance.");
        }
    }

    public void transferTo(Account targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            targetAccount.deposit(amount);
            System.out.println(amount + " transferred from " + this.accountHolder + " to " + targetAccount.getAccountHolder());
        } else {
            System.out.println("Transfer amount must be positive and less than or equal to balance.");
        }
    }


    public void saveToDb(Account account) throws SQLException {


        String sql = "INSERT INTO Accounts (AccountId, AccountName, Balance) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the values for the placeholders
            statement.setObject(1, UUID.randomUUID()); // Generate a new UUID for AccountId
            statement.setString(2, account.getAccountHolder()); // Set the account holder name
            statement.setDouble(3, account.getBalance()); // Set the account balance
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }


    public float findInfoById(String id) throws SQLException {
        float amount  =  0;
        String sql = "SELECT * FROM Accounts where AccountId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            {
                // Process the ResultSet
                while (resultSet.next()) {
                    // Retrieve data from the ResultSet
                     amount = resultSet.getFloat("Balance");

                    // Process retrieved data
                    System.out.println("Amount: " + amount);

                }



                return amount;
            }


        }


    }
}