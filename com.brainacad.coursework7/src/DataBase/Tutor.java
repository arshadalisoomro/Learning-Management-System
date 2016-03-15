package DataBase;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Зая on 15.02.2016.
 */
public class Tutor extends Person {
    private Set<Course> courseList = new LinkedHashSet<>();


    public Tutor(String firstName, String lastName) {
        super(firstName, lastName);
    //    super.setID(UniqueID.chooseID(2));
        super.setID(UniqueID.chooseID(2));

    }

    public String toString() {
        return getID() + "\n" +
                "Tutor: " + super.toString() + "\n";
    }

    public void addCourse(Course c)  {
        courseList.add(c);
    }

    public void removeCourse(Course c)  {
        Iterator <Course> itr = courseList.iterator();
        while (itr.hasNext()){
          Course course= itr.next();
            if (course.equals(c)){
                itr.remove();
            }
        }
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
        Tutor tutor = (Tutor) obj;
        return (getID() == tutor.getID() || (getID() != null && getID().equals(tutor.getID())));
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + (null == getID() ? 0 : getID().hashCode());
        return hash;
    }
}
