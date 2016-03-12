/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.controller;

import co.com.ppit2.controller.exceptions.IllegalOrphanException;
import co.com.ppit2.controller.exceptions.NonexistentEntityException;
import co.com.ppit2.controller.exceptions.PreexistingEntityException;
import co.com.ppit2.model.Datostranporteterrestre;
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
public class DatostranporteterrestreJpaController implements Serializable {

    public DatostranporteterrestreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Datostranporteterrestre datostranporteterrestre) throws PreexistingEntityException, Exception {
        if (datostranporteterrestre.getHistorialtransportesCollection() == null) {
            datostranporteterrestre.setHistorialtransportesCollection(new ArrayList<Historialtransportes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Historialtransportes> attachedHistorialtransportesCollection = new ArrayList<Historialtransportes>();
            for (Historialtransportes historialtransportesCollectionHistorialtransportesToAttach : datostranporteterrestre.getHistorialtransportesCollection()) {
                historialtransportesCollectionHistorialtransportesToAttach = em.getReference(historialtransportesCollectionHistorialtransportesToAttach.getClass(), historialtransportesCollectionHistorialtransportesToAttach.getCodigo());
                attachedHistorialtransportesCollection.add(historialtransportesCollectionHistorialtransportesToAttach);
            }
            datostranporteterrestre.setHistorialtransportesCollection(attachedHistorialtransportesCollection);
            em.persist(datostranporteterrestre);
            for (Historialtransportes historialtransportesCollectionHistorialtransportes : datostranporteterrestre.getHistorialtransportesCollection()) {
                Datostranporteterrestre oldDatosTranTerrOfHistorialtransportesCollectionHistorialtransportes = historialtransportesCollectionHistorialtransportes.getDatosTranTerr();
                historialtransportesCollectionHistorialtransportes.setDatosTranTerr(datostranporteterrestre);
                historialtransportesCollectionHistorialtransportes = em.merge(historialtransportesCollectionHistorialtransportes);
                if (oldDatosTranTerrOfHistorialtransportesCollectionHistorialtransportes != null) {
                    oldDatosTranTerrOfHistorialtransportesCollectionHistorialtransportes.getHistorialtransportesCollection().remove(historialtransportesCollectionHistorialtransportes);
                    oldDatosTranTerrOfHistorialtransportesCollectionHistorialtransportes = em.merge(oldDatosTranTerrOfHistorialtransportesCollectionHistorialtransportes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDatostranporteterrestre(datostranporteterrestre.getCodigo()) != null) {
                throw new PreexistingEntityException("Datostranporteterrestre " + datostranporteterrestre + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Datostranporteterrestre datostranporteterrestre) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Datostranporteterrestre persistentDatostranporteterrestre = em.find(Datostranporteterrestre.class, datostranporteterrestre.getCodigo());
            Collection<Historialtransportes> historialtransportesCollectionOld = persistentDatostranporteterrestre.getHistorialtransportesCollection();
            Collection<Historialtransportes> historialtransportesCollectionNew = datostranporteterrestre.getHistorialtransportesCollection();
            List<String> illegalOrphanMessages = null;
            for (Historialtransportes historialtransportesCollectionOldHistorialtransportes : historialtransportesCollectionOld) {
                if (!historialtransportesCollectionNew.contains(historialtransportesCollectionOldHistorialtransportes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historialtransportes " + historialtransportesCollectionOldHistorialtransportes + " since its datosTranTerr field is not nullable.");
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
            datostranporteterrestre.setHistorialtransportesCollection(historialtransportesCollectionNew);
            datostranporteterrestre = em.merge(datostranporteterrestre);
            for (Historialtransportes historialtransportesCollectionNewHistorialtransportes : historialtransportesCollectionNew) {
                if (!historialtransportesCollectionOld.contains(historialtransportesCollectionNewHistorialtransportes)) {
                    Datostranporteterrestre oldDatosTranTerrOfHistorialtransportesCollectionNewHistorialtransportes = historialtransportesCollectionNewHistorialtransportes.getDatosTranTerr();
                    historialtransportesCollectionNewHistorialtransportes.setDatosTranTerr(datostranporteterrestre);
                    historialtransportesCollectionNewHistorialtransportes = em.merge(historialtransportesCollectionNewHistorialtransportes);
                    if (oldDatosTranTerrOfHistorialtransportesCollectionNewHistorialtransportes != null && !oldDatosTranTerrOfHistorialtransportesCollectionNewHistorialtransportes.equals(datostranporteterrestre)) {
                        oldDatosTranTerrOfHistorialtransportesCollectionNewHistorialtransportes.getHistorialtransportesCollection().remove(historialtransportesCollectionNewHistorialtransportes);
                        oldDatosTranTerrOfHistorialtransportesCollectionNewHistorialtransportes = em.merge(oldDatosTranTerrOfHistorialtransportesCollectionNewHistorialtransportes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = datostranporteterrestre.getCodigo();
                if (findDatostranporteterrestre(id) == null) {
                    throw new NonexistentEntityException("The datostranporteterrestre with id " + id + " no longer exists.");
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
            Datostranporteterrestre datostranporteterrestre;
            try {
                datostranporteterrestre = em.getReference(Datostranporteterrestre.class, id);
                datostranporteterrestre.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datostranporteterrestre with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Historialtransportes> historialtransportesCollectionOrphanCheck = datostranporteterrestre.getHistorialtransportesCollection();
            for (Historialtransportes historialtransportesCollectionOrphanCheckHistorialtransportes : historialtransportesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Datostranporteterrestre (" + datostranporteterrestre + ") cannot be destroyed since the Historialtransportes " + historialtransportesCollectionOrphanCheckHistorialtransportes + " in its historialtransportesCollection field has a non-nullable datosTranTerr field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(datostranporteterrestre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Datostranporteterrestre> findDatostranporteterrestreEntities() {
        return findDatostranporteterrestreEntities(true, -1, -1);
    }

    public List<Datostranporteterrestre> findDatostranporteterrestreEntities(int maxResults, int firstResult) {
        return findDatostranporteterrestreEntities(false, maxResults, firstResult);
    }

    private List<Datostranporteterrestre> findDatostranporteterrestreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Datostranporteterrestre.class));
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

    public Datostranporteterrestre findDatostranporteterrestre(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Datostranporteterrestre.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatostranporteterrestreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Datostranporteterrestre> rt = cq.from(Datostranporteterrestre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
