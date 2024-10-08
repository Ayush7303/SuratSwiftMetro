/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.Booking;
import entity.Collection;
import entity.CollectionStation;
import entity.Fare;
import entity.Interchange;
import entity.Issue;
import entity.Metro;
import entity.Notification;
import entity.Passanger;
import entity.Payment;
import entity.Role;
import entity.Route;
import entity.Schedule;
import entity.Scheme;
import entity.SearchFromTo;
import entity.Station;
import entity.StationLink;
import entity.Subscripton;
import entity.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ayush
 */
@Local
public interface adminEJBLocal {
    void addStation(Station station);
    void updateStation(Station station);
    void deleteStation(Integer stationId);
    Station getStationById(Integer stationId);
    
    void addRoute(Route route);
    void updateRoute(Route route);
    void deleteRoute(Integer routeId);
    Route getRouteById(Integer routeId);
    
    void addStationToRoute(Integer station, Integer route);
    
    void addStationLink(StationLink stationLink);
    void updateStationLink(StationLink stationLink);
    void deleteStationLink(Integer stationLinkId);
    StationLink getStationLinkById(Integer stationLinkId);
    
    void addInterchange(Interchange interchange);
    void deleteInterchange(Integer interchangeId);
    void updateInterchange(Interchange interchange);
    Interchange getInterchangeById(Integer interchangeId);
    
    void addScheme(Scheme scheme);
    void updateScheme(Scheme scheme);
    void deleteScheme(Integer schemeId);
    Scheme getSchemeById(Integer schemeId);
    List<Scheme> getAllSchemes();
    
    void addFare(Fare fare);
    void updateFare(Fare fare);
    void deleteFare(Integer fareId);
    Fare getFareById(Integer fareID);
    
    void addSchedule(Schedule schedule);
    void updateSchedule(Schedule schedule);
    void deleteSchedule(Integer scheduleId);
    Schedule getScheduleById(Integer scheduleId);
    
    void addMetro(Metro metro);
    void updateMetro(Metro metro);
    void deleteMetro(Integer metroId);
    Metro getMetroById(Integer metroId);
    List<Metro> getAllMetros();
    
    void addNotification(Notification notification);
    void deleteNotification(Integer notificationId);
    void updateNotification(Notification notification);
    Notification getNotificationById(Integer notificationId);
    
    void resolveIssue(int issueId);
    List<Issue> getAllIssues();
    
    void assignMetroToSchedule(Metro metro, Schedule schedule);
    void removeMetroFromSchedule(Metro metro, Schedule schedule);
    Metro getMetroForSchedule(Integer scheduleId);
    Schedule getScheduleForMetro(Integer metroId);
    
    List<Booking> getAllBookings();
    List<Collection> getTotalCollections();
    List<CollectionStation> getAllCollectionStations();
    List<Passanger> getAllPassangers();
    List<User> getAllUsers();
    List<Subscripton> getAllSubcriptions();
    
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRole(Integer roleId);
    Role getRoleById(Integer roleId);
    List<Role> getAllRoles();
    List<Payment> getAllPayments();
    
    SearchFromTo searchByFromTo(Integer from,Integer to);
}
