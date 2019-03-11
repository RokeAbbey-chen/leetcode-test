package other;

import java.util.Scanner;

public class Main1{
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String name, sex;
            int age;
            name = scanner.next();
            age = scanner.nextInt();
            sex = scanner.next();
            System.out.println(new Student(name, sex, age));
    }
}

class Student {

    private String name, sex;
    private int age;

    public Student(String name, String sex, int age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ']';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
