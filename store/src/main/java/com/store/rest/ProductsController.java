package com.store.rest;
import javax.servlet.http.HttpServlet;
import javax.servlet.*;

import com.store.model.Customer;
import com.store.model.Products;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;

//import com.store.dao.*;
//import com.store.model.*;

//import com.musicApp.dao.*;
//import com.musicApp.model.*;

@Controller
@Path("/items")
public class ProductsController extends HttpServlet  {

    Products product = new Products();
    ProductsService ps = new ProductsService();

    //@Autowired
    //private AlbumService albumService = new AlbumService();
    public void init(ServletConfig config) {
        try{
            super.init(config);
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                    config.getServletContext());
        }catch(ServletException e){

        }
    }



    @GET
    @Path("/search/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg(@PathParam("param") String keyword) {
        //String s = "select * from Customer where username=prbrown";
        //String output = albumService.getMsg(msg);
        //customer = cs.getCustomer(msg);
        //JSONObject jsonObject = new JSONObject(customer);
        //String myJson = jsonObject.toString();

        ArrayList<Products> productsList = ps.getProductsByKeyword(keyword);
        JSONArray JsonArray = new JSONArray(productsList);
        String x = JsonArray.toString();
        return Response.status(200).entity(x).build();
    }

    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("param") long id) {
        //String s = "select * from Customer where username=prbrown";
        //String output = albumService.getMsg(msg);
        //customer = cs.getCustomer(msg);
        //JSONObject jsonObject = new JSONObject(customer);
        //String myJson = jsonObject.toString();
        ArrayList<Products> productsList = ps.getProductsById(id);
        JSONArray JsonArray = new JSONArray(productsList);
        String x = JsonArray.toString();
        return Response.status(200).entity(x).build();
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts()
    {
        ArrayList<Products> productsCol = ps.getProducts();
        //String finalList="";
        /*
        for(int i=0;i<productsCol.size();i++){
            JSONObject jsonObject = new JSONObject(productsCol.get(i));
            String myJson = jsonObject.toString();
            finalList+=myJson;
        }*/
        JSONArray JsonArray = new JSONArray(productsCol);
        String x = JsonArray.toString();
        return Response.status(200).entity(x).build();
    }

}