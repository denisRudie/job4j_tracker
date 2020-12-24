package ru.job4j.tracker;

import java.util.List;

public class ShowAllAction implements UserAction {

    @Override
    public String name() {
        return "Показать все заявки";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        System.out.println("==== Показать все заявки ====");
        List<Item> list = memTracker.findAll();
        int index = 1;
        for (Item item : list) {
            System.out.printf("Заявка %d. id = %s, name = %s;%n", index, item.getId(), item.getName());
            index++;
        }
        return true;
    }
}
