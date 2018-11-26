package com.store.rest;
import javax.servlet.http.HttpServlet;
import javax.servlet.*;

import com.store.model.Cart;
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
@Path("/carts")
public class CartController extends HttpServlet  {

    Customer customer = new Customer();
    CartService cs = new CartService();

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
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerCart(@DefaultValue("null")@QueryParam("username")String username,@DefaultValue("0")@QueryParam("productId")int productId){

        if(!username.equals("null")){

            Cart c = cs.getUserCart(username);
            JSONObject jo = new JSONObject(c);
            if(jo != null){
                return Response.status(200).entity(jo.toString()).build();
            }
            return Response.status(404).build();
        }
        else{
            ArrayList<String> x = cs.getUserList(productId);

            JSONArray jsonObject = new JSONArray(x);
            String myJson = jsonObject.toString();
            return Response.status(200).entity(myJson).build();
        }


    }


    @POST
    @Path("")
    public Response getMsg(@QueryParam("productId") int itemId, @QueryParam("username") String username) {
       // String s = "select * from Customer where username=prbrown";
        //String output = albumService.getMsg(msg);
        //customer = cs.getCustomer(msg);
        cs.addItem(itemId, username);
        //JSONObject jsonObject = new JSONObject(customer);
        //String myJson = jsonObject.toString();
        return Response.status(200).build();
    }

    @DELETE
    @Path("")
    public Response removeCart(@QueryParam("cartId") int cartId, @QueryParam("productId") int itemId){
        cs.removeCart(cartId, itemId);
        return Response.status(200).build();

    }

    @PUT
    @Path("/purchase/{param}")
    public Response buyItem(@PathParam("param") int cartId){
        cs.buyItem(cartId);
        return Response.status(200).build();

    }










}