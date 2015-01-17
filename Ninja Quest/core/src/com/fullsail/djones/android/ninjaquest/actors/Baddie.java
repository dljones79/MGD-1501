package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.fullsail.djones.android.ninjaquest.box2d.EnemyData;
import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David Jones on 1/15/15.
 * MGD 1501
 * Full Sail University
 */
public class Baddie extends GameActor {

    private Animation enemyAnimation;
    private float stateTime;

    public Baddie(Body body){

        super (body);

        TextureAtlas textureAtlas = new TextureAtlas(Constants.ATLAS_PATH);
        TextureRegion[] runningRegions = new TextureRegion[getUserData().getTextureRegions().length];
        for (int i = 0; i < getUserData().getTextureRegions().length; i++){
            String path = getUserData().getTextureRegions()[i];
            runningRegions[i] = textureAtlas.findRegion(path);
        }
        enemyAnimation = new Animation(0.1f, runningRegions);
        stateTime = 0f;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);

        stateTime += Gdx.graphics.getDeltaTime();
        batch.draw(enemyAnimation.getKeyFrame(stateTime, true),(onScreenRect.x - (onScreenRect.width * 0.1f)),
                onScreenRect.y, onScreenRect.width * 1.2f, onScreenRect.height * 1.1f);
    }

    @Override
    public EnemyData getUserData() {
        return (EnemyData) userData;
    }


    @Override
    public void act(float delta){
        super.act(delta);
        actorBody.setLinearVelocity(getUserData().getVelocity());
    }
}
