package ol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Login extends JFrame {

    JTable jTable1;
    DefaultTableModel model;
    Login_2 db;

    public Login() {
        db = new Login_2();
        initComponents();
        loadUsers();
    }

    private void initComponents() {
        setTitle("LOGIN_2 User Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(new String[]{"FIRSTNAME", "LASTNAME", "EMAIL", "PASSWORD"}, 0);
        jTable1 = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(jTable1);

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(e -> addUser());

        JPanel panel = new JPanel();
        panel.add(addUserButton);

        add(scrollPane, "Center");
        add(panel, "South");
    }

    private void loadUsers() {
        model.setRowCount(0);
        List<String[]> users = db.getAllUsers();
        for (String[] user : users) {
            model.addRow(user);
        }
    }

    private void addUser() {
        String fname = JOptionPane.showInputDialog(this, "Enter First Name:");
        String lname = JOptionPane.showInputDialog(this, "Enter Last Name:");
        String email = JOptionPane.showInputDialog(this, "Enter Email:");
        String password = JOptionPane.showInputDialog(this, "Enter Password:");

        if (fname != null && lname != null && email != null && password != null) {
            boolean success = db.addUser(fname, lname, email, password);
            if (success) {
                JOptionPane.showMessageDialog(this, "User added successfully.");
                loadUsers();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add user.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
