package projectlibarary;
import java.awt.*;
import java.awt.event.*;
import java.util.prefs.Preferences;
import javax.swing.*;


public class HomePageUser extends JFrame {
    JLabel pic1,pic2 ;
    JButton searchbooks,borrowing,profile,home,logout;
    String loggedInUser;


    
     public HomePageUser(String Username){
        loggedInUser = Username;
        this.setTitle("User Page");
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        setLocationRelativeTo(null);
        
        JPanel p = (JPanel) getContentPane();
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //left
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx =  0.1;
        gbc.weighty =  0.1;
        gbc.fill = GridBagConstraints.BOTH;
       
        
        JPanel mainLeftPanel = new JPanel();
        mainLeftPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainLeftPanel.setPreferredSize(new Dimension(200,500));
        mainLeftPanel.setBackground(new Color(74, 73, 71));
        
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setBackground(new Color(74, 73, 71));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 20, 0));
        pic1 = new JLabel(new ImageIcon("img\\person1.png"));
        imagePanel.add(pic1);
        mainLeftPanel.add(imagePanel);
        
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        buttonsPanel.setBackground(new Color(74, 73, 71));
        buttonsPanel.setPreferredSize(new Dimension(200,500));
        profile=new JButton("Profile Info");
        profile.setBorder(BorderFactory.createLineBorder(new Color(216,210,194) , 10, true));
        profile.setPreferredSize(new Dimension(120,30));
        profile.setForeground(Color.WHITE);
        profile.setBackground(new Color(216,210,194));
        profile.addActionListener(new ProfileButton());
        buttonsPanel.add(profile);
        
        home=new JButton("Home");
        home.setBorder(BorderFactory.createLineBorder(new Color(216,210,194) , 10, true));
        home.setForeground(Color.WHITE);
        home.setBackground(new Color(216,210,194));
        home.setPreferredSize(new Dimension(120,30));
        buttonsPanel.add(home);
        logout=new JButton("Logout");
        logout.setBorder(BorderFactory.createLineBorder(new Color(216,210,194) , 10, true));
        logout.setForeground(Color.WHITE);
        logout.setBackground(new Color(216,210,194));
        logout.addActionListener(new LogoutButton());
        logout.setPreferredSize(new Dimension(120,30));
        buttonsPanel.add(logout);
        mainLeftPanel.add(buttonsPanel);
        
        p.add(mainLeftPanel,gbc);
        
       
        
         //right
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.9;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel pc = new JPanel();
        pc.setLayout(new GridLayout(2,1,0,100));
        pc.setPreferredSize(new Dimension(400,500));
        pc.setBackground(new Color(250,247,240)); 
        pic2 = new JLabel(new ImageIcon("img\\HomePageLogo_new.png"));
        pc.add(pic2);
        
        JPanel secondbuttonpanael = new JPanel();
        secondbuttonpanael.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        secondbuttonpanael.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        secondbuttonpanael.setBackground(new Color(216,210,194));
        pc.add(secondbuttonpanael);
        
        searchbooks=new JButton("<html><div style='text-align: center;'>Search Books</div></html>");
        searchbooks.setBorder(BorderFactory.createLineBorder(new Color(177,116,87) , 10, true));
        searchbooks.setForeground(Color.WHITE);
        searchbooks.setBackground(new Color(177,116,87));
        searchbooks.setPreferredSize(new Dimension(120,60));

        borrowing=new JButton("<html><div style='text-align: center;'>Borrowing History</div></html>");
        borrowing.setBorder(BorderFactory.createLineBorder(new Color(177,116,87) , 10, true));
        borrowing.setForeground(Color.WHITE);
        borrowing.setBackground(new Color(177,116,87));
        borrowing.setPreferredSize(new Dimension(120,60));

        
       
       

        
      
        borrowing.addActionListener(new borrowingButton());
        searchbooks.addActionListener(new searchButton());
        
        
        secondbuttonpanael.add(searchbooks);
        secondbuttonpanael.add(borrowing);
        p.add(pc,gbc);
     
        
   
       
    }
     
     
     
     class searchButton implements ActionListener{

        public void actionPerformed(ActionEvent e) {
               
               HomePageUser.this.dispose();
               //new SearchScreen(loggedInUser);
               int userid =DataOperation.GetUserID(loggedInUser);
               new SearchBooks1(loggedInUser, userid).setVisible(true);

        }
       
   }
     
     class borrowingButton implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            
            int userId = DataOperation.GetUserID(loggedInUser);
            HomePageUser.this.dispose();
            new UserHistory(loggedInUser, userId).setVisible(true); 
        }
}

     
   
  
   
      class ProfileButton implements ActionListener{

        public void actionPerformed(ActionEvent e) {
               int id =DataOperation.GetUserID(loggedInUser);
               HomePageUser.this.dispose();
                new UserProfile(id).setVisible(true);
               

        }
       
   }
       class LogoutButton implements ActionListener{

        public void actionPerformed(ActionEvent e) {
              
               HomePageUser.this.dispose();
                new LoginScreen();
                

        }
       
   }
      
      
      
       
}
