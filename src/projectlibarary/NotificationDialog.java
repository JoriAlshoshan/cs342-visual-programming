/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectlibarary;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author shenz
 */

public class NotificationDialog extends javax.swing.JDialog {

    
    private Connection conx;
    private int count = 0;
    private String loggedInUser;
    private int userID;
    private int overdueCount = 0; 
    private int dueDateCount = 0; 
    private boolean isDialogVisible = false;
    private boolean isFirstDialogClosed = false;
    
    
    
    public NotificationDialog(java.awt.Frame parent, boolean modal, String loggedInUser) {
        super(parent, modal); 
        initComponents();
        connectToDatabase();
        this.loggedInUser = loggedInUser;
        setTitle("Reminder");
        setLocationRelativeTo(null);
        
         
        
        
        if (loggedInUser == null || loggedInUser.isEmpty()) {
            throw new IllegalArgumentException("LoggedInUser cannot be null or empty");
        }

        
        userID = fetchUserIDFromDatabase(loggedInUser);
        if (userID == -1) {
            JOptionPane.showMessageDialog(this, "User not found in the database.");
            dispose(); 
            return; 
        }

        checkUserInNotificationsData();
        checkBooksNearReturn();
        
        /*
        //to handle default close operation
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                onDialogClose();
            }
        });*/
    }
    
    /*private void onDialogClose() {
        dispose();
    }*/
    
    private void connectToDatabase() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            
            String pathToDB = "DB\\DatabaseFinal.accdb";
            String url = "jdbc:ucanaccess://" + pathToDB;
            conx = DriverManager.getConnection(url);
            
        } catch (ClassNotFoundException e) {
        JOptionPane.showMessageDialog(this, "Database driver not found.");
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection failed.");
        }
    }
    
 
    private int fetchUserIDFromDatabase(String loggedInUser) {
        String query = "SELECT UserID FROM [Users] WHERE Username = ?";
        int userID = -1;
        try (PreparedStatement stmt = conx.prepareStatement(query)) {
            stmt.setString(1, loggedInUser);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userID = rs.getInt("UserID");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching UserID: " + e.getMessage());
        }
        return userID;
    }
    

    public void checkUserInNotificationsData() {
        String query = "SELECT COUNT(*) FROM [Notifications Data] WHERE UserID = ?";
        try (PreparedStatement stmt = conx.prepareStatement(query)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                overdueCount++;
            }
        } catch (SQLException e) {
            System.err.println("Error checking Notifications Data: " + e.getMessage());
        }
    }
    
     public void checkBooksNearReturn() {
        String query = "SELECT * FROM [Borrowing Data] WHERE Status = 'Borrowed' AND UserID = ?";
        try (PreparedStatement stmt = conx.prepareStatement(query)) {
            stmt.setInt(1, userID);  
            ResultSet rs = stmt.executeQuery();

             while (rs.next()) {
                java.sql.Date calculatedReturnDate = rs.getDate("CalculatedReturnDate");

                if (calculatedReturnDate != null) {
                    LocalDate returnDate = calculatedReturnDate.toLocalDate();
                    LocalDate currentDate = LocalDate.now();
                    LocalDate threeDaysLater = currentDate.plusDays(3);

                    if (!returnDate.isBefore(currentDate) && !returnDate.isAfter(threeDaysLater)) {
                        dueDateCount++;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching Borrowing Data: " + e.getMessage());
        }

        updateLabels();
    }




    private void updateLabels() {
        
        if (overdueCount > 0 && dueDateCount > 0) {
            jLabel1.setText("<html>Dear user, you have " + overdueCount + " overdue and " + dueDateCount + " books due for return in the coming few days.</html>");
            jLabel2.setText("<html>For details, please check your Borrowing History.</html>");
        } else if (overdueCount > 0) {
            jLabel1.setText("<html>Dear user, you have " + overdueCount + " overdue books. Please return them as soon as you can.</html>");
            jLabel2.setText("<html>For details, please check your Borrowing History.</html>");
        } else if (dueDateCount > 0) {
            jLabel1.setText("<html>Dear user, you have " + dueDateCount + " books due for return in the coming few days.</html>");
            jLabel2.setText("<html>For details, please check your Borrowing History.</html>");
        } else {
            dispose();
            return;
        }

        showDialogIfNeeded();
    }
    
    
    
    
    
    
    private void showDialogIfNeeded() {
        if (!isDialogVisible) {
            this.setVisible(true);
            isDialogVisible = true;
        }}


     
    /*private void showNextDialogIfNeeded() {
        if (isFirstDialogClosed && dueDateCount > 0 && !isDialogVisible) {
            jLabel1.setText("<html> Dear user, you have " + dueDateCount + " books due for return in the coming few days.</html>");
            jLabel2.setText("<html> For details, please check your Borrowing History.</html>");
            showDialogIfNeeded();
        }
    }*/


    
    

    public int getCount() {
        return count;
    }
   
    



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(250, 247, 240));
        jPanel1.setPreferredSize(new java.awt.Dimension(390, 200));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(177, 116, 87));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ok");
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 150, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 330, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 330, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       /* if (overdueCount > 0 || dueDateCount > 0) {
            isFirstDialogClosed = true;
        }*/
        this.dispose();
        //showNextDialogIfNeeded();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NotificationDialog dialog = new NotificationDialog(new javax.swing.JFrame(), true, "");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
        
        System.setErr(new PrintStream(new OutputStream() {
    @Override
    public void write(int b) {
        
    }
}));

        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
