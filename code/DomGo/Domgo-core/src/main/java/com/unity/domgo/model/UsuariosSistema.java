/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unity.domgo.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author user
 */
@Entity
public class UsuariosSistema implements Serializable {

    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private NivelDeAcesso nivelDeAcesso;
    @OneToMany(mappedBy = "usuariosSistema")
    private List<Atributo> atributos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NivelDeAcesso getNivelDeAcesso() {
        return nivelDeAcesso;
    }

    public void setNivelDeAcesso(NivelDeAcesso nivelDeAcesso) {
        this.nivelDeAcesso = nivelDeAcesso;
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.nivelDeAcesso);
        hash = 47 * hash + Objects.hashCode(this.atributos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuariosSistema other = (UsuariosSistema) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
