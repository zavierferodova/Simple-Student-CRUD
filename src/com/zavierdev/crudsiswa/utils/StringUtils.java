/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zavierdev.crudsiswa.utils;

import java.util.Random;

/**
 *
 * @author tech
 */
public class StringUtils {
    public static String randomString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        
        String saltStr = salt.toString();
        return saltStr;
    }
    
    public static String randomString(int length, String saltCharacter) {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * saltCharacter.length());
            salt.append(saltCharacter.charAt(index));
        }
        
        String saltStr = salt.toString();
        return saltStr;
    }
}
