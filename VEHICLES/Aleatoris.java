package VEHICLES;

/* ===== UTILITAT D'ALEATORIS ===== */

import java.util.Random;

final class Aleatoris {
    private static final Random R = new Random();

    private Aleatoris() {
    }

    static int pesMaxKg() { // 3500 .. 40000
        return 3500 + R.nextInt(40000 - 3500 + 1);
    }

    static int places() { // 2 .. 7
        return 2 + R.nextInt(6);
    }

    static boolean boolea() {
        return R.nextBoolean();
    }
}