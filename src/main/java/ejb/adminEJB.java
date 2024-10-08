/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
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
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author ayush
 */
@Stateless
public class adminEJB implements adminEJBLocal {

    @PersistenceContext(unitName = "ssmpu")
    EntityManager em;
    Pbkdf2PasswordHashImpl pb=new Pbkdf2PasswordHashImpl();
    @Override
    public void addStation(Station station) {
        em.persist(station);
        Station st=em.createQuery("SELECT s FROM Station S WHERE s.stationname=:sn AND s.latitude=:lat AND s.longitude=:lon",Station.class)
                .setParameter("sn", station.getStationname())
                .setParameter("lat", station.getLatitude())
                .setParameter("lon", station.getLongitude())
                .getSingleResult();
        String cUsername="cashier"+st.getStationid()+"SSM";
        String cPassword="cashier"+st.getStationid()+"SSM";
        String enc=pb.generate(cPassword.toCharArray());
        User u=new User();
        u.setUsername(cUsername);
        u.setPassword(enc);
        u.setEmail("suratswiftmetro@gmail.com");
        u.setMobilenumber("9876543210");
        u.setDateofbirth(new Date());
        u.setGender("Male");
        em.persist(u);
        Role r=new Role();
        User u1=em.find(User.class, cUsername);
        r.setUsername(u1);
        r.setRolename("Cashier");
        em.persist(r);
    }

    @Override
    public void updateStation(Station station) {
        em.merge(station);
    }

    @Override
    public void deleteStation(Integer stationId) {
        Station s = em.find(Station.class, stationId);
        em.remove(s);
    }

    @Override
    public Station getStationById(Integer stationId) {
        return em.find(Station.class, stationId);
    }

    @Override
    public void addRoute(Route route) {
        Station startStation = em.find(Station.class, route.getStartstation().getStationid());
        Station endStation = em.find(Station.class, route.getEndstation().getStationid());

        startStation.getRouteCollection2().add(route);
        endStation.getRouteCollection1().add(route);

        em.merge(startStation);
        em.merge(endStation);

        em.persist(route);
    }

    @Override
    public void updateRoute(Route route) {
        Route existingRoute = em.find(Route.class, route.getRouteid());

        Station startStation = existingRoute.getStartstation();
        Station endStation = existingRoute.getEndstation();

        startStation.getRouteCollection2().remove(existingRoute);
        endStation.getRouteCollection1().remove(existingRoute);

        startStation.getRouteCollection2().add(route);
        endStation.getRouteCollection1().add(route);

        em.merge(existingRoute);
        em.merge(startStation);
        em.merge(endStation);
    }

    @Override
    public void deleteRoute(Integer routeId) {
        Route existingRoute = em.find(Route.class, routeId);

        Station startStation = existingRoute.getStartstation();
        Station endStation = existingRoute.getEndstation();

        startStation.getRouteCollection2().remove(existingRoute);
        endStation.getRouteCollection1().remove(existingRoute);

        em.merge(startStation);
        em.merge(endStation);
        em.remove(existingRoute);
    }

    @Override
    public Route getRouteById(Integer routeId) {
        return em.find(Route.class, routeId);
    }

    @Override
    public void addStationLink(StationLink stationLink) {
        Route route = em.find(Route.class, stationLink.getRouteid().getRouteid());
        Station currentStation = em.find(Station.class, stationLink.getCurrentstation().getStationid());
        Station nextStation = em.find(Station.class, stationLink.getNextstation().getStationid());
        Station previousStation = em.find(Station.class, stationLink.getPreviousstation().getStationid());

        route.getStationLinkCollection().add(stationLink);
        currentStation.getStationLinkCollection().add(stationLink);
        nextStation.getStationLinkCollection1().add(stationLink);
        previousStation.getStationLinkCollection2().add(stationLink);

        em.merge(route);
        em.merge(currentStation);
        em.merge(nextStation);
        em.merge(previousStation);

        em.persist(stationLink);

    }

    @Override
    public void updateStationLink(StationLink stationLink) {

        StationLink existingStationLink = em.find(StationLink.class, stationLink.getStationlinkid());

        Route route = existingStationLink.getRouteid();
        Station currentStation = existingStationLink.getCurrentstation();
        Station nextStation = existingStationLink.getNextstation();
        Station previousStation = existingStationLink.getPreviousstation();

        route.getStationLinkCollection().remove(existingStationLink);
        currentStation.getStationLinkCollection().remove(existingStationLink);
        nextStation.getStationLinkCollection1().remove(existingStationLink);
        previousStation.getStationLinkCollection2().remove(existingStationLink);

        route.getStationLinkCollection().add(stationLink);
        currentStation.getStationLinkCollection().add(stationLink);
        nextStation.getStationLinkCollection1().add(stationLink);
        previousStation.getStationLinkCollection2().add(stationLink);

        em.merge(stationLink);
        em.merge(route);
        em.merge(currentStation);
        em.merge(nextStation);
        em.merge(previousStation);
    }

    @Override
    public void deleteStationLink(Integer stationLinkId) {
        StationLink stationLink = em.find(StationLink.class, stationLinkId);

        Route route = em.find(Route.class, stationLink.getRouteid());
        Station currentStation = em.find(Station.class, stationLink.getCurrentstation());
        Station nextStation = em.find(Station.class, stationLink.getNextstation());
        Station previousStation = em.find(Station.class, stationLink.getPreviousstation());

        route.getStationLinkCollection().remove(stationLink);
        currentStation.getStationLinkCollection().remove(stationLink);
        nextStation.getStationLinkCollection1().remove(stationLink);
        previousStation.getStationLinkCollection2().remove(stationLink);

        em.merge(route);
        em.merge(currentStation);
        em.merge(nextStation);
        em.merge(previousStation);
        em.remove(stationLink);
    }

    @Override
    public StationLink getStationLinkById(Integer stationLinkId) {
        return em.find(StationLink.class, stationLinkId);
    }

    @Override
    public void addInterchange(Interchange interchange) {
        Station station = em.find(Station.class, interchange.getStationid().getStationid());
        Route route1 = em.find(Route.class, interchange.getRouteid1().getRouteid());
        Route route2 = em.find(Route.class, interchange.getRouteid2().getRouteid());

        station.getInterchangeCollection().add(interchange);
        route1.getInterchangeCollection().add(interchange);
        route2.getInterchangeCollection1().add(interchange);

        em.merge(station);
        em.merge(route1);
        em.merge(route2);
        em.persist(interchange);
    }

    @Override
    public void deleteInterchange(Integer interchangeId) {
        Interchange existingInterchange = em.find(Interchange.class, interchangeId);
        Station station = existingInterchange.getStationid();
        Route route1 = existingInterchange.getRouteid1();
        Route route2 = existingInterchange.getRouteid2();
        station.getInterchangeCollection().remove(existingInterchange);
        route1.getInterchangeCollection().remove(existingInterchange);
        route2.getInterchangeCollection1().remove(existingInterchange);

        em.merge(station);
        em.merge(route1);
        em.merge(route2);
        em.remove(existingInterchange);
    }

    @Override
    public Interchange getInterchangeById(Integer interchangeId) {
        return em.find(Interchange.class, interchangeId);
    }

    @Override
    public void updateInterchange(Interchange interchange) {
        Interchange existingInterchange = em.find(Interchange.class, interchange.getInterchangeid());
        Station station = existingInterchange.getStationid();
        Route r1 = existingInterchange.getRouteid1();
        Route r2 = existingInterchange.getRouteid2();

        station.getInterchangeCollection().remove(existingInterchange);
        r1.getInterchangeCollection().remove(existingInterchange);
        r2.getInterchangeCollection1().remove(existingInterchange);

        station.getInterchangeCollection().add(interchange);
        r1.getInterchangeCollection().add(interchange);
        r2.getInterchangeCollection1().add(interchange);

        em.merge(interchange);
        em.merge(station);
        em.merge(r1);
        em.merge(r2);
    }

    @Override
    public void addScheme(Scheme scheme) {
        em.persist(scheme);
    }

    @Override
    public void updateScheme(Scheme scheme) {
        em.merge(scheme);
    }

    @Override
    public void deleteScheme(Integer schemeId) {
        Scheme scheme = em.find(Scheme.class, schemeId);
        em.remove(scheme);
    }

    @Override
    public Scheme getSchemeById(Integer schemeId) {
        return em.find(Scheme.class, schemeId);
    }

    @Override
    public List<Scheme> getAllSchemes() {
        return em.createNamedQuery("Scheme.findAll").getResultList();
    }

    @Override
    public void addFare(Fare fare) {
        Station source = em.find(Station.class, fare.getSource().getStationid());
        Station destination = em.find(Station.class, fare.getDestination().getStationid());
        source.getFareCollection1().add(fare);
        destination.getFareCollection().add(fare);
        em.merge(source);
        em.merge(destination);
        em.persist(fare);
    }

    @Override
    public void updateFare(Fare fare) {
        Fare existingFare = em.find(Fare.class, fare.getFareid());
        Station source = existingFare.getSource();
        Station destination = existingFare.getDestination();
        source.getFareCollection1().remove(existingFare);
        destination.getFareCollection().remove(existingFare);
        source.getFareCollection1().add(fare);
        destination.getFareCollection().add(fare);
        em.merge(source);
        em.merge(destination);
        em.merge(fare);
    }

    @Override
    public void deleteFare(Integer fareId) {
        Fare fare = em.find(Fare.class, fareId);
        Station source = fare.getSource();
        Station destination = fare.getDestination();
        source.getFareCollection1().remove(fare);
        destination.getFareCollection().remove(fare);
        em.merge(source);
        em.merge(destination);
        em.remove(fare);
    }

    @Override
    public Fare getFareById(Integer fareID) {
        return em.find(Fare.class, fareID);
    }

    @Override
    public void addSchedule(Schedule schedule) {
        Metro metro = em.find(Metro.class, schedule.getMetroid().getMetroid());
        Route route = em.find(Route.class, schedule.getRouteid().getRouteid());
        metro.getScheduleCollection().add(schedule);
        route.getScheduleCollection().add(schedule);
        em.merge(metro);
        em.merge(route);
        em.persist(schedule);
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        Schedule existingSchedule = em.find(Schedule.class, schedule.getScheduleid());
        Metro metro = existingSchedule.getMetroid();
        Route route = existingSchedule.getRouteid();
        metro.getScheduleCollection().remove(existingSchedule);
        route.getScheduleCollection().remove(existingSchedule);
        metro.getScheduleCollection().add(schedule);
        metro.getScheduleCollection().add(schedule);
        em.merge(metro);
        em.merge(route);
        em.merge(schedule);
    }

    @Override
    public void deleteSchedule(Integer scheduleId) {
        Schedule existingSchedule = em.find(Schedule.class, scheduleId);
        Route route = existingSchedule.getRouteid();
        Metro metro = existingSchedule.getMetroid();
        route.getScheduleCollection().remove(existingSchedule);
        metro.getScheduleCollection().remove(existingSchedule);
        em.merge(route);
        em.merge(metro);
        em.remove(existingSchedule);
    }

    @Override
    public Schedule getScheduleById(Integer scheduleId) {
        return em.find(Schedule.class, scheduleId);
    }

    @Override
    public void addMetro(Metro metro) {
        em.persist(metro);
    }

    @Override
    public void updateMetro(Metro metro) {
        em.merge(metro);
    }

    @Override
    public void deleteMetro(Integer metroId) {
        Metro metro = em.find(Metro.class, metroId);
        em.remove(metro);
    }

    @Override
    public Metro getMetroById(Integer metroId) {
        return em.find(Metro.class, metroId);
    }

    @Override
    public List<Metro> getAllMetros() {
        return em.createNamedQuery("Metro.findAll").getResultList();
    }

    @Override
    public void addNotification(Notification notification) {
        em.persist(notification);
    }

    @Override
    public void deleteNotification(Integer notificationId) {
        Notification notification = em.find(Notification.class, notificationId);
        em.remove(notification);
    }

    @Override
    public Notification getNotificationById(Integer notificationId) {
        return em.find(Notification.class, notificationId);
    }

    @Override
    public void resolveIssue(int issueId) {
        Issue issue = em.find(Issue.class, issueId);
        issue.setStatus("Resolved");
        em.merge(issue);
    }

    @Override
    public List<Issue> getAllIssues() {
        return em.createNamedQuery("Issue.findAll").getResultList();
    }

    @Override
    public void assignMetroToSchedule(Metro metro, Schedule schedule) {
        metro.getScheduleCollection().add(schedule);
        em.persist(metro);
        em.merge(schedule);
    }

    @Override
    public void removeMetroFromSchedule(Metro metro, Schedule schedule) {
        metro.getScheduleCollection().remove(schedule);
        em.merge(schedule);
        em.remove(metro);
    }

    @Override
    public Metro getMetroForSchedule(Integer scheduleId) {
        return em.find(Metro.class, scheduleId);
    }

    @Override
    public Schedule getScheduleForMetro(Integer metroId) {
        return em.find(Schedule.class, metroId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return em.createNamedQuery("Booking.findAll").getResultList();
    }

    @Override
    public List<Collection> getTotalCollections() {
        return em.createNamedQuery("Collection.findAll").getResultList();
    }

    @Override
    public List<CollectionStation> getAllCollectionStations() {
        return em.createNamedQuery("CollectionStation.findAll").getResultList();
    }

    @Override
    public List<Passanger> getAllPassangers() {
        return em.createNamedQuery("Passanger.findAll").getResultList();
    }

    @Override
    public List<User> getAllUsers() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public List<Subscripton> getAllSubcriptions() {
        return em.createNamedQuery("Subscripton.findAll").getResultList();
    }

    @Override
    public void addRole(Role role) {
        User user = em.find(User.class, role.getUsername().getUsername());
        user.getRoleCollection().add(role);
        em.merge(role);
        em.persist(role);
    }

    @Override
    public void updateRole(Role role) {
        Role existingRole = em.find(Role.class, role.getRoleid());
        User user = existingRole.getUsername();
        user.getRoleCollection().remove(existingRole);
        user.getRoleCollection().add(role);
        em.merge(user);
        em.merge(role);
    }

    @Override
    public void deleteRole(Integer roleId) {
        Role role = em.find(Role.class, roleId);
        User user = role.getUsername();
        user.getRoleCollection().remove(role);
        em.merge(user);
        em.remove(role);
    }

    @Override
    public Role getRoleById(Integer roleId) {
        return em.find(Role.class, roleId);
    }

    @Override
    public List<Role> getAllRoles() {
        return em.createNamedQuery("Role.findAll").getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<Payment> getAllPayments() {

        return em.createNamedQuery("Payment.findAll").getResultList();
    }

    @Override
    public void addStationToRoute(Integer stationid, Integer routeid) {
        Route route = em.find(Route.class, routeid);
        Station station = em.find(Station.class, stationid);
        route.getStationCollection().add(station);
        em.merge(route);
    }

    @Override
    public void updateNotification(Notification notification) {
        em.merge(notification);

    }

    @Override
    public SearchFromTo searchByFromTo(Integer from, Integer to) {
        SearchFromTo se = new SearchFromTo();
        Fare f = (Fare) em.createQuery("SELECT f FROM Fare f WHERE f.source.stationid=:s AND f.destination.stationid=:d")
                .setParameter("s", from)
                .setParameter("d", to).getSingleResult();
        se.setFare(f);
        int stationcount = 0;
//        StationLink source = (StationLink) em.createQuery("SELECT sl FROM StationLink sl WHERE sl.currentstation.stationid=:cid").setParameter("cid", from).getSingleResult();
//        StationLink destination = (StationLink) em.createQuery("SELECT sl FROM StationLink sl WHERE sl.currentstation.stationid=:cid").setParameter("cid", to).getSingleResult();
//
//        while (!source.getCurrentstation().getStationid().equals(destination.getCurrentstation().getStationid())) {
//
//            stationcount++;
//                int next = source.getNextstation().getStationid();                
//                source = (StationLink) em.createQuery("SELECT sl FROM StationLink sl WHERE sl.currentstation.stationid=:cid").setParameter("cid", next).getSingleResult();
//               
//        }
     

        se.setInterchange(0);
        se.setStation(stationcount);
        return se;
    }

}
