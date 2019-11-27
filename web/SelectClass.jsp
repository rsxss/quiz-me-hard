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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <style>
            body,h1 {font-family: "Raleway", sans-serif}
            body, html {height: 100%}
            .bgimg {
                background: linear-gradient(0deg, rgba(48,86,166,1) 0%, rgba(41,53,140,1) 100%);
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
        <script>
            function checkDelete(className, uriString){
                if (confirm("Are you sure to delete "+className)){
                    window.location.href = uriString;
                }
            }
            </script>
    </head>
    <body>
        <%--<c:if test="${user.equals('admin')}">
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
        <c:if test="${user.student}">
            <div class="bgimg w3-display-container w3-text-white">
                <div class="w3-container w3-teal">
                    <h1>Quiz Me Hard</h1>
                    <div class="w3-display-topright w3-padding-large ">
                        <span><!--Student:-->${user.firstName} ${user.lastName}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
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
        </c:if> --%>
        <div class="bgimg w3-display-container w3-text-white">
            <div class="w3-container w3-text-white" style="background-color: #202C46;padding-bottom: 40px">
                <h1 style="color: #fffa4b">Quiz Me Hard</h1>
                <div class="w3-display-topright w3-padding-large ">
                    <div class="w3-large">${user.firstName} ${user.lastName}</div> 
                    <div align="right"><a class=" w3-btn w3-border w3-round-large w3-white w3-text-grey w3-hover-yellow" href="Logout">Logout</a></div>
                </div> 
            </div>

            <div class="w3-container">
                <span class="w3-xxxlarge" style="margin: 20px 0 0 20px">Classroom</span>
                <c:if test="${sessionScope.user.isAdmin}">
                    <a href="AddClass" class="w3-button  w3-teal w3-round-large" style="margin: 10px">+ Add Class</a>
                </c:if>
            </div>

            <div class="card-deck w3-text-black">
                <c:forEach items="${requestScope.classrooms}" var="classroom">
                    <div class="col-sm-3" style="margin-top: 20px">
                        <div class="card">
                            <center><img src="https://icon-library.net/images/icon-for-classroom/icon-for-classroom-28.jpg" width="200px"></center>
                            <div class="card-body">
                                <h3 class="card-text"><a href="ClassInfo?className=${classroom.classroomName}">
                                        ${classroom.classroomName}
                                    </a>
                                </h3>
                                <c:if test="${sessionScope.user.isAdmin}">
                                    <a href="AddClass.jsp" class="w3-button  w3-yellow w3-round" >Edit</a>
                                    <a href="javascript:checkDelete('${classroom.classroomName}', 'DeleteClass?classId=${classroom.id}');" class="w3-button  w3-red w3-display-topright w3-circle" style="margin: 10px">X</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>                    
            </div> 
        </div>
    </body>
</html>
