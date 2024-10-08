/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import Client.UserClient;
import entity.Fare;
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
@Named(value = "fareController")
@RequestScoped
public class fareController {

    AdminClient ac;
//    UserClient uc;
    Response res;
    List<Fare> fares;
    GenericType<List<Fare>> gFares;
    String source, destination;
    Fare newFare;
    Fare existingFare;
    String newSource, newDestination;

    /**
     * Creates a new instance of fareController
     */
//    public fareController() {
//        ac=new AdminClient();
//        uc=new UserClient();
//        fares=new ArrayList<>();
//        gFares=new GenericType<List<Fare>>(){};
//        newFare=new Fare();
//        existingFare=new Fare();
//    }
    @PostConstruct
    public void init() {
        ac = new AdminClient();
//        uc = new UserClient();
        fares = new ArrayList<>();
        gFares = new GenericType<List<Fare>>() {};
        newFare = new Fare();
        existingFare = new Fare();
        loadFares();
    }

    public void loadFares() {
        Response res = ac.getAllFares(Response.class);
        fares = res.readEntity(gFares);
    }

    public List<Fare> getAllFares() {
        return fares;
    }

    public String addFare() {
        res = ac.getStationById(Response.class, source);
        Station s = res.readEntity(Station.class);
        newFare.setSource(s);

        res = ac.getStationById(Response.class, destination);
        Station d = res.readEntity(Station.class);
        newFare.setDestination(d);

        ac.addFare(newFare);
        return "displayFare.jsf?faces-redirect=true";
    }
//    public List<Fare> getAllFares(){
//        res=uc.getAllFares(Response.class);
//        fares=res.readEntity(gFares);
//        return fares;
//    }

    public String deleteFare(String fid) {
        ac.deleteFare(fid);
        return "displayFare.jsf?faces-redirect=true";
    }

    public String reflectData(String fid) {
        res = ac.getFareById(Response.class, fid);
        existingFare = res.readEntity(Fare.class);
        newSource = existingFare.getSource().getStationid().toString();
        newDestination = existingFare.getDestination().getStationid().toString();
        return "updateFare.jsf";
    }

    public String updateFare() {
        res = ac.getStationById(Response.class, newSource);
        Station s = res.readEntity(Station.class);
        existingFare.setSource(s);

        res = ac.getStationById(Response.class, newDestination);
        Station d = res.readEntity(Station.class);
        existingFare.setDestination(d);
        ac.updateFare(existingFare);
        return "displayFare.jsf?faces-redirect=true";
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Fare getNewFare() {
        return newFare;
    }

    public void setNewFare(Fare newFare) {
        this.newFare = newFare;
    }

    public Fare getExistingFare() {
        return existingFare;
    }

    public void setExistingFare(Fare existingFare) {
        this.existingFare = existingFare;
    }

    public String getNewSource() {
        return newSource;
    }

    public void setNewSource(String newSource) {
        this.newSource = newSource;
    }

    public String getNewDestination() {
        return newDestination;
    }

    public void setNewDestination(String newDestination) {
        this.newDestination = newDestination;
    }

}
