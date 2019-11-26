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
    <script src="lib/codemirror.js"></script>
    <link rel="stylesheet" href="lib/codemirror.css">
    <script src="mode/javascript/javascript.js"></script>
    <script src="mode/python/python.js"></script>
    <link rel="stylesheet" href="addon/hint/show-hint.css">
    <script src="addon/hint/show-hint.js"></script>
    <script src="addon/hint/css-hint.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <style>
        body,h1 {font-family: "Raleway", sans-serif; }
        body, html {height: 100%;background-color: #eee }
        .bgimg {
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
        .inst{
            height:100%;
            width:25%;border:1px solid #ccc;
            overflow:auto;
            margin-left: 20 px;
        }
        #editor { 
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
        }
        #output {
            width: 100%;
            height: 150px;
            resize: none;
            background-color: hsl(0, 0%, 100%);
            border: 1px solid hsl(0, 0%, 66%);
            flex-direction: column;
            font-size: 15px;
            line-height: 22.5px;
            padding: 2px;
            overflow-y: scroll;
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
                    <a href="SelectClass" >Classes</a> /
                    <a href="SelectExam" >Exam</a> /
                    <a href="ViewExam">Testing Cookies</a> /
                    001
                </h5>
            </div>
            <div class="w3-sidebar w3-yellow w3-bar-block" style="width:25%">
                <h3 class="w3-bar-item">Instruction</h3>
                <p class="w3-bar-item">As you can see, once there's enough text in this box, the box will grow scroll bars... that's why we call it a scroll box! You could also place an image into the scroll box.</p>
            </div>
            <div style="margin-left:25%">
                <div class="w3-container" >
                    <p></p>
                    <textarea id="code-editor" ></textarea>
                    <center><button  class="w3-button  w3-teal" style="margin-top: 10px">&blacktriangleright; Run</button></center>
                    Output:
                    <textarea readonly="" style="width: 100%;height: 150px;resize: none">Hello World</textarea>
                    <center><button  class="w3-button  w3-yellow" style="margin-top: 10px" onclick="checkSubmit()">Submit Answer</button></center>
                </div>
                <script>
                    var editor = CodeMirror.fromTextArea(document.getElementById("code-editor"), {
                        mode: "python",
                        lineNumbers: true,
                        autofocus: true,
                        extraKeys: {"Ctrl-Space": "autocomplete"}
                    });
                    editor.setSize("100%", 250)
                </script>
                <script>
                    function checkSubmit() {
                        if (confirm("Are you sure you want to submit?")) {
                            alert("Your Answer has been submitted.");
                            window.location.replace("SelectExam");
                        }
                    }
                </script>
            </div>
        </c:if>
        <c:if test="${user.equals('teacher')}">
            <div class="w3-container w3-teal">
                <h1>Quiz Me Hard</h1>
                <div class="w3-display-topright w3-padding-large ">
                    <span>Teacher:${user}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
                </div> 
                <h5>
                    <a href="SelectClass" >Classes</a> /
                    <a href="SelectExam" >Exam</a> /
                    <a href="ViewExam">Testing Cookies</a> /
                    001
                </h5>
            </div>
            <div class="w3-sidebar w3-yellow w3-bar-block" style="width:25%">
                <h3 class="w3-bar-item">Instruction</h3>
                <p class="w3-bar-item">As you can see, once there's enough text in this box, the box will grow scroll bars... that's why we call it a scroll box! You could also place an image into the scroll box.</p>
            </div>
            <div style="margin-left:25%">
                <div class="w3-container" >
                    <p></p>
                    <textarea id="code-editor" ></textarea>
                    <center><button  class="w3-button  w3-teal" style="margin-top: 10px">&blacktriangleright; Run</button></center>
                    Output:
                    <textarea readonly="" style="width: 100%;height: 150px;resize: none">Hello World</textarea>
                    <center><button  class="w3-button  w3-yellow" style="margin-top: 10px" onclick="checkSubmit()">Submit Answer</button></center>
                </div>
                <script>
                    var editor = CodeMirror.fromTextArea(document.getElementById("code-editor"), {
                        mode: "python",
                        lineNumbers: true,
                        autofocus: true,
                        extraKeys: {"Ctrl-Space": "autocomplete"}
                    });
                    editor.setSize("100%", 250)
                </script>
                <script>
                    function checkSubmit() {
                        if (confirm("Are you sure you want to submit?")) {
                            alert("Your Answer has been submitted.");
                            window.location.replace("SelectExam");
                        }
                    }
                </script>
            </div>
        </c:if>
        <c:if test="${user.equals('student')}">
            <div class="w3-container w3-teal">
                <h1>Quiz Me Hard</h1>
                <div class="w3-display-topright w3-padding-large ">
                    <span>Student:${user}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
                </div> 
                <h5>
                    <a href="SelectClass" >Classes</a> /
                    <a href="SelectExam" >Exam</a> /
                    Testing Cookies
                </h5>
            </div>
            <div class="w3-sidebar w3-yellow w3-bar-block" style="width:25%">
                <h3 class="w3-bar-item">Instruction</h3>
                <p class="w3-bar-item">As you can see, once there's enough text in this box, the box will grow scroll bars... that's why we call it a scroll box! You could also place an image into the scroll box.</p>
            </div>
            <div style="margin-left:25%">
                <div class="w3-container" >
                    <p></p>
                    <textarea id="code-editor" ></textarea>
                    <center><button  class="w3-button  w3-teal" style="margin-top: 10px">&blacktriangleright; Run</button></center>
                    Output:
                    <textarea readonly="" style="width: 100%;height: 150px;resize: none">Hello World</textarea>
                    <center><button  class="w3-button  w3-yellow" style="margin-top: 10px" onclick="checkSubmit()">Submit Answer</button></center>
                </div>
                <script>
                    var editor = CodeMirror.fromTextArea(document.getElementById("code-editor"), {
                        mode: "python",
                        lineNumbers: true,
                        autofocus: true,
                        extraKeys: {"Ctrl-Space": "autocomplete"}
                    });
                    editor.setSize("100%", 250)
                </script>
                <script>
                    function checkSubmit() {
                        if (confirm("Are you sure you want to submit?")) {
                            alert("Your Answer has been submitted.");
                            window.location.replace("SelectExam");
                        }
                    }
                </script>
            </div>
        </c:if>--%>
       <%-- <div class="w3-container w3-teal">
                <h1>Quiz Me Hard</h1>
                <div class="w3-display-topright w3-padding-large ">
                    <span>${user.firstName} ${user.lastName}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
                </div> 
                <h5>
                    <a href="SelectClass" >Classes</a> /
                    <a href="SelectExam" >Exam</a> /
                    Testing Cookies
                </h5>
        </div>--%>
            <jsp:include page="header.jsp">
                <jsp:param name="className" value="${classroomName}"/>
                <jsp:param name="classExam" value="${classroomExam.name}"/>
            </jsp:include>
            <div class="w3-sidebar w3-bar-block" style="width:25%;background-color: #fffa4b">
                <h3 class="w3-bar-item">Instruction</h3>
                <p class="w3-bar-item">
                    ${classroomExam.description}
                </p>
            </div>
            <div style="margin-left:25%">
                <div class="w3-container" >
                    <p></p>
                    <textarea id="code-editor" ></textarea>
                    <center><button onclick="sendExecData()" class="w3-button  w3-teal w3-border" style="margin-top: 10px">&blacktriangleright; Run</button></center>
                    Output:
                    <!--<textarea id="output" readonly style="width: 100%;height: 150px;resize: none">Hello World</textarea>-->
                    <div id="output" ></div>
                    <c:if test="${!sessionScope.user.isAdmin}">
                        <center><button  class="w3-button  w3-yellow w3-border" style="margin-top: 10px" onclick="checkSubmit()">Submit Answer</button></center>
                    </c:if>
                </div>
                <script>
                    var editor = CodeMirror.fromTextArea(document.getElementById("code-editor"), {
                        mode: "python",
                        lineNumbers: true,
                        autofocus: true,
                        extraKeys: {"Ctrl-Space": "autocomplete"}
                    });
                    editor.setSize("100%", 250);
                </script>
                <script>
                    function appendOutput(output, mode){
                        if (mode==='success'){
                            for (let k in output){
                                if (output.hasOwnProperty(k)){
                                    let newOut='';
                                    newOut += k+': ';
                                    newOut += output[k].result ? 'Passed':'Failed';
                                    newOut += '\n';
                                    let para = document.createElement("span");
                                    para.style.color = output[k].result ? 'green' : 'red';
                                    let node = document.createTextNode(newOut);
                                    para.appendChild(node);
                                    para.appendChild(document.createElement("br"));
                                    document.getElementById("output").appendChild(para);
                                }
                            }
                        } else if (mode==='error'){
                            let para = document.createElement("span");
                            let node = document.createTextNode(output);
                            para.appendChild(node);
                            document.getElementById("output").appendChild(para);
                        };
//                        return newOut;
                    }
                    
                    function sendExecData(){
                        $.post(
                                "http://localhost:8080/quiz-me-hard/Exam",
                                {
                                    requestedBy: '${user.username}',
                                    execData: JSON.stringify({
                                        code: editor.getValue(),
                                        lang: 'python'
                                    })
                                }, function(data, status){
                                    if (status==='success'){
                                        console.log(data);
                                        let mode = null;
                                        document.getElementById("output").innerHTML = '';
                                        if (data.status !== 200){
                                            mode = 'error';
                                            appendOutput(data.error, mode);
                                        } else {
                                            mode = 'success';
                                            appendOutput(data.results.case_results, mode);
                                        };
                                        document.getElementById("output")
                                                .style.color = mode === 'error' ? 'red' : 'black';
                                    }
                        });
                    };
                    
                    function checkSubmit() {
                        if (confirm("Are you sure you want to submit?")) {
                            alert("Your Answer has been submitted.");
                            sendExecData();
//                            const formData = new FormData();
//                            formData.append('code', JSON.stringify(editor.getValue()));
//                            formData.append('lang', 'python');
//                            fetchOptions = {
//                                method: 'POST',
////                                headers: {
////                                    'Content-Type': //'application/json;charset=utf-8'
////                                }, 
//                                body: formData
//                            };
//                            fetch("http://localhost:8080/quiz-me-hard/Exam", fetchOptions)
//                                    .then(response => response.json())
//                                    .catch(rejected => console.log("rejected:", rejected))
//                                    .then(json => console.log("json",json));
                            //window.location.replace("SelectExam?className=${classroomName}");
                        }
                    };
                </script>
            </div>
    </body>
</html>
