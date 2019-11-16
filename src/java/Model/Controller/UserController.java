/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Controller;

import Entity.Users;
import JPAController.UsersJpaController;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Asus
 */
public class UserController {
    private final UsersJpaController ujc;
    
    public UserController(EntityManagerFactory emf,UserTransaction utx) {
        this.ujc = new UsersJpaController(utx, emf);
    }
    public Users findUserByUsername(String username){
        return ujc.findUsersWithUsername(username);
    }
}
