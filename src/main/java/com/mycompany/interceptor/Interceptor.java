/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interceptor;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter{
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            if(request.getMethod().equalsIgnoreCase("get"))
                return true;
            
            Enumeration<String> para=request.getParameterNames();
            while(para.hasMoreElements()){
                String key=(String)para.nextElement();
                String val=request.getParameter(key);
                if(checkPara(val)){
                    request.setAttribute("illegal_input", "true");
                    break;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return true;
    }
    
    private boolean checkPara(String value) {
        if (value != null) {
            // . 匹配除“\n”和"\r"之外的任何单个字符。
            // * 匹配前面的子表达式0或多次
            // ？ 匹配前面的子表达式零次或一次（懒惰模式）
            return (value.matches("<script>(.*?)") ||value.matches("<script(.*?)>")||value.matches("\"<script(.*?)>\""));
        }
        return false;
    }
    

}
