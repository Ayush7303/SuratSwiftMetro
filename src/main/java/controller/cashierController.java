/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.CashierClient;
import com.lowagie.text.Paragraph;
import entity.Collection;
import entity.CollectionStation;
import entity.Station;
import entity.User;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import java.io.OutputStream;
import java.net.http.HttpRequest;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.jsoup.Jsoup;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;
import record.keepRecord;

/**
 *
 * @author jeshanpatel1510
 */
@Named(value = "cashierController")
@RequestScoped
public class cashierController {

    @PersistenceContext(unitName = "ssmpu")
    EntityManager em;
    CashierClient cc;
    User user;
    Station station;
    CollectionStation collectionStation;
    List<CollectionStation> colls;
    GenericType<List<CollectionStation>> gcolls;
    Collection collection;
    String Source, Destination, username;
    double Fare;
    int Quantity;
    Response rs;

    /**
     * Creates a new instance of cashierController
     */
    public cashierController() {
        user = new User();
        cc = new CashierClient();
        collection = new Collection();
        collectionStation = new CollectionStation();
        colls=new ArrayList<>();
        gcolls=new GenericType<List<CollectionStation>>(){};
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getFare() {
        return Fare;
    }

    public void setFare(double Fare) {
        this.Fare = Fare;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    public List<CollectionStation> getAllCollectionStation(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String uname = (String) session.getAttribute("username");

        System.out.println("user : " + uname);
        
        rs=cc.getAllCollectionStationOfCashier(Response.class, uname);
        colls=rs.readEntity(gcolls);
        return colls;
    }
    public CollectionStation getCollectionStation() {
        String cashier = keepRecord.getUsername();
        if (cashier != null) {
            collectionStation = cc.getCollectionStationByCashier(CollectionStation.class, cashier);
        } else {
            collectionStation = new CollectionStation();
        }
        return collectionStation;
    }

    public void setCollectionStation(CollectionStation collectionStation) {
        this.collectionStation = collectionStation;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String today() {
        return new Date().toLocaleString();
    }

    @Transactional
    public String bookTicket() {
        try {
            System.out.println("Source : " + Source);
            System.out.println("Destination : " + Destination);
            System.out.println("Amount : " + Fare);
            System.out.println("Quantity : " + Quantity);

            collectionStation = cc.getCollectionStationByCashier(CollectionStation.class, keepRecord.getUsername());
            double total = collectionStation.getTotalcollection();

            total = total + Fare;

            collectionStation.setTotalcollection(total);
            em.merge(collectionStation);

            Date date = new Date();
            Collection collection1 = (Collection) em.createQuery("SELECT c FROM Collection c WHERE c.collectiondate = :collectiondate").setParameter("collectiondate", date).getSingleResult();
            collection1.setTotalcollection(collection1.getTotalcollection() + Fare);
            em.merge(collection1);

//            cc.updateCollection(collectionStation);
            GenerateTicket(Source, Destination, Fare, Quantity, keepRecord.getUsername());
    
//
//            String ticketHtml = "<html><body>"
//                    + "<h1>Ticket Details</h1>"
//                    + "<p><strong>Source:</strong> " + Source + "</p>"
//                    + "<p><strong>Destination:</strong> " + Destination + "</p>"
//                    + "<p><strong>Amount:</strong> " + Fare + "</p>"
//                    + "<p><strong>Quantity:</strong> " + Quantity + "</p>"
//                    + "</body></html>";
//
//            // Convert HTML to PDF
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ITextRenderer renderer = new ITextRenderer();
//            renderer.setDocumentFromString(ticketHtml);
//            renderer.layout();
//            renderer.createPDF(baos);
//
//            // Prepare for download
//            byte[] pdfContent = baos.toByteArray();
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            ExternalContext externalContext = facesContext.getExternalContext();
//            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
//            response.setContentType("application/pdf");
//            response.setContentLength(pdfContent.length);
//            response.setHeader("Content-disposition", "attachment; filename=ticket.pdf");
//
//            // Send the PDF content to the response
//            response.getOutputStream().write(pdfContent);
//            response.getOutputStream().flush();
//            response.getOutputStream().close();
//
//            // Complete JSF lifecycle
//            facesContext.responseComplete();

//            HttpServletResponse r=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//            r.sendRedirect("/Cashier/home.jsf?faces-redirect=true");
                  return "/Cashier/home.jsf?faces-redirect=true";

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception : " + e.getMessage());
        }
        return "/Cashier/home.jsf";
    }

//    @Transactional
//    public String creditCollection() {
//        Date date = new Date();
//
//        collectionStation = cc.getCollectionStationByCashier(CollectionStation.class, keepRecord.getUsername());
//        double stColl = collectionStation.getTotalcollection();
//
//        Collection collection1 = (Collection) em.createQuery("SELECT c FROM Collection c WHERE c.collectiondate = :collectiondate").setParameter("collectiondate", date).getSingleResult();
//
//        System.out.println("Collection Id : " + collection1.getCollectionid());
//        System.out.println("Collection : " + stColl);
//        collection1.setTotalcollection(stColl + collection1.getTotalcollection());
//
//        cc.collectPayment(collection1);
//        collectionStation.setTotalcollection(0);
//        em.merge(collectionStation);
//        return "home.jsf";
//    }

    public void returnHome() throws IOException {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.sendRedirect("Cashier/home.jsf");
    }

    public void GenerateTicket(String src, String dest, double amt, int qty, String user) {
        Calendar calendar = Calendar.getInstance();
        Date expiry = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String ticket = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Facelet Title</title>\n"
                + "<link href=\"css/ticket.css\" rel=\"stylesheet\" type=\"text/css\"/>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div class=\"main-box\">\n"
                + "            <div class=\"ticket-header\">\n"
                + "                <h2>PASSENGER TICKET</h2>\n"
                + "            </div>\n"
                + "            <div class=\"ticket-details\">\n"
                + "                <div class=\"trip-details\">\n"
                + "                    <h1>" + src + " > " + dest + "</h1>\n"
                + "                </div>\n"
                + "                <div class=\"fare-issuer-details\">\n"
                + "                    <table>\n"
                + "                        <tr>\n"
                + "                            <td>\n"
                + "                                <h2>Fare</h2>\n"
                + "                                <p>" + amt + "</p>\n"
                + "                            </td>\n"
                + "                            <td>\n"
                + "                                <h2>Quantity</h2>\n"
                + "                                <p>" + qty + "</p>\n"
                + "                            </td>\n"
                + "                            <td>\n"
                + "                                <h2>Issuer</h2>\n"
                + "                                <p>" + user + "</p>\n"
                + "                            </td>                            \n"
                + "                        </tr>\n"
                + "                    </table>\n"
                + "                </div>\n"
                + "                <div class=\"issue-expiry-dates\">\n"
                + "                    <table>\n"
                + "                        <tr>\n"
                + "                            <td>\n"
                + "                                <h2>ISSUED AT</h2>\n"
                + "                                <p>" + dateFormat.format(new Date()) + "</p>\n"
                + "                                <p>" + timeFormat.format(new Date()) + "</p>\n"
                + "                            </td>\n"
                + "                            <td>\n"
                + "                                <h2>EXPIRED AT</h2>\n"
                + "                                <p>" + dateFormat.format(expiry) + "</p>\n"
                + "                                <p>" + timeFormat.format(expiry) + "</p>\n"
                + "                            </td>                            \n"
                + "                        </tr>\n"
                + "                    </table>\n"
                + "                </div>\n"
                + "                <div class=\"qr_code\">\n"
                + "                    <img src=\"images/sampleQR.png\" id=\"qr_code\" alt=\"QR\"/>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "            <div class=\"ticket-footer\">\n"
                + "                <h2>#SURAT_SWIFT_METRO</h2>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>";
        try {
            File ticketFile = new File("F:\\swiftsuratmetro\\SuratSwiftMetro\\src\\main\\webapp\\ticket.html");
            FileWriter fw = new FileWriter(ticketFile);
            fw.write(ticket);
            fw.close();

            Random random = new Random();

            try (OutputStream os = new FileOutputStream("F:\\swiftsuratmetro\\cashierTickets\\" + random.nextInt(10000) + ".pdf")) {
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocument(ticketFile);
                renderer.layout();
                renderer.createPDF(os);
                System.out.println("done");
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }
}
