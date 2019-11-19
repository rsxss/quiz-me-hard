package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ClassInfo_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <style>\n");
      out.write("        body,h1 {font-family: \"Raleway\", sans-serif}\n");
      out.write("        body, html {height: 100%}\n");
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
      out.write("    </style>\n");
      out.write("    <body>\n");
      out.write("        ");
      out.write("\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("className", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${classroom.classroomName}", java.lang.String.class, (PageContext)_jspx_page_context, null), request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("            <div class=\"w3-sidebar w3-light-grey w3-bar-block\" style=\"width:10%\">\n");
      out.write("                <h3 class=\"w3-bar-item\">Menu</h3>\n");
      out.write("                <a href=\"ClassInfo?className=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${classroom.classroomName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"w3-bar-item w3-button  w3-grey\">Class Info</a>\n");
      out.write("                <a href=\"SelectExam?className=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${classroom.classroomName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"w3-bar-item w3-button\">Exam</a>\n");
      out.write("                <div class=\" w3-display-bottomleft\" style=\"margin: 10px;margin-bottom: 50px\"><a href=\"SelectClass\">Back to Classes</a></div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"w3-container\" style=\"margin-left:10%\">\n");
      out.write("                <h1>Class Information</h1>\n");
      out.write("                <h4>Class Name</h4>\n");
      out.write("                ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${classroom.classroomName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                <h4>Description</h4>\n");
      out.write("                ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${classroom.classroomDescription}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                <h4>Teacher</h4>\n");
      out.write("                x\n");
      out.write("                <h4>Member</h4>\n");
      out.write("                <p>x</p>\n");
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
