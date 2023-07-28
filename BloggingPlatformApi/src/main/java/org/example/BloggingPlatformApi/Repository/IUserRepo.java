package org.example.BloggingPlatformApi.Repository;

import org.example.BloggingPlatformApi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer> {
    User findByUserMail(String enteredMail);
}
