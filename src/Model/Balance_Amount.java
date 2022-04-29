/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.util.*;

public class Balance_Amount extends JFrame implements ActionListener {

    JTextField t1, t2;
    JButton b1, b2, b3;
    JLabel l1, l2, l3;
    String pin;
    int balance ;
    Balance_Amount(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("banking/management/icons/bc.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 960, 1080);
        add(l3);

        l1 = new JLabel();
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("BACK");

        setLayout(null);

        l1.setBounds(190, 350, 400, 35);
        l3.add(l1);

        b1.setBounds(390, 633, 150, 35);
        l3.add(b1);
        balance = 0;
        try{
            Connection c1 = new Connection();
            ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+pin+"'");
            while (rs.next()) {
                if (rs.getString("mode").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        }catch(Exception e){}
        
        l1.setText("Your Current Account Balance is Taka "+balance);

        b1.addActionListener(this);

        setSize(960, 1080);
        setUndecorated(true);
        setLocation(500, 0);
        setVisible(true);
    }
        
    public int Balance(int  amount ,String s ) throws SQLException{
        Connection c1 = new Connection();
            ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+pin+"'");
            rs.next();
            System.out.println(balance);
                if (rs.getString("mode").equals(s)) {
                    balance += amount;
                } else {
                    balance -= amount;
                }
                return balance;
            }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transaction_Count(pin).setVisible(true);
    }
}