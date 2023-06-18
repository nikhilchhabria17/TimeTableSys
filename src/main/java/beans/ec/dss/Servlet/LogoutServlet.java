package beans.ec.dss.Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;  
  
public class LogoutServlet extends HttpServlet {  
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
                                        
                HttpSession session=request.getSession();               
                if(request.getAttribute("Name")!=null)
                {
                    session.setAttribute("loggedIn","false");
                    session.setAttribute("message","You are Logged Out!");
                }                                    
             
                response.sendRedirect("http://localhost:8080/TimeTable-DSS/");
   }  
}  