/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.suratswiftmetro.resources;

import ejb.userEJBLocal;
import entity.*;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author jeshanpatel1510
 */

@Path("user")
public class UserResource {
    @EJB userEJBLocal userEJB;
    
    @GET
    @Path("user/login/{username}/{password}")
    @Produces("application/json")
    public User login(@PathParam("username") String username, @PathParam("password") String password) {
        return userEJB.login(username, password);
    }
    
    @POST
    @Path("user/register")
    @Consumes("application/json")
    public void register(PassengerUser passanger) {
        User user = new User();
        user.setUsername(passanger.getUsername());
        user.setDateofbirth(passanger.getDateofbirth());
        user.setEmail(passanger.getEmail());
        user.setGender(passanger.getGender());
        user.setMobilenumber(passanger.getMobilenumber());
        user.setPassword(passanger.getPassword());
        Passanger psg=new Passanger();
        psg.setFullname(passanger.getFullname());
        psg.setProfile(passanger.getProfile());
        psg.setType(passanger.getType());
        psg.setUsername(user);
//        user = passanger.getUsername();
        System.out.println("\nUser : "+user.getUsername()+"\n Passanger : "+passanger.getFullname());
        userEJB.register(user, psg);
    }
    
    @PUT
    @Path("user/resetPassword/{username}/{oldPassword}/{newPassword}")
    public boolean changePassword(@PathParam("username") String username, @PathParam("oldPassword") String oldPassword, @PathParam("newPassword") String newPassword) {
        return userEJB.changePassword(username, oldPassword, newPassword);
    }
    
    @GET
    @Path("passenger/type/get")
    @Produces("application/json")    
    public List<String> getPassengerTypes(){
        return userEJB.getPassengerTypes();
    }
    
    @GET
    @Produces("application/json")
    @Path("user/isAvailable/{username}")
    public boolean isUsernameAvailable(@PathParam("username") String username) {
        return userEJB.isUsernameAvailable(username);
    }
   
    @GET
    @Produces("application/json")
    @Path("user/isEmailAvailable/{email}")
    public boolean isEmailAvailable(@PathParam("email") String email) {
        return userEJB.isEmailAvailable(email);
    }
    
    @GET
    @Path("station/get")
    @Produces("application/json")
//    @RolesAllowed("Admin")
    public List<Station> getAllStations() {
        return userEJB.getAllStations();
    }
 
    @GET
    @Path("route/get")
    @Produces("application/json")
    public List<Route> getAllRoutes() {
        return userEJB.getAllRoutes();
    }
    
    @GET
    @Path("route_station/get")
    @Produces("application/json")
    public List<RouteStationDTO> getAllRouteStations() {
        return userEJB.getAllRouteStation();
    }
    
    @GET
    @Path("stationLink/get")
    @Produces("application/json")
    public List<StationLink> getAllStationLinks() {
        return userEJB.getAllStationLinks();
    }

    @GET
    @Path("interchange/get")
    @Produces("application/json")
    public List<Interchange> getAllInterchanges() {
        return userEJB.getAllInterchanges();
    }

    @GET
    @Path("fare/get")
    @Produces("application/json")
    public List<Fare> getAllFares() {
        return userEJB.getAllFares();
    }

    @GET
    @Path("schedule/get")
    @Produces("application/json")
    public List<Schedule> getAllSchedules() {
        return userEJB.getAllSchedules();
    }
    
    @GET
    @Path("issue/getByUser/{username}")
    @Produces("application/json")
    public List<Issue> getIssuesForUser(@PathParam("username") String username) {
        return userEJB.getIssuesForUser(username);
    }
    
    @GET
    @Path("notification/get")
    @Produces("application/json")
    public List<Notification> getAllNotifications() {
        return userEJB.viewNotifications();
    }
    
    @GET
    @Path("fare/getBySourceDestination/{source}/{destination}")
    @Produces("application/json")
    public Fare getFareBySourceAndDestination(@PathParam("source") String sourcce, @PathParam("destination") String destination) {
        return userEJB.getFareBySourceAndDestination(sourcce, destination);
    }
    
    @GET
    @Path("routeStation/getByStation/{station}")
    @Produces("application/json")
    public Route getRouteByStation(@PathParam("station") String station) {
        return userEJB.getRouteByStation(station);
    }
    
    @PUT
    @Path("update/booking/status/{bookingid}")
    public void updateBookingStatus(@PathParam("bookingid") int bookingId){
        userEJB.updateBookingStatus(bookingId);
    }
    
    @PUT
    @Path("resetpassword")
    @Consumes("application/json")
    public void resetPassword(RestPasswordDTO r) {
        userEJB.resetPassword(r);
    }
    @POST
    @Path("cashier/register")
    @Consumes("application/json")
    public void assignCashierToStation(CollectionStation collectionStation){
        userEJB.assignCashierToStation(collectionStation);
    }  


    @POST
    @Path("issue/add")
    @Consumes("application/json")
    public void addIssue(Issue issue) {
        userEJB.addIssue(issue);
    }


    @GET
    @Path("scheme/get")
    @Produces("application/json")
    public java.util.Collection<Scheme> getAllSchemes(){
        System.out.println("Customer Scheme Resource");
        return userEJB.getallSchemes();
    }
    
    @GET
    @Path("subscriptionById/get/{sid}")
    @Produces("application/json")
    public Subscripton getSubscriptionById(@PathParam("sid") Integer sid) {
        return userEJB.getSubscriptionById(sid);
    }
}
