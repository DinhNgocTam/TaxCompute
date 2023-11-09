package model;

public class Parent {
    private int age;
    private boolean gender;


    private boolean pension;
    public Parent(int age, boolean gender, boolean pension) {
        this.age = age;
        this.gender = gender;
        this.pension = pension;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }


    public boolean isPension() {

        return pension;
    }

    public void setPension(boolean pension) {
        this.pension = pension;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "age=" + age +
                ", gender=" + gender +
                '}';
    }
}
