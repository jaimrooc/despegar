/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.controller;

import co.com.ppit2.controller.exceptions.NonexistentEntityException;
import co.com.ppit2.controller.exceptions.PreexistingEntityException;
import co.com.ppit2.model.Historialpagos;
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
public class HistorialpagosJpaController implements Serializable {

    public HistorialpagosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historialpagos historialpagos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas historyPago = historialpagos.getHistoryPago();
            if (historyPago != null) {
                historyPago = em.getReference(historyPago.getClass(), historyPago.getIdentificacion());
                historialpagos.setHistoryPago(historyPago);
            }
            em.persist(historialpagos);
            if (historyPago != null) {
                historyPago.getHistorialpagosCollection().add(historialpagos);
                historyPago = em.merge(historyPago);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistorialpagos(historialpagos.getCodigo()) != null) {
                throw new PreexistingEntityException("Historialpagos " + historialpagos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historialpagos historialpagos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historialpagos persistentHistorialpagos = em.find(Historialpagos.class, historialpagos.getCodigo());
            Personas historyPagoOld = persistentHistorialpagos.getHistoryPago();
            Personas historyPagoNew = historialpagos.getHistoryPago();
            if (historyPagoNew != null) {
                historyPagoNew = em.getReference(historyPagoNew.getClass(), historyPagoNew.getIdentificacion());
                historialpagos.setHistoryPago(historyPagoNew);
            }
            historialpagos = em.merge(historialpagos);
            if (historyPagoOld != null && !historyPagoOld.equals(historyPagoNew)) {
                historyPagoOld.getHistorialpagosCollection().remove(historialpagos);
                historyPagoOld = em.merge(historyPagoOld);
            }
            if (historyPagoNew != null && !historyPagoNew.equals(historyPagoOld)) {
                historyPagoNew.getHistorialpagosCollection().add(historialpagos);
                historyPagoNew = em.merge(historyPagoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = historialpagos.getCodigo();
                if (findHistorialpagos(id) == null) {
                    throw new NonexistentEntityException("The historialpagos with id " + id + " no longer exists.");
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
            Historialpagos historialpagos;
            try {
                historialpagos = em.getReference(Historialpagos.class, id);
                historialpagos.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialpagos with id " + id + " no longer exists.", enfe);
            }
            Personas historyPago = historialpagos.getHistoryPago();
            if (historyPago != null) {
                historyPago.getHistorialpagosCollection().remove(historialpagos);
                historyPago = em.merge(historyPago);
            }
            em.remove(historialpagos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historialpagos> findHistorialpagosEntities() {
        return findHistorialpagosEntities(true, -1, -1);
    }

    public List<Historialpagos> findHistorialpagosEntities(int maxResults, int firstResult) {
        return findHistorialpagosEntities(false, maxResults, firstResult);
    }

    private List<Historialpagos> findHistorialpagosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historialpagos.class));
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

    public Historialpagos findHistorialpagos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historialpagos.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialpagosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historialpagos> rt = cq.from(Historialpagos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
