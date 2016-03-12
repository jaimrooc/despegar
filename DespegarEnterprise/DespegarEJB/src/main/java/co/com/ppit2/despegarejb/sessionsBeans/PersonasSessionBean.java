/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.despegarejb.sessionsBeans;

import co.com.ppit2.controller.PersonasJpaController;
import co.com.ppit2.controller.exceptions.PreexistingEntityException;
import co.com.ppit2.despegarejbimplementation.PersonasSessionBeanRemote;
import co.com.ppit2.model.Personas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Persistence;

/**
 *
 * @author root
 */
@Stateless(name = "jdbc/despegarDS")
public class PersonasSessionBean implements PersonasSessionBeanRemote {
    
    private static final String ENTITY_MANAGER_FACTORY = "despegarEjemplo";

    @Override
    public List<Personas> getTodasLasPersonas() throws PreexistingEntityException, Exception {
        PersonasJpaController personasJpaController = new PersonasJpaController(Persistence.createEntityManagerFactory(ENTITY_MANAGER_FACTORY));
        return personasJpaController.getTodasLasPersonas();
    }

    @Override
    public Personas getPersonaLogin(Personas personas) throws PreexistingEntityException, Exception {
        PersonasJpaController personasJpaController = new PersonasJpaController(Persistence.createEntityManagerFactory(ENTITY_MANAGER_FACTORY));
        return personasJpaController.login(personas);
    }

    
}
