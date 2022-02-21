package DBDAO;

import Beans.Task;
import DAO.Dao;
import SQL.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class TaskDB implements Dao {
    private static final String ADD_TASK = "INSERT INTO `taskManager`.`tasks`(`task_id`,`description`,`deadline`,`isDone`,`isPopped`) VALUES(?,?,?,?,?);";
    private static final String UPDATE_REPAIR = "UPDATE `taskManager`.`tasks` set description=?,deadline=?,isDone=?,isPopped=? WHERE task_id=?;";
    private static final String DELETE_BY_ID = "DELETE * FROM `taskManager`.`tasks` WHERE=?;";
    private static final String GET_ONE_REPAIR_BY_ID = "SELECT * FROM `taskManager`.`tasks` WHERE task_id=?;";
    private static final String GET_ALL_TASKS = "SELECT * FROM `taskManager`.`tasks`;";

    private Connection connection;
    @Override
    public Boolean addTask(Task task) throws SQLException {
        try {
            //get connection to the database
            connection = ConnectionPool.getInstance().getConnection();

            //create a prepared sql statement
            PreparedStatement statement = connection.prepareStatement(ADD_TASK);
            statement.setInt(1, task.getId());
            statement.setString(2, task.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(task.getDeadline()));
            statement.setBoolean(4, task.isDone());
            statement.setBoolean(5, task.isAlertPopped());
            statement.execute();
            return true;
        } catch (InterruptedException | SQLException e) {
            return false;
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    @Override
    public List<Task> getTasksList() {
        return null;
    }

    @Override
    public Set<Task> getTasksSet() {
        return null;
    }
}
