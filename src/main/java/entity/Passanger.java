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
@Table(name = "passanger")
@NamedQueries({
    @NamedQuery(name = "Passanger.findAll", query = "SELECT p FROM Passanger p"),
    @NamedQuery(name = "Passanger.findByPassangerid", query = "SELECT p FROM Passanger p WHERE p.passangerid = :passangerid"),
    @NamedQuery(name = "Passanger.findByType", query = "SELECT p FROM Passanger p WHERE p.type = :type"),
    @NamedQuery(name = "Passanger.findByFullname", query = "SELECT p FROM Passanger p WHERE p.fullname = :fullname"),
    @NamedQuery(name = "Passanger.findByUsername", query = "SELECT p FROM Passanger p WHERE p.username.username = :username"),
    @NamedQuery(name = "Passanger.findByProfile", query = "SELECT p FROM Passanger p WHERE p.profile = :profile")})

public class Passanger implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "fullname")
    private String fullname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "profile")
    private String profile;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "passangerid")
    private Integer passangerid;
    
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private User username;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "passangerid")
    private Collection<Booking> bookingCollection;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "passangerid")
    private Collection<Subscripton> subscriptonCollection;

    public Passanger() {
    }

    public Passanger(Integer passangerid) {
        this.passangerid = passangerid;
    }

    public Passanger(Integer passangerid, String type, String fullname, String profile) {
        this.passangerid = passangerid;
        this.type = type;
        this.fullname = fullname;
        this.profile = profile;
    }

    public Integer getPassangerid() {
        return passangerid;
    }

    public void setPassangerid(Integer passangerid) {
        this.passangerid = passangerid;
    }


    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public Collection<Booking> getBookingCollection() {
        return bookingCollection;
    }

    public void setBookingCollection(Collection<Booking> bookingCollection) {
        this.bookingCollection = bookingCollection;
    }

    public Collection<Subscripton> getSubscriptonCollection() {
        return subscriptonCollection;
    }

    public void setSubscriptonCollection(Collection<Subscripton> subscriptonCollection) {
        this.subscriptonCollection = subscriptonCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (passangerid != null ? passangerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Passanger)) {
            return false;
        }
        Passanger other = (Passanger) object;
        if ((this.passangerid == null && other.passangerid != null) || (this.passangerid != null && !this.passangerid.equals(other.passangerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Passanger[ passangerid=" + passangerid + " ]";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    
}
