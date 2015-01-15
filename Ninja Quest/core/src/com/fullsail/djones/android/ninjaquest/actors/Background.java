package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David on 1/14/15.
 */
public class Background extends Actor {

    private final TextureRegion textureRegion;
    private int velocity = 100;
    private Rectangle textureBoundsOne;
    private Rectangle textureBoundsTwo;

    // Constructor
    public Background() {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.BACKGROUND_IMG)));
        textureBoundsOne = new Rectangle(0 - Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);
        textureBoundsTwo = new Rectangle(Constants.APP_WIDTH / 2, 0,
                Constants.APP_WIDTH, Constants.APP_HEIGHT);

    }

    @Override
    public void act(float delta){
        if (leftSideReached(delta)) {
            resetBounds();
        } else {
            updateBounds(-delta);
        }
    }

    // draw background
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(textureRegion, textureBoundsOne.x, textureBoundsOne.y, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        batch.draw(textureRegion, textureBoundsTwo.x, textureBoundsTwo.y, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    }

    // Has the left of the screen been reached?
    private boolean leftSideReached(float delta) {
        return (textureBoundsTwo.x = (delta * velocity)) <= 0;
    }

    // Update the bounds for background
    private void updateBounds(float delta) {
        textureBoundsOne.x += delta * velocity;
        textureBoundsTwo.x += delta * velocity;
    }

    // Reset bounds for scrolling background
    private void resetBounds() {
        textureBoundsOne = textureBoundsTwo;
        textureBoundsTwo = new Rectangle(Constants.APP_WIDTH, 0,
                Constants.APP_WIDTH, Constants.APP_HEIGHT);
    }

}
