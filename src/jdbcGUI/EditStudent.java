package jdbcGUI;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


/**
 * Created by Shibu on 5/30/2015.
 */
public class EditStudent extends JFrame implements ActionListener{
    JFrame frame = new JFrame();
    JLabel label0, label1, label2, label3, label4, label5, label6, label7;
    JTextField id, fname, lname, phone, email, user, pw;
    JButton selectButton, updateButton, deleteButton, exitButton;
    JLabel iconLabel1, iconLabel2;

    public EditStudent(){
        frame.setTitle("PeopleNTech");

        Icon icon1 = new ImageIcon("images/logo1.png");
        Icon icon2 = new ImageIcon("images/logo2.png");

        iconLabel1 = new JLabel();
        iconLabel1.setIcon(icon1);
        iconLabel1.setBounds(100, 40, 300, 160);

        iconLabel2 = new JLabel();
        iconLabel2.setIcon(icon2);
        iconLabel2.setBounds(400, 40, 400, 160);

        label0 = new JLabel("Enter id");
        label0.setForeground(Color.RED);
        label0.setFont(new Font("Ariel", Font.BOLD, 14));
        label0.setBounds(300, 300, 208, 24);
        id = new JTextField(15);
        id.setBounds(420, 300, 158, 24);

        label1 = new JLabel("First Name");
        label1.setBounds(300, 400, 208, 24);
        label1.setForeground(new Color(0, 0, 155));
        label1.setFont(new Font("Ariel", Font.BOLD, 14));
        fname = new JTextField(15);
        fname.setBounds(420, 400, 158, 24);
        fname.setEditable(false);

        label2 = new JLabel("Last Name");
        label2.setBounds(300, 440, 208, 24);
        label2.setForeground(new Color(0, 0, 155));
        label2.setFont(new Font("Ariel", Font.BOLD, 14));
        lname = new JTextField(15);
        lname.setBounds(420, 440, 158, 24);
        lname.setEditable(false);

        label3 = new JLabel("Phone");
        label3.setBounds(300, 480, 208, 24);
        label3.setForeground(new Color(0, 0, 155));
        label3.setFont(new Font("Ariel", Font.BOLD, 14));
        phone = new JTextField(15);
        phone.setBounds(420, 480, 158, 24);
        phone.setEditable(false);

        label4 = new JLabel("Email Address");
        label4.setBounds(300, 520, 208, 24);
        label4.setForeground(new Color(0, 0, 155));
        label4.setFont(new Font("Ariel", Font.BOLD, 14));
        email = new JTextField(15);
        email.setBounds(420, 520, 158, 24);
        email.setEditable(false);

        label5 = new JLabel("User name");
        label5.setBounds(300, 560, 208, 24);
        label5.setForeground(new Color(0, 0, 155));
        label5.setFont(new Font("Ariel", Font.BOLD, 14));
        user = new JTextField(15);
        user.setBounds(420, 560, 158, 24);
        user.setEditable(false);

        label6 = new JLabel("Password");
        label6.setBounds(300, 600, 208, 24);
        label6.setForeground(new Color(0, 0, 155));
        label6.setFont(new Font("Ariel", Font.BOLD, 14));
        pw = new JTextField(15);
        pw.setBounds(420, 600, 158, 24);
        pw.setEditable(false);

        selectButton = new JButton("Select");
        selectButton.setBounds(600, 300, 80, 24);
        selectButton.addActionListener(EditStudent.this);
        selectButton.setForeground(new Color(205, 0, 205));
        selectButton.setBackground(new Color(205, 255, 205));

        updateButton = new JButton("Update");
        updateButton.setBounds(400, 700, 80, 24);
        updateButton.addActionListener(EditStudent.this);
        updateButton.setForeground(new Color(205, 0, 205));
        updateButton.setBackground(new Color(205, 255, 205));

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(500, 700, 80, 24);
        deleteButton.addActionListener(EditStudent.this);
        deleteButton.setForeground(new Color(205, 0, 205));
        deleteButton.setBackground(new Color(205, 255, 205));

        exitButton = new JButton("Exit");
        exitButton.setBounds(600, 700, 80, 24);
        exitButton.addActionListener(EditStudent.this);
        exitButton.setForeground(new Color(205, 0, 205));
        exitButton.setBackground(new Color(205, 255, 205));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        label7 = new JLabel("");
        label7.setBounds(300, 800, dimension.width - 300, 24);
        label7.setForeground(new Color(0, 0, 155));
        label7.setFont(new Font("Ariel", Font.BOLD, 14));

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, dimension.width, dimension.height);
        mainPanel.setLayout(null);
        mainPanel.add(iconLabel1);
        mainPanel.add(iconLabel2);
        mainPanel.add(label0);
        mainPanel.add(id);
        mainPanel.add(label1);
        mainPanel.add(fname);
        mainPanel.add(label2);
        mainPanel.add(lname);
        mainPanel.add(label3);
        mainPanel.add(phone);
        mainPanel.add(label4);
        mainPanel.add(email);
        mainPanel.add(label5);
        mainPanel.add(user);
        mainPanel.add(label6);
        mainPanel.add(pw);
        mainPanel.add(selectButton);
        mainPanel.add(updateButton);
        mainPanel.add(deleteButton);
        mainPanel.add(exitButton);
        mainPanel.add(label7);
        mainPanel.setBackground(new Color(205, 255, 255));

        Container c = frame.getContentPane();
        c.setLayout(null);
        c.add(mainPanel);
        c.setVisible(true);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== selectButton){
            if(id.getText().equals("")){
                JOptionPane.showMessageDialog(null,
                        "Entries Empty! Fill in the required entries to view data.",
                        "Entries Empty", JOptionPane.PLAIN_MESSAGE);
            }
            else{
                id.setEditable(false);
                fname.setEditable(true);
                lname.setEditable(true);
                phone.setEditable(true);
                email.setEditable(true);
                user.setEditable(true);
                pw.setEditable(true);
                selectData();
            }
        }

        if (e.getSource()== updateButton){
            if(fname.getText().equals("") || lname.getText().equals("") || phone.getText().equals("") ||
                    email.getText().equals("") || user.getText().equals("") || pw.getText().equals("")){
                JOptionPane.showMessageDialog(null,
                        "Entries Empty! Fill in the required entries to update data.",
                        "Entries Empty", JOptionPane.PLAIN_MESSAGE);
            }
            else{
                updateData();
            }
        }

        if (e.getSource()== deleteButton){
            if(id.getText().equals("") || fname.getText().equals("") || lname.getText().equals("") || phone.getText().equals("") ||
                    email.getText().equals("") || user.getText().equals("") || pw.getText().equals("")){
                JOptionPane.showMessageDialog(null,
                        "Entries Empty! Fill in the required entries to update data.",
                        "Entries Empty", JOptionPane.PLAIN_MESSAGE);
            }
            else{
                deleteData();
            }
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

    public void selectData(){

        label7.setText("");
        try{

            String dburl = "jdbc:mysql:///peoplentech";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(dburl, "root", "sddev");
            Statement stmt = con.createStatement();

            PreparedStatement ps = con.prepareStatement("select firstname, lastname, phone, email, username, password from students where id = ?");
            ps.setString(1, id.getText());


            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                fname.setText(rs.getString("firstname"));
                lname.setText(rs.getString("lastname"));
                phone.setText(rs.getString("phone"));
                email.setText(rs.getString("email"));
                user.setText(rs.getString("username"));
                pw.setText(rs.getString("password"));

            }
        }
        catch (Exception e){
            label7.setText("Information has not been found " );
        }

    }

    public void updateData(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            String dburl = "jdbc:mysql:///peoplentech";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(dburl, "root", "sddev");
            stmt = con.createStatement();

            String updateStudent =
                    "update students set firstname = ?, lastname = ?, phone = ?, email = ?, username = ?, password = ? where id = ?";
            PreparedStatement ps = con.prepareStatement(updateStudent);
            ps.setString(1, fname.getText());
            ps.setString(2, lname.getText());
            ps.setString(3, phone.getText());
            ps.setString(4, email.getText());
            ps.setString(5, user.getText());
            ps.setString(6, pw.getText());
            ps.setString(7, id.getText());

            int count = ps.executeUpdate();

            id.setText("");
            fname.setText("");
            lname.setText("");
            phone.setText("");
            email.setText("");
            user.setText("");
            pw.setText("");
            id.setEditable(true);
            label7.setText("Information has been updated");
        }
        catch (Exception e){
            label7.setText("Information has not been updated" );
        }
    }

    public void deleteData(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            String dburl = "jdbc:mysql:///peoplentech";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(dburl, "root", "sddev");
            stmt = con.createStatement();

            String deleteStudent =
                    "delete from students where id = ?";
            PreparedStatement ps = con.prepareStatement(deleteStudent);
            ps.setString(1, id.getText());

            int count = ps.executeUpdate();

            id.setText("");
            fname.setText("");
            lname.setText("");
            phone.setText("");
            email.setText("");
            user.setText("");
            pw.setText("");
            id.setEditable(true);
            label7.setText("Information has been updated");
        }
        catch (Exception e){
            label7.setText("Information has not been updated" );
        }
    }

    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){}

        new EditStudent();
    }
}
