/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import config.App;

//import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionScoped;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class EntityManagerProducer {
    @PersistenceContext(unitName=App.PERSISTANCE_NAME)
    private EntityManager entityManager;

    @Produces
    @TransactionScoped
    public EntityManager create() {
        return entityManager;
    }

    public void close(@Disposes EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }
}
