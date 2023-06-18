package beans.ec.dss.DAO;

import java.util.List;
import java.util.HashMap;
import java.util.TreeMap;

public interface MainDAO 
{    
    public HashMap<Integer,List<String>> viewCourse(String name);    
    public TreeMap<String,String> UpdateData(String tablename,String columnname,String value);
    public List<String> getTableName(String name);
    public List<List<String>> ValidateTimeTable(String str[][]);
    public List<HashMap<String, String>> validateSlots() ;
    public void DeleteData(String tablename,String columnname,String value);
}
