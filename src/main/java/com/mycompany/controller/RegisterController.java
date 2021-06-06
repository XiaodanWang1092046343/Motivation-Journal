/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.PostDao;
import com.mycompany.dao.UserDao;
import com.mycompany.model.User;
import com.mycompany.service.UserService;
import com.mycompany.validator.RegisterValidator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class RegisterController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RegisterValidator registerValidator;

    public RegisterController() {
    }

    @RequestMapping(value = "/register.htm", method = RequestMethod.GET)
    protected String handleGetRequest(ModelMap model, User user) {
        return "registerForm";
    }

    @RequestMapping(value = "/register.htm", method = RequestMethod.POST)
    protected String handlePostRequest(@ModelAttribute("user") User user, BindingResult result, SessionStatus status) {
        registerValidator.validate(user, result);
        if (result.hasErrors()) 
            return "registerForm";
        int insertResult = userDao.createUser(user);
        if (insertResult == 1) {
            status.setComplete();
            return "registerSuccess";
        }
        return "error";
    }

}

