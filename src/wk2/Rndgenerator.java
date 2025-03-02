package wk2;

import java.util.Random;

public class Rndgenerator {
    public static void main(String[] args) {
        Random rand = new Random();
        int sum = 0;
        for (int i = 0; i < 7; i++) {
            int r = rand.nextInt(100);
            System.out.println(r);
            sum += r;
            double average = (double) sum / 7;
            System.out.println(average);

        }

    }
}
