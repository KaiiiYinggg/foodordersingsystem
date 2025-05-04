package foodorderingsystem;

import domain.OrderObject;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OrderManagement extends javax.swing.JFrame {

    private String selectedOrderID;
    private String selectedCustomerID;
    private String selectedItems;
    private String selectedQuantity;
    private String selectedOrderType;
    private String selectedTotalAmount;
    private String selectedOrderStatus;
    private String selectedDate;
    private String transactionID;
    OrderObject order = OrderObject.getInstance();

    public String getSelectedOrderID() {
        return selectedOrderID;
    }

    public void setSelectedOrderID(String selectedOrderID) {
        this.selectedOrderID = selectedOrderID;
    }

    public String getSelectedCustomerID() {
        return selectedCustomerID;
    }

    public void setSelectedCustomerID(String selectedCustomerID) {
        this.selectedCustomerID = selectedCustomerID;
    }

    public String getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(String selectedItems) {
        this.selectedItems = selectedItems;
    }

    public String getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(String selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    public String getSelectedOrderType() {
        return selectedOrderType;
    }

    public void setSelectedOrderType(String selectedOrderType) {
        this.selectedOrderType = selectedOrderType;
    }

    public String getSelectedTotalAmount() {
        return selectedTotalAmount;
    }

    public void setSelectedTotalAmount(String selectedTotalAmount) {
        this.selectedTotalAmount = selectedTotalAmount;
    }

    public String getSelectedOrderStatus() {
        return selectedOrderStatus;
    }

    public void setSelectedOrderStatus(String selectedOrderStatus) {
        this.selectedOrderStatus = selectedOrderStatus;
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getTransactionID() {
        return selectedDate;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public OrderManagement() {
        initComponents();
        getContentPane().setBackground(new Color(200,249,190));
        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setBackground(new Color(77, 214, 77));
                button.setForeground(Color.WHITE);
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setFont(new Font("Rockwell", Font.PLAIN, 16));
            }
        }
        Login login = new Login();
        System.out.println(login.getUsername());
        ImageIcon img = new ImageIcon(getClass().getResource("/files/back.png"));
        Image scaledImage = img.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainMenu v = new MainMenu();
                v.setVisible(true);
                dispose();
            }
        });

        back.setText("");
        back.setIcon(scaledIcon);
        setSize(800, 500);
        setLocationRelativeTo(null);

        loadOrderData();//load orders from the file
        AcceptButton.addActionListener(evt -> updateOrderStatus("Accepted"));
        CancelButton.addActionListener(evt -> updateOrderStatus("Cancelled"));
        PreparedButton.addActionListener(evt -> updateOrderStatus("Order Prepared"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        AcceptButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        PreparedButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        orderTable.setBackground(new java.awt.Color(161, 204, 153));
        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Order ID", "Customer ID", "Items", "Quantity", "Order Type", "Total Amount", "Order Status", "Current Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(orderTable);

        AcceptButton.setText("Accept");
        AcceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcceptButtonActionPerformed(evt);
            }
        });

        CancelButton.setText("Cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Sitka Text", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Order Management");

        back.setText("jLabel2");

        PreparedButton.setBackground(new java.awt.Color(77, 214, 77));
        PreparedButton.setText("Order Prepared");
        PreparedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreparedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back)
                        .addGap(169, 169, 169)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PreparedButton)
                .addGap(28, 28, 28)
                .addComponent(AcceptButton)
                .addGap(18, 18, 18)
                .addComponent(CancelButton)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(back))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AcceptButton)
                    .addComponent(CancelButton)
                    .addComponent(PreparedButton))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed

    }//GEN-LAST:event_CancelButtonActionPerformed

    private void AcceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcceptButtonActionPerformed

    }//GEN-LAST:event_AcceptButtonActionPerformed

    private void PreparedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreparedButtonActionPerformed

    }//GEN-LAST:event_PreparedButtonActionPerformed


    // Method to load orders from the file and populate the JTable
    private void loadOrderData() {

        DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
        model.setRowCount(0);

        File file = new File("order.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "File not found: " + file.getAbsolutePath());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 12) {
                    String status = data[6];
                    if (data[6].equals("Accepted") || status.equals("Pending")) {
                        model.addRow(new Object[]{data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]});
                    }

                    if ("Pending".equals(status)) {
                        order.playSound(MenuForm.class.getResource("/files/new order.wav"));
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading orders: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateOrderStatus(String status) {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an order first.");
            return;
        }
        String orderId = orderTable.getValueAt(selectedRow, 0).toString();
        String customerId = orderTable.getValueAt(selectedRow, 1).toString();
        String items = orderTable.getValueAt(selectedRow, 2).toString();
        String quantity = orderTable.getValueAt(selectedRow, 3).toString();
        String orderType = orderTable.getValueAt(selectedRow, 4).toString();
        String totalAmount = orderTable.getValueAt(selectedRow, 5).toString();
        String currentStatus = orderTable.getValueAt(selectedRow, 6).toString();
        String date = orderTable.getValueAt(selectedRow, 7).toString();

        if (currentStatus.equals("Order Prepared")) {
            JOptionPane.showMessageDialog(null, "You are not allowed to Accept/Cancel Order once Order Prepared", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        orderTable.setValueAt(status, selectedRow, 6);

        if (!status.equals("Pending")) {
            order.setCancelOrder(true);
            order.setOrderId(orderId);
        }

        if (status.equals("Order Prepared") && (orderType.equals("Dine in") || orderType.equals("Take away"))) {
            status = "Completed";
        }
        double amount = Double.parseDouble(totalAmount.replace("RM ", ""));

        updateBalance(customerId, status, amount);
        updateOrderInFile(orderId, customerId, items, quantity, orderType, totalAmount, status, date);
        loadOrderData();
    }

    private void updateBalance(String customerId, String status, double totalAmount) {
        if (status.equals("Cancelled")) {
            Double currentBalance = 0.00;
            Double newBalance = 0.00;
            StringBuilder updatedData = new StringBuilder();
            boolean found = false;

            try (BufferedReader br = new BufferedReader(new FileReader("Balance.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] columns = line.split("\\|");
                    if (columns.length >= 2 && columns[0].trim().equals(customerId)) {
                        currentBalance = Double.valueOf(columns[1]);
                        newBalance = currentBalance + totalAmount;
                        updatedData.append(columns[0]).append("|").append(String.format("%.2f", newBalance)).append("\n");
                        found = true;
                    } else {
                        updatedData.append(line).append("\n");
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (found) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("Balance.txt"))) {
                    bw.write(updatedData.toString());
                    JOptionPane.showMessageDialog(null, "Refund Successful! New Balance: RM " + String.format("%.2f", totalAmount));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred while writing to the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Customer ID not found, refund failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateOrderInFile(String orderId, String customerId, String items, String quantity, String orderType, String totalAmount, String status, String date) {
        File inputFile = new File("order.txt");
        StringBuilder updatedContent = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data[0].equals(orderId)) {
                    if (data[0].equals(orderId)) {
                        updatedContent.append(orderId).append("|")
                                .append(customerId).append("|")
                                .append(items).append("|")
                                .append(quantity).append("|")
                                .append(orderType).append("|")
                                .append(totalAmount).append("|")
                                .append(status).append("|")
                                .append(date).append("|")
                                .append(data[8]).append("|")
                                .append(data[9]).append("|")
                                .append(data[10]).append("|")
                                .append(data[11]).append("|")
                                .append(data[12]).append("\n");
                    }

                } else {
                    updatedContent.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading order file: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            writer.write(updatedContent.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to order file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AcceptButton;
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton PreparedButton;
    private javax.swing.JLabel back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable orderTable;
    // End of variables declaration//GEN-END:variables
}
