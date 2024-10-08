/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ayush
 */
@Entity
@Table(name = "collection_station")
@NamedQueries({
    @NamedQuery(name = "CollectionStation.findAll", query = "SELECT c FROM CollectionStation c"),
    @NamedQuery(name = "CollectionStation.findByCollectionstationid", query = "SELECT c FROM CollectionStation c WHERE c.collectionstationid = :collectionstationid"),
    @NamedQuery(name = "CollectionStation.findByCollectiondate", query = "SELECT c FROM CollectionStation c WHERE c.collectiondate = :collectiondate"),
    @NamedQuery(name = "CollectionStation.findByTotalcollection", query = "SELECT c FROM CollectionStation c WHERE c.totalcollection = :totalcollection"),
    @NamedQuery(name = "CollectionStation.findByStatus", query = "SELECT c FROM CollectionStation c WHERE c.status = :status")})
public class CollectionStation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "collectionstationid")
    private Integer collectionstationid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "collectiondate")
    @Temporal(TemporalType.DATE)
    private Date collectiondate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalcollection")
    private double totalcollection;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    
    @JoinColumn(name = "cashier", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private User cashier;
    
    @JoinColumn(name = "stationid", referencedColumnName = "stationid")
    @ManyToOne(optional = false)
    private Station stationid;

    public CollectionStation() {
    }

    public CollectionStation(Integer collectionstationid) {
        this.collectionstationid = collectionstationid;
    }

    public CollectionStation(Integer collectionstationid, Date collectiondate, double totalcollection, boolean status) {
        this.collectionstationid = collectionstationid;
        this.collectiondate = collectiondate;
        this.totalcollection = totalcollection;
        this.status = status;
    }

    public Integer getCollectionstationid() {
        return collectionstationid;
    }

    public void setCollectionstationid(Integer collectionstationid) {
        this.collectionstationid = collectionstationid;
    }

    public Date getCollectiondate() {
        return collectiondate;
    }

    public void setCollectiondate(Date collectiondate) {
        this.collectiondate = collectiondate;
    }

    public double getTotalcollection() {
        return totalcollection;
    }

    public void setTotalcollection(double totalcollection) {
        this.totalcollection = totalcollection;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getCashier() {
        return cashier;
    }

    public void setCashier(User cashier) {
        this.cashier = cashier;
    }

    public Station getStationid() {
        return stationid;
    }

    public void setStationid(Station stationid) {
        this.stationid = stationid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (collectionstationid != null ? collectionstationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CollectionStation)) {
            return false;
        }
        CollectionStation other = (CollectionStation) object;
        if ((this.collectionstationid == null && other.collectionstationid != null) || (this.collectionstationid != null && !this.collectionstationid.equals(other.collectionstationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CollectionStation[ collectionstationid=" + collectionstationid + " ]";
    }
    
}
