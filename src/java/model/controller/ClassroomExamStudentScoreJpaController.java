/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import model.controller.exceptions.NonexistentEntityException;
import model.controller.exceptions.RollbackFailureException;
import model.entities.ClassroomExam;
import model.entities.ClassroomExamStudentScore;
import model.entities.Student;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class ClassroomExamStudentScoreJpaController implements Serializable {

    public ClassroomExamStudentScoreJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ClassroomExamStudentScore classroomExamStudentScore) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ClassroomExam examId = classroomExamStudentScore.getExamId();
            if (examId != null) {
                examId = em.getReference(examId.getClass(), examId.getId());
                classroomExamStudentScore.setExamId(examId);
            }
            em.persist(classroomExamStudentScore);
            if (examId != null) {
                examId.getClassroomExamStudentScoreCollection().add(classroomExamStudentScore);
                examId = em.merge(examId);
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

    public void edit(ClassroomExamStudentScore classroomExamStudentScore) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ClassroomExamStudentScore persistentClassroomExamStudentScore = em.find(ClassroomExamStudentScore.class, classroomExamStudentScore.getId());
            ClassroomExam examIdOld = persistentClassroomExamStudentScore.getExamId();
            ClassroomExam examIdNew = classroomExamStudentScore.getExamId();
            if (examIdNew != null) {
                examIdNew = em.getReference(examIdNew.getClass(), examIdNew.getId());
                classroomExamStudentScore.setExamId(examIdNew);
            }
            classroomExamStudentScore = em.merge(classroomExamStudentScore);
            if (examIdOld != null && !examIdOld.equals(examIdNew)) {
                examIdOld.getClassroomExamStudentScoreCollection().remove(classroomExamStudentScore);
                examIdOld = em.merge(examIdOld);
            }
            if (examIdNew != null && !examIdNew.equals(examIdOld)) {
                examIdNew.getClassroomExamStudentScoreCollection().add(classroomExamStudentScore);
                examIdNew = em.merge(examIdNew);
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
                Integer id = classroomExamStudentScore.getId();
                if (findClassroomExamStudentScore(id) == null) {
                    throw new NonexistentEntityException("The classroomExamStudentScore with id " + id + " no longer exists.");
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
            ClassroomExamStudentScore classroomExamStudentScore;
            try {
                classroomExamStudentScore = em.getReference(ClassroomExamStudentScore.class, id);
                classroomExamStudentScore.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The classroomExamStudentScore with id " + id + " no longer exists.", enfe);
            }
            ClassroomExam examId = classroomExamStudentScore.getExamId();
            if (examId != null) {
                examId.getClassroomExamStudentScoreCollection().remove(classroomExamStudentScore);
                examId = em.merge(examId);
            }
            em.remove(classroomExamStudentScore);
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

    public List<ClassroomExamStudentScore> findClassroomExamStudentScoreEntities() {
        return findClassroomExamStudentScoreEntities(true, -1, -1);
    }

    public List<ClassroomExamStudentScore> findClassroomExamStudentScoreEntities(int maxResults, int firstResult) {
        return findClassroomExamStudentScoreEntities(false, maxResults, firstResult);
    }

    private List<ClassroomExamStudentScore> findClassroomExamStudentScoreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClassroomExamStudentScore.class));
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

    public ClassroomExamStudentScore findClassroomExamStudentScore(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClassroomExamStudentScore.class, id);
        } finally {
            em.close();
        }
    }
    
    public ClassroomExamStudentScore findClassroomExamStudentScoreByExamByStudent(ClassroomExam ce, Student si) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ClassroomExamStudentScore> query = em.createNamedQuery(
                    "ClassroomExamStudentScore.findByExamByStudent", ClassroomExamStudentScore.class);
            query.setParameter("examId", ce);
            query.setParameter("studentId", si);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public int getClassroomExamStudentScoreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClassroomExamStudentScore> rt = cq.from(ClassroomExamStudentScore.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
