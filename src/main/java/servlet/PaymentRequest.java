/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.razorpay.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import entity.Payment;

/**
 *
 * @author jeshanpatel1510
 */
@WebServlet(name = "PaymentRequest", urlPatterns = {"/PaymentRequest"})
public class PaymentRequest extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Payment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Payment at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        RazorpayClient razorpay;
        String orderid = null;
        int price = Integer.parseInt(request.getParameter("price"));
        System.out.println("Price : "+price);
        try {
            razorpay = new RazorpayClient("rzp_test_YD7Gi5TdKmWROP", "0dbJ6E1TTlEeO252xe832j8F");

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", price*100); // amount in the smallest currency unit
            orderRequest.put("currency", "INR");

            Order order = razorpay.orders.create(orderRequest);
            orderid = order.get("id");
        } catch (RazorpayException e) {
            System.out.println(e.getMessage());
        }
        response.getWriter().append(orderid);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RazorpayClient razorpay = new RazorpayClient("rzp_test_YD7Gi5TdKmWROP", "0dbJ6E1TTlEeO252xe832j8F");
               
            JSONObject options = new JSONObject();
            options.put("razorpay_order_id", request.getParameter("razorpay_order_id"));
            options.put("razorpay_payment_id", request.getParameter("razorpay_payment_id"));
            options.put("razorpay_signature", request.getParameter("razorpay_signature"));

            boolean status = Utils.verifyPaymentSignature(options, "0dbJ6E1TTlEeO252xe832j8F");
            if(status){
                Payment payment = new Payment();
                
            }else{
                response.getWriter().append("Payment Failed!!");
            }
        } catch (RazorpayException ex) {
            Logger.getLogger(PaymentRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
