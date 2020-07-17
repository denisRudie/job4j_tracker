package ru.job4j.tracker.lambda;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
//        predicates for comparing each Person attribute
        Predicate<Person> compAddress = person -> person.getAddress().contains(key);
        Predicate<Person> compName = person -> person.getName().contains(key);
        Predicate<Person> compSurame = person -> person.getSurname().contains(key);
        Predicate<Person> compPhone = person -> person.getPhone().contains(key);

        ArrayList<Person> result = new ArrayList<>();
        for (var person : persons) {
            if (compAddress.or(compName.or(compSurame.or(compPhone))).test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
