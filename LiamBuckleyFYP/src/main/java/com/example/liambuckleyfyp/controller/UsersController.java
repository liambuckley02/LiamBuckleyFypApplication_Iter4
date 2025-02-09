//Below code is taken from a YouTube tutorial by Java Master (2021) on how to create a user register and login page
package com.example.liambuckleyfyp.controller;

import com.example.liambuckleyfyp.model.Booking;
import com.example.liambuckleyfyp.model.GolfCourse;
import com.example.liambuckleyfyp.service.BookingService;
import com.example.liambuckleyfyp.service.GolfCourseService;
import jakarta.servlet.http.HttpSession;
import com.example.liambuckleyfyp.service.UsersService;
import com.example.liambuckleyfyp.service.TimeSlotService;
import org.springframework.ui.Model;
import com.example.liambuckleyfyp.model.UsersModel;
import com.example.liambuckleyfyp.model.TimeSlot;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UsersController {

    private final UsersService usersService;
    private final TimeSlotService timeSlotService;
    private final GolfCourseService golfCourseService;
    private final BookingService bookingService;

    public UsersController(UsersService usersService, TimeSlotService timeSlotService, GolfCourseService golfCourseService, BookingService bookingService) {
        this.usersService = usersService;
        this.timeSlotService = timeSlotService;
        this.golfCourseService = golfCourseService;
        this.bookingService = bookingService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        System.out.println("register request: " + usersModel);
        UsersModel registeredUser = usersService.registerUser(usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model, HttpSession session) {
        System.out.println("login request: " + usersModel);
        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());
        if (authenticated != null) {
            session.setAttribute("userLogin", authenticated.getLogin());
            if ("Admin123".equals(authenticated.getLogin()) && "Munster09".equals(usersModel.getPassword())) {
                return "redirect:/admin/dashboard";
            }
            return "redirect:/golfcourses";
        } else {
            return "error_page";
        }
    }

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model) {
        List<UsersModel> users = usersService.getAllUsers();
        List<UsersModel> filteredUsers = users.stream().skip(12).collect(Collectors.toList());
        model.addAttribute("users", filteredUsers);

        List<TimeSlot> timeslots = timeSlotService.getAllTimeSlots()
                .stream()
                .sorted((ts1, ts2) -> ts1.getId().compareTo(ts2.getId()))
                .collect(Collectors.toList());
        model.addAttribute("timeslots", timeslots);


        List<Booking> bookings = bookingService.getAllUpcomingBookings();
        model.addAttribute("bookings", bookings);


        return "admin_dashboard";
    }



    @PostMapping("/admin/update")
    public String updateUser(@RequestParam("id") int id, @RequestParam("login") String login, @RequestParam("password") String password) {
        usersService.updateUser(id, login, password);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") int id) {
        usersService.deleteUser(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/timeslot/add")
    public String addTimeSlot(@RequestParam("golfCourseId") Long golfCourseId, @RequestParam("time") String time, @RequestParam("date") String date) {
        GolfCourse golfCourse = golfCourseService.getGolfCourseById(golfCourseId.intValue());
        TimeSlot newTimeSlot = new TimeSlot(time, date, golfCourse);
        timeSlotService.saveTimeSlot(newTimeSlot);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/timeslot/update")
    public String updateTimeSlot(@RequestParam("id") Long id, @RequestParam("time") String time, @RequestParam("date") String date) {
        TimeSlot timeSlot = timeSlotService.getTimeSlotById(id);
        timeSlot.setTime(time);
        timeSlot.setDate(date);
        timeSlotService.saveTimeSlot(timeSlot);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/timeslot/delete")
    public String deleteTimeSlot(@RequestParam("id") Long id) {
        timeSlotService.deleteTimeSlot(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/cancelBooking")
    public String adminCancelBooking(@RequestParam("bookingId") Long bookingId, Model model) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking != null) {
            boolean isCancelled = bookingService.cancelBooking((long) booking.getGolfCourse().getId(), booking.getTime(), booking.getDate(), booking.getUserLogin());
            if (isCancelled) {
                model.addAttribute("successMessage", "Booking cancelled successfully.");
            } else {
                model.addAttribute("errorMessage", "Failed to cancel booking. Please try again.");
            }
        }
        return "redirect:/admin/dashboard";
    }


}
//References
// Java Master (2021). Java Web Project | Create Login and Register Form From Scratch with, Java11, Spring MVC, PostgreSQL. [online] YouTube. Available at: https://www.youtube.com/watch?v=x_nfnVU0wAI [Accessed 2 Nov. 2024].