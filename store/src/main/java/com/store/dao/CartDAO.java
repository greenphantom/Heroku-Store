package com.store.dao;

import com.store.model.Cart;
import com.store.model.Customer;
import com.store.model.Products;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class CartDAO {

    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";
    private JdbcTemplate jdbcTemplate;

    public CartDAO(){
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    public CartDAO(JdbcTemplate jdbcTemp){
        this.jdbcTemplate = jdbcTemp;
    }


    public void addItemToCart(int productId, String username){
        String sql = "SELECT id FROM customers WHERE username = '"+username+"'";
        Customer c = new Customer();

        jdbcTemplate.query(sql, (rs) -> {
            c.setId(rs.getInt("id"));
        });

        jdbcTemplate.execute("INSERT INTO carts VALUES("+c.getId() + ", " + productId + ", '" + username + "')");

    }

    public Cart getCustomerCart(String username){
        Cart cart = new Cart();
        Collection<Products> products = new ArrayList<>();
        String sql = "SELECT id FROM customers WHERE username = '"+username+"'";

        jdbcTemplate.query(sql, (rs) -> {
            cart.setCartId(rs.getInt("id"));
        });

        this.jdbcTemplate.query(
                "select * from carts join products on carts.itemId = products.itemId where username = '"+username+"'", new Object[] { },
                (rs, rowNum) -> new Products(
                        rs.getInt("itemId"),
                        rs.getString("name"),
                        rs.getDouble("msrp"),
                        rs.getDouble("salePrice"),
                        rs.getInt("upc"),
                        rs.getString("shortDescription"),
                        rs.getString("brandName"),
                        rs.getString("size"),
                        rs.getString("color"),
                        rs.getString("gender"))
        ).forEach(product -> products.add(product));



        cart.setProductsList(products);
        return cart;

    }

    public void buyItem(int cartId){
    String sql ="insert into purchased (itemId, username) SELECT itemId, username FROM carts where carts.id = "+cartId;
    jdbcTemplate.execute(sql);
    String sql2 = "DELETE from carts where id = "+cartId;
    jdbcTemplate.update(sql2);
    }

    public void removeCart(int id, int itemId){
        String sql = "DELETE from carts where itemId = "+itemId+" AND id = "+id;
        jdbcTemplate.update(sql);

    }

    public ArrayList<String> getUserList(int productId){
        ArrayList<String> x = new ArrayList<>();
        String sql ="select username from purchased where itemId = "+productId;
        this.jdbcTemplate.query(
                sql, new Object[] { },
                (rs, rowNum) -> rs.getString("username")
        ).forEach(username -> x.add(username));
    return x;
    }
/*
 this.jdbcTemplate.query(
         "SELECT * FROM products", new Object[] { },
            (rs, rowNum) -> new Products(rs.getLong("itemId"), rs.getString("name"), rs.getDouble("msrp"),
                        rs.getDouble("salePrice"), rs.getInt("upc"), rs.getString("shortDescription"),
                                rs.getString("brandName"), rs.getString("size"), rs.getString("color"),
                                rs.getString("gender"))
                                ).forEach(album -> productList.add(album));*/

    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;

    }
}
