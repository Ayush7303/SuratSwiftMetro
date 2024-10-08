/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package record;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Set;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.CredentialValidationResult;

/**
 *
 * @author ayush
 */
@Named(value = "keepRecord")
@SessionScoped
public class keepRecord implements Serializable {
    private static CredentialValidationResult result;
    private static CallerPrincipal principal;
   private static Set<String> roles;
   private static String token;
   private static String username;
   private static String password;
   private static String errorStatus;
   private static Credential credential;

    public static String getErrorStatus() {
        return errorStatus;
    }

    public static Credential getCredential() {
        return credential;
    }

    public static void setCredential(Credential credential) {
        keepRecord.credential = credential;
    }

    public static void setErrorStatus(String errorStatus) {
        keepRecord.errorStatus = errorStatus;
    }

    public keepRecord() {
        principal=null;
       token=null;
       username=null;
       password=null;
       token=null;
       errorStatus="";
    }

   
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        keepRecord.username = username;
    }

    public  static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        keepRecord.password = password;
    }

    public static CredentialValidationResult getResult() {
        return result;
    }

    public static void setResult(CredentialValidationResult result) {
        keepRecord.result = result;
    }

    public static CallerPrincipal getPrincipal() {
        return principal;
    }

    public static void setPrincipal(CallerPrincipal principal) {
        keepRecord.principal = principal;
    }

    public static Set<String> getRoles() {
        return roles;
    }

    public static void setRoles(Set<String> roles) {
        keepRecord.roles = roles;
    }

    public  static String getToken() {
        return token;
    }

    public  static void setToken(String token) {
        keepRecord.token = token;
    }
   
    public static void reset()
    {
        
       principal=null;
       token=null;
       username=null;
       password=null;
       token=null;
       errorStatus="";
    }
}
