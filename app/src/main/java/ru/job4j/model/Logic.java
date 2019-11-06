package ru.job4j.model;

import android.util.Log;

public class Logic implements GameEngine{

    private static Logic logic;
    private Bot bot;

    private char[] playField = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

    private boolean whosTurn = true;
    private boolean botIsTurned = false;
    private boolean drawGame = false;
    private boolean gameOver = false;

    public static synchronized Logic getInstance() {
        if (logic == null)
            logic = new Logic();
        return logic;
    }

    private Logic() {
        bot = Bot.getInstance();
        //printDebug();
    }

    public void updateField(int cellIndex) {

        if (playField[cellIndex] == ' ') {

            if (whosTurn) {
                playField[cellIndex] = 'X';
            } else {
                playField[cellIndex] = 'O';
            }

            if (!(gameOver = isGameOver(cellIndex))) {
                if (drawGame = isDraw()) {
                    wipe();
                    whosTurn = false;
                }
            } else {
                wipe();
                whosTurn = false;
            }

            //printDebug();

            whosTurn = !whosTurn;

            if (botIsTurned && !whosTurn) {
                updateField(bot.botsTurn(playField));
            }
        }
    }

    private boolean isGameOver(int cellIndex) {

        int row = cellIndex - cellIndex % 3; //getting row

        if (playField[row] == playField[row + 1] && playField[row] == playField[row + 2])
            return true; //checking row

        int column = cellIndex % 3; //getting column

        if (playField[column] == playField[column + 3] && playField[column] == playField[column + 6])
            return true; //checking column

        if (cellIndex % 2 != 0) return false; //if corner value

        if (cellIndex % 4 == 0) {
            if (playField[0] == playField[4] && playField[0] == playField[8])
                return true; //checking left diagonal
            if (cellIndex != 4) return false;
        }

        return (playField[2] == playField[4] && playField[2] == playField[6]); //checking right diagonal
    }

    private void wipe() {
        for (int i = 0; i < 9; i++) {
            playField[i] = ' ';
        }
    }

    /*private void printDebug() {
        Log.d("print", "Debug" + "\n" + playField[0] + playField[1] + playField[2] + "\n" +
                playField[3] + playField[4] + playField[5] + "\n" +
                playField[6] + playField[7] + playField[8]);
    }*/

    private boolean isDraw() {
        boolean d = true;
        for (char c : playField) {
            if (c == ' ')
                d = false;
        }
        return d;
    }

    public void switchBot() {
        botIsTurned = !botIsTurned;
        if (!whosTurn)
            updateField(bot.botsTurn(playField));
    }

    public char[] getPlayField() {
        return playField;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isDrawGame() {
        return drawGame;
    }

    /**
     * Workaround for testing purposes. We need a clean object to run tests properly. Singleton is bad
     */
    public static Logic getNewInstance() {
        return new Logic();
    }
}
