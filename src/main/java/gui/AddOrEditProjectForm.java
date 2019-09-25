package gui;

import dataModels.Project;
import dataModels.ProjectController;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddOrEditProjectForm extends JDialog {
    private static final Logger log = LogManager.getLogger(AddOrEditProjectForm.class.getName());

    private JButton okButton;
    private JButton cancelButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField nameTextField;
    private JTextField descriptionTextField;

    private final ProjectController projectController;
    private Project project;

    public AddOrEditProjectForm(ProjectController projectController, Project project) {
        this.projectController = projectController;
        this.project = project;
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new JLabel("Название проекта");
        nameTextField = new JTextField();
        jLabel2 = new JLabel("Описание проекта");
        descriptionTextField = new JTextField();
        okButton = new JButton();
        cancelButton = new JButton("Отменить");

        setLocation(400, 100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        if(project == null) {
            setTitle("Добавить проект");
            okButton.setText("Добавить");
        }else{
            setTitle("Редактировать проект");
            okButton.setText("Применить");
            nameTextField.setText(project.getName());
            descriptionTextField.setText(project.getDescription());
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

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(descriptionTextField, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(92, 92, 92)
                                                .addComponent(okButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(cancelButton)))
                                .addContainerGap(48, Short.MAX_VALUE))
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
                                        .addComponent(descriptionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void okButtonClick(){
        String projectName = nameTextField.getText();
        if(projectName.equals("")){
            DialogForm error = new DialogForm("Ошибка!", "Поле имя проекта не может быть пустым.");
            error.setVisible(true);
            return;
        }
        String projectDescription = descriptionTextField.getText();
        boolean isName = projectController.getProject(projectName) != null;
        if(project == null){
            if(isName){
                DialogForm error = new DialogForm("Ошибка!", "Это имя проекта уже занято.");
                error.setVisible(true);
                return;
            }else {
                project = new Project(projectName, projectDescription);
                projectController.addProject(project);
                log.log(Level.INFO, "Add Project: " + project);
                dispose();
            }
        }else{
            if(projectName.equals(project.getName())){
                project.setDescription(projectDescription);
                projectController.updateProject(project);
                log.log(Level.INFO, "Update Project: " + project);
                dispose();
                return;
            }else if(isName){
                DialogForm error = new DialogForm("Ошибка!", "Вы поменяли имя проекта на уже занятое.");
                error.setVisible(true);
                return;
            }else {
                project.setName(projectName);
                project.setDescription(projectDescription);
                projectController.updateProject(project);
                log.log(Level.INFO, "Update Project: " + project);
                dispose();
            }
        }
    }

}
