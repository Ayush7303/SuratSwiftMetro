/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import Client.UserClient;
import entity.Notification;
import java.util.ArrayList;
import java.util.Date;
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
@Named(value = "notificationController")
@RequestScoped
public class notificationController {
    AdminClient ac;
    Response res;
    List<Notification> notifications;
    GenericType<List<Notification>> gNotifications;
    Notification existingNotification;
    Notification newNotification;
    /**
     * Creates a new instance of notificationController
     */
    @PostConstruct
    public void init(){
        ac=new AdminClient();
        notifications=new ArrayList<>();
        gNotifications=new GenericType<List<Notification>>(){};
        existingNotification=new Notification();
        newNotification=new Notification();
        loadNotifications();
    }
 public String addNotification(){
     Date curDate=new Date();
     newNotification.setPublishdate(curDate);
        ac.addNotification(newNotification);
        
        return "displayNotification.jsf?faces-redirect=true";
    }
 public void loadNotifications(){
     res=ac.getAllNotifications(Response.class);
        notifications=res.readEntity(gNotifications);
 }
     public List<Notification> getAllNotifications(){
        return notifications;
    }
    public String deleteNotification(String nid){
        ac.deleteNotification(nid);
        return "displayNotification.jsf?faces-redirect=true";
    }
    public String reflectData(String nid){
        res=ac.getNotificationById(Response.class, nid);
        existingNotification=res.readEntity(Notification.class);
        return "updateNotification.jsf";
    }
    public String updateNotification(){
        Date date=new Date();
        existingNotification.setPublishdate(date);
        ac.updateNotification(existingNotification);
        return "displayNotification.jsf?faces-redirect=true";
    }
    public Notification getExistingNotification() {
        return existingNotification;
    }

    public void setExistingNotification(Notification existingNotification) {
        this.existingNotification = existingNotification;
    }

    public Notification getNewNotification() {
        return newNotification;
    }

    public void setNewNotification(Notification newNotification) {
        this.newNotification = newNotification;
    }
    
    
}
