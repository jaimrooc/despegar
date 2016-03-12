/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.controller;

import co.com.ppit2.controller.exceptions.IllegalOrphanException;
import co.com.ppit2.controller.exceptions.NonexistentEntityException;
import co.com.ppit2.controller.exceptions.PreexistingEntityException;
import co.com.ppit2.model.Datostranporteaereo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.com.ppit2.model.Historialtransportes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jaro
 */
public class DatostranporteaereoJpaController implements Serializable {

    public DatostranporteaereoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datostranporteaereo datostranporteaereo) throws PreexistingEntityException, Exception {
        if (datostranporteaereo.getHistorialtransportesCollection() == null) {
            datostranporteaereo.setHistorialtransportesCollection(new ArrayList<Historialtransportes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Historialtransportes> attachedHistorialtransportesCollection = new ArrayList<Historialtransportes>();
            for (Historialtransportes historialtransportesCollectionHistorialtransportesToAttach : datostranporteaereo.getHistorialtransportesCollection()) {
                historialtransportesCollectionHistorialtransportesToAttach = em.getReference(historialtransportesCollectionHistorialtransportesToAttach.getClass(), historialtransportesCollectionHistorialtransportesToAttach.getCodigo());
                attachedHistorialtransportesCollection.add(historialtransportesCollectionHistorialtransportesToAttach);
            }
            datostranporteaereo.setHistorialtransportesCollection(attachedHistorialtransportesCollection);
            em.persist(datostranporteaereo);
            for (Historialtransportes historialtransportesCollectionHistorialtransportes : datostranporteaereo.getHistorialtransportesCollection()) {
                Datostranporteaereo oldDatosTranAeroOfHistorialtransportesCollectionHistorialtransportes = historialtransportesCollectionHistorialtransportes.getDatosTranAero();
                historialtransportesCollectionHistorialtransportes.setDatosTranAero(datostranporteaereo);
                historialtransportesCollectionHistorialtransportes = em.merge(historialtransportesCollectionHistorialtransportes);
                if (oldDatosTranAeroOfHistorialtransportesCollectionHistorialtransportes != null) {
                    oldDatosTranAeroOfHistorialtransportesCollectionHistorialtransportes.getHistorialtransportesCollection().remove(historialtransportesCollectionHistorialtransportes);
                    oldDatosTranAeroOfHistorialtransportesCollectionHistorialtransportes = em.merge(oldDatosTranAeroOfHistorialtransportesCollectionHistorialtransportes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatostranporteaereo(datostranporteaereo.getCodigo()) != null) {
                throw new PreexistingEntityException("Datostranporteaereo " + datostranporteaereo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datostranporteaereo datostranporteaereo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Datostranporteaereo persistentDatostranporteaereo = em.find(Datostranporteaereo.class, datostranporteaereo.getCodigo());
            Collection<Historialtransportes> historialtransportesCollectionOld = persistentDatostranporteaereo.getHistorialtransportesCollection();
            Collection<Historialtransportes> historialtransportesCollectionNew = datostranporteaereo.getHistorialtransportesCollection();
            List<String> illegalOrphanMessages = null;
            for (Historialtransportes historialtransportesCollectionOldHistorialtransportes : historialtransportesCollectionOld) {
                if (!historialtransportesCollectionNew.contains(historialtransportesCollectionOldHistorialtransportes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialtransportes " + historialtransportesCollectionOldHistorialtransportes + " since its datosTranAero field is not nullable.");
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
            datostranporteaereo.setHistorialtransportesCollection(historialtransportesCollectionNew);
            datostranporteaereo = em.merge(datostranporteaereo);
            for (Historialtransportes historialtransportesCollectionNewHistorialtransportes : historialtransportesCollectionNew) {
                if (!historialtransportesCollectionOld.contains(historialtransportesCollectionNewHistorialtransportes)) {
                    Datostranporteaereo oldDatosTranAeroOfHistorialtransportesCollectionNewHistorialtransportes = historialtransportesCollectionNewHistorialtransportes.getDatosTranAero();
                    historialtransportesCollectionNewHistorialtransportes.setDatosTranAero(datostranporteaereo);
                    historialtransportesCollectionNewHistorialtransportes = em.merge(historialtransportesCollectionNewHistorialtransportes);
                    if (oldDatosTranAeroOfHistorialtransportesCollectionNewHistorialtransportes != null && !oldDatosTranAeroOfHistorialtransportesCollectionNewHistorialtransportes.equals(datostranporteaereo)) {
                        oldDatosTranAeroOfHistorialtransportesCollectionNewHistorialtransportes.getHistorialtransportesCollection().remove(historialtransportesCollectionNewHistorialtransportes);
                        oldDatosTranAeroOfHistorialtransportesCollectionNewHistorialtransportes = em.merge(oldDatosTranAeroOfHistorialtransportesCollectionNewHistorialtransportes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = datostranporteaereo.getCodigo();
                if (findDatostranporteaereo(id) == null) {
                    throw new NonexistentEntityException("The datostranporteaereo with id " + id + " no longer exists.");
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
            Datostranporteaereo datostranporteaereo;
            try {
                datostranporteaereo = em.getReference(Datostranporteaereo.class, id);
                datostranporteaereo.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datostranporteaereo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Historialtransportes> historialtransportesCollectionOrphanCheck = datostranporteaereo.getHistorialtransportesCollection();
            for (Historialtransportes historialtransportesCollectionOrphanCheckHistorialtransportes : historialtransportesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Datostranporteaereo (" + datostranporteaereo + ") cannot be destroyed since the Historialtransportes " + historialtransportesCollectionOrphanCheckHistorialtransportes + " in its historialtransportesCollection field has a non-nullable datosTranAero field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(datostranporteaereo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datostranporteaereo> findDatostranporteaereoEntities() {
        return findDatostranporteaereoEntities(true, -1, -1);
    }

    public List<Datostranporteaereo> findDatostranporteaereoEntities(int maxResults, int firstResult) {
        return findDatostranporteaereoEntities(false, maxResults, firstResult);
    }

    private List<Datostranporteaereo> findDatostranporteaereoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Datostranporteaereo.class));
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

    public Datostranporteaereo findDatostranporteaereo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datostranporteaereo.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatostranporteaereoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Datostranporteaereo> rt = cq.from(Datostranporteaereo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
