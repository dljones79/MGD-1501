package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.fullsail.djones.android.ninjaquest.box2d.GroundData;
import com.fullsail.djones.android.ninjaquest.enums.GameStates;
import com.fullsail.djones.android.ninjaquest.utils.Constants;
import com.fullsail.djones.android.ninjaquest.utils.GameManagement;

/**
 * Created by David Jones on 1/14/15.
 * MGD 1501
 * Full Sail University
 */
public class Ground extends GameActor {

    private final TextureRegion textureRegion;
    private Rectangle textureBoundsOne;
    private Rectangle textureBoundsTwo;
    private int velocity = 9;

    public Ground(Body body) {
        super(body);

        textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.GROUND_IMG)));
        textureBoundsOne = new Rectangle(0 - getUserData().getWidth() / 2, 0, getUserData().getWidth(),
                getUserData().getHeight());
        textureBoundsTwo = new Rectangle(getUserData().getWidth() / 2, 0, getUserData().getWidth(),
                getUserData().getHeight());
    }

    @Override
    public GroundData getUserData() { return (GroundData) userData; }

    @Override
    public void act(float delta){
        super.act(delta);

        if (GameManagement.getInstance().getCurrentState() != GameStates.RUNNING){
            return;
        }
        if (leftSideReached(delta)) {
            resetBounds();
        } else {
            updateBounds(-delta);
        }
    }

    private boolean leftSideReached( float delta ) {
        return (textureBoundsTwo.x - fitToScreen(delta * velocity)) <= 0;
    }

    private void updateBounds(float delta) {
        textureBoundsOne.x += fitToScreen(delta * velocity);
        textureBoundsTwo.x += fitToScreen(delta * velocity);
    }

    private void resetBounds() {
        textureBoundsOne = textureBoundsTwo;
        textureBoundsTwo = new Rectangle(textureBoundsOne.x + onScreenRect.width, 0,
                onScreenRect.width, onScreenRect.height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, textureBoundsOne.x, onScreenRect.y, onScreenRect.getWidth(),
                onScreenRect.getHeight());
        batch.draw(textureRegion, textureBoundsTwo.x, onScreenRect.y, onScreenRect.getWidth(),
                onScreenRect.getHeight());
    }


}
