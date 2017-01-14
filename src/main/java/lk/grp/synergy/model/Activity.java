package lk.grp.synergy.model;

import lk.grp.synergy.util.ActivityType;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by isuru on 1/12/17.
 */
public class Activity {
    private int activityId;
    private ActivityType activityType;
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String venue;
    private String group;
    private String courseCode;

    public Activity(int activityId, ActivityType activityType, String name, LocalDate date, LocalTime startTime, LocalTime endTime, String venue, String group, String courseCode) {
        this.activityId = activityId;
        this.activityType = activityType;
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.group = group;
        this.courseCode = courseCode;
    }

    public Activity(int activityId, ActivityType activityType, String name, LocalDate date, LocalTime startTime, LocalTime endTime, String venue, String courseCode) {
        this.activityId = activityId;
        this.activityType = activityType;
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.courseCode = courseCode;
        this.group="NA";
    }

    public Activity() {
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        return activityId == activity.activityId;

    }

    @Override
    public int hashCode() {
        return activityId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
