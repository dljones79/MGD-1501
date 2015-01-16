package com.fullsail.djones.android.ninjaquest.box2d;

import com.badlogic.gdx.math.Vector2;
import com.fullsail.djones.android.ninjaquest.enums.DataTypes;
import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David Jones on 1/15/15.
 * MGD 1501
 * Full Sail University
 */
public class EnemyData extends UserData {

    private Vector2 velocity;

    public EnemyData(float width, float height) {
        super(width, height);
        dataType = DataTypes.ENEMY;
        velocity = Constants.ENEMY_VELOCITY;
    }

    // Set the linear velocity for the enemy
    public void setVelocity(Vector2 velocity){
        this.velocity = velocity;
    }

    // Get the linear velocity of the enemy
    public Vector2 getVelocity() {
        return velocity;
    }
}
