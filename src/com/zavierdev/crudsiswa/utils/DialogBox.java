/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zavierdev.crudsiswa.utils;

import javax.swing.JOptionPane;

/**
 *
 * @author tech
 */
public class DialogBox {
    public static void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
    public static int showOptionDialog(String title, String message, 
                                 int optionType, int messageType) {
        return JOptionPane.showOptionDialog(null, 
                message, 
                title, 
                optionType, 
                messageType, null, null, null);
    }
}
