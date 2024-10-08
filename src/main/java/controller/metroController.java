/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import Client.UserClient;
import entity.Metro;
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
@Named(value = "metroController")
@RequestScoped
public class metroController {
    AdminClient ac;
    Metro newMetro;
    Response res;
    Metro existingMetro;
    List<Metro> metros;
    GenericType<List<Metro>> gMetros;
    /**
     * Creates a new instance of metroController
     */
   
    @PostConstruct
    public void init(){
        ac=new AdminClient();
        newMetro=new Metro();
        existingMetro=new Metro();
        metros=new ArrayList<>();
        gMetros=new GenericType<List<Metro>>(){};
        loadMetro();
    }
    
     public String addMetro(){
        ac.addMetro(newMetro);
        return "displayMetro.jsf?faces-redirect=true";
    }
     public void loadMetro(){
         res=ac.getAllMetros(Response.class);
        metros=res.readEntity(gMetros);
     }
     public List<Metro> getAllMetros(){
        return metros;
    }
    public String deleteMetro(String mid){
        ac.deleteMetro(mid);
        return "displayMetro.jsf?faces-redirect=true";
    }
    public String reflectData(String mid){
        res=ac.getMetroById(Response.class, mid);
        existingMetro=res.readEntity(Metro.class);
        return "updateMetro.jsf";
    }
    public String updateMetro(){
        ac.updateMetro(existingMetro);
        return "displayMetro.jsf?faces-redirect=true";
    }
    public Metro getNewMetro() {
        return newMetro;
    }

    public void setNewMetro(Metro newMetro) {
        this.newMetro = newMetro;
    }

    public Metro getExistingMetro() {
        return existingMetro;
    }

    public void setExistingMetro(Metro existingMetro) {
        this.existingMetro = existingMetro;
    }
    
}
