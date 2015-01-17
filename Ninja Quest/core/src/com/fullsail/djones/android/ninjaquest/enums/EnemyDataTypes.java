package com.fullsail.djones.android.ninjaquest.enums;

import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David Jones on 1/15/15.
 * MGD 1501
 * Full Sail University
 */
public enum EnemyDataTypes {

    BLACK_NINJA(1f, 1f, Constants.ENEMY_X, Constants.STANDING_NINJA_Y, Constants.ENEMY_DENSITY,
            Constants.BLACK_NINJA_RUN),
    BLUE_CREATURE(1f, 1f, Constants.ENEMY_X, Constants.BLUE_CREATURE_Y, Constants.ENEMY_DENSITY,
            Constants.BLUE_CREATURE),
    FLYING_EYE(1f, 1f, Constants.ENEMY_X, Constants.FLYING_EYE_Y, Constants.ENEMY_DENSITY,
               Constants.FLYING_EYE);


    private float width;
    private float height;
    private float x;
    private float y;
    private float density;
    private String[] regions;

    // Constructor
    EnemyDataTypes(float width, float height, float x, float y, float density, String[] regions){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.density = density;
        this.regions = regions;
    }

    public String[] getRegions() {
        return regions;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDensity() {
        return density;
    }

}
