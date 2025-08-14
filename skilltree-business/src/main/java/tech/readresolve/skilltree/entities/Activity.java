package tech.readresolve.skilltree.entities;

import java.util.Objects;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_activities")
public class Activity extends BaseEntity {

    @Column(name = "code")
    @NaturalId
    private String code;

    @Column(name = "activity_name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "certification_id")
    private Certification certification;

    public Activity() {
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

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Certification getCertification() {
	return certification;
    }

    public void setCertification(Certification certification) {
	this.certification = certification;
    }

    /**
     * Indicates whether or not this {@code activity} is equal to the given
     * other object.
     * <p>
     * This implementation fulfils the general contract of
     * {@link Object#equals(Object)} comparing the "natural id" of an
     * {@code Activity} which is its {@code code} ignoring case.
     *
     * @param obj an object to compare to
     * @return {@code true} is this {@code activity} is equal to {@code obj};
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	return obj instanceof Activity other
		&& code.equalsIgnoreCase(other.code);
    }

    /**
     * Returns a hash code value for this {@code activity}.
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
		"{id=%s, code=%s, name=%s, description=%s, "
			+ "certification=[LAZY_LOADED]}",
		getId(), code, name, description);
    }

}
