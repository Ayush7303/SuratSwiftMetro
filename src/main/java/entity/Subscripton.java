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
@Table(name = "subscripton")
@NamedQueries({
    @NamedQuery(name = "Subscripton.findAll", query = "SELECT s FROM Subscripton s"),
    @NamedQuery(name = "Subscripton.findBySubscriptionid", query = "SELECT s FROM Subscripton s WHERE s.subscriptionid = :subscriptionid"),
    @NamedQuery(name = "Subscripton.findByStartdate", query = "SELECT s FROM Subscripton s WHERE s.startdate = :startdate"),
    @NamedQuery(name = "Subscripton.findByEnddate", query = "SELECT s FROM Subscripton s WHERE s.enddate = :enddate"),
    @NamedQuery(name = "Subscripton.findByAmount", query = "SELECT s FROM Subscripton s WHERE s.amount = :amount"),
    @NamedQuery(name = "Subscripton.findByStatus", query = "SELECT s FROM Subscripton s WHERE s.status = :status")})
public class Subscripton implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "subscriptionid")
    private Integer subscriptionid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private double amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    
    @JoinColumn(name = "passangerid", referencedColumnName = "passangerid")
    @ManyToOne(optional = false)
    private Passanger passangerid;
    
    @JoinColumn(name = "paymentid", referencedColumnName = "paymentid")
    @ManyToOne(optional = false)
    private Payment paymentid;
    
    @JoinColumn(name = "schemeid", referencedColumnName = "schemeid")
    @ManyToOne(optional = false)
    private Scheme schemeid;

    public Subscripton() {
    }

    public Subscripton(Integer subscriptionid) {
        this.subscriptionid = subscriptionid;
    }

    public Subscripton(Integer subscriptionid, Date startdate, Date enddate, double amount, boolean status) {
        this.subscriptionid = subscriptionid;
        this.startdate = startdate;
        this.enddate = enddate;
        this.amount = amount;
        this.status = status;
    }

    public Integer getSubscriptionid() {
        return subscriptionid;
    }

    public void setSubscriptionid(Integer subscriptionid) {
        this.subscriptionid = subscriptionid;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Passanger getPassangerid() {
        return passangerid;
    }

    public void setPassangerid(Passanger passangerid) {
        this.passangerid = passangerid;
    }

    public Payment getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(Payment paymentid) {
        this.paymentid = paymentid;
    }

    public Scheme getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(Scheme schemeid) {
        this.schemeid = schemeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subscriptionid != null ? subscriptionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subscripton)) {
            return false;
        }
        Subscripton other = (Subscripton) object;
        if ((this.subscriptionid == null && other.subscriptionid != null) || (this.subscriptionid != null && !this.subscriptionid.equals(other.subscriptionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Subscripton[ subscriptionid=" + subscriptionid + " ]";
    }
    
}
