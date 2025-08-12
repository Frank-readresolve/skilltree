package tech.readresolve.skilltree.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "t_certification_levels")
// Hibernate caching:
@Immutable // Required for read-only strategy
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class CertificationLevel extends BaseEntity {

	@Column(name = "code", insertable = false, updatable = false)
	private String code;

	@Column(name = "french_level", insertable = false, updatable = false)
	private String frenchLevel;

	@Column(name = "european_level", insertable = false, updatable = false)
	private String europeanLevel;

	@Column(name = "equivalence", insertable = false, updatable = false)
	private String equivalence;

	public CertificationLevel() {
		// Required no-arg constructor
	}

	public String getCode() {
		return code;
	}

	@SuppressWarnings("unused")
	private void setCode(String code) {
		// Not insertable/updatable
		this.code = code;
	}

	public String getFrenchLevel() {
		return frenchLevel;
	}

	@SuppressWarnings("unused")
	private void setFrenchLevel(String level) {
		// Not insertable/updatable
		frenchLevel = level;
	}

	public String getEuropeanLevel() {
		return europeanLevel;
	}

	@SuppressWarnings("unused")
	private void setEuropeanLevel(String level) {
		// Not insertable/updatable
		europeanLevel = level;
	}

	public String getEquivalence() {
		return equivalence;
	}

	@SuppressWarnings("unused")
	private void setEquivalence(String equivalence) {
		// Not insertable/updatable
		this.equivalence = equivalence;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		return obj instanceof CertificationLevel other
				&& Objects.equals(code, other.code);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public String toString() {
		return String.format(
				"{code=%s, frenchLevel=%s, europeanLevel=%s, equivalence=%s}",
				code, frenchLevel, europeanLevel, equivalence);
	}

}
