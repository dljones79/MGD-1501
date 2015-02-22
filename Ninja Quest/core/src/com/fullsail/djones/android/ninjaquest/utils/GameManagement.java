package com.fullsail.djones.android.ninjaquest.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.fullsail.djones.android.ninjaquest.enums.DifficultyLevel;
import com.fullsail.djones.android.ninjaquest.enums.GameStates;

/**
 * Created by David on 1/22/15.
 */
public class GameManagement implements EventListener {

    private GameStates currentState;
    private DifficultyLevel difficulty;
    private static GameManagement currentInstance = new GameManagement();
    private EventListener eventListener;

    public static final String PREFERENCES_NAME = "preferences";
    private static final String MAX_SCORE_PREFERENCE = "max_score";

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

    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }


    @Override
    public void signIn() {

    }

    @Override
    public void signOut() {

    }

    @Override
    public boolean isSignedIn() {
        return false;
    }

    @Override
    public void rateGame() {

    }

    @Override
    public void submitScore(int score) {
        eventListener.submitScore(score);
    }

    @Override
    public void displayLeaderboard() {
        eventListener.displayLeaderboard();
    }

    @Override
    public void share() {
        eventListener.share();
    }

    private Preferences getPreferences() {
        return Gdx.app.getPreferences(PREFERENCES_NAME);
    }

    public void saveScore(int score) {
        Preferences preferences = getPreferences();
        int maxScore = preferences.getInteger(MAX_SCORE_PREFERENCE, 0);
        if (score > maxScore) {
            preferences.putInteger(MAX_SCORE_PREFERENCE, score);
            preferences.flush();
        }
    }

    public boolean hasSavedMaxScore() {
        return getPreferences().getInteger(MAX_SCORE_PREFERENCE, 0 ) > 0;
    }

    public void submitSavedMaxScore() {
        Preferences preferences = getPreferences();
        submitScore(preferences.getInteger(MAX_SCORE_PREFERENCE, 0));
        preferences.remove(MAX_SCORE_PREFERENCE);
        preferences.flush();
    }


}
