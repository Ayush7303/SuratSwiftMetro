/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jeshanpatel1510
 */
@Named(value = "aboutController")
@RequestScoped
public class aboutController {
    @PersistenceContext(unitName = "ssmpu")
    EntityManager em;
    /**
     * Creates a new instance of aboutController
     */
    public aboutController() {        
    }
    
    public long userCounts(){
        long users = (long) em.createQuery("SELECT COUNT(u) FROM User u").getSingleResult();
        return users;
    }
    
    public long metroCounts(){
        long users = (long) em.createQuery("SELECT COUNT(m) FROM Metro m").getSingleResult();
        return users;
    }    
    
    public long stationCounts(){
        long users = (long) em.createQuery("SELECT COUNT(s) FROM Station s").getSingleResult();
        return users;
    }
    
    public double collectionCounts(){
        double users = (double) em.createQuery("SELECT SUM(c.totalcollection) FROM Collection c").getSingleResult();
        
        return users;
//return 0;
    }

    public long issueCounts(){
        long users = (long) em.createQuery("SELECT COUNT(i) FROM Issue i").getSingleResult();
        return users;
    }
}
