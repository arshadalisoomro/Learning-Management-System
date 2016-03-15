package Console;

import DataBase.Course;
import DataBase.Student;
import DataBase.TaskType;
import DataBase.Tutor;
import LMS22.LMS2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Зая on 21.02.2016.
 */
public class ConsoleMenu {
    //    private int numOfOptions;
    private LMS2 lms2 = new LMS2();

    private Role role;
    private String login, pass, iD;
    private Map<Role, Set<Integer>> permissions = new LinkedHashMap<Role, Set<Integer>>();

    public void fillMapPermissions() {
        addPermission(Role.ADM, new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16});
        addPermission(Role.TUT, new Integer[]{2, 3, 11, 12, 13, 14, 15, 16});
        addPermission(Role.STU, new Integer[]{2, 3, 12, 13, 16});
    }

    public void addPermission(Role r, Integer... val) {
        if (permissions.get(r) == null) {//нема  такого ключа в permissions
            Set<Integer> menuChoice = new LinkedHashSet<>(); //створюємо нову колекцію пунктів меню
            for (int i = 0; i < val.length; i++) {
                menuChoice.add(val[i]);//заповнюємо колекцію пунктів меню пунктами
                permissions.put(r, menuChoice);//додаємо новостворений елемент в колекцію permissions
            }
        } else {
            for (int i = 0; i < val.length; i++) {
                permissions.get(r).add(val[i]);
            }
        }
    }

    public ConsoleMenu() {
        fillMapPermissions();
        chooseRole();
        switchConstruct();
    }



    public Role chooseRole() {
        boolean finished = false;
        while (!finished) {
            login = Keyin.inString("Enter your login please:\n");
            pass = Keyin.inString("Enter your password please:\n");
            iD = lms2.searchOnLoginPass(login, pass);
            try {
                if (chooseKindOfPerson(iD) != null) {
                    finished = true;
                } else {
                    System.out.println("Wrong login or password!");
                }
            } catch (Exception e) {
            }

        }
        return role;
    }

    public Role chooseKindOfPerson(String Id) throws Exception {
        String sub = null;
        if (Id != null) {
            try {
                sub = Id.substring(0, 3);
            } catch (Exception ex) {
                throw new Exception();
            }
            if (sub.equals("Adm")) {
                role = Role.ADM;
                System.out.println("Hello, " + lms2.getAdmin().getLastName() + " " + lms2.getAdmin().getFirstName() + "!");
                System.out.println("You've logged in as administrator!\n");

                return role;

            } else if (sub.equals("Tut")) {
                role = Role.TUT;
                System.out.println("Hello, " + lms2.searchTutorInMapCollOnId(iD).getLastName() + " " + lms2.searchTutorInMapCollOnId(iD).getFirstName() + "!");
                System.out.println("You've logged in as tutor!\n");
                lms2.printTutorInfo(iD);
                return role;

            } else if (sub.equals("Stu")) {
                role = Role.STU;
                System.out.println("Hello, " + lms2.searchStudentOnId(iD).getLastName() + " " + lms2.searchStudentOnId(iD).getFirstName() + "!");
                System.out.println("You've logged in as student!\n");
                lms2.printStudentInfo(iD);
                return role;

            }
        }
        return null;
    }

    // Display menu graphics
    public void menuGraphics() {
        System.out.println("================================================");
        System.out.println("|   MENU SELECTION                             |");
        System.out.println("================================================");
        System.out.println("| Options:                                     |");
        if (permissions.get(role).contains(0)) System.out.println("|        0. CHANGE_LOGIN_PASSWORD              |");
        if (permissions.get(role).contains(1)) System.out.println("|        1. CREATE_COURSE                      |");
        if (permissions.get(role).contains(2)) System.out.println("|        2. COURSE_INFO_ON_ITS_ID              |");
        if (permissions.get(role).contains(3)) System.out.println("|        3. COURSES_LIST_TITLES                |");
        if (permissions.get(role).contains(4)) System.out.println("|        4. REGISTER_STUDENT_FOR_A_COURSE      |");
        if (permissions.get(role).contains(5)) System.out.println("|        5. STUDENT_CHANGE_COURSE              |");
        if (permissions.get(role).contains(6)) System.out.println("|        6. STUDENT_BIO                        |");
        if (permissions.get(role).contains(7)) System.out.println("|        7. REGISTER_TUTOR                     |");
        if (permissions.get(role).contains(8)) System.out.println("|        8. ASSIGN_TUTOR_FOR_COURSE            |");
        if (permissions.get(role).contains(9)) System.out.println("|        9. REMOVE_TUTOR_FROM_COURSE           |");
        if (permissions.get(role).contains(10)) System.out.println("|       10. TUTOR_INFO                         |");
        if (permissions.get(role).contains(11)) System.out.println("|       11. CREATE_TASKS_FOR_A_COURSE          |");
        if (permissions.get(role).contains(12)) System.out.println("|       12. COURSE_STUDENTS_INFO               |");
        if (permissions.get(role).contains(13)) System.out.println("|       13. GRADEBOOK_INFO                     |");
        if (permissions.get(role).contains(14)) System.out.println("|       14. SAVE_GRADEBOOK_IN_A_FILE           |");
        if (permissions.get(role).contains(15)) System.out.println("|       15. SET_MARK_TO_STUDENT                |");
        if (permissions.get(role).contains(16)) System.out.println("|       16. EXIT                               |");
        System.out.println("================================================");
    }




    public class Menu0 {
        private String id, login, pass;
        Role role2;
        Menu3 m3 = new Menu3();

        public Role chooseKindOfPerson(String Id) throws Exception {
            String sub = null;
            if (Id != null) {
                try {
                    sub = Id.substring(0, 3);
                } catch (Exception ex) {
                    throw new Exception();
                }
                if (sub.equals("Adm")) {
                    role2 = Role.ADM;
                    return role2;

                } else if (sub.equals("Tut")) {
                    role2 = Role.TUT;
                    return role2;

                } else if (sub.equals("Stu")) {
                    role2 = Role.STU;
                    return role2;
                }
            }
            return null;
        }

        public void menu0() {
            id = Keyin.inString("Enter the full ID of person, for, example: Admin, TutID0 or StudID0");
            try {
                if (chooseKindOfPerson(id).equals(null)) {
                    System.out.println("The person with such ID doesn't exist!");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Incorrect ID!");
                return;
            }
            try {
                switch (chooseKindOfPerson(id)) {
                    case TUT://search Tutor on ID
                        if (lms2.searchTutorInMapCollOnId(id) == null) {
                            System.out.println("The tutor with such ID doesn't exist");
                            return;
                        }
                        if (assignNewLoginPass() != 1) {
                            lms2.searchTutorInMapCollOnId(id).setLogPass(login, pass);
                        } else {
                            System.out.println("Exit selected");
                            return;
                        }
                        break;
                    case STU://search Student on ID
                        if (lms2.searchStudentOnId(id) == null) {
                            System.out.println("The student with such ID doesn't exist");
                            return;
                        }
                        if (assignNewLoginPass() != 1) {
                            lms2.searchStudentOnId(id).setLogPass(login, pass);
                        } else {
                            System.out.println("Exit selected");
                            return;
                        }
                        break;
                    case ADM://search Admin on ID
                        if (!lms2.getAdmin().getID().equals(id)) {
                            System.out.println("The admin with such ID doesn't exist");
                            return;
                        }
                        if (assignNewLoginPass() != 1) {
                            lms2.getAdmin().setLogPass(login, pass);
                        } else {
                            System.out.println("Exit selected");
                            return;
                        }
                        break;
                }
            } catch (Exception e) {

            }
            System.out.println("The login and password have been successfull changed");
        }


        public int assignNewLoginPass() {
            login = Keyin.inString("Enter the NEW login");
            pass = Keyin.inString("Enter the NEW pass");
            System.out.println("You entered the following:\n1) new login: " + login + "\n2) password: " + pass + "\n");
            int num = Keyin.inInt("To save: press 0; to exit: press 1");
            while (num != 0 && num != 1) {
                num = Keyin.inInt("Please, do the correct choice. To save: press 0; to exit: press 1. \n");
            }
            return num;
        }


    }//CHANGE_LOGIN_PASSWORD

    public class Menu1 {
        String cName, cDescription, startDate, endDate, chDays;

        public void menu1() {
            inputData();
            int choice = operateOnInputInformation();
            saveOrEditFields(choice);
        }

        public void inputData() {
            cName = Keyin.inString("Enter course name:\n (For example: Java for Beginners)\n");
            cDescription = Keyin.inString("Enter course description:\n (For example: " +
                    "Course for people that want learn Java programming language)\n");
            startDate = CheckUtilities.checkForValidDate(Keyin.inString("Enter start date: \n (For example:01.01.2015)\n"));
            endDate = CheckUtilities.checkForValidDate(Keyin.inString("Enter end date:\n (For example: 01.04.2015)\n"));
            CheckUtilities.checkTwoDates(startDate, endDate);
            chDays = CheckUtilities.getDayOfWeek();
        }

        public int operateOnInputInformation() {
            System.out.println("\nYou entered the following information:");
            System.out.println("1.Course name: " + cName + "\n" + "2.Course description: " + cDescription + "\n" +
                    "3.Start date: " + startDate + "\n" + "4.End date: " + endDate + "\n" + "5.Working days: " + chDays + "\n");
            int swValue2 = Keyin.inInt("SAVE (press 0) or EDIT the fields (press 1-5)?\n");
            return swValue2;
        }

        public void saveOrEditFields(int swValue2) {
            boolean finished = false;
            while (!finished) {
                if (swValue2 == 0) {
                    saveFields();
                    finished = true;
                } else if (swValue2 == 1 || swValue2 == 2 || swValue2 == 3 || swValue2 == 4 || swValue2 == 5) {
                    editFields(swValue2);
                    operateOnInputInformation();
                } else {
                    swValue2 = Keyin.inInt("Press the correct option: SAVE (press 0) or EDIT the fields (press 1-5)!\n");
                }
            }
        }

        public void saveFields() {
            boolean correctCourseName = false;
            Course newCourse = lms2.createCourse(cName, cDescription, startDate, endDate, chDays);
            while (!correctCourseName) {
                if (newCourse == null) {
                    cName = Keyin.inString("Enter course name:\n (For example: Java for Beginners)\n");
                    newCourse = lms2.createCourse(cName, cDescription, startDate, endDate, chDays);
                } else {
                    correctCourseName = true;
                }
            }
            System.out.println("\nNew course has been successfully created:");
            try {
                lms2.printCourseInfo(newCourse.getCourseID());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void editFields(int val) {
            switch (val) {
                case 1:
                    cName = Keyin.inString("Enter course name:\n (For example: Java for Beginners)\n");
                    break;
                case 2:
                    cDescription = Keyin.inString("Enter course description:\n (For example: " +
                            "Course for people that want learn Java programming language)\n");
                    break;
                case 3:
                    startDate = CheckUtilities.checkForValidDate(Keyin.inString("Enter start date: \n (For example:01.01.2015)\n"));
                    CheckUtilities.checkTwoDates(startDate, endDate);
                    break;
                case 4:
                    endDate = CheckUtilities.checkForValidDate(Keyin.inString("Enter end date:\n (For example: 01.04.2015)\n"));
                    CheckUtilities.checkTwoDates(startDate, endDate);
                    break;
                case 5:
                    chDays = CheckUtilities.getDayOfWeek();
                    break;
            }
        }

    }//CREATE_COURSE

    public class Menu2 {
        private String courseId;

        public void menu2() {
            boolean correctCourseID = false;
            while (!correctCourseID) {
                try {
                    System.out.println("\nPlease, enter the correct course ID:");
                    lms2.printTitlesOfCourses();
                    courseId = "CourseID" + Keyin.inInt("");
                    lms2.printCourseInfo(courseId);
                    correctCourseID = true;
                } catch (Exception ex) {
                    System.out.println("Course with such id doesn’t exist!");
                }
            }
        }
    }//COURSE_INFO_ON_ITS_ID

    public class Menu3 {
        public void menu3() {
            System.out.println("ID: " + " " + "Course title:" + "\t Tutor information:");
            System.out.println("------------------------");
            lms2.printTitlesOfCourses();
        }
    }//COURSES_LIST_TITLES

    public class Menu4 {
        String firstName, lastName, coursesID;
        Menu3 m = new Menu3();

        public void menu44() {
            inputData();
            int choice = operateOnInputInformation();
            saveOrEditFields(choice);
        }

        public void inputData() {
            firstName = Keyin.inString("Enter the Student's firstName:\n (For example: Ivan)\n");
            lastName = Keyin.inString("Enter the Student's lastName:\n (For example: Bobrov)\n");
            boolean correctCourseID = false;
            while (!correctCourseID) {
                try {
                    System.out.println("\nPlease, enter the correct course ID(s) to apply for:");
                    m.menu3();
                    coursesID = Keyin.inString("For example: 0 or 1,2\n");
                    isCorrectCourseID(idCoursesArray(coursesID));
                    correctCourseID = true;
                } catch (Exception e) {
                }
            }
        }

        public int operateOnInputInformation() {
            System.out.println("\nYou entered the following information:");
            System.out.println("1.Student's first name: " + firstName + "\n" + "2.Student's last name:  " + lastName + "\n" +
                    "3.ID courses to apply for: " + coursesID + "\n");
            int swValue2 = Keyin.inInt("SAVE (press 0) or EDIT the fields (press 1-3)?\n");
            return swValue2;
        }

        public void saveOrEditFields(int val) {
            boolean finished = false;
            while (!finished) {
                if (val == 0) {
                    saveFields();
                } else if (val == 1 || val == 2 || val == 3) {
                    switch (val) {
                        case 1:
                            firstName = Keyin.inString("Enter the Student's firstName:\n (For example: Ivan)\n");
                            break;
                        case 2:
                            lastName = Keyin.inString("Enter the Student's lastName:\n (For example: Bobrov)\n");
                            break;
                        case 3:
                            boolean correctCourseID = false;
                            while (!correctCourseID) {
                                try {
                                    System.out.println("\nPlease, enter the correct course ID(s) to apply for:");
                                    m.menu3();
                                    coursesID = Keyin.inString("For example: 0 or 1,2\n");
                                    isCorrectCourseID(idCoursesArray(coursesID));
                                    correctCourseID = true;
                                } catch (Exception e) {
                                }
                            }
                            break;
                    }
                    System.out.println("\nYou entered the following information:");
                    System.out.println("1.Student's first name: " + firstName + "\n" + "2.Student's last name:  " + lastName + "\n" +
                            "3.ID courses to apply for: " + coursesID + "\n");
                    val = Keyin.inInt("SAVE (press 0) or EDIT the fields (press 1-3)?\n");
                } else {
                    val = Keyin.inInt("Press the correct option: SAVE (press 0) or EDIT the fields (press 1-3)!\n");
                }
            }
        }

        public void saveFields() {
            Student newStudent = null;
            newStudent = lms2.registerStudent(firstName, lastName, idCoursesArray(coursesID));
            if (newStudent != null) {
                System.out.println("\nNew student has been successfully created and registered for a course");
                try {
                    lms2.printStudentInfo(newStudent.getID());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                boolean correctCourseID = false;
                while (!correctCourseID) {
                    try {
                        System.out.println("\nPlease, enter the correct course ID(s) to apply for:");
                        m.menu3();
                        coursesID = Keyin.inString("For example: 0 or 1,2\n");
                        isCorrectCourseID(idCoursesArray(coursesID));
                        correctCourseID = true;
                    } catch (Exception e) {
                    }
                }
            }
        }

        public void editFields(int val) {

        }

        public void menu4() {
            firstName = Keyin.inString("Enter the Student's firstName:\n (For example: Ivan)\n");
            lastName = Keyin.inString("Enter the Student's lastName:\n (For example: Bobrov)\n");
            boolean correctCourseID = false;
            while (!correctCourseID) {
                try {
                    System.out.println("\nPlease, enter the correct course ID(s) to apply for:");
                    m.menu3();
                    coursesID = Keyin.inString("For example: 0 or 1,2\n");
                    isCorrectCourseID(idCoursesArray(coursesID));
                    correctCourseID = true;
                } catch (Exception e) {
                }
            }
            System.out.println("\nYou entered the following information:");
            System.out.println("1.Student's first name: " + firstName + "\n" + "2.Student's last name:  " + lastName + "\n" +
                    "3.ID courses to apply for: " + coursesID + "\n");
            int swValue2 = Keyin.inInt("SAVE (press 0) or EDIT the fields (press 1-3)?\n");
            submenu_4(swValue2);

        }

        public void submenu_4(int val) {
            boolean finished = false;
            Student newStudent = null;
            while (!finished) {
                if (val == 0) {
                    newStudent = lms2.registerStudent(firstName, lastName, idCoursesArray(coursesID));
                    if (newStudent != null) {
                        System.out.println("\nNew student has been successfully created and registered for a course");
                        try {
                            lms2.printStudentInfo(newStudent.getID());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        finished = true;
                    } else {
                        boolean correctCourseID = false;
                        while (!correctCourseID) {
                            try {
                                System.out.println("\nPlease, enter the correct course ID(s) to apply for:");
                                m.menu3();
                                coursesID = Keyin.inString("For example: 0 or 1,2\n");
                                isCorrectCourseID(idCoursesArray(coursesID));
                                correctCourseID = true;
                            } catch (Exception e) {
                            }
                        }
                    }
                } else if (val == 1 || val == 2 || val == 3) {
                    switch (val) {
                        case 1:
                            firstName = Keyin.inString("Enter the Student's firstName:\n (For example: Ivan)\n");
                            break;
                        case 2:
                            lastName = Keyin.inString("Enter the Student's lastName:\n (For example: Bobrov)\n");
                            break;
                        case 3:
                            boolean correctCourseID = false;
                            while (!correctCourseID) {
                                try {
                                    System.out.println("\nPlease, enter the correct course ID(s) to apply for:");
                                    m.menu3();
                                    coursesID = Keyin.inString("For example: 0 or 1,2\n");
                                    isCorrectCourseID(idCoursesArray(coursesID));
                                    correctCourseID = true;
                                } catch (Exception e) {
                                }
                            }
                            break;
                    }
                    System.out.println("\nYou entered the following information:");
                    System.out.println("1.Student's first name: " + firstName + "\n" + "2.Student's last name:  " + lastName + "\n" +
                            "3.ID courses to apply for: " + coursesID + "\n");
                    val = Keyin.inInt("SAVE (press 0) or EDIT the fields (press 1-3)?\n");
                } else {
                    val = Keyin.inInt("Press the correct option: SAVE (press 0) or EDIT the fields (press 1-3)!\n");
                }
            }
        }
    }//REGISTER_STUDENT_FOR_A_COURSE

    public class Menu5 {
        private String studId, oldCourseId, newCourseID;
        Menu3 m = new Menu3();


        public void menu5() {
            //перевірка коректності введеного studId
            studId = "StudID" + m5CorrectStuID("Enter the correct student's ID:\n (For example: 0 or 1,2)\n");
            System.out.println("\nInformation about student:");
            try {
                lms2.printStudentInfo(studId);
            } catch (Exception e) {
            }
            boolean finished = false;
            while (!finished) {
                if (!lms2.studentContainsCourse(studId, oldCourseId)) {
                    System.out.println("\nPlease, enter the correct OLD course ID to sign out:");
                    m.menu3();
                    oldCourseId = "CourseID" + m5CorrectCourseID("", oldCourseId);
                } else {
                    finished = true;
                }
            }
            finished = false;
            boolean finished2 = false;
            while (!finished) {
                try {
                    System.out.println("\nPlease, enter the correct NEW course ID to sign up:");
                    m.menu3();
                    newCourseID = "CourseID" + m5CorrectCourseID("", newCourseID);
                    while (!finished2) {
                        if (lms2.studentContainsCourse(studId, newCourseID)) {
                            System.out.println("\nWrong NEW course ID! You enter the ID of an OLD course. Please, enter the correct one:");
                            m.menu3();
                            newCourseID = "CourseID" + m5CorrectCourseID("", newCourseID);
                        } else {
                            finished2 = true;
                        }
                    }
                    lms2.changeCourse(studId, oldCourseId, newCourseID);
                    finished = true;
                } catch (Exception e) {
                    System.out.println("Try again to enter the ID of new course with the listeners less than 12!");
                }
            }
            System.out.println("Student  has successfully changed the course!");
            System.out.println("\nThe updated information about student:");
            try {
                lms2.printStudentInfo(studId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String m5CorrectStuID(String line) { //перевірка коректності введеного studId
            boolean correctID = false;
            while (!correctID) {
                try {
                    studId = Keyin.inString(line);
                    isCorrectStudentID(studId);
                    correctID = true;
                } catch (Exception e) {
                }
            }
            return studId;
        }

        public void isCorrectStudentID(String studID) throws Exception {
            if (lms2.searchStudentOnId("StudID" + studID) == null) {
                System.out.println("Incorrect StudentID " + studID + "!");
                throw new Exception("Incorrect StudentID");
            }
        }


    }//STUDENT_CHANGE_COURSE

    public class Menu6 {
        private String studId;

        public void menu6() {
            boolean correctStudID = false;
            while (!correctStudID) {
                try {
                    int swValue = Keyin.inInt("Please, enter the correct student's ID: \n");
                    studId = "StudID" + swValue;
                    lms2.printStudentInfo(studId);
                    correctStudID = true;
                } catch (Exception ex) {
                    System.out.println("Student with such id doesn’t exist!");
                }
            }
        }
    }//STUDENT_BIO

    public class Menu7 {
        String firstName, lastName, coursesID;
        Menu3 m = new Menu3();

        public void menu7() {
            firstName = Keyin.inString("Enter the Tutor's firstName:\n (For example: Ivan)\n");
            lastName = Keyin.inString("Enter the Tutor's lastName:\n (For example: Bobrov)\n");

            // перевірка правильності coursesID

            System.out.println("\nYou entered the following information:");
            System.out.println("1.Tutor's first name: " + firstName + "\n" + "2.Tutor's last name:  " + lastName + "\n");
            int swValue2 = Keyin.inInt("SAVE (press 0) or EDIT the fields (press 1-2)?\n");
            submenu_7(swValue2);
        }

        public void submenu_7(int val) {
            boolean finished = false;
            Tutor newTutor = null;
            while (!finished) {
                if (val == 0) {
                    newTutor = lms2.registerTutor(firstName, lastName);
                    System.out.println("New tutor has been successfully created!");
                    finished = true;
                    try {
                        lms2.printTutorInfo(newTutor.getID());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (val == 1 || val == 2) {
                    switch (val) {
                        case 1:
                            firstName = Keyin.inString("Enter the Tutor's firstName:\n (For example: Ivan)\n");
                            break;
                        case 2:
                            lastName = Keyin.inString("Enter the Tutor's lastName:\n (For example: Bobrov)\n");
                            break;
                    }

                    System.out.println("\nYou entered the following information:");
                    System.out.println("1.Tutor's first name: " + firstName + "\n" + "2.Tutor's last name:  " + lastName + "\n");
                    val = Keyin.inInt("SAVE (press 0) or EDIT the fields (press 1-2)?\n");
                } else {
                    val = Keyin.inInt("Press the correct option: SAVE (press 0) or EDIT the fields (press 1-2)!\n");
                }

            }


        }
    }//REGISTER_TUTOR

    public class Menu8 {
        private String courseID;
        Menu3 m3 = new Menu3();
        Menu10 m10 = new Menu10();

        public void menu8() {
            m10.menu10();//ввід TutID та вивід інфи по ньому
            boolean correctCourseID = false;
            boolean correctCourseID2 = false;
            while (!correctCourseID) {
                try {
                    System.out.println("\nPlease, enter the correct course ID to apply for:");
                    m3.menu3();  // вивід списка курсів  та закріплених викладачів за ними
                    courseID = "CourseID" + m5CorrectCourseID("", courseID);//вводимо назву курса, на який хочемо записати викладача
                    while (!correctCourseID2) {
                        if (lms2.tutorContainsCourse(m10.tutId, courseID)) {//перевіряємо, що він хоче записатися на курс, якого у нього нема в списку
                            System.out.println("\nWrong  course ID! You entered the ID of an existing course in your list." +
                                    " Please, enter the correct one:");
                            m3.menu3();
                            courseID = "CourseID" + m5CorrectCourseID("", courseID);
                        } else {
                            correctCourseID2 = true;
                            correctCourseID = true;
                        }
                    }
                } catch (Exception e) {
                }
            }

            if (lms2.courseContainsTutor(courseID)) {
                System.out.println("Would you like anyway to remove the existing tutor and to register the new one?");
                String swValue = Keyin.inString("Press Y for yes or N for no. \n");
                while (!swValue.equals("Y") && !swValue.equals("N")) {
                    swValue = Keyin.inString("Please, do the correct choice. Press Y for yes or N for no. \n");
                }
                if (swValue.equals("N")) {
                    System.out.println("The registration of" + m10.tutId + "was rejected!");
                    return;
                }
            }
            try {
                lms2.addTutorAtCourse(m10.tutId, courseID);
                System.out.println("Success!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//ASSIGN_TUTOR_FOR_COURSE

    public class Menu9 {
        private String courseID;
        Menu3 m3 = new Menu3();
        Menu10 m10 = new Menu10();

        public void menu9() {
            m10.menu10();//ввід TutID та вивід інфи по ньому
            if (!lms2.tutorContainsAnyCourses(m10.tutId)) {
                return;
            }
            boolean correctCourseID2 = false;
            try {
                System.out.println("\nPlease, enter the correct course ID you want to sign out:");
                m3.menu3();  // вивід списка курсів  та закріплених викладачів за ними
                courseID = "CourseID" + m5CorrectCourseID("", courseID);//вводимо назву курса, звідки треба видалити  викладача
                while (!correctCourseID2) {
                    if (lms2.tutorContainsCourse(m10.tutId, courseID)) {//перевіряємо, що він хоче записатися на курс, якого у нього нема в списку
                        correctCourseID2 = true;
                        System.out.println("Please confirm the removing of course" + courseID + " from tutor's list?");
                        String swValue = Keyin.inString("Press Y for yes or N for no. \n");
                        while (!swValue.equals("Y") && !swValue.equals("N")) {
                            swValue = Keyin.inString("Please, do the correct choice. Press Y for yes or N for no. \n");
                        }
                        if (swValue.equals("N")) {
                            System.out.println("The removal" + "was rejected!");
                            return;
                        }
                        lms2.removeTutorFromCourse(m10.tutId, courseID);
                        System.out.println("The course " + courseID + " was successfully removed from the tutor's list!");
                    } else {
                        System.out.println("\nWrong  course ID! You entered the courseID that doesn't exist in your list." +
                                " Please, enter the correct one:");
                        m3.menu3();
                        courseID = "CourseID" + m5CorrectCourseID("", courseID);
                    }
                }
            } catch (Exception e) {
            }
        }
    }//REMOVE_TUTOR_FROM_COURSE

    public class Menu10 {
        private String tutId;

        public void menu10() {
            boolean correctTutID = false;
            while (!correctTutID) {
                try {
                    int swValue = Keyin.inInt("Please, enter the correct tutor's ID: \n");
                    tutId = "TutID" + swValue;
                    lms2.printTutorInfo(tutId);
                    correctTutID = true;
                } catch (Exception ex) {
                    System.out.println("Tutor with such id doesn’t exist!");
                }
            }
        }
    }//TUTOR_INFO


    public class Menu11 {
        private String courseId, descript;
        private TaskType type;
        Menu3 m = new Menu3();

        public TaskType chooseTaskType(int swValue) {
            boolean finished = false;
            while (!finished) {
                switch (swValue) {
                    case 1:
                        type = TaskType.LAB;
                        finished = true;
                        break;
                    case 2:
                        type = TaskType.TEST;
                        finished = true;
                        break;

                }
            }
            return type;
        }

        public void menu11() {
            //
            if (role.equals(role.ADM)) {
                m.menu3();
                System.out.println("\nEnter the  course ID, for which you are going to create a task:");
                courseId = "CourseID" + m5CorrectCourseID("", courseId);
                inputInformation();
            } else if (role.equals(role.TUT)) {
                if (!lms2.tutorContainsAnyCourses(iD)) {
                    System.out.println("You don't teach any courses!");
                    return;
                }
                System.out.println("\nList of your courses:");
                lms2.printTutorsCourses(iD);
                while (!lms2.tutorContainsCourse(iD, courseId)) {
                    System.out.println("Enter the correct course ID number from your list!");
                    courseId = "CourseID" + m5CorrectCourseID("", courseId);
                }
                inputInformation();
            }
        }

        public void inputInformation() {
            descript = (Keyin.inString("Please, enter the description of the task:"));
            int swValue = Keyin.inInt("Please, choose the type of task: LAB (press 1) or TEST (press 2): \n");
            while (swValue != 1 && swValue != 2) {
                swValue = Keyin.inInt("Please, choose the correct type of task: LAB (press 1) or TEST (press 2): \n");
            }
            type = chooseTaskType(swValue);
            lms2.createCourseTask(descript, courseId, type);
            String courseTitle = lms2.searchCourseOnId(courseId).getTitle();
            System.out.println("The task has been successfully created for the " + courseId + ": " + courseTitle);
        }
    }//CREATE_TASKS_FOR_A_COURSE

    public class Menu12 {
        private String courseId;
        Menu3 m = new Menu3();

        public void menu12() {
            boolean correctCourseID = false;
            while (!correctCourseID) {
                try {
                    if (role.equals(role.STU)) {
                        lms2.printStudentCourseTitles(iD);
                        while (!lms2.studentContainsCourse(iD, courseId)) {
                            int swValue = Keyin.inInt("Please, enter the correct course ID: \n");
                            courseId = "CourseID" + swValue;
                        }
                    }else {
                        m.menu3();
                        int swValue = Keyin.inInt("\nPlease, enter the course ID: \n");
                        courseId = "CourseID" + swValue;
                    }
                    lms2.printCourseStudents(courseId);
                    correctCourseID = true;
                } catch (Exception ex) {
                    System.out.println("Course with such id doesn’t exist!");
                }
            }
        }
    }//COURSE_STUDENTS_INFO

    public class Menu13 {
        private String courseId;

        public void menu13() {
            boolean correctCourseID = false;
            while (!correctCourseID) {
                try {
                    if (role.equals(role.STU)) {
                        lms2.printStudentCourseTitles(iD);
                        while (!lms2.studentContainsCourse(iD, courseId)) {
                            int swValue = Keyin.inInt("Please, enter the correct course ID: \n");
                            courseId = "CourseID" + swValue;
                        }
                    } else {
                        int swValue = Keyin.inInt("Please, enter the course ID: \n");
                        courseId = "CourseID" + swValue;
                    }
                    lms2.printGradeBook(courseId);
                    correctCourseID = true;
                } catch (Exception ex) {
                    System.out.println("Course with such id doesn’t exist!");
                }
            }
        }


    }//GRADEBOOK_INFO

    public class Menu14 {
        public void menu14() {
            if (role.equals(role.TUT)) {
                if (!lms2.tutorContainsAnyCourses(iD)) {
                    System.out.println("You don't teach any courses!");
                    return;
                }
            }
            lms2.saveGradebookInFile();
            System.out.println("The gradebook has been successfully saved in a file C:\\Users\\Зая\\IdeaProjects\\com.brainacad.coursework7\\Out1.txt!");
        }
    }//SAVE_GRADEBOOK_IN_A_FILE

    public class Menu15 {
        private String courseId;
        private String studId;
        private int taskID, ball;
        Menu5 m5 = new Menu5();


        public void menu15() {
            if (!lms2.tutorContainsAnyCourses(iD)) {
                System.out.println("You don't teach any courses!");
                return;
            }
            System.out.println("\nList of your courses:");
            lms2.printTutorsCourses(iD);
            while (!lms2.tutorContainsCourse(iD, courseId)) {
                System.out.println("Enter the correct course ID number from your list!");
                courseId = "CourseID" + m5CorrectCourseID("", courseId);
            }
            if (lms2.courseTaskListIsEmpty(courseId)) {
                System.out.println("There are no tasks for this course!");
                return;
                //
            } else {
                if (!lms2.courseContainsStudents(courseId)) {
                    System.out.println("This course doesn't contain any student!");
                    return;
                }
                try {
                    lms2.printCourseStudents(courseId);
                } catch (Exception e) {
                }
                while (!lms2.courseContainsStudent(studId, courseId)) {
                    studId = "StudID" + m5.m5CorrectStuID("Enter the correct student's ID:\n (For example: 0 or 1,2)\n");
                }
                Student student = lms2.searchStudentOnId(studId);
                System.out.println("Information about student: " + student.getID() + " " + student.getFirstName() + " " + student.getLastName());
                lms2.printCourseTasks(courseId);
                taskID = correctTaskID("Enter the correct task ID (for example:0,1) ");
                ball = correctBall("Enter the correct ball!");
                lms2.setGradeBook(courseId, studId, taskID, ball);
                System.out.println("You've successfully put the ball to a student!\n");
            }

        }

        public int correctTaskID(String line) { //перевірка коректності введеного studId
            boolean correctID = false;
            while (!correctID) {
                try {
                    taskID = Keyin.inInt(line);
                    isCorrectTaskID(taskID);
                    correctID = true;
                } catch (Exception e) {
                }
            }
            return taskID;
        }

        public void isCorrectTaskID(int taskID) throws Exception {
            if (lms2.searchTasksOnId(taskID) == null) {
                System.out.println("Incorrect TaskID " + taskID + "!");
                throw new Exception("Incorrect TaskID ");
            }
        }

        public int correctBall(String line) {
            boolean correctID = false;
            while (!correctID) {
                try {
                    ball = Keyin.inInt(line);
                    if (ball >= 0 && ball <= 5) {
                        correctID = true;
                    } else {
                        ball = Keyin.inInt("Enter the correct ball (0-5)!");
                    }
                } catch (Exception e) {
                }
            }
            return ball;
        }
    }//SET_MARK_TO_STUDENT

    public void switchConstruct() {
        boolean x = true;
        int swValue;
        while (x == true) {
            menuGraphics();
            swValue = Keyin.inInt(" Select option: \n");
            if (permissions.get(role).contains(swValue)) {
                switch (swValue) {
                    case 0:
                        new Menu0().menu0();
                        break;
                    case 1:
                        new Menu1().menu1();
                        break;
                    case 2:
                        new Menu2().menu2();
                        break;
                    case 3:
                        new Menu3().menu3();

                        break;
                    case 4:
                        new Menu4().menu4();
                        break;
                    case 5:
                        new Menu5().menu5();
                        break;
                    case 6:
                        new Menu6().menu6();
                        break;
                    case 7:
                        new Menu7().menu7();
                        break;
                    case 8:
                        new Menu8().menu8();
                        break;
                    case 9:
                        new Menu9().menu9();
                        break;
                    case 10:
                        new Menu10().menu10();
                        break;
                    case 11:
                        new Menu11().menu11();
                        break;
                    case 12:
                        new Menu12().menu12();
                        break;
                    case 13:
                        new Menu13().menu13();
                        break;
                    case 14:
                        new Menu14().menu14();
                        break;
                    case 15:
                        new Menu15().menu15();
                        break;
                    case 16:
                        System.out.println("Exit selected\n");
                        x = false;
                        break;
                    default:
                        System.out.println("Invalid selection\n");
                        break;
                }
            } else {
                System.out.println("Invalid selection\n");
            }
        }

    }

    public String[] idCoursesArray(String coursesID) {
        String[] words = coursesID.split("[,]");
        for (int i = 0; i < words.length; i++) {
            words[i] = "CourseID" + words[i].trim();
        }
        return words;
    }

    public void isCorrectCourseID(String[] coursesID) throws Exception {
        for (int i = 0; i < coursesID.length; i++) {
            if (lms2.searchCourseOnId(coursesID[i]) == null) {
                String subId = coursesID[i].substring(8);
                System.out.println("Incorrect courseID " + subId + "!");
                throw new Exception("Incorrect courseID");
            }
        }
    }

    public String m5CorrectCourseID(String line, String courID) {
        boolean correctID = false;
        while (!correctID) {
            try {
                courID = Keyin.inString(line);
                isCorrectCourseID222(courID);
                correctID = true;
            } catch (Exception e) {
            }
        }
        return courID;
    }

    public void isCorrectCourseID222(String courseID) throws Exception {
        if (lms2.searchCourseOnId("CourseID" + courseID.trim()) == null) {
            System.out.println("Incorrect courseID " + courseID + "!");
            throw new Exception("Incorrect courseID");
        }
    }
}
