/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import Client.UserClient;
import entity.Route;
import entity.Station;
import entity.StationLink;
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
@Named(value = "stationLinkController")
@RequestScoped
public class stationLinkController {

    AdminClient ac;
    Response res;
    List<StationLink> stationLinks;
    GenericType<List<StationLink>> gStationLinks;
    StationLink newStationLink;
    String routeId;
    String previousStationId;
    String nextStationId;
    String currentStationId;
    
    StationLink existingStationLink;
    String newRouteId;
    String newPreviousStationId;
    String newNextStationId;
    String newCurrentStationId;

    /**
     * Creates a new instance of stationLinkController
     */
    @PostConstruct
    public void init(){
        ac = new AdminClient();
        stationLinks = new ArrayList<>();
        gStationLinks = new GenericType<List<StationLink>>(){};
        newStationLink=new StationLink();
        existingStationLink=new StationLink();
        loadStationLinks();
    }
     public String addStationLink(){
        res=ac.getRouteById(Response.class, routeId);
        Route route=res.readEntity(Route.class);
        newStationLink.setRouteid(route);
        
        res=ac.getStationById(Response.class, previousStationId);
        Station previous=res.readEntity(Station.class);
        newStationLink.setPreviousstation(previous);
        
        res=ac.getStationById(Response.class, currentStationId);
        Station current=res.readEntity(Station.class);
        newStationLink.setCurrentstation(current);
        
        res=ac.getStationById(Response.class, nextStationId);
        Station next=res.readEntity(Station.class);
        newStationLink.setNextstation(next);
        
        ac.addStationLink(newStationLink);
        return "displayStationLink.jsf?faces-redirect=true";
    }
     public void loadStationLinks(){
         res=ac.getAllStationLinks(Response.class);
        stationLinks=res.readEntity(gStationLinks);
     }
    public List<StationLink> getAllStationLinks(){
        return stationLinks;
    }
    public String deleteStationLink(String slid){
        ac.deleteStationLink(slid);
        return "displayStationLink.jsf?faces-redirect=true";
    }
    public String reflectData(String slid){
        res=ac.getStationLinkById(Response.class, slid);
        existingStationLink=res.readEntity(StationLink.class);
        newRouteId=existingStationLink.getRouteid().getRouteid().toString();
        newPreviousStationId=existingStationLink.getPreviousstation().getStationid().toString();
        newCurrentStationId=existingStationLink.getCurrentstation().getStationid().toString();
        newNextStationId=existingStationLink.getNextstation().getStationid().toString();
        return "updateStationLink.jsf";
    }
    public String updateStationLink(){
         res=ac.getRouteById(Response.class, newRouteId);
        Route route=res.readEntity(Route.class);
        existingStationLink.setRouteid(route);
        
        res=ac.getStationById(Response.class, newPreviousStationId);
        Station previous=res.readEntity(Station.class);
        existingStationLink.setPreviousstation(previous);
        
        res=ac.getStationById(Response.class, newCurrentStationId);
        Station current=res.readEntity(Station.class);
        existingStationLink.setCurrentstation(current);
        
        res=ac.getStationById(Response.class, newNextStationId);
        Station next=res.readEntity(Station.class);
        existingStationLink.setNextstation(next);
        
        ac.updateStationLink(existingStationLink);
        return "displayStationLink.jsf?faces-redirect=true";
    }
    
    public StationLink getNewStationLink() {
        return newStationLink;
    }

    public void setNewStationLink(StationLink newStationLink) {
        this.newStationLink = newStationLink;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getPreviousStationId() {
        return previousStationId;
    }

    public void setPreviousStationId(String previousStationId) {
        this.previousStationId = previousStationId;
    }

    public String getNextStationId() {
        return nextStationId;
    }

    public void setNextStationId(String nextStationId) {
        this.nextStationId = nextStationId;
    }

    public String getCurrentStationId() {
        return currentStationId;
    }

    public void setCurrentStationId(String currentStationId) {
        this.currentStationId = currentStationId;
    }

    public StationLink getExistingStationLink() {
        return existingStationLink;
    }

    public void setExistingStationLink(StationLink existingStationLink) {
        this.existingStationLink = existingStationLink;
    }

    public String getNewRouteId() {
        return newRouteId;
    }

    public void setNewRouteId(String newRouteId) {
        this.newRouteId = newRouteId;
    }

    public String getNewPreviousStationId() {
        return newPreviousStationId;
    }

    public void setNewPreviousStationId(String newPreviousStationId) {
        this.newPreviousStationId = newPreviousStationId;
    }

    public String getNewNextStationId() {
        return newNextStationId;
    }

    public void setNewNextStationId(String newNextStationId) {
        this.newNextStationId = newNextStationId;
    }

    public String getNewCurrentStationId() {
        return newCurrentStationId;
    }

    public void setNewCurrentStationId(String newCurrentStationId) {
        this.newCurrentStationId = newCurrentStationId;
    }
    

}
