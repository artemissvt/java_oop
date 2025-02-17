package introduction;

public class Test1 {
    public static void main(String[] args) {
        int a = 10;
        System.out.println(a * 10);
        String s = "HELLO";

        double z;

        z = 3.14;
        System.out.println(z);

        if (a > 10){
        System.out.println("Greater than 10");
        }
        else if (a < 10) {
            System.out.println("Less than 10");
        }
        else {
            System.out.println("Equal 10");
        }
        for (int i = 0; i < a; i++) {
            System.out.println(i);
        }

        a = 10;
        while (a < 10) {
            System.out.println(a);
            a++;
        }
        a = 10;
        do {
            System.out.println(a);
            a++;
        } while (a < 10);
        }

    }
