/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Controller;

import Entity.Exam;
import Entity.Users;
import JPAController.ExamJpaController;
import JPAController.UsersJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Asus
 */
public class ExamController {
     private final ExamJpaController ejc;
        public ExamController(EntityManagerFactory emf,UserTransaction utx) {
        this.ejc = new ExamJpaController(utx, emf);
    }
    public Exam findExamById(int id){
        return ejc.findExam(id);
    }
    public List<Exam> findAllExams(){
        return ejc.findExamEntities();
    }
}
