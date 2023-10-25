package com.file;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Docket {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField startTimeField;
    private JTextField endTimeField;
    private JTextField hoursWorkedField;
    private JTextField ratePerHourField;
    private JComboBox<String> supplierNameDropdown;
    private JComboBox<String> purchaseOrderDropdown;

    public Docket() {
        frame = new JFrame("Docket Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel startTimeLabel = new JLabel("Start Time:");
        startTimeField = new JTextField();

        JLabel endTimeLabel = new JLabel("End Time:");
        endTimeField = new JTextField();

        JLabel hoursWorkedLabel = new JLabel("NoOfHoursWorked:");
        hoursWorkedField = new JTextField();
        
        JLabel ratePerHourLabel = new JLabel("ratePerHour:");
        ratePerHourField = new JTextField();

        JLabel supplierNameLabel = new JLabel("Supplier:");
        supplierNameDropdown = new JComboBox<>(getSupplier());

        JLabel purchaseOrderLabel = new JLabel("Purchase Order:");
        purchaseOrderDropdown = new JComboBox<>(getPurchaseOrders());

        JButton createDocketButton = new JButton("Create Docket");
        createDocketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDocket();
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(startTimeLabel);
        panel.add(startTimeField);
        panel.add(endTimeLabel);
        panel.add(endTimeField);
        panel.add(hoursWorkedLabel);
        panel.add(hoursWorkedField);
        panel.add(ratePerHourLabel);
        panel.add(ratePerHourField);
        panel.add(supplierNameLabel);
        panel.add(supplierNameDropdown);
        panel.add(purchaseOrderLabel);
        panel.add(purchaseOrderDropdown);
        panel.add(createDocketButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void createDocket() {
        String name = nameField.getText();
        String startTime = startTimeField.getText();
        String endTime = endTimeField.getText();
        String hoursWorked = hoursWorkedField.getText();
        String ratePerHour = ratePerHourField.getText();
        String selectedSupplier = (String) supplierNameDropdown.getSelectedItem();
        String selectedPurchaseOrder = (String) purchaseOrderDropdown.getSelectedItem();

        // Create a string with the docket data
        String docketData = "Name: " + name + "\n"
                + "Start Time: " + startTime + "\n"
                + "End Time: " + endTime + "\n"
                + "NoOfHoursWorked: " + hoursWorked + "\n"
                +"ratePerHour: " + ratePerHour + "\n"
                + "Supplier: " + selectedSupplier + "\n"
                + "Purchase Order: " + selectedPurchaseOrder + "\n";

        // For demonstration, print the docket data to the console
        System.out.println("Docket Created:");
        System.out.println(docketData);

        // You can add code here to save the docket data to a file or database.
        // For example, to save to a text file:
        try (PrintWriter writer = new PrintWriter(new FileWriter("Dockets.txt", true))) {
            writer.println(docketData);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Optionally, clear the input fields after creating the docket
        nameField.setText("");
        startTimeField.setText("");
        endTimeField.setText("");
        hoursWorkedField.setText("");
        ratePerHourField.setText("");
        
    }

    private String[] getSupplier() {
    	List<String> Supplier = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\SA\\export29913.csv"))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 12) {
                	Supplier.add(data[11]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Supplier.toArray(new String[0]);
    }
    
    	
        // Your existing getSupplier code...
    

    private String[] getPurchaseOrders() {
    	 List<String> orders = new ArrayList<>();
         try (BufferedReader br = new BufferedReader(new FileReader("D:\\SA\\export29913.csv"))) {
             String line;
             while ((line = br.readLine()) != null) {
                 String[] data = line.split(",");
                 if (data.length >= 2) {
                     orders.add(data[1]);
                 }
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
         return orders.toArray(new String[0]);
     }
        // Your existing getPurchaseOrders code...
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Docket();
            }
        });
    }
}
