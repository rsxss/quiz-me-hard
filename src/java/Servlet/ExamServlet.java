/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import static Servlet.ClassInfoServlet.getClassroom;
import config.App;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.utils.ExecutionData;
import model.controller.ClassroomExamJpaController;
import model.entities.Classroom;
import model.entities.ClassroomExam;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import model.retrofit.RetrofitExecutionData;
import model.utils.RetrofitClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 *
 * @author Asus
 */
public class ExamServlet extends BaseServlet {

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
        getServletContext().getRequestDispatcher("/Exam.jsp").forward(request, response);
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
        int examId = Integer.parseInt(request.getParameter("examId"));
        
        ClassroomExamJpaController cejpc = new ClassroomExamJpaController(utx, emf);
        ClassroomExam ce = cejpc.findClassroomExam(examId);
        if (!(Objects.isNull(classroomName)||classroomName.equals(""))){
            ClassroomExam classroomExam = ce;//getClassroomExam(emf, examId);
            if (!(Objects.isNull(classroomExam))){
                request.setAttribute("classroomExam", classroomExam);
                request.setAttribute("classroomName", classroomName);
                getServletContext().getRequestDispatcher("/Exam.jsp").forward(request,response);
                return;
            }
        } 
        request.setAttribute("message", "Classroom doesn't exists.");
        request.setAttribute("messageLevel", "error");
        List<Classroom> classrooms = SelectClassServlet.getClassrooms(utx, emf);
        request.setAttribute("classrooms", classrooms);
        getServletContext().getRequestDispatcher("/SelectClass.jsp").forward(request, response);
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
        String code = request.getParameter("execData");
        Gson gson = new Gson();
        ExecutionData execData = gson.fromJson(code, ExecutionData.class);
        
        sendExecData(execData);
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try{
            out.write(gson.toJson(execData));
            out.flush();
        } finally{
            out.close();
        }
        //getServletContext().getRequestDispatcher("/Exam.jsp").forward(request, response);
    }
    
    private void sendExecData(ExecutionData execData) throws  IOException{
        Retrofit retrofit = RetrofitClient.getClient(App.EXECUTION_SERVICE_API);
        RetrofitExecutionData red = retrofit.create(RetrofitExecutionData.class);
        
        Call<ExecutionData> call = red.sendExecData(execData);
        Response<ExecutionData> response = call.execute();
        getServletContext().log(String.format("status: %d",response.code()));
    }
    
//    public static ClassroomExam getClassroomExam(EntityManagerFactory emf, int examId){
//        ClassroomExamJpaController cejpc = new ClassroomExamJpaController(emf);
//        return cejpc.findClassroomExam(examId);
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
