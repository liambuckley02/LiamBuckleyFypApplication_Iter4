//Below code is a mixture of code from Java Master (2021) (line 7 - 36) and my own code the code itself has been modified to fit my project.
package com.example.liambuckleyfyp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "golfcourse")
public class GolfCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id; // Unique identifier for the golf course

    @Column(nullable = false)
    private String name; // Name of the golf course

    @Column(nullable = false)
    private String location; // Location of the golf course

    private String image; // Image URL or path for the golf course

    private String description; // Description of the golf course

    @Column(nullable = false)
    private String contactInfo; // Contact information for the golf course

    private Integer holes; // Number of holes in the golf course

    private Double rating; // Rating of the golf course

    private Double greenFee; // Green fee for the golf course

    @OneToMany(mappedBy = "golfCourse", cascade = CascadeType.ALL, orphanRemoval = true)// Defines a one-to-many relationship with TimeSlot entities, where the golfCourse field in TimeSlot is the owning side
    private List<TimeSlot> times; // List of available time slots for the golf course

    // Getters and setters for id, name, location, image, description, contactInfo, holes, rating, greenFee, and times

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Integer getHoles() {
        return holes;
    }

    public void setHoles(Integer holes) {
        this.holes = holes;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getGreenFee() {
        return greenFee;
    }

    public void setGreenFee(Double greenFee) {
        this.greenFee = greenFee;
    }

    public List<TimeSlot> getTimes() {
        return times;
    }

    public void setTimes(List<TimeSlot> times) {
        this.times = times;
    }
}
//References
//Java Master (2021). Java Web Project | Create Login and Register Form From Scratch with, Java11, Spring MVC, PostgreSQL. [online] YouTube. Available at: https://www.youtube.com/watch?v=x_nfnVU0wAI [Accessed 2 Nov. 2024].