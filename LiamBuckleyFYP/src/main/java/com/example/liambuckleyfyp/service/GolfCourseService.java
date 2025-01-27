//Below code is a mix of YouTube tutorial by Java Master (2021) on how to create a user register and login page and my own code just with the necessary changes to make it work for my project(line 11-27) GitHub copilot helped me with the rest from  line 27-33 line 33-45 is my own code .
package com.example.liambuckleyfyp.service;

import com.example.liambuckleyfyp.model.GolfCourse;
import com.example.liambuckleyfyp.repository.GolfCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GolfCourseService {

    @Autowired
    private GolfCourseRepository golfCourseRepository; // Injecting the GolfCourseRepository dependency

    // Method to retrieve all golf courses
    public List<GolfCourse> getAllGolfCourses() {
        return golfCourseRepository.findAll();
    }

    // Method to retrieve a golf course by its ID
    public GolfCourse getGolfCourseById(int id) {
        return golfCourseRepository.findById(id).orElse(null);
    }

    // Method to save or update a golf course currently not in use will be in later iterations
    public void saveGolfCourse(GolfCourse golfCourse) {
        golfCourseRepository.save(golfCourse);
    }

    // Method to delete a golf course by its ID not in use will be in later iterations
    public void deleteGolfCourse(Integer id) {
        golfCourseRepository.deleteById(id);
    }

    // Method to find golf courses by a specific date
    public List<GolfCourse> findGolfCoursesByDate(String date) {
        return golfCourseRepository.findByDate(date);
    }

    // Method to find golf courses by a name (case-insensitive)
    public List<GolfCourse> findGolfCoursesByName(String name) {
        return golfCourseRepository.findByNameContainingIgnoreCase(name);
    }
}
//References
//Java Master (2021). Java Web Project | Create Login and Register Form From Scratch with, Java11, Spring MVC, PostgreSQL. [online] YouTube. Available at: https://www.youtube.com/watch?v=x_nfnVU0wAI [Accessed 2 Nov. 2024].
//GitHub Co-pilot
//License to: Liam Buckley(liambuckley02)
//License restriction: For educational use only
//Valid through: November 2nd 2025