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
import model.entities.Student;
import model.entities.ClassroomMember;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import model.controller.exceptions.IllegalOrphanException;
import model.controller.exceptions.NonexistentEntityException;
import model.controller.exceptions.RollbackFailureException;
import model.entities.User;

/**
 *
 * @author Administrater
 */
public class UserJpaController implements Serializable {

     public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private UserTransaction utx;
    private EntityManagerFactory emf;
    
    public UserJpaController(UserTransaction utx, EntityManagerFactory emf){
        this.utx = utx;
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) throws RollbackFailureException, Exception {
        if (user.getClassroomMemberCollection() == null) {
            user.setClassroomMemberCollection(new ArrayList<ClassroomMember>());
        }
        EntityManager em = null;
        try {
            em.getTransaction().begin();
            em = getEntityManager();
            Student student = user.getStudent();
            if (student != null) {
                student = em.getReference(student.getClass(), student.getId());
                user.setStudent(student);
            }
            Collection<ClassroomMember> attachedClassroomMemberCollection = new ArrayList<ClassroomMember>();
            for (ClassroomMember classroomMemberCollectionClassroomMemberToAttach : user.getClassroomMemberCollection()) {
                classroomMemberCollectionClassroomMemberToAttach = em.getReference(classroomMemberCollectionClassroomMemberToAttach.getClass(), classroomMemberCollectionClassroomMemberToAttach.getId());
                attachedClassroomMemberCollection.add(classroomMemberCollectionClassroomMemberToAttach);
            }
            user.setClassroomMemberCollection(attachedClassroomMemberCollection);
            em.persist(user);
            if (student != null) {
                User oldUserIdOfStudent = student.getUserId();
                if (oldUserIdOfStudent != null) {
                    oldUserIdOfStudent.setStudent(null);
                    oldUserIdOfStudent = em.merge(oldUserIdOfStudent);
                }
                student.setUserId(user);
                student = em.merge(student);
            }
            for (ClassroomMember classroomMemberCollectionClassroomMember : user.getClassroomMemberCollection()) {
                User oldUserIdOfClassroomMemberCollectionClassroomMember = classroomMemberCollectionClassroomMember.getUserId();
                classroomMemberCollectionClassroomMember.setUserId(user);
                classroomMemberCollectionClassroomMember = em.merge(classroomMemberCollectionClassroomMember);
                if (oldUserIdOfClassroomMemberCollectionClassroomMember != null) {
                    oldUserIdOfClassroomMemberCollectionClassroomMember.getClassroomMemberCollection().remove(classroomMemberCollectionClassroomMember);
                    oldUserIdOfClassroomMemberCollectionClassroomMember = em.merge(oldUserIdOfClassroomMemberCollectionClassroomMember);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
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
            em.getTransaction().begin();
            em = getEntityManager();
            User persistentUser = em.find(User.class, user.getId());
            Student studentOld = persistentUser.getStudent();
            Student studentNew = user.getStudent();
            Collection<ClassroomMember> classroomMemberCollectionOld = persistentUser.getClassroomMemberCollection();
            Collection<ClassroomMember> classroomMemberCollectionNew = user.getClassroomMemberCollection();
            List<String> illegalOrphanMessages = null;
            if (studentOld != null && !studentOld.equals(studentNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Student " + studentOld + " since its userId field is not nullable.");
            }
            for (ClassroomMember classroomMemberCollectionOldClassroomMember : classroomMemberCollectionOld) {
                if (!classroomMemberCollectionNew.contains(classroomMemberCollectionOldClassroomMember)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClassroomMember " + classroomMemberCollectionOldClassroomMember + " since its userId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (studentNew != null) {
                studentNew = em.getReference(studentNew.getClass(), studentNew.getId());
                user.setStudent(studentNew);
            }
            Collection<ClassroomMember> attachedClassroomMemberCollectionNew = new ArrayList<ClassroomMember>();
            for (ClassroomMember classroomMemberCollectionNewClassroomMemberToAttach : classroomMemberCollectionNew) {
                classroomMemberCollectionNewClassroomMemberToAttach = em.getReference(classroomMemberCollectionNewClassroomMemberToAttach.getClass(), classroomMemberCollectionNewClassroomMemberToAttach.getId());
                attachedClassroomMemberCollectionNew.add(classroomMemberCollectionNewClassroomMemberToAttach);
            }
            classroomMemberCollectionNew = attachedClassroomMemberCollectionNew;
            user.setClassroomMemberCollection(classroomMemberCollectionNew);
            user = em.merge(user);
            if (studentNew != null && !studentNew.equals(studentOld)) {
                User oldUserIdOfStudent = studentNew.getUserId();
                if (oldUserIdOfStudent != null) {
                    oldUserIdOfStudent.setStudent(null);
                    oldUserIdOfStudent = em.merge(oldUserIdOfStudent);
                }
                studentNew.setUserId(user);
                studentNew = em.merge(studentNew);
            }
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
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
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
            em.getTransaction().begin();
            em = getEntityManager();
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Student studentOrphanCheck = user.getStudent();
            if (studentOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the Student " + studentOrphanCheck + " in its student field has a non-nullable userId field.");
            }
            Collection<ClassroomMember> classroomMemberCollectionOrphanCheck = user.getClassroomMemberCollection();
            for (ClassroomMember classroomMemberCollectionOrphanCheckClassroomMember : classroomMemberCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This User (" + user + ") cannot be destroyed since the ClassroomMember " + classroomMemberCollectionOrphanCheckClassroomMember + " in its classroomMemberCollection field has a non-nullable userId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
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
    
    public User findByUsername(String username){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch(NoResultException nre) {
            // Swallowing anti-pattern
        }
        finally {
            em.close();
        } return null;
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
