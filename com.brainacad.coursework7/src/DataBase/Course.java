package DataBase;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Зая on 15.02.2016.
 */
public class Course {
    private static int numberOfStudents=12;
    private String courseID;
    private String title, description;
    private Set<Student> students = new LinkedHashSet<>();
    private Tutor tutor;
    private String startDate, finishDate;
    private String daysOfWeek;
    private Set<Task> tasks = new LinkedHashSet<>();


    public Course(String title, String description, String startDate, String finishDate, String days) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.daysOfWeek = days;
        courseID = UniqueID.chooseID(1);

    }

    public void addTask(String description, TaskType type) {
        tasks.add(new Task(description, type));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        Course course = (Course) obj;
        return (courseID == course.courseID || (courseID != null && courseID.equals(course.courseID))) &&
                (title == course.title || (title != null && title.equals(course.title)));
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (null == courseID ? 0 : courseID.hashCode());
        hash = 31 * hash + (null == title ? 0 : title.hashCode());
        return hash;
    }

    public static int getNumberOfStudents() {
        return numberOfStudents;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }


    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Tutor getTutor() {
        return tutor;

    }

    public String getTitle() {


        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }


    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }


    @Override
    public String toString() {
        return courseID + "\n" + "Course name: " + title + "\n" +
                "Course description: " + description + "\n" +
                "Start date: " + startDate + "\n" +
                "End date: " + finishDate + "\n" +
                "Days: " + daysOfWeek + "\n";
    }
}
