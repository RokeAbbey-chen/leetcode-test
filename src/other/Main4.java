package other;

import java.util.ArrayList;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Animal> list = new ArrayList<>();
        for (int i = 0; i < n; i ++){
            String s1 = scanner.next();
            String s2 = scanner.next();
            int age = scanner.nextInt();
            list.add("cat".equals(s1)? new Cat("cat", s2, age) : new Dog("dog", s2, age));
        }

        for (Animal an : list){
            an.cry();
        }
        for (Animal an : list){
            System.out.println(an.getClass() + "," + an.getClass().getSuperclass());
        }
        System.out.println(n);
    }
}

abstract class Animal{
    String animaltype;
    String name;
    int age;

    public Animal(String animaltype, String name, int age) {
        this.animaltype = animaltype;
        this.name = name;
        this.age = age;
    }

    public abstract void cry();

    public String getAnimaltype() {
        return animaltype;
    }

    public void setAnimaltype(String animaltype) {
        this.animaltype = animaltype;
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

class Cat extends Animal{
    private String shout1;

    public Cat(String animaltype, String name, int age) {
        super(animaltype, name, age);
        this.shout1 = "MIAO~MIAO~MIAO";
    }

    @Override
    public void cry() {
        System.out.println("I am a " + animaltype + ",I'm " + age +",my name is " + name + "." + shout1+".");
    }

    public String getShout1() {
        return shout1;
    }

    public void setShout1(String shout1) {
        this.shout1 = shout1;
    }
}


class Dog extends Animal{

    private String shout2;

    public Dog(String animaltype, String name, int age) {
        super(animaltype, name, age);
        shout2 = "WANG~WANG~WANG~";
    }

    @Override
    public void cry() {
        System.out.println("I am a " + animaltype + ",I'm " + age +",my name is " + name + "." + shout2+".");
    }
}