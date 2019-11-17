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
import jpa.entities.Student;
import jpa.entities.User;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class UserJpaController implements Serializable {

    public UserJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) throws RollbackFailureException, Exception {
        if (user.getClassroomMemberCollection() == null) {
            user.setClassroomMemberCollection(new ArrayList<ClassroomMember>());
        }
        if (user.getStudentCollection() == null) {
            user.setStudentCollection(new ArrayList<Student>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<ClassroomMember> attachedClassroomMemberCollection = new ArrayList<ClassroomMember>();
            for (ClassroomMember classroomMemberCollectionClassroomMemberToAttach : user.getClassroomMemberCollection()) {
                classroomMemberCollectionClassroomMemberToAttach = em.getReference(classroomMemberCollectionClassroomMemberToAttach.getClass(), classroomMemberCollectionClassroomMemberToAttach.getId());
                attachedClassroomMemberCollection.add(classroomMemberCollectionClassroomMemberToAttach);
            }
            user.setClassroomMemberCollection(attachedClassroomMemberCollection);
            Collection<Student> attachedStudentCollection = new ArrayList<Student>();
            for (Student studentCollectionStudentToAttach : user.getStudentCollection()) {
                studentCollectionStudentToAttach = em.getReference(studentCollectionStudentToAttach.getClass(), studentCollectionStudentToAttach.getId());
                attachedStudentCollection.add(studentCollectionStudentToAttach);
            }
            user.setStudentCollection(attachedStudentCollection);
            em.persist(user);
            for (ClassroomMember classroomMemberCollectionClassroomMember : user.getClassroomMemberCollection()) {
                User oldUserIdOfClassroomMemberCollectionClassroomMember = classroomMemberCollectionClassroomMember.getUserId();
                classroomMemberCollectionClassroomMember.setUserId(user);
                classroomMemberCollectionClassroomMember = em.merge(classroomMemberCollectionClassroomMember);
                if (oldUserIdOfClassroomMemberCollectionClassroomMember != null) {
                    oldUserIdOfClassroomMemberCollectionClassroomMember.getClassroomMemberCollection().remove(classroomMemberCollectionClassroomMember);
                    oldUserIdOfClassroomMemberCollectionClassroomMember = em.merge(oldUserIdOfClassroomMemberCollectionClassroomMember);
                }
            }
            for (Student studentCollectionStudent : user.getStudentCollection()) {
                User oldUserIdOfStudentCollectionStudent = studentCollectionStudent.getUserId();
                studentCollectionStudent.setUserId(user);
                studentCollectionStudent = em.merge(studentCollectionStudent);
                if (oldUserIdOfStudentCollectionStudent != null) {
                    oldUserIdOfStudentCollectionStudent.getStudentCollection().remove(studentCollectionStudent);
                    oldUserIdOfStudentCollectionStudent = em.merge(oldUserIdOfStudentCollectionStudent);
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

    public void edit(User user) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            User persistentUser = em.find(User.class, user.getId());
            Collection<ClassroomMember> classroomMemberCollectionOld = persistentUser.getClassroomMemberCollection();
            Collection<ClassroomMember> classroomMemberCollectionNew = user.getClassroomMemberCollection();
            Collection<Student> studentCollectionOld = persistentUser.getStudentCollection();
            Collection<Student> studentCollectionNew = user.getStudentCollection();
            List<String> illegalOrphanMessages = null;
            for (ClassroomMember classroomMemberCollectionOldClassroomMember : classroomMemberCollectionOld) {
                if (!classroomMemberCollectionNew.contains(classroomMemberCollectionOldClassroomMember)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClassroomMember " + classroomMemberCollectionOldClassroomMember + " since its userId field is not nullable.");
                }
            }
            for (Student studentCollectionOldStudent : studentCollectionOld) {
                if (!studentCollectionNew.contains(studentCollectionOldStudent)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Student " + studentCollectionOldStudent + " since its userId field is not nullable.");
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
            user.setClassroomMemberCollection(classroomMemberCollectionNew);
            Collection<Student> attachedStudentCollectionNew = new ArrayList<Student>();
            for (Student studentCollectionNewStudentToAttach : studentCollectionNew) {
                studentCollectionNewStudentToAttach = em.getReference(studentCollectionNewStudentToAttach.getClass(), studentCollectionNewStudentToAttach.getId());
                attachedStudentCollectionNew.add(studentCollectionNewStudentToAttach);
            }
            studentCollectionNew = attachedStudentCollectionNew;
            user.setStudentCollection(studentCollectionNew);
            user = em.merge(user);
            for (ClassroomMember classroomMemberCollectionNewClassroomMember : classroomMemberCollectionNew) {
                if (!classroomMemberCollectionOld.contains(classroomMemberCollectionNewClassroomMember)) {
                    User oldUserIdOfClassroomMemberCollectionNewClassroomMember = classroomMemberCollectionNewClassroomMember.getUserId();
                    classroomMemberCollectionNewClassroomMember.setUserId(user);
                    classroomMemberCollectionNewClassroomMember = em.merge(classroomMemberCollectionNewClassroomMember);
                    if (oldUserIdOfClassroomMemberCollectionNewClassroomMember != null && !oldUserIdOfClassroomMemberCollectionNewClassroomMember.equals(user)) {
                        oldUserIdOfClassroomMemberCollectionNewClassroomMember.getClassroomMemberCollection().remove(classroomMemberCollectionNewClassroomMember);
                        oldUserIdOfClassroomMemberCollectionNewClassroomMember = em.merge(oldUserIdOfClassroomMemberCollectionNewClassroomMember);
                    }
                }
            }
            for (Student studentCollectionNewStudent : studentCollectionNew) {
                if (!studentCollectionOld.contains(studentCollectionNewStudent)) {
                    User oldUserIdOfStudentCollectionNewStudent = studentCollectionNewStudent.getUserId();
                    studentCollectionNewStudent.setUserId(user);
                    studentCollectionNewStudent = em.merge(studentCollectionNewStudent);
                    if (oldUserIdOfStudentCollectionNewStudent != null && !oldUserIdOfStudentCollectionNewStudent.equals(user)) {
                        oldUserIdOfStudentCollectionNewStudent.getStudentCollection().remove(studentCollectionNewStudent);
                        oldUserIdOfStudentCollectionNewStudent = em.merge(oldUserIdOfStudentCollectionNewStudent);
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
                Integer id = user.getId();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ClassroomMember> classroomMemberCollectionOrphanCheck = user.getClassroomMemberCollection();
            for (ClassroomMember classroomMemberCollectionOrphanCheckClassroomMember : classroomMemberCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the ClassroomMember " + classroomMemberCollectionOrphanCheckClassroomMember + " in its classroomMemberCollection field has a non-nullable userId field.");
            }
            Collection<Student> studentCollectionOrphanCheck = user.getStudentCollection();
            for (Student studentCollectionOrphanCheckStudent : studentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Student " + studentCollectionOrphanCheckStudent + " in its studentCollection field has a non-nullable userId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(user);
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

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
