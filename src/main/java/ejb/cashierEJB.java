/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ayush
 */
@Stateless
public class cashierEJB implements cashierEJBLocal {
    @PersistenceContext(unitName = "ssmpu")
    EntityManager em;

    @Override
    public void updateCollection(CollectionStation collectionStation) {
        em.merge(collectionStation);
    }

    @Override
    public void collectPayment(Collection collection) {
        Collection collection1 = em.find(Collection.class, collection.getCollectionid());
        System.out.println(collection1.getTotalcollection());
        System.out.println(collection1.getTotalcollection());
        double totalCollection = collection1.getTotalcollection() + collection.getTotalcollection();
        collection.setTotalcollection(totalCollection);
        em.merge(collection);
    }

    // Add business logic 
    @Override
    public Fare getFareBySourceAndDestination(Integer sourcce, Integer destination) {
        return (Fare) em.createQuery("SELECT f FROM Fare f WHERE f.source.stationid=:source AND f.destination.stationid=:destination").setParameter("source", sourcce).setParameter("destination", destination).getSingleResult();
    }
    // "Insert Code > Add Business Method")

    @Override
    public CollectionStation getCollectionStationByCashier(String cashier) {
        System.out.println("Cashier : "+cashier);
        return (CollectionStation) em.createQuery("SELECT cs FROM CollectionStation cs WHERE cs.cashier.username = :username and cs.status=:st").setParameter("username", cashier).setParameter("st", true).getSingleResult();
    }

    @Override
    public List<CollectionStation> getAllCollectionStationOfCashier(String username) {
        return em.createQuery("SELECT cs FROM CollectionStation cs WHERE cs.cashier.username=:un")
                .setParameter("un", username)
                .getResultList();
    }
    
}
