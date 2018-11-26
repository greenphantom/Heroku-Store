package com.store.dao;
import com.store.model.Products;
import org.springframework.jdbc.core.JdbcTemplate;

import com.store.model.Customer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.ArrayList;
import java.util.Collection;

public class ProductsDAO {
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";
    private JdbcTemplate jdbcTemplate;


    public ProductsDAO(JdbcTemplate jdbcTemp){
        this.jdbcTemplate = jdbcTemp;
    }
    public ProductsDAO(){
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    public ArrayList<Products> getProducts(){
        Products products = new Products();
        //String sql = "select * from products ";
        ArrayList<Products> productList = new ArrayList<Products>();
        this.jdbcTemplate.query(
                "SELECT * FROM products", new Object[] { },
                (rs, rowNum) -> new Products(rs.getLong("itemId"), rs.getString("name"), rs.getDouble("msrp"),
                        rs.getDouble("salePrice"), rs.getInt("upc"), rs.getString("shortDescription"),
                        rs.getString("brandName"), rs.getString("size"), rs.getString("color"),
                        rs.getString("gender"))
        ).forEach(album -> productList.add(album));
        return productList;
    }

    public ArrayList<Products> getProductsByKeword(String keyword){
        Products products = new Products();
        //String sql = "select * from products ";
        if(keyword.contains("'"))
            keyword = fixApostrophe(keyword);
        ArrayList<Products> productList = new ArrayList<Products>();
        String sql;
            sql=  "select * from products where name LIKE "+ "'%" +keyword+ "%'"+"" +
                    " or msrp like " + "'%" +keyword+ "%'" +
                    " or salePrice like " + "'%" +keyword+ "%'" +
                    " or upc like " + "'%" +keyword+ "%'" +
                    " or shortDescription like " + "'%" +keyword+ "%'" +
                    " or brandName like " + "'%" +keyword+ "%'" +
                    " or size like " + "'%" +keyword+ "%'" +
                    " or color like " + "'%" +keyword+ "%'" +
                    " or gender like " + "'%" +keyword+ "%'";

        this.jdbcTemplate.query(
                sql ,        //TODO replace with sql
                new Object[] { },
                (rs, rowNum) -> new Products(rs.getLong("itemId"), rs.getString("name"), rs.getDouble("msrp"),
                        rs.getDouble("salePrice"), rs.getInt("upc"), rs.getString("shortDescription"),
                        rs.getString("brandName"), rs.getString("size"), rs.getString("color"),
                        rs.getString("gender"))
        ).forEach(album -> productList.add(album));
        return productList;
    }

    public ArrayList<Products> getProductsById(long id){
        Products products = new Products();
        //String sql = "select * from products ";
        ArrayList<Products> productList = new ArrayList<Products>();
        this.jdbcTemplate.query(
                "select * from products where itemId = "+id, new Object[] { },
                (rs, rowNum) -> new Products(rs.getLong("itemId"), rs.getString("name"), rs.getDouble("msrp"),
                        rs.getDouble("salePrice"), rs.getInt("upc"), rs.getString("shortDescription"),
                        rs.getString("brandName"), rs.getString("size"), rs.getString("color"),
                        rs.getString("gender"))
        ).forEach(album -> productList.add(album));
        return productList;
    }

    private String fixApostrophe (String keyword){
        String[] s = new String[keyword.split("'").length];
        for(int i=0; i<s.length;i++){
            s[i]=keyword.split("'")[i];
            if(i!=s.length-1)
                s[i]+= "''";
        }
        keyword="";
        for(int i=0; i<s.length;i++)
        {
            keyword+=s[i];
        }
        return keyword;
    }



    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;

    }

}
