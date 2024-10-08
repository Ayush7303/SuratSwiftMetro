/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import ejb.customerEJBLocal;
import entity.Passanger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.Logger;
import record.keepRecord;

/**
 *
 * @author jeshanpatel1510
 */
@WebServlet(name = "FileUpload", urlPatterns = {"/FileUpload"})
@MultipartConfig
public class FileUpload extends HttpServlet {
    @PersistenceContext(unitName = "ssmpu")
    EntityManager em;
    @EJB customerEJBLocal custEJB;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

        }
    }

    public String getFilename(Part file) {
        for (String f : file.getHeader("content-disposition").split(";")) {
            if (f.trim().startsWith("filename")) {
                String filename = f.substring(f.indexOf('=') + 1).trim().replaceAll("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Random random = new Random();
        final String path = "F:\\swiftsuratmetro\\SuratSwiftMetro\\src\\main\\webapp\\Passenger\\profile";
        final Part filePart = request.getPart("profile");
        final String profile = "p"+random.nextInt(10000)+".png";
        final PrintWriter writer = response.getWriter();

        OutputStream output = null;
        InputStream input = null;

        try {
            output = new FileOutputStream(new File(path + File.separator + profile));
            input = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[5000];
            while ((read = input.read(bytes)) != -1) {
                output.write(bytes, 0, read);
            }
            Passanger passanger = (Passanger) em.createNamedQuery("Passanger.findByUsername").setParameter("username", keepRecord.getUsername()).getSingleResult();
            passanger.setProfile(profile);
            custEJB.ChangeProfile(passanger);
        } catch (Exception e) {
            writer.println("Exception : "+e.getMessage());
        } 
        output.close();
        input.close();
        response.sendRedirect("Passenger/profile.jsf");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
