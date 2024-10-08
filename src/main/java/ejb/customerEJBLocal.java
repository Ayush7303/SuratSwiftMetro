/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
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
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ayush
 */
@Local
public interface customerEJBLocal {
    Passanger getPassangerByUsername(String username);
    Scheme getSchemeById(Integer schemeId);
    void bookTicket(Booking booking);
    void subscribeToScheme(Subscripton subscription);
    List<Booking> getBookingsForPassanger(Integer passangerId);
    void makePayment(Payment payment);
    Fare getFareBySourceAndDestination(Station sourcce,Station destination);
    List<Subscripton> getSchemeSubscriptionsByPassenger(Integer passangerId);
    void ChangeProfile(Passanger passanger);
    Subscripton getActiveSubscriptionByPassenger(Integer passangerId);
}