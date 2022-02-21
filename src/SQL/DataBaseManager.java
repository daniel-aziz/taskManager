package SQL;

import java.sql.SQLException;

public class DataBaseManager {
    //connection DB
    public static final String URL = "jdbc:mysql://localhost:3306?createDatabaseIfNotExist=FALSE";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "12345678";

    //create & drop database
    public static final String CREATE_DB = "CREATE SCHEMA if not exists taskManager";
    public static final String DROP_DB = "DROP TaskManager";

    // create & drop table
    public static final String CREATE_TABLE = "CREATE TABLE if not exists `taskManager`.`tasks` (" +
            "  `task_id` INT NOT NULL," +
            "  `description` VARCHAR(2000) NULL," +
            "  `deadline` TIMESTAMP NULL," +
            "  `isDone` BIT NULL DEFAULT 0," +
            "  `isPopped` BIT NULL DEFAULT 0," +
            "  PRIMARY KEY (`task_id`));";

    public static void CrateDataBase() {
        try {
            DButils.runQuery(CREATE_DB);
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

    }

    public static void CreateTable() {

        try {
            DButils.runQuery(CREATE_TABLE);
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }

    }


}
