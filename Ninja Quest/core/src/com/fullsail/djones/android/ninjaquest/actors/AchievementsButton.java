package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.math.Rectangle;
import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David Jones on 2/26/15.
 * IAD 1502
 * Full Sail University
 */
public class AchievementsButton extends GameControls {

    public interface AchievementsButtonListener {
        public void onAchievements();
    }

    private AchievementsButtonListener listener;

    public AchievementsButton(Rectangle bounds, AchievementsButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return Constants.ACHIEVEMENTS_BUTTON_NAME;
    }

    @Override
    public void touched() {
        listener.onAchievements();
    }
}
