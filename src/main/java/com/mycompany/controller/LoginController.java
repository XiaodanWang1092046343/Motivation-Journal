/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.UserDao;
import com.mycompany.model.Role;
import com.mycompany.model.User;
import com.mycompany.validator.LoginValidator;
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
public class LoginController {
    
    @Autowired
    LoginValidator loginValidator;
    @Autowired
    UserDao userDao;
    
    public LoginController() {
    }
    
    @RequestMapping(value="/login.htm",method=RequestMethod.GET)
    protected String handleGetRequest(ModelMap model, User user){
        return "loginForm";
    }
    
    @RequestMapping(value="/login.htm",method=RequestMethod.POST)
    protected String handlePostRequest(@ModelAttribute("user") User user, HttpServletRequest request,BindingResult result, SessionStatus status){
        loginValidator.validate(user, result);
        if(result.hasErrors())
            return "loginForm"; 
        HttpSession session=request.getSession();
        User u= userDao.searchUserByName(user.getName());
        session.setAttribute("user", u);
        session.setAttribute("role", u.getRole());
        status.setComplete();
        if(u.isIsBanned())
            return "bannedView";
        return "home";
    }
    
    @RequestMapping(value="/logout.htm",method=RequestMethod.GET)
    protected String handleLogoutRequest(HttpServletRequest request){
        HttpSession session=request.getSession();
        if(session.getAttribute("user")==null){
            return "home";
        }
        session.removeAttribute("user");
        return "logout";
    }
    
}
