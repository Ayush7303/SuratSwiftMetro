/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ayush
 */
@Stateless
public class userEJB implements userEJBLocal {

    @PersistenceContext(unitName = "ssmpu")
    EntityManager em;

    @Override
    public User login(String username, String password) {
        return (User) em.createQuery("SELECT u FROM User u WHERE u.username=:un AND u.password=:pwd").setParameter("un", username).setParameter("pwd", password).getSingleResult();
    }

    @Override
    public void register(User user, Passanger passanger) {
        Role role = new Role();
        role.setRolename("Passenger");
        role.setUsername(user);
        em.persist(role);
        em.persist(user);
        em.persist(passanger);
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User user = em.find(User.class, username);
        if (user != null && user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            em.merge(user);
            return true;
        }
        return false;
    }

    @Override
    public void assignRole(String username, Role role) {
        User user = em.find(User.class, username);
        user.getRoleCollection().add(role);
        em.merge(user);
        em.persist(role);
    }
//
//    @Override
//    public void removeRole(String username, Role role) {
//        User user=em.find(User.class, username);
//        user.getRoleCollection().remove(role);
//        em.merge(user);
//        em.remove(role);
//    }

    @Override
    public boolean isUsernameAvailable(String username) {
        Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
        // If count is 0, username is available
        System.out.println(count);
        return count == 0;
    }

    @Override
    public List<Issue> getIssuesForUser(String username) {
        return em.createQuery("SELECT i FROM Issue i WHERE i.issuer.username=:un").setParameter("un", username).getResultList();
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return em.createNamedQuery("Schedule.findAll").getResultList();
    }

    @Override
    public List<Station> getAllStations() {
        return em.createNamedQuery("Station.findAll").getResultList();
    }

    @Override
    public List<Route> getAllRoutes() {
        return em.createNamedQuery("Route.findAll").getResultList();
    }

    @Override
    public List<StationLink> getAllStationLinks() {
        return em.createNamedQuery("StationLink.findAll").getResultList();
    }

    @Override
    public List<Fare> getAllFares() {
        return em.createNamedQuery("Fare.findAll").getResultList();
    }

    @Override
    public List<Notification> viewNotifications() {
        return em.createNamedQuery("Notification.findAll").getResultList();
    }

    @Override
    public List<Interchange> getAllInterchanges() {
        return em.createNamedQuery("Interchange.findAll").getResultList();
    }

    @Override
    public List<RouteStationDTO> getAllRouteStation() {
        List<Route> routes = em.createNamedQuery("Route.findAll", Route.class).getResultList();
        List<RouteStationDTO> routeStationDTOs = new ArrayList<>();

        for (Route route : routes) {
            for (Station station : route.getStationCollection()) {
                routeStationDTOs.add(new RouteStationDTO(route, station));
            }
        }
        return routeStationDTOs;
    }

    @Override
    public List<Role> getAllRoles() {
        return em.createNamedQuery("Role.findAll").getResultList();
    }

    @Override
    public Fare getFareBySourceAndDestination(String s, String d) {
        Station source = (Station) em.createNamedQuery("Station.findByStationname").setParameter("stationname", s).getSingleResult();
        Station destination = (Station) em.createNamedQuery("Station.findByStationname").setParameter("stationname", d).getSingleResult();
        System.out.println("Source : " + source.getStationname() + " Destination : " + destination.getStationname());
        Fare fare = (Fare) em.createQuery("SELECT f FROM Fare f WHERE f.source.stationid=:source AND f.destination.stationid=:destination").setParameter("source", source.getStationid()).setParameter("destination", destination.getStationid()).getSingleResult();
        System.out.println("Fare : " + fare.getFare());
        return fare;
    }

    @Override
    public Route getRouteByStation(String s) {
        Route route = new Route();
        Station station1 = (Station) em.createQuery("SELECT s FROM Station s WHERE s.stationname = :sn").setParameter("sn", s).getSingleResult();
        List<Route> routes = em.createNamedQuery("Route.findAll").getResultList();
        for (Route r : routes) {
            if (r.getStationCollection().contains(station1)) {
                route = r;
            }
        }
        return route;
    }

    @Override
    public void updateBookingStatus(int bookingId) {
        Booking b = em.find(Booking.class, bookingId);
        b.setStatus("Expired");
        em.merge(b);
    }

    @Override
    public boolean isEmailAvailable(String email) {
        Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
        // If count is 0, username is available
        System.out.println(count);
        return count == 0;
    }

    @Override
    public void resetPassword(RestPasswordDTO rs) {
        User u= em.createQuery("SELECT u FROM User u WHERE u.email=:e",User.class).setParameter("e", rs.getEmail()).getSingleResult();
        u.setPassword(rs.getPassword());
        
        em.merge(u);
    }
    @Override
    public List<String> getPassengerTypes() {
        return em.createQuery("SELECT DISTINCT s.passangertype FROM Scheme s").getResultList();
    }

    @Override
    public void addIssue(Issue issue) {
        User user = em.find(User.class, issue.getIssuer().getUsername());
        user.getIssueCollection().add(issue);
        em.merge(user);
        em.persist(issue);
    }        

    @Override
    public List<Scheme> getallSchemes() {
        return em.createNamedQuery("Scheme.findAll").getResultList();
    }

   @Override
    public void assignCashierToStation(CollectionStation collectionStation) {
        em.persist(collectionStation);
    }

    @Override
    public Subscripton getSubscriptionById(Integer sid) {
        return em.find(Subscripton.class, sid);
    }

}
