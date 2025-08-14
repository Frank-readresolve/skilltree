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
@Table(name = "t_certification_levels")
// Hibernate caching:
@Immutable // Required for read-only strategy
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@NaturalIdCache
public class CertificationLevel extends BaseEntity {

    @Column(name = "code", insertable = false, updatable = false)
    @NaturalId
    private String code;

    @Column(name = "french_level", insertable = false, updatable = false)
    private String frenchLevel;

    @Column(name = "european_level", insertable = false, updatable = false)
    private int europeanLevel;

    @Column(name = "equivalence", insertable = false, updatable = false)
    private String equivalence;

    protected CertificationLevel() {
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

    public String getFrenchLevel() {
	return frenchLevel;
    }

    @SuppressWarnings("unused")
    @ExcludeFromJacocoGeneratedReport
    private void setFrenchLevel(String level) {
	// Not insertable/updatable
	frenchLevel = level;
    }

    public int getEuropeanLevel() {
	return europeanLevel;
    }

    @SuppressWarnings("unused")
    @ExcludeFromJacocoGeneratedReport
    private void setEuropeanLevel(int level) {
	// Not insertable/updatable
	europeanLevel = level;
    }

    public String getEquivalence() {
	return equivalence;
    }

    @SuppressWarnings("unused")
    @ExcludeFromJacocoGeneratedReport
    private void setEquivalence(String equivalence) {
	// Not insertable/updatable
	this.equivalence = equivalence;
    }

    /**
     * Indicates whether or not this {@code level} is equal to the given other
     * object.
     * <p>
     * This implementation fulfils the general contract of
     * {@link Object#equals(Object)} comparing the "natural id" of a
     * {@code CertificationLevel} which is its {@code code} ignoring case.
     *
     * @param obj an object to compare to
     * @return {@code true} is this {@code level} is equal to {@code obj};
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	return obj instanceof CertificationLevel other
		&& code.equalsIgnoreCase(other.code);
    }

    /**
     * Returns a hash code value for this {@code level}.
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
	return String.format(
		"{id=%s, code=%s, frenchLevel=%s, europeanLevel=%s, equivalence=%s}",
		getId(), code, frenchLevel, europeanLevel, equivalence);
    }

}
