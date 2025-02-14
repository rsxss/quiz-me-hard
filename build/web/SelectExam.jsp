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
        <%--
        <c:if test="${user.equals('admin')}">
            <div class="w3-container w3-teal">
                <h1>Quiz Me Hard</h1>
                <div class="w3-display-topright w3-padding-large ">
                    <span>Admin:${user}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
                </div>
                <h5>
                    <a href="SelectClass">Classes</a> /
                    Exam
                </h5>
            </div>
            <div class="w3-sidebar w3-light-grey w3-bar-block" style="width:10%">
                <h3 class="w3-bar-item">Menu</h3>
                <a href="ClassInfo" class="w3-bar-item w3-button">Class Info</a>
                <a href="SelectExam" class="w3-bar-item w3-button w3-grey">Exam</a>
                <div class=" w3-display-bottomleft" style="margin: 10px;margin-bottom: 50px"><a href="SelectClass">Back to Classes</a></div>
            </div>
            <div class="w3-container" style="margin-left:10%">
                <h1>Exam</h1>
                <a href="AddExam" class="w3-button  w3-black  w3-round-xlarge" style="margin: 10px">+ Add Exam</a>
                <table class="w3-table w3-striped">
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Check</th>
                        <th><input type="button" value="Delete"></th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>Testing Cookies</td>
                        <td><a href="ViewExam" class="w3-button w3-teal" >View</a></td>
                        <td><input type="checkbox"  class="w3-check"></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Testing Cake</td>
                        <td><a href="ViewExam" class="w3-button w3-teal" >View</a></td>
                        <td><input type="checkbox" class="w3-check"></td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Testing Coco</td>
                        <td><a href="ViewExam" class="w3-button w3-teal" >View</a></td>
                        <td><input type="checkbox" class="w3-check"></td>
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
                    <a href="SelectClass">Classes</a> /
                    Exam
                </h5>
            </div>
            <div class="w3-sidebar w3-light-grey w3-bar-block" style="width:10%">
                <h3 class="w3-bar-item">Menu</h3>
                <a href="ClassInfo" class="w3-bar-item w3-button">Class Info</a>
                <a href="SelectExam" class="w3-bar-item w3-button w3-grey">Exam</a>
                <div class=" w3-display-bottomleft" style="margin: 10px;margin-bottom: 50px"><a href="SelectClass">Back to Classes</a></div>
            </div>
            <div class="w3-container" style="margin-left:10%">
                <h1>Exam</h1>
                <a href="AddExam" class="w3-button  w3-black  w3-round-xlarge" style="margin: 10px">+ Add Exam</a>
                <table class="w3-table w3-striped">
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Check</th>
                        <th><input type="button" value="Delete"></th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>Testing Cookies</td>
                        <td><a href="ViewExam" class="w3-button w3-teal" >View</a></td>
                        <td><input type="checkbox"  class="w3-check"></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Testing Cake</td>
                        <td><a href="ViewExam" class="w3-button w3-teal" >View</a></td>
                        <td><input type="checkbox"  class="w3-check"></td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Testing Coco</td>
                        <td><a href="ViewExam" class="w3-button w3-teal" >View</a></td>
                        <td><input type="checkbox"  class="w3-check"></td>
                    </tr>
                </table>
            </div>
        </c:if>--%>
            <jsp:include page="header.jsp">
                <jsp:param name="className" value="${classroom.classroomName}"/>
            </jsp:include>
            <div class="w3-sidebar w3-light-grey w3-bar-block" style="width:10%">
                <h3 class="w3-bar-item">Menu</h3>
                <a href="ClassInfo?className=${classroom.classroomName}" class="w3-bar-item w3-button">Class Info</a>
                <a href="SelectExam?className=${classroom.classroomName}" class="w3-bar-item w3-button w3-grey">Exam</a>
                <div class=" w3-display-bottomleft" style="margin: 10px;margin-bottom: 50px"><a href="SelectClass">Back to Classes</a></div>
            </div>
            <div class="w3-container" style="margin-left:10%">
                <h1>Exam</h1>
                <c:if test="${sessionScope.user.isAdmin}">
                    <a href="AddExam?className=${classroom.classroomName}" class="w3-button  w3-black  w3-round-xlarge" style="margin: 10px">+ Add Exam</a>
                </c:if>
                <form action="DeleteExam" method="POST">
                    <table class="w3-table w3-striped">
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <c:choose>
                                <c:when test="${sessionScope.user.isAdmin}">
                                    <th>Check</th>
                                    <th><input type="submit" value="Delete"></th>                              
                                </c:when>
                                <c:otherwise>
                                    <th>Score (100)</th>
                                    <th></th>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                        <c:forEach items="${classroom.classroomExamCollection}" var="classroomExam" varStatus="vs">
                            <tr>
                                <td>${vs.count}</td>
                                <td>${classroomExam.name}</td>
                                <c:choose>
                                    <c:when test="${sessionScope.user.isAdmin}">
                                        <td>
                                            <a href="Exam?className=${classroom.classroomName}&examId=${classroomExam.id}" 
                                               class="w3-button w3-teal">
                                                View
                                            </a>
                                        </td>
                                        <td><input type="checkbox" name="${classroomExam.id}" class="w3-check"></td>                            
                                    </c:when>
                                    <c:otherwise>
                                        <td></td>
                                        <td>
                                            <a href="Exam?className=${classroom.classroomName}&examId=${classroomExam.id}" 
                                               class="w3-button w3-teal">
                                               Start
                                            </a>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </div>
    </body>
</html>
