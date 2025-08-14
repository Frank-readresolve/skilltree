package tech.readresolve.skilltree.entities;

import java.util.Objects;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import tech.readresolve.skilltree.misc.ExcludeFromJacocoGeneratedReport;

@Entity
@Table(name = "t_roles")
// Hibernate caching:
@Immutable // Required for read-only strategy
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NaturalIdCache
public class Role extends BaseEntity {

    public static final String DEFAULT = "ROLE_TRAINER";

    @Column(name = "code", insertable = false, updatable = false)
    @NaturalId
    private String code;

    @Column(name = "role_name", insertable = false, updatable = false)
    private String name;

    protected Role() {
	// Required no-arg constructor
    }

    public String getCode() {
	return code;
    }

    @SuppressWarnings("unused")
    @ExcludeFromJacocoGeneratedReport
    private void setCode(String code) {
	// Not insertable/updatable
	this.code = code;
    }

    public String getName() {
	return name;
    }

    @SuppressWarnings("unused")
    @ExcludeFromJacocoGeneratedReport
    private void setName(String name) {
	// Not insertable/updatable
	this.name = name;
    }

    /**
     * Indicates whether or not this {@code role} is equal to the given other
     * object.
     * <p>
     * This implementation fulfils the general contract of
     * {@link Object#equals(Object)} comparing the "natural id" of a
     * {@code Role} which is its {@code code} ignoring case.
     *
     * @param obj an object to compare to
     * @return {@code true} is this {@code role} is equal to {@code obj};
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	return obj instanceof Role other && code.equalsIgnoreCase(other.code);
    }

    /**
     * Returns a hash code value for this {@code role}.
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
	return String.format("{id=%s, code=%s, name=%s}", getId(), code, name);
    }

}
