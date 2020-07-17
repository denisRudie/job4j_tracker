package ru.job4j.tracker.lambda;

import java.util.Iterator;
import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставки использовать add(int index, E value)
     *
     * @param task задача
     */
    public void put(Task task) {
        Iterator<Task> iter = tasks.iterator();
        int i = 0;
        for (; iter.hasNext(); i++) {
            if (iter.next().getPriority() > task.getPriority()) {
                break;
            }
        }
        tasks.add(i, task);

    }

    public Task take() {
        return this.tasks.poll();
    }
}