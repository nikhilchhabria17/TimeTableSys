package beans.ec.dss.Servlet;

import beans.ec.dss.DAO.MainDAOIMPL;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class ExportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
               String action=request.getPathInfo();
            System.out.println(action);
            String split[]=action.split("/");
            System.out.println(Arrays.toString(split));
            if(split.length>2&&"Export".equals(split[2]))
            {
                DisplayExportTable(request, response,split[1]);
            };
            
            switch(action)
            {
                case "/View":
                {
                    ExportData(request, response);
                    break;
                }
            }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
     private void ExportData(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, IOException 
    {
                    MainDAOIMPL mdi=new MainDAOIMPL();
            List<String> list=mdi.getTableName("");
            System.out.println(list.toString());
            request.setAttribute("TableList",list);            
            RequestDispatcher dispatcher=request.getRequestDispatcher("../ExportList.jsp");                
            dispatcher.forward(request, response);

    }

    private void DisplayExportTable(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, String tablename) throws jakarta.servlet.ServletException, IOException 
    {       
        MainDAOIMPL mdi=new MainDAOIMPL();        
        HashMap<Integer,List<String>> map=mdi.viewCourse(tablename);           
        System.out.println(map.toString());
        request.setAttribute("MainMain",map);        
        request.setAttribute("tablename",tablename);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../../ViewExport.jsp");                                                
        dispatcher.forward(request, response);      
    
    }
}
