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
import model.entities.Classroom;
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
import model.entities.ClassroomExam;
import model.entities.ClassroomExamStudentScore;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class ClassroomExamJpaController implements Serializable {

    public ClassroomExamJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ClassroomExam classroomExam) throws RollbackFailureException, Exception {
        if (classroomExam.getExamTagCollection() == null) {
            classroomExam.setExamTagCollection(new ArrayList<ExamTag>());
        }
        if (classroomExam.getClassroomExamStudentScoreCollection() == null) {
            classroomExam.setClassroomExamStudentScoreCollection(new ArrayList<ClassroomExamStudentScore>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Classroom classroomId = classroomExam.getClassroomId();
            if (classroomId != null) {
                classroomId = em.getReference(classroomId.getClass(), classroomId.getId());
                classroomExam.setClassroomId(classroomId);
            }
            Collection<ExamTag> attachedExamTagCollection = new ArrayList<ExamTag>();
            for (ExamTag examTagCollectionExamTagToAttach : classroomExam.getExamTagCollection()) {
                examTagCollectionExamTagToAttach = em.getReference(examTagCollectionExamTagToAttach.getClass(), examTagCollectionExamTagToAttach.getId());
                attachedExamTagCollection.add(examTagCollectionExamTagToAttach);
            }
            classroomExam.setExamTagCollection(attachedExamTagCollection);
            Collection<ClassroomExamStudentScore> attachedClassroomExamStudentScoreCollection = new ArrayList<ClassroomExamStudentScore>();
            for (ClassroomExamStudentScore classroomExamStudentScoreCollectionClassroomExamStudentScoreToAttach : classroomExam.getClassroomExamStudentScoreCollection()) {
                classroomExamStudentScoreCollectionClassroomExamStudentScoreToAttach = em.getReference(classroomExamStudentScoreCollectionClassroomExamStudentScoreToAttach.getClass(), classroomExamStudentScoreCollectionClassroomExamStudentScoreToAttach.getId());
                attachedClassroomExamStudentScoreCollection.add(classroomExamStudentScoreCollectionClassroomExamStudentScoreToAttach);
            }
            classroomExam.setClassroomExamStudentScoreCollection(attachedClassroomExamStudentScoreCollection);
            em.persist(classroomExam);
            if (classroomId != null) {
                classroomId.getClassroomExamCollection().add(classroomExam);
                classroomId = em.merge(classroomId);
            }
            for (ExamTag examTagCollectionExamTag : classroomExam.getExamTagCollection()) {
                ClassroomExam oldExamIdOfExamTagCollectionExamTag = examTagCollectionExamTag.getExamId();
                examTagCollectionExamTag.setExamId(classroomExam);
                examTagCollectionExamTag = em.merge(examTagCollectionExamTag);
                if (oldExamIdOfExamTagCollectionExamTag != null) {
                    oldExamIdOfExamTagCollectionExamTag.getExamTagCollection().remove(examTagCollectionExamTag);
                    oldExamIdOfExamTagCollectionExamTag = em.merge(oldExamIdOfExamTagCollectionExamTag);
                }
            }
            for (ClassroomExamStudentScore classroomExamStudentScoreCollectionClassroomExamStudentScore : classroomExam.getClassroomExamStudentScoreCollection()) {
                ClassroomExam oldExamIdOfClassroomExamStudentScoreCollectionClassroomExamStudentScore = classroomExamStudentScoreCollectionClassroomExamStudentScore.getExamId();
                classroomExamStudentScoreCollectionClassroomExamStudentScore.setExamId(classroomExam);
                classroomExamStudentScoreCollectionClassroomExamStudentScore = em.merge(classroomExamStudentScoreCollectionClassroomExamStudentScore);
                if (oldExamIdOfClassroomExamStudentScoreCollectionClassroomExamStudentScore != null) {
                    oldExamIdOfClassroomExamStudentScoreCollectionClassroomExamStudentScore.getClassroomExamStudentScoreCollection().remove(classroomExamStudentScoreCollectionClassroomExamStudentScore);
                    oldExamIdOfClassroomExamStudentScoreCollectionClassroomExamStudentScore = em.merge(oldExamIdOfClassroomExamStudentScoreCollectionClassroomExamStudentScore);
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

    public void edit(ClassroomExam classroomExam) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ClassroomExam persistentClassroomExam = em.find(ClassroomExam.class, classroomExam.getId());
            Classroom classroomIdOld = persistentClassroomExam.getClassroomId();
            Classroom classroomIdNew = classroomExam.getClassroomId();
            Collection<ExamTag> examTagCollectionOld = persistentClassroomExam.getExamTagCollection();
            Collection<ExamTag> examTagCollectionNew = classroomExam.getExamTagCollection();
            Collection<ClassroomExamStudentScore> classroomExamStudentScoreCollectionOld = persistentClassroomExam.getClassroomExamStudentScoreCollection();
            Collection<ClassroomExamStudentScore> classroomExamStudentScoreCollectionNew = classroomExam.getClassroomExamStudentScoreCollection();
            List<String> illegalOrphanMessages = null;
            for (ExamTag examTagCollectionOldExamTag : examTagCollectionOld) {
                if (!examTagCollectionNew.contains(examTagCollectionOldExamTag)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ExamTag " + examTagCollectionOldExamTag + " since its examId field is not nullable.");
                }
            }
            for (ClassroomExamStudentScore classroomExamStudentScoreCollectionOldClassroomExamStudentScore : classroomExamStudentScoreCollectionOld) {
                if (!classroomExamStudentScoreCollectionNew.contains(classroomExamStudentScoreCollectionOldClassroomExamStudentScore)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClassroomExamStudentScore " + classroomExamStudentScoreCollectionOldClassroomExamStudentScore + " since its examId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (classroomIdNew != null) {
                classroomIdNew = em.getReference(classroomIdNew.getClass(), classroomIdNew.getId());
                classroomExam.setClassroomId(classroomIdNew);
            }
            Collection<ExamTag> attachedExamTagCollectionNew = new ArrayList<ExamTag>();
            for (ExamTag examTagCollectionNewExamTagToAttach : examTagCollectionNew) {
                examTagCollectionNewExamTagToAttach = em.getReference(examTagCollectionNewExamTagToAttach.getClass(), examTagCollectionNewExamTagToAttach.getId());
                attachedExamTagCollectionNew.add(examTagCollectionNewExamTagToAttach);
            }
            examTagCollectionNew = attachedExamTagCollectionNew;
            classroomExam.setExamTagCollection(examTagCollectionNew);
            Collection<ClassroomExamStudentScore> attachedClassroomExamStudentScoreCollectionNew = new ArrayList<ClassroomExamStudentScore>();
            for (ClassroomExamStudentScore classroomExamStudentScoreCollectionNewClassroomExamStudentScoreToAttach : classroomExamStudentScoreCollectionNew) {
                classroomExamStudentScoreCollectionNewClassroomExamStudentScoreToAttach = em.getReference(classroomExamStudentScoreCollectionNewClassroomExamStudentScoreToAttach.getClass(), classroomExamStudentScoreCollectionNewClassroomExamStudentScoreToAttach.getId());
                attachedClassroomExamStudentScoreCollectionNew.add(classroomExamStudentScoreCollectionNewClassroomExamStudentScoreToAttach);
            }
            classroomExamStudentScoreCollectionNew = attachedClassroomExamStudentScoreCollectionNew;
            classroomExam.setClassroomExamStudentScoreCollection(classroomExamStudentScoreCollectionNew);
            classroomExam = em.merge(classroomExam);
            if (classroomIdOld != null && !classroomIdOld.equals(classroomIdNew)) {
                classroomIdOld.getClassroomExamCollection().remove(classroomExam);
                classroomIdOld = em.merge(classroomIdOld);
            }
            if (classroomIdNew != null && !classroomIdNew.equals(classroomIdOld)) {
                classroomIdNew.getClassroomExamCollection().add(classroomExam);
                classroomIdNew = em.merge(classroomIdNew);
            }
            for (ExamTag examTagCollectionNewExamTag : examTagCollectionNew) {
                if (!examTagCollectionOld.contains(examTagCollectionNewExamTag)) {
                    ClassroomExam oldExamIdOfExamTagCollectionNewExamTag = examTagCollectionNewExamTag.getExamId();
                    examTagCollectionNewExamTag.setExamId(classroomExam);
                    examTagCollectionNewExamTag = em.merge(examTagCollectionNewExamTag);
                    if (oldExamIdOfExamTagCollectionNewExamTag != null && !oldExamIdOfExamTagCollectionNewExamTag.equals(classroomExam)) {
                        oldExamIdOfExamTagCollectionNewExamTag.getExamTagCollection().remove(examTagCollectionNewExamTag);
                        oldExamIdOfExamTagCollectionNewExamTag = em.merge(oldExamIdOfExamTagCollectionNewExamTag);
                    }
                }
            }
            for (ClassroomExamStudentScore classroomExamStudentScoreCollectionNewClassroomExamStudentScore : classroomExamStudentScoreCollectionNew) {
                if (!classroomExamStudentScoreCollectionOld.contains(classroomExamStudentScoreCollectionNewClassroomExamStudentScore)) {
                    ClassroomExam oldExamIdOfClassroomExamStudentScoreCollectionNewClassroomExamStudentScore = classroomExamStudentScoreCollectionNewClassroomExamStudentScore.getExamId();
                    classroomExamStudentScoreCollectionNewClassroomExamStudentScore.setExamId(classroomExam);
                    classroomExamStudentScoreCollectionNewClassroomExamStudentScore = em.merge(classroomExamStudentScoreCollectionNewClassroomExamStudentScore);
                    if (oldExamIdOfClassroomExamStudentScoreCollectionNewClassroomExamStudentScore != null && !oldExamIdOfClassroomExamStudentScoreCollectionNewClassroomExamStudentScore.equals(classroomExam)) {
                        oldExamIdOfClassroomExamStudentScoreCollectionNewClassroomExamStudentScore.getClassroomExamStudentScoreCollection().remove(classroomExamStudentScoreCollectionNewClassroomExamStudentScore);
                        oldExamIdOfClassroomExamStudentScoreCollectionNewClassroomExamStudentScore = em.merge(oldExamIdOfClassroomExamStudentScoreCollectionNewClassroomExamStudentScore);
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
                Integer id = classroomExam.getId();
                if (findClassroomExam(id) == null) {
                    throw new NonexistentEntityException("The classroomExam with id " + id + " no longer exists.");
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
            ClassroomExam classroomExam;
            try {
                classroomExam = em.getReference(ClassroomExam.class, id);
                classroomExam.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The classroomExam with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ExamTag> examTagCollectionOrphanCheck = classroomExam.getExamTagCollection();
            for (ExamTag examTagCollectionOrphanCheckExamTag : examTagCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ClassroomExam (" + classroomExam + ") cannot be destroyed since the ExamTag " + examTagCollectionOrphanCheckExamTag + " in its examTagCollection field has a non-nullable examId field.");
            }
            Collection<ClassroomExamStudentScore> classroomExamStudentScoreCollectionOrphanCheck = classroomExam.getClassroomExamStudentScoreCollection();
            for (ClassroomExamStudentScore classroomExamStudentScoreCollectionOrphanCheckClassroomExamStudentScore : classroomExamStudentScoreCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ClassroomExam (" + classroomExam + ") cannot be destroyed since the ClassroomExamStudentScore " + classroomExamStudentScoreCollectionOrphanCheckClassroomExamStudentScore + " in its classroomExamStudentScoreCollection field has a non-nullable examId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Classroom classroomId = classroomExam.getClassroomId();
            if (classroomId != null) {
                classroomId.getClassroomExamCollection().remove(classroomExam);
                classroomId = em.merge(classroomId);
            }
            em.remove(classroomExam);
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

    public List<ClassroomExam> findClassroomExamEntities() {
        return findClassroomExamEntities(true, -1, -1);
    }

    public List<ClassroomExam> findClassroomExamEntities(int maxResults, int firstResult) {
        return findClassroomExamEntities(false, maxResults, firstResult);
    }

    private List<ClassroomExam> findClassroomExamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClassroomExam.class));
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

    public ClassroomExam findClassroomExam(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClassroomExam.class, id);
        } finally {
            em.close();
        }
    }

    public int getClassroomExamCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClassroomExam> rt = cq.from(ClassroomExam.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
