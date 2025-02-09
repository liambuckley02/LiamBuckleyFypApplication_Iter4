//Below code is taken from a YouTube tutorial by Java Master (2021) on how to create a user register and login page the code itself has been modified to fit my project.
// `src/main/java/com/example/liambuckleyfyp/repository/ReviewRepository.java`
package com.example.liambuckleyfyp.repository;

import com.example.liambuckleyfyp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByGolfCourseId(int golfCourseId);
}
//References
// Java Master (2021). Java Web Project | Create Login and Register Form From Scratch with, Java11, Spring MVC, PostgreSQL. [online] YouTube. Available at: https://www.youtube.com/watch?v=x_nfnVU0wAI [Accessed 2 Nov. 2024].