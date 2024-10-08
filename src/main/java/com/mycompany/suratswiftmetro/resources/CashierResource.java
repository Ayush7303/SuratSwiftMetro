/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.suratswiftmetro.resources;

import ejb.cashierEJBLocal;
import entity.CollectionStation;
import entity.Fare;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author jeshanpatel1510
 */
@DeclareRoles({"Cashier"})
@RolesAllowed({"Cashier"})
@Path("cashier")
public class CashierResource {
    @EJB cashierEJBLocal cashierEJB;
    
    @GET
    @Path("collection/getBycashier/{cashier}")
    @Produces("application/json")
    public CollectionStation getCollectionStationByCashier(@PathParam("cashier") String cashier) {
        return cashierEJB.getCollectionStationByCashier(cashier);
    }
    
    @GET
    @Path("fare/getBySourceDestination/{source}/{destination}")
    @Produces("application/json")
    public Fare getFareBySourceAndDestination(@PathParam("source") Integer sourcce, @PathParam("destination") Integer destination) {
        return cashierEJB.getFareBySourceAndDestination(sourcce, destination);
    }
    
    @GET
    @Path("fare/getAllCollectionStationOfCashier/{username}")
    @Produces("application/json")
    public List<CollectionStation> getAllCollectionStationOfCashier(@PathParam("username") String username) {
        return cashierEJB.getAllCollectionStationOfCashier(username);
    }
    
    @POST
    @Path("collection/update")
    @Consumes("application/json")
    public void updateCollection(CollectionStation collectionStation) {
        cashierEJB.updateCollection(collectionStation);
    }
    
    @PUT
    @Path("collection/collect")
    @Consumes("application/json")
    public void collectPayment(entity.Collection collection) {
        cashierEJB.collectPayment(collection);
    }
}
