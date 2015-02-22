package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.math.Rectangle;
import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David on 2/20/15.
 * IAD 1502
 * Full Sail University
 */
public class ShareButton extends GameControls {

    public interface ShareButtonListener {
        public void onShare();
    }

    private ShareButtonListener listener;

    public ShareButton(Rectangle bounds, ShareButtonListener listener){
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return Constants.SHARE_BUTTON_NAME;
    }

    @Override
    public void touched() {
        listener.onShare();
    }
}
