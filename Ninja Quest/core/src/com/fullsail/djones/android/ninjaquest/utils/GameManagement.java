package com.fullsail.djones.android.ninjaquest.utils;

import com.fullsail.djones.android.ninjaquest.enums.DifficultyLevel;
import com.fullsail.djones.android.ninjaquest.enums.GameStates;

/**
 * Created by David on 1/22/15.
 */
public class GameManagement {

    private GameStates currentState;
    private DifficultyLevel difficulty;
    private static GameManagement currentInstance = new GameManagement();

    public static GameManagement getInstance() {
        return currentInstance;
    }

    private GameManagement() {
        currentState = GameStates.OVER;
    }

    public GameStates getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameStates gameState) {
        this.currentState = gameState;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    public boolean atMaxDifficulty() {
        return difficulty == DifficultyLevel.values()[DifficultyLevel.values().length - 1];
    }

    public void resetDifficultyLevel() {
        setDifficulty(DifficultyLevel.values()[0]);
    }




}
