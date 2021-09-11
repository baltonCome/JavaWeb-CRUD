/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author Mr. Savagery
 */
public class DAO {
    
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://127.0.0.1:3306/agenda?useTimezone=true&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "";
    
    private Connection connect () throws SQLException{

        try{
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException e){
            e.getMessage();
        }
        return null;
    }
 
    
    public void insert(Bean contact) throws SQLException{
        
        String sql = "insert into contacts (name, number, email) values (?,?,?)";
        
        try{
            Connection conn = connect();
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getNumber());
            stmt.setString(3, contact.getEmail());
            
            stmt.executeUpdate();
        }catch(SQLException e){
            e.getMessage();
        }finally{
            connect().close();
        }
    }
    
    public ArrayList<Bean> list() throws SQLException{
        
        ArrayList<Bean> mylist = new ArrayList();
        String sql = "select * from contacts order by id";
        
        try{
            
            Connection conn  = connect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet result =  stmt.executeQuery();
            
            while(result.next()){
                
                int id = result.getInt(1);
                String name = result.getString(2);
                String number = result.getString(3);
                String email = result.getString(4);
                mylist.add(new Bean(id, name, number, email));
            }
            return mylist;
        }catch(SQLException e){
            e.getMessage();
        }           
        connect().close();
        return null;  
    }

    public void setContact(Bean contact) {
        
        String sql = "select * from contacts where id=?";
        
        try{
            
            PreparedStatement stmt = connect().prepareStatement(sql);
            stmt.setInt(1, contact.getId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                contact.setId(rs.getInt(1));
                contact.setName(rs.getString(2));
                contact.setNumber(rs.getString(3));
                contact.setEmail(rs.getString(4));
            }
            connect().close();
        }catch(SQLException e){
            e.getMessage();
        }
    }

    public void update(Bean contact) throws SQLException {
        
        String sql = "update contacts set name=?, number=?, email=? where id=?";
        
        PreparedStatement stmt = connect().prepareStatement(sql);
        
        stmt.setString(1, contact.getName());
        stmt.setString(2, contact.getNumber());
        stmt.setString(3, contact.getEmail());
        stmt.setInt(4, contact.getId());
        
        stmt.executeUpdate();
        connect().close();
        
    }

    public void delete(Bean contact) throws SQLException {
        
        String sql = "delete from contacts where id=?";
        
        PreparedStatement stmt = connect().prepareStatement(sql);
        
        stmt.setInt(1, contact.getId());
        
        stmt.executeUpdate();
        connect().close();
    }
}
