
package beans.ec.dss.Servlet;

import beans.ec.dss.Utils.DBCon;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@MultipartConfig
public class UploadServlet extends HttpServlet {

    public UploadServlet() {
        super();
    }

    @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                       
        String tablename=request.getParameter("tablename");
        System.out.println(tablename);
        boolean flag=false;
        try {    
            
            Connection cn=DBCon.getConnection();
            cn.setAutoCommit(false);
            String sql="delete from course_allocation."+tablename;                         
            Statement statement=cn.createStatement();                                                           
            statement.execute(sql);  
            System.out.println("K");
            Part filePart = request.getPart("file");             
             String fileName[] = filePart.getSubmittedFileName().split("\\.");
             System.out.println(Arrays.toString(fileName));
             if(fileName.length>1&&!"csv".equals(fileName[1]))
             {
                 HttpSession session=request.getSession();
            session.setAttribute("message","Import Unsuccesful! Wrong File Format , Only CSV Accepted!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Homepage.jsp");
            dispatcher.forward(request, response);            
             }
            InputStream fileContent = filePart.getInputStream();
            Reader in = new InputStreamReader(fileContent);            
            CSVReader reader;
            Iterator<String[]> iterator;

            reader = new CSVReader(new InputStreamReader(fileContent));
            iterator = reader.iterator();            
            String[] nextRecord;                        
             while ((nextRecord = reader.readNext()) != null) 
             {
                sql="insert into course_allocation."+tablename+" values(";
                for(int i=0;i<nextRecord.length;i++)
                {
                    if(i==nextRecord.length-1)
                    {
                        sql+="'"+nextRecord[i]+"')";
                    }
                    else
                    {
                        sql+="'"+nextRecord[i]+"',";
                    }
                }               
                 System.out.println(sql);

                    try
                {
                      statement.execute(sql);                                                 
                }
                 catch(SQLException e)
                 {            
                     flag=true;
                     System.out.println("Catch Catch!");  
                     break;
                 }                 
            }
                        if(flag)
                        {
                                System.out.println("Nahh");
                            cn.rollback();                     
                            HttpSession session=request.getSession();
            session.setAttribute("message","Import Unsuccesful! Please Correct the CSV File.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Homepage.jsp");
            dispatcher.forward(request, response);            
                        }
                     if(!flag)
                    {
                            System.out.println("Yeahh");
             HttpSession session=request.getSession();
            session.setAttribute("message","Imported Succesfully");
 
                        cn.commit();
                    }
            cn.close();
            RequestDispatcher dispatcher = request.getRequestDispatcher("Homepage.jsp");
            dispatcher.forward(request, response);
             
        } catch (CsvValidationException | SQLException ex)        
        {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
            HttpSession session=request.getSession();
            session.setAttribute("message","Can't Import!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Homepage.jsp");
            dispatcher.forward(request, response);            
        }                    
        }
   
  }

