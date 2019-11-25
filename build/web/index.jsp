<%-- 
    Document   : Homepage
    Created on : Nov 11, 2019, 10:12:38 PM
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
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Pridi&display=swap" rel="stylesheet">
        <style>
            body,h1 {font-family: 'Pridi', serif;}
            body, html {height: 100%}
            .bgimg {
                background-image: url('images/bg3.png');
                min-height: 100%;
                background-position: center;
                background-size: cover;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-md fixed-top" style="background-color: #202C46">
            <h2 style="color: #fffa4b">Quiz Me Hard</h2>
            <ul class="navbar-nav ml-md-auto">
                <li >
                    <a class="nav-link w3-large w3-text-white" href=""  data-toggle="modal" data-target="#myModal" style="margin-right: 20px">About</a>
                </li>
                <li>
                    <a class="w3-large w3-btn w3-border w3-round-large w3-white w3-text-grey w3-hover-yellow" href="Login.jsp">Login</a>
                </li>
            </ul>
        </nav>
        <div class=" w3-container w3-text-white bgimg">
            <div class="w3-display-middle w3-padding-large  w3-wide w3-text-light-grey w3-center">
                <div class=" w3-padding-large" style="margin-top: -5%">
                    <div class="w3-panel">
                        <div class="w3-large w3-animate-top"><h1>เว็บสำหรับในการฝึกเขียนและทดสอบโปรแกรมภาษา Python</h1></div>
                    </div>
                    <p class="w3-large w3-animate-top">สามารถที่จะแบ่งห้องเรียนสำหรับแต่ละวิชา สามารถตรวจข้อสอบและแสดงผลคะแนนได้ </p>
                    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Python-logo-notext.svg/1024px-Python-logo-notext.svg.png" width="200px" style="margin: 20px">
                </div>
            </div>
        </div>
        
        <div class="container" style="margin-top: 50px;margin-bottom: 200px">
            <center>
                <div class="w3-panel">
                    <div class="w3-large"><h1>Users</h1></div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <img src="https://i.pinimg.com/originals/7c/c7/a6/7cc7a630624d20f7797cb4c8e93c09c1.png" width="200px" style="margin: 20px">
                        <h2>Admin</h2>
                        <h4>ดูแลระบบ</h4>
                    </div>
                    <div class="col-sm-4">
                        <img src="https://deventerhuisgenoten.nl/storage/avatars/avatar-v.png" width="200px" style="margin: 20px">
                        <h2>Teacher</h2>
                        <h4>สร้างแบบทดสอบและให้คะแนน</h4>
                    </div>
                    <div class="col-sm-4">
                        <img src="https://inspireducation.org.pk/wp-content/uploads/2019/06/person-girl-flat.png" width="200px" style="margin: 20px">
                        <h2>Student</h2>
                        <h4>ทำแบบทดสอบ</h4>
                    </div>
                </div>
            </center>
        </div>
        <div class="w3-container w3-text-white" style="background-color: #202C46;padding: 10px">
            <center>Created by Quiz Me Hard developer</center>
        </div>

        <div class="modal" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header w3-xlarge">
                        <h4 class="modal-title">สมาชิก</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <!-- Modal body -->
                    <div class="modal-body w3-large">
                        <p>61130500013 นายจิรพัทธ์ เกลาเกลี้ยง</p>
                        <p>61130500032 นายณัฐวรพงษ์ ลอยไสว	</p>
                        <p>61130500050 นางสาวธมลวรรณ เปี่ยมหัสพล</p>
                        <br>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
