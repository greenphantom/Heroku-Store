package com.store.app;


//import jdk.vm.ci.code.site.Call;
import com.store.dao.CartDAO;
import com.store.rest.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

//import com.store.dao.*;
//import com.store.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
While you are developing your DAO layer, you can configure maven to build a jar file
and use this class to perform tests, before moving on to implementing the REST layer.
*/

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        ProductsService ps = new ProductsService();
        CartDAO cart =  new CartDAO();
        cart.getCustomerCart("jack");


        SpringApplication.run(Application.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        String creation;
        String addition;
        BufferedReader br = new BufferedReader(new FileReader("A:\\Everything\\installing junk\\IndividualProject1-master\\IndividualProject1-master\\store\\src\\scripts\\createDBTables.sql"));
        BufferedReader b2 = new BufferedReader(new FileReader("A:\\Everything\\installing junk\\IndividualProject1-master\\IndividualProject1-master\\store\\src\\scripts\\populateDBTables.sql"));

       /* try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                System.out.println("siadbuasdib  "+ line);
                jdbcTemplate.execute(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            creation = sb.toString();
            sb = new StringBuilder();
            line=b2.readLine();
            while (line != null) {
                sb.append(line);
                System.out.println("siadbuasdib  "+ line);
                jdbcTemplate.execute(line);
                sb.append(System.lineSeparator());
                line = b2.readLine();
            }
            addition = sb.toString();
        } finally {
            br.close();
        }*/
/*
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_store?allowPublicKeyRetrieval=true&useSSL=false"+ "/xe","springuser", "ThePassword");
        String s = "{call insertProducts(?) }";
        CallableStatement p = c.prepareCall(s);
        p.setString(1, "All");
        p.execute();
       // jdbcTemplate.execute(addition);


  */


        /** TODO:
			You use the provided .sql scripts to create and populate tables.
			Then, you can add calls to your CRUD operations from within this method.
		**/

    }
}
