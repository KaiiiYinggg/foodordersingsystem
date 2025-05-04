package foodorderingsystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class OrderHistoryFeedback extends javax.swing.JFrame {

    //properties with getters and setters
    private String selectedOrderID;
    private String selectedCustomerID;
    private String selectedReviewDate;

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

    public String getSelectedReviewDate() {
        return selectedReviewDate;
    }

    public void setSelectedReviewDate(String selectedReviewDate) {
        this.selectedReviewDate = selectedReviewDate;
    }

    public OrderHistoryFeedback() {
        initComponents();
        Login login = new Login();
        getContentPane().setBackground(new Color(200,249,190)); // Set background color
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
        System.out.println(login.getUsername());
        ImageIcon img = new ImageIcon(getClass().getResource("/files/back.png"));
        Image scaledImage = img.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);//with 200px,height 200px
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
        loadOrderData();
        jComboBox1.setSelectedItem("All");
        jComboBox1.addActionListener(evt -> {
            clearTextFields();
        });
        jTable1.getSelectionModel().addListSelectionListener(e -> { //to show feedback
            if (!e.getValueIsAdjusting()) {
                showFeedbackForSelectedRow();
            }
        });
        SearchButton.addActionListener(evt -> filterOrders());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        Monthtxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Datetxt = new javax.swing.JTextField();
        ExitButton = new javax.swing.JButton();
        Feedbacklbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        SearchButton = new javax.swing.JButton();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setBackground(new java.awt.Color(161, 204, 153));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Order ID", "Customer ID", "Review", "Date"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setBackground(new java.awt.Color(77, 214, 77));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Daily", "Monthly", "Quarterly" }));

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Enter Month: ");

        jLabel3.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Enter Date: ");

        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Feedback : ");

        SearchButton.setText("Search");

        back.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ExitButton)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Datetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Monthtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(Feedbacklbl, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(SearchButton)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(back)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(Datetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(Monthtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(SearchButton))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel1)
                        .addGap(25, 25, 25)
                        .addComponent(Feedbacklbl, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(ExitButton)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        // Close the current form
        this.dispose();
        // Open the Main Menu form
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true); // Show the main menu
    }//GEN-LAST:event_ExitButtonActionPerformed


    private void loadOrderData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        try (BufferedReader br = new BufferedReader(new FileReader("feedback.txt"))) {
            String line;
            model.setRowCount(0); // Clear existing rows
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 9) {
                    model.addRow(new Object[]{data[0], data[1], data[3], data[8]});
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load feedback data!");
            ex.printStackTrace();
        }
    }

    private void showFeedbackForSelectedRow() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            Feedbacklbl.setText("Select an order to view feedback.");
            return;
        }

        setSelectedOrderID(jTable1.getValueAt(selectedRow, 0).toString());

        try (BufferedReader br = new BufferedReader(new FileReader("feedback.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 9 && data[0].equals(getSelectedOrderID())) {
                    Feedbacklbl.setText(data[4]);
                    return;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load customer feedback!");
            ex.printStackTrace();
        }

        Feedbacklbl.setText("No feedback available.");
    }

    private void clearTextFields() {
        // Clear the text fields for date and month
        Datetxt.setText("");  // Clear the date text field
        Monthtxt.setText(""); // Clear the month text field
    }

    private void filterOrders() {
        String filterType = jComboBox1.getSelectedItem().toString();
        String dateInput = Datetxt.getText().trim();
        String monthInput = Monthtxt.getText().trim();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader("feedback.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into an array using "|" as a delimiter
                String[] data = line.split("\\|");

                // Ensure the line has enough fields to avoid ArrayIndexOutOfBoundsException
                if (data.length >= 9) {
                    String feedbackDate = data[8]; // The feedback date from the data
                    boolean matches = false; // Initialize the match flag to false

                    // Use traditional switch-case logic for compatibility with Java 11
                    switch (filterType) {
                        case "Daily":
                            // Check if feedback date matches the given input date
                            matches = feedbackDate.equals(dateInput);
                            break;

                        case "Monthly": {
                            // Extract the month part (e.g., "12" from "2025-12-15")
                            String feedbackMonth = feedbackDate.split("-")[1];
                            // Compare the feedback month with the input month
                            matches = feedbackMonth.equals(monthInput);
                            break;
                        }

                        case "Quarterly": {
                            // Parse the month number (e.g., 12 for December)
                            int monthNum = Integer.parseInt(feedbackDate.split("-")[1]);
                            // Calculate the quarter (1 to 4)
                            int quarter = (monthNum - 1) / 3 + 1;
                            // Convert input quarter to integer and compare
                            int inputQuarter = Integer.parseInt(monthInput);
                            matches = quarter == inputQuarter;
                            break;
                        }

                        case "All":
                            // "All" matches all rows, so always set matches to true
                            matches = true;
                            break;

                        default:
                            // Any unrecognized filter type will result in no match
                            matches = false;
                            break;
                    }

                    // If the row matches the filter, add it to the table model
                    if (matches) {
                        model.addRow(new Object[]{data[0], data[1], data[3], data[8]});
                    }
                }
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load or filter feedback data!");
            ex.printStackTrace();
        }

        clearTextFields();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Datetxt;
    private javax.swing.JButton ExitButton;
    private javax.swing.JLabel Feedbacklbl;
    private javax.swing.JTextField Monthtxt;
    private javax.swing.JButton SearchButton;
    private javax.swing.JLabel back;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
