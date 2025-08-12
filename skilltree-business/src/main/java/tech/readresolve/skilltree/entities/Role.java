package tech.readresolve.skilltree.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "t_roles")
// Hibernate caching:
@Immutable // Required for read-only strategy
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Role extends BaseEntity {

	@Column(name = "code", insertable = false, updatable = false)
	private String code;

	@Column(name = "role_name", insertable = false, updatable = false)
	private String name;

	public Role() {
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

	public String getName() {
		return name;
	}

	@SuppressWarnings("unused")
	private void setName(String name) {
		// Not insertable/updatable
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		return obj instanceof Role other && Objects.equals(code, other.code);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public String toString() {
		return String.format("{id=%s, code=%s, name=%s}", getId(), code, name);
	}

}
