<%-- 
    Document   : AddExam
    Created on : Nov 13, 2019, 10:31:44 PM
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
                background: rgb(238,174,202);
                background: radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(148,187,233,1) 100%);
                min-height: 100%;
                background-position: center;
                background-size: cover;
            }
            .box{
                border-radius: 25px;
                background-color: cadetblue   ;
                min-height: 40%;
                min-width:  40%;
                margin-top: 50px;
            }
        </style>
    </head>
    <body>
        <div class="bgimg w3-display-container w3-text-white">
            <div class="w3-container w3-teal" >
                <h1>Quiz Me Hard</h1>
                <div class="w3-display-topright w3-padding-large ">
                    <a href="" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
                </div>
            </div>
            <div class="box w3-display-middle w3-padding-large" >
                <h5>Add Exam</h5>
                <form action=""  method="post">
                    <p>Class name : <input type="text" name="classname" class="w3-round-large"></p>
                    <p>Description :</p>
                    <p><textarea rows="5" cols="70" name="comment" form="usrform" placeholder="Enter here..." class="w3-round-large"></textarea>
                    <p>Test Case :</p>
                    <p><textarea rows="5" cols="70" name="comment" form="usrform" placeholder="Enter here..." class="w3-round-large"></textarea>
                    <div class="w3-right  w3-padding-large ">
                        <a href="SelectExam" class="w3-btn w3-teal w3-border w3-border-gray w3-round-xlarge" >Cancel</a> <input type="submit" value="submit" class="w3-btn w3-teal w3-border w3-border-gray w3-round-xlarge">
                    </div>
                </form>   
            </div>
        </div>
    </body>
</html>

