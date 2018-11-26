package com.store.rest;

import com.store.dao.CustomerDAO;
import com.store.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    CustomerDAO customerDAO = new CustomerDAO();

    public Customer getCustomer(String username){

        return customerDAO.getCustomer(username);
    }

    public void createCustomer(String fname, String lname, String username, String email){
        customerDAO.createCustomer(new Customer(fname, lname,username,email));
    }

    public void updateCustomer(String fname, String lname, String username, String email){
        customerDAO.updateCustomer(fname, lname, username,email);
    }
    public void deleteCustomer(String username){
        customerDAO.deleteCustomer(username);
    }
}