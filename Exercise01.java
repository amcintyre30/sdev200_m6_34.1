import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Author: Aubrie McIntyre
 * Date: 9/26/2024
 * Assignment: SDEV200 - Java - Module 6 Programming Assignment 1 - 34.1
 */

public class Exercise01 extends JApplet {
    private static final long serialVersionUID = 1L;
    private JTextField jTextField1 = new JTextField(10);
    private JTextField jTextField2 = new JTextField(10);
    private JTextField jTextField3 = new JTextField(10);
    private JTextField jTextField4 = new JTextField(3);
    private JTextField jTextField5 = new JTextField(10);
    private JTextField jTextField6 = new JTextField(10);
    private JTextField jTextField7 = new JTextField(3);
    private JTextField jTextField8 = new JTextField(10);
    private JTextField jTextField9 = new JTextField(10);

    private Connection connection;

    public void init() {
        setLayout(new BorderLayout());
        initializeDB();

        // Create a panel for staff information with a titled border
        JPanel jPanel1 = new JPanel(new FlowLayout());
        jPanel1.setBorder(new TitledBorder("Staff information"));
        jPanel1.add(new JLabel("ID"));
        jPanel1.add(jTextField1);
        jPanel1.add(new JLabel("Last Name"));
        jPanel1.add(jTextField2);
        jPanel1.add(new JLabel("First Name"));
        jPanel1.add(jTextField3);
        jPanel1.add(new JLabel("mi"));
        jPanel1.add(jTextField4);
        jPanel1.add(new JLabel("Address"));
        jPanel1.add(jTextField5);
        jPanel1.add(new JLabel("City"));
        jPanel1.add(jTextField6);
        jPanel1.add(new JLabel("State"));
        jPanel1.add(jTextField7);
        jPanel1.add(new JLabel("Telephone"));
        jPanel1.add(jTextField8);
        jPanel1.add(new JLabel("E-mail"));
        jPanel1.add(jTextField9);
        add(jPanel1, BorderLayout.CENTER);

        // Create a panel for buttons with GridLayout
        JPanel jPanel2 = new JPanel(new GridLayout(1, 4, 5, 5));
        jPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Create and add buttons to the panel
        JButton jButton1 = new JButton("View");
        jPanel2.add(jButton1);
        JButton jButton2 = new JButton("Insert");
        jPanel2.add(jButton2);
        JButton jButton3 = new JButton("Update");
        jPanel2.add(jButton3);
        JButton jButton4 = new JButton("Clear");
        jPanel2.add(jButton4);

        // Add the button panel to the bottom of the applet
        add(jPanel2, BorderLayout.SOUTH);
  
  // ActionListener for View button
jButton1.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent aerg0) {
        // Define SQL query for selecting staff information based on ID
        String queryString = "select lastName, firstName, mi, address, city, state, telephone, email from Staff where id = ?;";
        try {
            // Prepare the SQL statement
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, jTextField1.getText());

            // Execute the query
            ResultSet rset = preparedStatement.executeQuery();

            // Check if a result is found and populate text fields accordingly
            if (rset.next()) {
                jTextField2.setText(rset.getString(1));
                jTextField3.setText(rset.getString(2));
                jTextField4.setText(rset.getString(3));
                jTextField5.setText(rset.getString(4));
                jTextField6.setText(rset.getString(5));
                jTextField7.setText(rset.getString(6));
                jTextField8.setText(rset.getString(7));
                jTextField9.setText(rset.getString(8));
            } else {
                // No matching record found, clear text fields
                jTextField2.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                jTextField6.setText("");
                jTextField7.setText("");
                jTextField8.setText("");
                jTextField9.setText("");
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }
});

// ActionListener for Insert button
jButton2.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Define SQL query for inserting a new staff record
        String queryString = "insert into Staff (id, lastName, firstName, mi, address, city, state, telephone, email) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            // Prepare the SQL statement
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, jTextField1.getText());
            preparedStatement.setString(2, jTextField2.getText());
            preparedStatement.setString(3, jTextField3.getText());
            preparedStatement.setString(4, jTextField4.getText());
            preparedStatement.setString(5, jTextField5.getText());
            preparedStatement.setString(6, jTextField6.getText());
            preparedStatement.setString(7, jTextField7.getText());
            preparedStatement.setString(8, jTextField8.getText());
            preparedStatement.setString(9, jTextField9.getText());

            // Execute the SQL insert statement
            preparedStatement.executeUpdate();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }
});

// ActionListener for Update button
jButton3.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Define SQL query for updating an existing staff record based on ID
        String queryString = "update Staff set lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? where id = ?";
    try {
            // Prepare the SQL statement for updating
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, jTextField2.getText());
            preparedStatement.setString(2, jTextField3.getText());
            preparedStatement.setString(3, jTextField4.getText());
            preparedStatement.setString(4, jTextField5.getText());
            preparedStatement.setString(5, jTextField6.getText());
            preparedStatement.setString(6, jTextField7.getText());
            preparedStatement.setString(7, jTextField8.getText());
            preparedStatement.setString(8, jTextField9.getText());
            preparedStatement.setString(9, jTextField1.getText());

            // Execute the SQL update statement
            preparedStatement.executeUpdate();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }
});

// ActionListener for Clear button
jButton4.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Create a statement for deleting all records from the Staff table
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from Staff;");

            // Clear all text fields
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");
            jTextField9.setText("");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }
});
    }
  // Method to initialize the database connection
private void initializeDB() {
    try {
        // Load the MySQL JDBC driver class
        Class.forName("com.mysql.jdbc.Driver");

        // Establish a connection to the MySQL database
        connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "root", "root");
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

/** Main method */
public static void main(String[] args) {
    // Create an instance of the Exercise01 applet
    Exercise01 applet = new Exercise01();

    // Create a JFrame for the applet
    JFrame frame = new JFrame();

    // Set the default close operation for the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Set the title for the frame
    frame.setTitle("Exercise01");

    // Add the applet to the content pane of the frame
    frame.getContentPane().add(applet, BorderLayout.CENTER);

    // Initialize the applet
    applet.init();

    // Start the applet
    applet.start();

    // Set the size of the frame
    frame.setSize(540, 200);

    // Center the frame on the screen
    frame.setLocationRelativeTo(null);

    // Make the frame visible
    frame.setVisible(true);
}
}