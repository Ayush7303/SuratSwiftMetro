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
import javax.validation.constraints.Size;

/**
 *
 * @author ayush
 */
@Entity
@Table(name = "notification")
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
    @NamedQuery(name = "Notification.findByNotificationid", query = "SELECT n FROM Notification n WHERE n.notificationid = :notificationid"),
    @NamedQuery(name = "Notification.findByTitle", query = "SELECT n FROM Notification n WHERE n.title = :title"),
    @NamedQuery(name = "Notification.findByDescription", query = "SELECT n FROM Notification n WHERE n.description = :description"),
    @NamedQuery(name = "Notification.findByPublishdate", query = "SELECT n FROM Notification n WHERE n.publishdate = :publishdate")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "notificationid")
    private Integer notificationid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "publishdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishdate;

    public Notification() {
    }

    public Notification(Integer notificationid) {
        this.notificationid = notificationid;
    }

    public Notification(Integer notificationid, String title, String description, Date publishdate) {
        this.notificationid = notificationid;
        this.title = title;
        this.description = description;
        this.publishdate = publishdate;
    }

    public Integer getNotificationid() {
        return notificationid;
    }

    public void setNotificationid(Integer notificationid) {
        this.notificationid = notificationid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificationid != null ? notificationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.notificationid == null && other.notificationid != null) || (this.notificationid != null && !this.notificationid.equals(other.notificationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Notification[ notificationid=" + notificationid + " ]";
    }
    
}
