package vw.snakesladders;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Play {
    public static void main(String[] args) {
        Map<Integer, Integer> snakes = new HashMap<Integer, Integer>() {{
            put(99, 80);
            put(95, 75);
            put(92, 88);
            put(89, 68);
            put(74, 53);
            put(64, 60);
            put(62, 19);
            put(49, 11);
            put(46, 25);
            put(16, 6);
        }};

        Map<Integer, Integer> ladders = new HashMap<Integer, Integer>() {{
            put(2, 38);
            put(7, 14);
            put(8, 31);
            put(15, 26);
            put(21, 42);
            put(28, 84);
            put(36, 44);
            put(51, 67);
            put(71, 91);
            put(78, 98);
            put(87, 94);
        }};


        SnakesLadders game = new SnakesLadders(2, 100, snakes, ladders);


        for(int i=0; ;i++) {
            int dies1 = dise();
            int dise2 = dise();
            System.out.println(i +": dise(" + dies1 +"," + dise2 + ") " + game.play(dies1, dise2));
            if(game.isGameOver())
                break;
        }


    }

    private static int dise() {
        return new Random().nextInt(6) + 1;
    }
}
