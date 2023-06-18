package beans.ec.dss.Servlet;

import beans.ec.dss.DAO.*;
import beans.ec.dss.Utils.DBCon;
import beans.ec.dss.entities.slotassigned;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerServlet extends HttpServlet
{
    @Override
    public void init()
    {
    }
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {      
        String action=request.getServletPath();
        System.out.println(action);
             HttpSession session = request.getSession();
             System.out.println(session.getAttribute("Name"));
        if(session.getAttribute("Name")==null)
        {
            response.sendRedirect("http://localhost:8080/TimeTable-DSS/");
            return;
        }
   
        switch(action)
        {
        
         case "/Display/Homepage":
        {
//            Homepage(request,response);
            break;
        }
         case "/TimeTable/UpdateSlot":
         {
            try {
                UpdateSlot(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
             break;
         }

         case "/TimeTable/Display":
            {
                DisplayEditTable(request,response);
                break;
                }                    
            case "/TimeTable/Validate":
            {
                ValidateTimeTable(request,response);
                break;
                }                    
            case "/TimeTable/Edit":
            {
            try {
                EditTimeTable(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
                break;
            }
            case "/EditSlots":
            {
                System.out.println("POST");
                UpdateSlots(request,response);
                break;
            }
              case "/Send":                
              {
//                Homepage(request, response);
                break;
              }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
    {        
        String action=request.getServletPath();
        System.out.println(request.getPathInfo());
        System.out.println(action);
        HttpSession session = request.getSession();
         System.out.println(session.getAttribute("Name"));
        if(session.getAttribute("Name")==null)
        {
            response.sendRedirect("http://localhost:8080/TimeTable-DSS/");
            return;
        }
   
        String[] split = action.substring(1,action.length()).split("/");
        System.out.println(Arrays.toString(split));
        System.out.println(split[0]);
        if("".equals(split[0]))
        {            
            
            response.sendRedirect("http://localhost:8080/TimeTable-DSS/Login");
        }        
        
        if("EditSlots".equals(split[0]))
        {                     
                UpdateSlots(request,response);                
        }
                if("EditSlots".equals(split[0]))
        {                     
                UpdateSlots(request,response);                
        }


        if(split.length>1&&split[1]!=null&&"Display".equals(split[1]))
        {                
                DisplayTable(request,response);            
            
        }
        if("Edit".equals(split[0]))
        {
            try {           
                EditTimeTable(request,response);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
                switch(split[1])
                {
                    case "TableList":
                    {
                        TableList(request, response);
                        break;
                    }
                    case "ExportData":
                    {
                        ExportData(request, response);
                        break;
                    }

                    case "ImportData":
                    {
                        ImportData(request, response);
                        break;
                    }

                    case "Validate":
                    {
                        ValidateTimeTable(request,response);
                        break;
                    }
                               case "ValidateSlots":
                    {
                        ValidateSlots(request,response);
                        break;
                    }
         
                    case "Download":
                    {
                        DownloadCSV(request,response);
                        break;
                    }
                    case "view":
                    {
                        mainmain(request,response,split[0]);
                        break;     
                    }
                    case "Export":
                {
                    DisplayExportTable(request,response,split[0]);
                }
                    case "edit":
                    {
                        try {
                            showEditForm(request, response,split[0]);
                        } catch (SQLException ex) {
                            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;

                }
                    case "Update":
                    {
                        try {
                            UpdateRecord(request, response,split[0]);
                        } catch (SQLException ex) {
                            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
          
         case "EditSlots":
            {
                System.out.println("PT");
                EditSlots(request,response);
                break;
                }                    
         case "UpdateSlots":
            {
                UpdateSlots(request,response);
                break;
                }                    
         case "/TimeTable/Display":
            {
                DisplayTable(request,response);
                break;
                }                    
            case "/insert":
                InsertForm(request,response);
                break;

            case "delete":
        {
            try {
                deleteRecord(request, response,split[0]);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
         

                case "Homepage":                                                
                {
//                    Homepage(request, response);
                    break;
                }
            }
        }

    }

    private void InsertForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditForm.jsp");
        dispatcher.forward(request, response);
    }
    private void deleteRecord(HttpServletRequest request, HttpServletResponse response,String tablename) throws IOException, SQLException 
    {
        String columnname=(String) request.getParameter("columnname");
        System.out.println(columnname);
        String columnval=(String) request.getParameter(columnname);        
        String sql="DELETE FROM course_allocation."+tablename+" WHERE "+columnname+"='"+columnval+"';";                
        System.out.println(sql);
        try (Connection cn = DBCon.getConnection()) {
            PreparedStatement smt=cn.prepareStatement(sql);
            
            smt.executeQuery();
            response.sendRedirect("http://localhost:8080/TimeTable-DSS/"+tablename+"/view");
        }
        catch(Exception e)
        {
            request.setAttribute("message","Error :"+e.getMessage());
            response.sendRedirect("http://localhost:8080/TimeTable-DSS/"+tablename+"/view");
        }         
        
        
    }

    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response,String tablename) throws SQLException, ServletException, ServletException, SQLException, IOException 
    {
        String columnname=request.getParameter("columnname");
        String value=request.getParameter(columnname);
        MainDAOIMPL mdi=new MainDAOIMPL();       
        TreeMap<String,String> map=mdi.UpdateData(tablename,columnname,value);        
        RequestDispatcher dispatcher = request.getRequestDispatcher("../EditForm.jsp");
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
                       response.sendRedirect("http://localhost:8080/TimeTable-DSS/"+name+"/view");
           }              
                    catch(SQLException e)
                    {
                        request.setAttribute("message","Error :"+e.getMessage());
                        response.sendRedirect("http://localhost:8080/TimeTable-DSS/"+name+"/view");
                    }
    }
    private void DisplayTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {    
        timetableDAOIMPL ttd=new timetableDAOIMPL();
        String str[][]=ttd.viewTimeTable();
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<5;j++)
            {
                System.out.print(str[i][j]+"\t");
            }
            System.out.println();
        }
        req.setAttribute("TimeTable",str);
        RequestDispatcher dispatcher=req.getRequestDispatcher("../check.jsp");
            dispatcher.forward(req, resp);
    }

    private void DisplayEditTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {        
        timetableDAOIMPL ttd=new timetableDAOIMPL();
        String str[][]=ttd.viewTimeTable();
        
        request.setAttribute("TimeTable",str);
        RequestDispatcher dispatcher=request.getRequestDispatcher("../EditTimeTable.jsp");                        
        dispatcher.forward(request, response);   

    }

    private void EditTimeTable(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
    {
        try
        {
            String str[][]=new String[8][5];
            for (int i = 0; i <8; i++) 
            {
                for(int j=1;j<=5;j++)
                {                                
                    str[i][j-1]=request.getParameter(i+""+(j));
                    System.out.print(str[i][j-1]+"\t");
                }
                System.out.println("");
            }
            timetableDAOIMPL ttd=new timetableDAOIMPL();        
            request.setAttribute("TimeTable",ttd.UpdateTimeTable(str));        
            RequestDispatcher dispatcher=request.getRequestDispatcher("../check.jsp");                        
            dispatcher.forward(request, response);   
        }
        catch(ServletException | IOException | SQLException e)
        {
            request.setAttribute("message","Error :"+e.getMessage());
            RequestDispatcher dispatcher=request.getRequestDispatcher("../check.jsp");                        
            dispatcher.forward(request, response);   
        }
    }

    private void EditSlots(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        try
        {                    
            String Val="";
            Val=request.getParameter("Val");
            slotAssignedDAOIMPL sad=new slotAssignedDAOIMPL();        
            if("".equals(Val))
            {        
                HashMap<String ,ArrayList<String>> map=sad.viewSlotAssigned();      
                ArrayList<String> KeyList=new ArrayList<>();
                for(String key:map.keySet())
                {
                    KeyList.add(key);
                }
                request.setAttribute("KeyList",KeyList);
                RequestDispatcher dispatcher=request.getRequestDispatcher("../UpdateSlots.jsp");                        
                dispatcher.forward(request, response);   
            }
            else
            {
               HashMap<String ,ArrayList<String>> map=sad.viewSlotAssigned();  
               ArrayList<String> list=map.get(Val);
                   ArrayList<String> KeyList=new ArrayList<>();
                for(String key:map.keySet())
                {
                    KeyList.add(key);
                }
                request.setAttribute("KeyList",KeyList);

               request.setAttribute("SlotValue",list);
               RequestDispatcher dispatcher=request.getRequestDispatcher("../UpdateSlots.jsp");                        
               dispatcher.forward(request, response);   
            }
        }
        catch(ServletException | IOException e)
        {
            request.setAttribute("message","Error :"+e.getMessage());
            RequestDispatcher dispatcher=request.getRequestDispatcher("../UpdateSlots.jsp");                        
            dispatcher.forward(request, response);   
        }
    }

    private void UpdateSlots(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServletException, IOException 
    {
        String slotno=request.getParameter("Val");
        String courseid=(String) request.getParameter("OldVal");
        System.out.println(courseid);
        slotassigned sa=new slotassigned(slotno,courseid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../EditSlots.jsp");
        request.setAttribute("SlotVal",sa);                        
        dispatcher.forward(request, response);           
    }

    private void UpdateSlot(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
    {
        String SlotNo=request.getParameter("SlotNo");
        String OldCourse=request.getParameter("oldcoursename");
        String NewCourse=request.getParameter("newcoursename");
        slotAssignedDAOIMPL sadi=new slotAssignedDAOIMPL();
        sadi.updateSlots(SlotNo,OldCourse,NewCourse);
        response.sendRedirect("http://localhost:8080/TimeTable-DSS/TimeTable/EditSlots?Val="+SlotNo);
    }
    private void Homepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
       
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        if("admin".equals(username)&&"admin".equals(password))
        {   
            HttpSession session=request.getSession();                        
            session.setAttribute("Name","Admin");
            RequestDispatcher dispatcher = request.getRequestDispatcher("../Homepage.jsp");
            dispatcher.forward(request, response);   
        }
        else
        {
            HttpSession session=request.getSession();            
            session.setAttribute("message","Invalid Details!");            
            RequestDispatcher dispatcher = request.getRequestDispatcher("../index.jsp");
            dispatcher.forward(request, response);   
        }

    }

    private void mainmain(HttpServletRequest request, HttpServletResponse response,String tablename) throws ServletException, ServletException, ServletException, IOException, ServletException, ServletException 
    {
        MainDAOIMPL mdi=new MainDAOIMPL();        
        HashMap<Integer,List<String>> map=mdi.viewCourse(tablename);           
        System.out.println(map.toString());
        request.setAttribute("MainMain",map);
        request.setAttribute("tablename",tablename);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../mainmain.jsp");                                        
        dispatcher.forward(request, response);      
        
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

    private void DownloadCSV(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
      timetableDAOIMPL ttd=new  timetableDAOIMPL();                        
      String [][] str=ttd.viewTimeTable();
            request.setAttribute("TimeTable",str);            
            RequestDispatcher dispatcher=request.getRequestDispatcher("../check.jsp");                
            dispatcher.forward(request, response);
 

    }

    private void ExportData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
                    MainDAOIMPL mdi=new MainDAOIMPL();
            List<String> list=mdi.getTableName("");
            System.out.println(list.toString());
            request.setAttribute("TableList",list);            
            RequestDispatcher dispatcher=request.getRequestDispatcher("../ExportList.jsp");                
            dispatcher.forward(request, response);

    }

    private void DisplayExportTable(HttpServletRequest request, HttpServletResponse response, String tablename) throws ServletException, IOException 
    {       
        MainDAOIMPL mdi=new MainDAOIMPL();        
        HashMap<Integer,List<String>> map=mdi.viewCourse(tablename);           
        System.out.println(map.toString());
        request.setAttribute("MainMain",map);        
        request.setAttribute("tablename",tablename);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../ViewExport.jsp");                                                
        dispatcher.forward(request, response);      
    
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

    private void ValidateTimeTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        
        MainDAOIMPL mdi=new MainDAOIMPL();        
        String[][] str=new String[8][5];
        for (int i = 0; i <8; i++) {
            for (int j = 0; j<5; j++)
            {         
                str[i][j]=request.getParameter((i)+""+((j+1)));
            }            
        }        
        List<List<String>> list=mdi.ValidateTimeTable(str);
        request.setAttribute("TimeTable",str);            
        request.setAttribute("days",list.get(0));            
        request.setAttribute("slots",list.get(1));            
        request.setAttribute("faculty",list.get(2));            
        RequestDispatcher dispatcher=request.getRequestDispatcher("../EditTimeTable.jsp");                
        dispatcher.forward(request, response);

        
    }

    private void ValidateSlots(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    MainDAOIMPL mdi=new MainDAOIMPL();                
    List<HashMap<String,String>> list=mdi.validateSlots();        
    request.setAttribute("Faculty",list.get(0));
    request.setAttribute("Rooms",list.get(1));
        System.out.println(list.get(0).toString());
        System.out.println(list.get(1).toString());
    RequestDispatcher dispatcher=request.getRequestDispatcher("../SlotsError.jsp");                
        dispatcher.forward(request, response);
    }
        
    
}
 
