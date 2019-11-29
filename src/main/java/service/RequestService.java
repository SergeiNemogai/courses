package service;

import dao.RequestDAO;
import entity.Course;
import entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestService {
    private static RequestDAO requestDAO = new RequestDAO();

    public static void printCourseCreator() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = ""; // default value of incorrect input case
        System.out.println("Enter course name:");
        try {
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(requestDAO.getCourseCreator(userInput));
    }

    public static void printStudentsByCourse() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = ""; // default value of incorrect input case
        System.out.println("Enter course name:");
        try {
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(User user : requestDAO.getStudentsByCourse(userInput)) {
            System.out.println(user);
        }
    }

    public static void printCoursesOnStudy() {
        System.out.println("Courses on study:");
        for(Course course : requestDAO.getCoursesOnStudy()) {
            System.out.println(course);
        }
    }

    public static void printStudentsByTeacherName() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = ""; // default value of incorrect input case
        System.out.println("Enter teacher name:");
        try {
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(User user : requestDAO.getStudentsByTeacherName(userInput)) {
            System.out.println(user);
        }
    }
}