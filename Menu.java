package foodorderingsystem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Menu extends javax.swing.JFrame {
    private List<MenuItem> menuItems = new ArrayList<>();
    
    public Menu() {
        initComponents();
        getContentPane().setBackground(new Color(200,249,190)); // Set background color
        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JPanel) {
                component.setBackground(new Color(144, 204, 132));
            } else if (component instanceof JButton) {
                JButton button = (JButton) component;
            button.setBackground(new Color(77, 214, 77));
            button.setForeground(Color.WHITE);
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setFont(new Font("Rockwell", Font.PLAIN, 16));
            }
        }
        Login login =  new Login();
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
        loadMenuData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        AddMenuButton = new javax.swing.JButton();
        UpdateMenuButton = new javax.swing.JButton();
        DeleteMenuButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Nametxt = new javax.swing.JTextField();
        Categorytxt = new javax.swing.JTextField();
        Pricetxt = new javax.swing.JTextField();
        ExitButton = new javax.swing.JButton();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Sitka Text", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Menu");

        jTable1.setBackground(new java.awt.Color(161, 204, 153));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Food Name", "Category", "Price"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        AddMenuButton.setText("Add");
        AddMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddMenuButtonActionPerformed(evt);
            }
        });

        UpdateMenuButton.setText("Update");
        UpdateMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateMenuButtonActionPerformed(evt);
            }
        });

        DeleteMenuButton.setText("Delete");
        DeleteMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteMenuButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel2.setText("Food Name: ");

        jLabel4.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel4.setText("Category: ");

        jLabel5.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel5.setText("Price: ");

        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        back.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back)
                        .addGap(118, 118, 118)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Categorytxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Pricetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Nametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ExitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(AddMenuButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(UpdateMenuButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DeleteMenuButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Nametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Categorytxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(Pricetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AddMenuButton)
                        .addGap(18, 18, 18)
                        .addComponent(UpdateMenuButton)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteMenuButton)))
                .addGap(23, 23, 23)
                .addComponent(ExitButton)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadMenuData() {
        menuItems.clear(); 
        try (BufferedReader br = new BufferedReader(new FileReader("menu.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 3) {
                    String foodName = data[0];
                    String category = data[1];
                    double price = Double.parseDouble(data[2]);
                    menuItems.add(new MenuItem(foodName, category, price));
                }
            }
            refreshTable();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading menu data.");
        }
    }

    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (MenuItem item : menuItems) {
            model.addRow(new Object[]{item.getFoodName(), item.getCategory(), item.getPrice()});
        }
    }
    
    private void saveMenuData() {
        try (FileWriter fw = new FileWriter("menu.txt", false)) {
            for (MenuItem item : menuItems) {
                fw.write(item.getFoodName() + "|" + item.getCategory() + "|" + item.getPrice() + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving menu data.");
        }
    }
    
    private void AddMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMenuButtonActionPerformed
        // Get values from text fields
        String foodName = Nametxt.getText().trim();
        String category = Categorytxt.getText().trim();
        String priceText = Pricetxt.getText().trim();

        if (foodName.isEmpty() || category.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            MenuItem newItem = new MenuItem(foodName, category, price);
            menuItems.add(newItem);
            refreshTable();
            saveMenuData();
            JOptionPane.showMessageDialog(this, "Food item added successfully!");
            Nametxt.setText("");
            Categorytxt.setText("");
            Pricetxt.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price! Please enter a numeric value.");
        }
    }//GEN-LAST:event_AddMenuButtonActionPerformed

    private void UpdateMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateMenuButtonActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update!");
            return;
        }

        String newFoodName = Nametxt.getText().trim();
        String newCategory = Categorytxt.getText().trim();
        String newPriceText = Pricetxt.getText().trim();

        if (newFoodName.isEmpty() || newCategory.isEmpty() || newPriceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!");
            return;
        }

        try {
            double newPrice = Double.parseDouble(newPriceText);
            MenuItem selectedItem = menuItems.get(selectedRow);
            selectedItem.setFoodName(newFoodName);
            selectedItem.setCategory(newCategory);
            selectedItem.setPrice(newPrice);
            refreshTable();
            saveMenuData();
            JOptionPane.showMessageDialog(this, "Row updated successfully!");
            Nametxt.setText("");
            Categorytxt.setText("");
            Pricetxt.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price! Please enter a numeric value.");
        }
    }//GEN-LAST:event_UpdateMenuButtonActionPerformed

    private void DeleteMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteMenuButtonActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete!");
            return;
        }

        menuItems.remove(selectedRow);
        refreshTable();
        saveMenuData();
        JOptionPane.showMessageDialog(this, "Row deleted successfully!");
    }//GEN-LAST:event_DeleteMenuButtonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        // Close the current form
        this.dispose();
        // Open the Main Menu form
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true); // Show the main menu
    }//GEN-LAST:event_ExitButtonActionPerformed

    
    //Nested MenuItem Class
    private class MenuItem{
        private String foodName;
        private String category;
        private double price;
        
        public MenuItem(String foodName, String category, double price){
            this.foodName = foodName;
            this.category = category;
            this.price = price;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
    /**
     * @param args the command line arguments
     */

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddMenuButton;
    private javax.swing.JTextField Categorytxt;
    private javax.swing.JButton DeleteMenuButton;
    private javax.swing.JButton ExitButton;
    private javax.swing.JTextField Nametxt;
    private javax.swing.JTextField Pricetxt;
    private javax.swing.JButton UpdateMenuButton;
    private javax.swing.JLabel back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
