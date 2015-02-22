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

    private boolean mLeaderboardRequested;

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
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mLeaderboardRequested = savedInstanceState.getBoolean(LOCAL_LEADERBOARD_REQUESTED, false);
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

    // If sign in failed
    @Override
    public void onSignInFailed() {
        mLeaderboardRequested = false;
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

    }
}
