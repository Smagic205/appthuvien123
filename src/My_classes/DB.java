package My_classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;



public class DB {
    
        public Connection con(){
        Connection c=null;
        try {
            String xl="LAPTOP-VJ8EK13Q";
            String url="jdbc:mysql://localhost:3306/quanlythuvien";
            String user="root";
            String passwork="200805";
            c= DriverManager.getConnection(url, user, passwork);
            System.out.println("ket noi thanh cong");
        } catch (Exception e) {
            System.out.println("loi");
            System.out.println(e);
        }
      return c;
    }
        
    
     public void offcon(Connection c){
        if(c!=null){
            try {
                 c.close();
            } catch (Exception e) {
                System.out.println("loi");
            }
        }
    }
     
     
     
}