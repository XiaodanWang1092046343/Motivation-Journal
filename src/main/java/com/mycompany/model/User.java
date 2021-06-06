/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.model;

public class User {
    private long userId;
    private String name;
    private String password;
    private Role role;
    private boolean isBanned;
    //private Set<User> followers = new HashSet<User>();
    //private Set<User> following = new HashSet<User>();


//    public Set<User> getFollowers() {
//        return followers;
//    }
//
//    public void setFollowers(Set<User> followers) {
//        this.followers = followers;
//    }
//
//    public Set<User> getFollowing() {
//        return following;
//    }
//
//    public void setFollowing(Set<User> following) {
//        this.following = following;
//    }
    
    public User() {
    }

    public User(long userId, String name, String password, Role role, boolean isBanned) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.role = role;
        this.isBanned = isBanned;
    }
    
    public long getUserId() {
        return userId;
    }
    
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isIsBanned() {
        return isBanned;
    }

    public void setIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }
    
    
}
