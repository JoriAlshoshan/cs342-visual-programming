
package projectlibarary;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HomePageManager extends JFrame {
    JLabel pic1,pic2 ;
    JButton book, borrowing,recipt,report,edit,profile,home,logout;
    String loggedInUser;
    
     public HomePageManager(String Username){
        loggedInUser = Username;
        this.setTitle("Manager Page");
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
        
        book=new JButton("Books");
        book.setBorder(BorderFactory.createLineBorder(new Color(177,116,87) , 10, true));
        book.setForeground(Color.WHITE);
        book.setBackground(new Color(177,116,87));
        book.setPreferredSize(new Dimension(120,60));

        
        borrowing=new JButton("<html><div style='text-align: center;'>Borrowing History</div></html>");
        borrowing.setBorder(BorderFactory.createLineBorder(new Color(177,116,87) , 10, true));
        borrowing.setForeground(Color.WHITE);
        borrowing.setBackground(new Color(177,116,87));
        borrowing.setPreferredSize(new Dimension(120,60));

        
        recipt=new JButton("<html><div style='text-align: center;'>Borrowing Return Receipt</div></html>");
        recipt.setBorder(BorderFactory.createLineBorder(new Color(177,116,87) , 10, true));
        recipt.setForeground(Color.WHITE);
        recipt.setBackground(new Color(177,116,87));
        recipt.setPreferredSize(new Dimension(120,60));

        report=new JButton("Report");
        report.setBorder(BorderFactory.createLineBorder(new Color(177,116,87) , 10, true));
        report.setForeground(Color.WHITE);
        report.setBackground(new java.awt.Color(177,116,87));
        report.setPreferredSize(new Dimension(120,60));

        
        edit=new JButton("<html><div style='text-align: center;'>Edit User Profile</div></html>");
        edit.setBorder(BorderFactory.createLineBorder(new Color(177,116,87) , 10, true));
        edit.setForeground(Color.WHITE);
        edit.setBackground(new Color(177,116,87));
        edit.setPreferredSize(new Dimension(120,60));

        
        book.addActionListener(new bookButton());
        borrowing.addActionListener(new borrowingButton());
        recipt.addActionListener(new reciptButton());
        report.addActionListener(new reportButton());
        edit.addActionListener(new editButton());  
        secondbuttonpanael.add(book);
        secondbuttonpanael.add(borrowing);
        secondbuttonpanael.add(recipt);
        secondbuttonpanael.add(report);
        secondbuttonpanael.add(edit);
        
        p.add(pc,gbc);
     
        
   
       
    }
     
     
     
     class bookButton implements ActionListener{

        public void actionPerformed(ActionEvent e) {
               int id =DataOperation.GetUserID(loggedInUser);
               HomePageManager.this.dispose();
                 new Books(loggedInUser).setVisible(true);

        }
       
   }
     
     class borrowingButton implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            
               int id =DataOperation.GetUserID(loggedInUser);
               HomePageManager.this.dispose();
                new ManagerHistory(loggedInUser).setVisible(true);

        }
       
   }
     
   class reciptButton implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            
               int id =DataOperation.GetUserID(loggedInUser);
               HomePageManager.this.dispose();
               new ManagerReceiptGenerator(loggedInUser).setVisible(true);

        }
       
   }
   
   class reportButton implements ActionListener{

        public void actionPerformed(ActionEvent e) {
               int id =DataOperation.GetUserID(loggedInUser);
               HomePageManager.this.dispose();
                 new Report(loggedInUser).setVisible(true);
        }
       
   }
   class editButton implements ActionListener{

        public void actionPerformed(ActionEvent e) {
               int id =DataOperation.GetUserID(loggedInUser);
               HomePageManager.this.dispose();
               new EditUserProfile(loggedInUser);
        }
       
   }
   
      class ProfileButton implements ActionListener{

        public void actionPerformed(ActionEvent e) {
               int id =DataOperation.GetUserID(loggedInUser);
               HomePageManager.this.dispose();
               
                new ManagerProfile(id).setVisible(true);
                 //new ManagerProfile(loggedInUser);

        }
       
   }
       class LogoutButton implements ActionListener{

        public void actionPerformed(ActionEvent e) {
               
               HomePageManager.this.dispose();
               new LoginScreen();

        }
       
   }
      

    
}
