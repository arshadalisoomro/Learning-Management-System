package DataBase;

/**
 * Created by Зая on 18.02.2016.
 */
public class UniqueID {
    static int studIDNumber;
    static int tutIDNumber;
    static int courseIDNumber;

    public static String chooseID(int choice) {

        switch (choice) {
            case 1:
                return "CourseID" + (courseIDNumber++);  //CourseID


            case 2:
                return "TutID" + (tutIDNumber++); //TutorID


            case 3:
                return "StudID" + (studIDNumber++);  //StudentID

        }
        return null;
    }
}
