package beans.ec.dss.Servlet;

import beans.ec.dss.DAO.MainDAOIMPL;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String action=request.getPathInfo();             
        System.out.println(action);
        switch(action)
        {
            case "/View":
            ViewUserTable(request,response);
            break;
            case "/delete" :
                deleteUser(request,response);
                break;
            case "/AddUser" :
                addUser(request,response);
                break;
                
            

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String action=request.getPathInfo();             
        System.out.println(action);
        switch(action)
        {
        case "/InsertUser" :
        {
                try {
                    insertUser(request,response);
                } catch (SQLException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(ManageUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
                break;
        }
    }

    private void ViewUserTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {   
        MainDAOIMPL mdi=new MainDAOIMPL();        
        HashMap<Integer,List<String>> map=mdi.viewCourse("users");           
        for (Integer key: map.keySet())
        {            
           map.get(key).remove(1);
        }
        request.setAttribute("MainMain",map);
        request.setAttribute("tablename","users");
        RequestDispatcher dispatcher=request.getRequestDispatcher("../ViewUserTable.jsp");                
        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {        
        MainDAOIMPL mdi=new MainDAOIMPL();
        String columnname=request.getParameter("columnname");
        String value=request.getParameter(columnname);
        mdi.DeleteData("users", columnname, value);        
        response.sendRedirect("http://localhost:8080/TimeTable-DSS/Homepage");        
        
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        RequestDispatcher dispatcher=request.getRequestDispatcher("../AddUser.jsp");                                
        dispatcher.forward(request, response);
        
    }
    
    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, NoSuchAlgorithmException 
    {
        try{
            System.out.println("Hello1");
            MainDAOIMPL mdi=new MainDAOIMPL();
            String username=request.getParameter("username");
            String password=request.getParameter("password");                    
            mdi.insertUser(username,digest("SHA-256",password));
            RequestDispatcher dispatcher=request.getRequestDispatcher("../AddUser.jsp");                                
            dispatcher.forward(request, response);
        }
        catch(ServletException | IOException | NoSuchAlgorithmException | SQLException e)
        {
            HttpSession session=request.getSession();
            session.setAttribute("message","Invalid! User Already Exists!");
            RequestDispatcher dispatcher=request.getRequestDispatcher("../AddUser.jsp");                                
            dispatcher.forward(request, response);
            
        }
        
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

