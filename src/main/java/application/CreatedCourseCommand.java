package application;

import entity.CreatedCourse;
import service.CreatedCourseService;

import java.io.BufferedReader;
import java.io.IOException;

class CreatedCourseCommand {
    private static CreatedCourseService createdCourseService = new CreatedCourseService();

    static void execute(BufferedReader bufferedReader) {
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
                    createdCourse = CreatedCourse.builder()
                            .userId(Integer.parseInt(data[0]))
                            .courseId(Integer.parseInt(data[1]))
                            .build();
                    createdCourseService.add(createdCourse);
                    System.out.println("Created course added successfully");
                    break;
                case "getByID":
                    System.out.println("Enter user_id");
                    userInput = bufferedReader.readLine();
                    System.out.println(createdCourseService.getById(Integer.parseInt(userInput)));
                    break;
                case "getAll":
                    System.out.println("List of the created_courses:");
                    for(CreatedCourse course : createdCourseService.getAll()) {
                        System.out.println(course);
                    }
                    break;
                case "edit":
                    System.out.println("Enter next data to choose created course needed to update:\n" +
                            "user_id course_id");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    createdCourse = CreatedCourse.builder()
                            .userId(Integer.parseInt(data[0]))
                            .courseId(Integer.parseInt(data[1]))
                            .build();
                    createdCourseService.edit(createdCourse);
                    System.out.println("Created course edited successfully");
                    break;
                case "remove":
                    System.out.println("Enter next data to choose created course needed to delete:\n" +
                            "user_id course_id");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    createdCourse = CreatedCourse.builder()
                            .userId(Integer.parseInt(data[0]))
                            .courseId(Integer.parseInt(data[1]))
                            .build();
                    createdCourseService.remove(createdCourse);
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
