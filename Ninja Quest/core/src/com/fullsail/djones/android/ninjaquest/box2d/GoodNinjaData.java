package com.fullsail.djones.android.ninjaquest.box2d;

import com.badlogic.gdx.math.Vector2;
import com.fullsail.djones.android.ninjaquest.enums.DataTypes;
import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David on 1/14/15.
 */
public class GoodNinjaData extends UserData {

    private Vector2 linearJump;

    public GoodNinjaData() {
        super();

        linearJump = Constants.GOODNINJA_JUMPING;
        dataType = DataTypes.GOODNINJA;
    }

    public Vector2 getLinearJump() {
        return linearJump;
    }

    public void setLinearJump(Vector2 linearJump) {
        this.linearJump = linearJump;
    }
}
