/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entities.ExamTag;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import model.controller.exceptions.IllegalOrphanException;
import model.controller.exceptions.NonexistentEntityException;
import model.controller.exceptions.RollbackFailureException;
import model.entities.Tag;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class TagJpaController implements Serializable {

    public TagJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tag tag) throws RollbackFailureException, Exception {
        if (tag.getExamTagCollection() == null) {
            tag.setExamTagCollection(new ArrayList<ExamTag>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<ExamTag> attachedExamTagCollection = new ArrayList<ExamTag>();
            for (ExamTag examTagCollectionExamTagToAttach : tag.getExamTagCollection()) {
                examTagCollectionExamTagToAttach = em.getReference(examTagCollectionExamTagToAttach.getClass(), examTagCollectionExamTagToAttach.getId());
                attachedExamTagCollection.add(examTagCollectionExamTagToAttach);
            }
            tag.setExamTagCollection(attachedExamTagCollection);
            em.persist(tag);
            for (ExamTag examTagCollectionExamTag : tag.getExamTagCollection()) {
                Tag oldTagIdOfExamTagCollectionExamTag = examTagCollectionExamTag.getTagId();
                examTagCollectionExamTag.setTagId(tag);
                examTagCollectionExamTag = em.merge(examTagCollectionExamTag);
                if (oldTagIdOfExamTagCollectionExamTag != null) {
                    oldTagIdOfExamTagCollectionExamTag.getExamTagCollection().remove(examTagCollectionExamTag);
                    oldTagIdOfExamTagCollectionExamTag = em.merge(oldTagIdOfExamTagCollectionExamTag);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tag tag) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tag persistentTag = em.find(Tag.class, tag.getId());
            Collection<ExamTag> examTagCollectionOld = persistentTag.getExamTagCollection();
            Collection<ExamTag> examTagCollectionNew = tag.getExamTagCollection();
            List<String> illegalOrphanMessages = null;
            for (ExamTag examTagCollectionOldExamTag : examTagCollectionOld) {
                if (!examTagCollectionNew.contains(examTagCollectionOldExamTag)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ExamTag " + examTagCollectionOldExamTag + " since its tagId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ExamTag> attachedExamTagCollectionNew = new ArrayList<ExamTag>();
            for (ExamTag examTagCollectionNewExamTagToAttach : examTagCollectionNew) {
                examTagCollectionNewExamTagToAttach = em.getReference(examTagCollectionNewExamTagToAttach.getClass(), examTagCollectionNewExamTagToAttach.getId());
                attachedExamTagCollectionNew.add(examTagCollectionNewExamTagToAttach);
            }
            examTagCollectionNew = attachedExamTagCollectionNew;
            tag.setExamTagCollection(examTagCollectionNew);
            tag = em.merge(tag);
            for (ExamTag examTagCollectionNewExamTag : examTagCollectionNew) {
                if (!examTagCollectionOld.contains(examTagCollectionNewExamTag)) {
                    Tag oldTagIdOfExamTagCollectionNewExamTag = examTagCollectionNewExamTag.getTagId();
                    examTagCollectionNewExamTag.setTagId(tag);
                    examTagCollectionNewExamTag = em.merge(examTagCollectionNewExamTag);
                    if (oldTagIdOfExamTagCollectionNewExamTag != null && !oldTagIdOfExamTagCollectionNewExamTag.equals(tag)) {
                        oldTagIdOfExamTagCollectionNewExamTag.getExamTagCollection().remove(examTagCollectionNewExamTag);
                        oldTagIdOfExamTagCollectionNewExamTag = em.merge(oldTagIdOfExamTagCollectionNewExamTag);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tag.getId();
                if (findTag(id) == null) {
                    throw new NonexistentEntityException("The tag with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tag tag;
            try {
                tag = em.getReference(Tag.class, id);
                tag.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tag with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ExamTag> examTagCollectionOrphanCheck = tag.getExamTagCollection();
            for (ExamTag examTagCollectionOrphanCheckExamTag : examTagCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tag (" + tag + ") cannot be destroyed since the ExamTag " + examTagCollectionOrphanCheckExamTag + " in its examTagCollection field has a non-nullable tagId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tag);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tag> findTagEntities() {
        return findTagEntities(true, -1, -1);
    }

    public List<Tag> findTagEntities(int maxResults, int firstResult) {
        return findTagEntities(false, maxResults, firstResult);
    }

    private List<Tag> findTagEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tag.class));
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

    public Tag findTag(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tag.class, id);
        } finally {
            em.close();
        }
    }

    public int getTagCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tag> rt = cq.from(Tag.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
