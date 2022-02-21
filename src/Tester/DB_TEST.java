package Tester;

import Beans.Task;
import DBDAO.TaskDB;
import SQL.ConnectionPool;
import SQL.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DB_TEST {
    public static void main(String[] args) throws SQLException{

            ConnectionPool.getInstance().openAllConnections();

        TaskDB tdb = new TaskDB();
        Task task = new Task("go to the mall", LocalDateTime.of(2023, 12, 10, 15, 30));
        tdb.addTask(task);




    }
}
