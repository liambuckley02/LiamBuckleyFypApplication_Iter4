//Below code is taken from a YouTube tutorial by Java Master (2021) on how to create a user register and login page the code itself has been modified to fit my project.
package com.example.liambuckleyfyp.repository;

import com.example.liambuckleyfyp.model.GolfCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GolfCourseRepository extends JpaRepository<GolfCourse, Integer> {
    List<GolfCourse> findByNameContainingIgnoreCase(String name);
    // This interface extends JpaRepository to provide CRUD operations for the GolfCourse entity.
    // The JpaRepository interface provides methods like save(), findAll(), findById(), deleteById(), etc.
    // The GolfCourse entity is identified by an Integer type ID.


    // Custom query to find golf courses by a specific date
    @Query("SELECT gc FROM GolfCourse gc JOIN gc.times t WHERE t.date = :date")
    List<GolfCourse> findByDate(@Param("date") String date);
}

//References
// Java Master (2021). Java Web Project | Create Login and Register Form From Scratch with, Java11, Spring MVC, PostgreSQL. [online] YouTube. Available at: https://www.youtube.com/watch?v=x_nfnVU0wAI [Accessed 2 Nov. 2024].