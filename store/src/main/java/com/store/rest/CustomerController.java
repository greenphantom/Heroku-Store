package com.store.rest;
import javax.servlet.http.HttpServlet;
import javax.servlet.*;

import com.store.model.Customer;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.stereotype.Controller;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Console;

//import com.store.dao.*;
//import com.store.model.*;

//import com.musicApp.dao.*;
//import com.musicApp.model.*;

@Controller
@Path("/customers")
public class CustomerController extends HttpServlet  {

    Customer customer = new Customer();
    CustomerService cs = new CustomerService();

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
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg(@PathParam("param") String msg) {
        String s = "select * from Customer where username=prbrown";
        //String output = albumService.getMsg(msg);
        customer = cs.getCustomer(msg);
        JSONObject jsonObject = new JSONObject(customer);
        String myJson = jsonObject.toString();
        return Response.status(200).entity(myJson).build();
    }

    @POST
    @Path("")
    public Response createUser(@QueryParam("fname") String firstName, @QueryParam("lname") String lastName,
                               @QueryParam("username") String userName, @QueryParam("email") String email){
        cs.createCustomer(firstName, lastName, userName, email);
        return Response.status(200).build();
    }

    @PUT
    @Path("")
    public Response updateUser(@QueryParam("fname") String firstName, @QueryParam("lname") String lastName,
                               @QueryParam("username") String userName, @QueryParam("email") String email){
        cs.updateCustomer(firstName, lastName, userName, email);
        return Response.status(200).build();
    }

    @DELETE
    @Path("/{param}")
    public Response deleteUser(@PathParam("param") String userName ) {
        cs.deleteCustomer(userName);
        return Response.status(200).build();
    }



    @GET
    @Produces("text/plain")
    public String getAllAlbums() {
        return "test";
    }

}