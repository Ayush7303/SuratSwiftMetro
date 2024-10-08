/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.UserClient;
//import entity.Passanger;
import entity.PassengerUser;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import jwt.TokenProvider;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import record.keepRecord;
import javax.mail.*;
import javax.mail.internet.*;
import javax.ws.rs.core.Response;

/**
 *
 * @author ayush
 */
@Named(value = "loginController")
@RequestScoped
public class loginController {

    @Inject
    SecurityContext ctx;
    private String username;
    private String password;
    private String role;
    private Set<String> roles;
    private String error;
    String confirmPassword;

    private AuthenticationStatus status;
    PassengerUser newPassenger;
    UserClient ac;
    Pbkdf2PasswordHashImpl pb;
    private String usernameMessage;
        private String emailMessage;
    private String passwordMatchMessage;

    /**
     * Creates a new instance of loginController
     */
    public loginController() {
        ac = new UserClient();
        newPassenger = new PassengerUser();
        pb = new Pbkdf2PasswordHashImpl();
    }

    public void checkUsername() {
        if (ac.isUsernameAvailable(Boolean.class, newPassenger.getUsername())) {
            usernameMessage = "*Username is available  ";
        } else {
            usernameMessage = "*Username is already taken  ";
        }
    }
    public void checkEmail() {
        if (ac.isEmailAvailable(Boolean.class, newPassenger.getEmail())) {
            emailMessage = "*";
        } else {
            emailMessage = "*Email is already registered.";
        }
    }

    public void checkPasswordMatch() {
        if (newPassenger.getPassword() != null && confirmPassword != null) {
            if (newPassenger.getPassword().equals(confirmPassword)) {
                passwordMatchMessage = "Passwords match!";
            } else {
                passwordMatchMessage = "Passwords do not match!";
            }
        }
    }

    public String getPasswordMatchMessage() {
        return passwordMatchMessage;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }

    public void setPasswordMatchMessage(String passwordMatchMessage) {
        this.passwordMatchMessage = passwordMatchMessage;
    }

    public String getUsernameMessage() {
        return usernameMessage;
    }

    public void setUsernameMessage(String usernameMessage) {
        this.usernameMessage = usernameMessage;
    }

    public String register() throws MessagingException {
        String pwd = newPassenger.getPassword();
        String enc = pb.generate(pwd.toCharArray());
        System.out.println(enc + " " + newPassenger.getFullname());
        newPassenger.setPassword(enc);
        newPassenger.setProfile("NULL");
        sendMail(newPassenger.getEmail(), newPassenger.getUsername());
        ac.register(newPassenger);
        return "login.jsf";
    }

    public String login() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        Credential c = new UsernamePasswordCredential(username, new Password(password));
        AuthenticationStatus auth = ctx.authenticate(request, response, withParams().credential(c));

        if (auth.equals(auth.SUCCESS)) {
            keepRecord.setUsername(username);
            keepRecord.setPassword(password);
            if (ctx.isCallerInRole("Admin") && role.equals("Admin")) {
                role = "Admin";
                return "Admin/home.jsf?faces-redirect=true";
            } else if (ctx.isCallerInRole("Passenger") && role.equals("Passenger")) {
                role = "Passenger";
                return "index.jsf?faces-redirect=true";
            } else if (ctx.isCallerInRole("Cashier") && role.equals("Cashier")) {
                role = "Cashier";
                return "Cashier/home.jsf?faces-redirect=true";
            }
        } else {
            error = "Username or Password is incorrect.";
        }
        return null;
    }

    public String logout() throws ServletException {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        HttpSession session = req.getSession();
        session.invalidate();
        req.logout();
        keepRecord.reset();

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            res.addCookie(cookie);
        }
        return "/index.jsf?faces-redirect=true";
    }

    public void sendMail(String to, String username) throws MessagingException {
        try {
            String host = "smtp.gmail.com";
            String user = "suratswiftmetro@gmail.com";
            String password = "abitrgafxievusgn";
            String from = "suratswiftmetro@gmail.com";
            String subject = "Welcome to SuratSwiftMetro!";
            String message = "<html>"
                    + "<body>"
                    + "<div style='text-align: center;'>"
                    + "<img src='cid:image2_id'  width='170px' height='23px'><br>"
                    + "<img src='cid:image_id'>"
                    + "</div>"
                    + "<br><br>Dear " + username + ",<br><br>"
                    + "Welcome to the SuratSwiftMetro!<br><br>"
                    + "We are delighted to have you with us as we embark on this journey to revolutionize urban transportation in Surat. Enjoy fast, eco-friendly, and convenient travel across the city with our state-of-the-art metro system.<br><br>"
                    + "If you have any questions or need assistance, our team is here to help.<br><br>"
                    + "Thank you for choosing SuratSwiftMetro. We look forward to serving you!<br><br>"
                    + "Best regards,<br>"
                    + "The SuratSwiftMetro Team.<br><br>"
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
            DataSource fds = new FileDataSource("F:\\swiftsuratmetro\\SuratSwiftMetro\\src\\main\\webapp\\welcome.png"); // Replace with the path to your image
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image_id>");
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            String imagePath2 = "F:\\swiftsuratmetro\\SuratSwiftMetro\\src\\main\\webapp\\images\\logo2.png"; // Use an absolute path for testing
            DataSource fds2 = new FileDataSource(imagePath2);
            messageBodyPart.setDataHandler(new DataHandler(fds2));
            messageBodyPart.setHeader("Content-ID", "<image2_id>");
            multipart.addBodyPart(messageBodyPart);

            // Set the multipart message to the email message
            msg.setContent(multipart);

//            msg.setText(message);
            Transport transport = mailsession.getTransport("smtp");
            transport.connect(host, user, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Mail Sent");
        } catch (AddressException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PassengerUser getNewPassenger() {
        return newPassenger;
    }

    public void setNewPassenger(PassengerUser newPassenger) {
        this.newPassenger = newPassenger;
    }

    public AuthenticationStatus getStatus() {
        return status;
    }

    public void setStatus(AuthenticationStatus status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
