<%-- 
    Document   : Login.jsp
    Created on : Nov 5, 2019, 7:47:47 PM
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
            background-image: url('images/bg1.jpg');
            min-height: 100%;
            background-position: center;
            background-size: cover;
        }
    </style>
    <body>
        <div class="bgimg w3-display-container w3-animate-opacity w3-text-white ">
            <div class="w3-large w3-display-topleft w3-padding-large w3-text-dark-gray " style="margin-right: 8%">
                <a href="index.jsp">Home Page</a>
            </div>
            <div class="w3-display-middle  w3-wide w3-text-light-grey w3-center ">
                <div class="w3-border w3-animate-top w3-padding-large ">
                    <div class="w3-light-grey  w3-padding-large w3-animate-top">
                        <h1 class="w3-jumbo w3-animate-top ">Login</h1>
                        <hr class="w3-border-grey w3-animate-top" style="margin:auto;width:40%">
                        <form action="Login" method="post" class="w3-large w3-center w3-animate-top ">
                            <p>Username : <input type="text"  name="username" class="w3-round-large" required/><br>
                            <p>Password : <input type="password"  name="password" class="w3-round-large" required/><br>                    
                                <br><label><input type="submit" value="Login"  class="w3-btn w3-teal w3-border w3-border-white w3-round-xlarge "></label>
                        </form>
                        <div class="w3-container w3-green ">
                            ${message}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
