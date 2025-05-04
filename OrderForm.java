package foodorderingsystem;

import domain.OrderObject;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OrderForm extends javax.swing.JFrame {

    private static JLabel notificationLabel;
    OrderObject order = OrderObject.getInstance();
    private String orderID;
    private boolean statusDisplayed = false;
    Login login = new Login();
    private String customerID;

    public OrderForm() {

        initComponents();
        getContentPane().setBackground(new Color(252, 154, 63)); // Set background color
        customerID = login.getUsername();
        System.out.println(login.getUsername());
        this.orderID = order.getOrderId();
        Timer timer = new Timer(1000, e -> checkOrderStatus());
        timer.start();

        boolean pngChange = order.isPngChange();

        if (order.isPngChange()) {
            notificationLabel = new JLabel(new ImageIcon(getScaledIcon("/files/notification_red.png", 48, 50)));
        } else {
            notificationLabel = new JLabel(new ImageIcon(getScaledIcon("/files/notification.png", 55, 50)));
        }

        notificationLabel.setBounds(20, 20, 50, 50);
        ImageIconPanel.add(notificationLabel);
        notificationLabel.revalidate();
        notificationLabel.repaint();
        notificationLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Receipt receipt = new Receipt();
                receipt.setVisible(true);
                order.setPngChange(false);
                notificationLabel.setIcon(new ImageIcon(getScaledIcon("/files/notification.png", 55, 50)));
                statusDisplayed = false; // Reset the flag when the customer views the notification
            }
        });

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
    }

    private void checkOrderStatus() {
        // Assuming you have the orderID available (e.g., stored in a variable or retrieved when the order is placed)
        File file = new File("order.txt");
        System.out.println(orderID);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data[0].equals(orderID)) { // Check if this is the order placed by the customer
                    String orderStatus = data[6]; // Get the current order status from the file
                    if (!orderStatus.equals("Pending") && !statusDisplayed) { // Only show if status hasn't been displayed
                        order.OrderNotification(orderID, orderStatus, customerID);
                        statusDisplayed = true; // Mark that status has been shown
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getScaledIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(MenuForm.class.getResource(path));
        return icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    private void currentOrderData(String filePath) {
        DefaultTableModel table = (DefaultTableModel) currentOrdertbl.getModel();
        table.setRowCount(0);

        try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {
            String read;
            while ((read = bf.readLine()) != null) {
                String[] data = read.split("\\|"); // Split by comma
                if (data[1].equals(customerID)) {

                    String[] columnSelected = {data[0], data[2], data[3], data[6]};

                    if (!data[6].equals("Done")) {
                        table.addRow(columnSelected); // Add the row to the table
                    }
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
    }

    private void orderHistoryData(String filePath) {
        DefaultTableModel table = (DefaultTableModel) orderHistorytbl.getModel();
        table.setRowCount(0);

        try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {
            String read;
            while ((read = bf.readLine()) != null) {
                String[] data = read.split("\\|"); // Split by comma
                if (data[1].equals(customerID)) {

                    String[] columnSelected = {data[0], data[2], data[3], data[7], data[5]};
                    if (data[6].equals("Completed")) {
                        table.addRow(columnSelected); // Add the row to the table
                    }
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
    }

    private void transactionHistoryData() {
        DefaultTableModel table = (DefaultTableModel) transactionHistorytbl.getModel();
        table.setRowCount(0);

        try (BufferedReader bf = new BufferedReader(new FileReader("order.txt"))) {
            String read;
            while ((read = bf.readLine()) != null) {
                String[] data = read.split("\\|"); // Split by comma
                if (data[1].equals(customerID)) {

                    String[] columnSelected = {data[8], data[5], data[7], data[6]};
                    if (data[6].equals("Completed")) {//dont have if
                        table.addRow(columnSelected);
                        System.out.println(columnSelected);
                    }
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error processing file: " + e.getMessage());
        }
    }

    public void updateOrderFile(String filePath, String orderIDToDelete) {
        try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {
            StringBuilder updatedData = new StringBuilder();
            String read;

            // Read and filter the file
            while ((read = bf.readLine()) != null) {
                String[] data = read.split("\\|");
                if (!data[0].equals(orderIDToDelete)) {  // Skip the order to delete
                    updatedData.append(read).append("\n");
                }
            }

            // Write back to the file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                bw.write(updatedData.toString().trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating file: " + e.getMessage());
        }
    }

    public void cancelOrder() {
        DefaultTableModel table = (DefaultTableModel) currentOrdertbl.getModel();
        int selectedRow = currentOrdertbl.getSelectedRow();

        if (selectedRow != -1) {
            String orderID = table.getValueAt(selectedRow, 0).toString();//getvalueat(row,column)

            int option = JOptionPane.showConfirmDialog(this, "Are you sure to cancel order?", "Cancel Confirmation", JOptionPane.YES_NO_OPTION);

            if (option == (JOptionPane.YES_OPTION)) {
                table.removeRow(selectedRow);

                updateOrderFile("order.txt", orderID);
                JOptionPane.showMessageDialog(this, "Order cancel successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order to delete.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelBt = new javax.swing.JButton();
        backBt = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        orderHistorytbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        orderAgainBt = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        transactionHistorytbl = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        currentOrdertbl = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btCO = new javax.swing.JButton();
        btOH = new javax.swing.JButton();
        btTH = new javax.swing.JButton();
        ImageIconPanel = new javax.swing.JPanel();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cancelBt.setBackground(new java.awt.Color(255, 106, 0));
        cancelBt.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        cancelBt.setText("Cancel order");
        cancelBt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cancelBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtActionPerformed(evt);
            }
        });

        backBt.setBackground(new java.awt.Color(255, 106, 0));
        backBt.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        backBt.setText("Back");
        backBt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        backBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtActionPerformed(evt);
            }
        });

        orderHistorytbl.setBackground(new java.awt.Color(255, 165, 101));
        orderHistorytbl.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        orderHistorytbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "OrderID", "Food", "Quantity", "Date", "Total Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(orderHistorytbl);

        jLabel1.setFont(new java.awt.Font("Sitka Text", 0, 36)); // NOI18N
        jLabel1.setText("MY ORDER");

        jLabel2.setFont(new java.awt.Font("Sitka Text", 2, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("******Order history******");

        orderAgainBt.setBackground(new java.awt.Color(255, 106, 0));
        orderAgainBt.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        orderAgainBt.setText("Order again");
        orderAgainBt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        orderAgainBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderAgainBtActionPerformed(evt);
            }
        });

        transactionHistorytbl.setBackground(new java.awt.Color(255, 165, 101));
        transactionHistorytbl.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        transactionHistorytbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "TransactionID", "Total amount", "Transaction Date", "Order status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(transactionHistorytbl);

        jLabel3.setFont(new java.awt.Font("Sitka Text", 2, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("******Transaction History******");

        jLabel4.setFont(new java.awt.Font("Sitka Text", 2, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("******Current Order******");

        currentOrdertbl.setBackground(new java.awt.Color(255, 165, 101));
        currentOrdertbl.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        currentOrdertbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "OrderID", "Items", "Quantity", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(currentOrdertbl);

        jPanel1.setBackground(new java.awt.Color(255, 150, 76));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel5.setText("<<< Check >>>");

        btCO.setBackground(new java.awt.Color(255, 106, 0));
        btCO.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        btCO.setText("MY CURRENT ORDER");
        btCO.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btCO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCOActionPerformed(evt);
            }
        });

        btOH.setBackground(new java.awt.Color(255, 106, 0));
        btOH.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        btOH.setText("MY ORDER HISTORY");
        btOH.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btOH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOHActionPerformed(evt);
            }
        });

        btTH.setBackground(new java.awt.Color(255, 106, 0));
        btTH.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        btTH.setText("MY TRANSACTION HISTORY");
        btTH.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btTH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btOH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btCO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel5)
                .addGap(34, 34, 34)
                .addComponent(btCO)
                .addGap(77, 77, 77)
                .addComponent(btOH)
                .addGap(60, 60, 60)
                .addComponent(btTH)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ImageIconPanel.setBackground(new java.awt.Color(255, 150, 76));

        javax.swing.GroupLayout ImageIconPanelLayout = new javax.swing.GroupLayout(ImageIconPanel);
        ImageIconPanel.setLayout(ImageIconPanelLayout);
        ImageIconPanelLayout.setHorizontalGroup(
            ImageIconPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        ImageIconPanelLayout.setVerticalGroup(
            ImageIconPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        back.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(backBt, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cancelBt)
                                    .addComponent(orderAgainBt)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(328, 328, 328)))
                .addComponent(ImageIconPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ImageIconPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(back)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelBt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orderAgainBt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(backBt)
                        .addGap(14, 14, 14))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtActionPerformed
        // TODO add your handling code here:back to main menu
        this.dispose();
        new CustomerMenu().setVisible(true);
    }//GEN-LAST:event_backBtActionPerformed

    private void orderAgainBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderAgainBtActionPerformed
        this.dispose();
        new MenuForm().setVisible(true);
    }//GEN-LAST:event_orderAgainBtActionPerformed

    private void btCOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCOActionPerformed
        currentOrderData("order.txt");
    }//GEN-LAST:event_btCOActionPerformed

    private void btOHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOHActionPerformed
        orderHistoryData("order.txt");
    }//GEN-LAST:event_btOHActionPerformed

    private void btTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTHActionPerformed
        //formTransactionFile();
        transactionHistoryData();
    }//GEN-LAST:event_btTHActionPerformed

    private void cancelBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtActionPerformed
        cancelOrder();
    }//GEN-LAST:event_cancelBtActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ImageIconPanel;
    private javax.swing.JLabel back;
    private javax.swing.JButton backBt;
    private javax.swing.JButton btCO;
    private javax.swing.JButton btOH;
    private javax.swing.JButton btTH;
    private javax.swing.JButton cancelBt;
    private javax.swing.JTable currentOrdertbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton orderAgainBt;
    private javax.swing.JTable orderHistorytbl;
    private javax.swing.JTable transactionHistorytbl;
    // End of variables declaration//GEN-END:variables

}
