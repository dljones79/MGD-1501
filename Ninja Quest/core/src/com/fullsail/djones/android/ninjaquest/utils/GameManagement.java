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
    private static final String ACHIEVEMENT_COUNT = "_count";
    private static final String ACHIEVEMENT_UNLOCKED = "_unlocked";

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

    @Override
    public void displayAchievements() {
        eventListener.displayAchievements();
    }

    @Override
    public void unlockAchievement(String id) {
        eventListener.unlockAchievement(id);
    }

    @Override
    public void incrementAchievement(String id, int steps) {
        eventListener.incrementAchievement(id, steps);
    }

    @Override
    public String get10JumpAchievementId() {
        return eventListener.get10JumpAchievementId();
    }

    @Override
    public String get50JumpAchievementId() {
        return eventListener.get50JumpAchievementId();
    }

    @Override
    public String get100JumpAchievementId() {
        return eventListener.get100JumpAchievementId();
    }

    @Override
    public String getFirstHitAchievementId() {
        return eventListener.getFirstHitAchievementId();
    }

    @Override
    public String getAddictedAchievementId() {
        return eventListener.getAddictedAchievementId();
    }

    @Override
    public String getTutorialWizardAchievementId() {
        return eventListener.getTutorialWizardAchievementId();
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

    public void addPlayedGame() {
        /*
        if (getAchievementCount(getAddictedAchievementId()) > 5) {
            return;
        }
        */
        if (!isAchievementUnlocked(getFirstHitAchievementId())) {
            unlockAchievement(getFirstHitAchievementId());
        }

        if (getAchievementCount(getAddictedAchievementId()) <= 9){
            incrementAchievement(getAddictedAchievementId(), 1);
        }

        /*
        if (!isAchievementUnlocked(getAddictedAchievementId())) {
            unlockAchievement(getAddictedAchievementId());
        }
        */

    }

    public void addJumpCount(int count){
        if (count <= 0) {
            return;
        }

        if (!isAchievementUnlocked(get10JumpAchievementId())) {
            if (count >= 10){
                unlockAchievement(get10JumpAchievementId());
            }
        }

        if (!isAchievementUnlocked(get50JumpAchievementId())) {
            if (count >= 50){
                unlockAchievement(get50JumpAchievementId());
            }
        }

        if (!isAchievementUnlocked(get100JumpAchievementId())) {
            if (count >= 100){
                unlockAchievement(get100JumpAchievementId());
            }
        }

        /*
        if (getAchievementCount(get100JumpAchievementId()) > 100) {
            return;
        }

        if (getAchievementCount(get100JumpAchievementId()) <= 10) {
            incrementAchievement(get10JumpAchievementId(), count);
        }

        if (getAchievementCount(get100JumpAchievementId()) <= 50) {
            incrementAchievement(get50JumpAchievementId(), count);
        }

        incrementAchievement(get100JumpAchievementId(), count);
        */
    }

    public void unlockTutorialAchievement(){
        if (!isAchievementUnlocked(getTutorialWizardAchievementId())){
            unlockAchievement(getTutorialWizardAchievementId());
        }
    }

    public void setAchievementUnlocked(String id){
        getPreferences().putBoolean(getAchievementsUnlockedId(id), true);
    }

    public void incrementAchievementCount(String id, int steps) {
        Preferences preferences = getPreferences();
        int count = preferences.getInteger(getAchievementCountId(id), 0);
        count += steps;
        preferences.putInteger(getAchievementCountId(id), count);
        preferences.flush();
    }

    private int getAchievementCount(String id) {
        return getPreferences().getInteger(getAchievementCountId(id), 0);
    }

    private boolean isAchievementUnlocked(String id) {
        return getPreferences().getBoolean(getAchievementsUnlockedId(id), false);
    }

    private String getAchievementCountId(String id){
        return id + ACHIEVEMENT_COUNT;
    }

    private String getAchievementsUnlockedId(String id){
        return id + ACHIEVEMENT_UNLOCKED;
    }


}
