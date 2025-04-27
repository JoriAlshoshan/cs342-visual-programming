
package projectlibarary;


public class User {
  
    int userID;
    String username;
    String usertype;
    String password;
    String contactInfo;

    public User(int userID, String username, String contactInfo, String password,String usertype) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.contactInfo = contactInfo;
        this.usertype=usertype;
    }

    public int getUserID() {
        return userID;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getUsertype() {
        return usertype;
    }

   
    
    
}

