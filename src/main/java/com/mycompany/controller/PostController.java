/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.ImageDao;
import com.mycompany.dao.PostDao;
import com.mycompany.dao.UserDao;
import com.mycompany.model.Image;
import com.mycompany.model.Post;
import com.mycompany.model.User;
import com.mycompany.validator.PostValidator;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {

    @Autowired
    PostDao postDao;
    @Autowired
    PostValidator postValidator;
    @Autowired
    ImageDao imageDao;
    @Autowired
    private UserDao userDao;

    public PostController() {
    }

    @RequestMapping(value = "/home.htm", method = RequestMethod.GET)
    protected ModelAndView handleGetRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<Post> posts = postDao.getAllPosts();
        List<User> users = userDao.getAllUsers();
        session.setAttribute("posts", posts);
        session.setAttribute("users", users);
        return new ModelAndView("home");
    }
    
    @RequestMapping(value = "/bannedView.htm", method = RequestMethod.GET)
    protected String getBannedView(HttpServletRequest request) {
        return "bannedView";
    }

    @RequestMapping(value = "/getPosts.htm", method = RequestMethod.GET)
    protected String handleGetPostsRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "home";
        }
        List<Post> posts = postDao.searchPostByUser(user.getName());
        session.setAttribute("my_posts", posts);
        return "viewMyPosts";
    }

    @RequestMapping(value = "/discover.htm", method = RequestMethod.GET)
    protected String handleDiscoverRequest(HttpServletRequest request) {
        return "discoverPosts";
    }

    @RequestMapping(value = "/discover.htm", method = RequestMethod.POST)
    protected String getDiscoverPosts(HttpServletRequest request) {

        try {
            String name = request.getParameter("username");
            String keyword = request.getParameter("keyword");

            Date beginDate = null;
            Date endDate = null;
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            
            String begin = request.getParameter("begin_date");
            String end = request.getParameter("end_date");
            if (begin != null && (!begin.equals(""))) {
                beginDate = sf.parse(begin);
            }
            if (end != null && (!end.equals(""))) {
                endDate = sf.parse(end);
            }
            List<Post> posts = postDao.searchPosts(name, keyword, beginDate, endDate);
            //List<Post> posts=postDao.searchPostByUser(name);
            //posts=postDao.searchPostsByKeyword(posts,keyword);
            request.setAttribute("postsList", posts);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "discoverView";
    }

    @RequestMapping(value = "/createPost.htm", method = RequestMethod.GET)
    protected String createPostRequest(ModelMap model, Post post) {
        return "createPost";
    }

    @RequestMapping(value = "/createPost.htm", method = RequestMethod.POST)
    protected String handleCreatePostRequest(HttpServletRequest request, @RequestParam CommonsMultipartFile[] image) {
        try {
            String text = request.getParameter("text");
            if(text.equals("")){
                return "emptyPostError";
            }

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Date createTime = new java.util.Date();

            Post post = postDao.createPost(user, createTime, text);
            if (post == null) {
                return "error";
            }

            for (CommonsMultipartFile aFile : image) {
                System.out.println("Saving file: " + aFile.getOriginalFilename());
                Image i = new Image();
                i.setFileName(aFile.getOriginalFilename());
                //File localFile = new File("/usr/local/tomcat-9/temp/", aFile.getOriginalFilename());
                File localFile = new File("/Users/wujianyan/NetBeansProjects/MotivationJournal/src/main/webapp/images/", aFile.getOriginalFilename());
                aFile.transferTo(localFile);
                i.setPath(localFile.getPath());
                i.setPost(post);
                imageDao.saveImage(i);
                post.addImage(i);
            }
            Post updated = postDao.updatePost(post);
            request.setAttribute("new_post", updated);

        } catch (Exception e) {
            System.out.println(e);
        }
        return "successUploadPost";
    }

    @RequestMapping(value = "/updatePost.htm", method = RequestMethod.GET)
    protected ModelAndView updatePostRequest(HttpServletRequest request, @ModelAttribute Post post) {
        long postId = Long.valueOf(request.getParameter("id"));
        post = postDao.searchPostById(postId);
        return new ModelAndView("updatePost", "post", post);
    }

    @RequestMapping(value = "/updatePost.htm", method = RequestMethod.POST)
    protected String doUpdatePostRequest(HttpServletRequest request, @RequestParam CommonsMultipartFile[] image) {
        try {
            long postId = Long.valueOf(request.getParameter("id"));
            Post post = postDao.searchPostById(postId);

            String text = request.getParameter("text");
            if(text.equals("")){
                return "emptyPostError";
            }
            Date createTime = new java.util.Date();
            post.setText(text);
            post.setCreateTime(createTime);
            for (Image temp : post.getImages()) {
                imageDao.deleteImage(temp);
            }
            post.setImages(new HashSet<Image>());
            post = postDao.updatePost(post);

            for (CommonsMultipartFile aFile : image) {
                System.out.println("Saving file: " + aFile.getOriginalFilename());
                Image i = new Image();
                i.setFileName(aFile.getOriginalFilename());
                //File localFile = new File("/usr/local/tomcat-9/temp/", aFile.getOriginalFilename());
                File localFile = new File("/Users/wujianyan/NetBeansProjects/MotivationJournal/src/main/webapp/images/", aFile.getOriginalFilename());
                aFile.transferTo(localFile);
                i.setPath(localFile.getPath());
                i.setPost(post);
                imageDao.saveImage(i);
                post.addImage(i);
            }
            Post updated = postDao.updatePost(post);
            request.setAttribute("updated_post", updated);

        } catch (Exception e) {
            System.out.println(e);
        }
        return "successUpdatePost";
    }

    @RequestMapping(value = "/deletePost.htm", method = RequestMethod.GET)
    protected ModelAndView deletePostRequest(HttpServletRequest request) {
        try {
            long postId = Long.valueOf(request.getParameter("id"));
            Post post = postDao.searchPostById(postId);
            int result = postDao.deletePost(post);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ModelAndView("redirect:/getPosts.htm");
    }

}
