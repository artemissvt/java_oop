package introduction;

public class ArrayExample {
    public static void main(String[] args) {
        int [] data = new int[5];
        data[0] = 1;
        data[1] = 20;
        data[2] = 30;
        data[3] = 40;
        data[4] = 50;

        // first way
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }

        // second way
        for (int value : data) {
            System.out.println(value);
        }
    }
}
