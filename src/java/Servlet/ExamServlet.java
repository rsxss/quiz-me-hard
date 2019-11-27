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
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import model.controller.ClassroomExamStudentScoreJpaController;
import model.controller.UserJpaController;
import model.controller.exceptions.RollbackFailureException;
import model.entities.ClassroomExamStudentScore;
import model.entities.Student;
import model.entities.User;
import model.retrofit.RetrofitExecutionData;
import model.utils.ExecutionResult;
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
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        
        Integer examId = Integer.valueOf(request.getParameter("examId"));
        String code = request.getParameter("execData");
        Boolean isSubmit = Integer.valueOf(request.getParameter("confirmSubmit"))==1;
        
        ClassroomExamJpaController cejc = new ClassroomExamJpaController(utx, emf);
        ClassroomExam classroomExam = cejc.findClassroomExam(examId);
        
        Gson gson = new Gson();
        ExecutionData execData = gson.fromJson(code, ExecutionData.class);
        
        String userCode = execData.getCode();
        
        execData.setCode(
                appendTestCase(execData.getCode(), 
                        classroomExam.getTestCase()
                )
        );
        ExecutionResult executionResult = sendExecData(execData);
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try{
            out.write(gson.toJson(executionResult));
            out.flush();
        } finally{
            out.close();
        }
        
        if (Objects.isNull(executionResult.getError()) && isSubmit){
            setResultFor(user, executionResult, examId, userCode);
        }
    }
    
    private void setResultFor(User user, ExecutionResult executionResult, Integer examId, String code){
        Student student = user.getStudent();
        if (Objects.isNull(student)){
            return;
        }
        
        ClassroomExamJpaController cejc = new ClassroomExamJpaController(utx, emf);
        ClassroomExam classroomExam = cejc.findClassroomExam(examId);
        
        ClassroomExamStudentScoreJpaController cessjc = new ClassroomExamStudentScoreJpaController(utx, emf);
        
        try {
            ClassroomExamStudentScore cess = cessjc.findClassroomExamStudentScoreByExamByStudent(classroomExam, student);
            cess.setExamId(classroomExam);
            cess.setStudentId(student);
            cess.setCode(code);
            cess.setScore((float)executionResult.getCaseSummary().getTotalScore());
            cess.setMaxTotalScore(35);
            try {
                cessjc.edit(cess);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(ExamServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ExamServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoResultException nre){
            ClassroomExamStudentScore classroomExamStudentScore = new ClassroomExamStudentScore();
            classroomExamStudentScore.setExamId(classroomExam);
            classroomExamStudentScore.setStudentId(student);
            classroomExamStudentScore.setCode(code);
            classroomExamStudentScore.setScore((float)executionResult.getCaseSummary().getTotalScore());
            classroomExamStudentScore.setMaxTotalScore(35);
            try {
                cessjc.create(classroomExamStudentScore);
            } catch (Exception ex) {
                Logger.getLogger(ExamServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private String appendTestCase(String code, String testCase){
        StringBuilder appendedString = new StringBuilder(code);
        appendedString.append("\n");
        appendedString.append(testCase);
        return appendedString.toString();
    }
    
    private ExecutionResult sendExecData(ExecutionData execData) throws  IOException{
        Retrofit retrofit = RetrofitClient.getClient(App.EXECUTION_SERVICE_API);
        RetrofitExecutionData red = retrofit.create(RetrofitExecutionData.class);
        
        Call<ExecutionResult> call = red.sendExecData(execData);
        Response<ExecutionResult> response = call.execute();
        
//        getServletContext().log(String.format("response json: %s", new Gson().toJson(response.body())));
//        getServletContext().log(String.format("response json: %s", response.body().getError()));
//        getServletContext().log(String.format("totalScore: %.2f",response.body().getCaseSummary().getTotalScore()));
//        getServletContext().log(String.format("status: %f",response.body().getTotalScore()));
//        getServletContext().log(String.format("status: %d",response.code()));
        
        return response.body();
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
