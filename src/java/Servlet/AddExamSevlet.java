/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.controller.ClassroomExamJpaController;
import model.controller.ClassroomJpaController;
import model.entities.ClassroomExam;

/**
 *
 * @author Asus
 */
public class AddExamSevlet extends BaseServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/AddExam.jsp").forward(request, response);
    }

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
        request.setAttribute("className", classroomName);
        getServletContext().getRequestDispatcher("/AddExam.jsp").forward(request, response);
        
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
        String classroomName = request.getParameter("className");
        String examName = request.getParameter("examName");
        String examDescription = request.getParameter("examDescription");
        String examTestCase = request.getParameter("examTestCase");
        
        Enumeration<String> es = request.getParameterNames();
        while(es.hasMoreElements()){
            String paramName = es.nextElement();
            getServletContext().log(String.format("%s: %s", paramName, request.getParameter(paramName)));
        }
        
        ClassroomExam ce = new ClassroomExam();
        ce.setName(examName);
        ce.setDescription(examDescription);
        ce.setTestCase(examTestCase);
        ce.setLevel((short)1);
        ce.setClassroomId(ClassInfoServlet.getClassroom(utx, emf, classroomName));
        
        ClassroomExamJpaController cejc = new ClassroomExamJpaController(utx, emf);
        try {
            cejc.create(ce);
            response.sendRedirect(String.format("SelectExam?className=%s",classroomName));
            return;
        } catch (Exception ex) {
            Logger.getLogger(AddExamSevlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        request.setAttribute("message", "Created failed due to ...");
        request.setAttribute("messageLevel", "error");
        getServletContext().log("Failed");
        getServletContext().getRequestDispatcher(String.format("/AddExam?className=%s",classroomName)).forward(request, response);
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
