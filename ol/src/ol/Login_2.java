package ol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Login_2 {

    private final String url = "jdbc:derby://localhost:1527/Database_Login";
    private final String user = "Renzyx29";
    private final String pass = "Renzyx29";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    public List<String[]> getAllUsers() {
        List<String[]> users = new ArrayList<>();
        String query = "SELECT * FROM LOGIN_2";
        try (
            Connection con = connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                users.add(new String[]{
                    rs.getString("FIRSTNAME"),
                    rs.getString("LASTNAME"),
                    rs.getString("EMAIL"),
                    rs.getString("PASSWORD")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean addUser(String fname, String lname, String email, String password) {
        String query = "INSERT INTO LOGIN_2 (FIRSTNAME, LASTNAME, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)";
        try (
            Connection con = connect();
            PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




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

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

