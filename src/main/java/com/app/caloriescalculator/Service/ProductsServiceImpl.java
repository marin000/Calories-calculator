package com.app.caloriescalculator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import com.app.caloriescalculator.Model.Products;
import com.app.caloriescalculator.Repository.ProductsRepository;
import com.app.caloriescalculator.Validator.ProductsDto;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public Products ProductsfindByName(String name) {
        return productsRepository.findByName(name);
    }

    @Override
    public Products add(ProductsDto  ProductDto) {
        Products product=new Products();
        product.setName(ProductDto.getName());
        product.setCarbohydrates(ProductDto.getCarbohydrates());
        product.setCalories(ProductDto.getCalories());
        product.setProteins(ProductDto.getProteins());
        product.setFats(ProductDto.getFats());
        return productsRepository.save(product);
    }

    @Override
    public Collection<Products> findAllProducts() {
		return productsRepository.findAll();
	}

    @Override
    public void addProduct(Products p) {
        Products newP=new Products();
        newP.setName(p.getName());
        newP.setCarbohydrates(p.getCarbohydrates());
        newP.setCalories(p.getCalories());
        newP.setProteins(p.getProteins());
        newP.setFats(p.getFats());
        productsRepository.save(newP);
    }

    @Override
    public void deleteProduct(Products product) {
        productsRepository.delete(product);
	}

    @Override
    public Products editPoduct(Products oldProduct, ProductsDto newProduct) {
        oldProduct.setName(newProduct.getName());
        oldProduct.setCarbohydrates(newProduct.getCarbohydrates());
        oldProduct.setCalories(newProduct.getCalories());
        oldProduct.setProteins(newProduct.getProteins());
        oldProduct.setFats(newProduct.getFats());
        return productsRepository.save(oldProduct);
    }


   
}