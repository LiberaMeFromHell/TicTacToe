package ru.job4j.model;

import java.util.Random;

class Bot {

    private Random random = new Random();

    public Bot(){}

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
