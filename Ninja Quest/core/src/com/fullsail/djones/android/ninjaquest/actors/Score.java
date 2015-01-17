package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David on 1/17/15.
 */
public class Score extends Actor {

    // For displaying score
    private BitmapFont scoreFont;
    private int multiplierAmount;
    private float scoreStreak;
    private Rectangle bounds;


    public Score(Rectangle bounds){
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        scoreStreak = 0;
        multiplierAmount = 5;
        setupFont();
    }

    @Override
    public void act (float delta) {
        super.act(delta);

        scoreStreak += multiplierAmount * delta;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (getScore() == 0) {
            return;
        }
        scoreFont.drawWrapped(batch, String.format("%d", getScore()), bounds.x, bounds.y,
                bounds.width, BitmapFont.HAlignment.CENTER);
    }

    public void setupFont() {
        // For score
        FreeTypeFontGenerator typeGenerator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_PATH));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParam = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParam.size = 40;
        scoreFont = typeGenerator.generateFont(fontParam);
        scoreFont.setColor(.21f, .22f, .21f, 1f);
        typeGenerator.dispose();
    }

    public int getScore() {
        return (int) Math.floor(scoreStreak);
    }
}
