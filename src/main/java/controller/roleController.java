/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import Client.UserClient;
import entity.Role;
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
@Named(value = "roleController")
@RequestScoped
public class roleController {
    AdminClient ac;
    UserClient uc;
    Response res;
    List<Role> roles;
    GenericType<List<Role>> groles;
    Role newRole;
    String username;
    Role existingRole;
    String newUsername;
    
    /**
     * Creates a new instance of roleController
     */
   
    @PostConstruct
    public void init(){
         ac=new AdminClient();
        uc=new UserClient();
        roles=new ArrayList<>();
        groles=new GenericType<List<Role>>(){};
        newRole=new Role();
        existingRole=new Role();
        loadRoles();
    }
    public String addRole(){
        res=uc.isUsernameAvailable(Response.class, username);
        User user=res.readEntity(User.class);
        newRole.setUsername(user);
        ac.addRole(newRole);
        return "displayRole.jsf?faces-redirect=true";
    }
    public void loadRoles(){
        res=ac.getAllRoles(Response.class);
        roles=res.readEntity(groles);
        
    }
    public List<Role> getAllRoles(){
        return roles;
    }
    public String deleteRole(String rid){
        ac.deleteRole(rid);
        return "displayRole.jsf?faces-redirect=true";
    }
    public String reflectData(String rid){
        res=ac.getRoleById(Response.class, rid);
        existingRole=res.readEntity(Role.class);
        newUsername=existingRole.getUsername().getUsername();
        return "updateRole.jsf";
    }
    public String updateRole(){
         res=uc.isUsernameAvailable(Response.class, newUsername);
            User user=res.readEntity(User.class);
        existingRole.setUsername(user);
        ac.updateRole(existingRole);
        return "displayRole.jsf?faces-redirect=true";
    }
    public Role getNewRole() {
        return newRole;
    }

    public void setNewRole(Role newRole) {
        this.newRole = newRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getExistingRole() {
        return existingRole;
    }

    public void setExistingRole(Role existingRole) {
        this.existingRole = existingRole;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }
    
    
}
