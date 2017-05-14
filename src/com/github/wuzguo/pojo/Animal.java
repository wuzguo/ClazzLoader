package com.github.wuzguo.pojo;

/**
 * IntelliJ IDEA 2016.3
 *
 * @author： admin
 * @date： 五月 13, 2017
 * @version: 1.0.0
 * @desc: ClazzLoader / com.github.wuzguo.pojo
 */
public class Animal {
    // 名称
    private String name;
    // 年龄
    private int age;
    // 性别
    private int gender;

    public Animal() {
        System.out.println("Animal的类加载器为： " + Animal.class.getClassLoader().getClass().getName());
    }

    public Animal(String name, int age, int gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        System.out.println("Animal的类加载器为： " + Animal.class.getClassLoader().getClass().getName());
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Hello, I'm Animal!";
    }
}
