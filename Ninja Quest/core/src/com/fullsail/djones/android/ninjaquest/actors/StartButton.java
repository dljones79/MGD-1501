package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.math.Rectangle;
import com.fullsail.djones.android.ninjaquest.enums.GameStates;
import com.fullsail.djones.android.ninjaquest.utils.Constants;
import com.fullsail.djones.android.ninjaquest.utils.GameManagement;

/**
 * Created by David Jones on 1/22/15.
 * MGD 1501
 * Full Sail University
 */
public class StartButton extends GameControls {


    public interface StartButtonListener {
        public void onStart();
    }

    private StartButtonListener listener;

    public StartButton(Rectangle bounds, StartButtonListener listener) {

        super(bounds);

        this.listener = listener;

    }

    @Override
    protected String getRegionName() {
        return Constants.PLAY_BUTTON_NAME;
    }

    @Override
    public void touched() {
        listener.onStart();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (GameManagement.getInstance().getCurrentState() != GameStates.OVER) {
            remove();
        }
    }
}
