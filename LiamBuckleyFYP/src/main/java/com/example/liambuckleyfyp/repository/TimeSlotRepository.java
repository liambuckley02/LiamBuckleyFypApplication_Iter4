//Below code is taken from a YouTube tutorial by Java Master (2021) on how to create a user register and login page the code itself has been modified to fit my project.
package com.example.liambuckleyfyp.repository;

import com.example.liambuckleyfyp.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByGolfCourseIdAndTimeAndDate(Long golfCourseId, String time, String date);
}
//References
// Java Master (2021). Java Web Project | Create Login and Register Form From Scratch with, Java11, Spring MVC, PostgreSQL. [online] YouTube. Available at: https://www.youtube.com/watch?v=x_nfnVU0wAI [Accessed 2 Nov. 2024].