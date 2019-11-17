/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import jpa.controller.exceptions.NonexistentEntityException;
import jpa.controller.exceptions.RollbackFailureException;
import jpa.entities.ClassroomExam;
import jpa.entities.ExamTag;
import jpa.entities.Tag;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class ExamTagJpaController implements Serializable {

    public ExamTagJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ExamTag examTag) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ClassroomExam examId = examTag.getExamId();
            if (examId != null) {
                examId = em.getReference(examId.getClass(), examId.getId());
                examTag.setExamId(examId);
            }
            Tag tagId = examTag.getTagId();
            if (tagId != null) {
                tagId = em.getReference(tagId.getClass(), tagId.getId());
                examTag.setTagId(tagId);
            }
            em.persist(examTag);
            if (examId != null) {
                examId.getExamTagCollection().add(examTag);
                examId = em.merge(examId);
            }
            if (tagId != null) {
                tagId.getExamTagCollection().add(examTag);
                tagId = em.merge(tagId);
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

    public void edit(ExamTag examTag) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ExamTag persistentExamTag = em.find(ExamTag.class, examTag.getId());
            ClassroomExam examIdOld = persistentExamTag.getExamId();
            ClassroomExam examIdNew = examTag.getExamId();
            Tag tagIdOld = persistentExamTag.getTagId();
            Tag tagIdNew = examTag.getTagId();
            if (examIdNew != null) {
                examIdNew = em.getReference(examIdNew.getClass(), examIdNew.getId());
                examTag.setExamId(examIdNew);
            }
            if (tagIdNew != null) {
                tagIdNew = em.getReference(tagIdNew.getClass(), tagIdNew.getId());
                examTag.setTagId(tagIdNew);
            }
            examTag = em.merge(examTag);
            if (examIdOld != null && !examIdOld.equals(examIdNew)) {
                examIdOld.getExamTagCollection().remove(examTag);
                examIdOld = em.merge(examIdOld);
            }
            if (examIdNew != null && !examIdNew.equals(examIdOld)) {
                examIdNew.getExamTagCollection().add(examTag);
                examIdNew = em.merge(examIdNew);
            }
            if (tagIdOld != null && !tagIdOld.equals(tagIdNew)) {
                tagIdOld.getExamTagCollection().remove(examTag);
                tagIdOld = em.merge(tagIdOld);
            }
            if (tagIdNew != null && !tagIdNew.equals(tagIdOld)) {
                tagIdNew.getExamTagCollection().add(examTag);
                tagIdNew = em.merge(tagIdNew);
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
                Integer id = examTag.getId();
                if (findExamTag(id) == null) {
                    throw new NonexistentEntityException("The examTag with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ExamTag examTag;
            try {
                examTag = em.getReference(ExamTag.class, id);
                examTag.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examTag with id " + id + " no longer exists.", enfe);
            }
            ClassroomExam examId = examTag.getExamId();
            if (examId != null) {
                examId.getExamTagCollection().remove(examTag);
                examId = em.merge(examId);
            }
            Tag tagId = examTag.getTagId();
            if (tagId != null) {
                tagId.getExamTagCollection().remove(examTag);
                tagId = em.merge(tagId);
            }
            em.remove(examTag);
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

    public List<ExamTag> findExamTagEntities() {
        return findExamTagEntities(true, -1, -1);
    }

    public List<ExamTag> findExamTagEntities(int maxResults, int firstResult) {
        return findExamTagEntities(false, maxResults, firstResult);
    }

    private List<ExamTag> findExamTagEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ExamTag.class));
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

    public ExamTag findExamTag(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExamTag.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamTagCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ExamTag> rt = cq.from(ExamTag.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
