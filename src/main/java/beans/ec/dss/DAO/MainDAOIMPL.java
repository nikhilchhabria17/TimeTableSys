package beans.ec.dss.DAO;

import beans.ec.dss.Utils.DBCon;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainDAOIMPL implements MainDAO
{ 


    @Override
    public HashMap<Integer,List<String>> viewCourse(String name)
    {    
        HashMap<Integer,List<String>> map=new HashMap<>();
        String sql="SELECT * FROM course_allocation."+name+" order by 1";        
        List<Object> list=new ArrayList<>();
        try {
            Connection cn = DBCon.getConnection();
            PreparedStatement smt=cn.prepareStatement(sql);        
            ResultSet rs=smt.executeQuery();                
            ResultSetMetaData rsmt=rs.getMetaData();
            int columncount=rsmt.getColumnCount();
            System.out.println(columncount);
            System.out.println(rs);
            int row = 0;
            
            if(rs.next()){
                map.put(0,new ArrayList<String>());
                map.put(++row,new ArrayList<String>());
                               for (int i = 1; i <=columncount; i++)
                {
                    String nae=rsmt.getColumnName(i);
                    String type=rsmt.getColumnTypeName(i);  
                    System.out.println(nae+""+type);
                        if("varchar".equals(type)||"bpchar".equals(type))
                        {                                
                           map.get(0).add(nae);
                        map.get(row).add(rs.getString(nae));                                

                        }
                            else if("numeric".equals(type)||"int4".equals(type))
                            {                                                                
                             map.get(0).add(nae);
                                 map.get(row).add(String.valueOf(rs.getInt(nae)));                                 
                             
                            }
 
                 }
            }
            
            while(rs.next())
            {
                map.put(++row, new ArrayList<String>());
                for (int i = 1; i <=columncount; i++)
                {
                    String nae=rsmt.getColumnName(i);
                    String type=rsmt.getColumnTypeName(i);                                        
                        if("varchar".equals(type)||"bpchar".equals(type))
                        {                                
                            System.out.println(rs.getString(nae));
                        map.get(row).add(rs.getString(nae));                                

                        }
                            else if("numeric".equals(type)||"int4".equals(type))
                            {                                                                
                            
                                System.out.println(rs.getInt(nae));
                                 map.get(row).add(String.valueOf(rs.getInt(nae)));                                 
                             
                            }
 
                 }
                
            }          
            cn.close();
        } 
        catch (SQLException ex) {            
        }   
        System.out.println(map.toString());        
        return map;
    }
    public TreeMap<String,String> UpdateData(String tablename,String columnname,String value)
    {                
        String sql="SELECT * FROM course_allocation."+tablename+" where "+columnname+"='"+value+"';";                
        System.out.println(sql);
        System.out.println(columnname);
        Connection cn;
        TreeMap<String,String> map=new TreeMap<>();
        try {
            cn = DBCon.getConnection();
            PreparedStatement smt=cn.prepareStatement(sql);        
            ResultSet rs=smt.executeQuery();                
            ResultSetMetaData rsmt=rs.getMetaData();
            int columncount=rsmt.getColumnCount();
            System.out.println(columncount);
            System.out.println(rs);
            
            
            
            
            
                        if(rs.next()){                
             for (int i = 1; i <=columncount; i++)
                {
                    String nae=rsmt.getColumnName(i);
                    String type=rsmt.getColumnTypeName(i);  
                    System.out.println(nae+""+type);
                        if("varchar".equals(type)||"bpchar".equals(type))
                        {                                
                           map.put(nae,rs.getString(nae));                        

                        }
                            else if("numeric".equals(type)||"int4".equals(type))
                            {                                                                                             
                                 map.put(nae,String.valueOf(rs.getInt(nae)));                                 
                             
                            }
 
                 }
            }

            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return map;
    }    
   
    public static void main(String[] args) {        
    }

    @Override
    public List<String> getTableName(String name) 
    {
        List<String> list=new ArrayList<>();
        try {
            Connection conn = DBCon.getConnection();                    
            DatabaseMetaData metaData = (DatabaseMetaData) conn.getMetaData();
            ResultSet tables = metaData.getTables(null, "course_allocation", null, new String[] {"TABLE"});
            while (tables.next()){
                list.add(tables.getString("TABLE_NAME"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }        
     return list;       
    }
    @Override
    public List<List<String>> ValidateTimeTable(String slots[][])
{
        try {   
            String sql="select a.shortname from (select distinct f.shortname from course_allocation.slotassigned s join course_allocation.facultyassigned f on f.courseno=s.courseno where s.slotno=?) a join (select distinct f.shortname from course_allocation.slotassigned s join course_allocation.facultyassigned f on f.courseno=s.courseno where s.slotno=?) b on a.shortname=b.shortname";                
            Connection conn = DBCon.getConnection();
            PreparedStatement smt=conn.prepareStatement(sql);                    
            ResultSet rsmt=null;            
            List<List<String>> list=new ArrayList<>();
            List<String> hour=new ArrayList<>();
            List<String> days=new ArrayList<>();
            List<String> facultyname=new ArrayList<>();
        for (int i = 0; i <5; i++) {
            for (int j = 0; j <7; j++)
            {
                  smt.setString(1,slots[j][i]);
                smt.setString(2,slots[j+1][i]);
                rsmt=smt.executeQuery();
                while(rsmt.next())
                {
                    
                    System.out.println(i+"-"+j); System.out.println(rsmt.getString(1));
                    String day="";
                    switch(i+1)
                    {  
                        case 1:
                        day="MONDAY";
                        break;
                    case 2:
                        day="TUESDAY";
                        break;
                    case 3:
                        day="WEDNESDAY";
                        break;
                    case 4:
                    day="THURSDAY";
                    break;
                    case 5:
                    day="FRIDAY";
                    break;
                    }
                    days.add(day);
                    hour.add("H"+(j+1)+"- H"+(j+2));
                    facultyname.add(rsmt.getString(1));
                
            }
                list.add(days);
                list.add(hour);
                list.add(facultyname);
                System.out.println(list.get(0).toString());
                System.out.println(list.get(1).toString());
                System.out.println(list.get(2).toString());
               System.out.println("----------------");
        }   
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(MainDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
}   
    @Override
    public List<HashMap<String, String>> validateSlots() 
    {
              String sql="select distinct slotno from course_allocation.slotassigned;";
            Connection conn;
        try {
            conn = DBCon.getConnection();
            PreparedStatement smt=conn.prepareStatement(sql);                    
            ResultSet rsmt=smt.executeQuery();
            HashMap<String,String> faculty=new HashMap<>();
            HashMap<String,String> rooms=new HashMap<>();                        
            while(rsmt.next())
            {
                String slot=rsmt.getString(1);
                List<List<String>> list=checkValidSlot(slot);
                if(!list.get(0).isEmpty())
                {
                    faculty.put(slot,(list.get(0)).toString());
                }
                if(!list.get(1).isEmpty())
                {
                    rooms.put(slot,(list.get(1)).toString());
                }
            }
            List<HashMap<String,String>> data=new ArrayList<>();
            data.add(faculty);
            data.add(rooms);
            conn.close();
            return data;
        } catch (SQLException ex) {
            Logger.getLogger(MainDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }                        
        return null;
    
    }

    private List<List<String>> checkValidSlot(String slot) throws SQLException 
    {
        
        Connection conn = DBCon.getConnection();
        String sql="select f.shortname,count(s.courseno) from course_allocation.slotassigned s join course_allocation.facultyassigned f on s.courseno=f.courseno where s.slotno=? group by f.shortname having count(s.courseno)>1";                
        String sql1="select r.roomno,count(s.courseno) from course_allocation.slotassigned s join course_allocation.roomassigned r on s.courseno=r.courseno where s.slotno=? group by r.roomno,s.slotno having count(s.courseno)>1";                
        PreparedStatement smt=conn.prepareStatement(sql);                    
        smt.setString(1, slot);
        ResultSet rsmt=smt.executeQuery();
        List<String> faculty=new ArrayList<>();
        List<String> rooms=new ArrayList<>();
        while(rsmt.next())
        {
            faculty.add(rsmt.getString(1));
        }
        smt=conn.prepareStatement(sql1);                    
        smt.setString(1, slot);
        rsmt=smt.executeQuery();
        while(rsmt.next())
        {
            rooms.add(rsmt.getString(1));
        }
        List<List<String>> list=new ArrayList<>();
        list.add(faculty);
        list.add(rooms);
        conn.close();
        return list;
    }

    @Override
    public void DeleteData(String tablename,String columnname,String value)     
    {       

        try {
            Connection conn = DBCon.getConnection();
            String sql="delete from course_allocation."+tablename+" where "+columnname+"=?";
            System.out.println(sql);       
            PreparedStatement smt=conn.prepareStatement(sql);                                
            smt.setString(1,value);
            smt.execute();
            conn.close();            
            
        } catch (SQLException ex) {
            Logger.getLogger(MainDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    public void insertUser(String username,String password) throws SQLException
    {        
        Connection conn = DBCon.getConnection();
        String sql="insert into course_allocation.users values(?,?)";
            System.out.println(sql);       
            PreparedStatement smt=conn.prepareStatement(sql);                                
            smt.setString(1,username);
            smt.setString(2, password);
            smt.execute();
            conn.close();            
    }
    public boolean CheckUser(String username,String password) throws SQLException 
    {
        Connection conn = DBCon.getConnection();
        String sql="select from course_allocation.users where userid=? and password=?";
        PreparedStatement smt=conn.prepareStatement(sql);
        smt.setString(1,username);
        smt.setString(2,password);
        ResultSet rsmt=smt.executeQuery();
        boolean userExists=rsmt.next();       
        System.out.println(userExists);
        return userExists;
    }
}
