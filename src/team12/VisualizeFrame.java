package team12;

import team12.panel.MenuPanel;
import team12.panel.SortingPanel;
import team12.panel.SubmitPanel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class VisualizeFrame extends JFrame {
    public MenuPanel menuPanel;
    public SortingPanel sortingPanel;
    public SubmitPanel submitPanel;

    public VisualizeFrame() {
        super("Sorting Visualize");
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(1100, 700));
        SortingPanel.widthFrame = Toolkit.getDefaultToolkit().getScreenSize().getWidth() + 14;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sortingPanel = new SortingPanel(this);
        submitPanel = new SubmitPanel(sortingPanel);
        menuPanel = new MenuPanel();

        final JMenuBar menuBar = new JMenuBar();
        JMenu extendMenu = new JMenu("Extend");
        JMenu editMenu = new JMenu("Edit");
        JMenu aboutMenu = new JMenu("About");

        JCheckBoxMenuItem recentMenuItem = new JCheckBoxMenuItem("Recent submit", false);
        recentMenuItem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (recentMenuItem.getState()) {
                    SubmitPanel.recentComboBox.setVisible(true);
                    SubmitPanel.recentLabel.setVisible(true);
                    if (SortingPanel.widthFrame <= 950)
                        SubmitPanel.submitLabel.setPreferredSize(new Dimension(10, 50));
                    else
                        SubmitPanel.submitLabel
                                .setPreferredSize(new Dimension((int) ((SortingPanel.widthFrame - 950) / 2), 50));
                } else {
                    SubmitPanel.recentComboBox.setVisible(false);
                    SubmitPanel.recentLabel.setVisible(false);
                    SubmitPanel.submitLabel
                            .setPreferredSize(new Dimension((int) ((SortingPanel.widthFrame - 410) / 2), 50));
                }
            }
        });
        JCheckBoxMenuItem randomMenuItem = new JCheckBoxMenuItem("Random submit", false);
        randomMenuItem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (randomMenuItem.getState()) {
                    MenuPanel.randomSubmit.setVisible(true);
                    MenuPanel.maxRangeRandom.setVisible(true);
                    MenuPanel.minRangeRandom.setVisible(true);
                    MenuPanel.numLabel.setVisible(true);
                    MenuPanel.rangeLabel.setVisible(true);
                    MenuPanel.rangeLabel1.setVisible(true);
                    MenuPanel.numOfArray.setVisible(true);
                } else {
                    MenuPanel.randomSubmit.setVisible(false);
                    MenuPanel.maxRangeRandom.setVisible(false);
                    MenuPanel.minRangeRandom.setVisible(false);
                    MenuPanel.numLabel.setVisible(false);
                    MenuPanel.rangeLabel.setVisible(false);
                    MenuPanel.rangeLabel1.setVisible(false);
                    MenuPanel.numOfArray.setVisible(false);
                    MenuPanel.randomSubmit.setSelected(false);
                }
            }
        });
        JCheckBoxMenuItem mixMenuItem = new JCheckBoxMenuItem("Mix Array", false);
        mixMenuItem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (mixMenuItem.getState()) {
                    MenuPanel.mixSubmit.setVisible(true);
                } else {
                    MenuPanel.mixSubmit.setVisible(false);
                    MenuPanel.mixSubmit.setSelected(false);
                }
            }
        });
        JMenuItem setMenuItem = new JMenuItem("Set all");
        JMenuItem unsetMenuItem = new JMenuItem("Unset all");
        JMenu colorMenuItem = new JMenu("Set color");
        JMenuItem resetMenuItem = new JMenuItem("Reset default");
        JMenuItem submitMenuItem = new JMenuItem("Submit panel");
        JMenuItem controlMenuItem = new JMenuItem("Control panel");
        JMenuItem sortingMenuItem = new JMenuItem("Sorting panel");
        JMenuItem arrayMenuItem = new JMenuItem("Array box");
        JMenuItem helpMenuItem = new JMenuItem("Help");
        JMenuItem infMenuItem = new JMenuItem("Information");

        setMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!recentMenuItem.getState())
                    recentMenuItem.setState(true);
                if (!mixMenuItem.getState())
                    mixMenuItem.setState(true);
                if (!randomMenuItem.getState())
                    randomMenuItem.setState(true);
            }
        });
        unsetMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (recentMenuItem.getState())
                    recentMenuItem.setState(false);
                if (mixMenuItem.getState())
                    mixMenuItem.setState(false);
                if (randomMenuItem.getState())
                    randomMenuItem.setState(false);
            }
        });
        resetMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanel.setBackground(MenuPanel.defaultColor);
                sortingPanel.setBackground(SortingPanel.defaultColor);
                MenuPanel.speedSlider.setBackground(MenuPanel.defaultColor);
                MenuPanel.randomSubmit.setBackground(MenuPanel.defaultColor);
                MenuPanel.mixSubmit.setBackground(MenuPanel.defaultColor);
                submitPanel.setBackground(SubmitPanel.defaultColor);
                SortingPanel.usertArrayColor = SortingPanel.defaultArrayColor;
            }
        });
        submitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(sortingPanel, "Select a color", SubmitPanel.defaultColor);
                submitPanel.setBackground(color);
            }
        });
        controlMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(sortingPanel, "Select a color", MenuPanel.defaultColor);
                menuPanel.setBackground(color);
                MenuPanel.speedSlider.setBackground(color);
                MenuPanel.randomSubmit.setBackground(color);
                MenuPanel.mixSubmit.setBackground(color);
            }
        });
        sortingMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(sortingPanel, "Select a color", SortingPanel.defaultColor);
                sortingPanel.setBackground(color);
            }
        });
        arrayMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SortingPanel.usertArrayColor = JColorChooser.showDialog(sortingPanel, "Select a color", SortingPanel.defaultArrayColor);
            }
        });
        infMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Information");
                frame.setBounds(550, 200, 250, 200);
                JTextArea inf = new JTextArea(
                        "  BTL học phần LTHDT (IT3080) kì 20212\n  Thành viên nhóm 12:\n  Nguyễn Đình Đạt 20204722\n  Nguyễn Xuân Kiên 20204573\n  Trần Việt Hùng 20204752\n  Trần Anh Tuấn 20204616");
                inf.setEditable(false);
                frame.add(inf);
                frame.setVisible(true);
            }
        });
        helpMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Help");
                frame.setBounds(550, 200, 500, 200);
                JTextArea inf = new JTextArea(
                        "   + Nhập các số trong khoảng 0-999 cách nhau bởi ít nhất một dấu cách\n   + Nhập tối đa 33 chữ số khi số lớn nhất của dãy có 1 chữ số\n   + Nhập tối đa 28 chữ số khi số lớn nhất của dãy có 2 chữ số\n   + Nhập tối đa 25 chữ số khi số lớn nhất của dãy có 3 chữ số\n   - Các chỉ số trên có thể thay đổi tùy thuộc vào kích thước màn hình \n   và độ thu phóng của cửa sổ");
                inf.setEditable(false);
                frame.add(inf);
                frame.setVisible(true);
            }
        });
        colorMenuItem.add(submitMenuItem);
        colorMenuItem.add(sortingMenuItem);
        colorMenuItem.add(controlMenuItem);
        colorMenuItem.add(arrayMenuItem);
        extendMenu.add(recentMenuItem);
        extendMenu.add(randomMenuItem);
        extendMenu.add(mixMenuItem);
        extendMenu.addSeparator();
        extendMenu.add(setMenuItem);
        extendMenu.add(unsetMenuItem);
        editMenu.add(colorMenuItem);
        editMenu.add(resetMenuItem);
        aboutMenu.add(helpMenuItem);
        aboutMenu.add(infMenuItem);
        menuBar.add(extendMenu);
        menuBar.add(editMenu);
        menuBar.add(aboutMenu);

        Border raisedBevel = BorderFactory.createRaisedBevelBorder();
        Border loweredBevel = BorderFactory.createLoweredBevelBorder();
        Border compound = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
        Border submitBorder = BorderFactory.createTitledBorder(compound, "Submit panel", TitledBorder.LEADING,
                TitledBorder.DEFAULT_POSITION);
        submitPanel.setBorder(submitBorder);

        Border menuBorder = BorderFactory.createTitledBorder(compound, "Control panel", TitledBorder.LEADING,
                TitledBorder.DEFAULT_POSITION);
        menuPanel.setBorder(menuBorder);

        Border sortingBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.red, Color.green);

        sortingPanel.setBorder(BorderFactory.createCompoundBorder(compound, sortingBorder));

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Double tmp = SortingPanel.widthFrame;
                SortingPanel.widthFrame = (int) SortingPanel.frame.getBounds().getWidth();
                SubmitPanel.max1 = (int) (Math.floor((SortingPanel.widthFrame - 5) / 45) - 1);
                SubmitPanel.max2 = (int) (Math.floor((SortingPanel.widthFrame - 5) / 52) - 1);
                SubmitPanel.max3 = (int) (Math.floor((SortingPanel.widthFrame - 5) / 58) - 1);
                submitPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));
                if (SubmitPanel.recentComboBox.isVisible()) {
                    if (SortingPanel.widthFrame <= 950)
                        SubmitPanel.submitLabel.setPreferredSize(new Dimension(10, 50));
                    else
                        SubmitPanel.submitLabel
                                .setPreferredSize(new Dimension((int) ((SortingPanel.widthFrame - 950) / 2), 50));
                } else
                    SubmitPanel.submitLabel
                            .setPreferredSize(new Dimension((int) ((SortingPanel.widthFrame - 410) / 2), 50));
                int a = (int) MenuPanel.maxRangeRandom.getValue();
                SpinnerNumberModel spinnerModel;
                if (a < 10)
                    spinnerModel = new SpinnerNumberModel(15, 2, SubmitPanel.max1, 1);
                else if (a < 100)
                    spinnerModel = new SpinnerNumberModel(15, 2, SubmitPanel.max2, 1);
                else
                    spinnerModel = new SpinnerNumberModel(15, 2, SubmitPanel.max3, 1);
                if (tmp != SortingPanel.widthFrame)
                    MenuPanel.numOfArray.setModel(spinnerModel);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
        setJMenuBar(menuBar);
        add(menuPanel, BorderLayout.SOUTH);
        add(sortingPanel, BorderLayout.CENTER);
        add(submitPanel, BorderLayout.NORTH);
        setVisible(true);
    }
}
