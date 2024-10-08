/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import entity.Passanger;
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
@Named(value = "passengerController")
@RequestScoped
public class passengerController {
    AdminClient ac;
    Response res;
    List<Passanger> passengers;
    GenericType<List<Passanger>> gPassengers;
    /**
     * Creates a new instance of passengerController
     */
    
    @PostConstruct
    public void init(){
        ac=new AdminClient();
        passengers=new ArrayList<>();
        gPassengers=new GenericType<List<Passanger>>(){};
        loadPassengers();
    }
    public void loadPassengers(){
         res=ac.getAllPassangers(Response.class);
        passengers=res.readEntity(gPassengers);
    }
    public List<Passanger> getAllPassengers(){
       return passengers;
    }
}
