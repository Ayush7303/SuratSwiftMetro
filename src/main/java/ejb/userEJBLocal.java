/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.*;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ayush
 */
@Local
public interface userEJBLocal {

    User login(String username, String password);
    void register(User user, Passanger passanger);
    boolean changePassword(String username, String oldPassword, String newPassword);
    void assignRole(String username, Role role);
//    void removeRole(String username,Role role);
    boolean isUsernameAvailable(String username);
    boolean isEmailAvailable(String email);
    void resetPassword(RestPasswordDTO rsd);
    List<Issue> getIssuesForUser(String username);
    Subscripton getSubscriptionById(Integer sid);
    List<Schedule> getAllSchedules();
    List<Station> getAllStations();
    List<Route> getAllRoutes();
    List<StationLink> getAllStationLinks();
    List<Interchange> getAllInterchanges();
    List<Fare> getAllFares();
    List<Notification> viewNotifications();
    List<RouteStationDTO> getAllRouteStation();
    List<Role> getAllRoles();
    Fare getFareBySourceAndDestination(String sourcce, String destination);
    Route getRouteByStation(String sourcce);
    public void updateBookingStatus(int bookingId);
    void addIssue(Issue issue);
    List<Scheme> getallSchemes();
    void assignCashierToStation(CollectionStation collectionStation);
    List<String> getPassengerTypes();
}
