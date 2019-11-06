package ru.job4j.model;

import java.util.Random;

public class Bot {

    private Random random = new Random();

    private static Bot bot;

    public static synchronized Bot getInstance() {
        if (bot == null)
            bot = new Bot();
        return bot;
    }

    private Bot(){}

    int botsTurn(char[] playField) {

        int b;
        for (; ; ) {
            b = random.nextInt(8);
            if (playField[b] == ' ')
                break;
        }
        return b;
    }
}
