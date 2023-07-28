package org.example.BloggingPlatformApi.Service;

import org.example.BloggingPlatformApi.Model.AuthenticationToken;
import org.example.BloggingPlatformApi.Model.User;
import org.example.BloggingPlatformApi.Repository.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IAuthenticationRepo authenticationRepo;

    public void saveAuth(AuthenticationToken authToken){
        authenticationRepo.save(authToken);
    }

    public AuthenticationToken findFirstByUser(User connectedUser) {
        return authenticationRepo.findFirstByAuthUser(connectedUser);
    }

    public void deleteAuth(AuthenticationToken authToken){
        authenticationRepo.delete(authToken);
    }

    public boolean authenticate(String email,String authToken){
        AuthenticationToken token = authenticationRepo.findFirstByTokenValue(authToken);

        if(token == null){
            return false;
        }

        String connectedMail = token.getAuthUser().getUserMail();
        return connectedMail.equals(email);
    }
}
