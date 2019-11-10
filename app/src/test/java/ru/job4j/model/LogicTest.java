package ru.job4j.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LogicTest {
    private Logic logic;
    private Logic.LogicCallback logicCallback;

    @Before
    public void setup() {
         logicCallback = new Logic.LogicCallback() {
            @Override
            public void updateUI(boolean isGameDraw, boolean isGameOver, char[] playFiled) {

            }
        };
        logic = Logic.getNewInstance(logicCallback);
    }

    @Test
    public void getInstance() {
        Logic logic = Logic.getInstance(logicCallback);
        assertTrue(logic instanceof GameEngine);
    }

    @Test
    public void updateFieldNotErase() {
        logic.updateField(0);
        logic.updateField(1);
        logic.updateField(4);
        logic.updateField(3);
        logic.updateField(5);
        logic.updateField(7);
        char[] resultField = {'X', 'O', ' ', 'O', 'X', 'X', ' ', 'O', ' '};
        assertArrayEquals(logic.getPlayField(), resultField);
    }

    @Test
    public void updateFieldWithErase() {
        logic.updateField(0);
        logic.updateField(8);
        logic.updateField(1);
        logic.updateField(4);
        logic.updateField(2);
        char[] emptyField = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        assertArrayEquals(logic.getPlayField(), emptyField);
    }

    @Test
    public void switchBot() {
        logic.updateField(0);
        logic.switchBot();
        ArrayList<Character> result = new ArrayList<Character>();
        for (char ch : logic.getPlayField()) {
            result.add(ch);
        }
        assertTrue(result.contains('O'));
    }

    @Test
    public void isGameOver() {
        logic.updateField(0);
        logic.updateField(1);
        logic.updateField(4);
        logic.updateField(5);
        logic.updateField(8);
        assertTrue(logic.isGameOver());
    }

    @Test
    public void isDrawGame() {
        logic.updateField(0);
        logic.updateField(1);
        logic.updateField(4);
        logic.updateField(5);
        logic.updateField(2);
        logic.updateField(6);
        logic.updateField(3);
        logic.updateField(8);
        logic.updateField(7);
        assertTrue(logic.isDrawGame());
    }
}