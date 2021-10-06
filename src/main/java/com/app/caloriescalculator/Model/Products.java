package com.app.caloriescalculator.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")

public class Products{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private Float carbohydrates;
    private Float calories;
    private Float proteins;
    private Float fats;

    public Products(){} 

    public Products(int id, String name, Float carbohydrates, Float calories, Float proteins, Float fats) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Products [calories=" + calories + ", carbohydrates=" + carbohydrates + ", fats=" + fats + ", id=" + id + ", name="
                + name + ", proteins=" + proteins + "]";
    }
}