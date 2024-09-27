package com.demo.project.Dao.impl;



import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import com.demo.project.Dao.ClientDao;
import com.demo.project.HibernateHelper.HibernateHelper;
import com.demo.project.entity.Client;


public class ClientDaoimpl implements ClientDao {

    private Validator validator;
    private Scanner scanner;

    public ClientDaoimpl() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void registerClient() {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Get user input
            System.out.println("Enter first name:");
            String firstName = scanner.nextLine();

            System.out.println("Enter last name:");
            String lastName = scanner.nextLine();

            System.out.println("Enter email:");
            String email = scanner.nextLine();

            System.out.println("Enter phone number:");
            String phoneNumber = scanner.nextLine();

            System.out.println("Enter pickup location:");
            String pickupLocation = scanner.nextLine();

            System.out.println("Enter delivery location:");
            String deliveryLocation = scanner.nextLine();

            System.out.println("Enter proof:");
            String proof = scanner.nextLine();

            Client client = new Client(firstName, lastName, email, phoneNumber, pickupLocation, deliveryLocation, proof);

            // Validate the Client object
            Set<ConstraintViolation<Client>> violations = validator.validate(client);
            if (violations.isEmpty()) {
                // Save and commit if no validation errors
                session.save(client);
                transaction.commit();
                System.out.println("Client registered successfully.");
            } else {
                // Print validation errors
                for (ConstraintViolation<Client> violation : violations) {
                    System.out.println(violation.getMessage());
                }
                transaction.rollback(); // Rollback transaction if validation fails
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void viewClient() {
        Session session = HibernateHelper.getSessionFactory().openSession();

        try {
            System.out.println("Enter Client ID:");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            Client client = session.get(Client.class, id);

            if (client != null) {
                // Display client details
                System.out.println(client);
            } else {
                // Display a message if no client is found with the given ID
                System.out.println("No Client found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void editClient() {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("Enter Client ID to edit:");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            Client client = session.get(Client.class, id);

            if (client != null) {
                System.out.println("What details do you want to modify?");
                System.out.println("1. Phone Number");
                System.out.println("2. Pickup Location");
                System.out.println("3. Delivery Location");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Enter new phone number:");
                        String newPhoneNumber = scanner.nextLine();
                        client.setPhoneNumber(newPhoneNumber);
                        break;

                    case 2:
                        System.out.println("Enter new pickup location:");
                        String newPickupLocation = scanner.nextLine();
                        client.setPickupLocation(newPickupLocation);
                        break;

                    case 3:
                        System.out.println("Enter new delivery location:");
                        String newDeliveryLocation = scanner.nextLine();
                        client.setDeliveryLocation(newDeliveryLocation);
                        break;

                    default:
                        System.out.println("Invalid choice");
                        return;
                }
                session.update(client);
                transaction.commit();
                System.out.println("Client details updated.");
            } else {
                System.out.println("No Client found with ID: " + id);
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}


