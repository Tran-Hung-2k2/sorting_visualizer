package team12;

import javax.swing.*;

public class BoxElement extends JButton {
    public static void goDown(BoxElement box1, BoxElement box2) {
        int min = 255;
        while (min < 400) {
            if (box1.getY() < 400)
                box1.setLocation(box1.getX(), box1.getY() + 1);
            if (box2.getY() < 400)
                box2.setLocation(box2.getX(), box2.getY() + 1);
            try {
                Thread.sleep(SortingVisualizer.getDelay() / 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            min = Math.min(box1.getY(), box2.getY());
        }
    }

    public static void goDown(BoxElement[] box, int start, int end) {
        int max = box[start].getY();
        for (int i = start; i < end; i++) {
            if (box[i].getY() != box[i + 1].getY())
                return;
        }
        while (box[end].getY() < max + 145) {
            for (int i = start; i <= end; i++) {
                box[i].setLocation(box[i].getX(), box[i].getY() + 1);
            }
            try {
                Thread.sleep(SortingVisualizer.getDelay() / 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void shift(BoxElement box1, BoxElement box2) {
        int x1 = box1.getX();
        int x2 = box2.getX();

        if (x1 < x2) {
            while (box2.getX() > x1) {
                box1.setLocation(box1.getX() + 1, box1.getY());
                box2.setLocation(box2.getX() - 1, box2.getY());
                try {
                    Thread.sleep(SortingVisualizer.getDelay() / 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (x1 > x2) {
            while (box2.getX() < x1) {
                box1.setLocation(box1.getX() - 1, box1.getY());
                box2.setLocation(box2.getX() + 1, box2.getY());
                try {
                    Thread.sleep(SortingVisualizer.getDelay() / 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void swap(BoxElement box1, BoxElement box2) {
        if (box1 == box2) return;
        int y = box1.getY();
        while (box1.getY() < y + 95 && box2.getY() > y - 95) {
            if (box1.getY() < y + 95)
                box1.setLocation(box1.getX(), box1.getY() + 1);
            if (box2.getY() > y - 95)
                box2.setLocation(box2.getX(), box2.getY() - 1);
            try {
                Thread.sleep(SortingVisualizer.getDelay() / 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        shift(box1, box2);

        while (box1.getY() > y && box2.getY() < y) {
            if (box1.getY() > y)
                box1.setLocation(box1.getX(), box1.getY() - 1);
            if (box2.getY() < y)
                box2.setLocation(box2.getX(), box2.getY() + 1);
            try {
                Thread.sleep(SortingVisualizer.getDelay() / 50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void goIndex(BoxElement box, int x, int y) {
        int xbox = box.getX();
        int ybox = box.getY();
        if (xbox <= x && ybox < y) {
            while (box.getX() < x || box.getY() < y) {
                if (box.getX() < x)
                    box.setLocation(box.getX() + 1, box.getY());
                if (box.getY() < y)
                    box.setLocation(box.getX(), box.getY() + 1);
                try {
                    Thread.sleep(SortingVisualizer.getDelay() / 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (xbox <= x && ybox > y) {
            while (box.getX() < x || box.getY() > y) {
                if (box.getX() < x)
                    box.setLocation(box.getX() + 1, box.getY());
                if (box.getY() > y)
                    box.setLocation(box.getX(), box.getY() - 1);
                try {
                    Thread.sleep(SortingVisualizer.getDelay() / 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (xbox >= x && ybox < y) {
            while (box.getX() > x || box.getY() < y) {
                if (box.getX() > x)
                    box.setLocation(box.getX() - 1, box.getY());
                if (box.getY() < y)
                    box.setLocation(box.getX(), box.getY() + 1);
                try {
                    Thread.sleep(SortingVisualizer.getDelay() / 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (xbox >= x && ybox > y) {
            while (box.getX() > x || box.getY() > y) {
                if (box.getX() > x)
                    box.setLocation(box.getX() - 1, box.getY());
                if (box.getY() > y)
                    box.setLocation(box.getX(), box.getY() - 1);
                try {
                    Thread.sleep(SortingVisualizer.getDelay() / 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
