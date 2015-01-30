package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.fullsail.djones.android.ninjaquest.utils.AssetManagement;
import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David Jones on 1/29/15.
 * MGD 1501
 * Full Sail University
 */
public class AboutText extends Actor {

    private Rectangle textBounds;
    private BitmapFont font;

    public AboutText(Rectangle bounds) {
        this.textBounds = bounds;
        setWidth(textBounds.width);
        setHeight(textBounds.height);
        font = AssetManagement.getAboutFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        font.drawWrapped(batch, Constants.ABOUT_TEXT, textBounds.x,
                textBounds.y, textBounds.width, BitmapFont.HAlignment.CENTER);
    }

}
