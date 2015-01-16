package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.fullsail.djones.android.ninjaquest.box2d.UserData;

/**
 * Created by David Jones on 1/14/15.
 * MGD 1501
 * Full Sail University
 */
public abstract class GameActor extends Actor {

    protected Rectangle onScreenRect; // Rectangle for current Actor being called
    protected Body actorBody;         // Custom Body class for current Actor being called
    protected UserData userData;      // For data type of Actor being called

    public GameActor(Body body) {
        onScreenRect = new Rectangle();
        this.actorBody = body;
        this.userData = (UserData) body.getUserData();
    }

    public abstract UserData getUserData();

    @Override
    public void act(float delta) {
        super.act(delta);

        if (actorBody.getUserData() != null) {
            // Update location of body on screen
            updateRect();
        } else {
            // Destroy the body if it goes out of bounds
            remove();
        }
    }

    private void updateRect() {
        //onScreenRect.x = adjustToScreen(actorBody.getPosition().x - userData.getWidth())
    }
}
