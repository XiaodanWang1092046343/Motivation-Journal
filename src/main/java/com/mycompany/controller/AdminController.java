/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.PostDao;
import com.mycompany.dao.UserDao;
import com.mycompany.model.Post;
import com.mycompany.model.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    PostDao postDao;
    @Autowired
    private UserDao userDao;

    public AdminController() {
    }

    @RequestMapping(value = "/manageUsers.htm", method = RequestMethod.GET)
    protected ModelAndView getUsersRequest(HttpServletRequest request) {
        List<User> users = userDao.getAllUsers();
        return new ModelAndView("manageUsers", "users", users);
    }

    @RequestMapping(value = "/managePosts.htm", method = RequestMethod.GET)
    protected ModelAndView getPostsRequest(HttpServletRequest request) {
        List<Post> posts = postDao.getAllPosts();
        return new ModelAndView("managePosts", "posts", posts);
    }

    @RequestMapping(value = "/operation.htm", method = RequestMethod.GET)
    protected ModelAndView manageUsersRequest(HttpServletRequest request) {
        try {
            long userId = Long.valueOf(request.getParameter("id"));
            userDao.banOrRemoveBan(userId);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ModelAndView("redirect:/manageUsers.htm");
    }

    @RequestMapping(value = "/adminDeletePost.htm", method = RequestMethod.GET)
    protected ModelAndView managePostsRequest(HttpServletRequest request) {
        try {
            long postId = Long.valueOf(request.getParameter("id"));
            Post post = postDao.searchPostById(postId);
            postDao.deletePost(post);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ModelAndView("redirect:/managePosts.htm");
    }

}
