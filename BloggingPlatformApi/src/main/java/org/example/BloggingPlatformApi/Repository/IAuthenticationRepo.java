package org.example.BloggingPlatformApi.Repository;

import org.example.BloggingPlatformApi.Model.AuthenticationToken;
import org.example.BloggingPlatformApi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findFirstByAuthUser(User connectedUser);

    AuthenticationToken findFirstByTokenValue(String authToken);
}
