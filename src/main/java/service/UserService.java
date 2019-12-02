package service;

import dao.DAOFactory;
import dao.UserDAO;
import entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class UserService {
    private static UserDAO userDAO = DAOFactory.getUserDAO();

    private static void add(User entity) {
        userDAO.add(entity);
    }

    private static User getById(int id) {
        return userDAO.getById(id);
    }

    private static List<User> getAll() {
        return userDAO.getAll();
    }

    private static void edit(User entity) {
        userDAO.edit(entity);
    }

    private static void remove(User entity) {
        userDAO.remove(entity);
    }

    public static void printCourseCreator() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = ""; // default value of incorrect input case
        System.out.println("Enter course name:");
        try {
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(userDAO.getCourseCreator(userInput));
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
        for(User user : userDAO.getStudentsByCourse(userInput)) {
            System.out.println(user);
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
        for(User user : userDAO.getStudentsByTeacherName(userInput)) {
            System.out.println(user);
        }
    }

    public static void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        User user;
        System.out.println("Use next commands to work with table:\n" +
                "add  getByID  getAll  edit  remove");
        try {
            String choice = bufferedReader.readLine();
            String userInput;
            String[] data;
            switch (choice) {
                case "add":
                    System.out.println("Enter: user_ID first_name last_name role");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    user = new User(Integer.parseInt(data[0]), data[1], data[2], data[3]);
                    add(user);
                    System.out.println("User added successfully");
                    break;
                case "getByID":
                    System.out.println("Enter user_id");
                    userInput = bufferedReader.readLine();
                    System.out.println(getById(Integer.parseInt(userInput)));
                    break;
                case "getAll":
                    System.out.println("List of the users:");
                    for(User user1 : getAll()) {
                        System.out.println(user1);
                    }
                    break;
                case "edit":
                    System.out.println("Enter next data to choose user needed to update:\n" +
                            "user_ID first_name last_name role");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    user = new User(Integer.parseInt(data[0]), data[1], data[2], data[3]);
                    edit(user);
                    System.out.println("User edited successfully");
                    break;
                case "remove":
                    System.out.println("Enter next data to choose user needed to delete:\n" +
                            "user_ID first_name last_name role");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    user = new User(Integer.parseInt(data[0]), data[1], data[2], data[3]);
                    remove(user);
                    System.out.println("User deleted successfully");
                    break;
                default:
                    System.out.println("non-specified command");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}