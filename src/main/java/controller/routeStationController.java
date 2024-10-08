/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import Client.UserClient;
import entity.RouteStationDTO;
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
@Named(value = "routeStationController")
@RequestScoped
public class routeStationController {
    AdminClient ac;
    Response res;
    List<RouteStationDTO> routeStation;
    GenericType<List<RouteStationDTO>> grouteStation;
    String stationId;
    String routeId;
    /**
     * Creates a new instance of routeStationController
     */
    @PostConstruct
    public void init(){
        ac=new AdminClient();
           routeStation=new ArrayList<>();
           grouteStation=new GenericType<List<RouteStationDTO>>(){};
           loadRouteStations();
    }
    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
     public String addRouteToStation(){
        ac.addStationToRoute(routeId, stationId);
        return "displayRouteStation.jsf?faces-redirect=true";
    }
     public void loadRouteStations(){
          res=ac.getAllRouteStations(Response.class);
        routeStation=res.readEntity(grouteStation);
     }
    public List<RouteStationDTO> getAllRouteStations(){
       return routeStation;
    }
}
