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

    private GameHelper gameHelper;
    private final static int REQUEST_CODE_UNUSED = 7779;

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

    @Override
    public boolean isSignedIn() {
        return gameHelper.isSignedIn();
    }

    @Override
    public void rateGame() {
        String string = "https://play.google.com/store/apps/details?id=com.fullsail.djones.android.ninjaquest";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(string)));
    }

    @Override
    public void submitScore(int score) {
        if (gameHelper.isSignedIn()) {
            Games.Leaderboards.submitScore(gameHelper.getApiClient(), getString(R.string.leaderboard_longest_scorestreak), score);
        } else {
            GameManagement.getInstance().saveScore(score);
        }
    }

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

    @Override
    public void onSignInFailed() {
        mLeaderboardRequested = false;
    }

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
