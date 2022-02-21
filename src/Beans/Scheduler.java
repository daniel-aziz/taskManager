package Beans;

import Beans.Task;
import Threads.SchedulerThread;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Scheduler {
    private List<Task> tasks;
    private boolean monitoringActive;
    private long checkRateMillis;
    private SchedulerThread schedulerThread;

    public Scheduler() {
        this.tasks = new ArrayList<>();
        this.schedulerThread = new SchedulerThread(tasks);
        startMonitoringTasks();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Empty Tasks!");
        }
        tasks.forEach(System.out::println);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getAllTasksUntil (LocalDateTime time) {
        List<Task> taskDate = new ArrayList<>();
        for (Task item:tasks) {
            if (item.getDeadline().isBefore(time)) {
                taskDate.add(item);
            }
        }
        return taskDate;
    }

    public void startMonitoringTasks() {
        monitoringActive = true;
        schedulerThread.start();
    }

    public void stopMonitoringTasks() {
        monitoringActive = false;
        schedulerThread.setStopThread(true);
    }

    public static void checkDeadline (Task task) {
        LocalDateTime now = LocalDateTime.now();
        if (task.getDeadline().isBefore(now) && !task.isAlertPopped()) {
            System.out.println(task);
            System.out.println("DeadLine Alert!!!");
            task.setAlertPopped(true);
        }
    }

    public void printByID (int id){
        if (!tasks.isEmpty()) {
            for (Task item : tasks) {
                if (id == item.getId()) {
                    System.out.println(item);
                }
            }
        }
    }

    public void deleteByID (int id) {
        try {
            if (!tasks.isEmpty()) {
                for (Task item : tasks) {
                    if (id == item.getId()) {
                        tasks.remove(item);
                    }
                    break;
                }
            }
        } catch (ConcurrentModificationException err) {
            System.out.println(err.getMessage());
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

    }
}
