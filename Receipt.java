/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package foodorderingsystem;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author HP
 */
public class Receipt extends javax.swing.JFrame {

    /**
     * Creates new form Receipt
     */
    private final JPanel receiptInnerPanel;

    public Receipt() {
        initComponents();
        getContentPane().setBackground(new Color(255, 156, 63)); // Set background color
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        receiptInnerPanel = new JPanel();
        receiptInnerPanel.setLayout(new BoxLayout(receiptInnerPanel, BoxLayout.Y_AXIS));

        receiptPanel.setViewportView(receiptInnerPanel);
        Login login = new Login();
        System.out.println(login.getUsername());
        ImageIcon img = new ImageIcon(getClass().getResource("/files/back.png"));
        Image scaledImage = img.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);//with 200px,height 200px
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                OrderForm c = new OrderForm();
                c.setVisible(true);
                dispose();
            }
        });

        back.setText("");
        back.setIcon(scaledIcon);

        addReceipt(login.getUsername());
        setLocationRelativeTo(null);
    }

    private void addReceipt(String customerId) {
        ArrayList<String[]> orderList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("order.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split("\\|");
                if (columns.length >= 8 && columns[1].trim().equals(customerId)) {
                    orderList.add(columns);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "An error occurred while reading the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        receiptInnerPanel.removeAll();
        receiptInnerPanel.setLayout(new BoxLayout(receiptInnerPanel, BoxLayout.Y_AXIS)); // Set vertical layout

        if (orderList.isEmpty()) {
            JLabel noOrdersLabel = new JLabel("No orders found for Customer ID: " + customerId, SwingConstants.CENTER);
            noOrdersLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            noOrdersLabel.setPreferredSize(new Dimension(receiptInnerPanel.getWidth(), 50));
            receiptInnerPanel.add(noOrdersLabel);
        } else {
            for (String[] order : orderList) {
                JPanel orderContainer = new JPanel(new BorderLayout());
                orderContainer.setPreferredSize(new Dimension(receiptInnerPanel.getWidth(), 50));
                orderContainer.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding
                orderContainer.setBackground(new Color(255, 189, 127)); // Light gray background

                JLabel orderLabel = new JLabel("Order ID: " + order[0], SwingConstants.LEFT);
                orderLabel.setFont(new Font("Rockwell", Font.BOLD, 12)); // Font size and bold
                orderLabel.setForeground(new Color(50, 50, 50)); // Dark gray text color
                orderLabel.setOpaque(true);
                orderLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                orderLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding inside label

                orderLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        showReceiptDialog(order);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        orderLabel.setBackground(new Color(200, 200, 200)); // Darker gray on hover
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        orderLabel.setBackground(new Color(230, 230, 230)); // Restore default background
                    }
                });

                orderContainer.add(orderLabel, BorderLayout.CENTER);
                receiptInnerPanel.add(orderContainer);
            }
        }

        // Refresh the panel to reflect changes
        receiptInnerPanel.revalidate();
        receiptInnerPanel.repaint();
    }

    public void addTopUpReceipt(String customerId, double topupAmount, double currentBalance, double newBalance) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentDate = now.format(dateFormatter);
        String currentTime = now.format(timeFormatter);

        StringBuilder receiptText = new StringBuilder();
        receiptText.append("********** Receipt **********\n");
        receiptText.append("Customer ID: ").append(customerId).append("\n");
        receiptText.append("Current Balance ID: ").append(currentBalance).append("\n");
        receiptText.append("Top Up Amount: ").append(topupAmount).append("\n");
        receiptText.append("New Balance: ").append(newBalance).append("\n");
        receiptText.append("Date: ").append(currentDate).append("\n");
        receiptText.append("Time: ").append(currentTime).append("\n");
        receiptText.append("****************************");

        JTextArea receiptArea = new JTextArea(receiptText.toString());
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JPanel buttonPanel = new JPanel();
        JButton printButton = new JButton("Print");
        JButton savePdfButton = new JButton("Save as PDF");
        buttonPanel.add(printButton);
        buttonPanel.add(savePdfButton);

        // Add button panel to the receipt area
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new JScrollPane(receiptArea), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    receiptArea.print();
                } catch (PrinterException ex) {
                    Logger.getLogger(Receipt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Save PDF Action
        savePdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Save content to a temporary HTML file
                    File tempHtml = File.createTempFile("receipt", ".html");
                    FileWriter writer = new FileWriter(tempHtml);
                    writer.write("<html><body><pre>" + receiptArea.getText() + "</pre></body></html>");
                    writer.close();

                    // Open the HTML file in the system's default browser
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(tempHtml);
                        JOptionPane.showMessageDialog(Receipt.this, "Use your browser's 'Print' option to save as PDF.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(Receipt.this, "Error creating temporary file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JOptionPane.showMessageDialog(
                this,
                mainPanel,
                "Receipt Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showReceiptDialog(String[] orderDetails) {
        // Display receipt details in a dialog
        StringBuilder receiptText = new StringBuilder();
        receiptText.append("********** Receipt **********\n");
        receiptText.append("Order ID: ").append(orderDetails[0]).append("\n");
        receiptText.append("Customer ID: ").append(orderDetails[1]).append("\n");
        receiptText.append("Items: ").append(orderDetails[2]).append("\n");
        receiptText.append("Total Quantity: ").append(orderDetails[3]).append("\n");
        receiptText.append("Order Type: ").append(orderDetails[4]).append("\n");
        receiptText.append("Total Amount: ").append(orderDetails[5]).append("\n");
        receiptText.append("Status: ").append(orderDetails[6]).append("\n");
        receiptText.append("Date: ").append(orderDetails[7]).append("\n");
        receiptText.append("****************************");

        JTextArea receiptArea = new JTextArea(receiptText.toString());
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JPanel buttonPanel = new JPanel();
        JButton printButton = new JButton("Print");
        JButton savePdfButton = new JButton("Save as PDF");
        buttonPanel.add(printButton);
        buttonPanel.add(savePdfButton);

        // Add button panel to the receipt area
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new JScrollPane(receiptArea), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    receiptArea.print();
                } catch (PrinterException ex) {
                    Logger.getLogger(Receipt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Save PDF Action
        savePdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Save content to a temporary HTML file
                    File tempHtml = File.createTempFile("receipt", ".html");
                    FileWriter writer = new FileWriter(tempHtml);
                    writer.write("<html><body><pre>" + receiptArea.getText() + "</pre></body></html>");
                    writer.close();

                    // Open the HTML file in the system's default browser
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(tempHtml);
                        JOptionPane.showMessageDialog(Receipt.this, "Use your browser's 'Print' option to save as PDF.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(Receipt.this, "Error creating temporary file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JOptionPane.showMessageDialog(
                this,
                mainPanel,
                "Receipt Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        receiptPanel = new javax.swing.JScrollPane();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Receipt");

        receiptPanel.setBackground(new java.awt.Color(255, 156, 63));

        back.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(receiptPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back)
                .addGap(179, 179, 179)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(back))
                .addGap(18, 18, 18)
                .addComponent(receiptPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane receiptPanel;
    // End of variables declaration//GEN-END:variables
}
