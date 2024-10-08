/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByMobilenumber", query = "SELECT u FROM User u WHERE u.mobilenumber = :mobilenumber"),
    @NamedQuery(name = "User.findByDateofbirth", query = "SELECT u FROM User u WHERE u.dateofbirth = :dateofbirth"),
    @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender")})
public class User implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "mobilenumber")
    private String mobilenumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateofbirth")
//    @Temporal(TemporalTypeTemporalType.DATE)
    private Date dateofbirth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "gender")
    private String gender;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "username")
    private String username;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<Passanger> passangerCollection;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<Role> roleCollection;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "issuer")
    private Collection<Issue> issueCollection;
    
    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cashier")
    private Collection<CollectionStation> collectionStationCollection;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, String email, String mobilenumber, Date dateofbirth, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobilenumber = mobilenumber;
        this.dateofbirth = dateofbirth;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Collection<Passanger> getPassangerCollection() {
        return passangerCollection;
    }

    public void setPassangerCollection(Collection<Passanger> passangerCollection) {
        this.passangerCollection = passangerCollection;
    }

    public Collection<Role> getRoleCollection() {
        return roleCollection;
    }

    public void setRoleCollection(Collection<Role> roleCollection) {
        this.roleCollection = roleCollection;
    }

    public Collection<Issue> getIssueCollection() {
        return issueCollection;
    }

    public void setIssueCollection(Collection<Issue> issueCollection) {
        this.issueCollection = issueCollection;
    }

    public Collection<CollectionStation> getCollectionStationCollection() {
        return collectionStationCollection;
    }

    public void setCollectionStationCollection(Collection<CollectionStation> collectionStationCollection) {
        this.collectionStationCollection = collectionStationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.User[ username=" + username + " ]";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
