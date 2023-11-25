package team12.panel;

import team12.SortingVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

public class SubmitPanel extends JPanel {

    public static SortingPanel sortingPanel;
    public static JButton submitButton;
    public static JTextField submitTextField;
    public static ArrayList<Integer> sortingSource = new ArrayList<Integer>();
    public static boolean randomSubmit = false;
    public static boolean mixSubmit = false;
    public static JComboBox<String> recentComboBox;
    public static ArrayList<String> recentSubmit = new ArrayList<String>();
    public static boolean selectRecent = false;
    public static JLabel recentLabel;
    public static JLabel submitLabel;
    public static Color defaultColor = new Color(182, 212, 241);
    public static int max1;
    public static int max2;
    public static int max3;

    public SubmitPanel(SortingPanel sortingPanel) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
        setPreferredSize(new Dimension(500, 85));
        SubmitPanel.sortingPanel = sortingPanel;
        max1 = (int) (Math.floor((SortingPanel.widthFrame - 5) / 45) - 1);
        max2 = (int) (Math.floor((SortingPanel.widthFrame - 5) / 52) - 1);
        max3 = (int) (Math.floor((SortingPanel.widthFrame - 5) / 58) - 1);
        recentComboBox = new JComboBox<String>();
        recentComboBox.setMaximumSize(new Dimension(200, 50));
        submitLabel = new JLabel("Input array: ");
        submitLabel.setPreferredSize(new Dimension((int) ((SortingPanel.widthFrame - 410) / 2), 50));
        submitLabel.setHorizontalAlignment(JLabel.RIGHT);
        recentLabel = new JLabel("Recent Submit: ");
        submitTextField = new JTextField();
        submitTextField.setPreferredSize(new Dimension(300, 40));

        submitButton = new JButton("Submit");
        submitButton.setLocation(200, 50);
        SortingPanel.defaultArrayColor = SubmitPanel.submitButton.getBackground();
        SortingPanel.usertArrayColor = SubmitPanel.submitButton.getBackground();

        add(submitLabel);
        add(submitTextField);
        add(submitButton);
        add(recentLabel);
        add(recentComboBox);
        recentLabel.setVisible(false);
        recentComboBox.setVisible(false);
        setBackground(defaultColor);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submit();
            }
        });

        submitButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SubmitPanel.submit();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        submitTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SubmitPanel.submit();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        recentComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitTextField.setText((String) recentComboBox.getSelectedItem());
                selectRecent = true;
            }
        });
    }

    public static void submit() {
        if (SortingVisualizer.isSorting) return;
        int maxChar = 1;
        sortingSource.removeAll(sortingSource);

        if (randomSubmit && !selectRecent) {
            int numOfArray = (int) MenuPanel.numOfArray.getValue();
            StringBuffer inputArray = new StringBuffer();
            int max = (int) MenuPanel.maxRangeRandom.getValue();
            int min = (int) MenuPanel.minRangeRandom.getValue();
            for (int i = 0; i < numOfArray; i++)
                inputArray.append((min + (int) Math.round(Math.random() * (max - min))) + " ");
            submitTextField.setText(inputArray.toString());
        }

        String stringList = submitTextField.getText();
        for (int i = 0; i < stringList.length(); i++) {
            char c = stringList.charAt(i);
            if ((c > '9' || c < '0') && c != 32)
                stringList = stringList.replace(c, ' ');
        }
        String[] splitString = stringList.split(" ");
        for (String numString : splitString) {
            if (numString.length() > maxChar)
                maxChar = numString.length();
            if (!numString.equals(""))
                sortingSource.add(Integer.parseInt(numString));
        }
        if (maxChar > 3) {
            sortingPanel.dislayError("Giá trị các số phải nằm trong khoảng từ 0 đến 999");
        } else if (sortingSource.size() > max1 && maxChar == 1) {
            sortingPanel.dislayError("Tối đa " + max1 + " chữ số khi số lớn nhất của dãy có 1 chữ số");
        } else if (sortingSource.size() > max2 && maxChar == 2) {
            sortingPanel.dislayError("Tối đa " + max2 + " chữ số khi số lớn nhất của dãy có 2 chữ số");
        } else if (sortingSource.size() > max3 && maxChar == 3) {
            sortingPanel.dislayError("Tối đa " + max3 + " chữ số khi số lớn nhất của dãy có 3 chữ số");
        } else if (sortingSource.size() != 0) {
            if (mixSubmit) {
                Collections.shuffle(sortingSource);
            }
            sortingPanel.prePaint(sortingSource);

            int index = recentSubmit.indexOf(sortingSource.toString());
            if (index > 0) {
                String tmp = recentSubmit.get(index);
                for (int i = index; i > 0; i--) {
                    recentSubmit.set(i, recentSubmit.get(i - 1));
                }
                recentSubmit.set(0, tmp);
            } else if (index != 0) {
                if (recentSubmit.size() < 10) {
                    recentSubmit.add(0, sortingSource.toString());
                } else {
                    recentSubmit.remove(recentSubmit.size() - 1);
                    recentSubmit.add(0, sortingSource.toString());
                }
            }
            recentComboBox.removeAllItems();
            for (int i = 0; i < recentSubmit.size(); i++) {
                recentComboBox.addItem(recentSubmit.get(i));
            }
            selectRecent = false;
            MenuPanel.startButton.setText("Start");
            MenuPanel.startButton.setEnabled(true);
            MenuPanel.nextStep.setEnabled(true);
            if (MenuPanel.pauseButton.getText().equals("Continue"))
                MenuPanel.pauseButton.setText("Pause");
        }
    }
}
