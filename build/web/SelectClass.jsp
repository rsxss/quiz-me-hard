<%-- 
    Document   : SelectClass
    Created on : Nov 10, 2019, 8:29:05 PM
    Author     : Yokymoth
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
    </head>
    <body>
        <c:out value="Welcome: ${sessionScope.user.firstName} ${sessionScope.user.lastName}"/>
        <c:if test="${user.equals('admin')}">
            <div class="bgimg w3-display-container w3-text-white ">
                <div class="w3-container w3-teal">
                    <h1>Quiz Me Hard</h1>
                    <div class="w3-display-topright w3-padding-large ">
                        <span>Admin:${user}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
                    </div>
                </div>
                <div>
                    <div class="box w3-display-middle w3-padding-large ">
                        <h2 class="w3-wide">Classroom</h2>
                        <a href="AddClass" class="w3-button  w3-black w3-display-topright w3-round-xlarge" style="margin: 10px">+ Add Class</a>
                        <table class="w3-table">
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>Check</th>
                                <th><input type="button" value="Delete"></th>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>Python 1</td>
                                <td><a href="ClassInfo" class="w3-button w3-teal" >View Class</a></td>
                                <td><input type="checkbox" class="w3-check"></td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>Python 2</td>
                                <td><a href="ClassInfo" class="w3-button w3-teal" >View Class</a></td>
                                <td><input type="checkbox" class="w3-check"></td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>Python 3</td>
                                <td><a href="ClassInfo" class="w3-button w3-teal" >View Class</a></td>
                                <td><input type="checkbox" class="w3-check"></td>
                            </tr>
                        </table>
                        <br>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${user.equals('teacher')}">
            <div class="bgimg w3-display-container w3-text-white">
                <div class="w3-container w3-teal">
                    <h1>Quiz Me Hard</h1>
                    <div class="w3-display-topright w3-padding-large ">
                        <span>Teacher:${user}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
                    </div>
                </div>
                <div>
                    <div class="box w3-display-middle w3-padding-large ">
                        <h2 class="w3-wide">Classroom</h2>
                        <ul class="w3-ul w3-border">
                            <li class="w3-hover-white"><a href="ClassInfo">Python 1 : ${teachername}</a></li>
                            <li class="w3-hover-white"><a href="#">Python 2 : ${teachername}</a></li>
                            <li class="w3-hover-white"><a href="#">Python 3 : ${teachername}</a></li>
                        </ul>
                        <br>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${user.equals('student')}">
            <div class="bgimg w3-display-container w3-text-white">
                <div class="w3-container w3-teal">
                    <h1>Quiz Me Hard</h1>
                    <div class="w3-display-topright w3-padding-large ">
                        <span>Student:${user}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
                    </div>
                </div>
                <div>
                    <div class="box w3-display-middle w3-padding-large ">
                        <h2 class="w3-wide">Classroom</h2>
                        <ul class="w3-ul w3-border">
                            <li class="w3-hover-white"><a href="ClassInfo">Python 1 : ${teachername}</a></li>
                            <li class="w3-hover-white"><a href="#">Python 2 : ${teachername}</a></li>
                            <li class="w3-hover-white"><a href="#">Python 3 : ${teachername}</a></li>
                        </ul>
                        <br>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
</html>
