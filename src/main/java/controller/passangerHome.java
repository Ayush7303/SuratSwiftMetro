/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.PassengerClient;
import Client.UserClient;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import ejb.customerEJBLocal;
import ejb.userEJBLocal;
import entity.Booking;
import entity.Fare;
import entity.Passanger;
import entity.Payment;
import entity.Scheme;
import entity.Station;
import entity.Subscripton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.SecurityContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import okhttp3.MultipartBody;
import org.xhtmlrenderer.pdf.ITextRenderer;
import record.keepRecord;

/**
 *
 * @author jeshanpatel1510
 */
@Named(value = "passangerHome")
@MultipartConfig
@RequestScoped
public class passangerHome {

    @PersistenceContext(unitName = "ssmpu")
    EntityManager em;
    PassengerClient cc;
    UserClient uc;
    @EJB
    userEJBLocal userEJB;
    @EJB
    customerEJBLocal custEJB;

    private String source, destinatin, paymentid, dob;
    private Double price;
    int schemeid;

    private List<Station> stations;
    private GenericType<List<Station>> gstations;
    private List<Subscripton> subscriptions;
    private GenericType<List<Subscripton>> gsubscriptions;
    private List<Booking> bookings;
    private GenericType<List<Booking>> gbookings;

    private Part profile;

    Response rs;
    private Fare fare;

    public passangerHome() {
        cc = new PassengerClient();
        this.stations = new ArrayList<>();
        this.gstations = new GenericType<List<Station>>() {
        };
        this.subscriptions = new ArrayList<>();
        this.gsubscriptions = new GenericType<List<Subscripton>>() {
        };
        this.bookings = new ArrayList<>();
        this.gbookings = new GenericType<List<Booking>>() {
        };
        this.fare = new Fare();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String Source) {
        this.source = Source;
    }

    public String getDestinatin() {
        return destinatin;
    }

    public void setDestinatin(String Destinatin) {
        this.destinatin = Destinatin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double Price) {
        this.price = Price;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public GenericType<List<Station>> getGstations() {
        return gstations;
    }

    public void setGstations(GenericType<List<Station>> gstations) {
        this.gstations = gstations;
    }

    public Fare getFare() {
        return fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }

    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

    public int getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(int schemeid) {
        this.schemeid = schemeid;
    }

    public Part getProfile() {
        return profile;
    }

    public void setProfile(Part profile) {
        this.profile = profile;
    }

    public List<Subscripton> getSubscriptions() {
        Passanger passanger = (Passanger) em.createNamedQuery("Passanger.findByUsername").setParameter("username", keepRecord.getUsername()).getSingleResult();
        rs = cc.getSubscriptionsByPassaanger(Response.class, String.valueOf(passanger.getPassangerid()));
        subscriptions = rs.readEntity(gsubscriptions);
        return subscriptions;
    }

    public void setSubscriptions(List<Subscripton> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    public Subscripton activeSubscription(){
        Passanger p = em.createNamedQuery("Passanger.findByUsername", Passanger.class).setParameter("username", keepRecord.getUsername()).getSingleResult();
        Subscripton subscription = cc.getActiveSubscriptionByPassenger(Subscripton.class, String.valueOf(p.getPassangerid()));
        return subscription;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void retriveFareBySourceDestination() {
        rs = uc.getFareBySourceAndDestination(Response.class, source, destinatin);
        fare = rs.readEntity(Fare.class);
        price = fare.getFare();
    }

    public String makeBooking() {
        try {
            Payment payment = new Payment();
            payment.setPaymentid(paymentid);
            payment.setAmount(price);
            payment.setPaymentdate(new Date());
            payment.setStatus(true);

            cc.makePayment(payment);

            System.out.println("Payment Successed!!");

            Station sourcest = new Station();
            sourcest.setStationname(source);
            Station destinationst = new Station();
            destinationst.setStationname(destinatin);

            String uname = keepRecord.getUsername();
            System.out.println(uname);

            Passanger p = em.createNamedQuery("Passanger.findByUsername", Passanger.class).setParameter("username", uname).getSingleResult();
            System.out.println("passanger : " + p.getFullname());

            Booking booking = new Booking();
            booking.setSource(sourcest);
            System.out.println("Source Set");
            booking.setDestination(destinationst);
            System.out.println("Destination Set");
            booking.setPaymentid(payment);
            System.out.println("Payment Id Set");
            booking.setTotalfair(price);
            System.out.println("Price Set");
            booking.setBookingdate(new Date());
            System.out.println("Booking Day Set : " + booking.getBookingdate());
            booking.setDistance(20.0);
            System.out.println("Distance Set");
            booking.setPassangerid(p);
            booking.setStatus("Active");
            System.out.println("Passabger Id Set");
            System.out.println("Booking Set!!");
            cc.bookTicket(booking);
//            GenerateTicket(source, destinatin, price, 1, uname);
            System.out.println("Booking Successed!!");

            Booking b = em.createQuery("SELECT b FROM Booking b WHERE b.paymentid.paymentid=:pid", Booking.class)
                    .setParameter("pid", payment.getPaymentid()).getSingleResult();
            String data = "SuratSwiftMetro/Booking/" + b.getBookingid();
            System.out.println(data);
            String path = "F:\\swiftsuratmetro\\BookingQR\\QR" + b.getBookingid();
            BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
            System.out.println("QR created.");

            String to = b.getPassangerid().getUsername().getEmail();
            String src = b.getSource().getStationname();
            String dst = b.getDestination().getStationname();
            double f = b.getTotalfair();
            int bid = b.getBookingid();
            System.out.println(to + src + dst + f + bid);
            sendMail(to, src, dst, f, bid);
            return "/index.jsf";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
        return "/index.jsf";
    }

    public void sendMail(String to, String source, String destination, double fare, int bookingId) throws MessagingException {
        try {
            String host = "smtp.gmail.com";
            String user = "suratswiftmetro@gmail.com";
            String password = "abitrgafxievusgn";
            String from = "suratswiftmetro@gmail.com";
            String subject = "Booking Details";
            String message = "<html>"
                    + "<body>"
                    + "<div style='text-align: center;'>"
                    + "<h5>Source: " + source + "</h5>"
                    + "<h5>Destination: " + destination + "</h5>"
                    + "<h5>Fare: " + fare + "</h5>"
                    + "<img src='cid:image_id'>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            boolean sessionDebug = false;
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailsession = Session.getDefaultInstance(props, null);
            mailsession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailsession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            // Create a multipart message for HTML content and image
            MimeMultipart multipart = new MimeMultipart("related");

            // HTML part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html");
            multipart.addBodyPart(messageBodyPart);

            // Image part
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource("F:\\swiftsuratmetro\\BookingQR\\QR" + bookingId); // Replace with the path to your image
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image_id>");
            multipart.addBodyPart(messageBodyPart);

            // Set the multipart message to the email message
            msg.setContent(multipart);

//            msg.setText(message);
            Transport transport = mailsession.getTransport("smtp");
            transport.connect(host, user, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Mail Sent");
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Booking> showAllBookings() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String uname = (String) session.getAttribute("username");

        System.out.println("user : " + uname);

        Passanger passanger = (Passanger) em.createQuery("SELECT p FROM Passanger p WHERE p.username.username = :username").setParameter("username", uname).getSingleResult();

        rs = cc.getBookingsForPassanger(Response.class, String.valueOf(passanger.getPassangerid()));
        List<Booking> bookings = new ArrayList<>();
        GenericType<List<Booking>> gbookings = new GenericType<List<Booking>>() {
        };
        bookings = rs.readEntity(gbookings);
        return bookings;
    }

    public List<Booking> getAllTodayBooking() {
        List<Booking> bookings = showAllBookings();
        List<Booking> todayBooking = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        
        for (Booking b : bookings) {
            LocalDate bookingDate = b.getBookingdate().toInstant()
                                      .atZone(ZoneId.systemDefault())
                                      .toLocalDate();
            if (bookingDate.equals(currentDate)) {
                todayBooking.add(b);
            }
        }
        return todayBooking;
    }

    public List<Booking> getAllPreviousBooking() {
        List<Booking> bookings = showAllBookings();
        List<Booking> preBooking = new ArrayList<>();
                LocalDate currentDate = LocalDate.now();
        for (Booking b : bookings) {
            LocalDate bookingDate = b.getBookingdate().toInstant()
                                      .atZone(ZoneId.systemDefault())
                                      .toLocalDate();
            if (bookingDate.isBefore(currentDate)) {
                preBooking.add(b);
            }
        }
        return preBooking;
    }

    public String subscribeScheme() {
        try {
            Payment payment = new Payment();
            payment.setPaymentid(paymentid);
            payment.setAmount(price);
            payment.setPaymentdate(new Date());
            payment.setStatus(true);

            cc.makePayment(payment);
            System.out.println("Payment Success!!");

            String uname = keepRecord.getUsername();

            System.out.println("user : " + uname);

            Passanger passanger = (Passanger) em.createNamedQuery("Passanger.findByUsername").setParameter("username", uname).getSingleResult();
            System.out.println("passanger : " + passanger.getFullname());
            passanger.setPassangerid(passanger.getPassangerid());

            Scheme scheme = new Scheme();
            scheme.setSchemeid(schemeid);

            Subscripton subscripton = new Subscripton();
            subscripton.setAmount(price);
            subscripton.setPassangerid(passanger);
            subscripton.setPaymentid(payment);
            subscripton.setStartdate(new Date());
            subscripton.setSchemeid(scheme);
            subscripton.setStatus(true);

            cc.subscribeToScheme(subscripton);
            System.out.println("Subscription Success!!");

            Subscripton s = em.createQuery("SELECT s FROM Subscripton s WHERE s.paymentid.paymentid=:pid", Subscripton.class)
                    .setParameter("pid", payment.getPaymentid()).getSingleResult();

            String data = "SuratSwiftMetro/Subscription/" + s.getSubscriptionid();
            System.out.println(data);
            String path = "F:\\swiftsuratmetro\\SubscriptionQR\\QR" + s.getSubscriptionid();
            BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
            MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path));
            System.out.println("QR created.");

            sendSubscribeMail(s.getPassangerid().getUsername().getEmail(), s.getStartdate().toString(), s.getEnddate().toString(), s.getSubscriptionid());

            HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            res.sendRedirect("Passenger/myscheme.jsf");
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
        return "/index.jsf";
    }

    public void sendSubscribeMail(String to, String sd, String ed, int subId) throws MessagingException {
        try {
            String host = "smtp.gmail.com";
            String user = "suratswiftmetro@gmail.com";
            String password = "abitrgafxievusgn";
            String from = "suratswiftmetro@gmail.com";
            String subject = "Subscription Details";
            String message = "<html>"
                    + "<body>"
                    + "<div style='text-align: center;'>"
                    + "<h5>Start Date: " + sd + "</h5>"
                    + "<h5>End Date: " + ed + "</h5>"
                    + "<img src='cid:image_id1'>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            boolean sessionDebug = false;
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailsession = Session.getDefaultInstance(props, null);
            mailsession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailsession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            // Create a multipart message for HTML content and image
            MimeMultipart multipart = new MimeMultipart("related");

            // HTML part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html");
            multipart.addBodyPart(messageBodyPart);

            // Image part
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource("F:\\swiftsuratmetro\\SubscriptionQR\\QR" + subId); // Replace with the path to your image
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image_id1>");
            multipart.addBodyPart(messageBodyPart);

            // Set the multipart message to the email message
            msg.setContent(multipart);

//            msg.setText(message);
            Transport transport = mailsession.getTransport("smtp");
            transport.connect(host, user, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Mail Sent");
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            File ticketFile = new File("C:/Users/kisho/Downloads/SuratSwiftMetro/SuratSwiftMetro/src/main/webapp/ticket.html");
            FileWriter fw = new FileWriter(ticketFile);
            fw.write(ticket);
            fw.close();
            Random random = new Random();

            try (OutputStream os = new FileOutputStream("C:/Users/kisho/Downloads/SuratSwiftMetro/SuratSwiftMetro/src/main/webapp/tickets/ticket" + random.nextInt(10000) + ".pdf")) {
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

    public List<Subscripton> activeSubscriptionsByPassenger() {
        List<Subscripton> active = new ArrayList<>();
        for (Subscripton s : getSubscriptions()) {
            if (s.getStatus()) {
                System.out.println("Active : " + s.getStatus());
                active.add(s);
            }
        }
        return active;
    }

    public List<Subscripton> expiredSubscriptionsByPassenger() {
        List<Subscripton> expired = new ArrayList<>();
        for (Subscripton s : getSubscriptions()) {
            if (!s.getStatus()) {
                System.out.println("Expired : " + s.getStatus());
                expired.add(s);
            }
        }
        return expired;
    }

    public Passanger getProfileDetails() throws ParseException {
        Passanger passanger = cc.getPassangerById(Passanger.class, keepRecord.getUsername());
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        dob = df.format(passanger.getUsername().getDateofbirth());
        return passanger;
    }

    public void changeProfile() throws IOException {
        String profileName = getFilename(profile);
        String dir = "C:\\Users\\kisho\\Downloads\\SuratSwiftMetro\\SuratSwiftMetro\\src\\main\\webapp\\Passenger\\profile";
        OutputStream out = null;
        InputStream in = null;
        int read = 0;

        in = profile.getInputStream();
        out = new FileOutputStream(new File(dir + File.separator + profileName));
        final byte[] bytes = new byte[1024];
        while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        in.close();
        out.close();
        System.out.println("File Uploaded!!");
        Passanger passanger = cc.getPassangerById(Passanger.class, keepRecord.getUsername());
        passanger.setProfile(profileName);
        custEJB.ChangeProfile(passanger);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.sendRedirect("/SuratSwiftMetro/Passenger/profile.jsf");
    }

    public String getFilename(Part file) {
        System.out.println("Header : " + file.getHeader("content-disposition"));
        for (String f : file.getHeader("content-disposition").split(";")) {
            System.out.println("Header : " + f);
            if (f.trim().startsWith("filename")) {
                System.out.println("File : " + f.substring(f.indexOf('=') + 1));
                System.out.println("File : " + f.substring(f.indexOf('=') + 1).trim().replaceAll("\"", ""));
                String filename = f.substring(f.indexOf('=') + 1).trim().replaceAll("\"", "");
                System.out.println("File : " + filename.substring(filename.lastIndexOf('/') + 1));
                System.out.println("File : " + filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1));
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
}
