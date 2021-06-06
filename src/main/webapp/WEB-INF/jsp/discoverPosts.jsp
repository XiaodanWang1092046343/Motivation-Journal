<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search Posts Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            .fakeimg {
                height: 200px;
                background: #aaa;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span> 
                        <span class="icon-bar"></span>  
                    </button>
                    <a class="navbar-brand" href="#">Motivation Journal</a>
                </div>

                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="home.htm">Home</a></li>
                        <li><a href="discover.htm">Discover</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Post</a>
                            <ul class="dropdown-menu">
                                <li class="divider"></li>
                                <li><a href="createPost.htm">Create a new post</a></li>
                                <li class="divider"></li>
                                <li><a href="getPosts.htm">View your posts</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">Account</a>
                            <ul class="dropdown-menu">
                                <li class="divider"></li>
                                <li><a href="confirmOldPassword.htm">Change password</a></li>
                                <li class="divider"></li>
                                <li><a href="logout.htm">Logout</a></li>
                            </ul>
                        </li>

                    </ul>
                </div>

            </div>
        </nav>
        
        <h1 align="center"><strong>Discover posts you're interested in.</strong></h1>
        <h2 align="center" style="color:Gray">Learn outstanding people from all walks of life.</h2>
        <div style="width:100%;text-align:center">
            <form action="/MotivationJournal/discover.htm" method="post">
                <br/>
                Search posts by user name: <input type="text" name="username"/><br/>

                Search posts by keyword:  &nbsp;&nbsp; <input type="text" name="keyword"/><br/>

                Search posts by date: 
                &nbsp;
                from <input type="date" name="begin_date" /><br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                to <input type="date" name="end_date" /><br/>
                <br/>
                <input type="submit" value="Search" style="height:27px;width:350px;"/>
            </form>
        </div>
    </body>
</html>
