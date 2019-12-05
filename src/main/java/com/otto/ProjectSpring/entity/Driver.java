package com.otto.ProjectSpring.entity;

import com.otto.ProjectSpring.validation.NameConstraint;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NameConstraint(message = " {validation.driver.fname}")
    private String firstName;

    @NameConstraint(message = " {validation.driver.lname}")
    private String lastName;

    @Pattern(regexp = "\\+\\d{12}", message = " {validation.driver.phone.wrong}")
    private String phoneNumber;

    @NotEmpty(message = " {validation.driver.email.empty}")
    @Email(message = " {validation.driver.email.wrong}")
    private String email;

    private boolean ready;
    private boolean free;

    @Transient
    private MultipartFile driverImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public MultipartFile getDriverImage() {
        return driverImage;
    }

    public void setDriverImage(MultipartFile driverImage) {
        this.driverImage = driverImage;
    }
}
