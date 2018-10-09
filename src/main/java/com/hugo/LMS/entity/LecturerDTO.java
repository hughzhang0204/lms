package com.hugo.LMS.entity;

public class LecturerDTO {

    private String title;
    private String description;
    private String language;
    private Integer maximumStudent;
    private double fee;

    public LecturerDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getMaximumStudent() {
        return maximumStudent;
    }

    public void setMaximumStudent(Integer maximumStudent) {
        this.maximumStudent = maximumStudent;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
