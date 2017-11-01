package org.LTT.persistence.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "companycard")
public class CompanyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "card_user", joinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Collection<User> users;

    @Length(min = 2, message = "*Your namePeriod must have at least 2 characters")
   	@NotEmpty(message = "*Please provide your nam")
    private String name;
    private  boolean status;
    
    @NotNull(message = "*Please provide user")
    private Long userId;
    private Date createDate;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    private Date modifileDate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setModifileDate(Date modifileDate) {
        this.modifileDate = modifileDate;
    }

    public Long getId() {

        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isStatus() {
        return status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getModifileDate() {
        return modifileDate;
    }
}
