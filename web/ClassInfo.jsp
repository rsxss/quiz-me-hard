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
        <%--<c:if test="${user.equals('admin')}">
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
                <a href="ClassInfo" class="w3-bar-item w3-button  w3-grey">Class Info</a>
                <a href="SelectExam" class="w3-bar-item w3-button">Exam</a>
                <div class=" w3-display-bottomleft" style="margin: 10px;margin-bottom: 50px"><a href="SelectClass">Back to Classes</a></div>
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
                <a href="ClassInfo" class="w3-bar-item w3-button  w3-grey">Class Info</a>
                <a href="SelectExam" class="w3-bar-item w3-button">Exam</a>
                <div class=" w3-display-bottomleft" style="margin: 10px;margin-bottom: 50px"><a href="SelectClass">Back to Classes</a></div>
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
        <c:if test="${user.equals('student')}">
            <div class="w3-container w3-teal">
                <h1>Quiz Me Hard</h1>
                <div class="w3-display-topright w3-padding-large ">
                    <span>Student:${user}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
                </div>
                <h5>
                    <a href="SelectClass">Classes</a> /
                    Exam
                </h5>
            </div>
            <div class="w3-sidebar w3-light-grey w3-bar-block" style="width:10%">
                <h3 class="w3-bar-item">Menu</h3>
                <a href="ClassInfo" class="w3-bar-item w3-button  w3-grey">Class Info</a>
                <a href="SelectExam" class="w3-bar-item w3-button">Exam</a>
                <div class=" w3-display-bottomleft" style="margin: 10px;margin-bottom: 50px"><a href="SelectClass">Back to Classes</a></div>
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
        </c:if>--%>
            <jsp:include page="header.jsp">
                <jsp:param name="className" value="${classroom.classroomName}"/>
            </jsp:include>
            <div class="w3-sidebar w3-light-grey w3-bar-block" style="width:10%">
                <h3 class="w3-bar-item">Menu</h3>
                <a href="ClassInfo?className=${classroom.classroomName}" class="w3-bar-item w3-button  w3-grey">Class Info</a>
                <a href="SelectExam?className=${classroom.classroomName}" class="w3-bar-item w3-button">Exam</a>
                <div class=" w3-display-bottomleft" style="margin: 10px;margin-bottom: 50px"><a href="SelectClass">Back to Classes</a></div>
            </div>
            <div class="w3-container" style="margin-left:10%">
                <h1 >Class Information</h1>
                <h4>Class Name</h4>
                ${classroom.classroomName}
                <h4>Description</h4>
                ${classroom.classroomDescription}
                <h4>Teacher</h4>
                <c:forEach items="${classroom.classroomMemberCollection}" var="classroomMember" varStatus="vs">
                    <c:forEach items="${classroomMember.classroomTeacherCollection}" var="classroomTeacher" varStatus="vs">
                        <c:if test="${classroomTeacher.classroomId.classroomName.equals(classroom.classroomName)}">
                            ${classroomMember.userId.firstName} ${classroomMember.userId.lastName}<br/>
                        </c:if>
                    </c:forEach>
                </c:forEach>
                <h4>Member</h4>
                <c:forEach items="${classroom.classroomMemberCollection}" var="classroomMember" varStatus="vs">
                    <c:out value="${classroomMember.userId.firstName} ${classroomMember.userId.lastName}"/><br/>
                </c:forEach>
            </div>
    </body>
</html>
