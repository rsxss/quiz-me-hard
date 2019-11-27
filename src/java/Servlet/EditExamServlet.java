/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.controller.ClassroomExamJpaController;
import model.controller.exceptions.NonexistentEntityException;
import model.controller.exceptions.RollbackFailureException;
import model.entities.ClassroomExam;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class EditExamServlet extends BaseServlet {

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
        String classroomName = request.getParameter("className").trim();
        Integer examId = Integer.valueOf(request.getParameter("examId").trim());
        ClassroomExamJpaController cej = new ClassroomExamJpaController(utx, emf);
        ClassroomExam classroomExam = cej.findClassroomExam(examId);
        request.setAttribute("className", classroomName);
        request.setAttribute("classroomExam", classroomExam);
        getServletContext().getRequestDispatcher("/EditExam.jsp").forward(request, response);
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
        String classroomName = request.getParameter("className").trim();
        Integer examId = Integer.valueOf(request.getParameter("examId").trim());
        
        String examName = request.getParameter("examName").trim();
        String examDescription = request.getParameter("examDescription").trim();
        String examTestCase = request.getParameter("examTestCase").trim();
        
        ClassroomExamJpaController cej = new ClassroomExamJpaController(utx, emf);
        ClassroomExam classroomExam = cej.findClassroomExam(examId);
        classroomExam.setName(examName);
        classroomExam.setDescription(examDescription);
        classroomExam.setTestCase(examTestCase);
        try {
            cej.edit(classroomExam);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EditExamServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(EditExamServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EditExamServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect(String.format("SelectExam?className=%s", classroomName));
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
