import dataModels.*;
import dataModels.dbService.DBService;
import dataModels.dbService.controllers.ProjectDBController;
import dataModels.dbService.controllers.TaskDBController;
import dataModels.dbService.controllers.UserDBController;
import gui.PrimaryForm;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger log = LogManager.getLogger(Main.class.getName());

    public static void main(String[] arg) throws Exception {

        DBService dbService = DBService.init();

        UserController userController = UserDBController.init(dbService.getSessionFactory());

        ProjectController projectController = ProjectDBController.init(dbService.getSessionFactory());

        TaskController taskController = TaskDBController.init(dbService.getSessionFactory());

        PrimaryForm primaryForm = new PrimaryForm(userController, projectController, taskController);
        log.log(Level.INFO, "bugTracker is started");
        primaryForm.setVisible(true);

    }
}