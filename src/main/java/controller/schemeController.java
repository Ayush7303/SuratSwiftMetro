/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import Client.UserClient;
import entity.Scheme;
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
@Named(value = "schemeController")
@RequestScoped
public class schemeController {
    AdminClient ac;
    Response res;
    List<Scheme> schemes;
    GenericType<List<Scheme>> gSchemes;
    Scheme newScheme;
    Scheme existingScheme;
    /**
     * Creates a new instance of schemeController
     */
    
    @PostConstruct
    public void init(){
        ac=new AdminClient();
        schemes=new ArrayList<>();
        gSchemes=new GenericType<List<Scheme>>(){};
        newScheme=new Scheme();
        existingScheme=new Scheme();
        loadSchemes();
    }
    public String addScheme(){
        ac.addScheme(newScheme);
        return "displayScheme.jsf?faces-redirect=true";
    }
    public void loadSchemes(){
        res=ac.getAllSchemes(Response.class);
        schemes=res.readEntity(gSchemes);
    }
     public List<Scheme> getAllSchemes(){
        return schemes;
    }
    public String deleteScheme(String sid){
        ac.deleteScheme(sid);
        return "displayScheme.jsf?faces-redirect=true";
    }
    public String reflectData(String sid){
        res=ac.getSchemeById(Response.class, sid);
        existingScheme=res.readEntity(Scheme.class);
        return "updateScheme.jsf";
    }   
    public String updateScheme(){
        ac.updateScheme(existingScheme);
        return "displayScheme.jsf?faces-redirect=true";
    }
    public Scheme getNewScheme() {
        return newScheme;
    }

    public void setNewScheme(Scheme newScheme) {
        this.newScheme = newScheme;
    }

    public Scheme getExistingScheme() {
        return existingScheme;
    }

    public void setExistingScheme(Scheme existingScheme) {
        this.existingScheme = existingScheme;
    }
    
    
}
