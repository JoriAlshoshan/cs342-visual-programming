
package projectlibarary;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RigsterPage extends JFrame  {
   
   JLabel user_name,user_email,pass_user;
   JTextField name,email;
   JPasswordField pass;
   JLabel icon_label_1,icon_label_2 , username_label, password_label,exit_btn;
   JButton register_btn;
   
    public RigsterPage(){
        super("Register");
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        JPanel p = (JPanel) getContentPane();
        p.setLayout(new BorderLayout());
        p.setBackground(new java.awt.Color(250, 247, 240));
        setLocationRelativeTo(null);
        
        JPanel top_panel = new JPanel();
        top_panel.setLayout(new FlowLayout());
        
        JPanel center_panel = new JPanel();
        center_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        center_panel.setBorder(BorderFactory.createEmptyBorder(0, 0, -100, 0));
        
        JPanel right = new JPanel();
        right.setLayout(new GridLayout(4,2,-70,20));
        
        user_name=new JLabel("User name: ");
        name=new JTextField(20);
        name.setBackground(new java.awt.Color(216, 210, 194));
        name.setBorder(BorderFactory.createEmptyBorder());
        
         user_email=new JLabel("Email: ");
         email=new JTextField(20);
         email.setBackground(new java.awt.Color(216, 210, 194));
         email.setBorder(BorderFactory.createEmptyBorder());

        
         pass_user=new JLabel("Password: ");
         pass=new JPasswordField(20);
         pass.setBackground(new java.awt.Color(216, 210, 194));
         pass.setBorder(BorderFactory.createEmptyBorder());

        
        
        right.add(user_name);
        right.add(name);
        right.add(user_email);
        right.add(email);
        right.add(pass_user);
        right.add(pass);
        
        center_panel.add(right);

        
        
        JPanel bottom_panel = new JPanel();
        bottom_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottom_panel.setBorder(BorderFactory.createEmptyBorder(0, 300, 200, 0));
        
        register_btn= new JButton("Register");
        register_btn.setForeground(Color.WHITE);
        register_btn.setBackground(new java.awt.Color(177, 116, 87));
        register_btn.addActionListener(new Ok());
        
        bottom_panel.add(register_btn);

        
        icon_label_1 = new JLabel(new ImageIcon("img\\login_screen_icon.png"));
       
        top_panel.add(icon_label_1);
        p.add(top_panel, BorderLayout.NORTH);
        
        p.add(center_panel, BorderLayout.CENTER);
        
        p.add(bottom_panel, BorderLayout.SOUTH);

    }
        
    class Ok implements ActionListener{
    
        public void actionPerformed(ActionEvent e) {
            boolean usernameValidated = false;
            boolean emailValidated = false;
            boolean passwordValidated = false;
            String errorMessage = "";
            
            String username = name.getText();
            String email_e = email.getText();
            String password = new String(pass.getPassword());
            
            if(!username.isEmpty() && username!=null){
                usernameValidated = true;
            }
            
            int atindex=email_e.indexOf("@");
            int dotindex=email_e.lastIndexOf(".");
            boolean emailCondition1 = email_e.isEmpty()||email_e==null || (email_e.contains(" "));
            boolean emailCondition2 = atindex==0 || atindex==email_e.length()-1;
            boolean emailCondition3 = !(dotindex > (atindex+1)) || dotindex == email_e.length()-1;
            
            if(!emailCondition1 && !emailCondition2 && !emailCondition3){
                emailValidated = true;
            }
             
            boolean passwardCondition1 = password.isEmpty()|| password==null || (password.contains(" "));
            boolean passwardCondition2 = password.length() < 8 ||  password.length() > 20;
            boolean passwardCondition3;
            boolean upperCase = false;
            boolean lowerCase = false;
            boolean digit = false;
            boolean special = false;
            char passwordChar;
            String specialchars="@#_!?$";
            for (int i=0;i<password.length();i++){
                passwordChar = password.charAt(i);
                if(Character.isUpperCase(passwordChar))
                    upperCase = true;
                if(Character.isLowerCase(passwordChar))
                    lowerCase = true;
                 if(Character.isDigit(passwordChar))
                    digit = true;
                if(specialchars.indexOf(passwordChar) != -1)
                    special = true;
            }
            
            passwardCondition3 = !upperCase || !lowerCase || !digit ||  !special;
             if(!passwardCondition1 && !passwardCondition2 && !passwardCondition3){
                passwordValidated = true;
            }
             
            if (!usernameValidated){
                errorMessage = "Please enter your username!\n";
            }
               
            if (!emailValidated){
                errorMessage+="Please enter a correct email!\n";
            }
             if (!passwordValidated){
                errorMessage+="Please enter a correct passward! The password should contian the following:\n 1- At least one uppercase \n 2- At least one lowercase \n 3- At least one special character (@#_!?$) \n 4- At least one digit \n 5- From 8 to 20 characters";
            }
            try {
                if(errorMessage!="")
                 throw new RegistrationException(errorMessage);
                else{
                    DataOperation.AddNewUser(username, email_e, password);
                    new HomePageUser(username);
                }
             }catch(RegistrationException r){
               JOptionPane.showMessageDialog(null,r.getMessage(),"Input Validation",JOptionPane.ERROR_MESSAGE);

             }
    }
   
      
   }        
}
