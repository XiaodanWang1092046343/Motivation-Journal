/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.validator;

import com.mycompany.model.Post;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PostValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return Post.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Post post=(Post)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "text.required", "Required field");
    }
    
}
