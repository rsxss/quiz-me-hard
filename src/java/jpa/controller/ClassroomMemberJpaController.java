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
import jpa.entities.Classroom;
import jpa.entities.User;
import jpa.entities.ClassroomTeacher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import jpa.controller.exceptions.IllegalOrphanException;
import jpa.controller.exceptions.NonexistentEntityException;
import jpa.controller.exceptions.RollbackFailureException;
import jpa.entities.ClassroomMember;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class ClassroomMemberJpaController implements Serializable {

    public ClassroomMemberJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ClassroomMember classroomMember) throws RollbackFailureException, Exception {
        if (classroomMember.getClassroomTeacherCollection() == null) {
            classroomMember.setClassroomTeacherCollection(new ArrayList<ClassroomTeacher>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Classroom classroomId = classroomMember.getClassroomId();
            if (classroomId != null) {
                classroomId = em.getReference(classroomId.getClass(), classroomId.getId());
                classroomMember.setClassroomId(classroomId);
            }
            User userId = classroomMember.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getId());
                classroomMember.setUserId(userId);
            }
            Collection<ClassroomTeacher> attachedClassroomTeacherCollection = new ArrayList<ClassroomTeacher>();
            for (ClassroomTeacher classroomTeacherCollectionClassroomTeacherToAttach : classroomMember.getClassroomTeacherCollection()) {
                classroomTeacherCollectionClassroomTeacherToAttach = em.getReference(classroomTeacherCollectionClassroomTeacherToAttach.getClass(), classroomTeacherCollectionClassroomTeacherToAttach.getId());
                attachedClassroomTeacherCollection.add(classroomTeacherCollectionClassroomTeacherToAttach);
            }
            classroomMember.setClassroomTeacherCollection(attachedClassroomTeacherCollection);
            em.persist(classroomMember);
            if (classroomId != null) {
                classroomId.getClassroomMemberCollection().add(classroomMember);
                classroomId = em.merge(classroomId);
            }
            if (userId != null) {
                userId.getClassroomMemberCollection().add(classroomMember);
                userId = em.merge(userId);
            }
            for (ClassroomTeacher classroomTeacherCollectionClassroomTeacher : classroomMember.getClassroomTeacherCollection()) {
                ClassroomMember oldClassroomMemberIdOfClassroomTeacherCollectionClassroomTeacher = classroomTeacherCollectionClassroomTeacher.getClassroomMemberId();
                classroomTeacherCollectionClassroomTeacher.setClassroomMemberId(classroomMember);
                classroomTeacherCollectionClassroomTeacher = em.merge(classroomTeacherCollectionClassroomTeacher);
                if (oldClassroomMemberIdOfClassroomTeacherCollectionClassroomTeacher != null) {
                    oldClassroomMemberIdOfClassroomTeacherCollectionClassroomTeacher.getClassroomTeacherCollection().remove(classroomTeacherCollectionClassroomTeacher);
                    oldClassroomMemberIdOfClassroomTeacherCollectionClassroomTeacher = em.merge(oldClassroomMemberIdOfClassroomTeacherCollectionClassroomTeacher);
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

    public void edit(ClassroomMember classroomMember) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ClassroomMember persistentClassroomMember = em.find(ClassroomMember.class, classroomMember.getId());
            Classroom classroomIdOld = persistentClassroomMember.getClassroomId();
            Classroom classroomIdNew = classroomMember.getClassroomId();
            User userIdOld = persistentClassroomMember.getUserId();
            User userIdNew = classroomMember.getUserId();
            Collection<ClassroomTeacher> classroomTeacherCollectionOld = persistentClassroomMember.getClassroomTeacherCollection();
            Collection<ClassroomTeacher> classroomTeacherCollectionNew = classroomMember.getClassroomTeacherCollection();
            List<String> illegalOrphanMessages = null;
            for (ClassroomTeacher classroomTeacherCollectionOldClassroomTeacher : classroomTeacherCollectionOld) {
                if (!classroomTeacherCollectionNew.contains(classroomTeacherCollectionOldClassroomTeacher)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClassroomTeacher " + classroomTeacherCollectionOldClassroomTeacher + " since its classroomMemberId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (classroomIdNew != null) {
                classroomIdNew = em.getReference(classroomIdNew.getClass(), classroomIdNew.getId());
                classroomMember.setClassroomId(classroomIdNew);
            }
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getId());
                classroomMember.setUserId(userIdNew);
            }
            Collection<ClassroomTeacher> attachedClassroomTeacherCollectionNew = new ArrayList<ClassroomTeacher>();
            for (ClassroomTeacher classroomTeacherCollectionNewClassroomTeacherToAttach : classroomTeacherCollectionNew) {
                classroomTeacherCollectionNewClassroomTeacherToAttach = em.getReference(classroomTeacherCollectionNewClassroomTeacherToAttach.getClass(), classroomTeacherCollectionNewClassroomTeacherToAttach.getId());
                attachedClassroomTeacherCollectionNew.add(classroomTeacherCollectionNewClassroomTeacherToAttach);
            }
            classroomTeacherCollectionNew = attachedClassroomTeacherCollectionNew;
            classroomMember.setClassroomTeacherCollection(classroomTeacherCollectionNew);
            classroomMember = em.merge(classroomMember);
            if (classroomIdOld != null && !classroomIdOld.equals(classroomIdNew)) {
                classroomIdOld.getClassroomMemberCollection().remove(classroomMember);
                classroomIdOld = em.merge(classroomIdOld);
            }
            if (classroomIdNew != null && !classroomIdNew.equals(classroomIdOld)) {
                classroomIdNew.getClassroomMemberCollection().add(classroomMember);
                classroomIdNew = em.merge(classroomIdNew);
            }
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getClassroomMemberCollection().remove(classroomMember);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getClassroomMemberCollection().add(classroomMember);
                userIdNew = em.merge(userIdNew);
            }
            for (ClassroomTeacher classroomTeacherCollectionNewClassroomTeacher : classroomTeacherCollectionNew) {
                if (!classroomTeacherCollectionOld.contains(classroomTeacherCollectionNewClassroomTeacher)) {
                    ClassroomMember oldClassroomMemberIdOfClassroomTeacherCollectionNewClassroomTeacher = classroomTeacherCollectionNewClassroomTeacher.getClassroomMemberId();
                    classroomTeacherCollectionNewClassroomTeacher.setClassroomMemberId(classroomMember);
                    classroomTeacherCollectionNewClassroomTeacher = em.merge(classroomTeacherCollectionNewClassroomTeacher);
                    if (oldClassroomMemberIdOfClassroomTeacherCollectionNewClassroomTeacher != null && !oldClassroomMemberIdOfClassroomTeacherCollectionNewClassroomTeacher.equals(classroomMember)) {
                        oldClassroomMemberIdOfClassroomTeacherCollectionNewClassroomTeacher.getClassroomTeacherCollection().remove(classroomTeacherCollectionNewClassroomTeacher);
                        oldClassroomMemberIdOfClassroomTeacherCollectionNewClassroomTeacher = em.merge(oldClassroomMemberIdOfClassroomTeacherCollectionNewClassroomTeacher);
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
                Integer id = classroomMember.getId();
                if (findClassroomMember(id) == null) {
                    throw new NonexistentEntityException("The classroomMember with id " + id + " no longer exists.");
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
            ClassroomMember classroomMember;
            try {
                classroomMember = em.getReference(ClassroomMember.class, id);
                classroomMember.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The classroomMember with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ClassroomTeacher> classroomTeacherCollectionOrphanCheck = classroomMember.getClassroomTeacherCollection();
            for (ClassroomTeacher classroomTeacherCollectionOrphanCheckClassroomTeacher : classroomTeacherCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ClassroomMember (" + classroomMember + ") cannot be destroyed since the ClassroomTeacher " + classroomTeacherCollectionOrphanCheckClassroomTeacher + " in its classroomTeacherCollection field has a non-nullable classroomMemberId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Classroom classroomId = classroomMember.getClassroomId();
            if (classroomId != null) {
                classroomId.getClassroomMemberCollection().remove(classroomMember);
                classroomId = em.merge(classroomId);
            }
            User userId = classroomMember.getUserId();
            if (userId != null) {
                userId.getClassroomMemberCollection().remove(classroomMember);
                userId = em.merge(userId);
            }
            em.remove(classroomMember);
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

    public List<ClassroomMember> findClassroomMemberEntities() {
        return findClassroomMemberEntities(true, -1, -1);
    }

    public List<ClassroomMember> findClassroomMemberEntities(int maxResults, int firstResult) {
        return findClassroomMemberEntities(false, maxResults, firstResult);
    }

    private List<ClassroomMember> findClassroomMemberEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClassroomMember.class));
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

    public ClassroomMember findClassroomMember(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClassroomMember.class, id);
        } finally {
            em.close();
        }
    }

    public int getClassroomMemberCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClassroomMember> rt = cq.from(ClassroomMember.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
