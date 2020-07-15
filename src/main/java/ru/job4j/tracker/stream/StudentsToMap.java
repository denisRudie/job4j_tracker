package ru.job4j.tracker.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentsToMap {

    List<Student> students = List.of(
            new Student("Beck", 100),
            new Student("Tiffany", 94),
            new Student("Beck", 100)
    );

    Map<String, Student> map(List<Student> students) {
        return students.stream()
                .distinct()
                .collect(Collectors.toMap(
                    Student::getSurname,
                    student -> student
                ));
    }

    public static void main(String[] args) {
        StudentsToMap studentsToMap = new StudentsToMap();
        Map<String, Student> studentMap = studentsToMap.map(studentsToMap.students);
        System.out.println(studentMap);
    }
}