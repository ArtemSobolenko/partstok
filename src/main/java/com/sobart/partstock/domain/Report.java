package com.sobart.partstock.domain;

import javax.persistence.*;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userReport;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "part_id")
    private Part partReport;

    private boolean active;

    public Report() {
    }

    public Report(User userReport, Part partReport) {
        this.userReport = userReport;
        this.partReport = partReport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserReport() {
        return userReport;
    }

    public void setUserReport(User userReport) {
        this.userReport = userReport;
    }

    public Part getPartReport() {
        return partReport;
    }

    public void setPartReport(Part partReport) {
        this.partReport = partReport;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
