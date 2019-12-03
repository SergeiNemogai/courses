package application;

import entity.Study;
import service.ServiceFactory;
import service.StudyService;

import java.io.BufferedReader;
import java.io.IOException;

class StudyCommand {
    private static StudyService studyService = ServiceFactory.getStudyService();

    static void execute(BufferedReader bufferedReader) {
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
                    study = Study.builder()
                            .id(Integer.parseInt(data[0]))
                            .courseId(Integer.parseInt(data[1]))
                            .userId(Integer.parseInt(data[2]))
                            .build();
                    studyService.add(study);
                    System.out.println("Study added successfully");
                    break;
                case "getByID":
                    System.out.println("Enter id:");
                    userInput = bufferedReader.readLine();
                    System.out.println(studyService.getById(Integer.parseInt(userInput)));
                    break;
                case "getAll":
                    System.out.println("List of the student_courses:");
                    for(Study course : studyService.getAll()) {
                        System.out.println(course);
                    }
                    break;
                case "edit":
                    System.out.println("Enter next data to choose studied course needed to update:\n" +
                            "id course_id user_id");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    study = Study.builder()
                            .id(Integer.parseInt(data[0]))
                            .courseId(Integer.parseInt(data[1]))
                            .userId(Integer.parseInt(data[2]))
                            .build();
                    studyService.edit(study);
                    System.out.println("Created course edited successfully");
                    break;
                case "remove":
                    System.out.println("Enter next data to choose created course needed to delete:\n" +
                            "user_id course_id");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    study = Study.builder()
                            .id(Integer.parseInt(data[0]))
                            .courseId(Integer.parseInt(data[1]))
                            .userId(Integer.parseInt(data[2]))
                            .build();
                    studyService.remove(study);
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