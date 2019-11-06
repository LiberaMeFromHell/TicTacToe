package ru.job4j.model;

public interface GameEngine {

    void updateField(int index);

    void switchBot();

    char[] getPlayField();

    boolean isGameOver();

    boolean isDrawGame();

}