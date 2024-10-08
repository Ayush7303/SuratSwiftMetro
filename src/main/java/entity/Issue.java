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
@Table(name = "issue")
@NamedQueries({
    @NamedQuery(name = "Issue.findAll", query = "SELECT i FROM Issue i"),
    @NamedQuery(name = "Issue.findByIssueid", query = "SELECT i FROM Issue i WHERE i.issueid = :issueid"),
    @NamedQuery(name = "Issue.findByDescription", query = "SELECT i FROM Issue i WHERE i.description = :description"),
    @NamedQuery(name = "Issue.findByIssuedate", query = "SELECT i FROM Issue i WHERE i.issuedate = :issuedate"),
    @NamedQuery(name = "Issue.findByStatus", query = "SELECT i FROM Issue i WHERE i.status = :status")})
public class Issue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "issueid")
    private Integer issueid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "issuedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issuedate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "issuer", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private User issuer;

    public Issue() {
    }

    public Issue(Integer issueid) {
        this.issueid = issueid;
    }

    public Issue(Integer issueid, String description, Date issuedate, String status) {
        this.issueid = issueid;
        this.description = description;
        this.issuedate = issuedate;
        this.status = status;
    }

    public Integer getIssueid() {
        return issueid;
    }

    public void setIssueid(Integer issueid) {
        this.issueid = issueid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issuedate) {
        this.issuedate = issuedate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getIssuer() {
        return issuer;
    }

    public void setIssuer(User issuer) {
        this.issuer = issuer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (issueid != null ? issueid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Issue)) {
            return false;
        }
        Issue other = (Issue) object;
        if ((this.issueid == null && other.issueid != null) || (this.issueid != null && !this.issueid.equals(other.issueid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Issue[ issueid=" + issueid + " ]";
    }
    
}
