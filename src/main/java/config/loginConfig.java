/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author ayush
 */

//@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(loginPage = "/login.jsf"))

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/ssm",
        callerQuery = "select password from user where username=?",
        groupsQuery = "select rolename from role where username=?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30)

@Named(value = "loginConfig")
@ApplicationScoped
public class loginConfig {
    
}
