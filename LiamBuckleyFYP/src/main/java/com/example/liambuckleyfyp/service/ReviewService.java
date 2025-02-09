// `src/main/java/com/example/liambuckleyfyp/service/ReviewService.java`
package com.example.liambuckleyfyp.service;

import com.example.liambuckleyfyp.model.Review;
import com.example.liambuckleyfyp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByGolfCourseId(int golfCourseId) {
        return reviewRepository.findByGolfCourseId(golfCourseId);
    }

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }
}

//References
//GitHub Co-pilot
//License to: Liam Buckley(liambuckley02)
//License restriction: For educational use only
//Valid through: November 2nd 2025