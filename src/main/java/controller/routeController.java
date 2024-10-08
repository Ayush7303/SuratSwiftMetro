/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import Client.UserClient;
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
@Named(value = "routeController")
@RequestScoped
public class routeController {
    AdminClient ac;
    Response res;
    List<Route> routes;
    GenericType<List<Route>> groutes;
    Route existingRoute;
    Route newRoute;
    String startStation;
    String endStation;
    String newStartStation;
    String newEndStation;
    /**
     * Creates a new instance of routeController
     */
    @PostConstruct
    public void init(){
        ac=new AdminClient();
        routes=new ArrayList<>();
        groutes=new GenericType<List<Route>>(){};
        existingRoute=new Route();
        newRoute=new Route();
        loadRoutes();
    }
    public String addRoute(){
        res=ac.getStationById(Response.class, startStation);
        Station start=res.readEntity(Station.class);
        newRoute.setStartstation(start);
        res=ac.getStationById(Response.class, endStation);
        Station end=res.readEntity(Station.class);
        newRoute.setEndstation(end);
        ac.addRoute(newRoute);
        return "displayRoute.jsf?faces-redirect=true";
    }
    public void loadRoutes(){
         res=ac.getAllRoutes(Response.class);
        routes=res.readEntity(groutes);
    }
    public List<Route> getAllRoutes(){
       return routes;
    }
    public String deleteRoute(String rid){
        ac.deleteRoute(rid);
        return "displayRoute.jsf?faces-redirect=true";
    }
    public String reflectData(String rid){
        res=ac.getRouteById(Response.class, rid);
        existingRoute=res.readEntity(Route.class);
        newStartStation=existingRoute.getStartstation().getStationid().toString();
        newEndStation=existingRoute.getEndstation().getStationid().toString();
        return "updateRoute.jsf";
    }
    public String updateRoute(){
        res=ac.getStationById(Response.class, newStartStation);
        Station start=res.readEntity(Station.class);
        existingRoute.setStartstation(start);
        res=ac.getStationById(Response.class, newEndStation);
        Station end=res.readEntity(Station.class);
        existingRoute.setEndstation(end);
        ac.updateRoute(existingRoute);
        return "displayRoute.jsf?faces-redirect=true";
    }
    public Route getExistingRoute() {
        return existingRoute;
    }

    public void setExistingRoute(Route existingRoute) {
        this.existingRoute = existingRoute;
    }

    public Route getNewRoute() {
        return newRoute;
    }

    public void setNewRoute(Route newRoute) {
        this.newRoute = newRoute;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getNewStartStation() {
        return newStartStation;
    }

    public void setNewStartStation(String newStartStation) {
        this.newStartStation = newStartStation;
    }

    public String getNewEndStation() {
        return newEndStation;
    }

    public void setNewEndStation(String newEndStation) {
        this.newEndStation = newEndStation;
    }
    
}
