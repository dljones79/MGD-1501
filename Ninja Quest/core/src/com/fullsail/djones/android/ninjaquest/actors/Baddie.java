package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.fullsail.djones.android.ninjaquest.box2d.EnemyData;

/**
 * Created by David Jones on 1/15/15.
 * MGD 1501
 * Full Sail University
 */
public class Baddie extends GameActor {


    public Baddie(Body body){
        super (body);
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
