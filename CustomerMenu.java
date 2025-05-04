package foodorderingsystem;

import domain.Customer;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CustomerMenu extends javax.swing.JFrame {

    Login login = new Login();
    Customer customer = new Customer();

    public CustomerMenu() {
        initComponents();
        System.out.println(login.getUsername());
        name.setText(customer.searchCustomerName(login.getUsername()));

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
        name = new javax.swing.JLabel();
        menuBt = new javax.swing.JButton();
        statusBt = new javax.swing.JButton();
        reviewBt = new javax.swing.JButton();
        infoUpdate = new javax.swing.JButton();
        logout = new javax.swing.JLabel();
        lblwelcome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(252, 154, 63));

        jPanel1.setBackground(new java.awt.Color(255, 175, 101));

        name.setFont(new java.awt.Font("Sitka Text", 0, 24)); // NOI18N
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("jLabel1");

        menuBt.setBackground(new java.awt.Color(252, 122, 0));
        menuBt.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        menuBt.setForeground(new java.awt.Color(255, 255, 255));
        menuBt.setText("View Menu and Place order");
        menuBt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        menuBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBtActionPerformed(evt);
            }
        });

        statusBt.setBackground(new java.awt.Color(252, 122, 0));
        statusBt.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        statusBt.setForeground(new java.awt.Color(255, 255, 255));
        statusBt.setText("My Order stautus");
        statusBt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        statusBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusBtActionPerformed(evt);
            }
        });

        reviewBt.setBackground(new java.awt.Color(252, 121, 0));
        reviewBt.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        reviewBt.setForeground(new java.awt.Color(255, 255, 255));
        reviewBt.setText("Review to order");
        reviewBt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        reviewBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reviewBtActionPerformed(evt);
            }
        });

        infoUpdate.setBackground(new java.awt.Color(252, 122, 0));
        infoUpdate.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        infoUpdate.setForeground(new java.awt.Color(255, 255, 255));
        infoUpdate.setText("Info Update");
        infoUpdate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        infoUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoUpdateActionPerformed(evt);
            }
        });

        logout.setText("jLabel1");

        lblwelcome.setFont(new java.awt.Font("Sitka Text", 0, 36)); // NOI18N
        lblwelcome.setText("WELCOME");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menuBt, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(infoUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(statusBt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                .addComponent(reviewBt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(lblwelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                .addComponent(logout)
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblwelcome)
                    .addComponent(logout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(menuBt)
                .addGap(18, 18, 18)
                .addComponent(statusBt)
                .addGap(18, 18, 18)
                .addComponent(reviewBt)
                .addGap(18, 18, 18)
                .addComponent(infoUpdate)
                .addGap(37, 37, 37))
        );

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

    private void menuBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBtActionPerformed

        this.dispose();
        new MenuForm().setVisible(true);
    }//GEN-LAST:event_menuBtActionPerformed

    private void statusBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusBtActionPerformed

        this.dispose();
        new OrderForm().setVisible(true);
    }//GEN-LAST:event_statusBtActionPerformed

    private void reviewBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reviewBtActionPerformed

        this.dispose();
        new ReviewForm().setVisible(true);
    }//GEN-LAST:event_reviewBtActionPerformed

    private void infoUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoUpdateActionPerformed
        this.dispose();
        new CustomerInfoUpdate().setVisible(true);
    }//GEN-LAST:event_infoUpdateActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton infoUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblwelcome;
    private javax.swing.JLabel logout;
    private javax.swing.JButton menuBt;
    private javax.swing.JLabel name;
    private javax.swing.JButton reviewBt;
    private javax.swing.JButton statusBt;
    // End of variables declaration//GEN-END:variables
}
