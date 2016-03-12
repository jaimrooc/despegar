/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.despegarejbimplementation;

import co.com.ppit2.controller.exceptions.PreexistingEntityException;
import co.com.ppit2.model.Personas;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jaro
 */
@Remote
public interface PersonasSessionBeanRemote {

    public List<Personas> getTodasLasPersonas() throws PreexistingEntityException, Exception;
    
    public Personas getPersonaLogin(Personas personas) throws PreexistingEntityException, Exception;
    
}
