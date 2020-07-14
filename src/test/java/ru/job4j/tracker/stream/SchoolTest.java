package ru.job4j.tracker.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {
    List<Student> students = List.of(
            new Student("Henderson", 87),
            new Student("Johns", 35),
            new Student("Bryant", 68)
    );

    @Test
    public void testClassA() {
        School school = new School();
        List<Student> studentsA = school.collect(students, school.getClassA());
        assertThat(studentsA.get(0), is(students.get(0)));
    }

    @Test
    public void testClassB() {
        School school = new School();
        List<Student> studentsB = school.collect(students, school.getClassB());
        assertThat(studentsB.get(0), is(students.get(2)));
    }

    @Test
    public void testClassC() {
        School school = new School();
        List<Student> studentsC = school.collect(students, school.getClassC());
        assertThat(studentsC.get(0), is(students.get(1)));
    }

}
