package team12.sorting;

import team12.BoxElement;
import team12.SortingVisualizer;
import team12.VisualizeFrame;
import team12.panel.MenuPanel;
import team12.panel.SortingPanel;

import java.awt.*;

public class BubbleSort implements Runnable {
    public static VisualizeFrame frame;

    public BubbleSort(VisualizeFrame frame) {
        BubbleSort.frame = frame;
    }

    private static void incBubbleSort() {

        BoxElement[] box = frame.sortingPanel.box;
        boolean flag;
        Color startColor = box[0].getBackground();
        Color normal = Color.orange;
        for (int i = 0; i < box.length; i++) {
            box[i].setBackground(normal);
        }
        SortingVisualizer.checkPause(SortingVisualizer.pause);
        SortingVisualizer.delay(SortingVisualizer.getDelay());
        for (int i = 0; i < box.length - 1; i++) {
            flag = false;
            for (int j = 0; j < box.length - i - 1; j++) {

                int a = Integer.parseInt(box[j].getText());
                int b = Integer.parseInt(box[j + 1].getText());

                box[j].setBackground(Color.yellow);
                box[j + 1].setBackground(Color.yellow);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                if (a > b) {

                    SortingPanel.compareNumber++;
                    box[j].setBackground(Color.green);
                    box[j + 1].setBackground(Color.red);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());

                    BoxElement.shift(box[j], box[j + 1]);
                    BoxElement tmp = box[j];
                    box[j] = box[j + 1];
                    box[j + 1] = tmp;
                    flag = true;

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());

                } else {

                    SortingPanel.compareNumber++;
                    box[j].setBackground(Color.red);
                    box[j + 1].setBackground(Color.green);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());

                }

                box[j].setBackground(normal);
                box[j + 1].setBackground(normal);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

            }
            if (!flag) {
                for (int k = 0; k < box.length - i; k++)
                    box[k].setBackground(Color.gray);
                break;
            } else
                box[box.length - i - 1].setBackground(Color.gray);
        }
        box[0].setBackground(Color.gray);
        SortingVisualizer.checkPause(SortingVisualizer.pause);
        SortingVisualizer.delay(SortingVisualizer.getDelay());
        for (int i = 0; i < box.length; i++) {
            box[i].setBackground(startColor);
        }
    }

    private static void decBubbleSort() {
        BoxElement[] box = frame.sortingPanel.box;
        boolean flag;
        Color startColor = box[0].getBackground();
        Color normal = Color.orange;
        for (int i = 0; i < box.length; i++) {
            box[i].setBackground(normal);
        }
        SortingVisualizer.checkPause(SortingVisualizer.pause);
        SortingVisualizer.delay(SortingVisualizer.getDelay());
        for (int i = 0; i < box.length - 1; i++) {
            flag = false;
            for (int j = 0; j < box.length - i - 1; j++) {
                int a = Integer.parseInt(box[j].getText());
                int b = Integer.parseInt(box[j + 1].getText());

                box[j].setBackground(Color.yellow);
                box[j + 1].setBackground(Color.yellow);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                if (a < b) {

                    SortingPanel.compareNumber++;
                    box[j].setBackground(Color.red);
                    box[j + 1].setBackground(Color.green);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());

                    BoxElement.shift(box[j], box[j + 1]);
                    BoxElement tmp = box[j];
                    box[j] = box[j + 1];
                    box[j + 1] = tmp;
                    flag = true;

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());

                } else {

                    SortingPanel.compareNumber++;
                    box[j].setBackground(Color.green);
                    box[j + 1].setBackground(Color.red);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());
                }

                box[j].setBackground(normal);
                box[j + 1].setBackground(normal);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

            }
            if (!flag) {
                for (int k = 0; k < box.length - i; k++)
                    box[k].setBackground(Color.gray);
                break;
            } else
                box[box.length - i - 1].setBackground(Color.gray);
            SortingVisualizer.checkPause(SortingVisualizer.pause);
            SortingVisualizer.delay(SortingVisualizer.getDelay());
        }
        box[0].setBackground(Color.gray);
        SortingVisualizer.checkPause(SortingVisualizer.pause);
        SortingVisualizer.delay(SortingVisualizer.getDelay());
        for (int i = 0; i < box.length; i++) {
            box[i].setBackground(startColor);
        }
    }

    @Override
    public void run() {
        String type = (String) MenuPanel.chooseComboBox.getSelectedItem();
        if (type.equals("Increase"))
            incBubbleSort();
        else if (type.equals("Decrease"))
            decBubbleSort();
        SortingVisualizer.endSortSuccess();
    }
}
