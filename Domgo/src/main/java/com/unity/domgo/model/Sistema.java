package com.unity.domgo.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.ManyToMany;

@Entity
public class Sistema implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column(nullable = false)
	private String nome;

	@ManyToMany
	private Set<UsuarioSistema> usuarios = new HashSet<UsuarioSistema>();

	@ManyToMany
	private Set<Entidade> entidades = new HashSet<Entidade>();

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
		if (!(obj instanceof Sistema)) {
			return false;
		}
		Sistema other = (Sistema) obj;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<UsuarioSistema> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(final Set<UsuarioSistema> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (nome != null && !nome.trim().isEmpty()) {
			result += "nome: " + nome;
		}
		return result;
	}

	public Set<Entidade> getEntidades() {
		return this.entidades;
	}

	public void setEntidades(final Set<Entidade> entidades) {
		this.entidades = entidades;
	}
}
