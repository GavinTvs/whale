package com.example.module_test;

/**
 * @author : gavin
 * @date 2018/9/4.
 */

public class PersonEntity {
    String name;
    int age;

    public PersonEntity(String name, int age) {
        this.name = name;
        this.age = age;
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
}
