# Blogging Application

## Introduction

- The Blogging app is a web-based application designed to help create blogs for the users. It allows the users to manage the posts and comments by letting the users create,update or delete the contents.

## Features

- SignUp as User
- SignIn as User
- SignOut
- Create,Update,Delete the posts
- Find all posts or find posts by Topic/Id
- Create,Update,Delete the comments
- Get all comments or get comments by Id

## Technologies used

- Java
- SpringBoot
- Spring Data JPA
- Mysql database

## Configuration

This application uses MYSQL as the database to store the blog data. The application configuration can be found in the **_application.properties_** file. You may need to modify the database connection settings,server port, or any other properties based on your requirements.

## Data Flow

The application is built using the Spring FrameWork and consists of several layers.

- **_DTO_** : The DTO layer consists of classes that are used to transfer data.

- **_Controller_** : The Controller layer handles the http request and delegates them to the respective endpoints.

- **_Service_** : The Service layer handles all the business logics. The service class receives the http request , performs operations and sends the response back to the controller.

- **_Repository_** : The Repository class handles the storing and managing of the data.

## Controllers

### Comment Controller

The comment controller class handles endpoints related to the comment operations. It includes the following methods :

- **_AddComment_** : Handles a POST request to add a new comment to a post.

- **_GetCommentById_** : Handles a GET request to fetch a comment based on the given commentId.

- **_GetAllComments_** : Handles a GET request to fetch all comments. It returns a list of Comment objects.

- **_UpdateComment_** : Handles a PUT request to update the comment based on the commentID provided as input.

- **_DeleteComment_** : Handles a DELETE request to delete the comment based on the commentId provided as input.

- **_GetCommentByPost_** : Handles a GET request to get a list of comments on the postId which is given as input.

### PostController

The post controller class handles endpoints related to the post operations. It includes the following methods :

- **_AddPost_** : Handles a POST request to add a new post.

- **_GetPostById_** : Handles a GET request to fetch the post based on the ID provided as input.

- **_GetAllPosts_** : Handles a GET request to fetch all posts. It returns a list of Post objects.

- **_UpdatePosts_** : Handles a PUT request to update the post based on the ID provided as input.

- **_DeletePost_** : Handles a DELETE request to delete the post based on the ID provided as input.

- **_GetPostByTopic_** : Handles a GET request to fetch the posts based on the Topic given as input.

### User Controller

The User Controller class handles the endpoints related to the user operations. It includes the following methods.

- **_SignUpUser_** : Handles a POST request to sign up a new user into the system.

- **_SignInUser_** : Handles a POST request to sign in a existing user into the system.

- **_SignOut_** : Handles a DELETE request to sign out the user from the system.

### Models

#### AuthenticationToken

- **_AuthId_** : Unique identfier for the authentication token.

- **_TokenValue_** : AuthenticationToken value.

- **_AuthCreationStamp_** : Time when the token was created.

#### Comment

- **_CommentId_** : Unique identifier for the comment.

- **_CommentText_** : Commented texts.

#### Post

- **_PostId_** : Unique identfier for the post.

- **_PostTopic_** : (Enum) Topics of the post.

#### User

- **_BlogUserId_** : Unique identifier for the user.

- **_UserName_** : Name of the user.

- **_UserEmail_** : Email of the user.

- **_UserPassword_** : Password set by the user.

- **_UserNumber_** : Contact number of the user.

### API DOCUMENTATION

The API endpoints will be available at http://localhost:8080.
