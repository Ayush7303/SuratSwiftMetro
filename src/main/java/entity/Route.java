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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "route")
@NamedQueries({
    @NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r"),
    @NamedQuery(name = "Route.findByRouteid", query = "SELECT r FROM Route r WHERE r.routeid = :routeid"),
    @NamedQuery(name = "Route.findByRoutenumber", query = "SELECT r FROM Route r WHERE r.routenumber = :routenumber"),
    @NamedQuery(name = "Route.findByDescription", query = "SELECT r FROM Route r WHERE r.description = :description"),
    @NamedQuery(name = "Route.findByDistance", query = "SELECT r FROM Route r WHERE r.distance = :distance")})
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "routeid")
    private Integer routeid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "routenumber")
    private String routenumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "distance")
    private double distance;
    
    @JoinTable(name = "route_station", joinColumns = {
        @JoinColumn(name = "routeid", referencedColumnName = "routeid")}, inverseJoinColumns = {
        @JoinColumn(name = "stationid", referencedColumnName = "stationid")})
//    @JsonbTransient
    @ManyToMany
    private Collection<Station> stationCollection;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "routeid")
    private Collection<Schedule> scheduleCollection;
    
    @JoinColumn(name = "endstation", referencedColumnName = "stationid")
    @ManyToOne(optional = false)
    private Station endstation;
    
    @JoinColumn(name = "startstation", referencedColumnName = "stationid")
    @ManyToOne(optional = false)
    private Station startstation;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "routeid")
    private Collection<StationLink> stationLinkCollection;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "routeid1")
    private Collection<Interchange> interchangeCollection;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "routeid2")
    private Collection<Interchange> interchangeCollection1;

    public Route() {
    }

    public Route(Integer routeid) {
        this.routeid = routeid;
    }

    public Route(Integer routeid, String routenumber, String description, double distance) {
        this.routeid = routeid;
        this.routenumber = routenumber;
        this.description = description;
        this.distance = distance;
    }

    public Integer getRouteid() {
        return routeid;
    }

    public void setRouteid(Integer routeid) {
        this.routeid = routeid;
    }

    public String getRoutenumber() {
        return routenumber;
    }

    public void setRoutenumber(String routenumber) {
        this.routenumber = routenumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Collection<Station> getStationCollection() {
        return stationCollection;
    }

    public void setStationCollection(Collection<Station> stationCollection) {
        this.stationCollection = stationCollection;
    }

    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    public Station getEndstation() {
        return endstation;
    }

    public void setEndstation(Station endstation) {
        this.endstation = endstation;
    }

    public Station getStartstation() {
        return startstation;
    }

    public void setStartstation(Station startstation) {
        this.startstation = startstation;
    }

    public Collection<StationLink> getStationLinkCollection() {
        return stationLinkCollection;
    }

    public void setStationLinkCollection(Collection<StationLink> stationLinkCollection) {
        this.stationLinkCollection = stationLinkCollection;
    }

    public Collection<Interchange> getInterchangeCollection() {
        return interchangeCollection;
    }

    public void setInterchangeCollection(Collection<Interchange> interchangeCollection) {
        this.interchangeCollection = interchangeCollection;
    }

    public Collection<Interchange> getInterchangeCollection1() {
        return interchangeCollection1;
    }

    public void setInterchangeCollection1(Collection<Interchange> interchangeCollection1) {
        this.interchangeCollection1 = interchangeCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (routeid != null ? routeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Route)) {
            return false;
        }
        Route other = (Route) object;
        if ((this.routeid == null && other.routeid != null) || (this.routeid != null && !this.routeid.equals(other.routeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Route[ routeid=" + routeid + " ]";
    }
    
}
