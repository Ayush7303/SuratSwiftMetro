/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filter;

import java.io.IOException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import static jwt.Constants.BEARER;
import record.keepRecord;

/**
 *
 * @author ayush
 */
@Provider
@PreMatching
public class RestFilter implements ClientRequestFilter {    
    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        System.out.println("In Form Auth Filter token : "+ keepRecord.getToken());
//        Cookie cookie = new Cookie("Token", keepRecord.getToken());
//        requestContext.getHeaders().add(HttpHeaders.COOKIE, cookie);
        requestContext.getHeaders().add(HttpHeaders.AUTHORIZATION,BEARER+ keepRecord.getToken());
//        requestContext.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer "+keepRecord.getToken());
        System.out.println("After cookie header Auth Client Filter token : "+ keepRecord.getToken());
    }
    
    
}
