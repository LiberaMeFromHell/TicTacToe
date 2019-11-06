package ru.job4j.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.model.GameEngine;
import ru.job4j.R;
import ru.job4j.viewmodel.TicTacViewModel;

public class MainActivity extends AppCompatActivity {

    TicTacViewModel viewModel;

    List<Button> buttons = new ArrayList<>();

    Switch botSwitch;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(TicTacViewModel.class);

        botSwitch = findViewById(R.id.botSwitch);
        botSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.switchBot();
            }
        });

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);

        buttons.add(button0);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        buttons.add(button8);

        viewModel.getLogic().observe(this, new Observer<GameEngine>() {
            @Override
            public void onChanged(GameEngine gameEngine) {
                updateUI(gameEngine.isDrawGame(), gameEngine.isGameOver(), gameEngine.getPlayField());
            }
        });
    }

    public void answer(View view) {
        switch (view.getId()) {
            case R.id.button0:
                viewModel.updateField(0);
                return;
            case R.id.button1:
                viewModel.updateField(1);
                return;
            case R.id.button2:
                viewModel.updateField(2);
                return;
            case R.id.button3:
                viewModel.updateField(3);
                return;
            case R.id.button4:
                viewModel.updateField(4);
                return;
            case R.id.button5:
                viewModel.updateField(5);
                return;
            case R.id.button6:
                viewModel.updateField(6);
                return;
            case R.id.button7:
                viewModel.updateField(7);
                return;
            case R.id.button8:
                viewModel.updateField(8);
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
                buttons.get(i).setText("" + playFiled[i]);
            }
        }
    }
}
