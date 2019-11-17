/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.entities.ClassroomMember;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.controller.exceptions.IllegalOrphanException;
import jpa.controller.exceptions.NonexistentEntityException;
import jpa.controller.exceptions.RollbackFailureException;
import jpa.entities.Classroom;
import jpa.entities.ClassroomExam;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class ClassroomJpaController implements Serializable {

    public ClassroomJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Classroom classroom) throws RollbackFailureException, Exception {
        if (classroom.getClassroomMemberCollection() == null) {
            classroom.setClassroomMemberCollection(new ArrayList<ClassroomMember>());
        }
        if (classroom.getClassroomExamCollection() == null) {
            classroom.setClassroomExamCollection(new ArrayList<ClassroomExam>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<ClassroomMember> attachedClassroomMemberCollection = new ArrayList<ClassroomMember>();
            for (ClassroomMember classroomMemberCollectionClassroomMemberToAttach : classroom.getClassroomMemberCollection()) {
                classroomMemberCollectionClassroomMemberToAttach = em.getReference(classroomMemberCollectionClassroomMemberToAttach.getClass(), classroomMemberCollectionClassroomMemberToAttach.getId());
                attachedClassroomMemberCollection.add(classroomMemberCollectionClassroomMemberToAttach);
            }
            classroom.setClassroomMemberCollection(attachedClassroomMemberCollection);
            Collection<ClassroomExam> attachedClassroomExamCollection = new ArrayList<ClassroomExam>();
            for (ClassroomExam classroomExamCollectionClassroomExamToAttach : classroom.getClassroomExamCollection()) {
                classroomExamCollectionClassroomExamToAttach = em.getReference(classroomExamCollectionClassroomExamToAttach.getClass(), classroomExamCollectionClassroomExamToAttach.getId());
                attachedClassroomExamCollection.add(classroomExamCollectionClassroomExamToAttach);
            }
            classroom.setClassroomExamCollection(attachedClassroomExamCollection);
            em.persist(classroom);
            for (ClassroomMember classroomMemberCollectionClassroomMember : classroom.getClassroomMemberCollection()) {
                Classroom oldClassroomIdOfClassroomMemberCollectionClassroomMember = classroomMemberCollectionClassroomMember.getClassroomId();
                classroomMemberCollectionClassroomMember.setClassroomId(classroom);
                classroomMemberCollectionClassroomMember = em.merge(classroomMemberCollectionClassroomMember);
                if (oldClassroomIdOfClassroomMemberCollectionClassroomMember != null) {
                    oldClassroomIdOfClassroomMemberCollectionClassroomMember.getClassroomMemberCollection().remove(classroomMemberCollectionClassroomMember);
                    oldClassroomIdOfClassroomMemberCollectionClassroomMember = em.merge(oldClassroomIdOfClassroomMemberCollectionClassroomMember);
                }
            }
            for (ClassroomExam classroomExamCollectionClassroomExam : classroom.getClassroomExamCollection()) {
                Classroom oldClassroomIdOfClassroomExamCollectionClassroomExam = classroomExamCollectionClassroomExam.getClassroomId();
                classroomExamCollectionClassroomExam.setClassroomId(classroom);
                classroomExamCollectionClassroomExam = em.merge(classroomExamCollectionClassroomExam);
                if (oldClassroomIdOfClassroomExamCollectionClassroomExam != null) {
                    oldClassroomIdOfClassroomExamCollectionClassroomExam.getClassroomExamCollection().remove(classroomExamCollectionClassroomExam);
                    oldClassroomIdOfClassroomExamCollectionClassroomExam = em.merge(oldClassroomIdOfClassroomExamCollectionClassroomExam);
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

    public void edit(Classroom classroom) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Classroom persistentClassroom = em.find(Classroom.class, classroom.getId());
            Collection<ClassroomMember> classroomMemberCollectionOld = persistentClassroom.getClassroomMemberCollection();
            Collection<ClassroomMember> classroomMemberCollectionNew = classroom.getClassroomMemberCollection();
            Collection<ClassroomExam> classroomExamCollectionOld = persistentClassroom.getClassroomExamCollection();
            Collection<ClassroomExam> classroomExamCollectionNew = classroom.getClassroomExamCollection();
            List<String> illegalOrphanMessages = null;
            for (ClassroomMember classroomMemberCollectionOldClassroomMember : classroomMemberCollectionOld) {
                if (!classroomMemberCollectionNew.contains(classroomMemberCollectionOldClassroomMember)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClassroomMember " + classroomMemberCollectionOldClassroomMember + " since its classroomId field is not nullable.");
                }
            }
            for (ClassroomExam classroomExamCollectionOldClassroomExam : classroomExamCollectionOld) {
                if (!classroomExamCollectionNew.contains(classroomExamCollectionOldClassroomExam)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClassroomExam " + classroomExamCollectionOldClassroomExam + " since its classroomId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ClassroomMember> attachedClassroomMemberCollectionNew = new ArrayList<ClassroomMember>();
            for (ClassroomMember classroomMemberCollectionNewClassroomMemberToAttach : classroomMemberCollectionNew) {
                classroomMemberCollectionNewClassroomMemberToAttach = em.getReference(classroomMemberCollectionNewClassroomMemberToAttach.getClass(), classroomMemberCollectionNewClassroomMemberToAttach.getId());
                attachedClassroomMemberCollectionNew.add(classroomMemberCollectionNewClassroomMemberToAttach);
            }
            classroomMemberCollectionNew = attachedClassroomMemberCollectionNew;
            classroom.setClassroomMemberCollection(classroomMemberCollectionNew);
            Collection<ClassroomExam> attachedClassroomExamCollectionNew = new ArrayList<ClassroomExam>();
            for (ClassroomExam classroomExamCollectionNewClassroomExamToAttach : classroomExamCollectionNew) {
                classroomExamCollectionNewClassroomExamToAttach = em.getReference(classroomExamCollectionNewClassroomExamToAttach.getClass(), classroomExamCollectionNewClassroomExamToAttach.getId());
                attachedClassroomExamCollectionNew.add(classroomExamCollectionNewClassroomExamToAttach);
            }
            classroomExamCollectionNew = attachedClassroomExamCollectionNew;
            classroom.setClassroomExamCollection(classroomExamCollectionNew);
            classroom = em.merge(classroom);
            for (ClassroomMember classroomMemberCollectionNewClassroomMember : classroomMemberCollectionNew) {
                if (!classroomMemberCollectionOld.contains(classroomMemberCollectionNewClassroomMember)) {
                    Classroom oldClassroomIdOfClassroomMemberCollectionNewClassroomMember = classroomMemberCollectionNewClassroomMember.getClassroomId();
                    classroomMemberCollectionNewClassroomMember.setClassroomId(classroom);
                    classroomMemberCollectionNewClassroomMember = em.merge(classroomMemberCollectionNewClassroomMember);
                    if (oldClassroomIdOfClassroomMemberCollectionNewClassroomMember != null && !oldClassroomIdOfClassroomMemberCollectionNewClassroomMember.equals(classroom)) {
                        oldClassroomIdOfClassroomMemberCollectionNewClassroomMember.getClassroomMemberCollection().remove(classroomMemberCollectionNewClassroomMember);
                        oldClassroomIdOfClassroomMemberCollectionNewClassroomMember = em.merge(oldClassroomIdOfClassroomMemberCollectionNewClassroomMember);
                    }
                }
            }
            for (ClassroomExam classroomExamCollectionNewClassroomExam : classroomExamCollectionNew) {
                if (!classroomExamCollectionOld.contains(classroomExamCollectionNewClassroomExam)) {
                    Classroom oldClassroomIdOfClassroomExamCollectionNewClassroomExam = classroomExamCollectionNewClassroomExam.getClassroomId();
                    classroomExamCollectionNewClassroomExam.setClassroomId(classroom);
                    classroomExamCollectionNewClassroomExam = em.merge(classroomExamCollectionNewClassroomExam);
                    if (oldClassroomIdOfClassroomExamCollectionNewClassroomExam != null && !oldClassroomIdOfClassroomExamCollectionNewClassroomExam.equals(classroom)) {
                        oldClassroomIdOfClassroomExamCollectionNewClassroomExam.getClassroomExamCollection().remove(classroomExamCollectionNewClassroomExam);
                        oldClassroomIdOfClassroomExamCollectionNewClassroomExam = em.merge(oldClassroomIdOfClassroomExamCollectionNewClassroomExam);
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
                Integer id = classroom.getId();
                if (findClassroom(id) == null) {
                    throw new NonexistentEntityException("The classroom with id " + id + " no longer exists.");
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
            Classroom classroom;
            try {
                classroom = em.getReference(Classroom.class, id);
                classroom.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The classroom with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ClassroomMember> classroomMemberCollectionOrphanCheck = classroom.getClassroomMemberCollection();
            for (ClassroomMember classroomMemberCollectionOrphanCheckClassroomMember : classroomMemberCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Classroom (" + classroom + ") cannot be destroyed since the ClassroomMember " + classroomMemberCollectionOrphanCheckClassroomMember + " in its classroomMemberCollection field has a non-nullable classroomId field.");
            }
            Collection<ClassroomExam> classroomExamCollectionOrphanCheck = classroom.getClassroomExamCollection();
            for (ClassroomExam classroomExamCollectionOrphanCheckClassroomExam : classroomExamCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Classroom (" + classroom + ") cannot be destroyed since the ClassroomExam " + classroomExamCollectionOrphanCheckClassroomExam + " in its classroomExamCollection field has a non-nullable classroomId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(classroom);
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

    public List<Classroom> findClassroomEntities() {
        return findClassroomEntities(true, -1, -1);
    }

    public List<Classroom> findClassroomEntities(int maxResults, int firstResult) {
        return findClassroomEntities(false, maxResults, firstResult);
    }

    private List<Classroom> findClassroomEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Classroom.class));
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

    public Classroom findClassroom(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Classroom.class, id);
        } finally {
            em.close();
        }
    }

    public int getClassroomCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Classroom> rt = cq.from(Classroom.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
