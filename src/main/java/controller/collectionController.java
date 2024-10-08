/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import Client.AdminClient;
import Client.UserClient;
import entity.CollectionStation;
import entity.Collection;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ayush
 */
@Named(value = "collectionController")
@RequestScoped
public class collectionController {

    AdminClient ac;
    Response res;
    List<Collection> collections;
    GenericType<List<Collection>> gCollections;
    List<CollectionStation> collectionStations;
    GenericType<List<CollectionStation>> gCollectionStations;

    /**
     * Creates a new instance of collectionController
     */
    @PostConstruct
    public void init() {
        ac = new AdminClient();
        collections = new ArrayList<>();
        gCollections = new GenericType<List<Collection>>() {};
        collectionStations = new ArrayList<>();
        gCollectionStations = new GenericType<List<CollectionStation>>() {};
        loadCollection();
        loadCollectionStation();
    }

    public void loadCollection() {
        res = ac.getTotalCollections(Response.class);
        collections = res.readEntity(gCollections);
    }
    public void loadCollectionStation(){
         res = ac.getAllCollectionStations(Response.class);
        collectionStations = res.readEntity(gCollectionStations);
    }
    public List<Collection> getAllCollection() {
        return collections;
    }

    public List<CollectionStation> getAllCollectionStation() {
        return collectionStations;
    }
}
