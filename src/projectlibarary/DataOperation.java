package projectlibarary;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataOperation {
    
    public static boolean ValiditUser(String user_name,String pass) {
        
        try { 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            String url="jdbc:ucanaccess://" +"DB\\DatabaseFinal.accdb";
            Connection conx= DriverManager.getConnection(url);
            Statement stmt=conx.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM Users WHERE Username = '"+user_name+ "' AND Password = '" +pass+"';");
            conx.close();
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
        
        public static String GetUserType(String user_name){
            try{
                
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

                String url="jdbc:ucanaccess://" +"DB\\DatabaseFinal.accdb";
                Connection conx= DriverManager.getConnection(url);
                Statement stmt=conx.createStatement();
                ResultSet rs=stmt.executeQuery("SELECT UserType FROM Users WHERE Username = '"+user_name+ "';");
                String user_type = null;

                while(rs.next()){
                user_type=rs.getString("UserType");
                }
                return user_type;
            }
            catch (ClassNotFoundException ex) {
            Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        
               public static int GetUserID(String user_name){
            try{
                
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

                String url="jdbc:ucanaccess://" +"DB\\DatabaseFinal.accdb";
                Connection conx= DriverManager.getConnection(url);
                Statement stmt=conx.createStatement();
                ResultSet rs=stmt.executeQuery("SELECT UserID FROM Users WHERE Username = '"+user_name+ "';");
                int user_type = 0;

                while(rs.next()){
                user_type=rs.getInt("UserID");
                }
                return user_type;
            }
            catch (ClassNotFoundException ex) {
            Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
            public static String GetUsername(int userid){
            try{
                
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

                String url="jdbc:ucanaccess://" +"DB\\DatabaseFinal.accdb";
                Connection conx= DriverManager.getConnection(url);
                Statement stmt=conx.createStatement();
                ResultSet rs=stmt.executeQuery("SELECT Username FROM Users WHERE UserID = '"+userid+ "';");
                String user_name = null;

                while(rs.next()){
                user_name=rs.getString("Username");
                }
                return user_name;
            }
            catch (ClassNotFoundException ex) {
            Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        public static boolean AddNewUser(String username, String user_email, String password){
             try { 
           
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            String url="jdbc:ucanaccess://" +"DB\\DatabaseFinal.accdb";
            Connection conx= DriverManager.getConnection(url);
            
            Statement selectQuery = conx.createStatement();
            ResultSet rs = selectQuery.executeQuery("SELECT * FROM Users ORDER BY UserID DESC;");
            rs.next();
            int lastUserID = rs.getInt("UserID");
            int newUserID = lastUserID + 1;
            Statement stmt=conx.createStatement();
            
            String user_type = "User";

            stmt.executeUpdate("INSERT INTO Users (UserID, Username, Password, ContactInfo, UserType) VALUES ('"+newUserID+"', '"+username+"','"+password+"','"+user_email+"','"+user_type+"');") ;
            conx.close();
            return true;
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
               return false;
        } catch (SQLException ex) {
            Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
               return false;

        }
        }
        
        public static User[] getUserInfo() {
        User[] users = null;
        Connection conx = null;
        try { 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String url="jdbc:ucanaccess://" +"DB\\DatabaseFinal.accdb";
            conx= DriverManager.getConnection(url);
            Statement countStmt = conx.createStatement();
            ResultSet countResult = countStmt.executeQuery("SELECT COUNT(*) AS Users_Count FROM Users;");
            countResult.next();
            int arraySize = Integer.parseInt(countResult.getString("Users_Count"));
            users = new User[arraySize];
            
            Statement stmt=conx.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT UserID,Username,Password,ContactInfo,UserType FROM Users; ");

            int index = 0;
            User user = null;
            while (rs.next()) {
            int userID = rs.getInt("UserID");
            String username = rs.getString("Username");
            String password = rs.getString("Password"); 
            String contactInfo = rs.getString("ContactInfo");
            String usertype = rs.getString("UserType");

            users[index] = new User(userID, username, contactInfo, password,usertype);
            index++;
         }
             conx.close();
             return users;
        }
           catch (ClassNotFoundException ex) {
            Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
            return users;

           } catch (SQLException ex) {
            Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
            return users;
             }
        


        }
         public static boolean upadateUserInfo(User user) {
               try { 
           
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            String url="jdbc:ucanaccess://" +"DB\\DatabaseFinal.accdb";
            Connection conx= DriverManager.getConnection(url);
            Statement stmt=conx.createStatement();
            
             stmt.executeUpdate("UPDATE Users SET Username = '"+user.getUsername()+"', Password = '"+user.password+"', ContactInfo = '"+user.getContactInfo()+"', UserType = '"+ user.getUsertype()+"' WHERE UserID = '"+ user.getUserID()+"';") ;
             conx.close();
             return true;
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
               return false;
        } catch (SQLException ex) {
            Logger.getLogger(DataOperation.class.getName()).log(Level.SEVERE, null, ex);
               return false;

        }
         }
}
        

