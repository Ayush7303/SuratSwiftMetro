/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import Client.UserClient;
import entity.SearchFromTo;
import entity.Station;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.primefaces.PrimeFaces;

/**
 *
 * @author ayush
 */
@Named(value = "stationController")
@RequestScoped
public class stationController {
    AdminClient ac;
    Response res;
    List<Station> stations;
    GenericType<List<Station>> gstations;
    Station existingStation;
    Station newStation;
    String from,to;
    double fare;
    int stationCount;
    /**
     * Creates a new instance of stationController
     */
   
    @PostConstruct
    public void init(){
        stations=new ArrayList<>();
        gstations=new GenericType<List<Station>>(){};
        newStation=new Station();
        ac=new AdminClient();
        existingStation=new Station();
        loadStations();
    }
    public SearchFromTo searchFare(){
        res=ac.searchByFromTo(Response.class, from, to);
        SearchFromTo s=res.readEntity(SearchFromTo.class);
        fare= s.getFare().getFare();
        stationCount=s.getStation();
        System.out.println(fare);
        return s;
    }
    public void loadStations(){
        res=ac.getAllStations(Response.class);
        stations=res.readEntity(gstations);
    }
    public List<Station> getAllStations(){
        return stations;
    }
    public String deleteStation(String sid){
        ac.deleteStation(sid);
        return "displayStation.jsf?faces-redirect=true";
    }
    public String reflectData(String sid){
        res=ac.getStationById(Response.class, sid);
        existingStation=res.readEntity(Station.class);
        return "updateStation.jsf";
    }
    public String updateStation(){
        ac.updateStation(existingStation);
        return "displayStation.jsf?faces-redirect=true";
    }
    public Station getNewStation() {
        return newStation;
    }

    public Station getExistingStation() {
        return existingStation;
    }

    public void setExistingStation(Station existingStation) {
        this.existingStation = existingStation;
    }

    public void setNewStation(Station newStation) {
        this.newStation = newStation;
    }

    public String addStation(){
        ac.addStation(newStation);
        return "displayStation.jsf?faces-redirect=true";
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getFare() {
                System.out.println(fare);
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public int getStationCount() {
        return stationCount;
    }

    public void setStationCount(int stationCount) {
        this.stationCount = stationCount;
    }
  

    
}
