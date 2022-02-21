package Facade;

import Beans.Scheduler;
import Beans.Task;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Program {
    private Scheduler scheduler;
    private Scanner scanner = new Scanner(System.in);

    public Program() {
        this.scheduler = new Scheduler();
        mainMenu();
    }

    public void mainMenu() {
        boolean quit = false;
        do {
            header("Menu");
            System.out.println("\nWhat fo you want to do?" +
                    "\n\t1. add Task" +
                    "\n\t2. See all Task" +
                    "\n\t3. See Task by ID" +
                    "\n\t4. Delete Task by ID " +
                    "\n\t5. Quit" +
                    "\nEnter Choice (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    printAllTasks();
                    break;
                case 3:
                    printTaskbyID();
                    break;
                case 4:
                    deleteTaskbyID();
                    break;
                case 5:
                    quit = quitProgram();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } while (quit == false);


    }

    private void header(String str) {
        System.out.println();
        for (int i = 0; i < 30; i += 1) {
            System.out.print("-");
        }
        System.out.println();
        System.out.print("|");
        int space = ((30 - str.length())-2) / 2;
        for (int i = 0 ; i < space; i+=1) {
            System.out.print(" ");
        }
        System.out.print(str);
        for (int i = 0 ; i < space; i+=1) {
            System.out.print(" ");
        }
        System.out.println("|");
        for (int i = 0; i < 30; i += 1) {
            System.out.print("-");
        }
        System.out.println();
    }

    private boolean quitProgram() {
        System.out.println("Bye Bye!");
        System.exit(200);
        return true;
    }

    private void deleteTaskbyID() {
        System.out.println("Enter ID #:");
        int id = scanner.nextInt();
        scanner.nextLine();
        scheduler.deleteByID(id);
    }

    private void printTaskbyID() {
        System.out.println("Enter ID #:");
        int id = scanner.nextInt();
        scheduler.printByID(id);
    }

    private void printAllTasks() {
        scheduler.displayTasks();
    }

    private void addTask() {
        header("Add Task");
        System.out.println("Enter Description: ");
        String description = scanner.nextLine();
        System.out.println("Enter Deadline:");
        System.out.println("Year: ");
        int year = scanner.nextInt();
        System.out.println("Month: ");
        int month = scanner.nextInt();
        System.out.println("Day: ");
        int day = scanner.nextInt();
        System.out.println("Hour: ");
        int hour = scanner.nextInt();
        System.out.println("Minutes: ");
        int minutes = scanner.nextInt();

        scheduler.addTask(new Task(description, LocalDateTime.of(year, month, day, hour, minutes)));


    }
}
