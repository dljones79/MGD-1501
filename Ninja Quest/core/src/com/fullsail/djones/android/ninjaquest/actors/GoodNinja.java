package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.fullsail.djones.android.ninjaquest.box2d.GoodNinjaData;

/**
 * Created by David on 1/14/15.
 */
public class GoodNinja extends GameActor {

    private boolean ninjaJumping;

    public GoodNinja(Body body) {
        super(body);
    }

    @Override
    public GoodNinjaData getUserData() {
        return (GoodNinjaData) userData;
    }

    public void ninjaJump() {
        if (!ninjaJumping){
            actorBody.applyLinearImpulse(getUserData().getLinearJump(), actorBody.getWorldCenter(), true);
            ninjaJumping = true;
        }
    }

    public void ninjaLanded() {
        ninjaJumping = false;
    }
}
