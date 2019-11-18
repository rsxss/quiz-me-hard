package Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import config.App;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.controller.ClassroomJpaController;
import model.entities.Classroom;

/**
 *
 * @author Asus
 */
public class ClassInfoServlet extends HttpServlet {

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
        getServletContext().getRequestDispatcher("/ClassInfo.jsp").forward(request, response);
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(App.PERSISTANCE_NAME);
        if (!(Objects.isNull(classroomName)||classroomName.equals(""))){
//            ClassroomJpaController cjc = new ClassroomJpaController(emf);
//            Classroom classroom = cjc.findClassroomByName(classroomName);
            Classroom classroom = getClassroom(emf, classroomName);
            if (!(Objects.isNull(classroom))){
                request.setAttribute("classroom", classroom);
                getServletContext().getRequestDispatcher("/ClassInfo.jsp").forward(request,response);
                return;
            }
        } 
        request.setAttribute("message", "Classroom doesn't exists.");
        request.setAttribute("messageLevel", "error");
        List<Classroom> classrooms = SelectClassServlet.getClassrooms(emf);
        request.setAttribute("classrooms", classrooms);
        getServletContext().getRequestDispatcher("/SelectClass.jsp").forward(request, response);
        emf.close();
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
        processRequest(request, response);
    }
    
    public static Classroom getClassroom(EntityManagerFactory emf, String classroomName){
        ClassroomJpaController cjc = new ClassroomJpaController(emf);
        return cjc.findClassroomByName(classroomName);
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
