/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import entity.Booking;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ayush
 */
@Named(value = "bookingController")
@RequestScoped
public class bookingController {
    AdminClient ac;
    Response res;
    List<Booking> bookings;
    GenericType<List<Booking>> gBookings;
    /**
     * Creates a new instance of bookingController
     */
    
    @PostConstruct
    public void init(){
        ac=new AdminClient();
        bookings=new ArrayList<>();
        gBookings=new GenericType<List<Booking>>(){};
        loadBookings();
    }
    public void loadBookings(){
        res=ac.getAllBookings(Response.class);
        bookings=res.readEntity(gBookings);
    }
    public List<Booking> getAllBookings(){
        return bookings;
    }
}
