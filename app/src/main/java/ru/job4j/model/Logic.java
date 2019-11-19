package ru.job4j.model;

import android.util.Log;

public class Logic implements GameEngine{

    private Bot bot;
    private LogicCallback logicCallback;

    private char[] playField = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

    private boolean whosTurn = true;
    private boolean botIsTurned = false;
    private boolean drawGame = false;
    private boolean gameOver = false;

    public Logic(LogicCallback logicCallback) {
        bot = new Bot();
        this.logicCallback = logicCallback;
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

            logicCallback.updateUI(drawGame,gameOver,playField);
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

    public void setPlayField(char[] playField) {
        this.playField = playField;
    }

    public boolean isWhosTurn() {
        return whosTurn;
    }

    public void setWhosTurn(boolean whosTurn) {
        this.whosTurn = whosTurn;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isDrawGame() {
        return drawGame;
    }

    public interface LogicCallback {
        void updateUI(boolean isGameDraw, boolean isGameOver, char[] playFiled);
    }

    private void printDebug() {
        Log.d("print", "Debug" + "\n" + playField[0] + playField[1] + playField[2] + "\n" +
                playField[3] + playField[4] + playField[5] + "\n" +
                playField[6] + playField[7] + playField[8]);
    }
}
