package DataBase;

import java.util.*;

/**
 * Created by Зая on 15.02.2016.
 */
public class Student extends Person {
    private Set<Course> courseList = new LinkedHashSet<>();



    public Student(String firstName, String lastName) {
        super(firstName, lastName);
        super.setID(UniqueID.chooseID(3));
    }


    public void addCourse(Course c) {
        courseList.add(c);
    }

    public void removeCourse(Course c) {
        courseList.remove(c);
    }





    @Override
    public String toString() {
        return getID() + "\n" +
                "Student name: " + super.toString();
    }


    public Set<Course> getCourseList() {
        return courseList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (obj.getClass() != this.getClass()))
            return false;
        Student student = (Student) obj;
        return (getID() == student.getID() || (getID() != null && getID().equals(student.getID())));
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (null == getID() ? 0 : getID().hashCode());
        return hash;
    }
}
