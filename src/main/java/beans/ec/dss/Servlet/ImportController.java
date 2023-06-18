
package beans.ec.dss.Servlet;

import beans.ec.dss.DAO.MainDAOIMPL;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class ImportController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
            String action=request.getPathInfo();
            System.out.println(action);
            String split[]=action.split("/");
            System.out.println(Arrays.toString(split));
             switch(action)
             {
                 case "/View":
                 {
                     ImportData(request, response);
                     break;
                 }
             }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

        private void ImportData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        MainDAOIMPL mdi=new MainDAOIMPL();
            List<String> list=mdi.getTableName("");
            System.out.println(list.toString());
            request.setAttribute("TableList",list);            
            RequestDispatcher dispatcher=request.getRequestDispatcher("../ImportList.jsp");                
            dispatcher.forward(request, response);

    }

}
