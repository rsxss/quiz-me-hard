<%-- 
    Document   : header
    Created on : Nov 18, 2019, 8:33:18 PM
    Author     : NATWORPONGLOYSWAI
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
        body,h1 {font-family: "Raleway", sans-serif; }
        body, html {height: 100%;background-color: #eee }
        .bgimg {
            background-image: url('images/bg2.png');
            min-height: 100%;
            background-position: center;
            background-size: cover;
        }
        .box{
            border-radius: 25px;
            background-color: cadetblue   ;
            min-height: 80%;
            min-width:  80%;
            margin: 20px;
        }
        .inst{
            height:100%;
            width:25%;border:1px solid #ccc;
            overflow:auto;
            margin-left: 20 px;
        }
        #editor { 
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
        }
    </style>
<div class="w3-container w3-teal">
    <h1>Quiz Me Hard</h1>
    <div class="w3-display-topright w3-padding-large ">
        <span>${user.firstName} ${user.lastName}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
    </div> 
    <h5>
        <a href="SelectClass">Classes</a> /
        <a href="SelectExam?className=${param.className}">Exam</a> /
        <c:if test="${param.classExam!=null}">
            ${param.classExam}
        </c:if>
    </h5>
</div>
