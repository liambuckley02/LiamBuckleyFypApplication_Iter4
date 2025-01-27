//Below code is taken from a YouTube tutorial by Java Master (2021) on how to create a user register and login page
package com.example.liambuckleyfyp.repository;
import com.example.liambuckleyfyp.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersModel, Integer> {

    // Method to find a user by login and password
    Optional<UsersModel> findByLoginAndPassword(String login, String password);

    // Method to find the first user by login
    Optional<UsersModel> findFirstByLogin(String login);
}

//References
// Java Master (2021). Java Web Project | Create Login and Register Form From Scratch with, Java11, Spring MVC, PostgreSQL. [online] YouTube. Available at: https://www.youtube.com/watch?v=x_nfnVU0wAI [Accessed 2 Nov. 2024].