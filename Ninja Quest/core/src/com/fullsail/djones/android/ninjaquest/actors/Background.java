package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.fullsail.djones.android.ninjaquest.enums.GameStates;
import com.fullsail.djones.android.ninjaquest.utils.Constants;
import com.fullsail.djones.android.ninjaquest.utils.GameManagement;

/**
 * Created by David Jones on 1/14/15.
 * MGD 1501
 * Full Sail University
 */
public class Background extends Actor {

    // Texture region to hold background image
    private final TextureRegion textureRegion;

    // Speed that background moves across screen
    private int velocity = 100;

    // Using two instances of background image and overlapping them to create
    // illusion of seamless background
    private Rectangle textureBoundsOne;
    private Rectangle textureBoundsTwo;

    // Constructor
    public Background() {

        // Link background image to texture region from Constants class
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.BACKGROUND_IMG)));
        textureBoundsOne = new Rectangle(0 - Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);
        textureBoundsTwo = new Rectangle(Constants.APP_WIDTH / 2, 0,
                Constants.APP_WIDTH, Constants.APP_HEIGHT);

    }

    // Class to update actor over time
    @Override
    public void act(float delta){

        if (GameManagement.getInstance().getCurrentState() != GameStates.RUNNING){
            return;
        }
        // When image hits left side of screen
        // we reset the bounds for the image
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
        return (textureBoundsTwo.x - (delta * velocity)) <= 0;
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
