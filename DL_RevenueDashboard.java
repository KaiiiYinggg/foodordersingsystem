package foodorderingsystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manreen
 */
public class DL_RevenueDashboard extends javax.swing.JFrame {

    /**
     * Creates new form DL_RevenueDashboard
     */
    String username;

    public DL_RevenueDashboard() {
        initComponents();
        Login login = new Login();
        username = login.getUsername();
        System.out.println(username);
        loadTable();

        ImageIcon img = new ImageIcon(getClass().getResource("/files/back.png"));
        Image scaledImage = img.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DL_DashboardPage c = new DL_DashboardPage();
                c.setVisible(true);
                dispose();
            }
        });

        back.setText("");
        back.setIcon(scaledIcon);
        setLocationRelativeTo(null);
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
        jLabel_RevenueDashboard = new javax.swing.JLabel();
        jComboBox_CheckRevenue = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Revenue = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        countOfJob = new javax.swing.JLabel();
        basic = new javax.swing.JLabel();
        bonus = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        totalEarn = new javax.swing.JLabel();
        loadTable = new javax.swing.JButton();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 193));

        jLabel_RevenueDashboard.setFont(new java.awt.Font("Sitka Text", 0, 36)); // NOI18N
        jLabel_RevenueDashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_RevenueDashboard.setText("Revenue Dashboard");

        jComboBox_CheckRevenue.setBackground(new java.awt.Color(151, 214, 139));
        jComboBox_CheckRevenue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Daily", "Weekly", "Monthly", "Yearly", " " }));
        jComboBox_CheckRevenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_CheckRevenueActionPerformed(evt);
            }
        });

        jLabel1.setText("Choose period:");

        jTable_Revenue.setBackground(new java.awt.Color(161, 204, 153));
        jTable_Revenue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Date", "Time", "OrderId"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable_Revenue);

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel2.setText("Count");

        jLabel3.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel3.setText("Basic");

        jLabel4.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel4.setText("Bonus");

        countOfJob.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        countOfJob.setText("jLabel5");

        basic.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        basic.setText("jLabel6");

        bonus.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        bonus.setText("jLabel7");

        jLabel9.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel9.setText("Total Earn");

        totalEarn.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        totalEarn.setText("jLabel10");

        loadTable.setBackground(new java.awt.Color(77, 214, 77));
        loadTable.setFont(new java.awt.Font("Rockwell", 0, 16)); // NOI18N
        loadTable.setText("Load Table");
        loadTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadTableActionPerformed(evt);
            }
        });

        back.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_RevenueDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1168, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_CheckRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(loadTable))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(169, 169, 169)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(314, 314, 314)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(bonus)
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(countOfJob)
                                                .addGap(121, 121, 121)
                                                .addComponent(basic)))
                                        .addGap(108, 108, 108)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel8))
                                            .addComponent(totalEarn))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_RevenueDashboard)
                    .addComponent(back))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_CheckRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(loadTable))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countOfJob)
                    .addComponent(basic)
                    .addComponent(bonus)
                    .addComponent(totalEarn))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jComboBox_CheckRevenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_CheckRevenueActionPerformed
        String selectedPeriod = (String) jComboBox_CheckRevenue.getSelectedItem(); // Get the selected item from the combo box
        displayTaskHistory(selectedPeriod); // Call the method to display tasks based on the selected period
    }//GEN-LAST:event_jComboBox_CheckRevenueActionPerformed

    private void loadTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadTableActionPerformed
        loadTable();
        basic.setText("0.00");
        bonus.setText("0.00");
        countOfJob.setText("0.00");
        totalEarn.setText("0.00");
    }//GEN-LAST:event_loadTableActionPerformed

    private boolean isTaskInPeriod(String taskDate, String period) {
        try {
            LocalDate date = LocalDate.parse(taskDate);
            LocalDate today = LocalDate.now();

            System.out.println("Checking Task Date: " + taskDate + " | Period: " + period);

            switch (period) {
                case "Daily":
                    return date.isEqual(today);
                case "Weekly":
                    return date.isAfter(today.minusDays(7)) && !date.isAfter(today);
                case "Monthly":
                    YearMonth currentMonth = YearMonth.from(today);
                    YearMonth taskMonth = YearMonth.from(date);
                    System.out.println("Task Month: " + taskMonth + " | Current Month: " + currentMonth);
                    return taskMonth.equals(currentMonth);
                case "Yearly":
                    LocalDate firstDayOfYear = today.withDayOfYear(1);
                    return !date.isBefore(firstDayOfYear) && !date.isAfter(today);
                default:
                    return false;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + taskDate);
            return false;
        }
    }

    private void displayTaskHistory(String period) {
        // Clear the current table
        DefaultTableModel model = (DefaultTableModel) jTable_Revenue.getModel();
        model.setRowCount(0); // Clear existing rows

        // Read tasks from the text file
        try (BufferedReader br = new BufferedReader(new FileReader("order.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                String taskDate = data[7];
                String taskStatus = data[6]; 
                if (isTaskInPeriod(taskDate, period)) {
                    if (data[11].equals(username) && taskStatus.equalsIgnoreCase("Completed")) {
                        model.addRow(new Object[]{data[7], data[10], data[0]});
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading tasks file.");
        }

        // Call calculateStatistics to update the revenue summary
        calculateStatistics(period);
    }

    private void calculateStatistics(String period) {
        double totalEarnings = 0.0;
        double totalBonus = 0.0;
        int completedDeliveries = 0;
        double totalBasic = 0.0;

        Map<String, List<String>> dailyOrders = new HashMap<>();
        Set<String> activeDays = new HashSet<>();

        String filePath = "order.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length < 12) {
                    continue;
                }

                String orderStatus = data[6].trim();
                String orderDate = data[7].trim();
                String orderTime = data[10].trim();
                String runnerID = data[11].trim();

                if (runnerID.equals(username) && orderStatus.equals("Completed") && isTaskInPeriod(orderDate, period)) {
                    completedDeliveries++;
                    totalBasic += 5.90;
                    activeDays.add(orderDate);

                    dailyOrders.computeIfAbsent(orderDate, k -> new ArrayList<>()).add(orderTime);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading order file: " + e.getMessage());
            return;
        }

        for (Map.Entry<String, List<String>> entry : dailyOrders.entrySet()) {
            String date = entry.getKey();
            List<String> times = entry.getValue();

            boolean bonusAdded = false;

            // Check if there are 15 or more orders
            if (times.size() >= 15 && !bonusAdded) {
                double dailyBonus = (5.90 * times.size()) * 0.015;
                totalBonus += dailyBonus;
                bonusAdded = true; // Mark bonus as added
            }

            // Check if there are more than 1 order and time difference is more than 8 hours
            if (times.size() > 1 && !bonusAdded) {
                String firstTime = times.get(0);
                String lastTime = times.get(times.size() - 1);

                if (isMoreThan8Hours(firstTime, lastTime) && times.size() >= 15) {
                    double dailyBonus = (5.90 * times.size()) * 0.015;
                    totalBonus += dailyBonus;
                    bonusAdded = true; // Mark bonus as added
                }
            }

            int activeDaysCount = activeDays.size();
            if (period.equals("Weekly") && activeDaysCount >= 7) {
                totalBonus += totalBasic * 0.02;
            }
            if (period.equals("Monthly") && activeDaysCount >= 30) {
                totalBonus += totalBasic * 0.025;
            }

            totalEarnings = totalBasic + totalBonus;

            countOfJob.setText(String.valueOf(completedDeliveries));
            basic.setText(String.format("RM %.2f", totalBasic));
            bonus.setText(String.format("RM %.2f", totalBonus));
            totalEarn.setText(String.format("RM %.2f", totalEarnings));
        }
    }

    private boolean isMoreThan8Hours(String startTime, String endTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date start = sdf.parse(startTime);
            Date end = sdf.parse(endTime);
            long difference = end.getTime() - start.getTime();
            return difference >= 8 * 60 * 60 * 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) jTable_Revenue.getModel();
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
                if (data.length >= 13) {
                    String taskStatus = data[6]; 
                    if (data[11].equals(username) && taskStatus.equalsIgnoreCase("Completed")) {
                        model.addRow(new Object[]{data[7], data[10], data[0]});
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading orders: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JLabel basic;
    private javax.swing.JLabel bonus;
    private javax.swing.JLabel countOfJob;
    private javax.swing.JComboBox<String> jComboBox_CheckRevenue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_RevenueDashboard;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Revenue;
    private javax.swing.JButton loadTable;
    private javax.swing.JLabel totalEarn;
    // End of variables declaration//GEN-END:variables

}
