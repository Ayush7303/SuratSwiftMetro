/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "collection")
@NamedQueries({
    @NamedQuery(name = "Collection.findAll", query = "SELECT c FROM Collection c"),
    @NamedQuery(name = "Collection.findByCollectionid", query = "SELECT c FROM Collection c WHERE c.collectionid = :collectionid"),
    @NamedQuery(name = "Collection.findByCollectiondate", query = "SELECT c FROM Collection c WHERE c.collectiondate = :collectiondate"),
    @NamedQuery(name = "Collection.findByTotalcollection", query = "SELECT c FROM Collection c WHERE c.totalcollection = :totalcollection")})
public class Collection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "collectionid")
    private Integer collectionid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "collectiondate")
    @Temporal(TemporalType.DATE)
    private Date collectiondate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalcollection")
    private double totalcollection;

    public Collection() {
    }

    public Collection(Integer collectionid) {
        this.collectionid = collectionid;
    }

    public Collection(Integer collectionid, Date collectiondate, double totalcollection) {
        this.collectionid = collectionid;
        this.collectiondate = collectiondate;
        this.totalcollection = totalcollection;
    }

    public Integer getCollectionid() {
        return collectionid;
    }

    public void setCollectionid(Integer collectionid) {
        this.collectionid = collectionid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (collectionid != null ? collectionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Collection)) {
            return false;
        }
        Collection other = (Collection) object;
        if ((this.collectionid == null && other.collectionid != null) || (this.collectionid != null && !this.collectionid.equals(other.collectionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Collection[ collectionid=" + collectionid + " ]";
    }
    
}
