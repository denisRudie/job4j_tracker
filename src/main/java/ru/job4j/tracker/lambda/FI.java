package ru.job4j.tracker.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class FI {

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Mike", 20, 5),
                new Student("John", 19, 4),
                new Student("Mary", 22, 5)
        );

        Comparator<Student> studentComparatorByAge = new Comparator<>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        };

        students.sort(studentComparatorByAge);
        System.out.println(students);

        students.get(0).setMark(new Mark(5));
        Function<Student, Mark> markFunction = new Function<>() {
            @Override
            public Mark apply(Student student) {
                return student.getMark();
            }
        };

        Mark m = markFunction.apply(students.get(0));
        System.out.println(m);
    }
}
