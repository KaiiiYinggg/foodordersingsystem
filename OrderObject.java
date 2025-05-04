/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class OrderObject {

    private boolean pngChange = false;
    private boolean cancelOrder = false;
    private String orderId;
    private String status;
    private static OrderObject instance;

    private OrderObject() {
    }

    public static OrderObject getInstance() {
        if (instance == null) {
            instance = new OrderObject();
        }
        return instance;
    }

    public boolean isPngChange() {
        return pngChange;
    }

    public void setPngChange(boolean pngChange) {
        this.pngChange = pngChange;
    }

    public boolean isCancelOrder() {
        return cancelOrder;
    }

    public void setCancelOrder(boolean cancelOrder) {
        this.cancelOrder = cancelOrder;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void playSound(URL soundURL) {
        try {
            if (soundURL != null) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void OrderNotification(String orderId, String status, String currentCustomerID) {
        try {
            if (status == null || status.isEmpty()) {
                return; // Exit if status is null or empty
            }

            File file = new File("order.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "Order file not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split("\\|");

                    if (data.length >= 3) { // Ensure the file has enough data
                        String fileOrderId = data[0];
                        String fileCustomerID = data[1];
                        String fileStatus = data[6]; // Assuming status is in the 7th column (index 6)

                        // Check if the order ID and customer ID match
                        if (fileOrderId.equals(orderId) && fileCustomerID.equals(currentCustomerID)) {
                            switch (fileStatus) {
                                case "Cancelled":
                                    JOptionPane.showMessageDialog(null,
                                            "We sincerely apologize, but your order (Order ID: " + orderId + ") has been cancelled by the restaurant.\n"
                                            + "The full amount has been refunded to your balance.\n\n"
                                            + "Thank you for your understanding!",
                                            "Order Cancelled",
                                            JOptionPane.WARNING_MESSAGE);
                                    break;
                                case "Accepted":
                                    JOptionPane.showMessageDialog(null, "Your order: " + orderId + " has been accepted by the restaurant.",
                                            "Order Status", JOptionPane.WARNING_MESSAGE);
                                    break;
                                case "Order Prepared":
                                    JOptionPane.showMessageDialog(null, "Your order: " + orderId + " has been prepared by the restaurant.Finding runner......",
                                            "Order Status", JOptionPane.WARNING_MESSAGE);
                                    break;
                                case "Order Accepted by Runner":
                                    JOptionPane.showMessageDialog(null, "Your order: " + orderId + " has been accpeted by the runner.",
                                            "Order Status", JOptionPane.WARNING_MESSAGE);
                                    break;
                                case "Food Pickup":
                                    JOptionPane.showMessageDialog(null, "Your order: " + orderId + " has been pick up by the runner.",
                                            "Order Status", JOptionPane.WARNING_MESSAGE);
                                    break;
                                case "Delivering":
                                    JOptionPane.showMessageDialog(null, "Your order: " + orderId + " has been delivering by the runner.",
                                            "Order Status", JOptionPane.WARNING_MESSAGE);
                                    break;
                                default:
                                    break;
                            }
                            break; // Stop searching once we find the matching order
                        }
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading order file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
