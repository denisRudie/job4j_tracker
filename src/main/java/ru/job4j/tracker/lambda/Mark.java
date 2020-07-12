package ru.job4j.tracker.lambda;

public class Mark {
    private int mark;

    public Mark(int mark) {
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Mark{"
                + "mark=" + mark
                + '}';
    }
}
