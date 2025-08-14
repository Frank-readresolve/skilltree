package tech.readresolve.skilltree.entities;

import java.util.Objects;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import tech.readresolve.skilltree.misc.ExcludeFromJacocoGeneratedReport;

@Entity
@Table(name = "t_trainers")
public class Trainer extends BaseEntity {

    @Column(name = "code", updatable = false)
    @NaturalId
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "account_id", updatable = false)
    private Account account;

    protected Trainer() {
	// Required no-arg constructor
    }

    public Trainer(String code, Account account) {
	this.code = code;
	this.account = account;
    }

    public String getCode() {
	return code;
    }

    @SuppressWarnings("unused")
    @ExcludeFromJacocoGeneratedReport
    private void setCode(String code) {
	// Not updatable
	this.code = code;
    }

    public Account getAccount() {
	return account;
    }

    @SuppressWarnings("unused")
    @ExcludeFromJacocoGeneratedReport
    private void setAccount(Account account) {
	// Not updatable
	this.account = account;
    }

    /**
     * Indicates whether or not this {@code trainer} is equal to the given other
     * object.
     * <p>
     * This implementation fulfils the general contract of
     * {@link Object#equals(Object)} comparing the "natural id" of a
     * {@code Trainer} which is its {@code code} ignoring case.
     *
     * @param obj an object to compare to
     * @return {@code true} is this {@code trainer} is equal to {@code obj};
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	return obj instanceof Trainer other
		&& code.equalsIgnoreCase(other.code);
    }

    /**
     * Returns a hash code value for this {@code trainer}.
     * <p>
     * This implementation fulfils the general contract of
     * {@link Object#hashCode()} being consistent with {@link #equals(Object)}.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
	return Objects.hash(code);
    }

    @Override
    public String toString() {
	return String.format("{id=%s, code=%s, account=[LAZY_LOADED]}", getId(),
		code);
    }

}
