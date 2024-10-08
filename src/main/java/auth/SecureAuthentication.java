/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package auth;

import controller.loginController;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jwt.JWTCredentials;
import jwt.TokenProvider;
import record.keepRecord;

/**
 *
 * @author ayush
 */
@Named(value = "newJSFManagedBean")
@RequestScoped
public class SecureAuthentication implements HttpAuthenticationMechanism, Serializable {

    @Inject
    IdentityStoreHandler handler;
    CredentialValidationResult result;
    AuthenticationStatus status;
    @Inject
    TokenProvider tokenProvider;
    @Inject
    loginController lcontroller;
    Cookie cookie;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
        String token = extractToken(httpMessageContext);

        try {
            if (token == null && lcontroller.getUsername() != null) {

                String username = lcontroller.getUsername();
                String password = lcontroller.getPassword();
                AuthenticationStatus status = lcontroller.getStatus();
                Credential credential = new UsernamePasswordCredential(username, new Password(password));
                result = handler.validate(credential);
                System.out.println(result.getStatus());
                if (result.getStatus() == CredentialValidationResult.Status.VALID) {
                    status = createToken(result, httpMessageContext);
                    status = httpMessageContext.notifyContainerAboutLogin(result);

                    keepRecord.setUsername(username);
                    keepRecord.setPassword(password);

                    keepRecord.setPrincipal(result.getCallerPrincipal());
                    keepRecord.setRoles(result.getCallerGroups());
                    request.getSession().setAttribute("username", username);
                    String role = "";
                    for(String r : result.getCallerGroups()){
                        role = r;
                    }
                    request.getSession().setAttribute("role", role);

                    lcontroller.setStatus(status);
                    lcontroller.setRoles(result.getCallerGroups());
                    return status;
                } else {
                    lcontroller.setError("*Username or Password is invalid!!");
                    lcontroller.setStatus(AuthenticationStatus.SEND_FAILURE);
//                    FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf);

                    httpMessageContext.getResponse().sendRedirect("login.jsf?error=" + lcontroller.getError());
//                   httpMessageContext.getRequest().getRequestDispatcher("login.jsf").forward(request, response);
                }
            }
            if (keepRecord.getToken() != null) {
//                Credential credential = new UsernamePasswordCredential(keepRecord.getUsername(), new Password(keepRecord.getPassword()));
//                result = handler.validate(cred/ential);
//                AuthenticationStatus status = createToken(result, httpMessageContext);
                httpMessageContext.notifyContainerAboutLogin(keepRecord.getPrincipal(), keepRecord.getRoles());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (token != null) {
            return validateToken(token, httpMessageContext);
        } else if (httpMessageContext.isProtected()) {
            System.out.println("User Unauthorized");
//            try {
//                httpMessageContext.getResponse().sendRedirect("/SuratSwiftMetro/forbidden.jsf");
////                httpMessageContext.getRequest().getRequestDispatcher("login.jsf").forward(request, response);
////                response.sendRedirect("/SuratSwiftMetro/forbidden.jsf");
//            } catch (IOException ex) {
//                Logger.getLogger(SecureAuthentication.class.getName()).log(Level.SEVERE, null, ex);
//            }
            return httpMessageContext.responseUnauthorized();
        }
        return httpMessageContext.doNothing();
    }

    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
//        if (!isRememberMe(context)) {
            String Jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), Boolean.FALSE);
            keepRecord.setToken(Jwt);
            System.out.println(Jwt);
            
            cookie = new Cookie("Token", Jwt);
            cookie.setMaxAge(2592000);
            context.getResponse().addCookie(cookie);
//        }
        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    public Boolean isRememberMe(HttpMessageContext context) {
        return Boolean.valueOf(context.getRequest().getParameter("rememberme"));
    }

    private String extractToken(HttpMessageContext context) {
        Cookie AuthHeader[] = context.getRequest().getCookies();
        if (AuthHeader != null) {
            int i = 0;
            for (Cookie c : AuthHeader) {
                if (c.getName().equals("Token")) {
                    String token = AuthHeader[i].getValue();
                    return token;
                }
                i++;
            }
        }
        return null;
    }

    //Done
    private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
        try {
            if (tokenProvider.validateToken(token)) {
                JWTCredentials credentials = tokenProvider.getCredential(token);
                Cookie cookie[] = context.getRequest().getCookies();
                if (cookie != null) {
                    int i = 0;
                    for (Cookie c : cookie) {
                        Cookie coki = cookie[i];
                        if (coki.getName().equals("Token")) {
                            coki.setMaxAge(0);
                            context.getResponse().addCookie(coki);
                        }
                        i++;
                    }
                }
                return context.notifyContainerAboutLogin(credentials.getPrincipal(), credentials.getAuthorities());
            }
            System.out.println("Unauthorized!!");
            context.getResponse().sendRedirect("/login.jsf?faces-redirect=true");
            return context.responseUnauthorized();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            return context.responseUnauthorized();
        }
    }

}
