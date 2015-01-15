package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.fullsail.djones.android.ninjaquest.box2d.UserData;

/**
 * Created by David on 1/14/15.
 */
public abstract class GameActor extends Actor {

    protected Body actorBody;
    protected UserData userData;

    public GameActor(Body body) {
        this.actorBody = body;
        this.userData = (UserData) body.getUserData();
    }

    public abstract UserData getUserData();
}
