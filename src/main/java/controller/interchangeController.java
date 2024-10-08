/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import Client.UserClient;
import entity.Interchange;
import entity.Route;
import entity.Station;
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
@Named(value = "interchangeController")
@RequestScoped
public class interchangeController {
    AdminClient ac;
    Response res;
    List<Interchange> interchanges;
    GenericType<List<Interchange>> gInterchanges;
    Interchange newInterchange;
    String stationId;
    String route1Id;
    String route2Id;
    
    Interchange existingInterchange;
    String newStationId;
    String newRoute1Id;
    String newRoute2Id;
    
    /**
     * Creates a new instance of interchangeController
     */
    @PostConstruct
    public void init(){
        ac=new AdminClient();
        interchanges=new ArrayList<>();
        gInterchanges=new GenericType<List<Interchange>>(){};
        newInterchange=new Interchange();
        existingInterchange=new Interchange();
        loadInterchange();
    }

     public String addInterchange(){
        res=ac.getStationById(Response.class, stationId);
        Station station=res.readEntity(Station.class);
        newInterchange.setStationid(station);
        
        res=ac.getRouteById(Response.class, route1Id);
        Route route1=res.readEntity(Route.class);
        newInterchange.setRouteid1(route1);
        
        res=ac.getRouteById(Response.class, route2Id);
        Route route2=res.readEntity(Route.class);
        newInterchange.setRouteid2(route2);
        
        ac.addInterchange(newInterchange);
        return "displayInterchange.jsf?faces-redirect=true";
    }
     public void loadInterchange(){
         res=ac.getAllInterchanges(Response.class);
        interchanges=res.readEntity(gInterchanges);
     }
    public List<Interchange> getAllInterchange(){
        return interchanges;
    }
    public String deleteInterchange(String iid){
        ac.deleteInterchange(iid);
        return "displayInterchange.jsf?faces-redirect=true";
    }
    public String reflectData(String iid){
        res=ac.getInterchangeById(Response.class, iid);
        existingInterchange=res.readEntity(Interchange.class);
        newStationId=existingInterchange.getStationid().getStationid().toString();
        newRoute1Id=existingInterchange.getRouteid1().getRouteid().toString();
        newRoute2Id=existingInterchange.getRouteid2().getRouteid().toString();
        return "updateInterchange.jsf";
    }
    public String updateInterchange(){
      res=ac.getStationById(Response.class, newStationId);
        Station station=res.readEntity(Station.class);
        existingInterchange.setStationid(station);
        
        res=ac.getRouteById(Response.class, newRoute1Id);
        Route route1=res.readEntity(Route.class);
        existingInterchange.setRouteid1(route1);
        
        res=ac.getRouteById(Response.class, newRoute2Id);
        Route route2=res.readEntity(Route.class);
        existingInterchange.setRouteid2(route2);
        
        ac.updateInterchange(existingInterchange);
        return "displayInterchange.jsf?faces-redirect=true";
    }
    
    public Interchange getNewInterchange() {
        return newInterchange;
    }

    public void setNewInterchange(Interchange newInterchange) {
        this.newInterchange = newInterchange;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getRoute1Id() {
        return route1Id;
    }

    public void setRoute1Id(String route1Id) {
        this.route1Id = route1Id;
    }

    public String getRoute2Id() {
        return route2Id;
    }

    public void setRoute2Id(String route2Id) {
        this.route2Id = route2Id;
    }

    public Interchange getExistingInterchange() {
        return existingInterchange;
    }

    public void setExistingInterchange(Interchange existingInterchange) {
        this.existingInterchange = existingInterchange;
    }

    public String getNewStationId() {
        return newStationId;
    }

    public void setNewStationId(String newStationId) {
        this.newStationId = newStationId;
    }

    public String getNewRoute1Id() {
        return newRoute1Id;
    }

    public void setNewRoute1Id(String newRoute1Id) {
        this.newRoute1Id = newRoute1Id;
    }

    public String getNewRoute2Id() {
        return newRoute2Id;
    }

    public void setNewRoute2Id(String newRoute2Id) {
        this.newRoute2Id = newRoute2Id;
    }
    
    
}
