package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Exam_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <title>Quiz Me Hard</title>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Raleway\">\n");
      out.write("    <script src=\"lib/codemirror.js\"></script>\n");
      out.write("    <link rel=\"stylesheet\" href=\"lib/codemirror.css\">\n");
      out.write("    <script src=\"mode/javascript/javascript.js\"></script>\n");
      out.write("    <script src=\"mode/python/python.js\"></script>\n");
      out.write("    <link rel=\"stylesheet\" href=\"addon/hint/show-hint.css\">\n");
      out.write("    <script src=\"addon/hint/show-hint.js\"></script>\n");
      out.write("    <script src=\"addon/hint/css-hint.js\"></script>\n");
      out.write("    <style>\n");
      out.write("        body,h1 {font-family: \"Raleway\", sans-serif; }\n");
      out.write("        body, html {height: 100%;background-color: #eee }\n");
      out.write("        .bgimg {\n");
      out.write("            background-image: url('images/bg2.png');\n");
      out.write("            min-height: 100%;\n");
      out.write("            background-position: center;\n");
      out.write("            background-size: cover;\n");
      out.write("        }\n");
      out.write("        .box{\n");
      out.write("            border-radius: 25px;\n");
      out.write("            background-color: cadetblue   ;\n");
      out.write("            min-height: 80%;\n");
      out.write("            min-width:  80%;\n");
      out.write("            margin: 20px;\n");
      out.write("        }\n");
      out.write("        .inst{\n");
      out.write("            height:100%;\n");
      out.write("            width:25%;border:1px solid #ccc;\n");
      out.write("            overflow:auto;\n");
      out.write("            margin-left: 20 px;\n");
      out.write("        }\n");
      out.write("        #editor { \n");
      out.write("            position: absolute;\n");
      out.write("            top: 0;\n");
      out.write("            right: 0;\n");
      out.write("            bottom: 0;\n");
      out.write("            left: 0;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("    <body>\n");
      out.write("        ");
      out.write("\n");
      out.write("       ");
      out.write("\n");
      out.write("       ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("            <div class=\"w3-sidebar w3-yellow w3-bar-block\" style=\"width:25%\">\n");
      out.write("                <h3 class=\"w3-bar-item\">Instruction</h3>\n");
      out.write("                <p class=\"w3-bar-item\">\n");
      out.write("                    ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${classroomExam.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&NewLine;\n");
      out.write("                    ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${classroomExam.description}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                </p>\n");
      out.write("            </div>\n");
      out.write("            <div style=\"margin-left:25%\">\n");
      out.write("                <div class=\"w3-container\" >\n");
      out.write("                    <p></p>\n");
      out.write("                    <textarea id=\"codeeditor\" ></textarea>\n");
      out.write("                    <center><button  class=\"w3-button  w3-teal\" style=\"margin-top: 10px\">&blacktriangleright; Run</button></center>\n");
      out.write("                    Output:\n");
      out.write("                    <textarea readonly=\"\" style=\"width: 100%;height: 150px;resize: none\">Hello World</textarea>\n");
      out.write("                    <center><button  class=\"w3-button  w3-yellow\" style=\"margin-top: 10px\" onclick=\"checkSubmit()\">Submit Answer</button></center>\n");
      out.write("                </div>\n");
      out.write("                <script>\n");
      out.write("                    var editor = CodeMirror.fromTextArea(document.getElementById(\"codeeditor\"), {\n");
      out.write("                        mode: \"python\",\n");
      out.write("                        lineNumbers: true,\n");
      out.write("                        autofocus: true,\n");
      out.write("                        extraKeys: {\"Ctrl-Space\": \"autocomplete\"}\n");
      out.write("                    });\n");
      out.write("                    editor.setSize(\"100%\", 250)\n");
      out.write("                </script>\n");
      out.write("                <script>\n");
      out.write("                    function checkSubmit() {\n");
      out.write("                        if (confirm(\"Are you sure you want to submit?\")) {\n");
      out.write("                            alert(\"Your Answer has been submitted.\");\n");
      out.write("                            window.location.replace(\"SelectExam\");\n");
      out.write("                        }\n");
      out.write("                    }\n");
      out.write("                </script>\n");
      out.write("            </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
