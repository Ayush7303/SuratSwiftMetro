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
@Table(name = "scheme")
@NamedQueries({
    @NamedQuery(name = "Scheme.findAll", query = "SELECT s FROM Scheme s"),
    @NamedQuery(name = "Scheme.findBySchemeid", query = "SELECT s FROM Scheme s WHERE s.schemeid = :schemeid"),
    @NamedQuery(name = "Scheme.findBySchemename", query = "SELECT s FROM Scheme s WHERE s.schemename = :schemename"),
    @NamedQuery(name = "Scheme.findBySchemedescription", query = "SELECT s FROM Scheme s WHERE s.schemedescription = :schemedescription"),
    @NamedQuery(name = "Scheme.findByDiscount", query = "SELECT s FROM Scheme s WHERE s.discount = :discount"),
    @NamedQuery(name = "Scheme.findByValidity", query = "SELECT s FROM Scheme s WHERE s.validity = :validity"),
    @NamedQuery(name = "Scheme.findByPassangertype", query = "SELECT s FROM Scheme s WHERE s.passangertype = :passangertype")})
public class Scheme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "schemeid")
    private Integer schemeid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "schemename")
    private String schemename;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "schemedescription")
    private String schemedescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount")
    private double discount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "validity")
    private int validity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "passangertype")
    private String passangertype;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schemeid")
    private Collection<Subscripton> subscriptonCollection;

    public Scheme() {
    }

    public Scheme(Integer schemeid) {
        this.schemeid = schemeid;
    }

    public Scheme(Integer schemeid, String schemename, String schemedescription, double discount, int validity, String passangertype) {
        this.schemeid = schemeid;
        this.schemename = schemename;
        this.schemedescription = schemedescription;
        this.discount = discount;
        this.validity = validity;
        this.passangertype = passangertype;
    }

    public Integer getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(Integer schemeid) {
        this.schemeid = schemeid;
    }

    public String getSchemename() {
        return schemename;
    }

    public void setSchemename(String schemename) {
        this.schemename = schemename;
    }

    public String getSchemedescription() {
        return schemedescription;
    }

    public void setSchemedescription(String schemedescription) {
        this.schemedescription = schemedescription;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getValidity() {
        return validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }

    public String getPassangertype() {
        return passangertype;
    }

    public void setPassangertype(String passangertype) {
        this.passangertype = passangertype;
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
        hash += (schemeid != null ? schemeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scheme)) {
            return false;
        }
        Scheme other = (Scheme) object;
        if ((this.schemeid == null && other.schemeid != null) || (this.schemeid != null && !this.schemeid.equals(other.schemeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Scheme[ schemeid=" + schemeid + " ]";
    }
    
}
