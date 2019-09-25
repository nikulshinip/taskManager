package gui;

import dataModels.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddOrEditTaskForm extends JDialog {
    private static final Logger log = LogManager.getLogger(AddOrEditTaskForm.class.getName());

    private JButton okButton;
    private JButton cancelButton;
    private JComboBox<String> projectsComboBox;
    private JComboBox<String> usersComboBox;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JTextField nameTextField;
    private JTextField typeTextField;
    private JTextField priorityTextField;
    private JTextField descriptionTextField;

    private final TaskController taskController;
    private final ProjectController projectController;
    private final UserController userController;
    private Task task;

    public AddOrEditTaskForm(TaskController taskController, ProjectController projectController,
                             UserController userController, Task task) {
        this.taskController = taskController;
        this.projectController = projectController;
        this.userController = userController;
        this.task = task;
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new JLabel("Наименование:");
        nameTextField = new JTextField();
        jLabel2 = new JLabel("Тип:");
        typeTextField = new JTextField();
        jLabel3 = new JLabel("Приоритет:");
        priorityTextField = new JTextField();
        jLabel4 = new JLabel("Описание:");
        descriptionTextField = new JTextField();
        jLabel5 = new JLabel("Проект:");
        projectsComboBox = new JComboBox<>();
        usersComboBox = new JComboBox<>();
        jLabel6 = new JLabel("Исполнитель:");
        okButton = new JButton();
        cancelButton = new JButton("Отменить");

        setLocation(400, 100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        if(task == null){
            setTitle("Добавить задачу");
            okButton.setText("Добавить");
        }else {
            setTitle("Редактировать задачу");
            okButton.setText("Применить");
            nameTextField.setText(task.getName());
            typeTextField.setText(task.getType());
            priorityTextField.setText(task.getPriority());
            descriptionTextField.setText(task.getDescription());
        }

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okButtonClick();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        List<Project> projects = projectController.getAllProjects();
        String[] projectsNamesArray = new String[projects.size()];
        for(int i = 0; i < projects.size(); i++){
            projectsNamesArray[i] = projects.get(i).getName();
        }
        projectsComboBox.setModel(new DefaultComboBoxModel<>(projectsNamesArray));

        List<User> users = userController.getAllUsers();
        String[] usersNamesArray = new String[users.size()];
        for (int i=0; i < users.size(); i++){
            usersNamesArray[i] = users.get(i).getName();
        }
        usersComboBox.setModel(new DefaultComboBoxModel<>(usersNamesArray));

        if(task != null) {
            projectsComboBox.setSelectedItem(task.getProject().getName());
            usersComboBox.setSelectedItem(task.getUser().getName());
        }

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel5, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(jLabel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(projectsComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(usersComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(priorityTextField)
                                                .addComponent(descriptionTextField))
                                        .addComponent(nameTextField)
                                        .addComponent(typeTextField))
                                .addGap(59, 59, 59))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(okButton)
                                .addGap(18, 18, 18)
                                .addComponent(cancelButton)
                                .addContainerGap(138, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(typeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(priorityTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(descriptionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(projectsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(usersComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void okButtonClick(){
        String name = nameTextField.getText();
        if(name.equals("")){
            DialogForm error = new DialogForm("Ошибка!", "Поле наименование задачи не может быть пустым.");
            error.setVisible(true);
            return;
        }
        String type = typeTextField.getText();
        String priority = priorityTextField.getText();
        String description = descriptionTextField.getText();
        String projectName = projectsComboBox.getSelectedItem().toString();
        String userName = usersComboBox.getSelectedItem().toString();
        boolean isName = taskController.getTask(name) != null;

        if(task == null){
            if(isName){
                DialogForm error = new DialogForm("Ошибка!", "Задача с таким именем уже существует.");
                error.setVisible(true);
                return;
            }else {
                task = new Task(name, type, priority, description,
                                    projectController.getProject(projectName),
                                    userController.getUser(userName));
                taskController.addTask(task);
                log.log(Level.INFO, "Add Task: " + task);
                dispose();
            }
        }else {
            if(name.equals(task.getName())){
                task.setType(type);
                task.setPriority(priority);
                task.setDescription(description);
                task.setProject(projectController.getProject(projectName));
                task.setUser(userController.getUser(userName));
                task.setDate();
                taskController.updateTask(task);
                log.log(Level.INFO, "Update Task: " + task);
                dispose();
            }else if(isName){
                DialogForm error = new DialogForm("Ошибка!", "Вы поменяли имя задачи на уже занятое другой задачей.");
                error.setVisible(true);
                return;
            }else{
                task.setName(name);
                task.setType(type);
                task.setPriority(priority);
                task.setDescription(description);
                task.setProject(projectController.getProject(projectName));
                task.setUser(userController.getUser(userName));
                task.setDate();
                taskController.updateTask(task);
                log.log(Level.INFO, "Updata Task: " + task);
                dispose();
            }
        }

    }
}