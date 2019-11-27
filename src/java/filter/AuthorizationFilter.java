/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.entities.User;

/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class AuthorizationFilter implements Filter {
    
    private FilterConfig filterConfig;
    
    public AuthorizationFilter() {
    }    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       this.filterConfig = filterConfig;
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        User user = (User) session.getAttribute("user");
        if(user.getIsAdmin()){
            chain.doFilter(request, response);
        } else {
            request.setAttribute("message", "NOT AUTHORIZED");
            request.setAttribute("messageLevel", "error");
            request.getServletContext().getRequestDispatcher("/Login").forward(request, response);
            session.invalidate();
        }
    }
    

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
