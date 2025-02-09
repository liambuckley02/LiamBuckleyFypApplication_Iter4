//Below code is taken from a YouTube tutorial by Java Master (2021) on how to create a user register and login page the code itself has been modified to fit my project.
// `src/main/java/com/example/liambuckleyfyp/repository/BookingRepository.java`
package com.example.liambuckleyfyp.repository;

import com.example.liambuckleyfyp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Method to find bookings by user login
    List<Booking> findByUserLogin(String userLogin);

    // Method to find bookings by user login, golf course ID, time, and date
    List<Booking> findByUserLoginAndGolfCourseIdAndTimeAndDate(String userLogin, Long golfCourseId, String time, String date);

}
// References
// Java Master (2021). Java Web Project | Create Login and Register Form From Scratch with, Java11, Spring MVC, PostgreSQL. [online] YouTube. Available at: https://www.youtube.com/watch?v=x_nfnVU0wAI [Accessed 2 Nov. 2024].