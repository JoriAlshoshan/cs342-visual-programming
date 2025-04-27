
package projectlibarary;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class ViewInfo extends JFrame{
   JLabel user_name,user_email,pass_user,icon_label_1,usertype;
   JComboBox type;
   JTextField name,email;
   JPasswordField pass;
   JButton update,back;
   User selectedUser;
   
   public ViewInfo(User user){
        selectedUser = user;
        this.setTitle("View Info for "+ user.getUsername());
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        JPanel p = (JPanel) getContentPane();
        p.setLayout(new BorderLayout());
        p.setBackground(new java.awt.Color(216, 210, 194));
        setLocationRelativeTo(null);
        
        JPanel top_panel = new JPanel();
        top_panel.setLayout(new FlowLayout());
        icon_label_1 = new JLabel(new ImageIcon("img\\login_screen_icon.png"));
        top_panel.add(icon_label_1);
        
        JPanel center_panel = new JPanel();
        center_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        center_panel.setBorder(BorderFactory.createEmptyBorder(0, 0, -100, 0));
        JPanel right = new JPanel();
        right.setLayout(new GridLayout(4,2,-100,20));
        
        user_name=new JLabel("User name: ");
        name=new JTextField(user.getUsername(),30);
        //name.setEditable(false);
        name.setBackground(new java.awt.Color(250,247,240));
        name.setBorder(BorderFactory.createEmptyBorder());
        
         user_email=new JLabel("Email: ");
         email=new JTextField(user.getContactInfo(),30);
         email.setBackground(new java.awt.Color(250,247,240));
         email.setBorder(BorderFactory.createEmptyBorder());

        
         pass_user=new JLabel("Password: ");
         pass=new JPasswordField(user.getPassword(),30);
         pass.setBackground(new java.awt.Color(250,247,240));
         pass.setBorder(BorderFactory.createEmptyBorder());
         
         usertype=new JLabel("User Type: ");
         String []userType={"User","Manager"};
         type=new JComboBox(userType);
         type.setSelectedItem(user.getUsertype());

         
      
        right.add(user_name);
        right.add(name);
        right.add(user_email);
        right.add(email);
        right.add(pass_user);
        right.add(pass);
        right.add(usertype);
        right.add(type);


        center_panel.add(right);
        JPanel bottom_panel = new JPanel();
        bottom_panel.setLayout(new FlowLayout());
        bottom_panel.setBorder(BorderFactory.createEmptyBorder(0, 300, 50, 0));
        update= new JButton("Update");
        update.setForeground(Color.WHITE);
        update.setBackground(new java.awt.Color(177, 116, 87));
        update.addActionListener(new UpdateUser());
        bottom_panel.add(update);
        
        back=new JButton("Back");
        back.setForeground(Color.WHITE);
        back.setBackground(new java.awt.Color(177, 116, 87));
        back.addActionListener(new Back());
        bottom_panel.add(back);
        
        p.add(top_panel, BorderLayout.NORTH);
        
        p.add(center_panel, BorderLayout.CENTER);
        
        p.add(bottom_panel, BorderLayout.SOUTH);
       
      
   }
   
    class Back implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           ViewInfo.this.dispose();
           String loggedInUser = null;
           new EditUserProfile(loggedInUser);
            
        }
    
 }
     class UpdateUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         int userID = selectedUser.getUserID();
         String username=name.getText();
         String useremail=email.getText();
         String userpass=new String(pass.getPassword());
         String usertype=(String)type.getSelectedItem();
         
         User user=new User(userID, username,useremail,userpass,usertype);
          boolean result=  DataOperation.upadateUserInfo(user);
          
          if(result){
           JOptionPane.showMessageDialog(null,"User information is successfully updated!","User Update",JOptionPane.INFORMATION_MESSAGE);

          }
          else{
           JOptionPane.showMessageDialog(null,"User information is not updated","User Update",JOptionPane.ERROR_MESSAGE);
         }

                 
            
        }
    
 }

}
