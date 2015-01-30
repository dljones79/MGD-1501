package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.math.Rectangle;
import com.fullsail.djones.android.ninjaquest.enums.GameStates;
import com.fullsail.djones.android.ninjaquest.utils.Constants;
import com.fullsail.djones.android.ninjaquest.utils.GameManagement;

/**
 * Created by David on 1/29/15.
 */
public class InstructionsButton extends GameControls {

    public interface InstructionsButtonListener {
        public void onInstruct();
    }

    private InstructionsButtonListener listener;

    public InstructionsButton(Rectangle bounds, InstructionsButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return GameManagement.getInstance().getCurrentState() == GameStates.INSTRUCTIONS ?
                Constants.DISMISS_REGION_NAME : Constants.INSTRUCTIONS_BUTTON_NAME;
    }

    @Override
    public void touched() {
        listener.onInstruct();
    }
}
