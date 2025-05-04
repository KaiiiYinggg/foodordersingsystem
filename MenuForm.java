package foodorderingsystem;

import domain.Customer;
import domain.OrderObject;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MenuForm extends javax.swing.JFrame {

    private static String customerID;
    private double totalAmount;
    private double creditRemain;
    private double additionalCharges = 5.90;
    private Map<String, Integer> orderMap;
    private String orderType;
    Login login = new Login();
    Customer customer = new Customer();
    private double mycredit;
    private String address;
    private String name;

    public MenuForm() {
        initComponents();

        System.out.println(login.getUsername());
        customerID = login.getUsername();
        name = customer.searchCustomerName(login.getUsername());
        System.out.println(name);

        loadTableDataFromFile("menu.txt"); // Call the method to load data when the form starts
        totalAmount = 0.0;
        orderMap = new HashMap<>();

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
        searchRecord();
        orderChoice();

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private boolean searchRecord() {
        boolean found = false;
        try (BufferedReader br = new BufferedReader(new FileReader("Balance.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] columns = line.split("\\|");
                if (columns.length >= 2 && columns[0].trim().equals(customerID)) {
                    mycredit = Double.parseDouble(columns[1]);
                    txtmyCredit.setText(String.valueOf(mycredit));
                    found = true;
                }

            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return found;
    }

    public static Image getScaledIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(MenuForm.class.getResource(path));
        return icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    private void loadTableDataFromFile(String filePath) {
        DefaultTableModel model = (DefaultTableModel) tbMenu.getModel();
        model.setRowCount(0);

        try (BufferedReader bf = new BufferedReader(new FileReader(filePath))) {
            String read;

            while ((read = bf.readLine()) != null) {
                String[] data = read.split("\\|");
                if (data.length == 3) {
                    model.addRow(data);// Add the data as a row in the table
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage());
        }

    }

    private double applyVoucherDiscount(String voucherID, double totalAmount) {
        try (BufferedReader br = new BufferedReader(new FileReader("voucher.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] voucherData = line.split("\\|");

                if (voucherData.length >= 3 && voucherData[1].trim().equals(voucherID)) {
                    String discount = voucherData[2].replace("%", "").trim();

                    try {
                        double discountPercentage = Double.parseDouble(discount) / 100.0;
                        return totalAmount * (1 - discountPercentage); // Apply discount
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid discount format in voucher file.", "Error", JOptionPane.ERROR_MESSAGE);
                        return totalAmount;
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading voucher file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return totalAmount; // No matching voucher found, return original amount
    }

    private void removeVoucher(String customerID, String usedVoucherID) {
        List<String> updatedVouchers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("voucher.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] voucherData = line.split("\\|");
                if (voucherData.length >= 2) {
                    String storedCustomerID = voucherData[0].trim();
                    String storedVoucherID = voucherData[1].trim();

                    if (!(storedCustomerID.equals(customerID) && storedVoucherID.equals(usedVoucherID))) {
                        updatedVouchers.add(line);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading voucher file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("voucher.txt"))) {
            for (String voucher : updatedVouchers) {
                bw.write(voucher);
                bw.newLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error updating voucher file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void orderChoice() {
        tbMenu.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tbMenu.getSelectedRow();
                if (selectedRow != -1) {
                    //get selected food data
                    String foodname = tbMenu.getValueAt(selectedRow, 0).toString();
                    String pricestr = tbMenu.getValueAt(selectedRow, 2).toString();
                    double price = Double.parseDouble(pricestr);

                    //update quantity
                    int quantity = orderMap.getOrDefault(foodname, 0) + 1;
                    orderMap.put(foodname, quantity);
                    totalAmount += price;

                    //update text area and total amount
                    StringBuilder orderDetails = new StringBuilder();
                    for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
                        orderDetails.append(entry.getKey()).append(" x").append(entry.getValue()).append("\n");
                    }
                    taBill.setText(orderDetails.toString()); // Update total amount 
                    txtTotalAmount.setText(String.valueOf(totalAmount));

                    if (mycredit > totalAmount) {

                        creditRemain = mycredit - totalAmount;
                        txtcreditRemain.setText(String.format("%.2f", creditRemain));

                    } else if (mycredit < totalAmount) {
                        int option = JOptionPane.showConfirmDialog(null, "NOT ENOUGH CREDIT.PLEASE TOP UP.DO YOU WANT TO TOP UP ?", "TOP UP", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            /////jump to top up page////
                            new TopUp().setVisible(true);
                        }
                    }
                }
            }
        });
    }

    private String orderType() {
        if ((!dineinrb.isSelected()) && (!takeawayrb.isSelected()) && (!deliveryrb.isSelected())) {
            JOptionPane.showMessageDialog(null, "Please select order type");
            return "";
        } else if ((dineinrb.isSelected() == true) && (takeawayrb.isSelected() == false) && (deliveryrb.isSelected() == false)) {
            return "Dine in";
        } else if ((dineinrb.isSelected() == false) && (takeawayrb.isSelected() == true) && (deliveryrb.isSelected() == false)) {
            return "Take away";
        } else if ((dineinrb.isSelected() == false) && (takeawayrb.isSelected() == false) && (deliveryrb.isSelected() == true)) {
            return "Delivery";
        } else {
            JOptionPane.showMessageDialog(null, "Choose one order type only");
            return "";
        }
    }

    private void saveOrderDetails(String orderID, String customerID, String orderType, String Status) {
        try (FileWriter myWriter = new FileWriter("order.txt", true); BufferedWriter bw = new BufferedWriter(myWriter)) {

            String currentDate = LocalDate.now().toString();
            String placeOrderTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            StringBuilder items = new StringBuilder();
            int totalQuantity = 0;

            for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
                items.append(entry.getKey()).append(",");
                totalQuantity += entry.getValue();
            }

            if (items.length() > 0) {
                items.setLength(items.length() - 1);
            }

            int option = JOptionPane.showConfirmDialog(null, "Do you want to use a voucher?", "Voucher", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                JPanel voucherPanel = new JPanel();
                voucherPanel.add(new JLabel("Select a voucher to make a discount:"));

                JComboBox<String> voucherComboBox = new JComboBox<>();
                List<String> vouchers = findVouchers(customerID);

                if (!vouchers.isEmpty()) {
                    for (String v : vouchers) {
                        voucherComboBox.addItem(v);
                    }
                } else {
                    voucherComboBox.addItem("No vouchers available");
                }

                voucherPanel.add(voucherComboBox);
                JOptionPane.showMessageDialog(null, voucherPanel);

                String selectedVoucher = (String) voucherComboBox.getSelectedItem();

                if (selectedVoucher != null && !selectedVoucher.equals("No vouchers available")) {
                    totalAmount = applyVoucherDiscount(selectedVoucher, totalAmount);
                    txtTotalAmount.setText(String.format("%.2f", totalAmount));

                    removeVoucher(customerID, selectedVoucher);
                }
            }

            String totalamount = String.format("RM %.2f", totalAmount);

            UUID randomUUID = UUID.randomUUID();
            String transactionID = randomUUID.toString();

            bw.write(orderID + "|"
                    + customerID + "|"
                    + items + "|"
                    + totalQuantity + "|"
                    + orderType + "|"
                    + totalamount + "|"
                    + Status + "|"
                    + currentDate + "|"
                    + transactionID + "|"
                    + getAddress() + "|"
                    + placeOrderTime + "|"
                    + "runnerId" + "|"
                    + name);
            bw.newLine();
            bw.flush();

            JOptionPane.showMessageDialog(this, "Order Placed");
            this.dispose();
            new OrderForm().setVisible(true);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving order: " + e.getMessage());
        }
    }

    public void updateBalance() {
        Double newBalance = 0.00;
        StringBuilder updatedData = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("Balance.txt"))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split("\\|");
                if (columns.length >= 2 && columns[0].trim().equals(customerID)) {
                    mycredit = Double.valueOf(columns[1]);
                    newBalance = mycredit - totalAmount;
                    updatedData.append(columns[0]).append("|").append(newBalance.toString()).append("\n");
                    found = true;
                } else {
                    updatedData.append(line).append("\n");
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("Balance.txt"))) {
                bw.write(updatedData.toString());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred while writing to the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String findCustomerAddress(String customerID) {
        File file = new File("custRegistration.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length >= 8 && parts[11].trim().equals(customerID)) {
                    return parts[4].trim() + ", " + parts[6].trim() + ", " + parts[7].trim() + ", " + parts[5].trim();
                }
            }
        } catch (FileNotFoundException e) {
            return "File not found.";
        }
        return null;
    }

    public static List<String> findVouchers(String customerID) {
        List<String> vouchers = new ArrayList<>();
        File file = new File("voucher.txt");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length >= 3 && parts[0].trim().equals(customerID)) {
                    vouchers.add(parts[1].trim());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: voucher.txt");
        }

        return vouchers;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dineinrb = new javax.swing.JRadioButton();
        takeawayrb = new javax.swing.JRadioButton();
        deliveryrb = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMenu = new javax.swing.JTable();
        backBt = new javax.swing.JButton();
        back = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taBill = new javax.swing.JTextArea();
        txtTotalAmount = new javax.swing.JTextField();
        lbltotalamount = new javax.swing.JLabel();
        orderBt = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtmyCredit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtcreditRemain = new javax.swing.JTextField();
        clearBt = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtAdditionalCharges = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(252, 154, 63));

        dineinrb.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        dineinrb.setText("Dine in");
        dineinrb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dineinrbActionPerformed(evt);
            }
        });

        takeawayrb.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        takeawayrb.setText("Take away");
        takeawayrb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takeawayrbActionPerformed(evt);
            }
        });

        deliveryrb.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        deliveryrb.setText("Delivery");
        deliveryrb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliveryrbActionPerformed(evt);
            }
        });

        tbMenu.setBackground(new java.awt.Color(255, 188, 127));
        tbMenu.setFont(new java.awt.Font("Sitka Text", 0, 12)); // NOI18N
        tbMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Food name", "Category", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbMenu);

        backBt.setBackground(new java.awt.Color(255, 85, 0));
        backBt.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        backBt.setForeground(new java.awt.Color(255, 255, 255));
        backBt.setText("Back");
        backBt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        backBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtActionPerformed(evt);
            }
        });

        back.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("Sitka Text", 0, 36)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Order Page");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 43, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(backBt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dineinrb, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(takeawayrb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deliveryrb, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(back)
                        .addGap(228, 228, 228)
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(takeawayrb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(backBt)
                        .addGap(0, 76, Short.MAX_VALUE))
                    .addComponent(deliveryrb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dineinrb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 155, 63));

        taBill.setEditable(false);
        taBill.setBackground(new java.awt.Color(255, 189, 127));
        taBill.setColumns(20);
        taBill.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        taBill.setRows(5);
        jScrollPane1.setViewportView(taBill);

        txtTotalAmount.setEditable(false);
        txtTotalAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalAmount.setText("0.0");

        lbltotalamount.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        lbltotalamount.setText("Total amount");

        orderBt.setBackground(new java.awt.Color(255, 123, 0));
        orderBt.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        orderBt.setForeground(new java.awt.Color(255, 255, 255));
        orderBt.setText("Place Order");
        orderBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderBtActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel1.setText("My Credit");

        txtmyCredit.setEditable(false);
        txtmyCredit.setBackground(new java.awt.Color(255, 255, 255));
        txtmyCredit.setText("0.0");

        jLabel2.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        jLabel2.setText("Credit Remain");

        txtcreditRemain.setEditable(false);
        txtcreditRemain.setBackground(new java.awt.Color(255, 255, 255));
        txtcreditRemain.setText("0.0");

        clearBt.setBackground(new java.awt.Color(255, 123, 0));
        clearBt.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        clearBt.setForeground(new java.awt.Color(255, 255, 255));
        clearBt.setText("Clear Order");
        clearBt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        clearBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jLabel3.setText("Additional charges");

        txtAdditionalCharges.setEditable(false);
        txtAdditionalCharges.setBackground(new java.awt.Color(255, 255, 255));
        txtAdditionalCharges.setText("0.0");

        jLabel4.setFont(new java.awt.Font("Sitka Text", 0, 24)); // NOI18N
        jLabel4.setText("Order List");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(orderBt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbltotalamount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTotalAmount)
                                .addComponent(txtcreditRemain, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                            .addComponent(txtmyCredit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAdditionalCharges))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(clearBt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(62, 62, 62))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearBt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtmyCredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAdditionalCharges, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltotalamount)
                    .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtcreditRemain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(orderBt)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dineinrbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dineinrbActionPerformed
        txtAdditionalCharges.setText("0.00");
    }//GEN-LAST:event_dineinrbActionPerformed

    private void backBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtActionPerformed
        // TODO add your handling code here:back to main manu
        this.dispose();
        new CustomerMenu().setVisible(true);
    }//GEN-LAST:event_backBtActionPerformed

    private void orderBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderBtActionPerformed
        String ordertype = orderType();
        String Status = "Pending";
        String orderID = "OR" + System.currentTimeMillis();

        OrderObject order = OrderObject.getInstance();

        if (!"".equals(ordertype)) {
            order.setPngChange(true);
            saveOrderDetails(orderID, customerID, ordertype, Status);
            order.playSound(MenuForm.class.getResource("/files/dingdong.wav"));
        }

        updateBalance();


    }//GEN-LAST:event_orderBtActionPerformed

    private void clearBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtActionPerformed
        // Clear order data
        orderMap.clear();
        totalAmount = 0.0;

        // Reset UI fields
        taBill.setText("");
        txtTotalAmount.setText("0.0");
        txtAdditionalCharges.setText("0.00");
        txtmyCredit.setText(Double.toString(mycredit));////////////
        txtcreditRemain.setText(Double.toString(mycredit));////////

        // Reset radio buttons
        dineinrb.setSelected(false);
        takeawayrb.setSelected(false);
        deliveryrb.setSelected(false);
    }//GEN-LAST:event_clearBtActionPerformed

    private void deliveryrbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliveryrbActionPerformed
        JPanel addressPanel = new JPanel();

        addressPanel.add(new JLabel("Enter Delivery Address"));
        JTextArea addresstxt = new JTextArea(3, 20);
        addressPanel.add(addresstxt);
        String customerAddress = findCustomerAddress(customerID);
        if (customerAddress != null) {
            addresstxt.setText(customerAddress);
        } else {
            addresstxt.setText("Address not found.");

        }

        JOptionPane.showMessageDialog(null, addressPanel);
        setAddress(addresstxt.getText());

        //customerID;
        if (deliveryrb.isSelected()) {
            txtAdditionalCharges.setText(String.valueOf(additionalCharges));
            totalAmount += additionalCharges;
        } else {
            txtAdditionalCharges.setText("0.00");
            totalAmount -= additionalCharges;
        }
        txtTotalAmount.setText(String.format("%.2f", totalAmount));

        if (mycredit > totalAmount) {
            creditRemain = mycredit - totalAmount;
            txtcreditRemain.setText(String.format("%.2f", creditRemain));
        } else if (mycredit < totalAmount) {
            int option = JOptionPane.showConfirmDialog(null, "NOT ENOUGH CREDIT.PLEASE TOP UP.DO YOU WANT TO TOP UP ?", "TOP UP", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                new TopUp().setVisible(true);
            }
        }
    }//GEN-LAST:event_deliveryrbActionPerformed

    private void takeawayrbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_takeawayrbActionPerformed
        txtAdditionalCharges.setText("0.00");
    }//GEN-LAST:event_takeawayrbActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JButton backBt;
    private javax.swing.JButton clearBt;
    private javax.swing.JRadioButton deliveryrb;
    private javax.swing.JRadioButton dineinrb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbltotalamount;
    private javax.swing.JButton orderBt;
    private javax.swing.JTextArea taBill;
    private javax.swing.JRadioButton takeawayrb;
    private javax.swing.JTable tbMenu;
    private javax.swing.JTextField txtAdditionalCharges;
    private javax.swing.JTextField txtTotalAmount;
    private javax.swing.JTextField txtcreditRemain;
    private javax.swing.JTextField txtmyCredit;
    // End of variables declaration//GEN-END:variables
}
