package tech.readresolve.skilltree.entities;

import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_trainings")
public class Training extends BaseEntity {

    @Column(name = "training_name")
    @NaturalId
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "certification_id")
    private Certification certification;

    @Column(name = "description")
    private String description;

    public Training() {
	// Required no-arg constructor
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public LocalDate getStartDate() {
	return startDate;
    }

    public void setStartDate(LocalDate date) {
	startDate = date;
    }

    public LocalDate getEndDate() {
	return endDate;
    }

    public void setEndDate(LocalDate date) {
	endDate = date;
    }

    public Certification getCertification() {
	return certification;
    }

    public void setCertification(Certification certification) {
	this.certification = certification;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Indicates whether or not this {@code training} is equal to the given
     * other object.
     * <p>
     * This implementation fulfils the general contract of
     * {@link Object#equals(Object)} comparing the "natural id" of a
     * {@code Training} which is its {@code name} ignoring case.
     *
     * @param obj an object to compare to
     * @return {@code true} is this {@code training} is equal to {@code obj};
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	return obj instanceof Training other
		&& name.equalsIgnoreCase(other.name);
    }

    /**
     * Returns a hash code value for this {@code training}.
     * <p>
     * This implementation fulfils the general contract of
     * {@link Object#hashCode()} being consistent with {@link #equals(Object)}.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
	return Objects.hash(name);
    }

    @Override
    public String toString() {
	return String.format(
		"{id=%s, name=%s, startDate=%s, endDate=%s, "
			+ "description=%s, certification=[LAZY_LOADED]}",
		getId(), name, startDate, endDate, description);
    }

}
