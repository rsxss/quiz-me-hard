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
import jpa.entities.ClassroomMember;
import jpa.entities.ClassroomTeacher;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class ClassroomTeacherJpaController implements Serializable {

    public ClassroomTeacherJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ClassroomTeacher classroomTeacher) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ClassroomMember classroomMemberId = classroomTeacher.getClassroomMemberId();
            if (classroomMemberId != null) {
                classroomMemberId = em.getReference(classroomMemberId.getClass(), classroomMemberId.getId());
                classroomTeacher.setClassroomMemberId(classroomMemberId);
            }
            em.persist(classroomTeacher);
            if (classroomMemberId != null) {
                classroomMemberId.getClassroomTeacherCollection().add(classroomTeacher);
                classroomMemberId = em.merge(classroomMemberId);
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

    public void edit(ClassroomTeacher classroomTeacher) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ClassroomTeacher persistentClassroomTeacher = em.find(ClassroomTeacher.class, classroomTeacher.getId());
            ClassroomMember classroomMemberIdOld = persistentClassroomTeacher.getClassroomMemberId();
            ClassroomMember classroomMemberIdNew = classroomTeacher.getClassroomMemberId();
            if (classroomMemberIdNew != null) {
                classroomMemberIdNew = em.getReference(classroomMemberIdNew.getClass(), classroomMemberIdNew.getId());
                classroomTeacher.setClassroomMemberId(classroomMemberIdNew);
            }
            classroomTeacher = em.merge(classroomTeacher);
            if (classroomMemberIdOld != null && !classroomMemberIdOld.equals(classroomMemberIdNew)) {
                classroomMemberIdOld.getClassroomTeacherCollection().remove(classroomTeacher);
                classroomMemberIdOld = em.merge(classroomMemberIdOld);
            }
            if (classroomMemberIdNew != null && !classroomMemberIdNew.equals(classroomMemberIdOld)) {
                classroomMemberIdNew.getClassroomTeacherCollection().add(classroomTeacher);
                classroomMemberIdNew = em.merge(classroomMemberIdNew);
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
                Integer id = classroomTeacher.getId();
                if (findClassroomTeacher(id) == null) {
                    throw new NonexistentEntityException("The classroomTeacher with id " + id + " no longer exists.");
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
            ClassroomTeacher classroomTeacher;
            try {
                classroomTeacher = em.getReference(ClassroomTeacher.class, id);
                classroomTeacher.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The classroomTeacher with id " + id + " no longer exists.", enfe);
            }
            ClassroomMember classroomMemberId = classroomTeacher.getClassroomMemberId();
            if (classroomMemberId != null) {
                classroomMemberId.getClassroomTeacherCollection().remove(classroomTeacher);
                classroomMemberId = em.merge(classroomMemberId);
            }
            em.remove(classroomTeacher);
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

    public List<ClassroomTeacher> findClassroomTeacherEntities() {
        return findClassroomTeacherEntities(true, -1, -1);
    }

    public List<ClassroomTeacher> findClassroomTeacherEntities(int maxResults, int firstResult) {
        return findClassroomTeacherEntities(false, maxResults, firstResult);
    }

    private List<ClassroomTeacher> findClassroomTeacherEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClassroomTeacher.class));
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

    public ClassroomTeacher findClassroomTeacher(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClassroomTeacher.class, id);
        } finally {
            em.close();
        }
    }

    public int getClassroomTeacherCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClassroomTeacher> rt = cq.from(ClassroomTeacher.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
