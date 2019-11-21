package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class AddExam_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Quiz Me Hard</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Raleway\">\n");
      out.write("        <style>\n");
      out.write("            body,h1 {font-family: \"Raleway\", sans-serif}\n");
      out.write("            body, html {height: 100%}\n");
      out.write("            .bgimg {\n");
      out.write("                background: rgb(238,174,202);\n");
      out.write("                background: radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(148,187,233,1) 100%);\n");
      out.write("                min-height: 100%;\n");
      out.write("                background-position: center;\n");
      out.write("                background-size: cover;\n");
      out.write("            }\n");
      out.write("            .box{\n");
      out.write("                border-radius: 25px;\n");
      out.write("                background-color: cadetblue   ;\n");
      out.write("                min-height: 40%;\n");
      out.write("                min-width:  40%;\n");
      out.write("                margin-top: 50px;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"bgimg w3-display-container w3-text-white\">\n");
      out.write("            <div class=\"w3-container w3-teal\" >\n");
      out.write("                <h1>Quiz Me Hard</h1>\n");
      out.write("                <div class=\"w3-display-topright w3-padding-large \">\n");
      out.write("                    <a href=\"\" class=\"w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white\">Logout</a>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"box w3-display-middle w3-padding-large\" >\n");
      out.write("                <h5>Add Exam</h5>\n");
      out.write("                <form action=\"\"  method=\"post\">\n");
      out.write("                    <p>Title : <input type=\"text\" name=\"examName\" class=\"w3-round-large\"></p>\n");
      out.write("                    <p>Description :</p>\n");
      out.write("                    <p><textarea rows=\"5\" cols=\"70\" name=\"examDescription\" placeholder=\"Enter here...\" class=\"w3-round-large\"></textarea>\n");
      out.write("                    <p>Test Case :</p>\n");
      out.write("                    <p><textarea rows=\"5\" cols=\"70\" name=\"examTestCase\" placeholder=\"Enter here...\" class=\"w3-round-large\"></textarea>\n");
      out.write("                    <input type=\"hidden\" name=\"className\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${className}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/>\n");
      out.write("                    <div class=\"w3-right  w3-padding-large \">\n");
      out.write("                        <a href=\"SelectExam?className=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${className}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"w3-btn w3-teal w3-border w3-border-gray w3-round-xlarge\" >Cancel</a> <input type=\"submit\" value=\"submit\" class=\"w3-btn w3-teal w3-border w3-border-gray w3-round-xlarge\">\n");
      out.write("                    </div>\n");
      out.write("                </form>   \n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
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
