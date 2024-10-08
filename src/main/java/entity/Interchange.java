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
@Table(name = "interchange")
@NamedQueries({
    @NamedQuery(name = "Interchange.findAll", query = "SELECT i FROM Interchange i"),
    @NamedQuery(name = "Interchange.findByInterchangeid", query = "SELECT i FROM Interchange i WHERE i.interchangeid = :interchangeid")})
public class Interchange implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "interchangeid")
    private Integer interchangeid;
    
    @JoinColumn(name = "routeid1", referencedColumnName = "routeid")
    @ManyToOne(optional = false)
    private Route routeid1;
    
    @JoinColumn(name = "routeid2", referencedColumnName = "routeid")
    @ManyToOne(optional = false)
    private Route routeid2;
    
    @JoinColumn(name = "stationid", referencedColumnName = "stationid")
    @ManyToOne(optional = false)
    private Station stationid;

    public Interchange() {
    }

    public Interchange(Integer interchangeid) {
        this.interchangeid = interchangeid;
    }

    public Integer getInterchangeid() {
        return interchangeid;
    }

    public void setInterchangeid(Integer interchangeid) {
        this.interchangeid = interchangeid;
    }

    public Route getRouteid1() {
        return routeid1;
    }

    public void setRouteid1(Route routeid1) {
        this.routeid1 = routeid1;
    }

    public Route getRouteid2() {
        return routeid2;
    }

    public void setRouteid2(Route routeid2) {
        this.routeid2 = routeid2;
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
        hash += (interchangeid != null ? interchangeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interchange)) {
            return false;
        }
        Interchange other = (Interchange) object;
        if ((this.interchangeid == null && other.interchangeid != null) || (this.interchangeid != null && !this.interchangeid.equals(other.interchangeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Interchange[ interchangeid=" + interchangeid + " ]";
    }
    
}
