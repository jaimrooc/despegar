/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.test;

import co.com.ppit2.controller.PersonasJpaController;
import co.com.ppit2.model.Personas;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jaro
 */
public class TestClass {
    
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("despegar");
        PersonasJpaController persona = new PersonasJpaController(emf);
        
        for (Personas nodo : persona.getPrimerasCincoPersonas()) {
            System.out.println(nodo.getNombre());
        }
    }
}
