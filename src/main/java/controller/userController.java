/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import entity.User;
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
@Named(value = "userController")
@RequestScoped
public class userController {
    AdminClient ac;
    Response res;
    List<User> users;
    GenericType<List<User>> gUsers;
    /**
     * Creates a new instance of userController
     */
    @PostConstruct
    public void init(){
        ac=new AdminClient();
        users=new ArrayList<>();
        gUsers=new GenericType<List<User>>(){};
        loadUsers();
    }
    public void loadUsers(){
        res=ac.getAllUsers(Response.class);
        users=res.readEntity(gUsers);
    }
    public List<User> getAllUsers(){
        return users;
    }
}

