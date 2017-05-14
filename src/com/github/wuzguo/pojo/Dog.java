package com.github.wuzguo.pojo;

/**
 * IntelliJ IDEA 2016.3
 *
 * @author： admin
 * @date： 五月 13, 2017
 * @version: 1.0.0
 * @desc: ClazzLoader / com.github.wuzguo.pojo
 */
public class Dog extends Animal {
    // 品种
    private String breed;
    // 居住地址
    private String addr;

    public Dog() {
        super();
        System.out.println("Dog的类加载器为： " + Dog.class.getClassLoader().getClass().getName());
    }

    public Dog(String name, int age, int gender) {
        super(name, age, gender);
        System.out.println("Dog的类加载器为： " + Dog.class.getClassLoader().getClass().getName());
    }

    public Dog(String name, int age, int gender, String breed, String addr) {
        super(name, age, gender);
        this.breed = breed;
        this.addr = addr;
        System.out.println("Dog的类加载器为： " + this.getClass().getClassLoader().getClass().getName());
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + this.getBreed().hashCode() + this.getAddr().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }

        Dog that = (Dog) obj;

        if (!this.getName().equals(that.getName())) {
            return false;
        }
        if (!this.getBreed().equals(that.getBreed())) {
            return false;
        }
        if (this.getAge() != that.getAge()) {
            return false;
        }
        if (this.getGender() != that.getGender()) {
            return false;
        }

        return this.getAddr().equals(that.getAddr());
    }

    @Override
    public String toString() {
        return "Hello, Everybody, I'm Tom!";
    }
}
