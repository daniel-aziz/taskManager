package Threads;

import Beans.Scheduler;
import Beans.Task;

import java.util.ArrayList;
import java.util.List;

public class SchedulerThread extends Thread {
    private List<Task> myTasks;
    private boolean stopThread;

    public SchedulerThread(List<Task> tasks) {
        this.myTasks = tasks;
        this.stopThread = false;
    }

    @Override
    public void run() {
        while (!stopThread) {
            if (!myTasks.isEmpty()) {
                for (Task item : myTasks) {
                    Scheduler.checkDeadline(item);
                }
            }
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException err) {
                System.out.println(err.getMessage());
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }
    }

    public boolean isStopThread() {
        return stopThread;
    }

    public void setStopThread(boolean stopThread) {
        this.stopThread = stopThread;
    }
}
