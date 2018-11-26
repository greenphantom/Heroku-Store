package com.store.rest;

import com.store.dao.ProductsDAO;
import com.store.model.Products;

import java.util.ArrayList;
import java.util.Collection;

public class ProductsService {
    ProductsDAO products = new ProductsDAO();

    public ArrayList<Products> getProducts(){
       ArrayList <Products> x = products.getProducts();
       return x;
    }

    public ArrayList<Products> getProductsByKeyword(String keyword){
        ArrayList <Products> x = products.getProductsByKeword(keyword);
        return x;
    }

    public ArrayList<Products> getProductsById(long id){
        ArrayList <Products> x = products.getProductsById(id);
        return x;
    }
}
