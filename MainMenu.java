package foodorderingsystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends javax.swing.JFrame {

    public MainMenu() {
        initComponents();
        getContentPane().setBackground(new Color(200,249,190)); // Set background color

        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                component.setBackground(new Color(200,249,190));
            }
        }

        Login login = new Login();
        System.out.println(login.getUsername());

        ImageIcon img = new ImageIcon(getClass().getResource("/files/logout.png"));
        Image scaledImage = img.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);//with 200px,height 200px
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                login.setVisible(true);

            }
        });

        logout.setText("");
        logout.setIcon(scaledIcon);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        MenuButton = new javax.swing.JButton();
        OrderButton = new javax.swing.JButton();
        HistoryButton = new javax.swing.JButton();
        RevenueButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MenuButton.setBackground(new java.awt.Color(77, 215, 77));
        MenuButton.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        MenuButton.setForeground(new java.awt.Color(255, 255, 255));
        MenuButton.setText("Menu");
        MenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButtonActionPerformed(evt);
            }
        });

        OrderButton.setBackground(new java.awt.Color(77, 214, 77));
        OrderButton.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        OrderButton.setForeground(new java.awt.Color(255, 255, 255));
        OrderButton.setText("Order Management");
        OrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrderButtonActionPerformed(evt);
            }
        });

        HistoryButton.setBackground(new java.awt.Color(77, 214, 77));
        HistoryButton.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        HistoryButton.setForeground(new java.awt.Color(255, 255, 255));
        HistoryButton.setText("Order History and Feedback");
        HistoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistoryButtonActionPerformed(evt);
            }
        });

        RevenueButton.setBackground(new java.awt.Color(77, 214, 77));
        RevenueButton.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        RevenueButton.setForeground(new java.awt.Color(255, 255, 255));
        RevenueButton.setText("Revenue Dashboard");
        RevenueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RevenueButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 36)); // NOI18N
        jLabel2.setText("Main Menu");

        logout.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 100, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(51, 51, 51)
                .addComponent(logout)
                .addGap(57, 57, 57))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MenuButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OrderButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HistoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(RevenueButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(logout))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2)))
                .addGap(41, 41, 41)
                .addComponent(MenuButton)
                .addGap(18, 18, 18)
                .addComponent(OrderButton)
                .addGap(18, 18, 18)
                .addComponent(HistoryButton)
                .addGap(18, 18, 18)
                .addComponent(RevenueButton)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuButtonActionPerformed
        // Close current form (MainMenu)
        this.dispose();

        // Create and show the new form (MenuPage)
        Menu menu = new Menu(); 
        menu.setVisible(true);  
    }//GEN-LAST:event_MenuButtonActionPerformed

    private void OrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrderButtonActionPerformed
        // Close current form (MainMenu)
        this.dispose();

        OrderManagement orderManagement = new OrderManagement();
        orderManagement.setVisible(true);
    }//GEN-LAST:event_OrderButtonActionPerformed

    private void HistoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistoryButtonActionPerformed
        // Close current form (MainMenu)
        this.dispose();

        OrderHistoryFeedback orderHistoryFeedback = new OrderHistoryFeedback();
        orderHistoryFeedback.setVisible(true);
    }//GEN-LAST:event_HistoryButtonActionPerformed

    private void RevenueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RevenueButtonActionPerformed
        // Close current form (MainMenu)
        this.dispose();

        RevenueDashboard revenueDashboard = new RevenueDashboard();
        revenueDashboard.setVisible(true);
    }//GEN-LAST:event_RevenueButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton HistoryButton;
    private javax.swing.JButton MenuButton;
    private javax.swing.JButton OrderButton;
    private javax.swing.JButton RevenueButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logout;
    // End of variables declaration//GEN-END:variables
}
