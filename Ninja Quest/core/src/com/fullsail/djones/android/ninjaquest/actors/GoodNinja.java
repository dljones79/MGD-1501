package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.fullsail.djones.android.ninjaquest.box2d.GoodNinjaData;

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

    // Constructor
    public GoodNinja(Body body) {
        super(body);
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
}
