package edu.henu.bioweb.LMS;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CopyFile extends JFrame implements ActionListener {

    private JProgressBar jpb = new JProgressBar();
    private JButton jbtCopy = new JButton("copy");
    private JTextField jtfFrom = new JTextField();
    private JTextField jtfTo = new JTextField();

    public CopyFile() {
        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new BorderLayout());
        jPanel2.setBorder(new TitledBorder("From"));
        jPanel2.add(jtfFrom, BorderLayout.CENTER);

        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout(new BorderLayout());
        jPanel3.setBorder(new TitledBorder("To"));
        jPanel3.add(jtfTo, BorderLayout.CENTER);

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(2, 1));
        jPanel1.add(jPanel2);
        jPanel1.add(jPanel3);

        JPanel jPanel4 = new JPanel();
        jPanel4.add(jbtCopy);

        this.getContentPane().add(jpb, BorderLayout.NORTH);
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
        this.getContentPane().add(jPanel4, BorderLayout.SOUTH);
        jpb.setStringPainted(true);
        jbtCopy.addActionListener(this);
    }

    public static void main(String[] args) {
        CopyFile frame = new CopyFile();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("copyfile");
        frame.setSize(400, 180);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0) {
        new CopyFileThread().start();
    }

    class CopyFileThread extends Thread {
        private int currentValue;

        public void run() {
            BufferedInputStream in = null;
            BufferedOutputStream out = null;
            try {
                File inFile = new File(jtfFrom.getText().trim());
                in = new BufferedInputStream(new FileInputStream(inFile));
                File outFile = new File(jtfTo.getText().trim());
                out = new BufferedOutputStream(new FileOutputStream(outFile));
                long totalByes = in.available();
                jpb.setValue(0);
                jpb.setMaximum(100);
                int r;
                long bytesRead = 0;
                byte[] b = new byte[10];
                while ((r = in.read(b, 0, b.length)) != -1) {
                    out.write(b, 0, r);
                    bytesRead += r;
                    currentValue = (int) (bytesRead * 100 / totalByes);
                    jpb.setValue(currentValue);
                }
            } catch (Exception e) {

            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                } catch (Exception e) {

                }
            }
        }
    }
}