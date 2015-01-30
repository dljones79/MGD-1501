package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.fullsail.djones.android.ninjaquest.utils.AssetManagement;
import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David Jones on 1/27/15.
 * MGD 1501
 * Full Sail University
 */
public class GameTitle extends Actor {

    private Rectangle labelBounds;
    private BitmapFont labelFont;

    public GameTitle(Rectangle labelBounds){
        this.labelBounds = labelBounds;
        setWidth(labelBounds.width);
        setHeight(labelBounds.height);
        labelFont = AssetManagement.getTitleFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        labelFont.drawWrapped(batch, Constants.GAME_TITLE, labelBounds.x, labelBounds.y, labelBounds.width,
                BitmapFont.HAlignment.CENTER);
    }

}
