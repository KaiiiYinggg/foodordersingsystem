package foodorderingsystem;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReviewForm extends javax.swing.JFrame {

    Login login = new Login();
    private String customerID;

    public ReviewForm() {
        initComponents();
        getContentPane().setBackground(new Color(252, 154, 63)); // Set background color
        System.out.println(login.getUsername());
        customerID = login.getUsername();
        ImageIcon img = new ImageIcon(getClass().getResource("/files/back.png"));
        Image scaledImage = img.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);//with 200px,height 200px
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CustomerMenu c = new CustomerMenu();
                c.setVisible(true);
                dispose();
            }
        });

        back.setText("");
        back.setIcon(scaledIcon);
        setLocationRelativeTo(null);
        showOrderTableData("order.txt");
    }

    private void showOrderTableData(String filePath) {
        DefaultTableModel model = (DefaultTableModel) ordertbl.getModel();
        model.setRowCount(0);

        try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {
            String read;
            while ((read = bf.readLine()) != null) {
                String[] data = read.split("\\|");
                if (data[1].equals(customerID)) {

                    String[] specificColumn = {data[0], data[2], data[7]};
                    if (data[6].equals("Completed")) {
                        model.addRow(specificColumn); 
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
        }
    }

    private void saveFeedback() {
        DefaultTableModel table = (DefaultTableModel) ordertbl.getModel();
        int selectedRow = ordertbl.getSelectedRow();
        if (selectedRow != -1) {
            String orderID = table.getValueAt(selectedRow, 0).toString();
            String food = table.getValueAt(selectedRow, 1).toString();

            String date = LocalDate.now().toString();

            String rating = reviewChoice();

            String comment = commenttxt.getText().trim();
            String commenttoRunner = txtrunnerComment.getText().trim();
            String sellerreplyFood = "";
            String sellerreplyRunner = "";

            if (rating != "") {
                if (comment.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please leave a comment.");
                    return;
                }
                if (commenttoRunner.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please leave a comment to our runner.");
                }

                String feedback = orderID + "|" + customerID + "|" + food + "|" + rating + "|" + comment + "|"
                        + sellerreplyFood + "|" + commenttoRunner + "|" + sellerreplyRunner + "|" + date + "\n";

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("feedback.txt", true))) {
                    writer.write(feedback);
                    JOptionPane.showMessageDialog(this, "Feedback saved successfully!");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error saving feedback: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order to review.");
        }
    }

    private String reviewChoice() {
        if ((!verygoodrb.isSelected()) && (!goodrb.isSelected()) && (!neutralrb.isSelected()) && (!badrb.isSelected()) && (!verybadrb.isSelected())) {
            JOptionPane.showMessageDialog(null, "Please make a rate");
            return "";
        } else if ((verygoodrb.isSelected() == true) && (goodrb.isSelected() == false)
                && (neutralrb.isSelected() == false) && (badrb.isSelected() == false) && (verybadrb.isSelected() == false)) {
            return "Very Good";
        } else if ((verygoodrb.isSelected() == false) && (goodrb.isSelected() == true)
                && (neutralrb.isSelected() == false) && (badrb.isSelected() == false) && (verybadrb.isSelected() == false)) {
            return "Good";
        } else if ((verygoodrb.isSelected() == false) && (goodrb.isSelected() == false)
                && (neutralrb.isSelected() == true) && (badrb.isSelected() == false) && (verybadrb.isSelected() == false)) {
            return "Neutral";
        } else if ((verygoodrb.isSelected() == false) && (goodrb.isSelected() == false)
                && (neutralrb.isSelected() == false) && (badrb.isSelected() == true) && (verybadrb.isSelected() == false)) {
            return "Bad";
        } else if ((verygoodrb.isSelected() == false) && (goodrb.isSelected() == false)
                && (neutralrb.isSelected() == false) && (badrb.isSelected() == false) && (verybadrb.isSelected() == true)) {
            return "Very Bad";
        } else {
            JOptionPane.showMessageDialog(null, "Choose one rate only");
            return "";
        }
    }

    private void showOtherCustomerReview() {
        DefaultTableModel reviewtable = (DefaultTableModel) reviewtbl.getModel();
        reviewtable.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader("feedback.txt"))) {
            String read;
            while ((read = br.readLine()) != null) {
                String[] data = read.split("\\|");
                String[] specificColumn = {data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]};
                if (!data[6].equals("Completed")) {
                    reviewtable.addRow(specificColumn); // Add the row to the table
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        verygoodrb = new javax.swing.JRadioButton();
        goodrb = new javax.swing.JRadioButton();
        neutralrb = new javax.swing.JRadioButton();
        badrb = new javax.swing.JRadioButton();
        verybadrb = new javax.swing.JRadioButton();
        commenttxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        saveBt = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        reviewtbl = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        backBt = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        ordertbl = new javax.swing.JTable();
        showBt = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtrunnerComment = new javax.swing.JTextField();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 24)); // NOI18N
        jLabel1.setText("~.Review.~");

        jPanel1.setBackground(new java.awt.Color(255, 150, 76));

        verygoodrb.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        verygoodrb.setForeground(new java.awt.Color(255, 255, 255));
        verygoodrb.setText("Very Good");

        goodrb.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        goodrb.setForeground(new java.awt.Color(255, 255, 255));
        goodrb.setText("Good");

        neutralrb.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        neutralrb.setForeground(new java.awt.Color(255, 255, 255));
        neutralrb.setText("Neutral");

        badrb.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        badrb.setForeground(new java.awt.Color(255, 255, 255));
        badrb.setText("Bad");

        verybadrb.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        verybadrb.setForeground(new java.awt.Color(255, 255, 255));
        verybadrb.setText("Very Bad");
        verybadrb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verybadrbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(verygoodrb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(goodrb, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(neutralrb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(badrb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(verybadrb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verygoodrb)
                    .addComponent(goodrb)
                    .addComponent(neutralrb)
                    .addComponent(badrb)
                    .addComponent(verybadrb))
                .addGap(16, 16, 16))
        );

        jLabel2.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel2.setText("Leave a comment : ");

        saveBt.setBackground(new java.awt.Color(255, 106, 0));
        saveBt.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        saveBt.setForeground(new java.awt.Color(255, 255, 255));
        saveBt.setText("Save");
        saveBt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        saveBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 2, 10)); // NOI18N
        jLabel3.setText("Review to each order......");

        reviewtbl.setBackground(new java.awt.Color(255, 165, 101));
        reviewtbl.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        reviewtbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "Food", "Review", "Comment", "Seller reply(food)", "Runner comment", "Seller reply(runner)", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(reviewtbl);

        jLabel4.setFont(new java.awt.Font("Sitka Text", 2, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Other customers reviews......");

        backBt.setBackground(new java.awt.Color(255, 106, 0));
        backBt.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        backBt.setForeground(new java.awt.Color(255, 255, 255));
        backBt.setText("Back");
        backBt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        backBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtActionPerformed(evt);
            }
        });

        ordertbl.setBackground(new java.awt.Color(255, 165, 101));
        ordertbl.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        ordertbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "OrderID", "Food", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(ordertbl);

        showBt.setBackground(new java.awt.Color(255, 110, 0));
        showBt.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        showBt.setForeground(new java.awt.Color(255, 255, 255));
        showBt.setText("Show other customer review");
        showBt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        showBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showBtActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel5.setText("Leave a comment ot runner : ");

        back.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(backBt, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(224, 224, 224)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtrunnerComment))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(commenttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(412, 412, 412)
                                .addComponent(saveBt, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(189, 189, 189)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(back)
                                .addGap(323, 323, 323)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addComponent(showBt, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(back))
                .addGap(4, 4, 4)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(commenttxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtrunnerComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(saveBt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showBt)
                .addGap(4, 4, 4)
                .addComponent(backBt)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void verybadrbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verybadrbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_verybadrbActionPerformed

    private void backBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtActionPerformed
        // TODO add your handling code here:back to main menu
        this.dispose();
        new CustomerMenu().setVisible(true);
    }//GEN-LAST:event_backBtActionPerformed

    private void saveBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtActionPerformed
        saveFeedback();
        verygoodrb.setSelected(false);
        goodrb.setSelected(false);
        neutralrb.setSelected(false);
        badrb.setSelected(false);
        verybadrb.setSelected(false);

        commenttxt.setText("");
        txtrunnerComment.setText("");
        ordertbl.clearSelection();
    }//GEN-LAST:event_saveBtActionPerformed

    private void showBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showBtActionPerformed
        showOtherCustomerReview();
    }//GEN-LAST:event_showBtActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JButton backBt;
    private javax.swing.JRadioButton badrb;
    private javax.swing.JTextField commenttxt;
    private javax.swing.JRadioButton goodrb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton neutralrb;
    private javax.swing.JTable ordertbl;
    private javax.swing.JTable reviewtbl;
    private javax.swing.JButton saveBt;
    private javax.swing.JButton showBt;
    private javax.swing.JTextField txtrunnerComment;
    private javax.swing.JRadioButton verybadrb;
    private javax.swing.JRadioButton verygoodrb;
    // End of variables declaration//GEN-END:variables
}
