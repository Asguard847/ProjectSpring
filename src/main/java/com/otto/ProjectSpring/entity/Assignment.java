package com.otto.ProjectSpring.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="driver_id")
    private Driver driver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bus_id")
    private Bus bus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="route_id")
    private Route route;

    private Date approved;
    private Date cancelled;
    private Date created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Date getApproved() {
        return approved;
    }

    public void setApproved(Date approved) {
        this.approved = approved;
    }

    public Date getCancelled() {
        return cancelled;
    }

    public void setCancelled(Date cancelled) {
        this.cancelled = cancelled;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
