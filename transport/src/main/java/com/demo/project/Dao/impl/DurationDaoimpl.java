package com.demo.project.Dao.impl;



import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.demo.project.Dao.DurationDao;
import com.demo.project.HibernateHelper.HibernateHelper;
import com.demo.project.entity.Duration;

public class DurationDaoimpl implements DurationDao {

    private Scanner scanner;

    public DurationDaoimpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void registerDuration() {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Get user input
            System.out.println("Enter duration in seconds:");
            long durationInSeconds = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            Duration duration = new Duration(durationInSeconds);

            // Save and commit
            session.save(duration);
            transaction.commit();
            System.out.println("Duration registered successfully.");
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
    public void viewDuration() {
        Session session = HibernateHelper.getSessionFactory().openSession();

        try {
            System.out.println("Enter Duration ID:");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            Duration duration = session.get(Duration.class, id);

            if (duration != null) {
                // Display duration details
                System.out.println(duration);
            } else {
                // Display a message if no duration is found with the given ID
                System.out.println("No Duration found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void editDuration() {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("Enter Duration ID to edit:");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            Duration duration = session.get(Duration.class, id);

            if (duration != null) {
                System.out.println("Enter new duration in seconds:");
                long newDurationInSeconds = scanner.nextLong();
                scanner.nextLine(); // Consume newline

                duration.setDurationInSeconds(newDurationInSeconds);
                session.update(duration);
                transaction.commit();
                System.out.println("Duration details updated.");
            } else {
                System.out.println("No Duration found with ID: " + id);
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
