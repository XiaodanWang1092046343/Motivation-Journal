/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

import java.sql.Blob;

public class Image {
    private long imageId;
    private Post post;
    private String fileName;
    //private Blob data;
    private String path;

    public Image() {
    }
//
//    public Image(long imageId, Post post, String fileName, Blob data) {
//        this.imageId = imageId;
//        this.post = post;
//        this.fileName = fileName;
//        this.data = data;
//    }

    public Image(long imageId, Post post, String fileName, String path) {
        this.imageId = imageId;
        this.post = post;
        this.fileName = fileName;
        this.path = path;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

//    public Blob getData() {
//        return data;
//    }
//
//    public void setData(Blob data) {
//        this.data = data;
//    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}
