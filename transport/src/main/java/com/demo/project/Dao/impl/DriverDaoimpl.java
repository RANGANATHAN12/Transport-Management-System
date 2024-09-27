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

import com.demo.project.Dao.DriverDao;
import com.demo.project.HibernateHelper.HibernateHelper;
import com.demo.project.entity.Driver;

public class DriverDaoimpl implements DriverDao {

    private Validator validator;
    private Scanner scanner;

    public DriverDaoimpl() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void registerDriver() {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Get user input
            System.out.println("Enter name:");
            String name = scanner.nextLine();

            System.out.println("Enter license number:");
            String licenseNumber = scanner.nextLine();

            System.out.println("Enter email:");
            String email = scanner.nextLine();

            System.out.println("Enter phone number:");
            String phoneNumber = scanner.nextLine();

            Driver driver = new Driver(name, licenseNumber, email, phoneNumber);

            // Validate the Driver object
            Set<ConstraintViolation<Driver>> violations = validator.validate(driver);
            if (violations.isEmpty()) {
                // Save and commit if no validation errors
                session.save(driver);
                transaction.commit();
                System.out.println("Driver registered successfully.");
            } else {
                // Print validation errors
                for (ConstraintViolation<Driver> violation : violations) {
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
    public void viewDriver() {
        Session session = HibernateHelper.getSessionFactory().openSession();

        try {
            System.out.println("Enter Driver ID:");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            Driver driver = session.get(Driver.class, id);

            if (driver != null) {
                // Display driver details
                System.out.println(driver);
            } else {
                // Display a message if no driver is found with the given ID
                System.out.println("No Driver found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void editDriver() {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("Enter Driver ID to edit:");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            Driver driver = session.get(Driver.class, id);

            if (driver != null) {
                System.out.println("What details do you want to modify?");
                System.out.println("1. Phone Number");
                System.out.println("2. Email");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Enter new phone number:");
                        String newPhoneNumber = scanner.nextLine();
                        driver.setPhoneNumber(newPhoneNumber);
                        break;

                    case 2:
                        System.out.println("Enter new email:");
                        String newEmail = scanner.nextLine();
                        driver.setEmail(newEmail);
                        break;

                    default:
                        System.out.println("Invalid choice");
                        return;
                }
                session.update(driver);
                transaction.commit();
                System.out.println("Driver details updated.");
            } else {
                System.out.println("No Driver found with ID: " + id);
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
