package com.example.liambuckleyfyp.service;

import com.example.liambuckleyfyp.model.Booking;
import com.example.liambuckleyfyp.model.GolfCourse;
import com.example.liambuckleyfyp.model.TimeSlot;
import com.example.liambuckleyfyp.repository.BookingRepository;
import com.example.liambuckleyfyp.repository.GolfCourseRepository;
import com.example.liambuckleyfyp.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BookingService {

    private static final Logger logger = Logger.getLogger(BookingService.class.getName());

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private GolfCourseRepository golfCourseRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private GolfCourseService golfCourseService;

    @Autowired
    private SendGridEmailService emailService;

    public boolean bookTimeSlot(Long golfCourseId, String time, String date) {
        List<TimeSlot> existingTimeSlots = timeSlotRepository.findByGolfCourseIdAndTimeAndDate(golfCourseId, time, date);
        if (!existingTimeSlots.isEmpty()) {
            TimeSlot existingTimeSlot = existingTimeSlots.get(0);
            timeSlotRepository.delete(existingTimeSlot); // Delete the time slot after booking
            logger.info("Time slot booked successfully.");
            return true;
        }
        logger.warning("Time slot not found for booking.");
        return false; // Time slot not found
    }

    public boolean cancelBooking(Long golfCourseId, String time, String date, String userLogin) {
        GolfCourse golfCourse = golfCourseService.getGolfCourseById(golfCourseId.intValue());
        if (golfCourse != null) {
            // Restore the timeslot
            TimeSlot timeSlot = new TimeSlot(time, date, golfCourse);
            timeSlotRepository.save(timeSlot);
            logger.info("Booking cancelled and timeslot restored successfully.");

            // Delete the booking from the database
            List<Booking> bookings = bookingRepository.findByUserLoginAndGolfCourseIdAndTimeAndDate(userLogin, golfCourseId, time, date);
            if (!bookings.isEmpty()) {
                bookingRepository.delete(bookings.get(0));
                logger.info("Booking removed from the database.");
            }

            // Send cancellation email
            String to = "liambuckley381@gmail.com";
            String subject = "Booking Cancellation via TeeFinder";
            String text = "Hello " + userLogin + ",\n\nYour booking at " + golfCourse.getName() + " on " + date + " at " + time + " has been cancelled.\n\nThank you for using TeeFinder ⛳️.";
            try {
                emailService.sendBookingConfirmation(to, subject, text);
            } catch (IOException e) {
                logger.severe("Failed to send booking cancellation email");
                return false;
            }

            // Send cancellation email to the golf manager
            String secondTo = "buckleykid10@gmail.com";
            String secondSubject = "Booking Cancellation Notification via TeeFinder";
            String secondText = "Dear " + secondTo + ",\n\nThe booking made by " + userLogin + " at your golf club " + golfCourse.getName() + " on " + date + " at " + time + " has been cancelled.\n\nBest regards,\nTeeFinder Team ⛳️.";
            try {
                emailService.sendBookingConfirmation(secondTo, secondSubject, secondText);
            } catch (IOException e) {
                logger.severe("Failed to send booking cancellation email to golf manager");
                return false;
            }

            return true;
        }
        logger.warning("Golf course not found for cancellation.");
        return false;
    }

    public Booking saveBooking(Booking booking, int golfCourseId) {
        GolfCourse golfCourse = golfCourseRepository.findById(golfCourseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid golf course ID: " + golfCourseId));
        booking.setGolfCourse(golfCourse);
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByUserLogin(String userLogin) {
        return bookingRepository.findByUserLogin(userLogin);
    }

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllUpcomingBookings() {
        // Implement the logic to retrieve all upcoming bookings
        return bookingRepository.findAll(); // Adjust this as needed to filter for upcoming bookings
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }
}

//References
//GitHub Co-pilot
//License to: Liam Buckley(liambuckley02)
//License restriction: For educational use only
//Valid through: November 2nd 2025