package com.demo.project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
	@Table(name = "payments")
	public class Payment {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;

	    @Column(name = "amount", nullable = false)
	    private double amount; // The amount of the payment

	    @Column(name = "payment_date", nullable = false)
	    private LocalDateTime paymentDate; // The date and time when the payment was made

	    @Column(name = "upi", nullable = true)
	    private String upi; // UPI ID, if the payment was made using UPI

	    @Column(name = "neft_reference", nullable = true)
	    private String neftReference; // NEFT reference number, if the payment was made using NEFT

	    @Column(name = "net_banking_details", nullable = true)
	    private String netBankingDetails; // Net banking details, if the payment was made using Net Banking

	    @Column(name = "card_number", nullable = true)
	    private String cardNumber; // Card number, if the payment was made using Card

	    @Column(name = "cash_received", nullable = true)
	    private boolean cashReceived; // Indicates if the payment was made in cash

	    // Default constructor
	    public Payment() {
	    }

	    // Parameterized constructor
	    public Payment(double amount, LocalDateTime paymentDate, String upi, String neftReference,
	                   String netBankingDetails, String cardNumber, boolean cashReceived) {
	        this.amount = amount;
	        this.paymentDate = paymentDate;
	        this.upi = upi;
	        this.neftReference = neftReference;
	        this.netBankingDetails = netBankingDetails;
	        this.cardNumber = cardNumber;
	        this.cashReceived = cashReceived;
	    }

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public void setAmount(double amount) {
	        this.amount = amount;
	    }

	    public LocalDateTime getPaymentDate() {
	        return paymentDate;
	    }

	    public void setPaymentDate(LocalDateTime paymentDate) {
	        this.paymentDate = paymentDate;
	    }

	    public String getUpi() {
	        return upi;
	    }

	    public void setUpi(String upi) {
	        this.upi = upi;
	    }

	    public String getNeftReference() {
	        return neftReference;
	    }

	    public void setNeftReference(String neftReference) {
	        this.neftReference = neftReference;
	    }

	    public String getNetBankingDetails() {
	        return netBankingDetails;
	    }

	    public void setNetBankingDetails(String netBankingDetails) {
	        this.netBankingDetails = netBankingDetails;
	    }

	    public String getCardNumber() {
	        return cardNumber;
	    }

	    public void setCardNumber(String cardNumber) {
	        this.cardNumber = cardNumber;
	    }

	    public boolean isCashReceived() {
	        return cashReceived;
	    }

	    public void setCashReceived(boolean cashReceived) {
	        this.cashReceived = cashReceived;
	    }

	    @Override
	    public String toString() {
	        return "Payment{" +
	                "id=" + id +
	                ", amount=" + amount +
	                ", paymentDate=" + paymentDate +
	                ", upi='" + upi + '\'' +
	                ", neftReference='" + neftReference + '\'' +
	                ", netBankingDetails='" + netBankingDetails + '\'' +
	                ", cardNumber='" + cardNumber + '\'' +
	                ", cashReceived=" + cashReceived +
	                '}';
	    }
	}


