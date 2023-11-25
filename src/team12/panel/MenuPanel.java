package team12.panel;

import team12.SortingVisualizer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel {
    private static final String[] s = {"Bubble Sort", "Merge Sort", "Quick Sort", "Selection Sort"};
    private static final String[] s1 = {"Increase", "Decrease"};
    private static final String[] s2 = {"First", "Middle", "Last"};
    public static JButton startButton;
    public static JButton pauseButton;
    public static JButton nextStep;
    public static JButton endButton;
    public static JSlider speedSlider;
    public static JCheckBox randomSubmit;
    public static JCheckBox mixSubmit;
    public static JSpinner maxRangeRandom;
    public static JSpinner minRangeRandom;
    public static JSpinner numOfArray;
    public static JComboBox<String> chooseComboBox;
    public static JComboBox<String> chooseComboBox1;
    public static JLabel rangeLabel;
    public static JLabel rangeLabel1;
    public static JLabel numLabel;
    public static Color defaultColor = new Color(182, 212, 241);
    private static JComboBox<String> typeSorting;

    public MenuPanel() {
        setSize(new Dimension(100000, 70));
        setBackground(defaultColor);

        typeSorting = new JComboBox<String>(s);
        chooseComboBox = new JComboBox<String>(s1);
        chooseComboBox1 = new JComboBox<String>(s2);
        chooseComboBox1.setEnabled(false);
        speedSlider = new JSlider(0, 1000, 150);
        speedSlider.setMinorTickSpacing(50);
        speedSlider.setMajorTickSpacing(200);
        speedSlider.setPaintLabels(true);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintTrack(true);
        speedSlider.setBackground(defaultColor);

        JLabel speedLabel = new JLabel("Delay: " + speedSlider.getValue() + "ms");
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        nextStep = new JButton("Next");
        endButton = new JButton("End");
        startButton.setEnabled(false);
        pauseButton.setEnabled(false);
        nextStep.setEnabled(false);
        endButton.setEnabled(false);

        randomSubmit = new JCheckBox("Random Submit");
        randomSubmit.setBackground(defaultColor);
        mixSubmit = new JCheckBox("Mix Array");
        mixSubmit.setBackground(defaultColor);

        rangeLabel = new JLabel("Range: ");
        rangeLabel.setEnabled(false);
        SpinnerModel maxSpinnerModel = new SpinnerNumberModel(99, 0, 999, 1);
        maxRangeRandom = new JSpinner(maxSpinnerModel);
        maxRangeRandom.setPreferredSize(new Dimension(40, 20));
        maxRangeRandom.setEnabled(false);

        rangeLabel1 = new JLabel(" - ");
        rangeLabel1.setEnabled(false);
        SpinnerModel minSpinnerModel = new SpinnerNumberModel(0, 0, 99, 1);
        minRangeRandom = new JSpinner(minSpinnerModel);
        minRangeRandom.setPreferredSize(new Dimension(40, 20));
        minRangeRandom.setEnabled(false);

        numLabel = new JLabel("Numbers: ");
        numLabel.setEnabled(false);
        SpinnerModel spinnerModel1 = new SpinnerNumberModel(15, 2, SubmitPanel.max2, 1);
        numOfArray = new JSpinner(spinnerModel1);
        numOfArray.setPreferredSize(new Dimension(40, 20));
        numOfArray.setEnabled(false);

        typeSorting.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String str = (String) typeSorting.getSelectedItem();
                assert str != null;
                chooseComboBox1.setEnabled(str.equals("Quick Sort"));
            }
        });

        randomSubmit.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    SubmitPanel.submitTextField.setEditable(false);
                    SubmitPanel.randomSubmit = true;
                    maxRangeRandom.setEnabled(true);
                    minRangeRandom.setEnabled(true);
                    numOfArray.setEnabled(true);
                    rangeLabel.setEnabled(true);
                    rangeLabel1.setEnabled(true);
                    numLabel.setEnabled(true);
                    mixSubmit.setSelected(false);
                    mixSubmit.setEnabled(false);
                } else {
                    SubmitPanel.submitTextField.setEditable(true);
                    SubmitPanel.randomSubmit = false;
                    maxRangeRandom.setEnabled(false);
                    minRangeRandom.setEnabled(false);
                    rangeLabel.setEnabled(false);
                    rangeLabel1.setEnabled(false);
                    numLabel.setEnabled(false);
                    numOfArray.setEnabled(false);
                    mixSubmit.setEnabled(true);
                }
            }
        });

        mixSubmit.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                SubmitPanel.mixSubmit = e.getStateChange() == 1;
            }
        });

        maxRangeRandom.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SpinnerModel spinnerModel;
                int a = (int) maxRangeRandom.getValue();
                if (a < 10)
                    spinnerModel = new SpinnerNumberModel(15, 2, SubmitPanel.max1, 1);
                else if (a < 100)
                    spinnerModel = new SpinnerNumberModel(15, 2, SubmitPanel.max2, 1);
                else
                    spinnerModel = new SpinnerNumberModel(15, 2, SubmitPanel.max3, 1);
                numOfArray.setModel(spinnerModel);

                SpinnerModel spinnerModel1;
                int c = (int) maxRangeRandom.getValue();
                int b = (int) minRangeRandom.getValue();
                spinnerModel1 = new SpinnerNumberModel(b, 0, c, 1);
                minRangeRandom.setModel(spinnerModel1);
            }
        });

        minRangeRandom.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                SpinnerModel spinnerModel;
                int a = (int) maxRangeRandom.getValue();
                int b = (int) minRangeRandom.getValue();
                spinnerModel = new SpinnerNumberModel(a, b, 999, 1);
                maxRangeRandom.setModel(spinnerModel);
            }
        });

        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                speedLabel.setText("Delay: " + speedSlider.getValue() + "ms");
            }
        });

        nextStep.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (startButton.isEnabled()) {
                        startButton.setEnabled(false);
                        pauseButton.setEnabled(true);
                        endButton.setEnabled(true);
                        SubmitPanel.submitButton.setEnabled(false);
                        SubmitPanel.submitTextField.setEnabled(false);
                        String typeSort = (String) typeSorting.getSelectedItem();
                        SortingVisualizer.startSort(typeSort);
                    }
                    if (pauseButton.getText().equals("Continue"))
                        SortingVisualizer.conti();
                    else
                        pauseButton.setText("Continue");
                    SortingVisualizer.pause();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        nextStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startButton.isEnabled()) {
                    startButton.setEnabled(false);
                    pauseButton.setEnabled(true);
                    endButton.setEnabled(true);
                    SubmitPanel.submitButton.setEnabled(false);
                    SubmitPanel.submitTextField.setEnabled(false);
                    String typeSort = (String) typeSorting.getSelectedItem();
                    SortingVisualizer.startSort(typeSort);
                }
                if (pauseButton.getText().equals("Continue"))
                    SortingVisualizer.conti();
                else
                    pauseButton.setText("Continue");
                SortingVisualizer.pause();
            }
        });

        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SortingVisualizer.endNow();
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startButton.getText().equals("Start")) {
                    String typeSort = (String) typeSorting.getSelectedItem();
                    SortingVisualizer.startSort(typeSort);
                    pauseButton.setEnabled(true);
                    endButton.setEnabled(true);
                    SubmitPanel.submitButton.setEnabled(false);
                    SubmitPanel.submitTextField.setEnabled(false);
                    startButton.setEnabled(false);
                } else {
                    SubmitPanel.submit();
                    String typeSort = (String) typeSorting.getSelectedItem();
                    SortingVisualizer.startSort(typeSort);
                    pauseButton.setEnabled(true);
                    endButton.setEnabled(true);
                    nextStep.setEnabled(true);
                    SubmitPanel.submitButton.setEnabled(false);
                    SubmitPanel.submitTextField.setEnabled(false);
                    startButton.setEnabled(false);
                }
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pauseButton.getText().equals("Pause")) {
                    SortingVisualizer.pause();
                    pauseButton.setText("Continue");
                } else {
                    SortingVisualizer.conti();
                    pauseButton.setText("Pause");
                }
            }
        });

        add(mixSubmit);
        add(randomSubmit);
        add(rangeLabel);
        add(minRangeRandom);
        add(rangeLabel1);
        add(maxRangeRandom);
        add(numLabel);
        add(numOfArray);
        add(speedLabel);
        add(speedSlider);
        add(pauseButton);
        add(nextStep);
        add(endButton);
        add(startButton);
        add(typeSorting);
        add(chooseComboBox);
        add(chooseComboBox1);

        randomSubmit.setVisible(false);
        maxRangeRandom.setVisible(false);
        minRangeRandom.setVisible(false);
        numLabel.setVisible(false);
        rangeLabel.setVisible(false);
        rangeLabel1.setVisible(false);
        numOfArray.setVisible(false);
        mixSubmit.setVisible(false);
    }
}