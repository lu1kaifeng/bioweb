package edu.henu.bioweb.LMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMonitorFrame extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField textField_1;

    public UserMonitorFrame() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 501, 184);
        setTitle("�ڶ�ȡ�ļ�ʱʹ�ý�����");
        getContentPane().setLayout(null);

        JLabel label = new JLabel("�ļ���ַ");
        label.setBounds(10, 10, 70, 15);
        getContentPane().add(label);

        textField = new JTextField();
        textField.setBounds(90, 7, 300, 21);
        getContentPane().add(textField);
        textField.setColumns(10);

        JLabel label_1 = new JLabel("���Ƶ�ַ");
        label_1.setBounds(10, 40, 70, 15);
        getContentPane().add(label_1);

        textField_1 = new JTextField();
        textField_1.setBounds(90, 38, 300, 21);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);


        JButton button_2 = new JButton("��ʼ����");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_copyButton_actionPerformed(e);
            }
        });
        button_2.setBounds(182, 69, 93, 23);
        getContentPane().add(button_2);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserMonitorFrame frame = new UserMonitorFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void do_copyButton_actionPerformed(ActionEvent arg0) {
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        ProgressMonitorTest test = new ProgressMonitorTest();
        String path = textField.getText();
        String save = textField_1.getText();
        test.useProgressMonitor(this, path, save + path.substring(path.lastIndexOf("."), path.length()));

    }
}