/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import domain.Person;
import java.io.*;
import java.text.*;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Vendor extends Person {
   
    @Override
    public boolean searchRecord(String ic) {
        // Implementation specific to Vendor
        boolean recordFound = false;
        try (BufferedReader br = new BufferedReader(new FileReader("vendorRegistration.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split("\\|");
                if (columns.length >= 9 && columns[1].trim().equals(ic)) {
                    recordFound = true;
                    break;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "An error occurred while reading the file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return recordFound;
    }

    @Override
    public String generateId() {
        int maxId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("vendorRegistration.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length > 1) {
                    String currentId = parts[parts.length - 1].trim();
                    if (currentId.startsWith("vd")) {
                        try {
                            int idNumber = Integer.parseInt(currentId.substring(2));
                            maxId = Math.max(maxId, idNumber);
                        } catch (NumberFormatException ignored) {
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return "vd" + (maxId + 1);

    }

    public String inputChecking(String name, String ic, String email, String phone, String address, String dob, String password, String gender) {
        
        if (name.matches(".*\\d.*")) {
            return "Name cannot contain numbers!";
        }

        if (ic.matches("\\d{6}-\\d{2}-\\d{4}")) {
            return "IC cannot contain letters!\n Example:110704-09-7423 \n IC number must be a valid 10-digit number.";
        }

        if (!email.contains("@")) {
            return "Please enter a valid email address (must contain '@').";
        }

        if (!phone.matches("01\\d-\\d{7}")) {
            return "Invalid format for phone number.\n Example:01x-xxxxxxx \n Phone number must be a valid 10-digit number.";
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(dob);  // Will throw an exception if the date format is incorrect
        } catch (ParseException e) {
            return "Date of Birth must be in the format YYYY-MM-DD.";
        }

        return null;
    }


}
