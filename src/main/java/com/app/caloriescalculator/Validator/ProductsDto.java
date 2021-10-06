package com.app.caloriescalculator.Validator;

import javax.validation.constraints.NotNull;

public class ProductsDto {

    @NotNull
    private String name;

    @NotNull
    private Float carbohydrates;

    @NotNull
    private Float calories;

    @NotNull
    private Float proteins;

    @NotNull
    private Float fats;

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
}