package tech.readresolve.skilltree.entities;

import java.time.Year;
import java.util.Objects;

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
    @JoinColumn(name = "certification_level_id", updatable = false)
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

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	return obj instanceof Certification other
		&& Objects.equals(code, other.code);
    }

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
