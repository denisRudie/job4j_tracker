package ru.job4j.tracker.lambda;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class SearchAtt {

    public static List<Attachment> filterSize(List<Attachment> list) {

        Supplier<String> filterBy = new Supplier<String>() {
            @Override
            public String get() {
                return Integer.toString(100);
            }
        };
        BiPredicate<String, Attachment> predicate = new BiPredicate<String, Attachment>() {
            @Override
            public boolean test(String s, Attachment attachment) {
                return attachment.getSize() > Integer.parseInt(filterBy.get());
            }
        };
        return filter(list, predicate, filterBy);
    }

    public static List<Attachment> filterName(List<Attachment> list) {

        Supplier<String> filterBy = new Supplier<String>() {
            @Override
            public String get() {
                return "bug";
            }
        };
        BiPredicate<String, Attachment> predicate = new BiPredicate<String, Attachment>() {
            @Override
            public boolean test(String s, Attachment attachment) {
                return attachment.getName().contains(filterBy.get());
            }
        };

        return filter(list, predicate, filterBy);
    }

    public static List<Attachment> filter(List<Attachment> list,
                                          BiPredicate<String, Attachment> predicate,
                                          Supplier<String> filterBy) {
        List<Attachment> filteredList = new ArrayList<>();
        for (Attachment attachment : list) {
           if (predicate.test(filterBy.get(), attachment)) {
               filteredList.add(attachment);
           }
        }
        return filteredList;
    }
}
