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
@Table(name = "instanciasistema")
public class InstanciaSistema implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Sistema sistema;
    @ElementCollection
    @CollectionTable
    @OneToMany
    private List<InstaciaEntidade> instaciaEntidades;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public List<InstaciaEntidade> getInstaciaEntidades() {
        return instaciaEntidades;
    }

    public void setInstaciaEntidades(List<InstaciaEntidade> instaciaEntidades) {
        this.instaciaEntidades = instaciaEntidades;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.sistema);
        hash = 83 * hash + Objects.hashCode(this.instaciaEntidades);
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
        final InstanciaSistema other = (InstanciaSistema) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.sistema, other.sistema)) {
            return false;
        }
        if (!Objects.equals(this.instaciaEntidades, other.instaciaEntidades)) {
            return false;
        }
        return true;
    }

}
