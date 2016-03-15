package LMS22;

import DataBase.*;

import java.io.*;
import java.util.*;

/**
 * Created by Зая on 23.02.2016.
 */
public class LMS2 {
    private Set<Course> courseList = new LinkedHashSet<>();
    Map<String, Integer> gradeBook = new LinkedHashMap<>();
    Map<String, Tutor> tutors = new LinkedHashMap<>();
    Map<String, Student> students = new LinkedHashMap<>();
    private Person admin;

    public Person getAdmin() {
        return admin;
    }

    public LMS2() {
        createInitialData();

    }

    //COURSE
    public Course createCourse(String title, String description,
                               String startDate, String finishDate, String days) {
        Iterator<Course> itr = courseList.iterator();
        while (itr.hasNext()) {
            Course element = itr.next();
            try {
                if (title.equals(element.getTitle())) {
                    throw new Exception();
                }
            } catch (Exception ex) {
                System.out.println("Course name should be unique. Please, enter another name!");
                return null;
            }
        }
        Course course = new Course(title, description, startDate, finishDate, days);
        courseList.add(course);
        return course;
    }

    public void printCourseInfo(String courId) throws Exception {
        Course course = searchCourseOnId(courId);
        if (course == null) {
            throw new Exception();
        }
        System.out.print(course);
        Tutor tutor = course.getTutor();
        if (tutor != null) {
            System.out.print(tutor);
        } else {
            System.out.println("Tutors: currently there are no tutors for this course!");
        }
        printCourseTasks(courId);
    }

    public void printTitlesOfCourses() {
        Iterator<Course> itr = courseList.iterator();
        int i = 0;
        while (itr.hasNext()) {
            Course element = itr.next();
            Tutor tutor = element.getTutor();
            if (tutor != null) {
                System.out.println((i++) + ". " + element.getTitle() + "\t Tutor: " + tutor.getLastName() + " "
                        + tutor.getFirstName() + "\t (" + tutor.getID() + ")");
            } else {
                System.out.println((i++) + ". " + element.getTitle() + " Tutors: 0");
            }
        }
    }

    public void createCourseTask(String description, String courseId, TaskType type) {
        Course course = searchCourseOnId(courseId);
        course.addTask(description, type);
    }

    public void printCourseTasks(String courseId) {
        Course course = searchCourseOnId(courseId);
        Iterator<Task> itr = course.getTasks().iterator();
        System.out.print("Tasks: ");
        if (!course.getTasks().isEmpty()) {
            while (itr.hasNext()) {
                Task element = itr.next();
                System.out.print("task" + element.getId());
                if (itr.hasNext()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        } else {
            System.out.println("Currently there are no tasks in a course!");
        }
    }

    public boolean courseTaskListIsEmpty(String courseId) {
        boolean result;
        Course course = searchCourseOnId(courseId);
        if (!course.getTasks().isEmpty()) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    public Task searchTasksOnId(int taskID) {
        Task task = null;
        Iterator<Course> itr = courseList.iterator();
        while (itr.hasNext()) {
            Course element = itr.next();
            Iterator<Task> itr2 = element.getTasks().iterator();
            while (itr2.hasNext()) {
                task = itr2.next();
                if (Integer.valueOf(task.getId()).equals(taskID)) {
                    return task;
                }
            }
        }
        return null;
    }

    public Course searchCourseOnId(String courseId) {
        Course obj = null;
        Iterator<Course> itr = courseList.iterator();
        while (itr.hasNext()) {
            obj = itr.next();
            if (obj.getCourseID().equals(courseId)) {
                return obj;
            }
        }
        return null;
    }

    public boolean courseContainsStudents(String courseId) {
        Course course = searchCourseOnId(courseId);
        try {
            if (!course.getStudents().isEmpty())
                return true;
        } catch (NullPointerException e) {
        }
        return false;
    }


    //STUDENTS
    public Student registerStudent(String firstName, String lastName, String... coursesIds) {
        Student stud = new Student(firstName, lastName);
        stud.setLogPass(stud.getID(), stud.getID() + "1");
        students.put(stud.getID(), stud);
        int i = -1;
        while (++i < coursesIds.length) {
            Course course = searchCourseOnId(coursesIds[i]);//предполагаем, что coursesIds введены правильно (проверка правильности
            if (course != null) {                                          // осуществляется на момент введения информации в консоль)
                if (!IsCourseFull(coursesIds[i])) {
                    Set<Student> tempstudents = course.getStudents();
                    tempstudents.add(stud);
                    course.setStudents(tempstudents);
                    stud.addCourse(course);
                } else {
                    System.out.println("The required course is full! Please try to sign up for another course!");
                    return null;
                }
            }
        }
        return stud;
    }

    public boolean IsCourseFull(String coursesId) {
        Course course = searchCourseOnId(coursesId);
        int number = course.getNumberOfStudents();
        if (course.getStudents().size() < number) {
            return false;
        } else {
            return true;
        }
    }

    public Student searchStudentOnId(String studId) {
        Iterator<Map.Entry<String, Student>> itr = students.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Student> entry = itr.next();
            if (entry.getKey().equals(studId)) {
                return entry.getValue();
            }
        }
        return null;
    }


    public boolean studentContainsCourse(String studId, String courseId) {
        Student st = searchStudentOnId(studId);
        Course course = searchCourseOnId(courseId);
        try {
            if (st.getCourseList().contains(course)) {
                return true;
            }
        } catch (NullPointerException e) {
        }

        return false;
    }

    public void changeCourse(String studId, String oldCourseId, String newCourseId) throws Exception {
        Student st = searchStudentOnId(studId);
        Course oldCourse = searchCourseOnId(oldCourseId);
        Course newCourse = searchCourseOnId(newCourseId);
        if (newCourse != null) {                                          // осуществляется на момент введения информации в консоль)
            if (!IsCourseFull(newCourseId)) {
                Set<Student> tempstudents = newCourse.getStudents();
                tempstudents.add(st);
                newCourse.setStudents(tempstudents);
                st.addCourse(newCourse);
            } else {
                System.out.println("The required course is full! Please try to sign up for another course!");
                throw new Exception();
            }
            st.removeCourse(oldCourse);
            oldCourse.getStudents().remove(st);
            deleteNotesAboutStudentGradeB(oldCourseId, studId);
        }
    }

    public void printStudentInfo(String studId) throws Exception {
        Student st = searchStudentOnId(studId);
        if (st == null) {
            throw new Exception();
        }
        System.out.println(st);
        printStudentCourseTitles(studId);
    }

    public void printStudentCourseTitles(String studId) {
        Student st = searchStudentOnId(studId);
        System.out.print("List of courses: ");
        Iterator<Course> itr = st.getCourseList().iterator();
        while (itr.hasNext()) {
            Course element = itr.next();
            System.out.print(element.getTitle() + "\t(" + element.getCourseID() + ")\t");
            if (itr.hasNext()) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public void printCourseStudents(String courseId) throws Exception {
        Course c = searchCourseOnId(courseId);
        if (c == null) {
            throw new Exception();
        }
        Iterator<Student> itr2 = c.getStudents().iterator();
        if (!courseContainsStudents(courseId)) {
            System.out.println("There are no students, signed up for a course!");
        } else {
            System.out.println("Students signed up for the course: ");
            int i = 0;
            while (itr2.hasNext()) {
                Student obj = itr2.next();
                System.out.println(++i + "." + obj.getFirstName() + " " + obj.getLastName() + " " + obj.getID());
            }
        }
    }

    public void printInfoAboutAllStudents() {
        Collection col = students.values();
        Set<Student> c = new TreeSet<>(col);
        Iterator<Student> itr = c.iterator();
        int i = 0;
        while (itr.hasNext()) {
            Student stud = itr.next();
            System.out.println((i++) + ". " + stud.getID() + "\t" + stud.getLastName() + "\t" + stud.getFirstName());
        }
    }

    public boolean courseContainsStudent(String studId, String courseId) {
        Student st = searchStudentOnId(studId);
        Course course = searchCourseOnId(courseId);
        try {
            if (course.getStudents().contains(st)) {
                return true;
            }
        } catch (NullPointerException e) {
        }

        return false;
    }


    //TUTOR
    public Tutor registerTutor(String firstName, String lastName) {
        Tutor tut = new Tutor(firstName, lastName);
        tut.setLogPass(tut.getID(), tut.getID() + "1");
        tutors.put(tut.getID(), tut);
        return tut;
        //assignTutorAtCourses(tut.getID(), coursesIds)
    }


    public void addTutorAtCourse(String tutId, String courseId) throws Exception {
        Tutor t = searchTutorInMapCollOnId(tutId);
        Course c = searchCourseOnId(courseId);
        if (c.getTutor() != null) {
            removeTutorFromCourse(c.getTutor().getID(), courseId);
        }
        c.setTutor(t);
        t.addCourse(c);
    }

    public void removeTutorFromCourse(String tutId, String courseId) throws Exception {
        Course c = searchCourseOnId(courseId);
        Tutor t = searchTutorInMapCollOnId(tutId);
        if (c.getTutor().equals(t)) {
            c.setTutor(null);
            t.removeCourse(c);
        } else {
            throw new Exception("Mentioned tutor doesn't teach this course!");
        }
    }

    public void printTutorInfo(String tutId) throws Exception {
        Tutor t = searchTutorInMapCollOnId(tutId);
        if (t == null) {
            throw new Exception();
        }
        System.out.print(t);//распечатает ФИО Tutor, если он когда либо регистрировался в LMS
        t = searchTutorOnId(tutId);
        if (t != null) {
            Iterator<Course> itr2 = t.getCourseList().iterator();
            System.out.print("List of courses: ");
            while (itr2.hasNext()) {
                Course element = itr2.next();
                System.out.print(element.getTitle());
                if (itr2.hasNext()) {
                    System.out.print(", ");
                }
            }
        } else {
            System.out.println("This tutor hasn't any courses!");
        }
        System.out.println();
    }

    public Tutor searchTutorInMapCollOnId(String tutorId) {
        Iterator<Map.Entry<String, Tutor>> itr = tutors.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Tutor> entry = itr.next();
            if (entry.getKey().equals(tutorId)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Tutor searchTutorOnId(String tutorId) {
        Iterator<Course> itr = courseList.iterator();
        while (itr.hasNext()) {
            Course element = itr.next();
            if (element.getTutor() != null) {
                if (element.getTutor().getID().equals(tutorId)) {
                    return element.getTutor();
                }
            }

        }
        return null;
    }

    public boolean tutorContainsCourse(String tutId, String courseId) {
        Tutor tutor = searchTutorOnId(tutId);
        Course course = searchCourseOnId(courseId);
        try {
            if (tutor.getCourseList().contains(course)) {
                return true;
            }
        } catch (NullPointerException e) {
        }

        return false;
    }

    public boolean courseContainsTutor(String courseId) {
        Course c = searchCourseOnId(courseId);
        if (c.getTutor() != null) {
            System.out.println("The course " + c.getTitle() + " has got a tutor: " + c.getTutor().getLastName() + " "
                    + c.getTutor().getFirstName() + " (" + c.getTutor().getID() + ")");
            return true;
        }
        return false;
    }

    public void printInfoAboutAllTutors() {
        Collection col = tutors.values();
        Set<Tutor> c = new TreeSet<>(col);
        Iterator<Tutor> itr = c.iterator();
        int i = 0;
        while (itr.hasNext()) {
            Tutor tut = itr.next();
            System.out.println((i++) + ". " + tut.getID() + "\t" + tut.getLastName() + "\t" + tut.getFirstName());
        }
    }

    public boolean tutorContainsAnyCourses(String tutId) {
        Tutor tutor = searchTutorOnId(tutId);
        try {
            if (!tutor.getCourseList().isEmpty())
                return true;
        } catch (NullPointerException e) {
        }
        return false;
    }

    public void printTutorsCourses(String tutId) {
        Tutor tutor = searchTutorOnId(tutId);
        if (tutorContainsAnyCourses(tutId)) {
            Set<Course> list = tutor.getCourseList();
            Iterator<Course> itr = list.iterator();
            while (itr.hasNext()) {
                Course element = itr.next();
                System.out.println(element.getCourseID() + "\t" + element.getTitle());
            }
        }
    }


    //GRADEBOOK
    public void setGradeBook(String courId, String studId, int taskId, int ball) {
        String stGradePerTask = courId + "/" + studId + "/" + taskId;
        gradeBook.put(stGradePerTask, ball);
    }

    public void printGradeBook(String courseId) throws Exception {
        Course course = searchCourseOnId(courseId);
        if (course == null) {
            throw new Exception();
        }
        Set<String> keyset = gradeBook.keySet();
        Iterator<String> itr = keyset.iterator();
        String key = null;
        Task t = null;
        TaskType taskType = null;
        System.out.println(courseId + " " + course.getTitle() + ": ");
        while (itr.hasNext()) {
            key = itr.next();
            String[] words = key.split("[/]");
            if (words[0].equals(courseId)) {
                Student student = searchStudentOnId(words[1]);
                String taskId = words[2];
                int ball = gradeBook.get(key);
                Set<Task> listOfTasks = course.getTasks();
                Iterator<Task> itr2 = listOfTasks.iterator();
                while (itr2.hasNext()) {
                    t = itr2.next();
                    if (Integer.valueOf(t.getId()).equals(Integer.valueOf(taskId))) {
                        taskType = t.getType();
                        break;
                    }
                }
                System.out.println(student.getFirstName() + " " +
                        student.getLastName() + ";" + "\t" + "TaskId " + taskId + ";" + "\t" +
                        "Task type: " + taskType + ";" + " " + "Ball: " + ball);

            } else {
                System.out.println("There are no any notes in the gradebook!");
                return;
            }
        }

    }

    public void deleteNotesAboutStudentGradeB(String courId, String studId) {
        Set<String> keyset = gradeBook.keySet();                     //отримати колекцію ключів в gradeBook
        Iterator<String> itr = keyset.iterator();
        String key = null;
        Map<String, Integer> gradeBook2 = new LinkedHashMap<>();    //створити колекцію маску, яка буде містити записи,
        //які треба видалити із основної колекції gradeBook
        while (itr.hasNext()) {
            key = itr.next();
            String[] words = key.split("[/]");
            if (words[0].equals(courId) && words[1].equals(studId)) {//знайти пару (Key,Value),які будуть містити courId та studId,
                // де courId ідентиф курса, який студент викреслив зі свого списку
                gradeBook2.put(key, gradeBook.get(key));            //помістити копію пари (Key,Value) в колекцію-маску gradeBook2
            }
        }
        Set<String> keyset4 = gradeBook2.keySet();
        Iterator<String> itr3 = keyset.iterator();                  //задаємо ще один раз ітератор, щоб  пройтися по gradeBook
        Iterator<String> itr4 = keyset4.iterator();                 //задаємо  ітератор, щоб  пройтися по колекції-масці gradeBook2
        while (itr3.hasNext() && itr4.hasNext()) {                  //ітеруємо одночасно поелементно в колекціях gradeBook та gradeBook2
            key = itr4.next();
            gradeBook.remove(key);                                  //прибираємо із колекції gradeBook всі пари (Key,Value) із gradeBook2
        }
    }

    public void saveGradebookInFile() {
        PrintStream stdout = System.out;
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(new FileOutputStream("Out1.txt"));
            System.setOut(printStream);
            Iterator<Course> itr = courseList.iterator();
            int i = 1;
            while (itr.hasNext()) {
                Course element = itr.next();
                System.out.println((i++) + ". " + element.getTitle());
                printGradeBook(element.getCourseID());
            }
            System.setOut(stdout);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
        } finally {
            if (printStream != null) printStream.close();
        }
    }

    //INITIAL_DATA
    public void createInitialData() {

        //CREATE_ADMIN
        admin = new Person("A", "B");
        admin.setID("Admin");
        admin.setLogPass("admin", "admin");


        //CREATE 3 COURSES
        Course course1 = createCourse("Java for Beginners", "Course for people that want learn Java programming language",
                "01.01.2015", "01.04.2015", "Tue, Wed, Sat");
        Course course2 = createCourse("Programming FrontEnd", "Course for people that want learn FrontEnd",
                "03.01.2015", "03.04.2015", "Mon, Thu");
        Course course3 = createCourse("Software Testing", "Course for people that want learn Software Testing",
                "10.01.2015", "10.04.2015", "Mon, Fri");
        Course course4 = createCourse("Telecommunication Systems", "Course for people that want learn Telecommunication technologies",
                "05.01.2015", "05.04.2015", "Mon, Fri");

        // CREATE COURSE TASKS
        createCourseTask("Collections", course1.getCourseID(), TaskType.LAB);
        createCourseTask("Collections", course1.getCourseID(), TaskType.TEST);
        createCourseTask("Generics", course1.getCourseID(), TaskType.LAB);
        createCourseTask("Generics", course1.getCourseID(), TaskType.TEST);

        //CREATE TUTORS
        Tutor tutor1 = registerTutor("Olga", "Nikitenko");
        Tutor tutor2 = registerTutor("Iryna", "Buz");
        Tutor tutor3 = registerTutor("Ivan", "Pelyh");
        Tutor tutor4 = registerTutor("Olga", "Pelyh");

        //ASSIGN TUTORS AT COURSES
        try {
            addTutorAtCourse(tutor1.getID(), course1.getCourseID());
            addTutorAtCourse(tutor2.getID(), course2.getCourseID());
            addTutorAtCourse(tutor3.getID(), course3.getCourseID());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //CREATE AND REGISTER STUDENTS FOR  COURSES
        Student student1 = registerStudent("Kirill", "Lavrik", course1.getCourseID(), course2.getCourseID());
        Student student2 = registerStudent("Olga", "Petryk", course1.getCourseID());
        Student student3 = registerStudent("Orest", "Anisim", course3.getCourseID());
        Student student4 = registerStudent("Kirill2", "Lavrik2", course1.getCourseID());
        Student student5 = registerStudent("Kirill3", "Lavrik3", course1.getCourseID());
        Student student6 = registerStudent("Kirill4", "Lavrik4", course1.getCourseID());
        Student student7 = registerStudent("Kirill5", "Lavrik5", course1.getCourseID());
        Student student8 = registerStudent("Kirill6", "Lavrik6", course1.getCourseID());
        Student student9 = registerStudent("Kirill7", "Lavrik7", course1.getCourseID());
        Student student10 = registerStudent("Kirill8", "Lavrik8", course1.getCourseID());
        Student student11 = registerStudent("Kirill9", "Lavrik9", course1.getCourseID());
        Student student12 = registerStudent("Kirill10", "Lavrik10", course1.getCourseID());
        Student student13 = registerStudent("Kirill11", "Lavrik11", course1.getCourseID());


        //CREATE ELEMENTS OF GRADEBOOK
        setGradeBook(course1.getCourseID(), student1.getID(), 0, 5);
        setGradeBook(course1.getCourseID(), student1.getID(), 1, 4);
        setGradeBook(course1.getCourseID(), student1.getID(), 2, 4);
        setGradeBook(course1.getCourseID(), student1.getID(), 3, 3);
        setGradeBook(course1.getCourseID(), student2.getID(), 0, 5);
        setGradeBook(course1.getCourseID(), student2.getID(), 1, 5);
        setGradeBook(course1.getCourseID(), student2.getID(), 2, 5);
        setGradeBook(course1.getCourseID(), student2.getID(), 3, 5);
    }

    //LOGIN_PASSWORD
    public String searchOnLoginPass(String login, String pass) {
        if (admin.getLogin().equals(login) && admin.getPassword().equals(pass)) {
            return admin.getID();
        } else {
            Collection<Student> stud = students.values();
            Iterator<Student> itr = stud.iterator();
            while (itr.hasNext()) {
                Student element = itr.next();
                if (element.getLogin().equals(login) && element.getPassword().equals(pass)) {
                    return element.getID();
                }
            }
            Collection<Tutor> tut = tutors.values();
            Iterator<Tutor> itr2 = tut.iterator();
            while (itr2.hasNext()) {
                Tutor element = itr2.next();
                if (element.getLogin().equals(login) && element.getPassword().equals(pass)) {
                    return element.getID();
                }
            }
        }
        return null;
    }

}

