package org.LTT.persistence.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import org.jboss.aerogear.security.otp.api.Base32;

@Entity
@Table(name = "user_account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @Column(length = 60)
    private String password;

    private boolean enabled;

    private boolean isUsing2FA;

    private Long universityId;

    private Long localId;

    public Long getLocalId() {
        return localId;
    }

    public void setLocalId(Long localId) {
        this.localId = localId;
    }

    private long assignTo;
    private String timesheet;

    public String getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(String timesheet) {
        this.timesheet = timesheet;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    public Long getUniversityId() {

        return universityId;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "users_univer", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "univer_id", referencedColumnName = "id"))
    private University university;

    public void setAssignTo(long assignTo) {
        this.assignTo = assignTo;
    }

    public long getAssignTo() {

        return assignTo;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setModifileDate(Date modifileDate) {
        this.modifileDate = modifileDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getModifileDate() {
        return modifileDate;
    }

    private String secret;
    private Date createDate;
    private Date modifileDate;
    
    private long roleId;

    public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@ManyToMany(mappedBy = "users")
    private Collection<CompanyCard> companyCards;

    public void setCompanycardId(Long companycardId) {
        this.companycardId = companycardId;
    }

    public Long getCompanycardId() {
        return companycardId;

    }

    private Long companycardId;



    public Collection<CompanyCard> getCompanyCards() {
        return companyCards;
    }
    public void setCompanyCards(Collection<CompanyCard> companyCards) {
        this.companyCards = companyCards;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public User() {
        super();
        this.secret = Base32.random();
        this.enabled = false;

    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Collection<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isUsing2FA() {
        return isUsing2FA;
    }

    public void setUsing2FA(boolean isUsing2FA) {
        this.isUsing2FA = isUsing2FA;
    }

    public String getSecret() {
        return secret;
    }


    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((email == null) ? 0 : email.hashCode());
        return result;
    }

   

    public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	@Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final User user = (User) obj;

        if (!email.equals(user.email)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(id).append(", firstName=").append(firstName).append(", lastName=").append(lastName).append(", email=").append(email).append(", password=").append(password).append(", enabled=").append(enabled).append(", isUsing2FA=")
                .append(isUsing2FA).append(", secret=").append(secret).append(", role=").append(roleId) .append(", university=").append(university) .append(", companyCards=").append(companyCards) .append(", roles=").append(roles).append(", companyCards=").append(companyCards).append("]");
        return builder.toString();
    }

}