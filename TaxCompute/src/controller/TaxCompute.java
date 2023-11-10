package controller;

import common.Library;
import model.BroSis;
import model.Children;
import model.Parent;
import model.Person;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author PC
 */
public class TaxCompute {

    Library library = new Library();

    public Children inputChildren() {
        int age = library.getIntegerGreatThan0("Age of children: ");
        System.out.print("School or not: ");
        boolean school = library.checkInputYN();
        return new Children(age, school);
    }

    public Parent inputParent() {
        int age = library.getIntegerGreatThan0("Input age of parent: ");
        System.out.print("Male or female: ");
        boolean gender = library.maleOrFemale();
        System.out.print("Have pension or not: ");
        boolean pension = library.checkInputYN();
        return new Parent(age, gender, pension);
    }

    public BroSis inputBrosis() {
        double salary = library.getDouble("Input salary of your brother or sister: ");
        return new BroSis(salary);
    }

    public double khauTruDoiVoiCon(Children[] childrens) {
        int count = 0;
        double result = 0;
        for (Children children : childrens) {
            if (count <= 2) {

                if (children.getAge() < 18) {
                    result += 4400000;
                    count++;
                } else if (children.getAge() >= 18 && children.isSchool()) {
                    result += 6000000;
                    count++;
                } else if ((children.getAge() >= 18 && !children.isSchool()) || children.getAge() >= 22) {
                    result += 0;
                }
            }
        }
        return result;
    }

    public double khauTruDoiVoiChaMe(Parent[] parents, BroSis[] broSES) {
        double result = 0;
        for (Parent parent : parents) {
            if (parent.getAge() >= 60 && parent.isGender() && !parent.isPension()) {
                result += 4400000;
            } else if (parent.getAge() >= 55 && !parent.isGender() && !parent.isPension()) {
                result += 4400000;
            }
        }

        int count = 0;
        for (BroSis broSis : broSES) {
            if (broSis.getSalary() >= 4000000) {
                count++;
            }
        }
        result = result / (double) (count + 1);
        return result;
    }

    public double soThuePhaiNop(double khauTruVoiCon, double khauTruVoiChaMe, double salary) {
        double result = 0;

        double khauTruGiaCanh = khauTruVoiCon + khauTruVoiChaMe + 11000000;

        result = salary - khauTruGiaCanh;

        if (result < 0) {
            return -1;
        }
        if (result < 4000000) {
            result *= 0.05;
        } else if (result >= 4000000 && result <= 6000000) {
            result *= 0.08;
        } else if (result > 6000000 && result <= 10000000) {
            result *= 0.1;
        } else if (result > 10000000) {
            result *= 0.2;
        }
        return result;
    }
    
    public double tienDu(double salary, double tienThue){
        double tenPercentSalary = salary * 0.1;
        return tenPercentSalary - tienThue;
    }

    public Person inputPerson() {

        String name = library.getValue("Input name: ");
        int numChildren = library.getIntegerGreatThan0("Input number of children: ");
        int numParent = library.getIntegerGreatThan0("Input number of Parent: ");
        int numBrosis = library.getIntegerGreatThan0("Input number of Brosis: ");
        double salary = library.getDouble("Input salary of you: ");
        return new Person(name, numChildren, numParent, numBrosis, salary);
    }

    public void display() {
        Person person = inputPerson();
        int numberChildren = person.getNumberChildren();
        Children[] childrens = new Children[numberChildren];
        for (int i = 0; i < numberChildren; i++) {
            System.out.println((i + 1) + "st children");
            childrens[i] = inputChildren();
        }

        int numberParent = person.getNumberParent();
        Parent[] parents = new Parent[numberParent];

        for (int j = 0; j < numberParent; j++) {
            System.out.println((j + 1) + "st parent");
            parents[j] = inputParent();
        }

        int numberBrosis = person.getNumberBrosis();
        BroSis[] broSES = new BroSis[numberBrosis];

        for (int j = 0; j < numberBrosis; j++) {
            System.out.println((j + 1) + "st bro or sis");
            broSES[j] = inputBrosis();
        }

        double giamThueViCon = khauTruDoiVoiCon(childrens);
        double giamThueViChaMe = khauTruDoiVoiChaMe(parents, broSES);

        String value1 = String.format("%.1f", giamThueViCon);
        System.out.println("Các khoản khấu trừ dành cho con cái: " + value1);
        String value2 = String.format("%.1f", giamThueViChaMe);
        System.out.println("Các khoản khấu trừ dành cho cha mẹ: " + value2);
        double taxPayable = soThuePhaiNop(giamThueViCon, giamThueViChaMe, person.getSalary());
        if (taxPayable == -1) {
            System.out.println("Bạn được miễn thuế");
            return;
        }
        String value3 = String.format("%.1f", taxPayable);

        System.out.println("Số thuế phải nộp là : " + value3);
        
        String value4 = String.format("%.1f", tienDu(person.getSalary(), taxPayable));
        System.out.println("Tiền được trả lại: " + value4);
    }

    public void run() {
        while (true) {

            display();
            System.out.print("Countinue or stop: ");

            boolean check = library.checkInputYN();
            if (!check) {
                break;
            }
        }
        System.out.println("Program stop");

    }
}
