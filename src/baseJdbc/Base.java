package baseJdbc;

import java.sql.*;

/**
 * Created by Shibu on 5/30/2015.
 */
public class Base {
    private Connection con;
    Statement stmt=null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    int affected=0;

    public Base(){
        this.connectDB();
    }

    private void connectDB() {
        try{
            String dbClass = "com.mysql.jdbc.Driver";
            String dburl="jdbc:mysql:///peoplentech";
            String userid="root";
            String password="sddev";

            Class.forName(dbClass).newInstance();
            con=DriverManager.getConnection(dburl,userid,password);

        /*    if(con != null){
                System.out.println("A database connection has been established");
            }*/
        }
        catch(Exception e){
            System.out.println("Problem: database connection has not been established");
        }
    }

    public void insertData(String id, String fname, String lname, String phone, String email, String user, String pw){
        try{

            String result="insert into students values(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(result);
            ps.setString(1, id);
            ps.setString(2, fname);
            ps.setString(3, lname);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.setString(6, user);
            ps.setString(7, pw);
            ps.executeUpdate();
            ps.close();
        }catch(Exception e){
            System.out.println("Problem: " + e.toString());
        }

    }

    public void selectData(){
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery("select * from students");

            System.out.println("Id\tName\t\tEmail Address\t\tPhone\t\tUserId\tPassword");
            System.out.println("==\t====\t\t=============\t\t=====\t\t======\t========");
            while(rs.next()){

                String id=rs.getString("id");
                String fname=rs.getString("firstname");
                String lname = rs.getString("lastname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String userid = rs.getString("username");
                String password = rs.getString("password");

                System.out.println(id+"\t"+fname+" "+lname+"\t"+email+"\t"+phone+"\t"+userid+"\t"+password);

            }
            rs.close();
        } catch(Exception e){
            System.out.println("Problem: " + e.toString());
        }

    }

    public void selectData(String studentId){
        try{

            String query = "select * from students where id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, studentId);
            rs = ps.executeQuery();
            System.out.println("Id\tName\t\tEmail Address\t\tPhone\t\tUserId\tPassword");
            System.out.println("==\t====\t\t=============\t\t=====\t\t======\t========");
            while(rs.next()){

                String id=rs.getString("id");
                String fname=rs.getString("firstname");
                String lname = rs.getString("lastname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String userid = rs.getString("username");
                String password = rs.getString("password");

                System.out.println(id+"\t"+fname+" "+lname+"\t"+email+"\t"+phone+"\t"+userid+"\t"+password);

            }
            rs.close();
            ps.close();
        } catch(Exception e){
            System.out.println("Problem: " + e.toString());
        }

    }

    public void deleteData(String studentId){
        try{
            String delete = "delete from students where id = ?";
            ps = con.prepareStatement(delete);
            ps.setString(1, studentId);
            ps.executeUpdate();
            ps.close();
        }catch(Exception e){
            System.out.println("Problem: " + e.toString());
        }
    }

    public void updateData(String phone, String id){
        try{
            String update = "update students set phone = ? where id = ?";
            ps = con.prepareStatement(update);
            ps.setString(1, phone);
            ps.setString(2, id);
            ps.executeUpdate();
            ps.close();
        }catch(Exception e){
            System.out.println("Problem: " + e.toString());
        }
    }
}
