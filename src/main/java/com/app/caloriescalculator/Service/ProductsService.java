package com.app.caloriescalculator.Service;
import java.util.Collection;
import com.app.caloriescalculator.Model.Products;
import com.app.caloriescalculator.Validator.ProductsDto;

public interface ProductsService{

    Products ProductsfindByName(String name);

    Products add(ProductsDto ProductDto);

    void addProduct(Products product);

    void deleteProduct(Products product);

    Collection <Products> findAllProducts();

    Products editPoduct(Products oldProduct,ProductsDto newProduct);

}