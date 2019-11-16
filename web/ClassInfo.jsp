<%-- 
    Document   : SelectClass
    Created on : Nov 10, 2019, 8:29:05 PM
    Author     : Yokymoth
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <title>Quiz Me Hard</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <style>
        body,h1 {font-family: "Raleway", sans-serif}
        body, html {height: 100%}
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
    </style>
    <body>
        <c:if test="${user.getRole().equals('teacher')}">
            <div class="w3-container w3-teal">
                <h1>Quiz Me Hard</h1>
                <div class="w3-display-topright w3-padding-large ">
                    <span>Teacher:${user.getFullname()}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
                </div>
                <h5>
                    <p> </p>
                </h5>
            </div>
            <div class="w3-sidebar w3-light-grey w3-bar-block" style="width:10%">
                <h3 class="w3-bar-item">Menu</h3>
                <a href="ClassInfo" class="w3-bar-item w3-button  w3-grey">Class Info</a>
                <a href="SelectExam" class="w3-bar-item w3-button">Exam</a>
            </div>
            <div class="w3-container" style="margin-left:10%">
                <h1>Class Information</h1>
                <h4>Class Name</h4>
                x
                <h4>Description</h4>
                x
                <h4>Teacher</h4>
                x
                <h4>Member</h4>
                <p>x</p>
            </div>
        </c:if>
        <c:if test="${user.getRole().equals('student')}">
            <div class="w3-container w3-teal">
                <h1>Quiz Me Hard</h1>
                <div class="w3-display-topright w3-padding-large ">
                    <span>Student:${user.getFullname()}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
                </div>
                <h5>
                    <p></p>
                </h5>
            </div>
            <div class="w3-sidebar w3-light-grey w3-bar-block" style="width:10%">
                <h3 class="w3-bar-item">Menu</h3>
                <a href="ClassInfo" class="w3-bar-item w3-button  w3-grey">Class Info</a>
                <a href="SelectExam" class="w3-bar-item w3-button">Exam</a>
            </div>
            <div class="w3-container" style="margin-left:10%">
                <h1>Class Information</h1>
                <h4>Class Name</h4>
                x
                <h4>Description</h4>
                x
                <h4>Teacher</h4>
                x
                <h4>Member</h4>
                <p>x</p>
            </div>
        </c:if>
    </body>
</html>
