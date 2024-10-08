/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.UserClient;
import entity.RestPasswordDTO;
import entity.User;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
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
import javax.servlet.http.HttpSession;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author ayush
 */
@Named(value = "forgotPassword")
@RequestScoped
public class forgotPassword {
    private String email;
    private String newPassword;
    private String enteredOtp;
    UserClient uc;
        Pbkdf2PasswordHashImpl pb;


    public String getEnteredOtp() {
        return enteredOtp;
    }

    public void setEnteredOtp(String enteredOtp) {
        this.enteredOtp = enteredOtp;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    /**
     * Creates a new instance of forgotPassword
     */
    public forgotPassword() {
        uc=new UserClient();
        pb=new Pbkdf2PasswordHashImpl();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String sendOTP() throws MessagingException{
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        // Store OTP in session
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("otp", String.valueOf(otp));
        session.setAttribute("email", email);
        try {
            String host = "smtp.gmail.com";
            String user = "suratswiftmetro@gmail.com";
            String password = "abitrgafxievusgn";
            String from = "suratswiftmetro@gmail.com";
            String subject = "Reset Password OTP.";
            String message = "<html>"
                    + "<body>"
                   + "OTP is:" + String.valueOf(otp) +"<br><br>"
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
            InternetAddress[] address = {new InternetAddress(email)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            // Create a multipart message for HTML content and image
            MimeMultipart multipart = new MimeMultipart("related");

            // HTML part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html");
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
        return "verifyOTP.jsf";
    }
    public String verifyOTP() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String sessionOtp = (String) session.getAttribute("otp");
        System.out.println(sessionOtp);
                System.out.println(enteredOtp);

        if (sessionOtp != null && sessionOtp.equals(enteredOtp)) {
            return "resetPassword.jsf"; // Navigate to reset password page
        } else {
            // Handle OTP mismatch case
            System.out.println("OTP Mismatch");
            return "Wrong OTP."; // Navigate to error page or show an error message
        }
    }
    public String resetPassword() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String em=(String) session.getAttribute("email");
        String enc = pb.generate(newPassword.toCharArray());
        RestPasswordDTO rs=new RestPasswordDTO();
        rs.setEmail(em);
        rs.setPassword(enc);
        uc.resetPassword(rs);
        return "login.jsf";  
    }
}
