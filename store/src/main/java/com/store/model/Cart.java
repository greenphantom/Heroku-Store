package com.store.model;

import java.util.ArrayList;
import java.util.Collection;

public class Cart {

    private int cartId;
    private Collection<Products> productsList;




    public Cart(){

    }

    public Cart(int userId,  ArrayList<Products> productsList){
        this.cartId = userId;
        this.productsList = productsList;
    }


    public int getCartId() {
        return cartId;
    }

    public void setCartId(int userId) {
        this.cartId = userId;
    }

    public Collection<Products> getProductsList() {
        return productsList;
    }
    public void setProductsList(Collection<Products> PL){
        productsList = PL;
    }

}
