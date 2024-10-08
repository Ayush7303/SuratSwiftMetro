/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import Client.UserClient;
import entity.Metro;
import entity.Route;
import entity.Schedule;
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
@Named(value = "scheduleController")
@RequestScoped
public class scheduleController {
    AdminClient ac;
    Response res;
    List<Schedule> schedules;
    GenericType<List<Schedule>> gSchedules;
    Schedule newSchedule;
    String routeId;
    String metroId;
    
    Schedule existingSchedule;
    String newRouteId;
    String newMetroId;
    /**
     * Creates a new instance of scheduleController
     */
    
    @PostConstruct
    public void init(){
        ac=new AdminClient();
        schedules=new ArrayList<>();
        gSchedules=new GenericType<List<Schedule>>(){};
        newSchedule=new Schedule();
        existingSchedule=new Schedule();
        loadSchedules();
    }
 public String addSchedule(){
        res=ac.getRouteById(Response.class, routeId);
        Route route=res.readEntity(Route.class);
        newSchedule.setRouteid(route);
        res=ac.getMetroById(Response.class, metroId);
        Metro metro=res.readEntity(Metro.class);
        newSchedule.setMetroid(metro);
        ac.addSchedule(newSchedule);
        return "displaySchedule.jsf?faces-redirect=true";
    }
 public void loadSchedules(){
     res=ac.getAllSchedules(Response.class);
        schedules=res.readEntity(gSchedules);
 }
    public List<Schedule> getAllSchedules(){
        return schedules;
    }
    public String deleteSchedule(String sid){
        ac.deleteSchedule(sid);
        return "displaySchedule.jsf?faces-redirect=true";
    }
    public String reflectData(String sid){
        res=ac.getScheduleById(Response.class, sid);
        existingSchedule=res.readEntity(Schedule.class);
        newMetroId=existingSchedule.getMetroid().getMetroid().toString();
        newRouteId=existingSchedule.getRouteid().getRouteid().toString();
        return "updateSchedule.jsf";
    }
    public String updateSchedule(){
         res=ac.getRouteById(Response.class, newRouteId);
        Route route=res.readEntity(Route.class);
        existingSchedule.setRouteid(route);
        res=ac.getMetroById(Response.class, newMetroId);
        Metro metro=res.readEntity(Metro.class);
        existingSchedule.setMetroid(metro);
        ac.updateSchedule(existingSchedule);
        return "displaySchedule.jsf?faces-redirect=true";
    }
    public Schedule getNewSchedule() {
        return newSchedule;
    }

    public void setNewSchedule(Schedule newSchedule) {
        this.newSchedule = newSchedule;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getMetroId() {
        return metroId;
    }

    public void setMetroId(String metroId) {
        this.metroId = metroId;
    }

    public Schedule getExistingSchedule() {
        return existingSchedule;
    }

    public void setExistingSchedule(Schedule existingSchedule) {
        this.existingSchedule = existingSchedule;
    }

    public String getNewRouteId() {
        return newRouteId;
    }

    public void setNewRouteId(String newRouteId) {
        this.newRouteId = newRouteId;
    }

    public String getNewMetroId() {
        return newMetroId;
    }

    public void setNewMetroId(String newMetroId) {
        this.newMetroId = newMetroId;
    }
    
    
}
