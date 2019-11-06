package ru.job4j.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ru.job4j.model.Bot;
import ru.job4j.model.GameEngine;
import ru.job4j.model.Logic;

public class TicTacViewModel extends AndroidViewModel {

    private GameEngine gameEngine;
    //private Logic logic;
    private MutableLiveData<GameEngine> logicLiveData = new MutableLiveData<>();

    public TicTacViewModel(@NonNull Application application) {
        super(application);
        gameEngine = Logic.getInstance();
        initLogicLiveData();
    }

    public void initLogicLiveData(){
        logicLiveData.postValue(gameEngine);
    }

    public LiveData<GameEngine> getLogic(){
        return logicLiveData;
    }

    public void updateField(int index){
        gameEngine.updateField(index);
        logicLiveData.postValue(gameEngine);
    }

    public void switchBot(){
        gameEngine.switchBot();
        logicLiveData.postValue(gameEngine);
    }
}
