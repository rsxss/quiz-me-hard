/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.utils;

import model.entities.User;
import config.App;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author NATWORPONGLOYSWAI
 */
public class Authentication {
    public static boolean authenticate(User user, String password) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance(App.PASSWORD_HASHING_ALGORITHM);
            messageDigest.update(password.getBytes());
            String encryptedPassword = new String(messageDigest.digest());
            return encryptedPassword.equals(user.getPassword());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
