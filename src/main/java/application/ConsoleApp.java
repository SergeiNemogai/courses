package application;

import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleApp {
    public static void main(String[] args) {
        System.out.println("Hello. Welcome to simple CRUD console app.\n" +
                "Choose table you want to work with or enter 'exit' command:\n" +
                "courses  created_courses  roles  statuses  study  users");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String choice = bufferedReader.readLine();
            while(!choice.equals("exit")) {
                switch (choice) {
                    case "courses":
                        System.out.println(1);
                        break;
                    case "created_courses":
                        System.out.println(2);
                        break;
                    case "roles":
                        System.out.println(3);
                        break;
                    case "statuses":
                        System.out.println(4);
                        break;
                    case "study":
                        System.out.println(5);
                        break;
                    case "users":
                        System.out.println("Table 'users' selected");
                        UserService.run();
                        break;
                    default:
                        System.out.println("Incorrect input! Try again");
                }
                System.out.println("Choose table you want to work with or enter 'exit' command:\n" +
                        "courses  created_courses  roles  statuses  study  users");
                choice = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}