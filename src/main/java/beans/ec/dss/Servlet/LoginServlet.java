package beans.ec.dss.Servlet;

import beans.ec.dss.DAO.MainDAOIMPL;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String action=req.getServletPath();
        System.out.println(action);
        switch(action)
        {
            case "/Homepage":
            {
                RequestDispatcher dispatcher = req.getRequestDispatcher("Homepage.jsp");
                dispatcher.forward(req, resp);   
                return;
            }
            case "/Login":
            {
                ViewLoginPage(req, resp);
                break;
            }            
                    
        }        
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
            
        try {
            Login(req, resp);
        } catch (NoSuchAlgorithmException | SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 private void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException, SQLException
  {
       
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println(username);
        System.out.println("K");
        System.out.println(password);
        HttpSession session=request.getSession();                                
        session.setAttribute("LoggedIn","false");
        if(session.getAttribute("LoggedIn").equals("true"))
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Homepage.jsp");
            dispatcher.forward(request, response);   
        }
        else
        {

            MainDAOIMPL mdi=new  MainDAOIMPL();
            if(mdi.CheckUser(username,digest("SHA-256",password)))
            {   
                session.setAttribute("Name",username);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Homepage.jsp");
                dispatcher.forward(request, response);   
                session.setAttribute("LoggedIn","true");                
            }
            else
            {
                session.setAttribute("message","Invalid Details!");            
                response.sendRedirect("http://localhost:8080/TimeTable-DSS/");            
            }            
        }

    }

    private void ViewLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {        
        HttpSession session=request.getSession();                        
        RequestDispatcher dispatcher = request.getRequestDispatcher("../index.jsp");
        dispatcher.forward(request, response);
    }
        public static String digest(String alg, String input) throws NoSuchAlgorithmException {
    try {
        MessageDigest md = MessageDigest.getInstance(alg);
        byte[] buffer = input.getBytes("UTF-8");
        md.update(buffer);
        byte[] digest = md.digest();
        return encodeHex(digest);
    } catch (UnsupportedEncodingException e) {
        return e.getMessage();
    }
    
}
    private static String encodeHex(byte[] digest) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < digest.length; i++) {
        sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
    }
    return sb.toString();
    }
}

