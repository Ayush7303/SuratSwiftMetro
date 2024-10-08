/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.*;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ayush
 */
@Local
public interface cashierEJBLocal {
    CollectionStation getCollectionStationByCashier(String cashier);
    void updateCollection(CollectionStation collectionStation);
    void collectPayment(Collection collection);
    Fare getFareBySourceAndDestination(Integer sourcce,Integer destination);
    List<CollectionStation> getAllCollectionStationOfCashier(String username);
}
