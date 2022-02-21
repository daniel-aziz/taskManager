package DAO;

import Beans.Task;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface Dao {
    Boolean addTask(Task task) throws SQLException;
    List<Task> getTasksList();
    Set<Task> getTasksSet();
}
