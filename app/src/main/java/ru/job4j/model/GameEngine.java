package ru.job4j.model;

public interface GameEngine {

    void updateField(int index);

    void switchBot();

    char[] getPlayField();

    void setPlayField(char[] playField);

    boolean isGameOver();

    boolean isDrawGame();

    boolean isWhosTurn();

    void setWhosTurn(boolean whosTurn);


}