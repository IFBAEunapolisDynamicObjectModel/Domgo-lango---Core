/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unity.domgo.app;

import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class GeraTabelas {

    public static void main(String[] args) {
        Persistence.createEntityManagerFactory("com.unity.domgocore");
    }
}
