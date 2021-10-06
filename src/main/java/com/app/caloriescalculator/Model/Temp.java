package com.app.caloriescalculator.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "temp")

public class Temp{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String product;
    private String meal;
    private Float quantity;
    private Float carbohydrates;
    private Float calories;
    private Float proteins;
    private Float fats;

    public Temp(){}
    public Temp(int id, String product, String meal, Float quantity, Float carbohydrates, Float calories,
    Float proteins, Float fats) {
        this.id = id;
        this.product = product;
        this.meal = meal;
        this.quantity = quantity;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public Float getProteins() {
        return proteins;
    }

    public void setProteins(Float proteins) {
        this.proteins = proteins;
    }

    public Float getFats() {
        return fats;
    }

    public void setFats(Float fats) {
        this.fats = fats;
    }
}
