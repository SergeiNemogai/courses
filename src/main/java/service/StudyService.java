package service;

import dao.DAOFactory;
import dao.StudyDAO;
import entity.Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class StudyService {
    private static StudyDAO studyDAO = DAOFactory.getStudyDAO();

    private static void add(Study entity) {
        studyDAO.add(entity);
    }

    private static Study getById(int id) {
        return studyDAO.getById(id);
    }

    private static List<Study> getAll() {
        return studyDAO.getAll();
    }

    private static void edit(Study entity) {
        studyDAO.edit(entity);
    }

    private static void remove(Study entity) {
        studyDAO.remove(entity);
    }

    public static void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Study study;
        System.out.println("Use next commands to work with table:\n" +
                "add  getByID  getAll  edit  remove");
        try {
            String choice = bufferedReader.readLine();
            String userInput;
            String[] data;
            switch (choice) {
                case "add":
                    System.out.println("Enter: id course_id user_id");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    study = new Study(Integer.parseInt(data[0]),
                            Integer.parseInt(data[1]),
                            Integer.parseInt(data[2]));
                    add(study);
                    System.out.println("Study added successfully");
                    break;
                case "getByID":
                    System.out.println("Enter id:");
                    userInput = bufferedReader.readLine();
                    System.out.println(getById(Integer.parseInt(userInput)));
                    break;
                case "getAll":
                    System.out.println("List of the student_courses:");
                    for(Study course : getAll()) {
                        System.out.println(course);
                    }
                    break;
                case "edit":
                    System.out.println("Enter next data to choose studied course needed to update:\n" +
                            "id course_id user_id");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    study = new Study(Integer.parseInt(data[0]),
                            Integer.parseInt(data[1]),
                            Integer.parseInt(data[2]));
                    edit(study);
                    System.out.println("Created course edited successfully");
                    break;
                case "remove":
                    System.out.println("Enter next data to choose created course needed to delete:\n" +
                            "user_id course_id");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    study = new Study(Integer.parseInt(data[0]),
                            Integer.parseInt(data[1]),
                            Integer.parseInt(data[2]));
                    remove(study);
                    System.out.println("Studied course deleted successfully");
                    break;
                default:
                    System.out.println("non-specified command");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}