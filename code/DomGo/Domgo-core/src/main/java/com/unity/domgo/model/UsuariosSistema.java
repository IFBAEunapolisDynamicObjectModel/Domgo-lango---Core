/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unity.domgo.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author user
 */
@Entity
public class UsuariosSistema extends User implements Serializable {

    @Enumerated(EnumType.STRING)
    private NivelDeAcesso nivelDeAcesso;
}
