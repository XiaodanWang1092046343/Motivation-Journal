/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.model.Role;
import com.mycompany.model.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.Query;

public class UserDao extends ConnectionDao{
    
    public List<User> getAllUsers(){
        List<User> users=new ArrayList<>();
        try{
            beginTransaction();
            //users=getSession().createQuery("from user", User.class).list();
            org.hibernate.Query query = getSession().createQuery("from User");
            users = query.list();
            commit();
        }catch (Exception e){
            rollbackTransaction();       
        }finally{
            close();
        }
        return users;
    }
    
    
    public int createUser(User user){
        int result=0;
        try{
            beginTransaction();
            getSession().save(user);
            commit();
            result=1;
        }catch(Exception e){
            rollbackTransaction(); 
        }finally{
            close();
        }
        return result;
    }
    
    public boolean isUserExsist(String username) {
        boolean result=false;
        try{
            beginTransaction();
            String hql = "from User where name =: username";  
            Query query=getSession().createQuery(hql);
            query.setString("username", username);
            List results=query.list();
            if(!results.isEmpty())
                result=true;
            commit();
        }catch (Exception e){
            rollbackTransaction();       
        }finally{
            close();
        }
        return result;
    }
    
    public User searchUserById(long userId) {
        User user=new User();
        try{
            beginTransaction();
            String hql = "from User where userId =: userId";  
            Query query=getSession().createQuery(hql);
            query.setParameter("userId", userId);
            user=(User)query.getSingleResult();
            commit();
        }catch (Exception e){
            rollbackTransaction();       
        }finally{
            close();
        }
        return user;
    }
    
    public User searchUserByName(String username) {
        User user=new User();
        try{
            beginTransaction();
            String hql = "from User where name =: username";  
            Query query=getSession().createQuery(hql);
            query.setString("username", username);
            user=(User)query.getSingleResult();
            commit();
        }catch (Exception e){
            rollbackTransaction();       
        }finally{
            close();
        }
        return user;
    }
    
    public User banOrRemoveBan(long userId){
        User user=new User();
        try{
            beginTransaction();
            String hql = "from User where userId =: userId";  
            Query query=getSession().createQuery(hql);
            query.setParameter("userId", userId);
            user=(User)query.getSingleResult();
            if(!user.isIsBanned())
                user.setIsBanned(true);
            else
                user.setIsBanned(false);
            getSession().update(user);
            commit();
        }catch (Exception e){
            rollbackTransaction();       
        }finally{
            close();
        }
        return user;
    }
    
    
    public boolean validatePassword(String userName, String password) {
        boolean result=false;
        try{
            beginTransaction();
            String hql = "from User u where u.name=:username and u.password=:password";  
            Query query=getSession().createQuery(hql);
            query.setString("username", userName);
            query.setString("password", password);
            List results=query.list();
            if(!results.isEmpty())
                result=true;
            commit();
        }catch (Exception e){
            rollbackTransaction();       
        }finally{
            close();
        }
        return result;
    }

    public boolean validateRole(String userName, Role role) {
        boolean result=false;
        try{
            beginTransaction();
            String hql = "from User where name=:username and role=:role";  
            Query query=getSession().createQuery(hql);
            query.setParameter("username", userName);
            query.setParameter("role",role);
            List results=query.list();
            if(!results.isEmpty())
                result=true;
            commit();
        }catch (Exception e){
            rollbackTransaction();       
        }finally{
            close();
        }
        return result;
    }
    
    public int updatePassword(User user,String newPassword){
        int result=0;
        try{
            beginTransaction();
            String hql = "from User where name=:username";  
            Query query=getSession().createQuery(hql);
            query.setString("username", user.getName());
            User u=(User)query.getSingleResult();
            u.setPassword(newPassword);
            getSession().update(u);
            commit();
            result=1;
        }catch(Exception e){
            rollbackTransaction(); 
        }finally{
            close();
        }
        return result;
    }
    
    
}
