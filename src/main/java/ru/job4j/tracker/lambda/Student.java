package ru.job4j.tracker.lambda;

public class Student {
    private String name;
    private int age;
    private Mark mark;

    public Student(String name, int age, int mark) {
        this.name = name;
        this.age = age;
        this.mark = new Mark(mark);
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }
}