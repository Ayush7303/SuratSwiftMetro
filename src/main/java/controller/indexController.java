/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import Client.PassengerClient;
import Client.UserClient;
import entity.CollectionStation;
import entity.Fare;
import entity.Issue;
import entity.Metro;
import entity.Notification;
import entity.Passanger;
import entity.Role;
import entity.Route;
import entity.Scheme;
import entity.Station;
import entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import record.keepRecord;

/**
 *
 * @author jeshanpatel1510
 */
@Named(value = "indexController")
@RequestScoped
public class indexController {

    @PersistenceContext(unitName = "ssmpu")
    EntityManager em;
    UserClient uc;
    PassengerClient cc;

    private String source, destinatin, issueTxt;
    private Double price;
    private Scheme scheme;
    private Issue issue;
    List<Station> stations;
    List<Scheme> schemes;
    List<Notification> notifications;
    private List<Issue> issues;
    GenericType<List<Scheme>> gschemes;
    GenericType<List<Station>> gstations;
    private GenericType<List<Issue>> gissues;
    GenericType<List<Notification>> gnotifications;
    Response rs;
    private Fare fare;

    public indexController() {
        uc = new UserClient();
        stations = new ArrayList<>();
        gstations = new GenericType<List<Station>>() {
        };
        this.fare = new Fare();
        this.scheme = new Scheme();
        this.schemes = new ArrayList<>();
        this.gschemes = new GenericType<List<Scheme>>() {
        };
        this.issue = new Issue();
        this.issues = new ArrayList<>();
        this.gissues = new GenericType<List<Issue>>() {
        };

        this.notifications = new ArrayList<>();
        this.gnotifications = new GenericType<List<Notification>>() {
        };
        cc = new PassengerClient();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestinatin() {
        return destinatin;
    }

    public void setDestinatin(String destinatin) {
        this.destinatin = destinatin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Station> getAllStations() {
        rs = uc.getAllStations(Response.class);
        stations = rs.readEntity(gstations);
        return stations;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    public List<Scheme> getAllSchemes() {
        System.out.println("get Scheme");
        rs = uc.getAllSchemes(Response.class);
        schemes = rs.readEntity(gschemes);
//        for (Scheme s : schemes) {
//            System.out.println("Scheme : " + s.getSchemename());
//        }
        return schemes;
    }

    public Fare getFare() {
        return fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public List<Issue> getIssues() {
        rs = uc.getIssuesForUser(Response.class, keepRecord.getUsername());
        issues = rs.readEntity(gissues);
        return issues;
    }

    public String getIssueTxt() {
        return issueTxt;
    }

    public void setIssueTxt(String issueTxt) {
        this.issueTxt = issueTxt;
    }

    public List<Notification> getNotifications() {
        rs = uc.getAllNotifications(Response.class);
        notifications = rs.readEntity(gnotifications);
        return notifications;
    }

    public String userRole() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String role = (String) session.getAttribute("role");
        return role;
    }

    public String userName() {
        String username = keepRecord.getUsername();
//        assignCashierToStation();
        return username;
    }

    public String userImg() {
        String img = "";
        String username = keepRecord.getUsername();
        System.out.println(username);
        System.out.println(username);
        if (username != null) {
            Passanger p = (Passanger) em.createQuery("SELECT p FROM Passanger p WHERE p.username.username=:un").setParameter("un", username).getSingleResult();
            if (p.getProfile().equals("NULL")) {
                img = "guest_user.png";
            } else {
                img = p.getProfile();
            }
        }
        return img;
    }

    public String passengerType() {
        Passanger passanger = (Passanger) em.createNamedQuery("Passanger.findByUsername").setParameter("username", keepRecord.getUsername()).getSingleResult();
        String type = null;
        if (passanger.getType() != null) {
            type = passanger.getType();
        } else {
            type = null;
        }
        return passanger.getType();
    }

    public List<String> passengerTypes() {
        List<String> types = new ArrayList<>();
        GenericType<List<String>> gtypes = new GenericType<List<String>>() {
        };
        rs = uc.getPassengerTypes(Response.class);
        types = rs.readEntity(gtypes);
        return types;
    }

    public List<Scheme> findAllSchemes() {
        System.out.println("get Scheme");
        rs = uc.getAllSchemes(Response.class);
        schemes = rs.readEntity(gschemes);
        return schemes;
    }

    public String subScheme() {
        System.out.println("Payment Success!!");
        return "myscheme.jsf";
    }

    public void assignCashierToStation() {
        System.out.println("Hiiii");

    }

    public void reportIssue() throws IOException {
        issue = new Issue();
        User user = new User();
        user.setUsername(keepRecord.getUsername());
        issue.setDescription(issueTxt);
        issue.setIssuer(user);
        issue.setIssuedate(new Date());
        issue.setStatus("Pending");

        System.out.println("Issue : " + issue.getDescription());
        System.out.println("Issuer : " + issue.getIssuer().getUsername());
        System.out.println("Issue Date : " + issue.getIssuedate());
        System.out.println("Issue Status : " + issue.getStatus());
        uc.addIssue(issue);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.sendRedirect("issue.jsf");
    }
}