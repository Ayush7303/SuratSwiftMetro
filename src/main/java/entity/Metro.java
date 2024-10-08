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
@Table(name = "metro")
@NamedQueries({
    @NamedQuery(name = "Metro.findAll", query = "SELECT m FROM Metro m"),
    @NamedQuery(name = "Metro.findByMetroid", query = "SELECT m FROM Metro m WHERE m.metroid = :metroid"),
    @NamedQuery(name = "Metro.findBySections", query = "SELECT m FROM Metro m WHERE m.sections = :sections"),
    @NamedQuery(name = "Metro.findByMetroname", query = "SELECT m FROM Metro m WHERE m.metroname = :metroname"),
    @NamedQuery(name = "Metro.findBySeats", query = "SELECT m FROM Metro m WHERE m.seats = :seats")})
public class Metro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "metroid")
    private Integer metroid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sections")
    private int sections;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "metroname")
    private String metroname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seats")
    private int seats;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "metroid")
    private Collection<Schedule> scheduleCollection;

    public Metro() {
    }

    public Metro(Integer metroid) {
        this.metroid = metroid;
    }

    public Metro(Integer metroid, int sections, String metroname, int seats) {
        this.metroid = metroid;
        this.sections = sections;
        this.metroname = metroname;
        this.seats = seats;
    }

    public Integer getMetroid() {
        return metroid;
    }

    public void setMetroid(Integer metroid) {
        this.metroid = metroid;
    }

    public int getSections() {
        return sections;
    }

    public void setSections(int sections) {
        this.sections = sections;
    }

    public String getMetroname() {
        return metroname;
    }

    public void setMetroname(String metroname) {
        this.metroname = metroname;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (metroid != null ? metroid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metro)) {
            return false;
        }
        Metro other = (Metro) object;
        if ((this.metroid == null && other.metroid != null) || (this.metroid != null && !this.metroid.equals(other.metroid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Metro[ metroid=" + metroid + " ]";
    }
    
}
