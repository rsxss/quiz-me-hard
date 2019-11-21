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
import model.entities.Classroom;
import model.entities.ClassroomMember;
import model.entities.ClassroomTeacher;
import model.entities.User;

/**
 *
 * @author Asus
 */
public class AddClassServlet extends BaseServlet {

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
            UserJpaController ujc = new UserJpaController(utx, emf);
            List<User> users = ujc.findByNonStudent();
//            getServletContext().log("users.length: "+users.size());
//            users.forEach((user) -> {
//                getServletContext().log(user.toString());
//        });
            request.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/AddClass.jsp").forward(request, response);
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
            UserJpaController ujc = new UserJpaController(utx, emf);
            List<User> users = ujc.findByNonStudent();
            request.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/AddClass.jsp").forward(request, response);
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
        String classroomDescription = request.getParameter("classDescription");
        Integer teacherId = Integer.valueOf(request.getParameter("teacher"));

        ClassroomJpaController cjc = new ClassroomJpaController(utx, emf);

        Classroom classroom = new Classroom();
        classroom.setClassroomName(classroomName);
        classroom.setClassroomDescription(classroomDescription);

        try {
            cjc.create(classroom);
            Collection<ClassroomMember> classroomMembers = preparedFirstClassroomMember(classroom, teacherId);
            classroom.setClassroomMemberCollection(classroomMembers);
            cjc.edit(classroom);
            response.sendRedirect("SelectClass");
        } catch (Exception ex) {
            getServletContext().log("Create classroom failed.");
            getServletContext().log(ex.toString());
            getServletContext().log(Arrays.toString(ex.getStackTrace()));
        } 
        response.sendRedirect(request.getHeader("Referer"));
        
    }
    
    private Collection<ClassroomMember> preparedFirstClassroomMember(Classroom classroom, Integer teacherId) throws Exception{
        UserJpaController ujc = new UserJpaController(utx, emf);
        ClassroomMemberJpaController cmjc = new ClassroomMemberJpaController(utx, emf);
        ClassroomJpaController cjc = new ClassroomJpaController(utx, emf);
        
        Classroom managedClassroom = cjc.findClassroomByName(classroom.getClassroomName());
        User managedUser = ujc.findUser(teacherId);
        
        Collection<ClassroomMember> classroomMembers = new ArrayList<>();
        
        ClassroomMember firstClassroomMember = new ClassroomMember();
        firstClassroomMember.setClassroomId(managedClassroom);
        firstClassroomMember.setUserId(managedUser);
        cmjc.create(firstClassroomMember);
        Collection<ClassroomTeacher> classroomTeachers = preparedClassroomTeacher(firstClassroomMember);
        firstClassroomMember.setClassroomTeacherCollection(classroomTeachers);
        cmjc.edit(firstClassroomMember);
        classroomMembers.add(firstClassroomMember); return classroomMembers;
        
    }
    
    private Collection<ClassroomTeacher> preparedClassroomTeacher(ClassroomMember classroomMember) throws Exception{
        ClassroomTeacherJpaController ctjc = new ClassroomTeacherJpaController(utx, emf);
        Collection<ClassroomTeacher> classroomTeachers = new ArrayList<>();
        ClassroomTeacher classroomTeacher = new ClassroomTeacher();
        classroomTeacher.setClassroomMemberId(classroomMember);
        ctjc.create(classroomTeacher);
        classroomTeacher = ctjc.findClassroomTeacherByClassroomMember(classroomMember);
        classroomTeachers.add(classroomTeacher);
        return classroomTeachers;
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
