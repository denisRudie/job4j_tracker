package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StartUI {

    public void init(Input input, Store memTracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Выберите действие ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, memTracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        Consumer<String> show = System.out::println;
        show.accept("Меню: ");
        for (UserAction item : actions) {
            show.accept(actions.indexOf(item) + ". " + item.name());
        }
    }

    public void run(Store store) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        try (store) {
            store.init();
            List<UserAction> actions = new ArrayList<>();
            actions.add(new CreateAction());
            actions.add(new ShowAllAction());
            actions.add(new ReplaceItemAction());
            actions.add(new DeleteItemAction());
            actions.add(new FindItemByIdAction());
            actions.add(new FindItemByNameAction());
            actions.add(new ExitAction());
            new StartUI().init(validate, store, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        StartUI startUI = new StartUI();
        Store tracker = new HbmTracker(); // для БД Hbm
//        Store tracker = new SqlTracker(); // для БД SQL
//        Store tracker = new MemTracker(); // для БД Mem
        startUI.run(tracker);
    }
}
