//Below code is taken from a YouTube tutorial by Java Master (2021) on how to create a user register and login page
package com.example.liambuckleyfyp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import java.util.Objects;

@Entity
@Table(name = "golfplayer")
public class UsersModel {

    // Primary key for the UsersModel entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    // User's login name
    String login;

    // User's password
    String password;

    // User's email address
    String email;

    // Getter for id
    public Integer getId() {
        return id;
    }

    // Setter for id
    public void setId(Integer id) {
        this.id = id;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for login
    public String getLogin() {
        return login;
    }

    // Setter for login
    public void setLogin(String login) {
        this.login = login;
    }

    // Override equals method to compare UsersModel objects
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersModel that = (UsersModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email);
    }

    // Override hashCode method to generate hash code for UsersModel objects
    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email);
    }

    // Override toString method to return string representation of UsersModel object
    @Override
    public String toString() {
        return "UsersModel{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
//References
// Java Master (2021). Java Web Project | Create Login and Register Form From Scratch with, Java11, Spring MVC, PostgreSQL. [online] YouTube. Available at: https://www.youtube.com/watch?v=x_nfnVU0wAI [Accessed 2 Nov. 2024].