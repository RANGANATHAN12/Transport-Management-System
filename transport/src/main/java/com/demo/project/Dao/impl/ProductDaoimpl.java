package com.demo.project.Dao.impl;


import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.project.Dao.ProductDao;
import com.demo.project.HibernateHelper.HibernateHelper;
import com.demo.project.entity.Product;

public class ProductDaoimpl implements ProductDao {

    private Scanner scanner;

    public ProductDaoimpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void registerProduct() {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Get user input
            System.out.println("Enter product name:");
            String name = scanner.nextLine();

            System.out.println("Enter product type (e.g., Home Use, Own Use, Manufacturing Items, Food Items):");
            String productType = scanner.nextLine();

            System.out.println("Is it for home use? (true/false):");
            boolean homeUse = scanner.nextBoolean();

            System.out.println("Is it for personal use? (true/false):");
            boolean ownUse = scanner.nextBoolean();

            System.out.println("Is it a manufacturing item? (true/false):");
            boolean manufacturingItems = scanner.nextBoolean();

            System.out.println("Is it a food item? (true/false):");
            boolean foodItems = scanner.nextBoolean();
            scanner.nextLine(); // Consume newline

            Product product = new Product(name, productType, homeUse, ownUse, manufacturingItems, foodItems);

            // Save and commit
            session.save(product);
            transaction.commit();
            System.out.println("Product registered successfully.");
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
    public void viewProduct() {
        Session session = HibernateHelper.getSessionFactory().openSession();

        try {
            System.out.println("Enter Product ID:");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            Product product = session.get(Product.class, id);

            if (product != null) {
                // Display product details
                System.out.println(product);
            } else {
                // Display a message if no product is found with the given ID
                System.out.println("No Product found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void editProduct() {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("Enter Product ID to edit:");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            Product product = session.get(Product.class, id);

            if (product != null) {
                System.out.println("What details do you want to modify?");
                System.out.println("1. Name");
                System.out.println("2. Product Type");
                System.out.println("3. Home Use");
                System.out.println("4. Own Use");
                System.out.println("5. Manufacturing Items");
                System.out.println("6. Food Items");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Enter new product name:");
                        String newName = scanner.nextLine();
                        product.setName(newName);
                        break;

                    case 2:
                        System.out.println("Enter new product type:");
                        String newProductType = scanner.nextLine();
                        product.setProductType(newProductType);
                        break;

                    case 3:
                        System.out.println("Is it for home use? (true/false):");
                        product.setHomeUse(scanner.nextBoolean());
                        break;

                    case 4:
                        System.out.println("Is it for personal use? (true/false):");
                        product.setOwnUse(scanner.nextBoolean());
                        break;

                    case 5:
                        System.out.println("Is it a manufacturing item? (true/false):");
                        product.setManufacturingItems(scanner.nextBoolean());
                        break;

                    case 6:
                        System.out.println("Is it a food item? (true/false):");
                        product.setFoodItems(scanner.nextBoolean());
                        break;

                    default:
                        System.out.println("Invalid choice");
                        return;
                }
                session.update(product);
                transaction.commit();
                System.out.println("Product details updated.");
            } else {
                System.out.println("No Product found with ID: " + id);
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



