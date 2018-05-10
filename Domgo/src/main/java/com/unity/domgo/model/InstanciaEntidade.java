package com.unity.domgo.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.ManyToOne;

@Entity
public class InstanciaEntidade implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@ManyToOne
	private Entidade entidades;

	@ManyToOne
	private InstanciaAtributo instanciaAtributo;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof InstanciaEntidade)) {
			return false;
		}
		InstanciaEntidade other = (InstanciaEntidade) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public Entidade getEntidades() {
		return this.entidades;
	}

	public void setEntidades(final Entidade entidades) {
		this.entidades = entidades;
	}

	public InstanciaAtributo getInstanciaAtributo() {
		return this.instanciaAtributo;
	}

	public void setInstanciaAtributo(final InstanciaAtributo instanciaAtributo) {
		this.instanciaAtributo = instanciaAtributo;
	}

}
