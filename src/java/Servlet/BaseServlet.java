/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import config.App;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServlet;
import javax.transaction.UserTransaction;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public abstract class BaseServlet extends HttpServlet{
    @PersistenceUnit(unitName=App.PERSISTANCE_NAME)
    protected EntityManagerFactory emf;
    
    @Resource
    protected UserTransaction utx;
}
