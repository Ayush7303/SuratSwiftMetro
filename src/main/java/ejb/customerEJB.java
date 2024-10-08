/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.Booking;
import entity.Fare;
import entity.Issue;
import entity.Notification;
import entity.Passanger;
import entity.Payment;
import entity.Route;
import entity.Scheme;
import entity.Station;
import entity.StationLink;
import entity.Subscripton;
import entity.User;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author ayush
 */
@Stateless
public class customerEJB implements customerEJBLocal {

    @PersistenceContext(unitName = "ssmpu")
    EntityManager em;


    @Override
    public Scheme getSchemeById(Integer schemeId) {
        return em.find(Scheme.class, schemeId);
    }

    @Override
    public void bookTicket(Booking booking) {
        System.out.println("Booking EJB!!");
        Passanger passanger = em.find(Passanger.class, booking.getPassangerid().getPassangerid());
        Station source = (Station) em.createNamedQuery("Station.findByStationname").setParameter("stationname", booking.getSource().getStationname()).getSingleResult();
        Station destination = (Station) em.createNamedQuery("Station.findByStationname").setParameter("stationname", booking.getDestination().getStationname()).getSingleResult();

        Payment payment = em.find(Payment.class, booking.getPaymentid().getPaymentid());

        booking.setPassangerid(passanger);
        booking.setPaymentid(payment);
        booking.setSource(source);
        booking.setDestination(destination);

        System.out.println("Booking Source : " + source.getStationname());
        System.out.println("Booking Destination : " + destination.getStationname());
        System.out.println("Booking Amount : " + booking.getTotalfair());
        System.out.println("Booking Date : " + booking.getBookingdate());
        System.out.println("Booking Payment Id : " + booking.getPaymentid());
        System.out.println("Booking Passanger : " + booking.getPassangerid().getFullname());
        System.out.println("Booking Distance : " + booking.getDistance());

        try {
            passanger.getBookingCollection().add(booking);
            source.getBookingCollection1().add(booking);
            destination.getBookingCollection().add(booking);
            payment.getBookingCollection().add(booking);
            em.persist(booking);
            em.merge(passanger);
            em.merge(source);
            em.merge(destination);
            em.merge(payment);
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

    @Override
    public void subscribeToScheme(Subscripton subscription) {
        Scheme scheme = em.find(Scheme.class, subscription.getSchemeid().getSchemeid());
        Passanger passanger = em.find(Passanger.class, subscription.getPassangerid().getPassangerid());
        Payment payment = em.find(Payment.class, subscription.getPaymentid().getPaymentid());

        Date endDate = subscription.getStartdate();
        
        endDate = DateUtils.addMonths(endDate, scheme.getValidity());
        
        subscription.setPassangerid(passanger);
        subscription.setPaymentid(payment);
        subscription.setSchemeid(scheme);        
        subscription.setEnddate(endDate);
        
        System.out.println("Scheme Id : "+subscription.getSchemeid());
        System.out.println("Passenger Id : "+subscription.getPassangerid());
        System.out.println("Amount : "+subscription.getAmount());
        System.out.println("start : "+subscription.getStartdate());
        System.out.println("status : "+subscription.getStatus());
        System.out.println("end date : "+subscription.getEnddate());
        
        scheme.getSubscriptonCollection().add(subscription);
        passanger.getSubscriptonCollection().add(subscription);
        payment.getSubscriptonCollection().add(subscription);

        em.persist(subscription);
        em.merge(scheme);
        em.merge(passanger);
        em.merge(payment);
    }

    @Override
    public List<Booking> getBookingsForPassanger(Integer passangerId) {
        return em.createQuery("SELECT b FROM Booking b WHERE b.passangerid.passangerid=:pid").setParameter("pid", passangerId).getResultList();
    }

    @Override
    public Fare getFareBySourceAndDestination(Station sourcce, Station destination) {
        return (Fare) em.createQuery("SELECT f FROM Fare f WHERE f.source.stationid=:source AND f.destination.stationid=:destination").setParameter("source", sourcce.getStationid()).setParameter("destination", destination.getStationid()).getSingleResult();
    }

    @Override
    public void makePayment(Payment payment) {
        em.persist(payment);
    }

    @Override
    public List<Subscripton> getSchemeSubscriptionsByPassenger(Integer passangerId) {
        return em.createQuery("SELECT s FROM Subscripton s WHERE s.passangerid.passangerid = :passangerid").setParameter("passangerid", passangerId).getResultList();
    }

    @Override
    public Passanger getPassangerByUsername(String username) {
        Passanger p = (Passanger) em.createNamedQuery("Passanger.findByUsername").setParameter("username", username).getSingleResult();
        return p;
    }

    @Override
    public Subscripton getActiveSubscriptionByPassenger(Integer passangerId) {
        Subscripton subscripton = null;
        try{
            subscripton = em.createQuery("SELECT s FROM Subscripton s WHERE s.passangerid.passangerid = :passangerid AND s.status = 1", Subscripton.class).setParameter("passangerid", passangerId).getSingleResult();
            System.out.println("Subscription : "+subscripton.getSubscriptionid());
        }catch(Exception e){
            System.out.println("Exception : "+e.getMessage());
        }
        return subscripton;
    }
    
    @Override
    public void ChangeProfile(Passanger passanger) {
        em.merge(passanger);
    }
}