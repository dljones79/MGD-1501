package com.fullsail.djones.android.ninjaquest.box2d;

import com.badlogic.gdx.math.Vector2;
import com.fullsail.djones.android.ninjaquest.enums.DataTypes;
import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David Jones on 1/14/15.
 * MGD 1501
 * Full Sail University
 */
public class GoodNinjaData extends UserData {

    private Vector2 linearJump;
    private final Vector2 uprightPosition = new Vector2(Constants.GOODNINJA_X, Constants.GOODNINJA_Y);
    private final Vector2 slidingPosition = new Vector2(Constants.GOODNINJA_SLIDE_X, Constants.GOODNINJA_SLIDE_Y);


    // Constructor
    public GoodNinjaData(float width, float height) {
        super(width, height);

        linearJump = Constants.GOODNINJA_JUMPING;
        dataType = DataTypes.GOODNINJA;
    }

    public Vector2 getLinearJump() {
        return linearJump;
    }

    public void setLinearJump(Vector2 linearJump) {
        this.linearJump = linearJump;
    }

    // Get the angle of the ninja while sliding
    public float getSlidingAngle() {
        return (float) (-90f * (Math.PI / 180f));
    }

    // Get position of ninja while running
    public Vector2 getUprightPosition() {
        return uprightPosition;
    }

    // Get position of ninja while sliding
    public Vector2 getSlidingPosition(){
        return slidingPosition;
    }

    public float getEnemyContactImpulse() {
        return Constants.GOODNINJA_CONTACT_IMPULSE;
    }
}
