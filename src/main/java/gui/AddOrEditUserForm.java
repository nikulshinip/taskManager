package gui;

import dataModels.User;
import dataModels.UserController;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddOrEditUserForm extends JDialog{
    private static final Logger log = LogManager.getLogger(AddOrEditUserForm.class.getName());

    private JButton okButton;
    private JButton cancelButton;
    private JLabel jLabel1;
    private JTextField nameTextField;

    private final UserController userController;
    private User user;

    public AddOrEditUserForm(UserController userController, User user) {
        this.userController = userController;
        this.user = user;
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new JLabel("Имя пользователя:");
        nameTextField = new JTextField();
        okButton = new JButton();
        cancelButton = new JButton("Отмена");

        setLocation(400, 100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        if(user == null) {
            setTitle("Добавить сотрудника");
            okButton.setText("Добавить");
        }else{
            setTitle("Редактировать сотрудника");
            okButton.setText("Применить");
            nameTextField.setText(user.getName());
        }

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addOrUpdateUser();
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
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(108, 108, 108)
                                                .addComponent(okButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(cancelButton)))
                                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }

    private void addOrUpdateUser(){
        String userName = nameTextField.getText();
        if(userName.equals("")){
            DialogForm error = new DialogForm("Ошибка!", "Поле имя не может быть пустым.");
            error.setVisible(true);
            return;
        }
        if(user == null){ //addUser
            if(userController.getUser(userName) == null){
                user = new User(userName);
                userController.addUser(user);
                log.log(Level.INFO, "Add User: " + user);
                dispose();
            }else{
                DialogForm error = new DialogForm("Ошибка!", "Пользователь с таким именем уже существует.");
                error.setVisible(true);
            }
        }else{  //updateUser
            if(userName.equals(user.getName())){
                dispose();
                return;
            }
            if(userController.getUser(userName) == null){
                user.setName(userName);
                userController.updateUser(user);
                log.log(Level.INFO, "Update User: " + user);
                dispose();
            }else{
                DialogForm error = new DialogForm("Ошибка!", "Это имя пользователя уже занято другим пользователем");
                error.setVisible(true);
            }

        }
    }
}
