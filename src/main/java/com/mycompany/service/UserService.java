/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public boolean isPasswordValid(String password) {
        char[] charArray = password.toCharArray();
        boolean containsDigit = false;
        boolean containsUpper = false;
        boolean containsLower = false;
        for (char c : charArray) {
            if (Character.isDigit(c)) {
                containsDigit = true;
            } else if (Character.isUpperCase(c)) {
                containsUpper = true;
            } else if (Character.isLowerCase(c)) {
                containsLower = true;
            }
            if (containsDigit && containsUpper && containsLower) {
                return true;
            }
        }
        return false;
    }

}
