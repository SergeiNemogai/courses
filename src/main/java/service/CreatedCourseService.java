package service;

import dao.CreatedCourseDAO;
import entity.CreatedCourse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CreatedCourseService {
    private static CreatedCourseDAO createdCourseDAO = new CreatedCourseDAO();

    private static void add(CreatedCourse entity) {
        createdCourseDAO.add(entity);
    }

    private static CreatedCourse getByID(int ID) {
        return createdCourseDAO.getByID(ID);
    }

    private static List<CreatedCourse> getAll() {
        return createdCourseDAO.getAll();
    }

    private static void edit(CreatedCourse entity) {
        createdCourseDAO.edit(entity);
    }

    private static void remove(CreatedCourse entity) {
        createdCourseDAO.remove(entity);
    }

    public static void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        CreatedCourse createdCourse;
        System.out.println("Use next commands to work with table:\n" +
                "add  getByID  getAll  edit  remove");
        try {
            String choice = bufferedReader.readLine();
            String userInput;
            String[] data;
            switch (choice) {
                case "add":
                    System.out.println("Enter: user_id course_id");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    createdCourse = new CreatedCourse(Integer.parseInt(data[0]),
                            Integer.parseInt(data[1]));
                    add(createdCourse);
                    System.out.println("Created course added successfully");
                    break;
                case "getByID":
                    System.out.println("Enter user_id");
                    userInput = bufferedReader.readLine();
                    System.out.println(getByID(Integer.parseInt(userInput)));
                    break;
                case "getAll":
                    System.out.println("List of the created_courses:");
                    for(CreatedCourse course : getAll()) {
                        System.out.println(course);
                    }
                    break;
                case "edit":
                    System.out.println("Enter next data to choose created course needed to update:\n" +
                            "user_id course_id");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    createdCourse = new CreatedCourse(Integer.parseInt(data[0]),
                            Integer.parseInt(data[1]));
                    edit(createdCourse);
                    System.out.println("Created course edited successfully");
                    break;
                case "remove":
                    System.out.println("Enter next data to choose created course needed to delete:\n" +
                            "user_id course_id");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    createdCourse = new CreatedCourse(Integer.parseInt(data[0]),
                            Integer.parseInt(data[1]));
                    remove(createdCourse);
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