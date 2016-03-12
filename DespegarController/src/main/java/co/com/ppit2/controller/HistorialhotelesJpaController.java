/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.controller;

import co.com.ppit2.controller.exceptions.NonexistentEntityException;
import co.com.ppit2.controller.exceptions.PreexistingEntityException;
import co.com.ppit2.model.Historialhoteles;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.com.ppit2.model.Personas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jaro
 */
public class HistorialhotelesJpaController implements Serializable {

    public HistorialhotelesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historialhoteles historialhoteles) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas historyHotel = historialhoteles.getHistoryHotel();
            if (historyHotel != null) {
                historyHotel = em.getReference(historyHotel.getClass(), historyHotel.getIdentificacion());
                historialhoteles.setHistoryHotel(historyHotel);
            }
            em.persist(historialhoteles);
            if (historyHotel != null) {
                historyHotel.getHistorialhotelesCollection().add(historialhoteles);
                historyHotel = em.merge(historyHotel);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistorialhoteles(historialhoteles.getCodigo()) != null) {
                throw new PreexistingEntityException("Historialhoteles " + historialhoteles + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historialhoteles historialhoteles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historialhoteles persistentHistorialhoteles = em.find(Historialhoteles.class, historialhoteles.getCodigo());
            Personas historyHotelOld = persistentHistorialhoteles.getHistoryHotel();
            Personas historyHotelNew = historialhoteles.getHistoryHotel();
            if (historyHotelNew != null) {
                historyHotelNew = em.getReference(historyHotelNew.getClass(), historyHotelNew.getIdentificacion());
                historialhoteles.setHistoryHotel(historyHotelNew);
            }
            historialhoteles = em.merge(historialhoteles);
            if (historyHotelOld != null && !historyHotelOld.equals(historyHotelNew)) {
                historyHotelOld.getHistorialhotelesCollection().remove(historialhoteles);
                historyHotelOld = em.merge(historyHotelOld);
            }
            if (historyHotelNew != null && !historyHotelNew.equals(historyHotelOld)) {
                historyHotelNew.getHistorialhotelesCollection().add(historialhoteles);
                historyHotelNew = em.merge(historyHotelNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = historialhoteles.getCodigo();
                if (findHistorialhoteles(id) == null) {
                    throw new NonexistentEntityException("The historialhoteles with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historialhoteles historialhoteles;
            try {
                historialhoteles = em.getReference(Historialhoteles.class, id);
                historialhoteles.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialhoteles with id " + id + " no longer exists.", enfe);
            }
            Personas historyHotel = historialhoteles.getHistoryHotel();
            if (historyHotel != null) {
                historyHotel.getHistorialhotelesCollection().remove(historialhoteles);
                historyHotel = em.merge(historyHotel);
            }
            em.remove(historialhoteles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historialhoteles> findHistorialhotelesEntities() {
        return findHistorialhotelesEntities(true, -1, -1);
    }

    public List<Historialhoteles> findHistorialhotelesEntities(int maxResults, int firstResult) {
        return findHistorialhotelesEntities(false, maxResults, firstResult);
    }

    private List<Historialhoteles> findHistorialhotelesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historialhoteles.class));
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

    public Historialhoteles findHistorialhoteles(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historialhoteles.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialhotelesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historialhoteles> rt = cq.from(Historialhoteles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
