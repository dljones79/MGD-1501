package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.math.Rectangle;
import com.fullsail.djones.android.ninjaquest.enums.GameStates;
import com.fullsail.djones.android.ninjaquest.utils.Constants;
import com.fullsail.djones.android.ninjaquest.utils.GameManagement;

/**
 * Created by David Jones on 1/29/15.
 * MGD 1501
 * Full Sail University
 */
public class AboutButton extends GameControls {

    public interface AboutListener {
        public void onAbout();
    }

    private AboutListener aboutListener;

    public AboutButton(Rectangle bounds, AboutListener listener){
        super(bounds);
        this.aboutListener = listener;
    }

    @Override
    protected String getRegionName() {
        return GameManagement.getInstance().getCurrentState() == GameStates.ABOUT ?
                Constants.DISMISS_REGION_NAME : Constants.ABOUT_NAME;
    }

    @Override
    public void touched() {
        aboutListener.onAbout();
    }
}
