package application;

import entity.Course;
import service.CourseService;
import service.ServiceFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

class CourseCommand {
    private static CourseService courseService = ServiceFactory.getCourseService();

    static void printCoursesOnStudy() {
        System.out.println("Courses on study:");
        for (Course course : courseService.getCoursesOnStudy()) {
            System.out.println(course);
        }
    }

    static void execute(BufferedReader bufferedReader) {
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
                            .id(Long.parseLong(data[0]))
                            .name(data[1])
                            .createdAt(Date.valueOf(data[2]))
                            .startDateTime(Timestamp.valueOf(data[3]))
                            .endDateTime(Timestamp.valueOf(data[4]))
                            .status(data[5])
                            .build();
                    courseService.add(course);
                    System.out.println("Course added successfully");
                    break;
                case "getByID":
                    System.out.println("Enter course_id");
                    userInput = bufferedReader.readLine();
                    System.out.println(courseService.getById(Integer.parseInt(userInput)));
                    break;
                case "getAll":
                    System.out.println("List of the courses:");
                    for(Course course1 : courseService.getAll()) {
                        System.out.println(course1);
                    }
                    break;
                case "edit":
                    System.out.println("Enter next data to choose user needed to update:\n" +
                            "id name created_at start_at end_at status");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    course = Course.builder()
                            .id(Long.parseLong(data[0]))
                            .name(data[1])
                            .createdAt(Date.valueOf(data[2]))
                            .startDateTime(Timestamp.valueOf(data[3]))
                            .endDateTime(Timestamp.valueOf(data[4]))
                            .status(data[5])
                            .build();
                    courseService.edit(course);
                    System.out.println("Course edited successfully");
                    break;
                case "remove":
                    System.out.println("Enter next data to choose user needed to delete:\n" +
                            "id name created_at start_at end_at status");
                    userInput = bufferedReader.readLine();
                    data = userInput.split(" ");
                    course = Course.builder()
                            .id(Long.parseLong(data[0]))
                            .name(data[1])
                            .createdAt(Date.valueOf(data[2]))
                            .startDateTime(Timestamp.valueOf(data[3]))
                            .endDateTime(Timestamp.valueOf(data[4]))
                            .status(data[5])
                            .build();
                    courseService.remove(course);
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