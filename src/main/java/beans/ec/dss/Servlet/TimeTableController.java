package beans.ec.dss.Servlet;

import java.io.BufferedReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.*;
 
import java.io.InputStreamReader;
import beans.ec.dss.DAO.MainDAOIMPL;
import beans.ec.dss.DAO.slotAssignedDAOIMPL;
import beans.ec.dss.DAO.timetableDAOIMPL;
import beans.ec.dss.entities.slotassigned;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeTableController extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {        
        try {
            String action=req.getPathInfo();
            System.out.println(action);
            switch(action)
            {
                case "/Display":
                    ViewEditTimeTable(req,resp);
                    break;
                case "/Validate":
                    ValidateTimeTable(req,resp);
                    break;                   
                case "/Homepage":
                    System.out.println("Parth");
                    break;
                case "/Edit":
                    SubmitEditTimeTable(req,resp);
                    break;
                case "/UpdateSlot":
                    UpdateEditSlots(req, resp);        
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        
        String action=req.getPathInfo();
        System.out.println(action);
        switch(action)
        {
            case "/Display":
            {
                DisplayTable(req,resp);
                break;
            }
            case "/Homepage" :
            {
                System.out.println("Parth");
                DisplayTable(req,resp);
                break;
            }
            case "/EditSlots":
            {
                DisplayEditSlots(req,resp);
                break;
            }
            case "/DownloadTimeTable":
            {
                DownloadTimeTable(req,resp);
                break;
            }
            case "/UpdateSlots":
            {
                UpdateSlots(req, resp);            
                break;
            }
            case "/ValidateSlots":
            {
                ValidateSlots(req, resp);            
                break;
            }

        }
    }

    private void DisplayTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
           timetableDAOIMPL ttd=new timetableDAOIMPL();
        String str[][]=ttd.viewTimeTable();        
        request.setAttribute("TimeTable",str);
        RequestDispatcher dispatcher=request.getRequestDispatcher("../ViewTimeTable.jsp");                        
        dispatcher.forward(request, response);   

    }

    private void ViewEditTimeTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException 
    {
        timetableDAOIMPL ttd=new timetableDAOIMPL();
        String str[][]=ttd.viewTimeTable();                        
        request.setAttribute("TimeTable",str);
        RequestDispatcher dispatcher=request.getRequestDispatcher("../EditTimeTable.jsp");                                        
        dispatcher.forward(request, response);           
    }

    private void SubmitEditTimeTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServletException, IOException 
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
            RequestDispatcher dispatcher=request.getRequestDispatcher("../ViewTimeTable.jsp");                        
            dispatcher.forward(request, response);   
        }
        catch(ServletException | IOException | SQLException e)
        {
            request.setAttribute("message","Error :"+e.getMessage());
            RequestDispatcher dispatcher=request.getRequestDispatcher("../ViewTimeTable.jsp");                                                
            dispatcher.forward(request, response);   
        }
    }

    private void DisplayEditSlots(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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

    private void UpdateSlots(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
     
        String slotno=request.getParameter("Val");
        String courseid=(String) request.getParameter("OldVal");
        System.out.println(courseid);
        slotassigned sa=new slotassigned(slotno,courseid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../EditSlots.jsp");
        request.setAttribute("SlotVal",sa);                                        
        dispatcher.forward(request, response);           
    }

    private void UpdateEditSlots(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
    {
        try
        {
            String SlotNo=request.getParameter("SlotNo");
            String OldCourse=request.getParameter("oldcoursename");
            String NewCourse=request.getParameter("newcoursename");
            slotAssignedDAOIMPL sadi=new slotAssignedDAOIMPL();
            sadi.updateSlots(SlotNo,OldCourse,NewCourse);
            response.sendRedirect("http://localhost:8080/TimeTable-DSS/TimeTable/EditSlots?Val="+SlotNo);
        }
        catch(IOException | SQLException e)
        {
            String SlotNo=request.getParameter("SlotNo");
            String OldCourse=request.getParameter("oldcoursename");
            HttpSession session=request.getSession();
            session.setAttribute("message","Invalid! Course entered doesn't exists");
            response.sendRedirect("http://localhost:8080/TimeTable-DSS/TimeTable/UpdateSlots?Val="+SlotNo+"&OldVal="+OldCourse);
                    
        }
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
        HttpSession session=request.getSession();
        session.setAttribute("message","Scroll down to find the Errors");
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

    private void DownloadTimeTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {        
            try {
	      // Run a Python script
              System.out.println("Working Directory = " + System.getProperty("user.dir"));
	      Process p = Runtime.getRuntime().exec("python D:\\Java\\TimeTableSys\\src\\main\\webapp\\ROOT\\Python\\createTimetable.py");

	      // Get the output from the script
	      BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
	      String output = in.readLine();
	      while (output != null) {
	        output = in.readLine();
	      }

	      // Handle any errors that occur
	      BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	      String errorOutput = error.readLine();
              String message="";
	      while (errorOutput != null) 
              {
                errorOutput = error.readLine();
                message+=errorOutput+"\n";
	      }
              
              
               CopyOption[] options = new CopyOption[]{
            StandardCopyOption.REPLACE_EXISTING
     }; 
              
         Path temp=Files.move
        (Paths.get(System.getProperty("user.dir")+"\\timetable.xlsx"),
        Paths.get("D:\\Java\\TimeTableSys\\src\\main\\webapp\\ROOT\\Python\\timetable.xlsx"),options);
         
         if(temp != null)
        {
            HttpSession session=request.getSession();
            session.setAttribute("message","TimeTable Downloaded Succesfully! File Created : timeTable.xlsx");
            System.out.println("File renamed and moved successfully");
        }
        else
        {
            System.out.println("Failed to move the file");            
            message+="\n Error:File Not Created";
        }
              
            HttpSession session=request.getSession();
            if(!"".equals(message))
                session.setAttribute("message","Error :"+message);
            response.sendRedirect("http://localhost:8080/TimeTable-DSS/TimeTable/Homepage");

              
	    } catch (IOException e) {
	               HttpSession session=request.getSession();
                       
                       session.setAttribute("message","Error :"+e.getMessage());
                       
            response.sendRedirect("http://localhost:8080/TimeTable-DSS/TimeTable/Homepage");
	    }
	}
    }


