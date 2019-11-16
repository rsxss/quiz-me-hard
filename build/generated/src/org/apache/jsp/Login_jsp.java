package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Login</title>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Raleway\">\n");
      out.write("    <style>\n");
      out.write("        body,h1 {font-family: \"Raleway\", sans-serif}\n");
      out.write("        body, html {height: 100%}\n");
      out.write("        .bgimg {\n");
      out.write("            background-image: url('images/bg1.jpg');\n");
      out.write("            min-height: 100%;\n");
      out.write("            background-position: center;\n");
      out.write("            background-size: cover;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <div class=\"bgimg w3-display-container w3-animate-opacity w3-text-white \">\n");
      out.write("            <div class=\"w3-display-middle  w3-wide w3-text-light-grey w3-center \">\n");
      out.write("                <div class=\"w3-border w3-animate-top w3-padding-large \">\n");
      out.write("                    <div class=\"w3-light-grey  w3-padding-large w3-animate-top\">\n");
      out.write("                        <h1 class=\"w3-jumbo w3-animate-top \">LOGIN</h1>\n");
      out.write("                        <hr class=\"w3-border-grey w3-animate-top\" style=\"margin:auto;width:40%\">\n");
      out.write("                        <form action=\"Login\" method=\"post\" class=\"w3-large w3-center w3-animate-top \">\n");
      out.write("                            <p>Username : <input type=\"text\"  name=\"username\" class=\"w3-round-large\" required/><br>\n");
      out.write("                            <p>Password : <input type=\"password\"  name=\"password\" class=\"w3-round-large\" required/><br>                    \n");
      out.write("                                ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${message}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                                <br><input type=\"submit\" value=\"Login\"  class=\"w3-btn w3-teal w3-border w3-border-white w3-round-xlarge \">\n");
      out.write("                                <br><a href=\"Login?role=admin\">Admin</a> <a href=\"Login?role=teacher\">Teacher</a> <a href=\"Login?role=student\">Student</a>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"w3-display-bottomleft w3-padding-large\">\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
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
