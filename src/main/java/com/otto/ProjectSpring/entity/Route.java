package com.otto.ProjectSpring.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = " {validation.route.number}")
    private String number;

    @NotEmpty(message = " {validation.route.start}")
    private String startPoint;

    @NotEmpty(message = " {validation.route.end}")
    private String endPoint;

    @Min(value = 0, message = "Length must not be less then 0")
    private int length;

    @OneToMany(mappedBy = "route")
    private List<Bus> buses = new ArrayList<>();

    @Transient
    private int timeInterval;

    @Transient
    private int newBusId;

    public void addBus(Bus bus){
        buses.add(bus);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public int getNewBusId() {
        return newBusId;
    }

    public void setNewBusId(int newBusId) {
        this.newBusId = newBusId;
    }
}
