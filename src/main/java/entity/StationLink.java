/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ayush
 */
@Entity
@Table(name = "station_link")
@NamedQueries({
    @NamedQuery(name = "StationLink.findAll", query = "SELECT s FROM StationLink s"),
    @NamedQuery(name = "StationLink.findByStationlinkid", query = "SELECT s FROM StationLink s WHERE s.stationlinkid = :stationlinkid")})
public class StationLink implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stationlinkid")
    private Integer stationlinkid;
    
    @JoinColumn(name = "currentstation", referencedColumnName = "stationid")
    @ManyToOne(optional = false)
    private Station currentstation;
    
    @JoinColumn(name = "nextstation", referencedColumnName = "stationid")
    @ManyToOne(optional = false)
    private Station nextstation;
    
    @JoinColumn(name = "previousstation", referencedColumnName = "stationid")
    @ManyToOne(optional = false)
    private Station previousstation;
    
    @JoinColumn(name = "routeid", referencedColumnName = "routeid")
    @ManyToOne(optional = false)
    private Route routeid;

    public StationLink() {
    }

    public StationLink(Integer stationlinkid) {
        this.stationlinkid = stationlinkid;
    }

    public Integer getStationlinkid() {
        return stationlinkid;
    }

    public void setStationlinkid(Integer stationlinkid) {
        this.stationlinkid = stationlinkid;
    }

    public Station getCurrentstation() {
        return currentstation;
    }

    public void setCurrentstation(Station currentstation) {
        this.currentstation = currentstation;
    }

    public Station getNextstation() {
        return nextstation;
    }

    public void setNextstation(Station nextstation) {
        this.nextstation = nextstation;
    }

    public Station getPreviousstation() {
        return previousstation;
    }

    public void setPreviousstation(Station previousstation) {
        this.previousstation = previousstation;
    }

    public Route getRouteid() {
        return routeid;
    }

    public void setRouteid(Route routeid) {
        this.routeid = routeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stationlinkid != null ? stationlinkid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StationLink)) {
            return false;
        }
        StationLink other = (StationLink) object;
        if ((this.stationlinkid == null && other.stationlinkid != null) || (this.stationlinkid != null && !this.stationlinkid.equals(other.stationlinkid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StationLink[ stationlinkid=" + stationlinkid + " ]";
    }
    
}
