<%-- 
    Document   : AddClass
    Created on : Nov 11, 2019, 9:09:15 PM
    Author     : Yokymoth
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Me Hard</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <style>
            body,h1 {font-family: "Raleway", sans-serif}
            body, html {height: 100%}
            .bgimg {
                background-image: url('images/bg3.png');
                min-height: 100%;
                background-position: center;
                background-size: cover;
            }
            .box{
                border-radius: 25px;
                background-color: cadetblue   ;
                min-height: 40%;
                min-width:  40%;
            }
        </style>
    </head>
    <body>
        <div class="bgimg w3-display-container w3-text-white">
            <div class="w3-container w3-teal ">
                <h1>Quiz Me Hard</h1>
                <div class="w3-display-topright w3-padding-large ">
                    <span>${user.firstName} ${user.lastName}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
                </div>
            </div>
            <div class="box w3-display-middle w3-padding-large" >
                <h5>Add Class</h5>
                <form action="AddClass" method="post">
                    <p>Class name : <input type="text" name="className" class="w3-round-large"></p>
                    <p>Class details :</p>
                    <p><textarea rows="4" cols="50" name="classDescription" placeholder="Enter here..." class="w3-round-large"></textarea>
                    <p>Teacher name : </p>
                    <p></p>
                    <c:forEach items="${requestScope.users}" var="user" varStatus="vs">
                        <c:set var="userName" value="${user.firstName} ${user.lastName}"/>
                        <input type="radio" name="teacher" value="${user.id}"/><c:out value="${pageScope.userName}"/><br/>
                    </c:forEach>
                    <div class="w3-display-bottomright w3-padding-large ">
                        <a href="SelectClass" class="w3-btn w3-teal w3-border w3-border-gray w3-round-xlarge" >Cancel</a> <input type="submit" value="submit" class="w3-btn w3-teal w3-border w3-border-gray w3-round-xlarge">
                    </div>
                </form>   
            </div>
        </div>
    </body>
</html>
