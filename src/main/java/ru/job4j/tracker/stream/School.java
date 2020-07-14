package ru.job4j.tracker.stream;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {

    private Predicate<Student> classA = student -> student.getScore() >= 70
            && student.getScore() <= 100;
    private Predicate<Student> classB = student -> student.getScore() >= 50
            && student.getScore() < 70;
    private Predicate<Student> classC = student -> student.getScore() > 0
            && student.getScore() < 50;

    public Predicate<Student> getClassA() {
        return classA;
    }

    public Predicate<Student> getClassB() {
        return classB;
    }

    public Predicate<Student> getClassC() {
        return classC;
    }

    List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream()
                .filter(predict)
                .collect(Collectors.toList());
    }
}
