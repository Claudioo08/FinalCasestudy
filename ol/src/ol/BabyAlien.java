/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ol;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
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
    
    public String[][] getAllProducts() {
    List<String[]> products = new ArrayList<>();
    try {
        wasd(); // Initialize connection
        String sql = "SELECT * FROM CART";
        rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            String[] product = new String[3];
            product[0] = rs.getString("SHOENAME");
            product[1] = "₱" + rs.getString("PRICE");
            product[2] = rs.getString("STOCK");
            products.add(product);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return products.toArray(new String[0][0]);
}
    public String[] getFirstProductDetails() {
    String[] productData = new String[3]; // [Shoename, Price, Stock]
    try {
        wasd(); // Ensure data is loaded
        if (rs.next()) {
            productData[0] = rs.getString("SHOENAME");
            productData[1] = rs.getString("PRICE");
            productData[2] = rs.getString("STOCK");
        }
        rs.close();
        stmt.close();
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return productData;
    }
    
}
