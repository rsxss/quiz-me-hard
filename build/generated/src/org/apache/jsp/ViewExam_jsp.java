package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ViewExam_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Classroom</title>\n");
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
      out.write("        <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.equals('teacher')}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("            <div class=\"w3-container w3-teal\">\n");
      out.write("                <h1>Quiz Me Hard</h1>\n");
      out.write("                <div class=\"w3-display-topright w3-padding-large \">\n");
      out.write("                    <span>Teacher:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span> <a href=\"Logout\" class=\"w3-btn w3-teal w3-border w3-round-xlarge w3-hover-white\">Logout</a>\n");
      out.write("                </div>\n");
      out.write("                <h5>\n");
      out.write("                    <a href=\"SelectClass\" >Classes</a> /\n");
      out.write("                    <a href=\"SelectExam\" >Exam</a> /\n");
      out.write("                    Testing Cookies\n");
      out.write("                </h5>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"w3-sidebar w3-light-grey w3-bar-block\" style=\"width:10%\">\n");
      out.write("                <h3 class=\"w3-bar-item\">Menu</h3>\n");
      out.write("                <a href=\"ClassInfo\" class=\"w3-bar-item w3-button\">Class Info</a>\n");
      out.write("                <a href=\"SelectExam\" class=\"w3-bar-item w3-button w3-grey\">Exam</a>\n");
      out.write("                <div class=\" w3-display-bottomleft\" style=\"margin: 10px;margin-bottom: 50px\"><a href=\"SelectClass\">Back to Classes</a></div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"w3-container\" style=\"margin-left:10%\">\n");
      out.write("            <h1>Testing Cookies</h1>\n");
      out.write("                        <table class=\"w3-table w3-striped\">\n");
      out.write("                <tr>\n");
      out.write("                    <th>StudentID</th>\n");
      out.write("                    <th>First Name</th>\n");
      out.write("                    <th>Last Name</th>\n");
      out.write("                    <th>Score</th>\n");
      out.write("                    <th></th>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>001</td>\n");
      out.write("                    <td>นายฮายาโมโตะ</td>\n");
      out.write("                    <td>เกนชิโร่</td>\n");
      out.write("                    <td>50</td>\n");
      out.write("                    <td><a href=\"Exam\" class=\"w3-button w3-teal\" >Check</a></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>002</td>\n");
      out.write("                    <td>นางสาวยุย</td>\n");
      out.write("                    <td>ฮาจิบานะ</td>\n");
      out.write("                    <td>90</td>\n");
      out.write("                    <td><a href=\"Exam\" class=\"w3-button w3-teal\" >Check</a></td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("            </div>\n");
      out.write("        </c:if>\n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("        \n");
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
