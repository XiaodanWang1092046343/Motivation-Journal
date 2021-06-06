/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.model.Image;
import com.mycompany.model.Post;
import com.mycompany.model.User;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class PostDao extends ConnectionDao {

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        try {
            beginTransaction();
            org.hibernate.Query query = getSession().createQuery("from Post");
            posts = query.list();
            //posts = getSession().createQuery("from post", Post.class).list();
            commit();
        } catch (Exception e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return posts;
    }

    public List<Post> searchPostByUser(String username) {
        List<Post> posts = new ArrayList<>();
        try {
            beginTransaction();
//            org.hibernate.Query query = getSession().createQuery("from Post");
//            posts=query.list();
//            for(Post post:posts){
//                if(!post.getUser().getName().equals(username))
//                    posts.remove(post);
//            }
            org.hibernate.Query postQuery = getSession().createQuery("from Post where user.name = : username");
            postQuery.setParameter("username", username);
            posts = postQuery.list();
            commit();
        } catch (Exception e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return posts;
    }

//    public List<Post> searchPostsByKeyword(List<Post> posts, String keyword) {
//        List<Post> result=new ArrayList<>();
//        try {
//            for(Post post:posts){
//                if()
//            }
//            String hql = "from Post where 1=1";
//        if(username!=null&&!("").equals(username)){
//            hql=hql+" and user.name='"+username+"'";
//        }
//        if(keyword!=null&&!("").equals(keyword)){
//            hql=hql+" and text= %'"+keyword+"'"+"%";
//        }
//           
//        if(from!=null&&!("").equals(from)){
//            
//            hql=hql+" and createTime>='"+from+"'";
//        }
//        if(to!=null&&!("").equals(to)){
//            hql=hql+" and createTime<='"+to+"'";
//        }
//            Query query = getSession().createQuery(hql);
//            posts = query.list();
//            Criteria crit = getSession().createCriteria(Post.class);
//            if (!username.equals("")) {
//                crit.add(Restrictions.eq("user.name", username));
//            }
//            if(!keyword.equals("")){
//                crit.add(Restrictions.like("text", "%"+keyword+"%"));
//            }
//            if (from != null) {
//                crit.add(Restrictions.ge("createTime", from));
//            }
//            if (to != null) {
//                crit.add(Restrictions.le("createTime", to));
//            }
//            posts=crit.list();
    public List<Post> searchPosts(String username, String keyword, Date from, Date to) {
        List<Post> posts = new ArrayList<>();
        List<Post> result = new ArrayList<>();
        boolean mark = true;
        try {
            posts = getSession().createQuery("from Post", Post.class).list();
            for (Post post : posts) {
                if (username != null && (!username.equals(""))) {
                    if (!post.getUser().getName().equals(username)) {
                        mark = false;
                    }
                }
                if (keyword != null && (!keyword.equals(""))) {
                    if (!post.getText().contains(keyword)) {
                        mark = false;
                    }

                }
                if (from != null) {
                    if (post.getCreateTime().before(from)) {
                        mark = false;
                    }

                }
                if (to != null) {
                    if (post.getCreateTime().after(to)) {
                        mark = false;
                    }

                }
                if (mark) {
                    result.add(post);
                }
                mark=true;
            }
            commit();
        } catch (Exception e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }

    public Post searchPostById(long postId) {
        Post post = new Post();
        try {
            beginTransaction();
            org.hibernate.Query query = getSession().createQuery("from Post where postId = : postId");
            query.setParameter("postId", postId);
            post = (Post) query.getSingleResult();
            commit();
        } catch (Exception e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return post;
    }

    public int createPost(Post post) {
        int result = 0;
        try {
            beginTransaction();
            getSession().save(post);
            commit();
            result = 1;
        } catch (Exception e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }

    public Post createPost(User user, Date createTime, String text) {
        Post post = new Post();
        try {
            beginTransaction();
            org.hibernate.Query userQuery = getSession().createQuery("from User where name = :username");
            userQuery.setString("username", user.getName());
            User u = (User) userQuery.getSingleResult();

            post.setUser(u);
            post.setCreateTime(createTime);
            post.setText(text);

            getSession().save(post);
            commit();
        } catch (Exception e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return post;
    }

    public Post updatePost(Post post) {
        try {
            beginTransaction();
            getSession().update(post);
            commit();
        } catch (Exception e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return post;
    }

    public int deletePost(Post post) {
        int result = 0;
        try {
            beginTransaction();
            getSession().delete(post);
            result = 1;
            commit();
        } catch (Exception e) {
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }

}
