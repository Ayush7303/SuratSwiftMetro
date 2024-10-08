/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ayush
 */
@Entity
@Table(name = "station")
@NamedQueries({
    @NamedQuery(name = "Station.findAll", query = "SELECT s FROM Station s"),
    @NamedQuery(name = "Station.findByStationid", query = "SELECT s FROM Station s WHERE s.stationid = :stationid"),
    @NamedQuery(name = "Station.findByStationname", query = "SELECT s FROM Station s WHERE s.stationname = :stationname"),
    @NamedQuery(name = "Station.findByLatitude", query = "SELECT s FROM Station s WHERE s.latitude = :latitude"),
    @NamedQuery(name = "Station.findByLongitude", query = "SELECT s FROM Station s WHERE s.longitude = :longitude"),
    @NamedQuery(name = "Station.findByArea", query = "SELECT s FROM Station s WHERE s.area = :area"),
    @NamedQuery(name = "Station.findByActive", query = "SELECT s FROM Station s WHERE s.active = :active")})
public class Station implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "stationname")
    private String stationname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private double latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private double longitude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "area")
    private String area;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stationid")
    private Integer stationid;
    
    @JsonbTransient
    @ManyToMany(mappedBy = "stationCollection")
    private Collection<Route> routeCollection;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destination")
    private Collection<Fare> fareCollection;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "source")
    private Collection<Fare> fareCollection1;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destination")
    private Collection<Booking> bookingCollection;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "source")
    private Collection<Booking> bookingCollection1;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stationid")
    private Collection<CollectionStation> collectionStationCollection;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "endstation")
    private Collection<Route> routeCollection1;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "startstation")
    private Collection<Route> routeCollection2;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currentstation")
    private Collection<StationLink> stationLinkCollection;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nextstation")
    private Collection<StationLink> stationLinkCollection1;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "previousstation")
    private Collection<StationLink> stationLinkCollection2;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stationid")
    private Collection<Interchange> interchangeCollection;

    public Station() {
    }

    public Station(Integer stationid) {
        this.stationid = stationid;
    }

    public Station(Integer stationid, String stationname, double latitude, double longitude, String area, boolean active) {
        this.stationid = stationid;
        this.stationname = stationname;
        this.latitude = latitude;
        this.longitude = longitude;
        this.area = area;
        this.active = active;
    }

    public Integer getStationid() {
        return stationid;
    }

    public void setStationid(Integer stationid) {
        this.stationid = stationid;
    }


    public Collection<Route> getRouteCollection() {
        return routeCollection;
    }

    public void setRouteCollection(Collection<Route> routeCollection) {
        this.routeCollection = routeCollection;
    }

    public Collection<Fare> getFareCollection() {
        return fareCollection;
    }

    public void setFareCollection(Collection<Fare> fareCollection) {
        this.fareCollection = fareCollection;
    }

    public Collection<Fare> getFareCollection1() {
        return fareCollection1;
    }

    public void setFareCollection1(Collection<Fare> fareCollection1) {
        this.fareCollection1 = fareCollection1;
    }

    public Collection<Booking> getBookingCollection() {
        return bookingCollection;
    }

    public void setBookingCollection(Collection<Booking> bookingCollection) {
        this.bookingCollection = bookingCollection;
    }

    public Collection<Booking> getBookingCollection1() {
        return bookingCollection1;
    }

    public void setBookingCollection1(Collection<Booking> bookingCollection1) {
        this.bookingCollection1 = bookingCollection1;
    }

    public Collection<CollectionStation> getCollectionStationCollection() {
        return collectionStationCollection;
    }

    public void setCollectionStationCollection(Collection<CollectionStation> collectionStationCollection) {
        this.collectionStationCollection = collectionStationCollection;
    }

    public Collection<Route> getRouteCollection1() {
        return routeCollection1;
    }

    public void setRouteCollection1(Collection<Route> routeCollection1) {
        this.routeCollection1 = routeCollection1;
    }

    public Collection<Route> getRouteCollection2() {
        return routeCollection2;
    }

    public void setRouteCollection2(Collection<Route> routeCollection2) {
        this.routeCollection2 = routeCollection2;
    }

    public Collection<StationLink> getStationLinkCollection() {
        return stationLinkCollection;
    }

    public void setStationLinkCollection(Collection<StationLink> stationLinkCollection) {
        this.stationLinkCollection = stationLinkCollection;
    }

    public Collection<StationLink> getStationLinkCollection1() {
        return stationLinkCollection1;
    }

    public void setStationLinkCollection1(Collection<StationLink> stationLinkCollection1) {
        this.stationLinkCollection1 = stationLinkCollection1;
    }

    public Collection<StationLink> getStationLinkCollection2() {
        return stationLinkCollection2;
    }

    public void setStationLinkCollection2(Collection<StationLink> stationLinkCollection2) {
        this.stationLinkCollection2 = stationLinkCollection2;
    }

    public Collection<Interchange> getInterchangeCollection() {
        return interchangeCollection;
    }

    public void setInterchangeCollection(Collection<Interchange> interchangeCollection) {
        this.interchangeCollection = interchangeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stationid != null ? stationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Station)) {
            return false;
        }
        Station other = (Station) object;
        if ((this.stationid == null && other.stationid != null) || (this.stationid != null && !this.stationid.equals(other.stationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Station[ stationid=" + stationid + " ]";
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
