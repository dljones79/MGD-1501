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

public class PauseButton extends GameControls {

    public interface PauseListener {
        public void onPause();
        public void onResume();
    }

    private PauseListener pauseListener;

    public PauseButton(Rectangle bounds, PauseListener listener) {
        super(bounds);
        this.pauseListener = listener;
    }

    @Override
    protected String getRegionName() {
        return GameManagement.getInstance().getCurrentState() == GameStates.PAUSED ? Constants.PLAY_BUTTON_NAME :
                Constants.PAUSE_BUTTON_NAME;
    }

    @Override
    public void touched() {
        if (GameManagement.getInstance().getCurrentState() == GameStates.PAUSED){
            pauseListener.onResume();
        } else {
            pauseListener.onPause();
        }
    }

    @Override
    public void act(float delta){
        super.act(delta);

        if (GameManagement.getInstance().getCurrentState() == GameStates.OVER) {
            remove();
        }
    }
}
