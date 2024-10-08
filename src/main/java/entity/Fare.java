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
import javax.validation.constraints.NotNull;

/**
 *
 * @author ayush
 */
@Entity
@Table(name = "fare")
@NamedQueries({
    @NamedQuery(name = "Fare.findAll", query = "SELECT f FROM Fare f"),
    @NamedQuery(name = "Fare.findByFareid", query = "SELECT f FROM Fare f WHERE f.fareid = :fareid"),
    @NamedQuery(name = "Fare.findByFare", query = "SELECT f FROM Fare f WHERE f.fare = :fare")})
public class Fare implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fareid")
    private Integer fareid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fare")
    private double fare;

    @JoinColumn(name = "destination", referencedColumnName = "stationid")
    @ManyToOne(optional = false)
    private Station destination;
    
    @JoinColumn(name = "source", referencedColumnName = "stationid")
    @ManyToOne(optional = false)
    private Station source;

    public Fare() {
    }

    public Fare(Integer fareid) {
        this.fareid = fareid;
    }

    public Fare(Integer fareid, double fare) {
        this.fareid = fareid;
        this.fare = fare;
    }

    public Integer getFareid() {
        return fareid;
    }

    public void setFareid(Integer fareid) {
        this.fareid = fareid;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public Station getSource() {
        return source;
    }

    public void setSource(Station source) {
        this.source = source;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fareid != null ? fareid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fare)) {
            return false;
        }
        Fare other = (Fare) object;
        if ((this.fareid == null && other.fareid != null) || (this.fareid != null && !this.fareid.equals(other.fareid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Fare[ fareid=" + fareid + " ]";
    }
    
}
