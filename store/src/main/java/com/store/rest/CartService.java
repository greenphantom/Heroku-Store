package com.store.rest;

import com.store.dao.CartDAO;
import com.store.model.Cart;
import com.store.model.Products;

import java.util.ArrayList;
import java.util.Collection;

public class CartService {
    CartDAO cart = new CartDAO();

    public void addItem(int itemId, String username){
        cart.addItemToCart(itemId,username);
    }

    public Cart getUserCart(String username){
        Cart c = cart.getCustomerCart(username);
        return c;
    }


    public void removeCart(int id , int itemId){
        cart.removeCart(id, itemId);
    }

    public void buyItem(int cartId){
        cart.buyItem(cartId);
    }

    public ArrayList<String> getUserList(int productId){
        ArrayList<String> x = cart.getUserList(productId);

        return x;
    }
}
