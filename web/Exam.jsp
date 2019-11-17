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

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script> 
    <script src="http://www.skulpt.org/js/skulpt.min.js" type="text/javascript"></script> 
    <script src="http://www.skulpt.org/js/skulpt-stdlib.js" type="text/javascript"></script>
    <script>
        function insertTab(o, e)
        {
            var kC = e.keyCode ? e.keyCode : e.charCode ? e.charCode : e.which;
            if (kC == 9 && !e.shiftKey && !e.ctrlKey && !e.altKey)
            {
                var oS = o.scrollTop;
                if (o.setSelectionRange)
                {
                    var sS = o.selectionStart;
                    var sE = o.selectionEnd;
                    o.value = o.value.substring(0, sS) + "\t" + o.value.substr(sE);
                    o.setSelectionRange(sS + 1, sS + 1);
                    o.focus();
                } else if (o.createTextRange)
                {
                    document.selection.createRange().text = "\t";
                    e.returnValue = false;
                }
                o.scrollTop = oS;
                if (e.preventDefault)
                {
                    e.preventDefault();
                }
                return false;
            }
            return true;
        }
    </script>
    <script type="text/javascript">
        // output functions are configurable.  This one just appends some text
        // to a pre element.
        function outf(text) {
            var mypre = document.getElementById("output");
            mypre.innerHTML = mypre.innerHTML + text;
        }
        function builtinRead(x) {
            if (Sk.builtinFiles === undefined || Sk.builtinFiles["files"][x] === undefined)
                throw "File not found: '" + x + "'";
            return Sk.builtinFiles["files"][x];
        }

        // Here's everything you need to run a python program in skulpt
        // grab the code from your textarea
        // get a reference to your pre element for output
        // configure the output function
        // call Sk.importMainWithBody()
        function runit() {
            var prog = document.getElementById("yourcode").value;
            var mypre = document.getElementById("output");
            mypre.innerHTML = '';
            Sk.pre = "output";
            Sk.configure({output: outf, read: builtinRead});
            (Sk.TurtleGraphics || (Sk.TurtleGraphics = {})).target = 'mycanvas';
            var myPromise = Sk.misceval.asyncToPromise(function () {
                return Sk.importMainWithBody("<stdin>", false, prog, true);
            });
            myPromise.then(function (mod) {
                console.log('success');
            },
                    function (err) {
                        console.log(err.toString());
                    });
        }
    </script> 
    <style>
        body,h1 {font-family: "Raleway", sans-serif; }
        body, html {height: 100%;background-color: #eee }
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
    </style>
    <body>
        <c:if test="${user.getRole().equals('teacher')}">
            <div class="w3-container w3-teal">
                <h1>Quiz Me Hard</h1>
                <div class="w3-display-topright w3-padding-large ">
                    <span>Teacher:${user.getFullname()}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
                </div> 
                <h5>
                    <a href="SelectExam" >Exam</a> /
                    <a href="ViewExam?id=${exam.getEid()}">${exam.getName()}</a> /
                    ${student.getFullname()}
                </h5>
            </div>
            <div class="w3-sidebar w3-yellow w3-bar-block" style="width:25%">
                <h3 class="w3-bar-item">Instruction</h3>
                <p class="w3-bar-item">${exam.getDescription()}</p>
            </div>
            <div style="margin-left:25%">
                <div class="w3-container" >
                    <p></p>
                    <textarea id="yourcode" style="width: 100%;height: 200px;resize: none;font-family: 'consolas'" onkeydown="insertTab(this, event);">print(5)</textarea>
                    <center><button  class="w3-button  w3-teal" style="margin-top: 10px" onclick="runit()">&blacktriangleright; Run</button></center>
                    Output:
                    <textarea readonly="" style="width: 100%;height: 150px;resize: none;font-family: 'consolas'"  id="output"></textarea>
                    <div id="mycanvas"></div> 
                    <center><button  class="w3-button  w3-yellow" style="margin-top: 10px" onclick="checkSubmit()">Submit Answer</button></center>
                </div>
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
        <c:if test="${user.getRole().equals('student')}">
            <div class="w3-container w3-teal">
                <h1>Quiz Me Hard</h1>
                <div class="w3-display-topright w3-padding-large ">
                    <span>Student:${user.getFullname()}</span> <a href="Logout" class="w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white">Logout</a>
                </div> 
                <h5>
                    <a href="SelectExam" >Exam</a> /
                    ${exam.getName()}
                </h5>
            </div>
            <div class="w3-sidebar w3-yellow w3-bar-block" style="width:25%">
                <h3 class="w3-bar-item">Instruction</h3>
                <p class="w3-bar-item">${exam.getDescription()}</p>
            </div>
            <div style="margin-left:25%">
                <div class="w3-container" >
                    <p></p>
                    <textarea id="yourcode" style="width: 100%;height: 200px;resize: none ;font-family: 'consolas'" onkeydown="insertTab(this, event);">print(5)</textarea>
                    <center><button  class="w3-button  w3-teal" style="margin-top: 10px" onclick="runit()">&blacktriangleright; Run</button></center>
                    Output:
                    <textarea readonly="" style="width: 100%;height: 150px;resize: none;font-family: 'consolas' "  id="output"></textarea>
                    <div id="mycanvas"></div> 
                    <center><button  class="w3-button  w3-yellow" style="margin-top: 10px" onclick="checkSubmit()">Submit Answer</button></center>
                </div>
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
    </body>
</html>
