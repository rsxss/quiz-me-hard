/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import config.App;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class EntityManagerFactoryProvider {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(App.PERSISTANCE_NAME);
}
