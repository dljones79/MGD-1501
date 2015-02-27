package com.fullsail.djones.android.ninjaquest.utils;

/**
 * Created by David Jones on 2/16/15.
 * IAD 1502
 * Full Sail University
 */
public interface EventListener {

    public void signIn();
    public void signOut();
    public boolean isSignedIn();
    public void rateGame();
    public void submitScore(int score);                     // submit score to GPS
    public void displayLeaderboard();                       // display GPS leaderboard
    public void share();                                    // share game socially
    public void displayAchievements();                      // display GPS achievements
    public void unlockAchievement(String id);               // unlock an achievement
    public void incrementAchievement(String id, int steps); // increment an achievement
    public String get10JumpAchievementId();
    public String get50JumpAchievementId();
    public String get100JumpAchievementId();
    public String getFirstHitAchievementId();
    public String getAddictedAchievementId();
    public String getTutorialWizardAchievementId();

}
