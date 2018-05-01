/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unity.domgo.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "instanciaEntidade")
public class InstaciaEntidade implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Entidade entidade;
    @ElementCollection
    @CollectionTable
    @OneToMany
    private List<InstaciaAtributo> instaciaAtributo;

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    public List<InstaciaAtributo> getInstaciaAtributo() {
        return instaciaAtributo;
    }

    public void setInstaciaAtributo(List<InstaciaAtributo> instaciaAtributo) {
        this.instaciaAtributo = instaciaAtributo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.entidade);
        hash = 89 * hash + Objects.hashCode(this.instaciaAtributo);
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
        final InstaciaEntidade other = (InstaciaEntidade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.entidade, other.entidade)) {
            return false;
        }
        if (!Objects.equals(this.instaciaAtributo, other.instaciaAtributo)) {
            return false;
        }
        return true;
    }

}
