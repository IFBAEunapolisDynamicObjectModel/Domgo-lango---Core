/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unity.domgo.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author user
 */
@Entity
public class Usuario extends User implements Serializable {

    @ElementCollection
    @CollectionTable
    @OneToMany
    private List<Sistema> sistema;

}
