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
@Table(name = "schedule")
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s"),
    @NamedQuery(name = "Schedule.findByScheduleid", query = "SELECT s FROM Schedule s WHERE s.scheduleid = :scheduleid"),
    @NamedQuery(name = "Schedule.findByScheduledate", query = "SELECT s FROM Schedule s WHERE s.scheduledate = :scheduledate"),
    @NamedQuery(name = "Schedule.findByStarttime", query = "SELECT s FROM Schedule s WHERE s.starttime = :starttime"),
    @NamedQuery(name = "Schedule.findByExpectedendtime", query = "SELECT s FROM Schedule s WHERE s.expectedendtime = :expectedendtime"),
    @NamedQuery(name = "Schedule.findByStatus", query = "SELECT s FROM Schedule s WHERE s.status = :status")})
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "scheduleid")
    private Integer scheduleid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "scheduledate")
    @Temporal(TemporalType.DATE)
    private Date scheduledate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "starttime")
    @Temporal(TemporalType.TIME)
    private Date starttime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expectedendtime")
    @Temporal(TemporalType.TIME)
    private Date expectedendtime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    
    @JoinColumn(name = "metroid", referencedColumnName = "metroid")
    @ManyToOne(optional = false)
    private Metro metroid;
    
    @JoinColumn(name = "routeid", referencedColumnName = "routeid")
    @ManyToOne(optional = false)
    private Route routeid;

    public Schedule() {
    }

    public Schedule(Integer scheduleid) {
        this.scheduleid = scheduleid;
    }

    public Schedule(Integer scheduleid, Date scheduledate, Date starttime, Date expectedendtime, boolean status) {
        this.scheduleid = scheduleid;
        this.scheduledate = scheduledate;
        this.starttime = starttime;
        this.expectedendtime = expectedendtime;
        this.status = status;
    }

    public Integer getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Integer scheduleid) {
        this.scheduleid = scheduleid;
    }

    public Date getScheduledate() {
        return scheduledate;
    }

    public void setScheduledate(Date scheduledate) {
        this.scheduledate = scheduledate;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getExpectedendtime() {
        return expectedendtime;
    }

    public void setExpectedendtime(Date expectedendtime) {
        this.expectedendtime = expectedendtime;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Metro getMetroid() {
        return metroid;
    }

    public void setMetroid(Metro metroid) {
        this.metroid = metroid;
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
        hash += (scheduleid != null ? scheduleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.scheduleid == null && other.scheduleid != null) || (this.scheduleid != null && !this.scheduleid.equals(other.scheduleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Schedule[ scheduleid=" + scheduleid + " ]";
    }
    
}
