package projectlibarary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class EditUserProfile extends JFrame {
    
    JLabel user_name;
    JTextField name;
    JLabel icon_label_1;
    JButton edit,search,back;
    JList usersList;
    JScrollPane scroll;
    String[] usernames ;
    User[] users;
    String loggedInUser;
    
    public EditUserProfile(String loggedInUser){
        super("Edit User Profile");
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        setLocationRelativeTo(null);
        this.loggedInUser = loggedInUser;
        
        JPanel p = (JPanel) getContentPane();
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // p1 size
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.01;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        icon_label_1 = new JLabel(new ImageIcon("img\\login_screen_icon.png"));
        p1.add(icon_label_1);
        p1.setBackground(new java.awt.Color(74, 73, 71));
        p.add(p1, gbc);
        
        // p2 size
        gbc.gridx = 1; 
        gbc.weightx = 0.99; 
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(50,0,0,0);
        JPanel p2 = new JPanel();
        p2.setLayout(new  GridLayout(3,1));
        p2.setPreferredSize(new Dimension(100,600));
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        user_name=new JLabel("User name: ");
        name=new JTextField(20);
        search=new JButton("Search");
        search.setForeground(Color.WHITE);
        search.setBackground(new java.awt.Color(177, 116, 87));
        search.addActionListener(new SearchButton());
        top.add(user_name);
        top.add(name);
        top.add(search,FlowLayout.RIGHT);

        p2.add(top);
        JPanel cnter = new JPanel();
        cnter.setLayout(new FlowLayout());
       
        users = DataOperation.getUserInfo();
        usernames = new String[users.length];
        
        if (users != null) {
         for (int i = 0; i < users.length; i++) {
          if (users[i] != null) {
            usernames[i]=users[i].getUsername();
         }
        }
        }
        
        usersList=new JList(usernames);
        scroll=new JScrollPane (usersList);
        scroll.setPreferredSize(new Dimension(400,200));
      
        cnter.add(scroll);
         p2.add(cnter);
         
        JPanel buttum = new JPanel();
        buttum.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttum.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 50));
       
        edit= new JButton("Edit");
        edit.setForeground(Color.WHITE);
        edit.setBackground(new java.awt.Color(177, 116, 87));
        edit.addActionListener(new EditButton());
        buttum.add(edit);
        
        back= new JButton("Back");
        back.setForeground(Color.WHITE);
        back.setBackground(new java.awt.Color(177, 116, 87));
        back.addActionListener(new BackButton());
        buttum.add(back);
        
        p2.add(buttum);

        p2.setBackground(new java.awt.Color(250, 247, 240));
        p.add(p2, gbc);

    }
    class EditButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedusername = (String)usersList.getSelectedValue();
            
            if (selectedusername == null)
                JOptionPane.showMessageDialog(null,"Please select a user!","User not selected",JOptionPane.ERROR_MESSAGE);
            else{
                for (int i=0;i<users.length;i++){
                    if(users[i].getUsername().equalsIgnoreCase(selectedusername)){
                        EditUserProfile.this.dispose();
                        new ViewInfo(users[i]);
                    }
                }
            }
            
        }
    
 }
     class SearchButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String search_name=name.getText();
            search_name = search_name.toLowerCase();
            String [] matchingname=new String[usernames.length];
           
            for (int i=0;i<usernames.length;i++){
                if((usernames[i].toLowerCase()).contains(search_name)){
                    matchingname[i]=usernames[i];
                }
            }
            usersList.setListData(matchingname);
        }
    
 }
      class BackButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             EditUserProfile.this.dispose();
                new HomePageManager(loggedInUser);
        }
    
 }
    
    }
