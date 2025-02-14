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
    <c:if test="${user.equals('admin')}">
        <div class="w3-container w3-teal">
            <h1>Quiz Me Hard</h1>
            <div class="w3-display-topright w3-padding-large ">
                <span>Admin:${user}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
            </div>
            <h5>
                <a href="SelectClass" >Classes</a> /
                <a href="SelectExam" >Exam</a> /
                Testing Cookies
            </h5>
        </div>
        <div class="w3-sidebar w3-light-grey w3-bar-block" style="width:10%">
            <h3 class="w3-bar-item">Menu</h3>
            <a href="ClassInfo" class="w3-bar-item w3-button">Class Info</a>
            <a href="SelectExam" class="w3-bar-item w3-button w3-grey">Exam</a>
            <div class=" w3-display-bottomleft" style="margin: 10px;margin-bottom: 50px"><a href="SelectClass">Back to Classes</a></div>
        </div>
        <div class="w3-container" style="margin-left:10%">
            <h1>Testing Cookies</h1>
            <table class="w3-table w3-striped">
                <tr>
                    <th>StudentID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Score</th>
                    <th></th>
                </tr>
                <tr>
                    <td>001</td>
                    <td>นายฮายาโมโตะ</td>
                    <td>เกนชิโร่</td>
                    <td>50</td>
                    <td><a href="Exam" class="w3-button w3-teal" >Check</a></td>
                </tr>
                <tr>
                    <td>002</td>
                    <td>นางสาวยุย</td>
                    <td>ฮาจิบานะ</td>
                    <td>90</td>
                    <td><a href="Exam" class="w3-button w3-teal" >Check</a></td>
                </tr>
            </table>
        </div>
    </c:if>

    <c:if test="${user.equals('teacher')}">
        <div class="w3-container w3-teal">
            <h1>Quiz Me Hard</h1>
            <div class="w3-display-topright w3-padding-large ">
                <span>Teacher:${user}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
            </div>
            <h5>
                <a href="SelectClass" >Classes</a> /
                <a href="SelectExam" >Exam</a> /
                Testing Cookies
            </h5>
        </div>
        <div class="w3-sidebar w3-light-grey w3-bar-block" style="width:10%">
            <h3 class="w3-bar-item">Menu</h3>
            <a href="ClassInfo" class="w3-bar-item w3-button">Class Info</a>
            <a href="SelectExam" class="w3-bar-item w3-button w3-grey">Exam</a>
            <div class=" w3-display-bottomleft" style="margin: 10px;margin-bottom: 50px"><a href="SelectClass">Back to Classes</a></div>
        </div>
        <div class="w3-container" style="margin-left:10%">
            <h1>Testing Cookies</h1>
            <table class="w3-table w3-striped">
                <tr>
                    <th>StudentID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Score</th>
                    <th></th>
                </tr>
                <tr>
                    <td>001</td>
                    <td>นายฮายาโมโตะ</td>
                    <td>เกนชิโร่</td>
                    <td>50</td>
                    <td><a href="Exam" class="w3-button w3-teal" >Check</a></td>
                </tr>
                <tr>
                    <td>002</td>
                    <td>นางสาวยุย</td>
                    <td>ฮาจิบานะ</td>
                    <td>90</td>
                    <td><a href="Exam" class="w3-button w3-teal" >Check</a></td>
                </tr>
            </table>
        </div>
    </c:if>
</body>
</html>
