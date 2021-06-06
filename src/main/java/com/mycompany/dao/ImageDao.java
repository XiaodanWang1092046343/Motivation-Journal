/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.model.Image;

public class ImageDao extends ConnectionDao{
    public int saveImage(Image image){
        int result=0;
        try{
            beginTransaction();
            getSession().save(image);
            commit();
            result=1;
        }catch(Exception e){
            rollbackTransaction(); 
        }finally{
            close();
        }
        return result;
    }
    
    public int deleteImage(Image image){
        int result=0;
        try{
            beginTransaction();
            getSession().delete(image);
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
