/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.controller;

import co.com.ppit2.controller.exceptions.NonexistentEntityException;
import co.com.ppit2.controller.exceptions.PreexistingEntityException;
import co.com.ppit2.model.Convenios;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author jaro
 */
public class ConveniosJpaController implements Serializable {

    public ConveniosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Convenios convenios) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(convenios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConvenios(convenios.getId()) != null) {
                throw new PreexistingEntityException("Convenios " + convenios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Convenios convenios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            convenios = em.merge(convenios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = convenios.getId();
                if (findConvenios(id) == null) {
                    throw new NonexistentEntityException("The convenios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Convenios convenios;
            try {
                convenios = em.getReference(Convenios.class, id);
                convenios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The convenios with id " + id + " no longer exists.", enfe);
            }
            em.remove(convenios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Convenios> findConveniosEntities() {
        return findConveniosEntities(true, -1, -1);
    }

    public List<Convenios> findConveniosEntities(int maxResults, int firstResult) {
        return findConveniosEntities(false, maxResults, firstResult);
    }

    private List<Convenios> findConveniosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Convenios.class));
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

    public Convenios findConvenios(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Convenios.class, id);
        } finally {
            em.close();
        }
    }

    public int getConveniosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Convenios> rt = cq.from(Convenios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Convenios> getTodosLosConvenios() throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query query = em.createQuery("SELECT c FROM Convenios c");
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
}
