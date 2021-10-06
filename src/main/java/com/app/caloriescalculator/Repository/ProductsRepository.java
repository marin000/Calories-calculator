package com.app.caloriescalculator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.caloriescalculator.Model.Products;
@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
    Products findByName(String name);

}