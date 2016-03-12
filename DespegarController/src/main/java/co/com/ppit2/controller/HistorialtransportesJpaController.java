/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.ppit2.controller;

import co.com.ppit2.controller.exceptions.NonexistentEntityException;
import co.com.ppit2.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.com.ppit2.model.Personas;
import co.com.ppit2.model.Datostranporteterrestre;
import co.com.ppit2.model.Datostranporteaereo;
import co.com.ppit2.model.Historialtransportes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jaro
 */
public class HistorialtransportesJpaController implements Serializable {

    public HistorialtransportesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historialtransportes historialtransportes) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Personas historyTransp = historialtransportes.getHistoryTransp();
            if (historyTransp != null) {
                historyTransp = em.getReference(historyTransp.getClass(), historyTransp.getIdentificacion());
                historialtransportes.setHistoryTransp(historyTransp);
            }
            Datostranporteterrestre datosTranTerr = historialtransportes.getDatosTranTerr();
            if (datosTranTerr != null) {
                datosTranTerr = em.getReference(datosTranTerr.getClass(), datosTranTerr.getCodigo());
                historialtransportes.setDatosTranTerr(datosTranTerr);
            }
            Datostranporteaereo datosTranAero = historialtransportes.getDatosTranAero();
            if (datosTranAero != null) {
                datosTranAero = em.getReference(datosTranAero.getClass(), datosTranAero.getCodigo());
                historialtransportes.setDatosTranAero(datosTranAero);
            }
            em.persist(historialtransportes);
            if (historyTransp != null) {
                historyTransp.getHistorialtransportesCollection().add(historialtransportes);
                historyTransp = em.merge(historyTransp);
            }
            if (datosTranTerr != null) {
                datosTranTerr.getHistorialtransportesCollection().add(historialtransportes);
                datosTranTerr = em.merge(datosTranTerr);
            }
            if (datosTranAero != null) {
                datosTranAero.getHistorialtransportesCollection().add(historialtransportes);
                datosTranAero = em.merge(datosTranAero);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistorialtransportes(historialtransportes.getCodigo()) != null) {
                throw new PreexistingEntityException("Historialtransportes " + historialtransportes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historialtransportes historialtransportes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historialtransportes persistentHistorialtransportes = em.find(Historialtransportes.class, historialtransportes.getCodigo());
            Personas historyTranspOld = persistentHistorialtransportes.getHistoryTransp();
            Personas historyTranspNew = historialtransportes.getHistoryTransp();
            Datostranporteterrestre datosTranTerrOld = persistentHistorialtransportes.getDatosTranTerr();
            Datostranporteterrestre datosTranTerrNew = historialtransportes.getDatosTranTerr();
            Datostranporteaereo datosTranAeroOld = persistentHistorialtransportes.getDatosTranAero();
            Datostranporteaereo datosTranAeroNew = historialtransportes.getDatosTranAero();
            if (historyTranspNew != null) {
                historyTranspNew = em.getReference(historyTranspNew.getClass(), historyTranspNew.getIdentificacion());
                historialtransportes.setHistoryTransp(historyTranspNew);
            }
            if (datosTranTerrNew != null) {
                datosTranTerrNew = em.getReference(datosTranTerrNew.getClass(), datosTranTerrNew.getCodigo());
                historialtransportes.setDatosTranTerr(datosTranTerrNew);
            }
            if (datosTranAeroNew != null) {
                datosTranAeroNew = em.getReference(datosTranAeroNew.getClass(), datosTranAeroNew.getCodigo());
                historialtransportes.setDatosTranAero(datosTranAeroNew);
            }
            historialtransportes = em.merge(historialtransportes);
            if (historyTranspOld != null && !historyTranspOld.equals(historyTranspNew)) {
                historyTranspOld.getHistorialtransportesCollection().remove(historialtransportes);
                historyTranspOld = em.merge(historyTranspOld);
            }
            if (historyTranspNew != null && !historyTranspNew.equals(historyTranspOld)) {
                historyTranspNew.getHistorialtransportesCollection().add(historialtransportes);
                historyTranspNew = em.merge(historyTranspNew);
            }
            if (datosTranTerrOld != null && !datosTranTerrOld.equals(datosTranTerrNew)) {
                datosTranTerrOld.getHistorialtransportesCollection().remove(historialtransportes);
                datosTranTerrOld = em.merge(datosTranTerrOld);
            }
            if (datosTranTerrNew != null && !datosTranTerrNew.equals(datosTranTerrOld)) {
                datosTranTerrNew.getHistorialtransportesCollection().add(historialtransportes);
                datosTranTerrNew = em.merge(datosTranTerrNew);
            }
            if (datosTranAeroOld != null && !datosTranAeroOld.equals(datosTranAeroNew)) {
                datosTranAeroOld.getHistorialtransportesCollection().remove(historialtransportes);
                datosTranAeroOld = em.merge(datosTranAeroOld);
            }
            if (datosTranAeroNew != null && !datosTranAeroNew.equals(datosTranAeroOld)) {
                datosTranAeroNew.getHistorialtransportesCollection().add(historialtransportes);
                datosTranAeroNew = em.merge(datosTranAeroNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = historialtransportes.getCodigo();
                if (findHistorialtransportes(id) == null) {
                    throw new NonexistentEntityException("The historialtransportes with id " + id + " no longer exists.");
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
            Historialtransportes historialtransportes;
            try {
                historialtransportes = em.getReference(Historialtransportes.class, id);
                historialtransportes.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialtransportes with id " + id + " no longer exists.", enfe);
            }
            Personas historyTransp = historialtransportes.getHistoryTransp();
            if (historyTransp != null) {
                historyTransp.getHistorialtransportesCollection().remove(historialtransportes);
                historyTransp = em.merge(historyTransp);
            }
            Datostranporteterrestre datosTranTerr = historialtransportes.getDatosTranTerr();
            if (datosTranTerr != null) {
                datosTranTerr.getHistorialtransportesCollection().remove(historialtransportes);
                datosTranTerr = em.merge(datosTranTerr);
            }
            Datostranporteaereo datosTranAero = historialtransportes.getDatosTranAero();
            if (datosTranAero != null) {
                datosTranAero.getHistorialtransportesCollection().remove(historialtransportes);
                datosTranAero = em.merge(datosTranAero);
            }
            em.remove(historialtransportes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historialtransportes> findHistorialtransportesEntities() {
        return findHistorialtransportesEntities(true, -1, -1);
    }

    public List<Historialtransportes> findHistorialtransportesEntities(int maxResults, int firstResult) {
        return findHistorialtransportesEntities(false, maxResults, firstResult);
    }

    private List<Historialtransportes> findHistorialtransportesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historialtransportes.class));
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

    public Historialtransportes findHistorialtransportes(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historialtransportes.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialtransportesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historialtransportes> rt = cq.from(Historialtransportes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
