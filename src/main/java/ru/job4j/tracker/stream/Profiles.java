package ru.job4j.tracker.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {

    List<Address> collect(List<Profile> profiles) {
        return profiles.stream()
                .map(Profile::getAddress)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<Address> addressList = List.of(
            new Address("Moscow", "Arbat", 1, 1),
            new Address("Amsterdam", "Arbat", 1, 1),
            new Address("Moscow", "Arbat", 1, 1)
        );

        List<Address> sortedList = addressList.stream()
                .sorted(Comparator.comparing(Address::getCity))
                .distinct()
                .collect(Collectors.toList());

        sortedList.forEach(System.out::println);
    }
}