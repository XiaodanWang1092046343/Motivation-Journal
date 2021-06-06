<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            #table2{
                width:500px;
                height:100px
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
        
        <h1 align="center"><strong>You have created a new post successfully!</strong></h1>
        <h2 align="center" style="color:Gray">Here is the post you uploaded: </h2>


        <br/>
        <table width="700" height="80%" align="center">
            <tr>
                <td>
                    <div align="center">
                        <table id="table2" border="0" cellpadding="5" cellspacing="0">
                            <tr>
                                <td><strong style="font-size:20px;">${new_post.user.name} </strong></td>
                            </tr>
                            <tr>
                                <td><small>${new_post.createTime}</small></td>
                            </tr>
                            <tr>
                                <td><div style="font-size:20px;">${new_post.text}</div></td>
                            </tr>

                        </table>
                    </div>
                </td>
            </tr>
        </table>

        <table width="700" height="100%" align="center">
            <tr>
                <td>
                    <div align="center">
                        <table id="table2" border="0" cellpadding="5" cellspacing="0">

                            <tr>
                                <c:forEach var="image" items="${new_post.images}">

                                    <td><img style="height:10%; weight:10%;" src="${pageContext.request.contextPath}/images/${image.fileName}"></td>

                                </c:forEach>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
        </table>

    </body>
</html>
