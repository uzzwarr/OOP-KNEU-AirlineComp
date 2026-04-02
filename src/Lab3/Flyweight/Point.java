package Lab3.Flyweight;

public class Point implements Shape {
    @Override

    public void draw(int x, int y) {

        System.out.println("point - x: " + x + ", y: " + y);
    }
}
