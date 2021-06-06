/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.validator;

import com.mycompany.dao.UserDao;
import com.mycompany.model.Role;
import com.mycompany.model.User;
import com.mycompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;

    @Override
    public boolean supports(Class<?> type) {
        return User.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required", "Required field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required", "Required field");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role.required", "Required field");

        if (!userDao.isUserExsist(user.getName())) {
            errors.rejectValue("name", "user_not_exist", "User not exist");
        } else {
            if (!this.userService.isPasswordValid(user.getPassword())) {
                errors.rejectValue("password", "password_not_match", "Password must contains at least one digit, uppercase letter and lowercase letter");
            }
            if (!userDao.validatePassword(user.getName(), user.getPassword())) {
                errors.rejectValue("password", "password_wrong", "Wrong password");
            }
            if (!userDao.validateRole(user.getName(), user.getRole())) {
                errors.rejectValue("role", "role_wrong", "Role does not match");
            }
        }
    }
}
