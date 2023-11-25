package team12;

import team12.panel.MenuPanel;
import team12.panel.SortingPanel;
import team12.panel.SubmitPanel;
import team12.sorting.BubbleSort;
import team12.sorting.MergeSort;
import team12.sorting.QuickSort;
import team12.sorting.SelectionSort;

public class SortingVisualizer {
    public static Thread sortingThread;
    public static VisualizeFrame frame;
    public static String typerSort;
    public static boolean pause = false;
    public static boolean endSort = false;
    public static boolean isSorting = false;

    public static void startSort(String typeSort) {
        SortingVisualizer.typerSort = typeSort;
        switch (typeSort) {
            case "Bubble Sort":
                sortingThread = new Thread(new BubbleSort(frame));
                break;
            case "Merge Sort":
                sortingThread = new Thread(new MergeSort(frame));
                break;
            case "Quick Sort":
                sortingThread = new Thread(new QuickSort(frame));
                break;
            case "Selection Sort":
                sortingThread = new Thread(new SelectionSort(frame));
                break;
            default:
                return;
        }
        isSorting = true;
        sortingThread.start();
    }


    public static void endNow() {
        endSort = true;
        if (pause)
            conti();
    }

    public static void pause() {
        pause = true;
    }

    public static void conti() {
        pause = false;
        sortingThread.interrupt();
    }

    public static void checkPause(boolean pause) {
        SortingPanel.compareLabel.setText("Comparison: " + SortingPanel.compareNumber);
        if (pause) {
            try {
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }

    public static void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void endSortSuccess() {
        frame.sortingPanel.successLabel.setVisible(true);
        MenuPanel.nextStep.setEnabled(false);
        MenuPanel.pauseButton.setEnabled(false);
        SubmitPanel.submitButton.setEnabled(true);
        SubmitPanel.submitTextField.setEnabled(true);
        MenuPanel.startButton.setText("Restart");
        MenuPanel.startButton.setEnabled(true);
        MenuPanel.endButton.setEnabled(false);
        pause = false;
        endSort = false;
        isSorting = false;
    }

    public static int getDelay() {
        if (endSort)
            return 0;
        else
            return MenuPanel.speedSlider.getValue();
    }
}
