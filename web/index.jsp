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
        <style>
            body,h1 {font-family: "Raleway", sans-serif}
            body, html {height: 100%}
            .bgimg {
                background-image: url('images/bg4.jpg');
                min-height: 100%;
                background-position: center;
                background-size: cover;
            }
        </style>
    </head>
    <body>

        <div class="bgimg w3-display-container w3-text-white ">
            <div class="w3-display-middle w3-padding-large  w3-wide w3-text-light-grey w3-center">
                <div class=" w3-padding-large" style="margin-top: -15%">
                    <h1 class="w3-jumbo w3-animate-top">- Quiz Me Hard -</h1>
                    <hr class="w3-border-indigo w3-animate-top" style="margin:auto;width:40%">
                    <div class="w3-panel">
                        <span style="font-size:50px;line-height:2.5em;opacity:0.5">❝</span>
                        <p class="w3-large w3-animate-top" style="margin-top:-40px"><i>เว็บสำหรับในการฝึกเขียนและทดสอบโปรแกรมภาษา Python</i></p>
                    </div>
                    <p class="w3-large w3-animate-top">สามารถที่จะแบ่งห้องเรียนสำหรับแต่ละวิชา สามารถตรวจข้อสอบและแสดงผลคะแนนได้ </p>
                 </div>
            </div>         
                <a class="w3-display-middle w3-padding-large w3-xlarge w3-btn w3-border w3-round-xlarge w3-sand w3-text-grey" style="margin-top: 13%" href="Login.jsp">Login</a>
        </div>
    </body>
</html>
