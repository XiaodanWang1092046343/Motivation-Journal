/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Post {
    private long postId;
    private User user;
    private Date createTime;
    private String text;
    private Set<Image> images=new HashSet<>();

    public Post() {
    }

    public Post(long postId, User user, Date createTime, String text) {
        this.postId = postId;
        this.user = user;
        this.createTime = createTime;
        this.text = text;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
    
    public void addImage(Image image) {
        getImages().add(image);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    
            
}
