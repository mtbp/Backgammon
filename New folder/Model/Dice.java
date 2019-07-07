package Model;

import java.util.Random;

public class Dice {
    int value;
    boolean isPlayed;

    void toss() {
        Random rand = new Random();
        int n;
        n = rand.nextInt(6);
        this.value = n + 1;
    }
}
