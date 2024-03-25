package org.example.BloggingPlatformApi.Controller;

import jakarta.validation.Valid;
import org.example.BloggingPlatformApi.Model.DTO.SignInInput;
import org.example.BloggingPlatformApi.Model.DTO.SignUpOutput;
import org.example.BloggingPlatformApi.Model.Enums.GENDER;
import org.example.BloggingPlatformApi.Model.User;
import org.example.BloggingPlatformApi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("User/SignUp")
    public SignUpOutput signUpUser(@RequestBody @Valid User user){
        return userService.signUpUser(user);
    }

    @PostMapping("User/SignIn")
    public String signInUser(@RequestBody @Valid SignInInput signInInput){
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("User/SignOut/{mail}")
    public String signOut(@PathVariable @Valid String mail){
        return userService.signOutUser(mail);
    }


    @GetMapping("User/{gender}")
    public long getGenderCount(@PathVariable GENDER gender){
        return userService.getGenderCount(gender);
    }
}
