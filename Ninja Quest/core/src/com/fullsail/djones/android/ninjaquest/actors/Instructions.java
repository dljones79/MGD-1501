package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.fullsail.djones.android.ninjaquest.enums.GameStates;
import com.fullsail.djones.android.ninjaquest.utils.AssetManagement;
import com.fullsail.djones.android.ninjaquest.utils.GameManagement;

/**
 * Created by David Jones on 1/28/15.
 * MGD 1501
 * Full Sail University
 */
public class Instructions extends Actor {

    private String instructionText;
    private Rectangle instructionBounds;
    private TextureRegion textureRegion;
    private BitmapFont instructionFont;



    public Instructions(Rectangle bounds, String regionName, String text) {
        this.instructionBounds = bounds;
        this.instructionText = text;
        textureRegion = AssetManagement.getTextureRegion(regionName);

        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.delay(5f));
        sequenceAction.addAction(Actions.removeActor());
        addAction(sequenceAction);

        instructionFont = AssetManagement.getInstructionFont();
        setWidth(instructionBounds.width);
        setHeight(instructionBounds.height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (GameManagement.getInstance().getCurrentState() == GameStates.OVER) {
            remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);

        batch.draw(textureRegion, instructionBounds.x, instructionBounds.y,
                instructionBounds.width, instructionBounds.height);
        instructionFont.drawWrapped(batch, instructionText, instructionBounds.x,
                instructionBounds.y, instructionBounds.width,
                BitmapFont.HAlignment.CENTER);
    }

}
