
package My_classes;

import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Genre { // the loai sach
    private int id;
    private String name;

    public Genre() {
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    // them mot the loai
    public void addGenre(String name){
        
        
        String insertQuery="INSERT INTO `book_genres`( `name`) VALUES (?)";
        try {
            PreparedStatement ps= (PreparedStatement) DB.getConnection().prepareStatement(insertQuery);
            
            ps.setString(1,name);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null, "Thêm thể loại thành công","them the loai",1);
            }
            else{
                JOptionPane.showMessageDialog(null, "Thêm thể loại không thành công","them the loai",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Genre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        // sua mot the loai
    public void editGenre(int id,String name){
        
        
        String editQuery="UPDATE `book_genres` SET `name` = ? WHERE `id` = ?";
        try {
            PreparedStatement ps= (PreparedStatement) DB.getConnection().prepareStatement(editQuery);
            
            ps.setString(1, name);
            ps.setInt(2,id);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null, "Sửa thể loại thành công","sua the loai",1);
            }
            else{
                JOptionPane.showMessageDialog(null, "Sửa thể loại không thành công","sua the loai",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Genre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // xoa mot the loai
    public void removeGenre(int id){
        
        
        String removeQuery="DELETE FROM `book_genres` WHERE `id`=?";
        try {
            PreparedStatement ps= (PreparedStatement) DB.getConnection().prepareStatement(removeQuery);
            
            ps.setInt(1,id);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null, "Xóa thể loại thành công","xoa the loai",1);
            }
            else{
                JOptionPane.showMessageDialog(null, "Xóa thể loại không thành công","xoa the loai",2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Genre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // danh sach 
    public ArrayList<Genre> genresList(){
        
        ArrayList<Genre> list = new ArrayList<>();
        
        
        String selectQuery="SELECT * FROM `book_genres`";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = (PreparedStatement) DB.getConnection().prepareStatement(selectQuery);
            rs=ps.executeQuery();
            Genre genre;
            while(rs.next()){
                genre = new Genre(rs.getInt("id"),rs.getString("name"));
                list.add(genre);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Genre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
