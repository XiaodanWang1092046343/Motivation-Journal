/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.UserDao;
import com.mycompany.model.User;
import com.mycompany.service.UserService;
import com.mycompany.validator.UpdatePasswordValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class AccountController {
    
    @Autowired    
    UpdatePasswordValidator updatePasswordValidator;
    @Autowired    
    UserDao userDao;
    
    public AccountController() {
    }
    
    @RequestMapping(value = "/confirmOldPassword.htm", method = RequestMethod.GET)
    protected String handleGetRequest(ModelMap model, User user) {
        return "updatePassword";
    }
    
    @RequestMapping(value = "/confirmOldPassword.htm", method = RequestMethod.POST)
    protected String handlePostRequest(@ModelAttribute("user") User user, BindingResult result, SessionStatus status) {
        updatePasswordValidator.validate(user, result);
        if (result.hasErrors()) {
            return "updatePassword";
        }
        return "newPassword";
    }
    
    @RequestMapping(value = "/inputNewPassword.htm", method = RequestMethod.POST)
    protected String handleUpdatePasswordRequest(HttpServletRequest request) {
        String newPassword = request.getParameter("new_password");
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            userDao.updatePassword(user, newPassword);
            return "UpdatePasswordSuccess";
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
    }
}
