/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import entity.Subscripton;
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
@Named(value = "subscriptionController")
@RequestScoped
public class subscriptionController {
    AdminClient ac;
    Response res;
    List<Subscripton> subs;
    GenericType<List<Subscripton>> gSubs;
    /**
     * Creates a new instance of subscriptionController
     */
   
    @PostConstruct
    public void init(){
        ac=new AdminClient();
        subs=new ArrayList<>();
        gSubs=new GenericType<List<Subscripton>>(){};
        loadSubs();
    }
    public void loadSubs(){
        res=ac.getAllSubcriptions(Response.class);
        subs=res.readEntity(gSubs);
    }
    public List<Subscripton> getAllSubscriptions(){
        return subs;
    }
    
}
