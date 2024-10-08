/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.suratswiftmetro.resources;

import ejb.adminEJBLocal;
import ejb.userEJBLocal;
import entity.*;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author jeshanpatel1510
 */
@DeclareRoles({"Admin"})
@RolesAllowed({"Admin"})
@Path("admin")
public class AdminResource {
    @EJB adminEJBLocal adminEJB;
    @EJB userEJBLocal userEJB;
    
    @POST
    @Path("station/add")
    @Consumes("application/json")
    public void addStation(Station station) {
        adminEJB.addStation(station);
    }
    
    @PUT
    @Path("station/update")
    @Consumes("application/json")
    public void updateStation(Station station) {
        adminEJB.updateStation(station);
    }
    
    @DELETE
    @Path("station/delete/{stationId}")
    public void deleteStation(@PathParam("stationId") Integer stationId) {
        adminEJB.deleteStation(stationId);
    }
    
    @GET
    @Path("station/get/{stationId}")
    @Produces("application/json")
    public Station getStationById(@PathParam("stationId") Integer stationId) {
        return adminEJB.getStationById(stationId);
    }
    
    @POST
    @Path("route/add")
    @Consumes("application/json")
    public void addRoute(Route route) {
        adminEJB.addRoute(route);
    }
    
    @PUT
    @Path("route/update")
    @Consumes("application/json")
    public void updateRoute(Route route) {
        adminEJB.updateRoute(route);
    }
    
    @DELETE
    @Path("route/delete/{routeId}")
    public void deleteRoute(@PathParam("routeId") Integer routeId) {
        adminEJB.deleteRoute(routeId);
    }
    
    @GET
    @Path("route/get/{routeId}")
    @Produces("application/json")
    public Route getRouteById(@PathParam("routeId") Integer routeId) {
        return adminEJB.getRouteById(routeId);
    }
    
    
    @POST
    @Path("stationLink/add")
    @Consumes("application/json")
    public void addStationLink(StationLink stationLink) {
        adminEJB.addStationLink(stationLink);
    }
    
    @PUT
    @Path("stationLink/update")
    @Consumes("application/json")
    public void updateStationLink(StationLink stationLink) {
        adminEJB.updateStationLink(stationLink);
    }
    
    @DELETE
    @Path("stationLink/delete/{stationLinkId}")
    public void deleteStationLink(@PathParam("stationLinkId") Integer stationLinkId) {
        adminEJB.deleteStationLink(stationLinkId);      
    }
    
    @GET
    @Path("stationLink/get/{stationLinkId}")
    @Produces("application/json")
    public StationLink getStationLinkById(@PathParam("stationLinkId") Integer stationLinkId) {
        return adminEJB.getStationLinkById(stationLinkId);
    }
    
    
    @POST
    @Path("interchange/add")
    @Consumes("application/json")
    public void addInterchange(Interchange interchange) {
        adminEJB.addInterchange(interchange);
    }
    
    @DELETE
    @Path("interchange/delete/{interchangeId}")
    public void deleteInterchange(@PathParam("interchangeId") Integer interchangeId) {
        adminEJB.deleteInterchange(interchangeId);
    }
    
    @PUT
    @Path("interchange/update")
    @Consumes("application/json")
    public void updateInterchange(Interchange interchange) {
        adminEJB.updateInterchange(interchange);
    }
    
    @GET
    @Path("interchange/get/{interchangeId}")
    @Produces("application/json")
    public Interchange getInterchangeById(@PathParam("interchangeId") Integer interchangeId) {
        return adminEJB.getInterchangeById(interchangeId);
    }
    
    @GET
    @Path("schemes/get")
    @Produces("application/json")
    public List<Scheme> getAllSchemes() {
        return adminEJB.getAllSchemes();
    }
    
    @POST
    @Path("schemes/add")
    @Consumes("application/json")
    public void addScheme(Scheme scheme) {
        adminEJB.addScheme(scheme);
    }
    
    @PUT
    @Path("schemes/update")
    @Consumes("application/json")
    public void updateScheme(Scheme scheme) {
        adminEJB.updateScheme(scheme);
    }
    
    @DELETE
    @Path("schemes/delete/{schemeId}")
    public void deleteScheme(@PathParam("schemeId") Integer schemeId) {
        adminEJB.deleteScheme(schemeId);
    }
    
    @GET
    @Path("schemes/get/{schemeId}")
    @Produces("application/json")
    public Scheme getSchemeById(@PathParam("schemeId") Integer schemeId) {
        return adminEJB.getSchemeById(schemeId);
    }
    
    
    @POST
    @Path("fare/add")
    @Consumes("application/json")
    public void addFare(Fare fare) {
        adminEJB.addFare(fare);
    }
    
    @PUT
    @Path("fare/update")
    @Consumes("application/json")
    public void updateFare(Fare fare) {
        adminEJB.updateFare(fare);
    }
    
    @DELETE
    @Path("fare/delete/{fareId}")
    public void deleteFare(@PathParam("fareId") Integer fareId) {
        adminEJB.deleteFare(fareId);
    }
    
    @GET
    @Path("fare/get/{fareId}")
    @Produces("application/json")
    public Fare getFareById(@PathParam("fareId") Integer fareID) {
        return adminEJB.getFareById(fareID);
    }
    
    
    @POST
    @Path("schedule/add")
    @Consumes("application/json")
    public void addSchedule(Schedule schedule) {
        adminEJB.addSchedule(schedule);
    }
    
    @PUT
    @Path("schedule/update")
    @Consumes("application/json")
    public void updateSchedule(Schedule schedule) {
        adminEJB.updateSchedule(schedule);
    }
    
    @PUT
    @Path("notification/update")
    @Consumes("application/json")
    public void updateNotification(Notification notification) {
        adminEJB.updateNotification(notification);
    }
    
    @DELETE
    @Path("schedule/delete/{scheduleId}")
    public void deleteSchedule(@PathParam("scheduleId") Integer scheduleId) {
        adminEJB.deleteSchedule(scheduleId);
    }
    
    @GET
    @Path("schedule/get/{scheduleId}")
    @Produces("application/json")
    public Schedule getScheduleById(@PathParam("scheduleId") Integer scheduleId) {
        return adminEJB.getScheduleById(scheduleId);
    }
    
    @GET
    @Path("metro/get")
    @Produces("application/json")
    public List<Metro> getAllMetros() {
        return adminEJB.getAllMetros();
    }
    
    @POST
    @Path("metro/add")
    @Consumes("application/json")
    public void addMetro(Metro metro) {
        adminEJB.addMetro(metro);
    }
    
    @PUT
    @Path("metro/update")
    @Consumes("application/json")
    public void updateMetro(Metro metro) {
        adminEJB.updateMetro(metro);
    }
    
    @DELETE
    @Path("metro/delete/{metroId}")
    public void deleteMetro(@PathParam("metroId") Integer metroId) {
        adminEJB.deleteMetro(metroId);
    }
    
    @GET
    @Path("metro/get/{metroId}")
    @Produces("application/json")
    public Metro getMetroById(@PathParam("metroId") Integer metroId) {
        return adminEJB.getMetroById(metroId);
    }
    
    @POST
    @Path("notification/add")
    @Consumes("application/json")
    public void addNotification(Notification notification) {
        adminEJB.addNotification(notification);
    }
    
    @DELETE
    @Path("notification/delete/{notificationId}")
    public void deleteNotification(@PathParam("notificationId") Integer notificationId) {
        adminEJB.deleteNotification(notificationId);
    }
    
    @GET
    @Path("notification/get/{notificationId}")
    @Produces("application/json")
    public Notification getNotificationById(@PathParam("notificationId") Integer notificationId) {
        return adminEJB.getNotificationById(notificationId);
    }
    
    @GET
    @Path("isuue/get")
    @Produces("application/json")
    public List<Issue> getAllIssues() {
        return adminEJB.getAllIssues();
    }
    
    @POST
    @Path("issue/resolve/{issueId}")
    public void resolveIssue(@PathParam("issueId") Integer issueId) {
        System.out.println("Res");
        adminEJB.resolveIssue(issueId);
    }
    
    @POST
    @Path("schedule/assignSchedule")
    public void assignMetroToSchedule(Schedule schedule) {
        Metro metro = schedule.getMetroid();
        adminEJB.assignMetroToSchedule(metro, schedule);
    }
    
    @DELETE
    @Path("schedule/removeSchedule")
    public void removeMetroFromSchedule(Schedule schedule) {
        Metro metro = schedule.getMetroid();
        adminEJB.removeMetroFromSchedule(metro, schedule);
    }
    
    @GET
    @Path("schedule/getMetro/{scheduleid}")
    @Produces("application/json")
    public Metro getMetroForSchedule(Integer scheduleId) {
        return adminEJB.getMetroForSchedule(scheduleId);
    }
    
    @GET
    @Path("schedule/getSchedule/{metroid}")
    @Produces("application/json")
    public Schedule getScheduleForMetro(Integer metroId) {
        return adminEJB.getScheduleForMetro(metroId);
    }
    
    @GET
    @Path("booking/get")
    @Produces("application/json")
    public List<Booking> getAllBookings() {
        return adminEJB.getAllBookings();
    }
    
    @GET
    @Path("collection/get")
    @Produces("application/json")
    public List<entity.Collection> getTotalCollections() {
        return adminEJB.getTotalCollections();
    }
    
    @GET
    @Path("collectionStation/get")
    @Produces("application/json")
    public List<CollectionStation> getAllCollectionStations() {
        return adminEJB.getAllCollectionStations();
    }
    
    @GET
    @Path("passanger/get")
    @Produces("application/json")
    public List<Passanger> getAllPassangers() {
        return adminEJB.getAllPassangers();
    }
    
    @GET
    @Path("user/get")
    @Produces("application/json")
    public List<User> getAllUsers() {
        return adminEJB.getAllUsers();
    }
    
    @GET
    @Path("subscription/get")
    @Produces("application/json")
    public List<Subscripton> getAllSubcriptions() {
        return adminEJB.getAllSubcriptions();
    }
    
    @GET
    @Path("role/get")
    @Produces("application/json")
    public List<Role> getAllRoles() {
        return adminEJB.getAllRoles();
    }
    
    @POST
    @Path("role/add")
    @Consumes("application/json")
    public void addRole(Role role) {
        adminEJB.addRole(role);
    }
    
    @PUT
    @Path("role/update")
    @Consumes("application/json")
    public void updateRole(Role role) {
        adminEJB.updateRole(role);
    }
    
    @DELETE
    @Path("role/delete/{roleId}")
    public void deleteRole(@PathParam("roleId") Integer roleId) {
        adminEJB.deleteRole(roleId);
    }
    
    @GET
    @Path("role/get/{roleId}")
    @Produces("application/json")
    public Role getRoleById(@PathParam("roleId") Integer roleId) {
        return adminEJB.getRoleById(roleId);
    }
    
    @GET
    @Path("payment")
    @Produces("application/json")
    public List<Payment> getAllPayments(){
        return adminEJB.getAllPayments();
    }
    
    @POST
    @Path("route_station/add/{routeid}/{stationid}")
    public void addStationToRoute(@PathParam("routeid") Integer routeid, @PathParam("stationid") Integer stationid) {
        adminEJB.addStationToRoute(stationid, routeid);
    }
    @GET
    @Path("searchFareStationLink/get/{from}/{to}")
    @Produces("application/json")
    public SearchFromTo searchByFromTo(@PathParam("from") Integer from,@PathParam("to") Integer to) {
        return adminEJB.searchByFromTo(from, to);
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
    
//    @GET
//    @Path("issue/getByUser/{userId}")
//    @Produces("application/json")
//    public List<Issue> getIssuesForUser(@PathParam("userId") Integer userId) {
//        return userEJB.getIssuesForUser(userId);
//    }
    
    @GET
    @Path("notification/get")
    @Produces("application/json")
    public List<Notification> getAllNotifications() {
        return userEJB.viewNotifications();
    }
}
