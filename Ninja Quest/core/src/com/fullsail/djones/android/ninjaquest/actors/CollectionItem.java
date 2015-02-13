package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.fullsail.djones.android.ninjaquest.box2d.CollectionData;
import com.fullsail.djones.android.ninjaquest.enums.GameStates;
import com.fullsail.djones.android.ninjaquest.utils.Constants;
import com.fullsail.djones.android.ninjaquest.utils.GameManagement;

/**
 * Created by David on 2/12/15.
 */
public class CollectionItem extends GameActor {

    private Animation collectionAnimation;
    private float stateTime;

    public CollectionItem(Body body){

        super (body);

        TextureAtlas textureAtlas = new TextureAtlas(Constants.ATLAS_PATH);
        TextureRegion[] runningRegions = new TextureRegion[getUserData().getTextureRegions().length];
        for (int i = 0; i < getUserData().getTextureRegions().length; i++){
            String path = getUserData().getTextureRegions()[i];
            runningRegions[i] = textureAtlas.findRegion(path);
        }
        collectionAnimation = new Animation(0.1f, runningRegions);
        stateTime = 0f;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);

        if (GameManagement.getInstance().getCurrentState() != GameStates.PAUSED) {
            stateTime += Gdx.graphics.getDeltaTime();
        }
        batch.draw(collectionAnimation.getKeyFrame(stateTime, true),(onScreenRect.x - (onScreenRect.width * 0.1f)),
                onScreenRect.y, onScreenRect.width * 1.2f, onScreenRect.height * 1.1f);
    }

    @Override
    public CollectionData getUserData() {
        return (CollectionData) userData;
    }


    @Override
    public void act(float delta){
        super.act(delta);
        actorBody.setLinearVelocity(getUserData().getVelocity());
    }
}
