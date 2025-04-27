package projectlibarary;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginScreen extends JFrame {

    // Attributes:
    private JTextField username_text;
    JPasswordField password_text;
    private JLabel icon_label_1,icon_label_2 , username_label, password_label,exit_btn;
    private JButton login_btn, register_btn;
    
    private boolean notificationShownInSession = false;
    
    //constructor:
    public LoginScreen()
    {
        super("Login");
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        JPanel p = (JPanel) getContentPane();
        p.setLayout(new BorderLayout());
        p.setBackground(new java.awt.Color(250, 247, 240));
        setLocationRelativeTo(null);
        
        JPanel top_panel = new JPanel();
        top_panel.setLayout(new FlowLayout());
        icon_label_1 = new JLabel(new ImageIcon("img\\login_screen_icon.png"));
        top_panel.add(icon_label_1);
        
        JPanel center_panel = new JPanel();
        center_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        center_panel.setBorder(BorderFactory.createEmptyBorder(0, 0, -100, 0));
        JPanel left = new JPanel();
        icon_label_2=new JLabel(new ImageIcon("img\\person1.png"));
        left.add(icon_label_2);
        center_panel.add(left);
        
        JPanel right = new JPanel();
        right.setLayout(new GridLayout(2,2,-70,20));
        
        username_label=new JLabel ("Username");
        password_label=new JLabel ("Password");
        
        username_text=new JTextField(20);
        username_text.setBackground(new java.awt.Color(216, 210, 194));
        username_text.setBorder(BorderFactory.createEmptyBorder());

        password_text=new JPasswordField (20);
        password_text.setBackground(new java.awt.Color(216, 210, 194));
        password_text.setBorder(BorderFactory.createEmptyBorder());

        

        right.add(username_label);
        right.add(username_text);
        right.add(password_label);
        right.add(password_text);
        center_panel.add(right);

        
        
        JPanel bottom_panel = new JPanel();
        bottom_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottom_panel.setBorder(BorderFactory.createEmptyBorder(0, 300, 200, 0));
        login_btn= new JButton("Login");
        login_btn.setBackground(new java.awt.Color(177, 116, 87));
        login_btn.setForeground(Color.WHITE);
        login_btn.addActionListener(new Log());
        
        register_btn= new JButton("Register");
        register_btn.setForeground(Color.WHITE);
        register_btn.setBackground(new java.awt.Color(177, 116, 87));
        register_btn.addActionListener(new Register());
        
        bottom_panel.add(login_btn);
        bottom_panel.add(register_btn);

        
        
        p.add(top_panel, BorderLayout.NORTH);
        
        p.add(center_panel, BorderLayout.CENTER);
        
        p.add(bottom_panel, BorderLayout.SOUTH);
    }
      
    class Log implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            String username =username_text.getText();
            String paa= new String(password_text.getPassword());
           
            if (username.isEmpty() || paa.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Your User Name and Password!", "Login Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (DataOperation.ValiditUser(username, paa)) {
                    String userType = DataOperation.GetUserType(username);

                    if (userType.trim().equalsIgnoreCase("User")) {
                        LoginScreen.this.dispose();

                        // Show notification after successful login
                        if (!notificationShownInSession) {
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        NotificationDialog n = new NotificationDialog(new javax.swing.JFrame(), true, username);
                                        n.checkBooksNearReturn();  // Check for books near return

                                        if (n.getCount() > 0) {
                                            n.setVisible(true);
                                            notificationShownInSession = true; // Mark notification as shown
                                        }
                                    } catch (NullPointerException ex) {
                                        System.out.println("An error occurred in NotificationDialog: " + ex.getMessage());
                                        ex.printStackTrace();
                                    }
                                }
                            });
                        }

                        // Now go to HomePageUser
                        new HomePageUser(username).setVisible(true);
                    } else {
                        LoginScreen.this.dispose();
                        new HomePageManager(username);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
     
   class Register implements ActionListener{

        public void actionPerformed(ActionEvent e) {
               
               LoginScreen.this.dispose();
                new RigsterPage();
        }
       
   }
    

    public static void main(String[] args) {

        LoginScreen project = new LoginScreen();
        project.setVisible(true);

      
    }    
}