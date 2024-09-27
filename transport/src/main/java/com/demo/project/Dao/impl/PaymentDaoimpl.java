package com.demo.project.Dao.impl;



import java.time.LocalDateTime;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.demo.project.Dao.PaymentDao;
import com.demo.project.HibernateHelper.HibernateHelper;
import com.demo.project.entity.Payment;

public class PaymentDaoimpl implements PaymentDao {

    private Scanner scanner;

    public PaymentDaoimpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void registerPayment() {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Get user input
            System.out.println("Enter payment amount:");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            System.out.println("Enter payment date (YYYY-MM-DDThh:mm:ss):");
            LocalDateTime paymentDate = LocalDateTime.parse(scanner.nextLine());

            System.out.println("Enter UPI ID (or leave blank if not applicable):");
            String upi = scanner.nextLine();
            if (upi.isEmpty()) upi = null;

            System.out.println("Enter NEFT reference (or leave blank if not applicable):");
            String neftReference = scanner.nextLine();
            if (neftReference.isEmpty()) neftReference = null;

            System.out.println("Enter Net banking details (or leave blank if not applicable):");
            String netBankingDetails = scanner.nextLine();
            if (netBankingDetails.isEmpty()) netBankingDetails = null;

            System.out.println("Enter card number (or leave blank if not applicable):");
            String cardNumber = scanner.nextLine();
            if (cardNumber.isEmpty()) cardNumber = null;

            System.out.println("Was the payment made in cash? (true/false):");
            boolean cashReceived = scanner.nextBoolean();
            scanner.nextLine(); // Consume newline

            Payment payment = new Payment(amount, paymentDate, upi, neftReference, netBankingDetails, cardNumber, cashReceived);

            // Save and commit
            session.save(payment);
            transaction.commit();
            System.out.println("Payment registered successfully.");
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
    public void viewPayment() {
        Session session = HibernateHelper.getSessionFactory().openSession();

        try {
            System.out.println("Enter Payment ID:");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            Payment payment = session.get(Payment.class, id);

            if (payment != null) {
                // Display payment details
                System.out.println(payment);
            } else {
                // Display a message if no payment is found with the given ID
                System.out.println("No Payment found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void editPayment() {
        Session session = HibernateHelper.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("Enter Payment ID to edit:");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Consume newline

            Payment payment = session.get(Payment.class, id);

            if (payment != null) {
                System.out.println("What details do you want to modify?");
                System.out.println("1. Amount");
                System.out.println("2. Payment Date");
                System.out.println("3. UPI ID");
                System.out.println("4. NEFT Reference");
                System.out.println("5. Net Banking Details");
                System.out.println("6. Card Number");
                System.out.println("7. Cash Received");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Enter new amount:");
                        payment.setAmount(scanner.nextDouble());
                        scanner.nextLine(); // Consume newline
                        break;

                    case 2:
                        System.out.println("Enter new payment date (YYYY-MM-DDThh:mm:ss):");
                        payment.setPaymentDate(LocalDateTime.parse(scanner.nextLine()));
                        break;

                    case 3:
                        System.out.println("Enter new UPI ID (or leave blank if not applicable):");
                        String newUpi = scanner.nextLine();
                        payment.setUpi(newUpi.isEmpty() ? null : newUpi);
                        break;

                    case 4:
                        System.out.println("Enter new NEFT reference (or leave blank if not applicable):");
                        String newNeftReference = scanner.nextLine();
                        payment.setNeftReference(newNeftReference.isEmpty() ? null : newNeftReference);
                        break;

                    case 5:
                        System.out.println("Enter new Net banking details (or leave blank if not applicable):");
                        String newNetBankingDetails = scanner.nextLine();
                        payment.setNetBankingDetails(newNetBankingDetails.isEmpty() ? null : newNetBankingDetails);
                        break;

                    case 6:
                        System.out.println("Enter new card number (or leave blank if not applicable):");
                        String newCardNumber = scanner.nextLine();
                        payment.setCardNumber(newCardNumber.isEmpty() ? null : newCardNumber);
                        break;

                    case 7:
                        System.out.println("Was the payment made in cash? (true/false):");
                        payment.setCashReceived(scanner.nextBoolean());
                        scanner.nextLine(); // Consume newline
                        break;

                    default:
                        System.out.println("Invalid choice");
                        return;
                }
                session.update(payment);
                transaction.commit();
                System.out.println("Payment details updated.");
            } else {
                System.out.println("No Payment found with ID: " + id);
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



