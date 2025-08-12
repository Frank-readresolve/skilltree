package tech.readresolve.skilltree.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import tech.readresolve.skilltree.misc.ExcludeFromJacocoGeneratedReport;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	BaseEntity() {
		// Required no-arg constructor
	}

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	@ExcludeFromJacocoGeneratedReport
	private void setId(Long id) {
		// Prevents from accidental assignment (set by DB)
		this.id = id;
	}

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract int hashCode();

	@Override
	public abstract String toString();

}
