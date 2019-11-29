package service;

import dao.UserDAO;
import entity.User;

import javax.jws.soap.SOAPBinding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class UserService {
    private static UserDAO userDAO = new UserDAO();

    private static void add(User entity) {
        userDAO.add(entity);
    }

    private static User getByID(int ID) {
        return userDAO.getByID(ID);
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

    public static void run() {
        UserService userService = new UserService();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Use next commands to work with table:\n" +
                "add  getByID  getAll  edit  remove");
        try {
            String choice = bufferedReader.readLine();
            switch (choice) {
                case "add":
                    System.out.println("Enter: user_ID first_name last_name role");
                    String input = bufferedReader.readLine();
                    String[] data = input.split(" ");
                    User user = new User(Integer.parseInt(data[0]), data[1], data[2], data[3]);
                    add(user);
                    System.out.println("User added successfully");
                    break;
                default:
                    System.out.println("non-specified command");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}