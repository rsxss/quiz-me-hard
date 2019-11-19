/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

//import model.controller.UserJpaController;

import config.App;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.utils.Authentication;
import model.controller.UserJpaController;
import model.entities.User;

/**
 *
 * @author Asus
 */
public class LoginServlet extends BaseServlet {
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        
        
//        getServletContext().log(user.toString());
//        getServletContext().log(user.getStudent().toString());
//        getServletContext().log(user.getPassword());
//        getServletContext().log(Authentication.getEncryptedPassword(password));
//        getServletContext().log(Authentication.authenticate(user, password) ? "true" : "false");
        
        UserJpaController ujc = new UserJpaController(utx, emf);
        User user = ujc.findByUsername(username);
        if(!Objects.isNull(user)&&Authentication.authenticate(user, password)){
           HttpSession session = request.getSession();
           session.setAttribute("user", user);
           getServletContext().getRequestDispatcher("/SelectClass").include(request, response);
           return;
        }
        request.setAttribute("message", "INVALID CREDENTIALS");
        request.setAttribute("messageLevel", "error");
        getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
