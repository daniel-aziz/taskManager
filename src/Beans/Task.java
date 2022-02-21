package Beans;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Comparable<Task> {
    // Fields
    private static int idCounter = 1000;
    private int id;
    private String description;
    private boolean isDone;
    private LocalDateTime deadline;
    private boolean alertPopped;

    // CTRO
    public Task(String description, LocalDateTime deadline) {
        this.id = idCounter;
        this.description = description;
        this.deadline = deadline;
        this.isDone = false;
        this.alertPopped = false;
        idCounter += 1;
    }

    // GET'S N SET'S
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isAlertPopped() {
        return alertPopped;
    }

    public void setAlertPopped(boolean alertPopped) {
        this.alertPopped = alertPopped;
    }

    // METHODS


    @Override
    public String toString() {
        return "Task ID #" + id + "\n" +
                "Description: " + description +
                ", Deadline: " + deadline +
                ", is Done?: " + (isDone ? "Yes" : "No") +
                ", is Popped?: " + (alertPopped ? "Yes" : "No");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && isDone == task.isDone && alertPopped == task.alertPopped && Objects.equals(description, task.description) && Objects.equals(deadline, task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, isDone, deadline, alertPopped);
    }

    @Override
    public int compareTo(Task o) {
       if (this.id > o.id) {
           return 1;
       }
       return -1;
    }
}
