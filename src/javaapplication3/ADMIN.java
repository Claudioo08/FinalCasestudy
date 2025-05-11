/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication3;
import java.sql.*;
import javax.swing.*;

public class ADMIN {
    static final String DB_URL = "jdbc:derby:CustomerDB;create=true";

    public static void main(String[] args) {
        System.setProperty("derby.system.home", "derby_data");
        createTableIfNotExists();
        createDefaultAdmin();

        String[] options = {"Register", "Login", "View Customers"};
        int choice = JOptionPane.showOptionDialog(null, "Choose an option", "Customer System",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> registerCustomer();
            case 1 -> loginCustomer();
            case 2 -> viewCustomers();
        }
    }

    private static void createTableIfNotExists() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE CUSTOMERS (" +
                    "EMAIL VARCHAR(100) PRIMARY KEY, " +
                    "FIRSTNAME VARCHAR(50), " +
                    "LASTNAME VARCHAR(50), " +
                    "PASSWORD VARCHAR(50), " +
                    "ROLE VARCHAR(10))";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            if (!"X0Y32".equals(e.getSQLState())) {
                JOptionPane.showMessageDialog(null, "Error creating table: " + e.getMessage());
            }
        }
    }

    private static void createDefaultAdmin() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String checkSql = "SELECT EMAIL FROM CUSTOMERS WHERE EMAIL = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, "admin@example.com");
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                String insertSql = "INSERT INTO CUSTOMERS (EMAIL, FIRSTNAME, LASTNAME, PASSWORD, ROLE) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setString(1, "admin@example.com");
                insertStmt.setString(2, "Admin");
                insertStmt.setString(3, "User");
                insertStmt.setString(4, "adminpassword");
                insertStmt.setString(5, "admin");
                insertStmt.executeUpdate();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error creating admin: " + e.getMessage());
        }
    }

    private static void registerCustomer() {
        String fname = JOptionPane.showInputDialog("Enter First Name:");
        String lname = JOptionPane.showInputDialog("Enter Last Name:");
        String email = JOptionPane.showInputDialog("Enter Email:");
        String password = JOptionPane.showInputDialog("Enter Password:");

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO CUSTOMERS (EMAIL, FIRSTNAME, LASTNAME, PASSWORD, ROLE) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, fname);
            ps.setString(3, lname);
            ps.setString(4, password);
            ps.setString(5, "customer");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Customer registered successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Registration failed: " + e.getMessage());
        }
    }

    private static void loginCustomer() {
        String email = JOptionPane.showInputDialog("Enter Email:");
        String password = JOptionPane.showInputDialog("Enter Password:");

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT * FROM CUSTOMERS WHERE EMAIL=? AND PASSWORD=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String fname = rs.getString("FIRSTNAME");
                String role = rs.getString("ROLE");
                JOptionPane.showMessageDialog(null, "Welcome, " + fname + "! You are logged in as " + role);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid email or password.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Login failed: " + e.getMessage());
        }
    }

    private static void viewCustomers() {
        StringBuilder sb = new StringBuilder("Registered Customers:\n\n");

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT FIRSTNAME, LASTNAME, EMAIL FROM CUSTOMERS WHERE LOWER(ROLE) = 'customer'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                sb.append(rs.getString("FIRSTNAME")).append(" ")
                  .append(rs.getString("LASTNAME")).append(" - ")
                  .append(rs.getString("EMAIL")).append("\n");
            }

            if (sb.toString().equals("Registered Customers:\n\n")) {
                sb.append("No customers found.");
            }

            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to retrieve customers: " + e.getMessage());
        }
    }
}
/**
 *
 * @author ryy
 */
public class ADMIN extends javax.swing.JFrame {

    /**
     * Creates new form ADMIN
     */
    public ADMIN() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ADMIN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
