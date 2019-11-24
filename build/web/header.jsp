<%-- 
    Document   : header
    Created on : Nov 18, 2019, 8:33:18 PM
    Author     : NATWORPONGLOYSWAI
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="w3-container w3-text-white" style="background: linear-gradient(180deg, rgba(48,86,166,1) 0%, rgba(41,53,140,1) 100%);">
    <h1 style="color: #fffa4b">Quiz Me Hard</h1>
    <div class="w3-display-topright w3-padding-large ">
        <div class="w3-large">${user.firstName} ${user.lastName}</div> 
        <div align="right"><a class=" w3-btn w3-border w3-round-large w3-white w3-text-grey w3-hover-yellow" href="Logout">Logout</a></div>
    </div> 
    <h5>
        <a href="SelectClass">Classes</a> /
        <a href="SelectExam?className=${param.className}">Exam</a> /
        <c:if test="${param.classExam!=null}">
            ${param.classExam}
        </c:if>
    </h5>
</div>
