package org.example.BloggingPlatformApi.Service;

import org.example.BloggingPlatformApi.Model.AuthenticationToken;
import org.example.BloggingPlatformApi.Model.DTO.SignInInput;
import org.example.BloggingPlatformApi.Model.DTO.SignUpOutput;
import org.example.BloggingPlatformApi.Model.Enums.GENDER;
import org.example.BloggingPlatformApi.Model.User;
import org.example.BloggingPlatformApi.Repository.IUserRepo;
import org.example.BloggingPlatformApi.Service.HashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authService;

    public SignUpOutput signUpUser(User user){
        String signUpStatusMessage = null;
        boolean signUpStatus = true;

        String enteredMail = user.getUserMail();
        if(enteredMail==null){
            signUpStatusMessage="Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatusMessage,signUpStatus);
        }
        User connectedUser = userRepo.findByUserMail(enteredMail);
        if(connectedUser != null){
            signUpStatusMessage = "Email already registered";
            signUpStatus = false;
            return new SignUpOutput(signUpStatusMessage,signUpStatus);
        }
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());
            user.setUserPassword(encryptedPassword);
            userRepo.save(user);
            signUpStatusMessage = "User registered successfully";
            return new SignUpOutput(signUpStatusMessage, signUpStatus);
        }catch(Exception e){
            signUpStatus = false;
            signUpStatusMessage = "Internal error occurred during sign up";
            return new SignUpOutput(signUpStatusMessage,signUpStatus);
        }
    }

    public String signInUser(SignInInput signInInput){
        String signInStatusMessage = null;

        String enteredMail = signInInput.getMail();
        if(enteredMail == null){
            signInStatusMessage = "Invalid mail";
            return signInStatusMessage;
        }
        User connectedUser = userRepo.findByUserMail(enteredMail);
        if(connectedUser == null){
            signInStatusMessage = "Email not registered";
            return signInStatusMessage;
        }
        AuthenticationToken token = authService.findFirstByUser(connectedUser);
        if(token!=null){
            signInStatusMessage= "Already logged in with the mailId :" + enteredMail;
            return signInStatusMessage;
        }
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if (connectedUser.getUserPassword().equals(encryptedPassword)) {
                AuthenticationToken authToken = new AuthenticationToken(connectedUser);
                authService.saveAuth(authToken);

                signInStatusMessage = authToken.toString();
                return signInStatusMessage;
            }
            else{
                signInStatusMessage = "Invalid credentials";
                return signInStatusMessage;
            }
        }catch(Exception e){
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }
    }

    public String signOutUser(String email){
        User connectedUser = userRepo.findByUserMail(email);
        if(connectedUser==null){
            return "User not found";
        }
        AuthenticationToken authenticationToken = authService.findFirstByUser(connectedUser);
        authService.deleteAuth(authenticationToken);
        return "User signed out successfully";
    }

public long getGenderCount(GENDER gender){
        long count = userRepo.findAll().stream()
                .filter(user->user.getGender().equals(gender))
                .count();

        return count;
}


}
