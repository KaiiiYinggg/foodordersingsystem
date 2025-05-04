package foodorderingsystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RevenueDashboard extends javax.swing.JFrame {

    private final List<Order> orders = new ArrayList<>();
    private final DefaultTableModel leaderboardModel;

    public RevenueDashboard() {
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
        setSize(600, 500); 
        setLocationRelativeTo(null);
        
        leaderboardModel = new DefaultTableModel(new Object[]{"Rank", "Food", "Orders"}, 0);
        LeaderBoardTable.setModel(leaderboardModel);
        loadData();
        updateDashboard("All"); // load data for "All" by default
        MonthSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MonthSelectionActionPerformed(evt);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        totalSalesLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        totalCustomersLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        totalRevenueLabel = new javax.swing.JLabel();
        MonthSelection = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        LeaderBoardTable = new javax.swing.JTable();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Sitka Text", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Revenue Dashboard");

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 16)); // NOI18N
        jLabel2.setText("Sales");

        totalSalesLabel.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        totalSalesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSalesLabel.setText("0");

        jLabel3.setFont(new java.awt.Font("Sitka Text", 0, 16)); // NOI18N
        jLabel3.setText("Customer");

        totalCustomersLabel.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        totalCustomersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalCustomersLabel.setText("0");

        jLabel4.setFont(new java.awt.Font("Sitka Text", 0, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Revenue");

        totalRevenueLabel.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        totalRevenueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalRevenueLabel.setText("0.00");

        MonthSelection.setBackground(new java.awt.Color(77, 214, 77));
        MonthSelection.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        MonthSelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        MonthSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonthSelectionActionPerformed(evt);
            }
        });

        LeaderBoardTable.setBackground(new java.awt.Color(161, 204, 153));
        LeaderBoardTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                " Rank", "Name", "Orders"
            }
        ));
        jScrollPane1.setViewportView(LeaderBoardTable);

        back.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalSalesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(totalCustomersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(totalRevenueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(MonthSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back)
                        .addGap(85, 85, 85)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(back)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalSalesLabel)
                    .addComponent(totalCustomersLabel)
                    .addComponent(totalRevenueLabel)
                    .addComponent(MonthSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadData() {
        DefaultTableModel model = (DefaultTableModel) LeaderBoardTable.getModel();
        model.setRowCount(0); // Clear table data

        File file = new File("order.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "File not found: " + file.getAbsolutePath());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            double amount = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length >= 13) { // Ensure correct data format
                    amount = Double.parseDouble(data[5].replace("RM ", ""));
                    orders.add(new Order(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], amount, data[6], data[7]));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading orders: " + e.getMessage());
            e.printStackTrace();
        }
        // Debug: Print orders list
        System.out.println("Orders loaded: " + orders.size());
    }

    private void updateDashboard(String selectedMonth) {
        int totalSales = 0;
        Set<String> uniqueCustomers = new HashSet<>();
        double totalRevenue = 0.0;

        Map<String, Integer> foodOrders = new HashMap<>();

        for (Order order : orders) {
            if (selectedMonth.equals("All") || order.getMonth().equalsIgnoreCase(selectedMonth)) {
                if (order.getStatus().equalsIgnoreCase("Completed")) {
                    totalSales++;
                    uniqueCustomers.add(order.getCustomerId());
                    totalRevenue += order.getAmount();

                    for (String food : order.getFoodItems().split(",")) {
                        foodOrders.put(food, foodOrders.getOrDefault(food, 0) + 1);
                    }
                }
            }
        }

        // Debug: Print food orders map
        System.out.println("Food Orders: " + foodOrders);

        // Update labels
        totalSalesLabel.setText(String.valueOf(totalSales));
        totalCustomersLabel.setText(String.valueOf(uniqueCustomers.size()));
        totalRevenueLabel.setText(new DecimalFormat("0.00").format(totalRevenue));

        // Update leaderboard
        leaderboardModel.setRowCount(0); // Clear existing rows
        foodOrders.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .forEach(entry -> leaderboardModel.addRow(new Object[]{
            leaderboardModel.getRowCount() + 1, entry.getKey(), entry.getValue()
        }));
    }

    private void MonthSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonthSelectionActionPerformed
        String selectedMonth = MonthSelection.getSelectedItem().toString();
        updateDashboard(selectedMonth);
    }//GEN-LAST:event_MonthSelectionActionPerformed

    // Nested Order class
    public class Order {

        private String orderId;
        private String customerId;
        private String foodItems;
        private int quantity;
        private String orderType;
        private double amount;
        private String status;
        private String date;

        // Constructor for Order
        public Order(String orderId, String customerId, String foodItems, int quantity, String orderType, double amount, String status, String date) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.foodItems = foodItems;
            this.quantity = quantity;
            this.orderType = orderType;
            this.amount = amount;
            this.status = status;
            this.date = date;
        }

        // Method to get the month from the date
        public String getMonth() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date orderDate = sdf.parse(date);
                SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
                return monthFormat.format(orderDate);
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        }

        public String getStatus() {
            return status;
        }

        public String getCustomerId() {
            return customerId;
        }

        public double getAmount() {
            return amount;
        }

        public String getFoodItems() {
            return foodItems;
        }
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable LeaderBoardTable;
    private javax.swing.JComboBox<String> MonthSelection;
    private javax.swing.JLabel back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel totalCustomersLabel;
    private javax.swing.JLabel totalRevenueLabel;
    private javax.swing.JLabel totalSalesLabel;
    // End of variables declaration//GEN-END:variables
}
