package team12.panel;

import team12.BoxElement;
import team12.VisualizeFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SortingPanel extends JPanel {

    public static int compareNumber = 0;
    public static JLabel compareLabel;
    public static Color defaultColor = new Color(255, 255, 255);
    public static Color defaultArrayColor;
    public static Color usertArrayColor;
    public static double widthFrame;
    public static VisualizeFrame frame;
    public BoxElement[] box;
    public JLabel successLabel;

    public SortingPanel(VisualizeFrame frame) {
        SortingPanel.frame = frame;
        JLabel welcome = new JLabel("WELCOME TO SORTING VISUALIZER");
        welcome.setBounds((int) ((widthFrame - 350) / 2 - 10), 250, 350, 50);
        welcome.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
        add(welcome);
        setBackground(defaultColor);
        setLayout(null);
    }

    public void prePaint(ArrayList<Integer> source) {
        removeAll();
        repaint();
        widthFrame = (int) frame.getBounds().getWidth();

        box = new BoxElement[source.size()];
        int maxChar = 1;
        int size;
        setLayout(new FlowLayout());

        for (int i = 0; i < source.size(); i++) {
            String numStr = Integer.toString(source.get(i));
            if (maxChar < numStr.length())
                maxChar = numStr.length();
            box[i] = new BoxElement();
            box[i].setText(numStr);

            add(box[i]);
        }

        switch (maxChar) {
            case 1:
                size = 40;
                break;
            case 2:
                size = 47;
                break;
            case 3:
                size = 53;
                break;
            default:
                size = 53;
        }

        for (int i = 0; i < source.size(); i++) {
            box[i].setPreferredSize(new Dimension(size, size));
        }
        validate();
        setLayout(null);
        for (int i = 0; i < source.size(); i++) {
            box[i].setLocation(box[i].getX(), box[i].getY() + 250);
            box[i].setBackground(usertArrayColor);
        }
        successLabel = new JLabel("Sorting is successfull");
        successLabel.setBounds((int) ((widthFrame - 130) / 2), 330, 130, 50);
        successLabel.setVisible(false);
        compareNumber = 0;
        compareLabel = new JLabel("Comparison: " + compareNumber);
        compareLabel.setBounds((int) ((widthFrame - 100) / 2), 90, 250, 30);
        add(compareLabel);
        add(successLabel);
    }

    public void dislayError(String errorString) {
        removeAll();
        repaint();
        JLabel error = new JLabel(errorString);
        error.setBounds((int) ((widthFrame - 300) / 2), 260, 300, 50);
        add(error);
    }
}
