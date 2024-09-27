package com.demo.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type", nullable = false)
    private String type; // Type of vehicle (e.g., Truck, Van, etc.)

    @Column(name = "heavy_per_hour_price", nullable = false)
    private double heavyPerHourPrice; // Price per hour for heavy vehicles

    @Column(name = "model_name", nullable = false)
    private String modelName; // Model name of the vehicle

    // Default constructor
    public Vehicle() {
    }

    // Parameterized constructor
    public Vehicle(String type, double heavyPerHourPrice, String modelName) {
        this.type = type;
        this.heavyPerHourPrice = heavyPerHourPrice;
        this.modelName = modelName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getHeavyPerHourPrice() {
        return heavyPerHourPrice;
    }

    public void setHeavyPerHourPrice(double heavyPerHourPrice) {
        this.heavyPerHourPrice = heavyPerHourPrice;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", heavyPerHourPrice=" + heavyPerHourPrice +
                ", modelName='" + modelName + '\'' +
                '}';
    }
}

