/**
 * @author Barry
 * */
public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.toString());
        Square c = new Square(1);
        c.setColor("blue");
        c.setFilled(false);
//        System.out.println(c.getArea());
//        System.out.println(c.getPerimeter());
        System.out.println(c.toString());
    }
}
