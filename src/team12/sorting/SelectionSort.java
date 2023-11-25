package team12.sorting;


import team12.BoxElement;
import team12.SortingVisualizer;
import team12.VisualizeFrame;
import team12.panel.MenuPanel;
import team12.panel.SortingPanel;

import java.awt.*;

public class SelectionSort implements Runnable {
    public static VisualizeFrame frame;

    public SelectionSort(VisualizeFrame frame) {
        SelectionSort.frame = frame;
    }

    @Override
    public void run() {
        String type = (String) MenuPanel.chooseComboBox.getSelectedItem();
        if (type.equals("Increase"))
            incSelectionSort();
        else if (type.equals("Decrease"))
            decSelectionSort();
        SortingVisualizer.endSortSuccess();
    }

    public void incSelectionSort() {
        BoxElement[] box = frame.sortingPanel.box;
        Color startColor = box[0].getBackground();
        Color normal = Color.orange;
        int n = box.length;

        for (int i = 0; i < n; i++) {
            box[i].setBackground(normal);
        }
        SortingVisualizer.checkPause(SortingVisualizer.pause);
        SortingVisualizer.delay(SortingVisualizer.getDelay());

        for (int i = 0; i < n - 1; i++) {
            box[i].setBackground(Color.red);
            SortingVisualizer.checkPause(SortingVisualizer.pause);
            SortingVisualizer.delay(SortingVisualizer.getDelay());
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {

                box[j].setBackground(Color.yellow);
                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                int a = Integer.parseInt(box[j].getText());
                int b = Integer.parseInt(box[min_idx].getText());

                if (a < b) {
                    SortingPanel.compareNumber++;
                    box[j].setBackground(Color.blue);
                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());
                    box[min_idx].setBackground(normal);
                    box[j].setBackground(Color.red);
                    min_idx = j;
                } else {
                    SortingPanel.compareNumber++;
                    box[j].setBackground(Color.green);
                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());
                    box[j].setBackground(normal);
                }
                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());
            }

            if (i != min_idx) {
                BoxElement.swap(box[i], box[min_idx]);
                BoxElement temp = box[min_idx];
                box[min_idx] = box[i];
                box[i] = temp;
                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());
            }
            box[i].setBackground(Color.gray);
            SortingVisualizer.checkPause(SortingVisualizer.pause);
            SortingVisualizer.delay(SortingVisualizer.getDelay());
        }
        box[box.length - 1].setBackground(Color.gray);
        SortingVisualizer.checkPause(SortingVisualizer.pause);
        SortingVisualizer.delay(SortingVisualizer.getDelay());
        for (int i = 0; i < n; i++) {
            box[i].setBackground(startColor);
        }
    }

    public void decSelectionSort() {
        BoxElement[] box = frame.sortingPanel.box;
        Color startColor = box[0].getBackground();
        Color normal = Color.orange;
        int n = box.length;

        for (int i = 0; i < n; i++) {
            box[i].setBackground(normal);
        }
        SortingVisualizer.checkPause(SortingVisualizer.pause);
        SortingVisualizer.delay(SortingVisualizer.getDelay());

        for (int i = 0; i < n - 1; i++) {
            box[i].setBackground(Color.red);
            SortingVisualizer.checkPause(SortingVisualizer.pause);
            SortingVisualizer.delay(SortingVisualizer.getDelay());
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {

                box[j].setBackground(Color.yellow);
                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                int a = Integer.parseInt(box[j].getText());
                int b = Integer.parseInt(box[min_idx].getText());

                if (a > b) {
                    SortingPanel.compareNumber++;
                    box[j].setBackground(Color.blue);
                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());
                    box[min_idx].setBackground(normal);
                    box[j].setBackground(Color.red);
                    min_idx = j;
                } else {
                    SortingPanel.compareNumber++;
                    box[j].setBackground(Color.green);
                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());
                    box[j].setBackground(normal);
                }
                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());
            }

            if (i != min_idx) {
                BoxElement.swap(box[i], box[min_idx]);
                BoxElement temp = box[min_idx];
                box[min_idx] = box[i];
                box[i] = temp;
                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());
            }
            box[i].setBackground(Color.gray);
            SortingVisualizer.checkPause(SortingVisualizer.pause);
            SortingVisualizer.delay(SortingVisualizer.getDelay());
        }
        box[box.length - 1].setBackground(Color.gray);
        SortingVisualizer.checkPause(SortingVisualizer.pause);
        SortingVisualizer.delay(SortingVisualizer.getDelay());
        for (int i = 0; i < n; i++) {
            box[i].setBackground(startColor);
        }
    }
}
