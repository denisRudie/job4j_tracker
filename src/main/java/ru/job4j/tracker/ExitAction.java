package ru.job4j.tracker;

public class ExitAction implements UserAction {

    @Override
    public String name() {
        return "Выход";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        System.out.println("==== Выход из приложения ====");
        return false;
    }
}
