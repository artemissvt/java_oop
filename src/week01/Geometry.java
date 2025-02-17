package week01;

public class Geometry {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(10, 5);
        Rectangle r2 = new Rectangle(6, 8);

        r1.draw();
        System.out.println();
        r2.draw();
    }
}

class Rectangle {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw() {
        for (int i = 0; i < width; i++) {
            System.out.print("* ");
        }
        System.out.println();


        for (int i = 0; i < height - 2; i++) {
            System.out.print("* ");
            for (int j = 0; j < width - 2; j++) {
                System.out.print("  ");
            }
            if (width > 1) {
                System.out.print("*");
            }
            System.out.println();
        }

        if (height > 1) {
            for (int i = 0; i < width; i++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
