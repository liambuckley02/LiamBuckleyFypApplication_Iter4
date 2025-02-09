//Below code is taken from a YouTube tutorial by Java Master (2021) on how to create a user register and login page
package com.example.liambuckleyfyp.service;

import com.example.liambuckleyfyp.model.UsersModel;
import com.example.liambuckleyfyp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String login, String password, String email) {
        if (login == null || password == null) {
            return null;
        } else {
            if (usersRepository.findFirstByLogin(login).isPresent()) {
                System.out.println("Duplicate login");
                return null;
            }
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            return usersRepository.save(usersModel);
        }
    }

    public UsersModel authenticate(String login, String password) {
        List<UsersModel> users = usersRepository.findByLoginAndPassword(login, password);
        System.out.println("Retrieved users: " + users);
        if (!users.isEmpty()) {
            return users.get(0); // Return the first user found
        } else {
            return null;
        }
    }
    public List<UsersModel> getAllUsers() {
        // Implementation to retrieve all users
        return usersRepository.findAll();// return the list of users;
    }

    public void updateUser(int id, String login, String password) {
        Optional<UsersModel> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            UsersModel user = userOptional.get();
            user.setLogin(login);
            user.setPassword(password);
            usersRepository.save(user);
        }
    }

    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }


}
//References
// Java Master (2021). Java Web Project | Create Login and Register Form From Scratch with, Java11, Spring MVC, PostgreSQL. [online] YouTube. Available at: https://www.youtube.com/watch?v=x_nfnVU0wAI [Accessed 2 Nov. 2024].