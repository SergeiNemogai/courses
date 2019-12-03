package service;

import dao.CourseDAO;
import dao.DAOFactory;
import entity.Course;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class CourseService {
    private static CourseDAO courseDAO = DAOFactory.getCourseDAO();

    private static void add(Course entity) {
        courseDAO.add(entity);
    }

    private static Course getById(int id) {
        return courseDAO.getById(id);
    }

    private static List<Course> getAll() {
        return courseDAO.getAll();
    }

    private static void edit(Course entity) {
        courseDAO.edit(entity);
    }

    private static void remove(Course entity) {
        courseDAO.remove(entity);
    }

    public static void printCoursesOnStudy() {
        System.out.println("Courses on study:");
        for(Course course : courseDAO.getCoursesOnStudy()) {
            System.out.println(course);
        }
    }

    public static void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Course course;
        System.out.println("Use next commands to work with table:\n" +
                "add  getByID  getAll  edit  remove");
        try {
            String choice = bufferedReader.readLine();
            String userInput;
            String[] data;
            switch (choice) {
                case "add":
                    System.out.println("Enter: id name created_at start_at end_at status");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    course = Course.builder()
                            .id(Integer.parseInt(data[0]))
                            .name(data[1])
                            .createdAt(Date.valueOf(data[2]))
                            .startDateTime(Timestamp.valueOf(data[3]))
                            .endDateTime(Timestamp.valueOf(data[4]))
                            .status(data[5])
                            .build();
                    add(course);
                    System.out.println("Course added successfully");
                    break;
                case "getByID":
                    System.out.println("Enter course_id");
                    userInput = bufferedReader.readLine();
                    System.out.println(getById(Integer.parseInt(userInput)));
                    break;
                case "getAll":
                    System.out.println("List of the courses:");
                    for(Course course1 : getAll()) {
                        System.out.println(course1);
                    }
                    break;
                case "edit":
                    System.out.println("Enter next data to choose user needed to update:\n" +
                            "id name created_at start_at end_at status");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    course = Course.builder()
                            .id(Integer.parseInt(data[0]))
                            .name(data[1])
                            .createdAt(Date.valueOf(data[2]))
                            .startDateTime(Timestamp.valueOf(data[3]))
                            .endDateTime(Timestamp.valueOf(data[4]))
                            .status(data[5])
                            .build();
                    edit(course);
                    System.out.println("Course edited successfully");
                    break;
                case "remove":
                    System.out.println("Enter next data to choose user needed to delete:\n" +
                            "id name created_at start_at end_at status");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    course = Course.builder()
                            .id(Integer.parseInt(data[0]))
                            .name(data[1])
                            .createdAt(Date.valueOf(data[2]))
                            .startDateTime(Timestamp.valueOf(data[3]))
                            .endDateTime(Timestamp.valueOf(data[4]))
                            .status(data[5])
                            .build();
                    remove(course);
                    System.out.println("Course deleted successfully");
                    break;
                default:
                    System.out.println("non-specified command");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}