package application;

import entity.User;
import service.ServiceFactory;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

class UserCommand {
    private static UserService userService = ServiceFactory.getUserService();

    static void printCourseCreator(BufferedReader bufferedReader) {
        String userInput = ""; // default value of incorrect input case
        System.out.println("Enter course name:");
        try {
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(userService.getCourseCreator(userInput));
    }

    static void printStudentsByCourse(BufferedReader bufferedReader) {
        String userInput = ""; // default value of incorrect input case
        System.out.println("Enter course name:");
        try {
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(User user : userService.getStudentsByCourse(userInput)) {
            System.out.println(user);
        }
    }

    static void printStudentsByTeacherName(BufferedReader bufferedReader) {
        String userInput = ""; // default value of incorrect input case
        System.out.println("Enter teacher name:");
        try {
            userInput = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(User user : userService.getStudentsByTeacherName(userInput)) {
            System.out.println(user);
        }
    }

    static void execute(BufferedReader bufferedReader) {
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
                    user = User.builder()
                            .id(Long.parseLong(data[0]))
                            .firstName(data[1])
                            .lastName(data[2])
                            .role(data[3]).build();
                    userService.add(user);
                    System.out.println("User added successfully");
                    break;
                case "getByID":
                    System.out.println("Enter user_id");
                    userInput = bufferedReader.readLine();
                    System.out.println(userService.getById(Integer.parseInt(userInput)));
                    break;
                case "getAll":
                    System.out.println("List of the users:");
                    for(User user1 : userService.getAll()) {
                        System.out.println(user1);
                    }
                    break;
                case "edit":
                    System.out.println("Enter next data to choose user needed to update:\n" +
                            "user_ID first_name last_name role");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    user = User.builder()
                            .id(Long.parseLong(data[0]))
                            .firstName(data[1])
                            .lastName(data[2])
                            .role(data[3]).build();
                    userService.edit(user);
                    System.out.println("User edited successfully");
                    break;
                case "remove":
                    System.out.println("Enter next data to choose user needed to delete:\n" +
                            "user_ID first_name last_name role");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    user = User.builder()
                            .id(Long.parseLong(data[0]))
                            .firstName(data[1])
                            .lastName(data[2])
                            .role(data[3]).build();
                    userService.remove(user);
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