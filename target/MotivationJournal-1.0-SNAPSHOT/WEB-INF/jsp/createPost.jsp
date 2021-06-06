<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Create New Post Page</title>
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
        <style type="text/css">
            .divForm{
                position: absolute;
                width: 300px;
                height: 600px;

                text-align: left;
                top: 50%;
                left: 50%;
                margin-top: -150px;
                margin-left: -150px;
            }
        </style>

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


        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <h1 align="center"><strong>You haven't logged in.</strong></h1>
                <h2 align="center" style="color:Gray">
                    <a href="login.htm">Click here to sign up/in</a>
                </h2>
            </c:when>
            <c:otherwise>    
                <h1 align="center"><strong>Create a new post</strong></h1>
                <h2 align="center" style="color:Gray">Record your daily accomplishment</h2>
                
                <div class="divForm">
                    <form method="post" action="/MotivationJournal/createPost.htm" enctype="multipart/form-data">
                        Text:
                        <textarea name="text"></textarea><br/>
                        Select image 1:
                        <input type="file" name="image" size="50" accept="image/*"/><br/>
                        Select image 2:
                        <input type="file" name="image" size="50" accept="image/*"/><br/>
                        Select image 3:
                        <input type="file" name="image" size="50" accept="image/*"/><br/>
                        <input type="submit" value="Upload" style="height:27px;width:200px;"/>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
