package jdbcGUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;


/**
 * Created by Shibu on 5/30/2015.
 */
public class EntryNewStudent extends JFrame implements ActionListener{
    JFrame frame = new JFrame();
    JLabel iconLabel1, iconLabel2;
    JLabel labelId, labelFirstname, labelLastname, labelPhone, labelEmail, labelUsername, labelPassword;
    JTextField id, fname, lname, phone, email, username, password;
    JButton submitButton, clearButton, exitButton;

    public EntryNewStudent(){
        frame.setTitle("PeopleNTech");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        Icon icon1 = new ImageIcon("images/logo1.png");
        Icon icon2 = new ImageIcon("images/logo2.png");

        iconLabel1 = new JLabel();
        iconLabel1.setIcon(icon1);
        iconLabel1.setBounds(300, 40, 300, 160);

        iconLabel2 = new JLabel();
        iconLabel2.setIcon(icon2);
        iconLabel2.setBounds(600, 40, 400, 160);

        labelId = new JLabel("Id");
        labelId.setBounds( 300, 300, 500, 24 );
        labelId.setForeground(new Color(0,0,155));
        labelId.setFont(new Font("Ariel", Font.BOLD, 14));
        id = new JTextField(15);
        id.setBounds(420, 300, 158, 24);

        labelFirstname = new JLabel("First Name");
        labelFirstname.setBounds(300, 340, 208, 24);
        labelFirstname.setForeground(new Color(0,0,155));
        labelFirstname.setFont(new Font("Ariel", Font.BOLD, 14));
        fname = new JTextField(15);
        fname.setBounds(420, 340, 158, 24);

        labelLastname = new JLabel("Last Name");
        labelLastname.setBounds(300, 380, 208, 24);
        labelLastname.setForeground(new Color(0,0,155));
        labelLastname.setFont(new Font("Ariel", Font.BOLD, 14));
        lname = new JTextField(15);
        lname.setBounds(420, 380, 158, 24);

        labelPhone = new JLabel("Phone");
        labelPhone.setBounds(300, 420, 208, 24);
        labelPhone.setForeground(new Color(0,0,155));
        labelPhone.setFont(new Font("Ariel", Font.BOLD, 14));
        phone = new JTextField(15);
        phone.setBounds(420, 420, 158, 24);

        labelEmail = new JLabel("Email Address");
        labelEmail.setBounds(300, 460, 208, 24);
        labelEmail.setForeground(new Color(0,0,155));
        labelEmail.setFont(new Font("Ariel", Font.BOLD, 14));
        email = new JTextField(15);
        email.setBounds(420, 460, 158, 24);

        labelUsername = new JLabel("Username");
        labelUsername.setBounds(300, 500, 208, 24);
        labelUsername.setForeground(new Color(0,0,155));
        labelUsername.setFont(new Font("Ariel", Font.BOLD, 14));
        username = new JTextField(15);
        username.setBounds(420, 500, 158, 24);

        labelPassword = new JLabel("Password");
        labelPassword.setBounds(300, 540, 208, 24);
        labelPassword.setForeground(new Color(0,0,155));
        labelPassword.setFont(new Font("Ariel", Font.BOLD, 14));
        password = new JTextField(15);
        password.setBounds(420, 540, 158, 24);

        submitButton = new JButton("Submit");
        submitButton.setBounds( 300, 600, 80, 24 );
        submitButton.addActionListener(EntryNewStudent.this);
        submitButton.setForeground(new Color(205,0,205));
        submitButton.setBackground(new Color(205,255,205));

        clearButton = new JButton("Clear");
        clearButton.setBounds( 400, 600, 80, 24 );
        clearButton.addActionListener(EntryNewStudent.this);
        clearButton.setForeground(new Color(205,0,205));
        clearButton.setBackground(new Color(205,255,205));

        exitButton = new JButton("Exit");
        exitButton.setBounds( 500, 600, 80, 24 );
        exitButton.addActionListener(EntryNewStudent.this);
        exitButton.setForeground(new Color(205,0,205));
        exitButton.setBackground(new Color(205,255,205));

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds( 0, 0, dimension.width, dimension.height );
        mainPanel.setLayout( null );
        mainPanel.add(iconLabel1);
        mainPanel.add(iconLabel2);
        mainPanel.add(labelId);
        mainPanel.add(id);
        mainPanel.add(labelFirstname);
        mainPanel.add(fname);
        mainPanel.add(labelLastname);
        mainPanel.add(lname);
        mainPanel.add(labelPhone);
        mainPanel.add(phone);
        mainPanel.add(labelEmail);
        mainPanel.add(email);
        mainPanel.add(labelUsername);
        mainPanel.add(username);
        mainPanel.add(labelPassword);
        mainPanel.add(password);
        mainPanel.add( submitButton );
        mainPanel.add( clearButton );
        mainPanel.add( exitButton );
        mainPanel.setBackground(new Color(205,255,255));

        Container c = frame.getContentPane();
        c.setLayout( null );
        c.add( mainPanel );

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible( true );
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== submitButton){
            insertData();
        }
        if (e.getSource()== clearButton){
            id.setText("");
            fname.setText("");
            lname.setText("");
            phone.setText("");
            email.setText("");
            username.setText("");
            password.setText("");
        }
        if (e.getSource()== exitButton){
            try {
                new ViewStudents();
            } catch (Exception exp) {
                exp.printStackTrace();
            }
            frame.dispose();
        }
    }

    public void insertData(){
        Connection con = null;
        Statement stmt = null;

        try{
            String dburl = "jdbc:mysql:///peoplentech";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(dburl, "root", "sddev");
            stmt = con.createStatement();

            String insertStudent =
                    "insert into Students(id, firstname, lastname, phone, email, username, password) "
                            + "values(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(insertStudent);
            ps.setString(1, id.getText());
            ps.setString(2, fname.getText());
            ps.setString(3, lname.getText());
            ps.setString(4, phone.getText());
            ps.setString(5, email.getText());
            ps.setString(6, username.getText());
            ps.setString(7, password.getText());
            ps.executeUpdate();

            if(id.getText().equals("") ||fname.getText().equals("") || lname.getText().equals("") ||
                    phone.getText().equals("") || email.getText().equals("") ||
                    username.getText().equals("") || password.getText().equals("") ){
                JOptionPane.showMessageDialog(null,
                        "Entries Empty! Fill in the required entries to save Contact.",
                        "Entries Empty", JOptionPane.INFORMATION_MESSAGE);
            }

            else{
                JOptionPane.showMessageDialog(null, "You have successfully inserted data",
                        "Successful!", JOptionPane.PLAIN_MESSAGE);

                id.setText("");
                fname.setText("");
                lname.setText("");
                phone.setText("");
                email.setText("");
                username.setText("");
                password.setText("");
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.toString(),
                    "Problem: ", JOptionPane.PLAIN_MESSAGE);
        }

    }

    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){}

        new EntryNewStudent();
    }
}
