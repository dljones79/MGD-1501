package com.fullsail.djones.android.ninjaquest.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.fullsail.djones.android.ninjaquest.box2d.UserData;
import com.fullsail.djones.android.ninjaquest.enums.DataTypes;

/**
 * Created by David on 1/14/15.
 */
public class ActorUtils {

    // Is the body object a Good Ninja?
    public static boolean bodyIsGoodNinja(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getDataType() == DataTypes.GOODNINJA;
    }

    // Is the body object the Ground?
    public static boolean bodyIsGround(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getDataType() == DataTypes.GROUND;
    }
}
