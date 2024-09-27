package com.demo.project.Dao.impl;


import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import com.demo.project.Dao.VehicleDao;
import com.demo.project.HibernateHelper.HibernateHelper;
import com.demo.project.entity.Vehicle;

public class VehicleDaoimpl implements VehicleDao {

    private Validator validator;
    private Scanner scanner;

    public VehicleDaoimpl() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void registerVehicle() {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Get user input
            System.out.println("Enter vehicle type:");
            String type = scanner.nextLine();

            System.out.println("Enter heavy per hour price:");
            double heavyPerHourPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.println("Enter model name:");
            String modelName = scanner.nextLine();

            Vehicle vehicle = new Vehicle(type, heavyPerHourPrice, modelName);

            // Validate the Vehicle object
            Set<ConstraintViolation<Vehicle>> violations = validator.validate(vehicle);
            if (violations.isEmpty()) {
                // Save and commit if no validation errors
                session.save(vehicle);
                transaction.commit();
                System.out.println("Vehicle registered successfully.");
            } else {
                // Print validation errors
                for (ConstraintViolation<Vehicle> violation : violations) {
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
    public void viewVehicle() {
        Session session = HibernateHelper.getSessionFactory().openSession();

        try {
            System.out.println("Enter Vehicle ID:");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            Vehicle vehicle = session.get(Vehicle.class, id);

            if (vehicle != null) {
                // Display vehicle details
                System.out.println(vehicle);
            } else {
                // Display a message if no vehicle is found with the given ID
                System.out.println("No Vehicle found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void editVehicle() {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("Enter Vehicle ID to edit:");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            Vehicle vehicle = session.get(Vehicle.class, id);

            if (vehicle != null) {
                System.out.println("What details do you want to modify?");
                System.out.println("1. Type");
                System.out.println("2. Heavy Per Hour Price");
                System.out.println("3. Model Name");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Enter new type:");
                        String newType = scanner.nextLine();
                        vehicle.setType(newType);
                        break;

                    case 2:
                        System.out.println("Enter new heavy per hour price:");
                        double newHeavyPerHourPrice = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        vehicle.setHeavyPerHourPrice(newHeavyPerHourPrice);
                        break;

                    case 3:
                        System.out.println("Enter new model name:");
                        String newModelName = scanner.nextLine();
                        vehicle.setModelName(newModelName);
                        break;

                    default:
                        System.out.println("Invalid choice");
                        return;
                }
                session.update(vehicle);
                transaction.commit();
                System.out.println("Vehicle details updated.");
            } else {
                System.out.println("No Vehicle found with ID: " + id);
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


