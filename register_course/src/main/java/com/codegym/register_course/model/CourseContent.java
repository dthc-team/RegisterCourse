package com.codegym.register_course.model;

import javax.persistence.*;

@Entity
@Table(name = "course_content")
public class CourseContent {
    @Id
    @Column(name = "content_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contentID;
    @Column(name = "content_title", columnDefinition = "varchar(255)")
    private String contentTitle;
    @Column(name = "content_description", columnDefinition = "text")
    private String contentDescription;
    @Column(name = "content_order")
    private Integer contentOrder;
    @Column(name = "content_img", columnDefinition = "varchar(255)")
    private String contentImg;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public CourseContent() {
    }

    public CourseContent(Integer contentID, String contentTitle, String contentDescription, Integer contentOrder, String contentImg, Course course) {
        this.contentID = contentID;
        this.contentTitle = contentTitle;
        this.contentDescription = contentDescription;
        this.contentOrder = contentOrder;
        this.contentImg = contentImg;
        this.course = course;
    }

    public Integer getContentID() {
        return contentID;
    }

    public void setContentID(Integer contentID) {
        this.contentID = contentID;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public Integer getContentOrder() {
        return contentOrder;
    }

    public void setContentOrder(Integer contentOrder) {
        this.contentOrder = contentOrder;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
