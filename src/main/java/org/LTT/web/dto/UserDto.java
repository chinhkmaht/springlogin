package org.LTT.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.LTT.validation.PasswordMatches;
import org.LTT.validation.ValidEmail;
import org.LTT.validation.ValidPassword;

@PasswordMatches
public class UserDto {
    @NotNull
    @Size(min = 1)
    private String firstName;

    @NotNull
    @Size(min = 1)
    private String lastName;

    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @Size(min = 1)
    private String email;

    private Long universityId;


    private Long companycardId;

    public void setCompanycardId(Long companycardId) {
        this.companycardId = companycardId;
    }

    public Long getCompanycardId() {
        return companycardId;
    }

    private boolean isUsing2FA;

    public Long getUniversityId() {
        return universityId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    private Integer role;
    private long roleId;

    public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public Integer getRole() {
        return role;
    }

    public void setRole(final Integer role) {
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(final String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public boolean isUsing2FA() {
        return isUsing2FA;
    }

    public void setUsing2FA(boolean isUsing2FA) {
        this.isUsing2FA = isUsing2FA;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [firstName=").append(firstName).append(", lastName=").append(lastName).append(", password=").append(password).append(", matchingPassword=").append(matchingPassword).append(", email=").append(email).append(", isUsing2FA=")
                .append(isUsing2FA).append(", role=") .append(role) .append(", role=").append(roleId) .append(", universityId=").append(universityId) .append(", companycardId=").append(companycardId).append("]");
        return builder.toString();
    }

}
