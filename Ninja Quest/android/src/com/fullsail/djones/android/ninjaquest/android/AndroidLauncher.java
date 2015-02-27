package com.fullsail.djones.android.ninjaquest.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.fullsail.djones.android.ninjaquest.MyGdxGame;
import com.fullsail.djones.android.ninjaquest.utils.Constants;
import com.fullsail.djones.android.ninjaquest.utils.EventListener;
import com.fullsail.djones.android.ninjaquest.utils.GameManagement;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;

public class AndroidLauncher extends AndroidApplication implements EventListener, GameHelper.GameHelperListener {

    // Create instance of GameHelper from BaseGameUtils
    private GameHelper gameHelper;

    private static String LOCAL_LEADERBOARD_REQUESTED = "LOCAL_LEADERBOARD_REQUESTED";
    private static String LOCAL_ACHIEVEMENTS_REQUESTED = "LOCAL_ACHIEVEMENTS_REQUESTED";

    private boolean mLeaderboardRequested;
    private boolean mAchievementsRequested;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new MyGdxGame(this), config);

        // Create a GameHelper
        gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
        gameHelper.setup(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(LOCAL_LEADERBOARD_REQUESTED, mLeaderboardRequested);
        outState.putBoolean(LOCAL_ACHIEVEMENTS_REQUESTED, mAchievementsRequested);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mLeaderboardRequested = savedInstanceState.getBoolean(LOCAL_LEADERBOARD_REQUESTED, false);
        mAchievementsRequested = savedInstanceState.getBoolean(LOCAL_ACHIEVEMENTS_REQUESTED, false);
    }

    @Override
    protected void onStart(){
        super.onStart();
        gameHelper.onStart(this);
    }

    @Override
    protected void onStop(){
        super.onStop();
        gameHelper.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        gameHelper.onActivityResult(requestCode, resultCode, data);
    }

    // Override method for singning in.
    @Override
    public void signIn() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
        }
    }

    // Override method for signing out
    @Override
    public void signOut() {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameHelper.signOut();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
        }
    }

    // Checks to see if user is signed in
    @Override
    public boolean isSignedIn() {
        return gameHelper.isSignedIn();
    }

    // Rate game...button not implemented yet.
    @Override
    public void rateGame() {
        String string = "https://play.google.com/store/apps/details?id=com.fullsail.djones.android.ninjaquest";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(string)));
    }

    // Method to submit score to leaderboard
    @Override
    public void submitScore(int score) {
        if (gameHelper.isSignedIn()) {
            Games.Leaderboards.submitScore(gameHelper.getApiClient(), getString(R.string.leaderboard_longest_scorestreak), score);
        } else {
            GameManagement.getInstance().saveScore(score);
        }
    }

    // Method to display the leaderboard
    @Override
    public void displayLeaderboard() {
        if (gameHelper.isSignedIn()) {
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
                    getString(R.string.leaderboard_longest_scorestreak)), 24);
        } else {
            gameHelper.beginUserInitiatedSignIn();
            mLeaderboardRequested = true;
        }
    }

    // Method to share game through social sharing
    @Override
    public void share() {
        String url = String.format("http://play.google.com/store/apps/details?id=%s",
                BuildConfig.APPLICATION_ID);
        String message = String.format(Constants.SHARE_MESSAGE, url);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, Constants.SHARE_HEADING));
    }

    // Method to display GPS achievements for the game
    @Override
    public void displayAchievements() {
        if (gameHelper.isSignedIn()) {
            startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient()), 25);
        } else {
            gameHelper.beginUserInitiatedSignIn();
            mAchievementsRequested = true;
        }
    }

    // Unlock an achievement for the game
    @Override
    public void unlockAchievement(String id) {
        if (gameHelper.isSignedIn()) {
            Games.Achievements.unlock(gameHelper.getApiClient(), id);
            GameManagement.getInstance().setAchievementUnlocked(id);
        }
    }

    // Increment an achievement for the game
    @Override
    public void incrementAchievement(String id, int steps) {
        if (gameHelper.isSignedIn()) {
            Games.Achievements.increment(gameHelper.getApiClient(), id, steps);
            GameManagement.getInstance().incrementAchievementCount(id, steps);
        }
    }

    // Achievements
    @Override
    public String get10JumpAchievementId() {
        return getString(R.string.achievement_10_jumps);
    }

    @Override
    public String get50JumpAchievementId() {
        return getString(R.string.achievement_50_jumps);
    }

    @Override
    public String get100JumpAchievementId() {
        return getString(R.string.achievement_100_jumps);
    }

    @Override
    public String getFirstHitAchievementId() {
        return getString(R.string.achievement_first_hit);
    }

    @Override
    public String getAddictedAchievementId() {
        return getString(R.string.achievement_addicted);
    }

    @Override
    public String getTutorialWizardAchievementId() {
        return getString(R.string.achievement_tutorial_wizard);
    }

    // If sign in failed
    @Override
    public void onSignInFailed() {
        mLeaderboardRequested = false;
        mAchievementsRequested = false;
    }

    // If sign in succeeded
    @Override
    public void onSignInSucceeded() {
        if (GameManagement.getInstance().hasSavedMaxScore()) {
            GameManagement.getInstance().submitSavedMaxScore();
        }

        if (mLeaderboardRequested) {
            displayLeaderboard();
            mLeaderboardRequested = false;
        }

        if (mAchievementsRequested) {
            displayAchievements();
            mAchievementsRequested = false;
        }

    }
}
