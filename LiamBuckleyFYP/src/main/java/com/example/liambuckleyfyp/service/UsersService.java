//Below code is taken from a YouTube tutorial by Java Master (2021) on how to create a user register and login page
package com.example.liambuckleyfyp.service;
import com.example.liambuckleyfyp.model.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.liambuckleyfyp.repository.UsersRepository;

@Service
public class UsersService {

    // Dependency injection of UsersRepository
    private final UsersRepository usersRepository;

    // Constructor to inject UsersRepository dependency
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // Method to register a new user
    public UsersModel registerUser(String login, String password, String email) {
        // Check if login or password is null
        if (login == null || password == null) {
            return null;
        } else {
            // Check if a user with the same login already exists
            if (usersRepository.findFirstByLogin(login).isPresent()) {
                System.out.println("Duplicate login");
                return null;
            }
            // Create a new UsersModel object and set its properties
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            // Save the new user to the repository and return the saved user
            return usersRepository.save(usersModel);
        }
    }

    // Method to authenticate a user
    public UsersModel authenticate(String login, String password) {
        // Find a user by login and password, return null if not found
        return usersRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}
//References
// Java Master (2021). Java Web Project | Create Login and Register Form From Scratch with, Java11, Spring MVC, PostgreSQL. [online] YouTube. Available at: https://www.youtube.com/watch?v=x_nfnVU0wAI [Accessed 2 Nov. 2024].