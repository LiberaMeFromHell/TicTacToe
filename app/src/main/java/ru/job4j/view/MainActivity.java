package ru.job4j.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.job4j.R;
import ru.job4j.model.GameEngine;
import ru.job4j.model.Logic;

public class MainActivity extends AppCompatActivity implements Logic.LogicCallback {

    private GameEngine gameEngine;
    private List<Button> buttons;
    private List<Integer> buttonIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameEngine = new Logic(this);
        initButtons();
    }

    public void answer(View view) {
        switch (view.getId()) {
            case R.id.button0:
                gameEngine.updateField(0);
                break;
            case R.id.button1:
                gameEngine.updateField(1);
                break;
            case R.id.button2:
                gameEngine.updateField(2);
                break;
            case R.id.button3:
                gameEngine.updateField(3);
                break;
            case R.id.button4:
                gameEngine.updateField(4);
                break;
            case R.id.button5:
                gameEngine.updateField(5);
                break;
            case R.id.button6:
                gameEngine.updateField(6);
                break;
            case R.id.button7:
                gameEngine.updateField(7);
                break;
            case R.id.button8:
                gameEngine.updateField(8);
                break;
        }
    }

    public void updateUI(boolean isGameDraw, boolean isGameOver, char[] playFiled) {
        if (isGameDraw) {
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < 9; i++) {
                buttons.get(i).setText("");
            }
        } else if (isGameOver) {
            Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < 9; i++) {
                buttons.get(i).setText("");
            }
        } else {
            for (int i = 0; i < playFiled.length; i++) {
                Log.d("print", "updateUI: " + playFiled[i]);
                buttons.get(i).setText("" + playFiled[i]);
            }
            Log.d("print", "------------------------------");
        }
    }

    private void initButtons() {
        buttonIDs = Arrays.asList(R.id.button0, R.id.button1, R.id.button2,
                R.id.button3, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8);
        Log.d("initButtons", "" + buttonIDs.get(0));

        Switch botSwitch = findViewById(R.id.botSwitch);
        botSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameEngine.switchBot();
            }
        });

        buttons = new ArrayList<>();
        for (Integer i : buttonIDs) {
            buttons.add((Button)findViewById(i));
        }
        Log.d("initButtons", "" + buttons.get(0).getId());

        for (Button b : buttons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("initButtons", "" + view.getId());
                    answer(view);
                }
            });
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("whosTurn", gameEngine.isWhosTurn());
        outState.putCharArray("playField", gameEngine.getPlayField());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        gameEngine.setWhosTurn(savedInstanceState.getBoolean("whosTurn"));
        Log.d("wasa", "onRestoreInstanceState: " + savedInstanceState.getBoolean("whosTurn"));
        gameEngine.setPlayField(savedInstanceState.getCharArray("playField"));
        Log.d("wasa", "onRestoreInstanceState: " + savedInstanceState.getCharArray("playField"));
        this.updateUI(gameEngine.isDrawGame(), gameEngine.isGameOver(), gameEngine.getPlayField());
    }
}
