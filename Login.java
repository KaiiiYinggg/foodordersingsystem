/* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Manreen
 */
package foodorderingsystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login1
     */
    private String loggedInRunner;

    private static String username;
    private String pageReturn;

    public static String getUsername() {
        return Login.username;
    }

    public static void setUsername(String username) {
        Login.username = username;
    }

    public String getPageReturn() {
        return pageReturn;
    }

    public void setPageReturn(String pageReturn) {
        this.pageReturn = pageReturn;
    }

    public Login() {
        setUndecorated(true);
        getContentPane().setBackground(new Color(252, 154, 67)); // Set background color
        initComponents();

        SwingUtilities.invokeLater(() -> {
            ImageIcon img1 = new ImageIcon(getClass().getResource("/files/p1.jpg"));
            int labelWidth = jLabel1.getWidth();
            int labelHeight = jLabel1.getHeight();

            if (labelWidth > 0 && labelHeight > 0) {
                Image scaledImage1 = img1.getImage().getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
                jLabel1.setText("");
                jLabel1.setIcon(scaledIcon1);
            } else {
                // Set the original image if label size is not available yet
                jLabel1.setIcon(img1);
            }
        });

        ImageIcon img2 = new ImageIcon(getClass().getResource("/files/p2.png"));
        Image scaledImage2 = img2.getImage().getScaledInstance(400, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        jLabel3.setText("");
        jLabel3.setIcon(scaledIcon2);

        ImageIcon img3 = new ImageIcon(getClass().getResource("/files/p3.png"));
        Image scaledImage3 = img3.getImage().getScaledInstance(400, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
        jLabel4.setText("");
        jLabel4.setIcon(scaledIcon3);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_Login_Login = new javax.swing.JLabel();
        jLabel_Login_Email = new javax.swing.JLabel();
        jTextField_Login_Username = new javax.swing.JTextField();
        jLabel_Login_Password = new javax.swing.JLabel();
        jPasswordField_Login_Password = new javax.swing.JPasswordField();
        jButton_Login_Login = new javax.swing.JButton();
        jButton_Login_Register = new javax.swing.JButton();
        jLable_p1 = new javax.swing.JLabel();
        jLabel_p2 = new javax.swing.JLabel();
        jLabel_p3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel_Close = new javax.swing.JLabel();
        jLabel_Minimize = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Page");
        setBackground(new java.awt.Color(252, 154, 63));
        setMinimumSize(new java.awt.Dimension(940, 530));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(252, 154, 63));

        jPanel2.setBackground(new java.awt.Color(252, 174, 100));
        jPanel2.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(327, 331));

        jLabel_Login_Login.setFont(new java.awt.Font("Rockwell", 3, 36)); // NOI18N
        jLabel_Login_Login.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Login_Login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Login_Login.setText("Login");
        jLabel_Login_Login.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel_Login_Email.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel_Login_Email.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Login_Email.setText("Username:");

        jTextField_Login_Username.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jTextField_Login_Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Login_UsernameActionPerformed(evt);
            }
        });

        jLabel_Login_Password.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jLabel_Login_Password.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Login_Password.setText("Password:");

        jPasswordField_Login_Password.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N

        jButton_Login_Login.setBackground(new java.awt.Color(253, 125, 0));
        jButton_Login_Login.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        jButton_Login_Login.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Login_Login.setText("Login");
        jButton_Login_Login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton_Login_Login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Login_Login.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_Login_Login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_Login_LoginMouseClicked(evt);
            }
        });

        jButton_Login_Register.setBackground(new java.awt.Color(153, 255, 153));
        jButton_Login_Register.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        jButton_Login_Register.setText("Don't have an account? Register now.");
        jButton_Login_Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Login_RegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel_Login_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 108, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_Login_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Login_Password))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton_Login_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextField_Login_Username)
                            .addComponent(jPasswordField_Login_Password))))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jButton_Login_Register)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(131, Short.MAX_VALUE)
                .addComponent(jLabel_Login_Login)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Login_Email)
                    .addComponent(jTextField_Login_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_Login_Password)
                    .addComponent(jPasswordField_Login_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jButton_Login_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Login_Register)
                .addGap(50, 50, 50))
        );

        jLable_p1.setPreferredSize(new java.awt.Dimension(949, 502));

        jLabel1.setText("jLabel1");
        jLabel1.setMaximumSize(new java.awt.Dimension(653, 337));
        jLabel1.setMinimumSize(new java.awt.Dimension(653, 337));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("jLabel3");

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_p2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_p3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel4))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(174, 174, 174)
                        .addComponent(jLable_p1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(181, 181, 181)
                                    .addComponent(jLabel2)
                                    .addGap(374, 374, 374))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addGap(376, 376, 376)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4))
                                    .addGap(163, 163, 163)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLable_p1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_p2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_p3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel_p2.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 980, 530));

        jLabel_Close.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel_Close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Close.setText("X");
        jLabel_Close.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel_Close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_CloseMouseClicked(evt);
            }
        });
        getContentPane().add(jLabel_Close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 0, 20, 34));
        jLabel_Close.getAccessibleContext().setAccessibleParent(jLabel_Close);

        jLabel_Minimize.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel_Minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Minimize.setText("_");
        jLabel_Minimize.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel_Minimize.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel_Minimize.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jLabel_Minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_MinimizeMouseClicked(evt);
            }
        });
        getContentPane().add(jLabel_Minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 0, 20, 34));
        jLabel_Minimize.getAccessibleContext().setAccessibleParent(jLabel_Minimize);

        setSize(new java.awt.Dimension(1055, 530));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_Login_UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Login_UsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Login_UsernameActionPerformed

    private void jButton_Login_RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Login_RegisterActionPerformed
        CustomerRegistration next = new CustomerRegistration();
        next.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButton_Login_RegisterActionPerformed

    private void jButton_Login_LoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_Login_LoginMouseClicked
        try {
            if (verifyUser() == true) {
                JOptionPane.showMessageDialog(this, "User logged in successfully!");
                this.dispose();

                switch (getPageReturn()) {
                    case "custRegistration.txt":
                        new CustomerMenu().setVisible(true);
                        this.dispose();
                        break;
                    case "vendorRegistration.txt":
                        new MainMenu().setVisible(true);
                        this.dispose();
                        break;
                    case "runnerRegistration.txt":
                        DL_DashboardPage dashboard = new DL_DashboardPage();
                        dashboard.setVisible(true);
                        break;
                    case "admin":
                        new AdminChoose().setVisible(true);
                        this.dispose();
                        break;

                    case "manager":
                        new ManagerChoose().setVisible(true);
                        this.dispose();
                        break;

                }

            } else {
                JOptionPane.showMessageDialog(this, "Incorrect credentials. Please try again!");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_Login_LoginMouseClicked

    private void jLabel_MinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_MinimizeMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel_MinimizeMouseClicked

    private void jLabel_CloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_CloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel_CloseMouseClicked

    public boolean verifyUser() throws IOException {
        String[] customer = {"custRegistration.txt", "11", "10", "12"};
        String[] vendor = {"vendorRegistration.txt", "8", "7", "9"};
        String[] runner = {"runnerRegistration.txt", "11", "10", "12"};
        String[] manager = {"manager", "mng123", "manager123"};
        String[] admin = {"admin", "admin123", "admin123123"};

        File file = null;
        int usernameIndex = -1;
        int passwordIndex = -1;
        int length = -1;

        String username = jTextField_Login_Username.getText();
        setUsername(username);
        String password = new String(jPasswordField_Login_Password.getPassword());

        if (username.startsWith("c")) {
            file = new File(customer[0]);
            usernameIndex = Integer.parseInt(customer[1]);
            passwordIndex = Integer.parseInt(customer[2]);
            length = Integer.parseInt(customer[3]);
            setPageReturn(customer[0]);
        } else if (username.startsWith("v")) {
            file = new File(vendor[0]);
            usernameIndex = Integer.parseInt(vendor[1]);
            passwordIndex = Integer.parseInt(vendor[2]);
            length = Integer.parseInt(vendor[3]);
            setPageReturn(vendor[0]);
        } else if (username.startsWith("r")) {
            file = new File(runner[0]);
            usernameIndex = Integer.parseInt(runner[1]);
            passwordIndex = Integer.parseInt(runner[2]);
            length = Integer.parseInt(runner[3]);
            setPageReturn(runner[0]);
        } else if (username.equals(manager[1]) && password.equals(manager[2])) {
            setPageReturn(manager[0]);
            return true;
        } else if (username.equals(admin[1]) && password.equals(admin[2])) {
            setPageReturn(admin[0]);
            return true;
        } else {
            return false;
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.trim().split("\\|");

                if (row.length > Math.max(usernameIndex, passwordIndex)) {
                    String fileUsername = row[usernameIndex];
                    String filePassword = row[passwordIndex];

                    if (username.equals(fileUsername) && password.equals(filePassword)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Login_Login;
    private javax.swing.JButton jButton_Login_Register;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_Close;
    private javax.swing.JLabel jLabel_Login_Email;
    private javax.swing.JLabel jLabel_Login_Login;
    private javax.swing.JLabel jLabel_Login_Password;
    private javax.swing.JLabel jLabel_Minimize;
    private javax.swing.JLabel jLabel_p2;
    private javax.swing.JLabel jLabel_p3;
    private javax.swing.JLabel jLable_p1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField_Login_Password;
    private javax.swing.JTextField jTextField_Login_Username;
    // End of variables declaration//GEN-END:variables
}
