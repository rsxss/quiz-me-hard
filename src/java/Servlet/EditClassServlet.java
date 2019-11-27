/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.controller.ClassroomJpaController;
import model.controller.ClassroomMemberJpaController;
import model.controller.ClassroomTeacherJpaController;
import model.controller.UserJpaController;
import model.controller.exceptions.RollbackFailureException;
import model.entities.Classroom;
import model.entities.ClassroomMember;
import model.entities.ClassroomTeacher;
import model.entities.User;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class EditClassServlet extends BaseServlet {

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
        String className = request.getParameter("className");
        UserJpaController ujc = new UserJpaController(utx, emf);
        List<User> users = ujc.findByNonStudent();
        request.setAttribute("users", users);
        request.setAttribute("className", className);
        getServletContext().getRequestDispatcher("/EditClass.jsp").forward(request, response);
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
        String editedClassroomName = request.getParameter("editedClassName");
        String classroomName = request.getParameter("className");
        String classroomDescription = request.getParameter("classDescription");
//        Integer teacherId = Integer.valueOf(request.getParameter("teacher"));

        ClassroomJpaController cjc = new ClassroomJpaController(utx, emf);

        Classroom editedClassroom = cjc.findClassroomByName(editedClassroomName);
//        Classroom classroom = new Classroom();
        editedClassroom.setClassroomName(classroomName);
        editedClassroom.setClassroomDescription(classroomDescription);

        try {
            cjc.edit(editedClassroom);
            response.sendRedirect("SelectClass");
        } catch (Exception ex) {
            getServletContext().log("Edit classroom failed.");
            getServletContext().log(ex.toString());
            getServletContext().log(Arrays.toString(ex.getStackTrace()));
        } 
        response.sendRedirect(request.getHeader("Referer"));
        
    }
       
//    private void destroyOldClassroomTeacher(ClassroomTeacherJpaController ctjc, Classroom oldClassroom){
//        ArrayList<ClassroomTeacher> oldClassroomTeachers = 
//                (ArrayList<ClassroomTeacher>) oldClassroom.getClassroomTeacherList();
//        
//        oldClassroomTeachers.forEach((ClassroomTeacher oldClassroomTeacher) -> {
//            try {
//                ctjc.destroy(oldClassroomTeacher.getId());
//            } catch (RollbackFailureException ex) {
//                Logger.getLogger(EditClassServlet.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (Exception ex) {
//                Logger.getLogger(EditClassServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
//    }
    
//    private Collection<ClassroomTeacher> preparedClassroomTeacher(Classroom classroom, Integer teacherId) throws Exception{
//        ClassroomTeacherJpaController ctjc = new ClassroomTeacherJpaController(utx, emf);
//        UserJpaController ujc = new UserJpaController(utx, emf);
//        
//        ujc.findUser(teacherId);
//        
//        this.destroyOldClassroomTeacher(ctjc, classroom);
//        
//        Collection<ClassroomTeacher> classroomTeachers = new ArrayList<>();
//        ClassroomTeacher classroomTeacher = new ClassroomTeacher();
//        classroomTeacher.setClassroomMemberId(classroomMember);
//        classroomTeacher.setClassroomId(classroom);
//        ctjc.create(classroomTeacher);
//        classroomTeacher = ctjc.findClassroomTeacherByClassroomMember(classroomMember);
//        classroomTeachers.add(classroomTeacher);
//        return classroomTeachers;
//    }
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
