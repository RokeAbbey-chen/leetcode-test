package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int width = scanner.nextInt();
        ArrayList<Rectangle> rs = new ArrayList<>();
        rs.add(new Rectangle(width, length));

        length = scanner.nextInt();
        width = scanner.nextInt();
        rs.add(new Rectangle(width, length));

        ArrayList<Circle> cs = new ArrayList<>();
        int radius = scanner.nextInt();
        cs.add(new Circle(radius));
        radius = scanner.nextInt();
        cs.add(new Circle(radius));

        System.out.println(rs.get(0).getPerimeter() + rs.get(1).getPerimeter() + cs.get(0).getPerimeter() + cs.get(1).getPerimeter());
        System.out.println(rs.get(0).getArea() + rs.get(1).getArea() + cs.get(0).getArea() + cs.get(1).getArea());
        System.out.println(Arrays.deepToString(rs.toArray()));
        System.out.println(Arrays.deepToString(cs.toArray()));
    }

}

class Rectangle{

    private int width, length;

    public Rectangle(int width, int length) {
        this.width = width;
        this.length = length;
    }

    public int getPerimeter(){
        return (width + length) << 1;
    }

    public int getArea(){
        return width * length;
    }

    @Override
    public String toString() {
        return "Rectangle [" +
                "width=" + width +
                ", length=" + length +
                ']';
    }
}

class Circle{
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public int getPerimeter(){
        return (int )(2 * radius * Math.PI);
    }

    public int getArea(){
        return (int )(radius * radius * Math.PI);
    }

    @Override
    public String toString() {
        return "Circle [" +
                "radius=" + radius +
                ']';
    }
}
