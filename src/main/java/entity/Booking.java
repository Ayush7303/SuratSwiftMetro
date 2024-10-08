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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ayush
 */
@Entity
@Table(name = "booking")
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b"),
    @NamedQuery(name = "Booking.findByBookingid", query = "SELECT b FROM Booking b WHERE b.bookingid = :bookingid"),
    @NamedQuery(name = "Booking.findByDistance", query = "SELECT b FROM Booking b WHERE b.distance = :distance"),
    @NamedQuery(name = "Booking.findByTotalfair", query = "SELECT b FROM Booking b WHERE b.totalfair = :totalfair"),
    @NamedQuery(name = "Booking.findByBookingdate", query = "SELECT b FROM Booking b WHERE b.bookingdate = :bookingdate"),
    @NamedQuery(name = "Booking.findByStatus", query = "SELECT b FROM Booking b WHERE b.status = :status")})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bookingid")
    private Integer bookingid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "distance")
    private double distance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalfair")
    private double totalfair;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bookingdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingdate;
    @Size(max = 200)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "destination", referencedColumnName = "stationid")
    @ManyToOne(optional = false)
    private Station destination;
    @JoinColumn(name = "passangerid", referencedColumnName = "passangerid")
    @ManyToOne(optional = false)
    private Passanger passangerid;
    @JoinColumn(name = "paymentid", referencedColumnName = "paymentid")
    @ManyToOne(optional = false)
    private Payment paymentid;
    @JoinColumn(name = "source", referencedColumnName = "stationid")
    @ManyToOne(optional = false)
    private Station source;

    public Booking() {
    }

    public Booking(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public Booking(Integer bookingid, double distance, double totalfair, Date bookingdate) {
        this.bookingid = bookingid;
        this.distance = distance;
        this.totalfair = totalfair;
        this.bookingdate = bookingdate;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTotalfair() {
        return totalfair;
    }

    public void setTotalfair(double totalfair) {
        this.totalfair = totalfair;
    }

    public Date getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(Date bookingdate) {
        this.bookingdate = bookingdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
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

    public Station getSource() {
        return source;
    }

    public void setSource(Station source) {
        this.source = source;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingid != null ? bookingid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.bookingid == null && other.bookingid != null) || (this.bookingid != null && !this.bookingid.equals(other.bookingid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Booking[ bookingid=" + bookingid + " ]";
    }
    
}
