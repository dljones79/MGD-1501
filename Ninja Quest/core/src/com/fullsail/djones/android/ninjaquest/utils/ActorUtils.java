package com.fullsail.djones.android.ninjaquest.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.fullsail.djones.android.ninjaquest.box2d.UserData;
import com.fullsail.djones.android.ninjaquest.enums.DataTypes;

/**
 * Created by David Jones on 1/14/15.
 * MGD 1501
 * Full Sail University
 */
public class ActorUtils {

    // Is the body object a Good Ninja?
    public static boolean bodyIsGoodNinja(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getDataType() == DataTypes.GOODNINJA;
    }

    // Is the body object an enemy?
    public static boolean bodyIsBaddie(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getDataType() == DataTypes.ENEMY;
    }

    // Is the body object the Ground?
    public static boolean bodyIsGround(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getDataType() == DataTypes.GROUND;
    }

    // Test to see if the Body is on the screen
    public static boolean bodyOnScreen(Body body) {
        UserData userData = (UserData) body.getUserData();

        switch (userData.getDataType()){
            case GOODNINJA:
            case ENEMY:
                return body.getPosition().x + userData.getWidth() / 2 > 0;
            case COLLECTION:
                return body.getPosition().x + userData.getWidth() / 2 > 0;
        }

        return true;
    }

    public static boolean bodyIsCollection(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getDataType() == DataTypes.COLLECTION;
    }

}
