/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectlibarary;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.util.logging.*;
/**
 *
 * @author shenz
 */
public class UserHistory extends javax.swing.JFrame {
    
    Connection conx;
    
  private void connectToDatabase() {
        try {
            
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            String pathToDB = "DB\\DatabaseFinal.accdb";
            String url = "jdbc:ucanaccess://" + pathToDB;
            conx = DriverManager.getConnection(url);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserHistory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

private void fillLists() {
        DefaultListModel<String> listModel1 = new DefaultListModel<>();
        DefaultListModel<String> listModel2 = new DefaultListModel<>();

        try {
           
            String sql1 = "SELECT b.Title, b.CalculatedReturnDate FROM [Borrowing Data] b " +
                          "JOIN [Notifications Data] n ON b.BookID = n.BookID " +
                          "WHERE b.Status = 'borrowed' AND b.ReturnDate IS NULL AND b.UserID = ?";
            PreparedStatement stmt1 = conx.prepareStatement(sql1);
            stmt1.setInt(1, userID);
            ResultSet rs1 = stmt1.executeQuery();

            while (rs1.next()) {
                String title = rs1.getString("Title");
                String calculatedReturnDate = rs1.getString("CalculatedReturnDate");
                String formattedCalculatedReturnDate = calculatedReturnDate != null ? calculatedReturnDate.split(" ")[0] : "Unknown";
                listModel1.addElement(title + " | Return by: " + formattedCalculatedReturnDate);
            }

            jList1.setModel(listModel1);

            
            String sql2 = "SELECT Title, BorrowDate FROM [Borrowing Data] WHERE UserID = ?";
            PreparedStatement stmt2 = conx.prepareStatement(sql2);
            stmt2.setInt(1, userID); 
            ResultSet rs2 = stmt2.executeQuery();

            while (rs2.next()) {
                String title = rs2.getString("Title");
                String borrowDate = rs2.getString("BorrowDate");
                String formattedBorrowDate = borrowDate != null ? borrowDate.split(" ")[0] : "Unknown";
                listModel2.addElement(title + " | Borrowed on: " + formattedBorrowDate);
            }

            jList2.setModel(listModel2);

            jList1.addListSelectionListener(e -> handleListSelection(true));
            jList2.addListSelectionListener(e -> handleListSelection(false));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage());
        }
    }


    private void handleListSelection(boolean isList1) {
        if (isList1) {
            if (!jList1.isSelectionEmpty()) {
                jList2.clearSelection();
            }
        } else {
            if (!jList2.isSelectionEmpty()) {
                jList1.clearSelection();
            }
        }
        updateButtonState();
    }

    
    private void updateButtonState() {
        boolean isItemSelected = !jList1.isSelectionEmpty() || !jList2.isSelectionEmpty();
        jButton2.setEnabled(isItemSelected);
    }


    private BorrowingData getBorrowingDataFromDatabase(String title) {
        BorrowingData borrowingData = null;

        try {
            String query = "SELECT * FROM [Borrowing Data] WHERE Title LIKE ? AND UserID = ?";
            PreparedStatement stmt = conx.prepareStatement(query);
            stmt.setString(1, "%" + title + "%");
            stmt.setInt(2, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int borrowingID = rs.getInt("BorrowingID");
                int bookID = rs.getInt("BookID");
                String borrowDate = rs.getString("BorrowDate");
                String returnDate = rs.getString("ReturnDate");
                String returnStatus = rs.getString("ReturnStatus");
                String allowedBorrowRange = rs.getString("AllowedBorrowRange");
                String calculatedReturnDate = rs.getString("CalculatedReturnDate");

                String formattedBorrowDate = formatDate(borrowDate);
                String formattedReturnDate = formatDate(returnDate);
                String formattedCalculatedReturnDate = formatDate(calculatedReturnDate);

                borrowingData = new BorrowingData(Integer.toString(borrowingID), Integer.toString(userID), Integer.toString(bookID),
                        title, formattedBorrowDate, formattedReturnDate, returnStatus, allowedBorrowRange, formattedCalculatedReturnDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrowingData;
    }

    // Format date
    public String formatDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return "";
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(dateString);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            return new SimpleDateFormat("dd MMM yyyy").format(sqlDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid Date";
        }
    }
    
    
    
    String loggedInUser;
    int userID;
    
    public UserHistory(String loggedInUser, int userID) {
        this.loggedInUser = loggedInUser;
        this.userID = userID;
        initComponents();
        connectToDatabase();
        fillLists();
        setLocationRelativeTo(null);
        setTitle("Borrowing History");
        
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(250, 247, 240));
        jPanel1.setMinimumSize(new java.awt.Dimension(1070, 590));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(74, 73, 71));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(229, 226, 226));
        jLabel10.setText("@All rights belong to Iqraa Library");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectlibarary/Images/logo1.jpg"))); // NOI18N
        jLabel6.setText("jLabel2");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 590));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Overdue books:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 140, -1));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(74, 73, 71)));
        jScrollPane1.setDoubleBuffered(true);

        jList1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setFocusable(false);
        jList1.setRequestFocusEnabled(false);
        jList1.setVisibleRowCount(5);
        jScrollPane1.setViewportView(jList1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 530, 101));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("All borrwed books:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, 194, -1));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(74, 73, 71)));

        jList2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.setFocusable(false);
        jList2.setVerifyInputWhenFocusTarget(false);
        jList2.setVisibleRowCount(10);
        jScrollPane2.setViewportView(jList2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, 527, 197));

        jButton2.setBackground(new java.awt.Color(177, 116, 87));
        jButton2.setFont(new java.awt.Font("Leelawadee UI Semilight", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Show Receipt");
        jButton2.setToolTipText("");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton2.setDefaultCapable(false);
        jButton2.setEnabled(false);
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.setRequestFocusEnabled(false);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 500, 136, 37));

        jButton1.setBackground(new java.awt.Color(250, 247, 240));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectlibarary/Images/arrow (2).png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusable(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, -1, -1));

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

    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
   String selectedTitle = null;

        if (!jList1.isSelectionEmpty()) {
            selectedTitle = jList1.getSelectedValue();
        } else if (!jList2.isSelectionEmpty()) {
            selectedTitle = jList2.getSelectedValue();
        }

        if (selectedTitle == null || selectedTitle.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a book.");
            return;
        }

        // Extract book title
        String titleOnly = selectedTitle.split(" \\| ")[0];

        BorrowingData borrowingData = getBorrowingDataFromDatabase(titleOnly);

        if (borrowingData == null) {
            JOptionPane.showMessageDialog(this, "No borrowing data found for the selected book.");
        } else {
            UserReceipt receiptDialog = new UserReceipt(this, true);
            receiptDialog.displayBorrowingReceipt(borrowingData);
            receiptDialog.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
        new HomePageUser(loggedInUser).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

  
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new UserHistory("", 1).setVisible(true));
       
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
