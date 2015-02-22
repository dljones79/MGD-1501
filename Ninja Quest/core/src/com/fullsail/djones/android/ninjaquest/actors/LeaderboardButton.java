package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.math.Rectangle;
import com.fullsail.djones.android.ninjaquest.enums.GameStates;
import com.fullsail.djones.android.ninjaquest.utils.Constants;
import com.fullsail.djones.android.ninjaquest.utils.GameManagement;

/**
 * Created by David Jones on 2/20/15.
 * IAD 1502
 * Full Sail University
 */
public class LeaderboardButton extends GameControls {

    public interface LeaderboardButtonListener {
        public void onLeaderboard();
    }

    private LeaderboardButtonListener listener;

    public LeaderboardButton(Rectangle bounds, LeaderboardButtonListener listener){
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return Constants.LEADERBOARD_BUTTON_NAME;
    }

    @Override
    public void touched() {
        listener.onLeaderboard();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (GameManagement.getInstance().getCurrentState() != GameStates.OVER) {
            remove();
        }
    }
}
