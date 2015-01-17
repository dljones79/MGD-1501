package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.fullsail.djones.android.ninjaquest.box2d.GoodNinjaData;
import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David Jones on 1/14/15.
 * MGD 1501
 * Full Sail University
 */
public class GoodNinja extends GameActor {

    // Bool to convey jumping status
    // Bool to convey sliding status
    private boolean ninjaJumping;
    private boolean ninjaSliding;

    // Bool to cenvey if ninja hit an enemy
    private boolean ninjaCollision;

    // Textures
    private Animation runAnimation;
    private Animation jumpAnimation;
    private Animation slideAnimation;
    private TextureRegion hitTexture;
    private TextureRegion slideTexture;

    // Time for animation
    private float stateTime;

    // Constructor
    public GoodNinja(Body body) {

        super(body);

        TextureAtlas textureAtlas = new TextureAtlas(Constants.ATLAS_PATH);
        TextureRegion[] runningTextures = new TextureRegion[Constants.NINJA_RUNNING_REGION.length];
        for (int i = 0; i < Constants.NINJA_RUNNING_REGION.length; i++) {
            String path = Constants.NINJA_RUNNING_REGION[i];
            runningTextures[i] = textureAtlas.findRegion(path);
        }
        runAnimation = new Animation(0.1f, runningTextures);
        stateTime = 0f;
        TextureRegion[] jumpingTextures = new TextureRegion[Constants.NINJA_JUMP.length];
        for (int i = 0; i < Constants.NINJA_JUMP.length; i++){
            String path = Constants.NINJA_JUMP[i];
            jumpingTextures[i] = textureAtlas.findRegion(path);
        }
        jumpAnimation = new Animation(1.0f, jumpingTextures);
        TextureRegion[] slidingTextures = new TextureRegion[Constants.NINJA_SLIDE_REGION.length];
        for (int i = 0; i < Constants.NINJA_SLIDE_REGION.length; i++) {
            String path = Constants.NINJA_SLIDE_REGION[i];
            slidingTextures[i] = textureAtlas.findRegion(path);
        }
        slideAnimation = new Animation(0.1f, slidingTextures);
        hitTexture = textureAtlas.findRegion(Constants.NINJA_HIT);
        slideTexture = textureAtlas.findRegion(Constants.NINJA_SLIDE);
    }

    // Returns GoodNinjaData type
    @Override
    public GoodNinjaData getUserData() {
        return (GoodNinjaData) userData;
    }

    // Method to make ninja jump off ground
    public void ninjaJump() {
        if (!(ninjaJumping || ninjaSliding || ninjaCollision)){
            actorBody.applyLinearImpulse(getUserData().getLinearJump(), actorBody.getWorldCenter(), true);
            ninjaJumping = true;
        }
    }

    // when ninja has landed on ground
    // we set ninjaJumping to false
    public void ninjaLanded() {
        ninjaJumping = false;
    }

    // method to make ninja slide
    // only while ninja isn't in the air
    public void ninjaSlide() {
        if (!(ninjaJumping || ninjaCollision)) {
            actorBody.setTransform(getUserData().getSlidingPosition(), getUserData().getSlidingAngle());
            ninjaSliding = true;
        }
    }

    // method to stop ninja from sliding
    // only when not hit
    public void stopSliding() {
        ninjaSliding = false;

        if (!ninjaCollision) {
            actorBody.setTransform(getUserData().getUprightPosition(), 0f);
        }
    }

    // check to see if ninja is sliding
    public boolean isNinjaSliding() {
        return ninjaSliding;
    }

    // check to see if ninja collided with baddie
    public void collision() {
        actorBody.applyAngularImpulse(getUserData().getEnemyContactImpulse(), true);
        ninjaCollision = true;
    }

    // bool to tell if ninja hit a bad guy
    public boolean didCollide() {
        return ninjaCollision;
    }

    @Override
    public void draw(Batch batch, float parentAlpah) {
        super.draw(batch, parentAlpah);

        if (ninjaSliding) {
            /*
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(slideAnimation.getKeyFrame(stateTime, true), onScreenRect.x, onScreenRect.y,
                    onScreenRect.getWidth(), onScreenRect.getHeight());
            */
            batch.draw(slideTexture, onScreenRect.x, onScreenRect.y + onScreenRect.height / 4,
                    onScreenRect.width, onScreenRect.height * 3 / 4);
        } else if (ninjaCollision) {
            batch.draw(hitTexture, onScreenRect.x, onScreenRect.y, onScreenRect.width * 0.5f,
                    onScreenRect.height * 0.5f, onScreenRect.width, onScreenRect.height, 1f, 1f,
                    (float) Math.toDegrees(actorBody.getAngle()));
        } else if (ninjaJumping) {
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(jumpAnimation.getKeyFrame(stateTime, true), onScreenRect.x, onScreenRect.y,
                    onScreenRect.getWidth(), onScreenRect.getHeight());
        } else {
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(runAnimation.getKeyFrame(stateTime, true), onScreenRect.x, onScreenRect.y,
                    onScreenRect.getWidth(), onScreenRect.getHeight());
        }
    }
}
