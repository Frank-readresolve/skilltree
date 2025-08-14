package tech.readresolve.skilltree.entities;

import java.util.Objects;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import tech.readresolve.skilltree.misc.ExcludeFromJacocoGeneratedReport;

@Entity
@Table(name = "t_accounts")
public class Account extends BaseEntity {

    @Column(name = "username", updatable = false)
    @NaturalId
    private String username;

    @Column(name = "account_password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", updatable = false)
    private Role role;

    protected Account() {
	// Required no-arg constructor
    }

    public Account(String username, Role role) {
	this.username = username;
	this.role = role;
    }

    public String getUsername() {
	return username;
    }

    @SuppressWarnings("unused")
    @ExcludeFromJacocoGeneratedReport
    private void setUsername(String username) {
	// Not updatable
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getFirstname() {
	return firstname;
    }

    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    public String getLastname() {
	return lastname;
    }

    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    public Role getRole() {
	return role;
    }

    @SuppressWarnings("unused")
    @ExcludeFromJacocoGeneratedReport
    private void setRole(Role role) {
	// Not updatable
	this.role = role;
    }

    /**
     * Indicates whether or not this {@code account} is equal to the given other
     * object.
     * <p>
     * This implementation fulfils the general contract of
     * {@link Object#equals(Object)} comparing the "natural id" of an
     * {@code Account} which is its {@code username} ignoring case.
     *
     * @param obj an object to compare to
     * @return {@code true} is this {@code account} is equal to {@code obj};
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	return obj instanceof Account other
		&& username.equalsIgnoreCase(other.username);
    }

    /**
     * Returns a hash code value for this {@code account}.
     * <p>
     * This implementation fulfils the general contract of
     * {@link Object#hashCode()} being consistent with {@link #equals(Object)}.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
	return Objects.hash(username);
    }

    @Override
    public String toString() {
	// DO NOT OUTPUT password!
	return String.format(
		"{id=%s, username=%s, [PROTECTED], "
			+ "firstname=%s, lastname=%s, role=[LAZY_LOADED]}",
		getId(), username, firstname, lastname);
    }

}
