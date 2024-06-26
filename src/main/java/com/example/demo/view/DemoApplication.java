package com.example.demo.view;

import com.example.demo.model.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws SQLException {

   SpringApplication.run(DemoApplication.class, args);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Connected to database successfully!");

              // First name input
        System.out.print("Enter 1st Name: ");
        String name = scanner.nextLine();

            // First initial amount input
        System.out.print("Enter your initial amount: ");
        int initialAmount = scanner.nextInt();

            // Consume the newline left-over
        scanner.nextLine();

            // Second name input
        System.out.print("Enter 2nd Name: ");
        String name2 = scanner.nextLine();

            // Second initial amount input
        System.out.print("Enter your initial amount: ");
        int initialAmount2 = scanner.nextInt();

            //SpringApplication.run(DemoApplication.class, args);
        Account account1 = new Account(name, initialAmount);
        Account account2 = new Account(name2, initialAmount2);

        System.out.println("Initial Balances:");
        System.out.println(account1.getAccountHolder() + ": " + account1.getBalance());
        System.out.println(account2.getAccountHolder() + ": " + account2.getBalance());

            // Transfer money from Alice to Bob
        account1.transferTo(account2, 100);

        System.out.println("Balances after transfer:");
        System.out.println(account1.getAccountHolder() + ": " + account1.getBalance());
        System.out.println(account2.getAccountHolder() + ": " + account2.getBalance());

        try {
            account1.saveToDb(account1);
            account2.saveToDb(account2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        }


    }


