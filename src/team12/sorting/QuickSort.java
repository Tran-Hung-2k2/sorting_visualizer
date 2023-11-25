package team12.sorting;

import team12.BoxElement;
import team12.SortingVisualizer;
import team12.VisualizeFrame;
import team12.panel.MenuPanel;
import team12.panel.SortingPanel;

import java.awt.*;

public class QuickSort implements Runnable {
    public static VisualizeFrame frame;
    private static String pivot;

    public QuickSort(VisualizeFrame frame) {
        QuickSort.frame = frame;
    }

    @Override
    public void run() {
        BoxElement[] box = frame.sortingPanel.box;

        Color startColor = box[0].getBackground();

        pivot = (String) MenuPanel.chooseComboBox1.getSelectedItem();

        for (int k = 0; k < box.length; k++)
            box[k].setBackground(Color.orange);

        SortingVisualizer.checkPause(SortingVisualizer.pause);
        SortingVisualizer.delay(SortingVisualizer.getDelay());

        String type = (String) MenuPanel.chooseComboBox.getSelectedItem();
        if (type.equals("Increase"))
            incQuickSort(box, 0, box.length - 1);
        else if (type.equals("Decrease"))
            decQuickSort(box, 0, box.length - 1);

        for (int k = 0; k < box.length; k++)
            box[k].setBackground(startColor);

        SortingVisualizer.endSortSuccess();
    }

    public void incQuickSort(BoxElement[] box, int start, int end) {
        if (start >= end) {
            if (start == end) {

                box[start].setBackground(Color.yellow);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                box[start].setBackground(Color.gray);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());
            }
            return;
        }

        int a, b;
        int i = start;
        int j = end;
        int pValue;
        int pIndex;

        if (pivot.equals("First"))
            pIndex = start;
        else if (pivot.equals("Last"))
            pIndex = end;
        else
            pIndex = start + (end - start) / 2;

        pValue = Integer.parseInt(box[pIndex].getText());
        box[pIndex].setBackground(Color.red);

        SortingVisualizer.checkPause(SortingVisualizer.pause);
        SortingVisualizer.delay(SortingVisualizer.getDelay());

        while (i < j) {
            do {
                a = Integer.parseInt(box[i].getText());
                if (box[i].getBackground() == Color.orange && i != pIndex) {
                    box[i].setBackground(Color.yellow);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());

                    SortingPanel.compareNumber++;
                    if (a <= pValue)
                        box[i].setBackground(Color.blue);
                    else
                        box[i].setBackground(Color.green);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());
                }

                i++;
            } while (a <= pValue && i <= end);
            i--;

            do {
                b = Integer.parseInt(box[j].getText());
                if (box[j].getBackground() == Color.orange && j != pIndex) {
                    box[j].setBackground(Color.yellow);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());

                    SortingPanel.compareNumber++;
                    if (b <= pValue)
                        box[j].setBackground(Color.blue);
                    else
                        box[j].setBackground(Color.green);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());
                } else if (j == pIndex)
                    b = pValue + 1;
                j--;
            } while (b > pValue && j >= start);
            j++;

            if (i < j) {
                if (Math.abs(j - i) > 1)
                    BoxElement.swap(box[i], box[j]);
                else
                    BoxElement.shift(box[i], box[j]);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                BoxElement tmp = box[i];
                box[i] = box[j];
                box[j] = tmp;
            }
        }

        boolean swapflag = false;
        int swapIndex;
        if (pIndex == start && j != pIndex) {
            swapIndex = j;
            if (pValue >= Integer.parseInt(box[swapIndex].getText())) {
                if (Math.abs(swapIndex - pIndex) > 1)
                    BoxElement.swap(box[pIndex], box[swapIndex]);
                else
                    BoxElement.shift(box[pIndex], box[swapIndex]);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                BoxElement tmp = box[pIndex];
                box[pIndex] = box[swapIndex];
                box[swapIndex] = tmp;
                swapflag = true;
            }
        } else if (pIndex == end && i != pIndex) {
            swapIndex = i;
            if (pValue < Integer.parseInt(box[swapIndex].getText())) {
                if (Math.abs(swapIndex - pIndex) > 1)
                    BoxElement.swap(box[pIndex], box[swapIndex]);
                else
                    BoxElement.shift(box[pIndex], box[swapIndex]);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                BoxElement tmp = box[pIndex];
                box[pIndex] = box[swapIndex];
                box[swapIndex] = tmp;
                swapflag = true;
            }
        } else {
            if (pIndex > i && pIndex > j) {
                swapIndex = i;
                if (Math.abs(swapIndex - pIndex) > 1)
                    BoxElement.swap(box[pIndex], box[swapIndex]);
                else
                    BoxElement.shift(box[pIndex], box[swapIndex]);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                BoxElement tmp = box[pIndex];
                box[pIndex] = box[swapIndex];
                box[swapIndex] = tmp;
                swapflag = true;
            } else if (pIndex < i && pIndex < j) {
                swapIndex = j;
                if (Math.abs(swapIndex - pIndex) > 1)
                    BoxElement.swap(box[pIndex], box[swapIndex]);
                else
                    BoxElement.shift(box[pIndex], box[swapIndex]);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                BoxElement tmp = box[pIndex];
                box[pIndex] = box[swapIndex];
                box[swapIndex] = tmp;
                swapflag = true;
            } else
                swapIndex = pIndex;
        }

        for (int k = start; k <= end; k++) {
            if (swapflag) {
                if (k == swapIndex)
                    box[k].setBackground(Color.gray);
                else
                    box[k].setBackground(Color.orange);
            } else {
                if (k == pIndex)
                    box[k].setBackground(Color.gray);
                else
                    box[k].setBackground(Color.orange);
            }
        }

        SortingVisualizer.checkPause(SortingVisualizer.pause);
        SortingVisualizer.delay(SortingVisualizer.getDelay());

        if (end - start == 1) {
            if (pIndex == start) {
                if (swapflag)
                    incQuickSort(box, start, j - 1);
                else
                    incQuickSort(box, i, end);
            } else {
                if (swapflag)
                    incQuickSort(box, i + 1, end);
                else
                    incQuickSort(box, start, j);
            }
        } else {
            if (swapflag) {
                if (swapIndex == start)
                    incQuickSort(box, start + 1, end);
                else if (swapIndex == end)
                    incQuickSort(box, start, end - 1);
                else {
                    incQuickSort(box, start, swapIndex - 1);
                    incQuickSort(box, swapIndex + 1, end);
                }
            } else {
                if (pIndex == start) {
                    incQuickSort(box, start + 1, end);
                } else if (pIndex == end) {
                    incQuickSort(box, start, end - 1);
                } else {
                    incQuickSort(box, start, pIndex - 1);
                    incQuickSort(box, pIndex + 1, end);
                }
            }
        }
    }

    public void decQuickSort(BoxElement[] box, int start, int end) {
        if (start >= end) {
            if (start == end) {

                box[start].setBackground(Color.yellow);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                box[start].setBackground(Color.gray);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());
            }
            return;
        }

        int a, b;
        int i = start;
        int j = end;
        int pValue;
        int pIndex;

        if (pivot.equals("First"))
            pIndex = start;
        else if (pivot.equals("Last"))
            pIndex = end;
        else
            pIndex = start + (end - start) / 2;

        pValue = Integer.parseInt(box[pIndex].getText());
        box[pIndex].setBackground(Color.red);

        SortingVisualizer.checkPause(SortingVisualizer.pause);
        SortingVisualizer.delay(SortingVisualizer.getDelay());

        while (i < j) {
            do {
                a = Integer.parseInt(box[i].getText());
                if (box[i].getBackground() == Color.orange && i != pIndex) {
                    box[i].setBackground(Color.yellow);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());

                    SortingPanel.compareNumber++;
                    if (a < pValue)
                        box[i].setBackground(Color.blue);
                    else
                        box[i].setBackground(Color.green);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());
                }

                i++;
            } while (a >= pValue && i <= end);
            i--;

            do {
                b = Integer.parseInt(box[j].getText());
                if (box[j].getBackground() == Color.orange && j != pIndex) {
                    box[j].setBackground(Color.yellow);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());

                    SortingPanel.compareNumber++;
                    if (b < pValue)
                        box[j].setBackground(Color.blue);
                    else
                        box[j].setBackground(Color.green);

                    SortingVisualizer.checkPause(SortingVisualizer.pause);
                    SortingVisualizer.delay(SortingVisualizer.getDelay());
                } else if (j == pIndex)
                    b = pValue - 1;
                j--;
            } while (b < pValue && j >= start);
            j++;

            if (i < j) {
                if (Math.abs(j - i) > 1)
                    BoxElement.swap(box[i], box[j]);
                else
                    BoxElement.shift(box[i], box[j]);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                BoxElement tmp = box[i];
                box[i] = box[j];
                box[j] = tmp;
            }
        }

        boolean swapflag = false;
        int swapIndex;
        if (pIndex == start && pIndex != j) {
            swapIndex = j;
            if (pValue <= Integer.parseInt(box[swapIndex].getText())) {
                if (Math.abs(swapIndex - pIndex) > 1)
                    BoxElement.swap(box[pIndex], box[swapIndex]);
                else
                    BoxElement.shift(box[pIndex], box[swapIndex]);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                BoxElement tmp = box[pIndex];
                box[pIndex] = box[swapIndex];
                box[swapIndex] = tmp;
                swapflag = true;
            }
        } else if (pIndex == end && pIndex != i) {
            swapIndex = i;
            if (pValue > Integer.parseInt(box[swapIndex].getText())) {
                if (Math.abs(swapIndex - pIndex) > 1)
                    BoxElement.swap(box[pIndex], box[swapIndex]);
                else
                    BoxElement.shift(box[pIndex], box[swapIndex]);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                BoxElement tmp = box[pIndex];
                box[pIndex] = box[swapIndex];
                box[swapIndex] = tmp;
                swapflag = true;
            }
        } else {
            if (pIndex > i && pIndex > j) {
                swapIndex = i;
                if (Math.abs(swapIndex - pIndex) > 1)
                    BoxElement.swap(box[pIndex], box[swapIndex]);
                else
                    BoxElement.shift(box[pIndex], box[swapIndex]);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                BoxElement tmp = box[pIndex];
                box[pIndex] = box[swapIndex];
                box[swapIndex] = tmp;
                swapflag = true;
            } else if (pIndex < i && pIndex < j) {
                swapIndex = j;
                if (Math.abs(swapIndex - pIndex) > 1)
                    BoxElement.swap(box[pIndex], box[swapIndex]);
                else
                    BoxElement.shift(box[pIndex], box[swapIndex]);

                SortingVisualizer.checkPause(SortingVisualizer.pause);
                SortingVisualizer.delay(SortingVisualizer.getDelay());

                BoxElement tmp = box[pIndex];
                box[pIndex] = box[swapIndex];
                box[swapIndex] = tmp;
                swapflag = true;
            } else
                swapIndex = pIndex;
        }

        for (int k = start; k <= end; k++) {
            if (swapflag) {
                if (k == swapIndex)
                    box[k].setBackground(Color.gray);
                else
                    box[k].setBackground(Color.orange);
            } else {
                if (k == pIndex)
                    box[k].setBackground(Color.gray);
                else
                    box[k].setBackground(Color.orange);
            }
        }

        SortingVisualizer.checkPause(SortingVisualizer.pause);
        SortingVisualizer.delay(SortingVisualizer.getDelay());

        if (end - start == 1) {
            if (pIndex == start) {
                if (swapflag)
                    decQuickSort(box, start, j - 1);
                else
                    decQuickSort(box, i, end);
            } else {
                if (swapflag)
                    decQuickSort(box, i + 1, end);
                else
                    decQuickSort(box, start, j);
            }
        } else {
            if (swapflag) {
                if (swapIndex == start)
                    decQuickSort(box, start + 1, end);
                else if (swapIndex == end)
                    decQuickSort(box, start, end - 1);
                else {
                    decQuickSort(box, start, swapIndex - 1);
                    decQuickSort(box, swapIndex + 1, end);
                }
            } else {
                if (pIndex == start) {
                    decQuickSort(box, start + 1, end);
                } else if (pIndex == end) {
                    decQuickSort(box, start, end - 1);
                } else {
                    decQuickSort(box, start, pIndex - 1);
                    decQuickSort(box, pIndex + 1, end);
                }
            }
        }
    }
}
