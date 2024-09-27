
package com.demo.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name; // Name of the product

    @Column(name = "product_type", nullable = false)
    private String productType; // Type of the product (e.g., Home Use, Own Use, Manufacturing Items, Food Items)

    @Column(name = "home_use", nullable = true)
    private boolean homeUse; // Indicates if the product is for home use

    @Column(name = "own_use", nullable = true)
    private boolean ownUse; // Indicates if the product is for personal use

    @Column(name = "manufacturing_items", nullable = true)
    private boolean manufacturingItems; // Indicates if the product is a manufacturing item

    @Column(name = "food_items", nullable = true)
    private boolean foodItems; // Indicates if the product is a food item

    // Default constructor
    public Product() {
    }

    // Parameterized constructor
    public Product(String name, String productType, boolean homeUse, boolean ownUse, 
                   boolean manufacturingItems, boolean foodItems) {
        this.name = name;
        this.productType = productType;
        this.homeUse = homeUse;
        this.ownUse = ownUse;
        this.manufacturingItems = manufacturingItems;
        this.foodItems = foodItems;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public boolean isHomeUse() {
        return homeUse;
    }

    public void setHomeUse(boolean homeUse) {
        this.homeUse = homeUse;
    }

    public boolean isOwnUse() {
        return ownUse;
    }

    public void setOwnUse(boolean ownUse) {
        this.ownUse = ownUse;
    }

    public boolean isManufacturingItems() {
        return manufacturingItems;
    }

    public void setManufacturingItems(boolean manufacturingItems) {
        this.manufacturingItems = manufacturingItems;
    }

    public boolean isFoodItems() {
        return foodItems;
    }

    public void setFoodItems(boolean foodItems) {
        this.foodItems = foodItems;
    }

  
}
