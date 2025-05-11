/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ryy
 */
public class BabyAlien extends javax.swing.JFrame { 
    Connection con;
    Statement stmt;
    ResultSet rs;
    DefaultTableModel loginmodel = new DefaultTableModel();
    int ShoeID, Shoesize;
    String Shoename; 
    int Stock;
    double Price;
   
    public void wasd(){
        try{
            String host = "jdbc:derby://localhost:1527/Database";
            String newname = "Renzyx28";
            String newpass = "Renzyx28";
            con = DriverManager.getConnection(host, newname, newpass);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM CART";
            rs = stmt.executeQuery(sql);           
        } catch(SQLException a){
            JOptionPane.showMessageDialog(BabyAlien.this, a.getMessage());
            
        }
    }
    public void refreshruvic(){
        try{
            stmt.close();
            rs.close();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM CART";
            rs = stmt.executeQuery(sql);   
        } catch(SQLException ex){
            Logger.getLogger(BabyAlien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
