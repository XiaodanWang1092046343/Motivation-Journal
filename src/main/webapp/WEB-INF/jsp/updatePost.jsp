<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Update Post Page</title>
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

        <h1 align="center"><strong>Update a post</strong></h1>
        <h2 align="center" style="color:Gray">Record your daily accomplishment</h2>

        <div class="divForm">
            <form:form modelAttribute="post" method="post" enctype="multipart/form-data">
                Text: <form:textarea path="text"/><br/>
                <br/>
                Select image 1: <input type="file" name="image" accept="image/*"/><br/>
                Select image 2: <input type="file" name="image" accept="image/*"/><br/>
                Select image 3: <input type="file" name="image" accept="image/*"/><br/>
                <input type="submit" value="Upload" style="height:27px;width:200px;"/>
            </form:form> 
        </div>

    </body>
</html>
