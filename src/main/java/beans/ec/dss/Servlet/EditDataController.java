package beans.ec.dss.Servlet;

import beans.ec.dss.DAO.MainDAOIMPL;
import beans.ec.dss.Utils.DBCon;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditDataController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
            String action=request.getPathInfo();
            System.out.println(action);
            String split[]=action.split("/");
            System.out.println(Arrays.toString(split));
            if(split.length>2&&"view".equals(split[2]))
            {
                mainmain(request,response,split[1]);                    
                return;
            }
           if(split.length>2&&"edit".equals(split[2]))
            {
                try {
                    showEditForm(request, response,split[1]);
                } catch (SQLException ex) {
                    Logger.getLogger(EditDataController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }
           if(split.length>2&&"Update".equals(split[2]))
            {
                try {
                    UpdateRecord(request, response,split[1]);
                } catch (SQLException ex) {
                    Logger.getLogger(EditDataController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }


            switch(action)
            {
                case "/TableList":
                    TableList(request, response);
                    break;                    
            }            
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
            private void TableList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
            MainDAOIMPL mdi=new MainDAOIMPL();
            List<String> list=mdi.getTableName("");
            System.out.println(list.toString());
            request.setAttribute("TableList",list);            
            RequestDispatcher dispatcher=request.getRequestDispatcher("../TableList.jsp");                
            dispatcher.forward(request, response);
 
    }
    private void mainmain(HttpServletRequest request, HttpServletResponse response,String tablename) throws ServletException, ServletException, ServletException, IOException, ServletException, ServletException 
    {
        MainDAOIMPL mdi=new MainDAOIMPL();        
        HashMap<Integer,List<String>> map=mdi.viewCourse(tablename);           
        System.out.println(map.toString());
        request.setAttribute("MainMain",map);
        request.setAttribute("tablename",tablename);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../../mainmain.jsp");                                        
        dispatcher.forward(request, response);      
        
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response,String tablename) throws SQLException, ServletException, ServletException, SQLException, IOException 
    {
        String columnname=request.getParameter("columnname");
        String value=request.getParameter(columnname);
        MainDAOIMPL mdi=new MainDAOIMPL();       
        TreeMap<String,String> map=mdi.UpdateData(tablename,columnname,value);        
        RequestDispatcher dispatcher = request.getRequestDispatcher("../../EditForm.jsp");
        request.setAttribute("MapData",map);                
        dispatcher.forward(request, response);   
    }
    
    private void UpdateRecord(HttpServletRequest request, HttpServletResponse response,String name) throws SQLException, IOException, ServletException 
    {                    
            HashMap<String,String> map=new HashMap<>();
            String sql="SELECT * FROM course_allocation."+name+" order by 1";                
            try {
                    Connection cn = DBCon.getConnection();
                    PreparedStatement smt=cn.prepareStatement(sql);        
                    ResultSet rs=smt.executeQuery();                
                    ResultSetMetaData rsmt=rs.getMetaData();
                    int columncount=rsmt.getColumnCount();
                    System.out.println(columncount);
                    System.out.println(rs);
                    int row = 0;                        

                  if(rs.next())
                   {
                       sql="Update course_allocation."+name+" SET";
                      for (int i = 1; i <=columncount; i++)
                        {
                            String nae=rsmt.getColumnName(i);
                            String type=rsmt.getColumnTypeName(i);  
                            if("numeric".equals(type)||"int4".equals(type))
                            {
                                sql+=" "+nae+"="+request.getParameter(nae)+" ";
                            }
                            else
                            {
                                sql+=" "+nae+"='"+request.getParameter(nae)+"'";
                            }
                               if(i<columncount)
                            {
                                sql+=" , ";
                            }
                                                        
                         }
                      sql+=" WHERE";
                      for(int i=1;i<=columncount;i++)
                      {
                            String nae=rsmt.getColumnName(i);
                            String type=rsmt.getColumnTypeName(i);  
                            if("numeric".equals(type)||"int4".equals(type))
                            {
                                sql+=" "+nae+"="+request.getParameter("D"+nae)+" ";
                            }
                            else
                            {
                                sql+=" "+nae+"='"+request.getParameter("D"+nae)+"' ";
                            }
                            if(i<columncount)
                            {
                                sql+=" AND ";
                            }
                      }
                       sql+=";";
                       System.out.println(sql);
                       
                    }  
                  smt=cn.prepareStatement(sql);        
                       smt.executeUpdate();
                       cn.close();
                       response.sendRedirect("../"+name+"/view");
           }              
                    catch(SQLException e)
                    {
                        request.setAttribute("message","Error :"+e.getMessage());
                        response.sendRedirect("../"+name+"/view");
                    }
    }
}
