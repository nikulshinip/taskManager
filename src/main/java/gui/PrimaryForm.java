package gui;

import dataModels.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PrimaryForm extends JFrame {
    private static final Logger log = LogManager.getLogger(PrimaryForm.class.getName());

    private JComboBox<String> projectsComboBox;
    private JComboBox<String> usersComboBox;
    private JButton addTaskButton;
    private JButton editTaskButton;
    private JButton removeTaskButton;
    private JButton addProjectButton;
    private JButton editProjectButton;
    private JButton removeProjectButton;
    private JButton addUserButton;
    private JButton editUserButton;
    private JButton removeUserButton;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JTabbedPane jTabbedPane1;
    private JTable taskTable;
    private JTable projectTable;
    private JTable userTable;

    private UserController userController;
    private ProjectController projectController;
    private TaskController taskController;

    private String[][] userTableRows;
    private String[][] projectTableRows;
    private String[][] taskTableRows;

    public PrimaryForm(UserController userController, ProjectController projectController, TaskController taskController) {
        this.userController = userController;
        this.projectController = projectController;
        this.taskController = taskController;
        initComponents();
    }

    private void initComponents() {

        jTabbedPane1 = new JTabbedPane();
        jPanel2 = new JPanel();
        jScrollPane2 = new JScrollPane();
        taskTable = new JTable();
        projectsComboBox = new JComboBox<>();
        usersComboBox = new JComboBox<>();
        addTaskButton = new JButton("Добавить задачу");
        editTaskButton = new JButton("Редактировать");
        removeTaskButton = new JButton("Удалить");
        jPanel3 = new JPanel();
        jScrollPane3 = new JScrollPane();
        projectTable = new JTable();
        addProjectButton = new JButton("Добавить проект");
        editProjectButton = new JButton("Редактировать");
        removeProjectButton = new JButton("Удалить");
        jPanel4 = new JPanel();
        jScrollPane4 = new JScrollPane();
        userTable = new JTable();
        addUserButton = new JButton("Добавить пользователя");
        editUserButton = new JButton("Редактировать");
        removeUserButton = new JButton("Удалить");

        setLocation(200, 50);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("TaskManager");
        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(900, 600));

//таблица задач

        refreshProjectComboBox();
        projectsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTaskTable();
            }
        });

        refreshUserComboBox();
        usersComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTaskTable();
            }
        });

        refreshTaskTable();
        taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskTable.setDefaultEditor(Object.class, null);
        taskTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            //обработка выделения
            public void valueChanged(ListSelectionEvent e) {
                editTaskButton.setEnabled(true);
                removeTaskButton.setEnabled(true);
            }
        });

        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTaskButtonClick();
            }
        });
        editTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String taskName = taskTableRows[taskTable.getSelectedRow()][0];
                Task task = taskController.getTask(taskName);
                editTaskButtonClick(task);
            }
        });
        removeTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String taskName = taskTableRows[taskTable.getSelectedRow()][0];
                Task task = taskController.getTask(taskName);
                removeTaskButtonClick(task);
            }
        });

        jScrollPane2.setViewportView(taskTable);

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(306, Short.MAX_VALUE)
                                .addComponent(projectsComboBox)
                                .addGap(18, 18, 18)
                                .addComponent(usersComboBox)
                                .addGap(18, 18, 18)
                                .addComponent(addTaskButton)
                                .addGap(18, 18, 18)
                                .addComponent(editTaskButton)
                                .addGap(18, 18, 18)
                                .addComponent(removeTaskButton)
                                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(projectsComboBox)
                                        .addComponent(usersComboBox)
                                        .addComponent(addTaskButton)
                                        .addComponent(editTaskButton)
                                        .addComponent(removeTaskButton))
                                .addContainerGap())
        );

        jTabbedPane1.addTab("Задачи", jPanel2);

//таблица проектов
        refreshProjectTable();

        projectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        projectTable.setDefaultEditor(Object.class, null);
        projectTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            //обработка выделения
            public void valueChanged(ListSelectionEvent e) {
                editProjectButton.setEnabled(true);
                removeProjectButton.setEnabled(true);
            }
        });

        addProjectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProjectButtonClick();
            }
        });
        editProjectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String projectName = projectTableRows[projectTable.getSelectedRow()][0];
                Project project = projectController.getProject(projectName);
                editProjectButtonClick(project);
            }
        });
        removeProjectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String projectName = projectTableRows[projectTable.getSelectedRow()][0];
                Project project = projectController.getProject(projectName);
                removeProjectButtonClick(project);
            }
        });

        jScrollPane3.setViewportView(projectTable);

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(306, Short.MAX_VALUE)
                                .addComponent(addProjectButton)
                                .addGap(18, 18, 18)
                                .addComponent(editProjectButton)
                                .addGap(18,18,18)
                                .addComponent(removeProjectButton)
                                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(addProjectButton)
                                        .addComponent(editProjectButton)
                                        .addComponent(removeProjectButton))
                                .addContainerGap())
        );

        jTabbedPane1.addTab("Проекты", jPanel3);

//таблица пользователей
        refreshUserTable();

        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userTable.setDefaultEditor(Object.class, null);
        userTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            //обработка выделения
            public void valueChanged(ListSelectionEvent e) {
                editUserButton.setEnabled(true);
                removeUserButton.setEnabled(true);
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUserButtonClick();
            }
        });

        editUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = userTable.getSelectedRow();
                String userName = userTableRows[row][0];
                User user = userController.getUser(userName);
                editUserButtonClick(user);
            }
        });

        removeUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = userTableRows[userTable.getSelectedRow()][0];
                User user = userController.getUser(userName);
                removeUserButtonClick(user);
                //userTableModel.removeRow(userTable.getSelectedRow());
            }
        });

        jScrollPane4.setViewportView(userTable);

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane4)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap(284, Short.MAX_VALUE)
                                .addComponent(addUserButton)
                                .addGap(18, 18, 18)
                                .addComponent(editUserButton)
                                .addGap(18, 18, 18)
                                .addComponent(removeUserButton)
                                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(addUserButton)
                                        .addComponent(editUserButton)
                                        .addComponent(removeUserButton))
                                .addContainerGap())
        );

        jTabbedPane1.addTab("Сотрудники", jPanel4);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jTabbedPane1)
        );

        pack();
    }

    private void addUserButtonClick(){
        JDialog addUserForm = new AddOrEditUserForm(userController, null);
        addUserForm.setVisible(true);
        refreshUserTable();
    }

    private void editUserButtonClick(User user){
        JDialog editUserForm = new AddOrEditUserForm(userController, user);
        editUserForm.setVisible(true);
        refreshUserTable();
        refreshTaskTable();
    }

    private void removeUserButtonClick(User user){
        if(taskController.getAllTasksForUser(user).size() == 0) {
            DialogForm removeUser = new DialogForm("Удалить пользователя?",
                    "Вы действительно хотите удалить пользователя с именем: " + user.getName() +"?",
                    true);
            removeUser.setVisible(true);
            if(removeUser.getOk()){
                userController.removeUser(user);
                log.log(Level.INFO, "Delete user: " + user);
                refreshUserTable();
            }
        }else{
            JDialog error = new DialogForm("Ошибка!",
                    "Невозможно удалить пользователя " + user.getName()
                            + ", так как у него есть невыполненые задачи.");
            error.setVisible(true);
        }
    }

    private void addProjectButtonClick(){
        JDialog addProjectForm = new AddOrEditProjectForm(projectController, null);
        addProjectForm.setVisible(true);
        refreshProjectTable();
    }

    private void editProjectButtonClick(Project project){
        JDialog editProjectForm = new AddOrEditProjectForm(projectController, project);
        editProjectForm.setVisible(true);
        refreshProjectTable();
        refreshTaskTable();
    }

    private void removeProjectButtonClick(Project project){
        if(taskController.getAllTasksOnProject(project).size() == 0){
            DialogForm removeProject = new DialogForm("Удалить проект?",
                    "Вы действительно хотите удалить проект с именем: " + project.getName() + "?",
                    true);
            removeProject.setVisible(true);
            if(removeProject.getOk()){
                projectController.removeProject(project);
                log.log(Level.INFO, "Delete project: " + project);
                refreshProjectTable();
            }
        }else {
            JDialog error = new DialogForm("Ошибка!", "Невозможно удалить проект: " + project.getName()
                                            + ", так как в нем есть открытые задачи.");
            error.setVisible(true);
        }
    }

    private void addTaskButtonClick(){
        if (projectController.getAllProjects().size() < 1){
            JDialog error = new DialogForm("Информация",
                            "Сначала в систему необходимо добавить проекты.");
            error.setVisible(true);
            return;
        }
        if(userController.getAllUsers().size() < 1){
            JDialog error = new DialogForm("Информация",
                    "Сначала в систему необходимо добавить сотрудников.");
            error.setVisible(true);
            return;
        }
        JDialog addTaskForm = new AddOrEditTaskForm(taskController, projectController, userController, null);
        addTaskForm.setVisible(true);
        refreshTaskTable();
    }

    private void editTaskButtonClick(Task task){
        JDialog editTaskForm = new AddOrEditTaskForm(taskController, projectController, userController, task);
        editTaskForm.setVisible(true);
        refreshTaskTable();
    }

    private void removeTaskButtonClick(Task task){
        DialogForm removeTask = new DialogForm("Удалить задачу",
                "Вы действительно хотите удалить задачу с именем: " + task.getName() + "?",
                true);
        removeTask.setVisible(true);
        if(removeTask.getOk()){
            taskController.removeTask(task);
            log.log(Level.INFO, "Delete task: " + task);
            refreshTaskTable();
        }
    }

    private void refreshUserTable(){
        List<User> users = userController.getAllUsers();
        userTableRows = new String[users.size()][1];
        for (int i = 0; i < users.size(); i++){
            userTableRows[i][0] = users.get(i).getName();
        }
        userTable.setModel(new javax.swing.table.DefaultTableModel(userTableRows, new String [] {"Имя пользователя"}));
        editUserButton.setEnabled(false);
        removeUserButton.setEnabled(false);
        refreshUserComboBox();
        refreshTaskTable();
    }

    private void refreshProjectTable(){
        String[] projectTableColumns = new String[]{"Название", "Описание"};

        List<Project> projects = projectController.getAllProjects();
        projectTableRows = new String[projects.size()][projectTableColumns.length];
        for (int i = 0; i < projects.size(); i++){
            projectTableRows[i][0] = projects.get(i).getName();
            projectTableRows[i][1] = projects.get(i).getDescription();
        }
        projectTable.setModel(new javax.swing.table.DefaultTableModel(projectTableRows, projectTableColumns));
        editProjectButton.setEnabled(false);
        removeProjectButton.setEnabled(false);
        refreshProjectComboBox();
        refreshTaskTable();
    }

    private void refreshTaskTable(){
        final String[] taskTableColumns = new String[]{"Название", "Тип", "Приоритет", "Описание",
                "Проект", "Исполнитель", "Время создания"};

        boolean allProject = projectsComboBox.getSelectedItem().toString().equals("All");
        boolean allUsers = usersComboBox.getSelectedItem().toString().equals("All");
        List<Task> tasks;

        if(allProject && allUsers){
            tasks = taskController.getAllTasks();
        }else if(allProject){
            User user = userController.getUser(usersComboBox.getSelectedItem().toString());
            tasks = taskController.getAllTasksForUser(user);
        }else if (allUsers){
            Project project = projectController.getProject(projectsComboBox.getSelectedItem().toString());
            tasks = taskController.getAllTasksOnProject(project);
        }else {
            User user = userController.getUser(usersComboBox.getSelectedItem().toString());
            Project project = projectController.getProject(projectsComboBox.getSelectedItem().toString());
            tasks = taskController.getAllTaskOnProjectForUser(project, user);
        }


        taskTableRows = new String[tasks.size()][taskTableColumns.length];
        int b;
        for (int i = 0; i < tasks.size(); i++){
            b = 0;
            taskTableRows[i][b++] = tasks.get(i).getName();
            taskTableRows[i][b++] = tasks.get(i).getType();
            taskTableRows[i][b++] = tasks.get(i).getPriority();
            taskTableRows[i][b++] = tasks.get(i).getDescription();
            taskTableRows[i][b++] = tasks.get(i).getProject().getName();
            taskTableRows[i][b++] = tasks.get(i).getUser().getName();
            taskTableRows[i][b] = String.valueOf(tasks.get(i).getDate());
        }

        taskTable.setModel(new javax.swing.table.DefaultTableModel(taskTableRows, taskTableColumns));
        editTaskButton.setEnabled(false);
        removeTaskButton.setEnabled(false);
    }

    private void refreshProjectComboBox(){
        List<Project> projects = projectController.getAllProjects();
        String[] projectsNamesArray = new String[projects.size() + 1];
        projectsNamesArray[0] = "All";
        int i = 1;
        for (Project p : projects){
            projectsNamesArray[i] = p.getName();
            i++;
        }
        projectsComboBox.setModel(new DefaultComboBoxModel<>(projectsNamesArray));
    }

    private void refreshUserComboBox(){
        List<User> users = userController.getAllUsers();
        String[] usersNamesArray = new String[users.size() + 1];
        usersNamesArray[0] = "All";
        int i = 1;
        for (User u : users){
            usersNamesArray[i] = u.getName();
            i++;
        }
        usersComboBox.setModel(new DefaultComboBoxModel<>(usersNamesArray));
    }
}