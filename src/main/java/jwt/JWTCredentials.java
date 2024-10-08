/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package jwt;

import java.io.Serializable;
import java.util.Set;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.security.enterprise.credential.Credential;

/**
 *
 * @author ayush
 */
@Named
@RequestScoped
public class JWTCredentials implements Credential, Serializable{

     private final String principal;
    private final Set<String> authorities;

    public JWTCredentials(String principal, Set<String> authorities) {
        this.principal = principal;
        this.authorities = authorities;
    }

    public String getPrincipal() {
        return principal;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }
}
