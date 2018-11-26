package com.store.dao;
import org.springframework.jdbc.core.JdbcTemplate;

import com.store.model.Customer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CustomerDAO {
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_store?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";
    private JdbcTemplate jdbcTemplate;


    public CustomerDAO(JdbcTemplate jdbcTemp){
        this.jdbcTemplate = jdbcTemp;
    }
    public CustomerDAO(){
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    public Customer getCustomer(String username){
        Customer customer = new Customer();
        String sql = "select * from customers where username = "+ "'" +username+ "'";
        jdbcTemplate.query(sql, (rs) -> {
            customer.setFirstName(rs.getString("fname"));
            customer.setLastName(rs.getString("lname"));
            customer.setUserName(username);
            customer.seteMail(rs.getString("email"));
        });
        return customer;
    }

    public void createCustomer(Customer c){
        jdbcTemplate.execute("INSERT INTO customers "+ "VALUES ("+null+", '"+c.getFirstName()+"', '"+c.getLastName()+"', '"+c.getUserName()+ "', '"+c.geteMail()+"')");
    }

    public void updateCustomer(String fname, String lname, String username, String email ){
        String sql = "update customers set fname = ?, lname = ?, username = ?, email = ? where username = ?";
        jdbcTemplate.update(sql, fname, lname, username, email, username);
    }
    public void deleteCustomer(String username){
        jdbcTemplate.update("delete from customers where username = '"+username+"'");

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
