/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.controller;

import co.com.ppit2.controller.exceptions.IllegalOrphanException;
import co.com.ppit2.controller.exceptions.NonexistentEntityException;
import co.com.ppit2.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.com.ppit2.model.Historialtransportes;
import java.util.ArrayList;
import java.util.Collection;
import co.com.ppit2.model.Historialpagos;
import co.com.ppit2.model.Historialhoteles;
import co.com.ppit2.model.Personas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jaro
 */
public class PersonasJpaController implements Serializable {

    public PersonasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Personas personas) throws PreexistingEntityException, Exception {
        if (personas.getHistorialtransportesCollection() == null) {
            personas.setHistorialtransportesCollection(new ArrayList<Historialtransportes>());
        }
        if (personas.getHistorialpagosCollection() == null) {
            personas.setHistorialpagosCollection(new ArrayList<Historialpagos>());
        }
        if (personas.getHistorialhotelesCollection() == null) {
            personas.setHistorialhotelesCollection(new ArrayList<Historialhoteles>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Historialtransportes> attachedHistorialtransportesCollection = new ArrayList<Historialtransportes>();
            for (Historialtransportes historialtransportesCollectionHistorialtransportesToAttach : personas.getHistorialtransportesCollection()) {
                historialtransportesCollectionHistorialtransportesToAttach = em.getReference(historialtransportesCollectionHistorialtransportesToAttach.getClass(), historialtransportesCollectionHistorialtransportesToAttach.getCodigo());
                attachedHistorialtransportesCollection.add(historialtransportesCollectionHistorialtransportesToAttach);
            }
            personas.setHistorialtransportesCollection(attachedHistorialtransportesCollection);
            Collection<Historialpagos> attachedHistorialpagosCollection = new ArrayList<Historialpagos>();
            for (Historialpagos historialpagosCollectionHistorialpagosToAttach : personas.getHistorialpagosCollection()) {
                historialpagosCollectionHistorialpagosToAttach = em.getReference(historialpagosCollectionHistorialpagosToAttach.getClass(), historialpagosCollectionHistorialpagosToAttach.getCodigo());
                attachedHistorialpagosCollection.add(historialpagosCollectionHistorialpagosToAttach);
            }
            personas.setHistorialpagosCollection(attachedHistorialpagosCollection);
            Collection<Historialhoteles> attachedHistorialhotelesCollection = new ArrayList<Historialhoteles>();
            for (Historialhoteles historialhotelesCollectionHistorialhotelesToAttach : personas.getHistorialhotelesCollection()) {
                historialhotelesCollectionHistorialhotelesToAttach = em.getReference(historialhotelesCollectionHistorialhotelesToAttach.getClass(), historialhotelesCollectionHistorialhotelesToAttach.getCodigo());
                attachedHistorialhotelesCollection.add(historialhotelesCollectionHistorialhotelesToAttach);
            }
            personas.setHistorialhotelesCollection(attachedHistorialhotelesCollection);
            em.persist(personas);
            for (Historialtransportes historialtransportesCollectionHistorialtransportes : personas.getHistorialtransportesCollection()) {
                Personas oldHistoryTranspOfHistorialtransportesCollectionHistorialtransportes = historialtransportesCollectionHistorialtransportes.getHistoryTransp();
                historialtransportesCollectionHistorialtransportes.setHistoryTransp(personas);
                historialtransportesCollectionHistorialtransportes = em.merge(historialtransportesCollectionHistorialtransportes);
                if (oldHistoryTranspOfHistorialtransportesCollectionHistorialtransportes != null) {
                    oldHistoryTranspOfHistorialtransportesCollectionHistorialtransportes.getHistorialtransportesCollection().remove(historialtransportesCollectionHistorialtransportes);
                    oldHistoryTranspOfHistorialtransportesCollectionHistorialtransportes = em.merge(oldHistoryTranspOfHistorialtransportesCollectionHistorialtransportes);
                }
            }
            for (Historialpagos historialpagosCollectionHistorialpagos : personas.getHistorialpagosCollection()) {
                Personas oldHistoryPagoOfHistorialpagosCollectionHistorialpagos = historialpagosCollectionHistorialpagos.getHistoryPago();
                historialpagosCollectionHistorialpagos.setHistoryPago(personas);
                historialpagosCollectionHistorialpagos = em.merge(historialpagosCollectionHistorialpagos);
                if (oldHistoryPagoOfHistorialpagosCollectionHistorialpagos != null) {
                    oldHistoryPagoOfHistorialpagosCollectionHistorialpagos.getHistorialpagosCollection().remove(historialpagosCollectionHistorialpagos);
                    oldHistoryPagoOfHistorialpagosCollectionHistorialpagos = em.merge(oldHistoryPagoOfHistorialpagosCollectionHistorialpagos);
                }
            }
            for (Historialhoteles historialhotelesCollectionHistorialhoteles : personas.getHistorialhotelesCollection()) {
                Personas oldHistoryHotelOfHistorialhotelesCollectionHistorialhoteles = historialhotelesCollectionHistorialhoteles.getHistoryHotel();
                historialhotelesCollectionHistorialhoteles.setHistoryHotel(personas);
                historialhotelesCollectionHistorialhoteles = em.merge(historialhotelesCollectionHistorialhoteles);
                if (oldHistoryHotelOfHistorialhotelesCollectionHistorialhoteles != null) {
                    oldHistoryHotelOfHistorialhotelesCollectionHistorialhoteles.getHistorialhotelesCollection().remove(historialhotelesCollectionHistorialhoteles);
                    oldHistoryHotelOfHistorialhotelesCollectionHistorialhoteles = em.merge(oldHistoryHotelOfHistorialhotelesCollectionHistorialhoteles);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersonas(personas.getIdentificacion()) != null) {
                throw new PreexistingEntityException("Personas " + personas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Personas personas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas persistentPersonas = em.find(Personas.class, personas.getIdentificacion());
            Collection<Historialtransportes> historialtransportesCollectionOld = persistentPersonas.getHistorialtransportesCollection();
            Collection<Historialtransportes> historialtransportesCollectionNew = personas.getHistorialtransportesCollection();
            Collection<Historialpagos> historialpagosCollectionOld = persistentPersonas.getHistorialpagosCollection();
            Collection<Historialpagos> historialpagosCollectionNew = personas.getHistorialpagosCollection();
            Collection<Historialhoteles> historialhotelesCollectionOld = persistentPersonas.getHistorialhotelesCollection();
            Collection<Historialhoteles> historialhotelesCollectionNew = personas.getHistorialhotelesCollection();
            List<String> illegalOrphanMessages = null;
            for (Historialtransportes historialtransportesCollectionOldHistorialtransportes : historialtransportesCollectionOld) {
                if (!historialtransportesCollectionNew.contains(historialtransportesCollectionOldHistorialtransportes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialtransportes " + historialtransportesCollectionOldHistorialtransportes + " since its historyTransp field is not nullable.");
                }
            }
            for (Historialpagos historialpagosCollectionOldHistorialpagos : historialpagosCollectionOld) {
                if (!historialpagosCollectionNew.contains(historialpagosCollectionOldHistorialpagos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialpagos " + historialpagosCollectionOldHistorialpagos + " since its historyPago field is not nullable.");
                }
            }
            for (Historialhoteles historialhotelesCollectionOldHistorialhoteles : historialhotelesCollectionOld) {
                if (!historialhotelesCollectionNew.contains(historialhotelesCollectionOldHistorialhoteles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialhoteles " + historialhotelesCollectionOldHistorialhoteles + " since its historyHotel field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Historialtransportes> attachedHistorialtransportesCollectionNew = new ArrayList<Historialtransportes>();
            for (Historialtransportes historialtransportesCollectionNewHistorialtransportesToAttach : historialtransportesCollectionNew) {
                historialtransportesCollectionNewHistorialtransportesToAttach = em.getReference(historialtransportesCollectionNewHistorialtransportesToAttach.getClass(), historialtransportesCollectionNewHistorialtransportesToAttach.getCodigo());
                attachedHistorialtransportesCollectionNew.add(historialtransportesCollectionNewHistorialtransportesToAttach);
            }
            historialtransportesCollectionNew = attachedHistorialtransportesCollectionNew;
            personas.setHistorialtransportesCollection(historialtransportesCollectionNew);
            Collection<Historialpagos> attachedHistorialpagosCollectionNew = new ArrayList<Historialpagos>();
            for (Historialpagos historialpagosCollectionNewHistorialpagosToAttach : historialpagosCollectionNew) {
                historialpagosCollectionNewHistorialpagosToAttach = em.getReference(historialpagosCollectionNewHistorialpagosToAttach.getClass(), historialpagosCollectionNewHistorialpagosToAttach.getCodigo());
                attachedHistorialpagosCollectionNew.add(historialpagosCollectionNewHistorialpagosToAttach);
            }
            historialpagosCollectionNew = attachedHistorialpagosCollectionNew;
            personas.setHistorialpagosCollection(historialpagosCollectionNew);
            Collection<Historialhoteles> attachedHistorialhotelesCollectionNew = new ArrayList<Historialhoteles>();
            for (Historialhoteles historialhotelesCollectionNewHistorialhotelesToAttach : historialhotelesCollectionNew) {
                historialhotelesCollectionNewHistorialhotelesToAttach = em.getReference(historialhotelesCollectionNewHistorialhotelesToAttach.getClass(), historialhotelesCollectionNewHistorialhotelesToAttach.getCodigo());
                attachedHistorialhotelesCollectionNew.add(historialhotelesCollectionNewHistorialhotelesToAttach);
            }
            historialhotelesCollectionNew = attachedHistorialhotelesCollectionNew;
            personas.setHistorialhotelesCollection(historialhotelesCollectionNew);
            personas = em.merge(personas);
            for (Historialtransportes historialtransportesCollectionNewHistorialtransportes : historialtransportesCollectionNew) {
                if (!historialtransportesCollectionOld.contains(historialtransportesCollectionNewHistorialtransportes)) {
                    Personas oldHistoryTranspOfHistorialtransportesCollectionNewHistorialtransportes = historialtransportesCollectionNewHistorialtransportes.getHistoryTransp();
                    historialtransportesCollectionNewHistorialtransportes.setHistoryTransp(personas);
                    historialtransportesCollectionNewHistorialtransportes = em.merge(historialtransportesCollectionNewHistorialtransportes);
                    if (oldHistoryTranspOfHistorialtransportesCollectionNewHistorialtransportes != null && !oldHistoryTranspOfHistorialtransportesCollectionNewHistorialtransportes.equals(personas)) {
                        oldHistoryTranspOfHistorialtransportesCollectionNewHistorialtransportes.getHistorialtransportesCollection().remove(historialtransportesCollectionNewHistorialtransportes);
                        oldHistoryTranspOfHistorialtransportesCollectionNewHistorialtransportes = em.merge(oldHistoryTranspOfHistorialtransportesCollectionNewHistorialtransportes);
                    }
                }
            }
            for (Historialpagos historialpagosCollectionNewHistorialpagos : historialpagosCollectionNew) {
                if (!historialpagosCollectionOld.contains(historialpagosCollectionNewHistorialpagos)) {
                    Personas oldHistoryPagoOfHistorialpagosCollectionNewHistorialpagos = historialpagosCollectionNewHistorialpagos.getHistoryPago();
                    historialpagosCollectionNewHistorialpagos.setHistoryPago(personas);
                    historialpagosCollectionNewHistorialpagos = em.merge(historialpagosCollectionNewHistorialpagos);
                    if (oldHistoryPagoOfHistorialpagosCollectionNewHistorialpagos != null && !oldHistoryPagoOfHistorialpagosCollectionNewHistorialpagos.equals(personas)) {
                        oldHistoryPagoOfHistorialpagosCollectionNewHistorialpagos.getHistorialpagosCollection().remove(historialpagosCollectionNewHistorialpagos);
                        oldHistoryPagoOfHistorialpagosCollectionNewHistorialpagos = em.merge(oldHistoryPagoOfHistorialpagosCollectionNewHistorialpagos);
                    }
                }
            }
            for (Historialhoteles historialhotelesCollectionNewHistorialhoteles : historialhotelesCollectionNew) {
                if (!historialhotelesCollectionOld.contains(historialhotelesCollectionNewHistorialhoteles)) {
                    Personas oldHistoryHotelOfHistorialhotelesCollectionNewHistorialhoteles = historialhotelesCollectionNewHistorialhoteles.getHistoryHotel();
                    historialhotelesCollectionNewHistorialhoteles.setHistoryHotel(personas);
                    historialhotelesCollectionNewHistorialhoteles = em.merge(historialhotelesCollectionNewHistorialhoteles);
                    if (oldHistoryHotelOfHistorialhotelesCollectionNewHistorialhoteles != null && !oldHistoryHotelOfHistorialhotelesCollectionNewHistorialhoteles.equals(personas)) {
                        oldHistoryHotelOfHistorialhotelesCollectionNewHistorialhoteles.getHistorialhotelesCollection().remove(historialhotelesCollectionNewHistorialhoteles);
                        oldHistoryHotelOfHistorialhotelesCollectionNewHistorialhoteles = em.merge(oldHistoryHotelOfHistorialhotelesCollectionNewHistorialhoteles);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = personas.getIdentificacion();
                if (findPersonas(id) == null) {
                    throw new NonexistentEntityException("The personas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas personas;
            try {
                personas = em.getReference(Personas.class, id);
                personas.getIdentificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Historialtransportes> historialtransportesCollectionOrphanCheck = personas.getHistorialtransportesCollection();
            for (Historialtransportes historialtransportesCollectionOrphanCheckHistorialtransportes : historialtransportesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Historialtransportes " + historialtransportesCollectionOrphanCheckHistorialtransportes + " in its historialtransportesCollection field has a non-nullable historyTransp field.");
            }
            Collection<Historialpagos> historialpagosCollectionOrphanCheck = personas.getHistorialpagosCollection();
            for (Historialpagos historialpagosCollectionOrphanCheckHistorialpagos : historialpagosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Historialpagos " + historialpagosCollectionOrphanCheckHistorialpagos + " in its historialpagosCollection field has a non-nullable historyPago field.");
            }
            Collection<Historialhoteles> historialhotelesCollectionOrphanCheck = personas.getHistorialhotelesCollection();
            for (Historialhoteles historialhotelesCollectionOrphanCheckHistorialhoteles : historialhotelesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Personas (" + personas + ") cannot be destroyed since the Historialhoteles " + historialhotelesCollectionOrphanCheckHistorialhoteles + " in its historialhotelesCollection field has a non-nullable historyHotel field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(personas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Personas> findPersonasEntities() {
        return findPersonasEntities(true, -1, -1);
    }

    public List<Personas> findPersonasEntities(int maxResults, int firstResult) {
        return findPersonasEntities(false, maxResults, firstResult);
    }

    private List<Personas> findPersonasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Personas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Personas findPersonas(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Personas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Personas> rt = cq.from(Personas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Personas> getPrimerasCincoPersonas() throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query query = em.createQuery("select p from Personas p");
            query.setMaxResults(5);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Personas> getTodasLasPersonas() throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query query = em.createQuery("SELECT p FROM Personas p");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Personas login(Personas persona) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query query = em.createQuery("SELECT p FROM Personas p WHERE p.identificacion = ?1 AND P.password = ?2", Personas.class);
            query.setParameter(1, persona.getIdentificacion().trim());
            query.setParameter(2, persona.getPassword().trim());
            try {
                return (Personas) query.getSingleResult();
            } catch (Exception e) {
                return null;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
