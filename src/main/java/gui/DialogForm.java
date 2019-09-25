package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogForm extends JDialog {

    private JButton okButton;
    private JButton cancelButton;
    private JLabel textLabel;

    private boolean showCancelButton = false;
    private String title;
    private String text;

    private boolean ok;

    public DialogForm(String title, String text){
        this.title = title;
        this.text = text;
        ok = false;
        initComponents();
    }
    public DialogForm(String title, String text, boolean showCancelButton) {
        this.title = title;
        this.text = text;
        this.showCancelButton = showCancelButton;
        ok = false;
        initComponents();
    }

    public boolean getOk(){
        return ok;
    }

    private void initComponents() {

        textLabel = new JLabel();
        okButton = new JButton("Ок");
        cancelButton = new JButton("Нет");

        setLocation(400, 100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setTitle(title);

        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setText(text);

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ok = true;
                dispose();
            }
        });

        cancelButton.setVisible(showCancelButton);
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
                                .addContainerGap()
                                .addComponent(textLabel, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(okButton)
                                .addGap(18, 18, 18)
                                .addComponent(cancelButton)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(textLabel)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

}
