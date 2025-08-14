package tech.readresolve.skilltree.entities;

import java.time.Year;
import java.util.Objects;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_certifications")
public class Certification extends BaseEntity {

    @Column(name = "code")
    @NaturalId
    private String code;

    @Column(name = "certification_name")
    private String name;

    @Column(name = "acronym")
    private String acronym;

    @Column(name = "start_year")
    private Year startYear;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "certification_level_id")
    private CertificationLevel level;

    public Certification() {
	// Required no-arg constructor
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getAcronym() {
	return acronym;
    }

    public void setAcronym(String acronym) {
	this.acronym = acronym;
    }

    public Year getStartYear() {
	return startYear;
    }

    public void setStartYear(Year year) {
	startYear = year;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public CertificationLevel getLevel() {
	return level;
    }

    public void setLevel(CertificationLevel level) {
	this.level = level;
    }

    /**
     * Indicates whether or not this {@code certification} is equal to the given
     * other object.
     * <p>
     * This implementation fulfils the general contract of
     * {@link Object#equals(Object)} comparing the "natural id" of a
     * {@code Certification} which is its {@code code} ignoring case.
     *
     * @param obj an object to compare to
     * @return {@code true} is this {@code certification} is equal to
     *         {@code obj}; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	return obj instanceof Certification other
		&& code.equalsIgnoreCase(other.code);
    }

    /**
     * Returns a hash code value for this {@code certification}.
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
		"{id=%s, code=%s, name=%s, acronym=%s, "
			+ "startYear=%s, description=%s, level=%s}",
		getId(), code, name, acronym, startYear, description, level);
    }

}
