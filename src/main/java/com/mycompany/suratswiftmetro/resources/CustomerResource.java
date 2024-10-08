/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.suratswiftmetro.resources;

import ejb.customerEJBLocal;
import entity.*;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author jeshanpatel1510
 */
@DeclareRoles({"Passenger"})
@RolesAllowed({"Passenger"})
@Path("customer")
public class CustomerResource {
    @EJB customerEJBLocal customerEJB;
    
    @GET
    @Path("passanger/get/{username}")
    @Produces("application/json")
    public Passanger getPassangerById(@PathParam("username") String username) {
        return customerEJB.getPassangerByUsername(username);
    }
    
    @POST
    @Path("booking/book")
    @Consumes("application/json")
    public void bookTicket(Booking booking) {
        customerEJB.bookTicket(booking);
    }
    
    @POST
    @Path("payment")
    @Consumes("application/json")
    public void makePayment(Payment payment) {
        System.out.println("Booking Resource!!");
        customerEJB.makePayment(payment);
    } 
     @GET
    @Path("subscription/getActiveByPassanger/{passangerId}")
    @Produces("application/json")    
    public Subscripton getActiveSubscriptionByPassenger(@PathParam("passangerId") Integer passangerId){        
        return customerEJB.getActiveSubscriptionByPassenger(passangerId);
    }
    
    @POST
    @Path("scheme/subscribe")
    @Consumes("application/json")
    public void subscribeToScheme(Subscripton subscription) {
        customerEJB.subscribeToScheme(subscription);
    }
    
    @GET
    @Path("booking/getByPassanger/{passangerId}")
    @Produces("application/json")
    public List<Booking> getBookingsForPassanger(@PathParam("passangerId") Integer passangerId) {
        return customerEJB.getBookingsForPassanger(passangerId);
    }
 
    @GET
    @Path("subscription/getByPassanger/{passangerId}")
    @Produces("application/json")
    public List<Subscripton> getSubscriptionsByPassaanger(@PathParam("passangerId") Integer passangerId){        
        return customerEJB.getSchemeSubscriptionsByPassenger(passangerId);
    }
}