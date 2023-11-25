package team12.sorting;

import team12.BoxElement;
import team12.SortingVisualizer;
import team12.VisualizeFrame;
import team12.panel.MenuPanel;
import team12.panel.SortingPanel;

import java.awt.*;


public class MergeSort implements Runnable {
    public static VisualizeFrame frame;
    public static int y;

    public MergeSort(VisualizeFrame frame) {
        MergeSort.frame = frame;
    }

    @Override
    public void run() {
        BoxElement[] box = frame.sortingPanel.box;
        String type = (String) MenuPanel.chooseComboBox.getSelectedItem();
        Color startColor = box[0].getBackground();
        y = box[0].getY();
        for (int k = 0; k < box.length; k++)
            box[k].setBackground(Color.orange);

        SortingVisualizer.checkPause(SortingVisualizer.pause);
        SortingVisualizer.delay(SortingVisualizer.getDelay());

        if (type.equals("Increase"))
            incMergeSort(box, 0, box.length - 1);
        else if (type.equals("Decrease"))
            decMergeSort(box, 0, box.length - 1);
        for (int k = 0; k < box.length; k++)
            box[k].setBackground(startColor);
        SortingVisualizer.endSortSuccess();
    }

    public void incMergeSort(BoxElement[] box, int start, int end) {

        Color splitColor = (new Color((float) Math.random(), (float) Math.random(), (float) Math.random())).brighter();
        Color normal = box[start].getBackground();

        if (start == end) {

            BoxElement.goDown(box, start, end);

            SortingVisualizer.checkPause(SortingVisualizer.pause);
            SortingVisualizer.delay(SortingVisualizer.getDelay());

            BoxElement.goIndex(box[start], box[start].getX(), y);

            SortingVisualizer.checkPause(SortingVisualizer.pause);
            SortingVisualizer.delay(SortingVisualizer.getDelay());
            return;
        } else {
            int mid = split(box, start, end);

            for (int i = mid; i <= end; i++)
                box[i].setBackground(splitColor);

            SortingVisualizer.checkPause(SortingVisualizer.pause);
            SortingVisualizer.delay(SortingVisualizer.getDelay());

            incMergeSort(box, start, mid - 1);

            incMergeSort(box, mid, end);

            BoxElement.goDown(box, start, end);

            SortingVisualizer.checkPause(SortingVisualizer.pause);
            SortingVisualizer.delay(SortingVisualizer.getDelay());

            incMerge(box, start, end, mid, normal);
        }
    }

    public void incMerge(BoxElement[] box, int start, int end, int mid, Color normal) {
        BoxElement[] abox = new BoxElement[end - start + 1];
        int array1 = start;
        int array2 = mid;
        int x = box[start].getX();
        int offset = box[start].getWidth() + 5;
        int index = 0;

        while (array1 < mid || array2 <= end) {

            if (array1 == mid && array2 <= end) {
                box[array2].setBackground(normal);
                BoxElement.goIndex(box[array2], x + index * offset, y);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                abox[index] = box[array2];
                index++;
                array2++;
            } else if (array1 < mid && array2 == end + 1) {
                box[array1].setBackground(normal);
                BoxElement.goIndex(box[array1], x + index * offset, y);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                abox[index] = box[array1];
                index++;
                array1++;
            } else if (array1 < mid && array2 <= end) {
                int a = Integer.parseInt(box[array1].getText());
                int b = Integer.parseInt(box[array2].getText());
                if (a <= b) {
                    SortingPanel.compareNumber++;
                    box[array1].setBackground(normal);
                    BoxElement.goIndex(box[array1], x + index * offset, y);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());

                    abox[index] = box[array1];
                    index++;
                    array1++;
                } else {
                    SortingPanel.compareNumber++;
                    box[array2].setBackground(normal);
                    BoxElement.goIndex(box[array2], x + index * offset, y);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());

                    abox[index] = box[array2];
                    index++;
                    array2++;
                }
            }
        }

        for (int i = start; i <= end; i++)
            box[i] = abox[i - start];
    }

    public void decMergeSort(BoxElement[] box, int start, int end) {

        Color splitColor = (new Color((float) Math.random(), (float) Math.random(), (float) Math.random())).brighter();
        Color normal = box[start].getBackground();

        if (start == end) {

            BoxElement.goDown(box, start, end);

            SortingVisualizer.checkPause(SortingVisualizer.pause);
            SortingVisualizer.delay(SortingVisualizer.getDelay());

            BoxElement.goIndex(box[start], box[start].getX(), y);

            SortingVisualizer.checkPause(SortingVisualizer.pause);
            SortingVisualizer.delay(SortingVisualizer.getDelay());
            return;
        } else {
            int mid = split(box, start, end);

            for (int i = mid; i <= end; i++)
                box[i].setBackground(splitColor);

            SortingVisualizer.checkPause(SortingVisualizer.pause);
            SortingVisualizer.delay(SortingVisualizer.getDelay());

            decMergeSort(box, start, mid - 1);

            decMergeSort(box, mid, end);

            BoxElement.goDown(box, start, end);

            SortingVisualizer.checkPause(SortingVisualizer.pause);
            SortingVisualizer.delay(SortingVisualizer.getDelay());

            decMerge(box, start, end, mid, normal);
        }
    }

    public void decMerge(BoxElement[] box, int start, int end, int mid, Color normal) {
        BoxElement[] abox = new BoxElement[end - start + 1];
        int array1 = start;
        int array2 = mid;
        int x = box[start].getX();
        int offset = box[start].getWidth() + 5;
        int index = 0;

        while (array1 < mid || array2 <= end) {

            if (array1 == mid && array2 <= end) {
                box[array2].setBackground(normal);
                BoxElement.goIndex(box[array2], x + index * offset, y);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                abox[index] = box[array2];
                index++;
                array2++;
            } else if (array1 < mid && array2 == end + 1) {
                box[array1].setBackground(normal);
                BoxElement.goIndex(box[array1], x + index * offset, y);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                abox[index] = box[array1];
                index++;
                array1++;
            } else if (array1 < mid && array2 <= end) {
                int a = Integer.parseInt(box[array1].getText());
                int b = Integer.parseInt(box[array2].getText());
                if (a >= b) {
                    SortingPanel.compareNumber++;
                    box[array1].setBackground(normal);
                    BoxElement.goIndex(box[array1], x + index * offset, y);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());

                    abox[index] = box[array1];
                    index++;
                    array1++;
                } else {
                    SortingPanel.compareNumber++;
                    box[array2].setBackground(normal);
                    BoxElement.goIndex(box[array2], x + index * offset, y);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());

                    abox[index] = box[array2];
                    index++;
                    array2++;
                }
            }
        }

        for (int i = start; i <= end; i++)
            box[i] = abox[i - start];
    }

    public int split(BoxElement[] box, int start, int end) {
        return start + (end - start + 1) / 2;
    }
}

