package com.fullsail.djones.android.ninjaquest.enums;

import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David Jones on 1/15/15.
 * MGD 1501
 * Full Sail University
 */
public enum EnemyDataTypes {

    BLACK_NINJA(1f, 1f, Constants.ENEMY_X, Constants.STANDING_NINJA_Y, Constants.ENEMY_DENSITY),
    GREEN_DRAGON(2f, 1f, Constants.ENEMY_X, Constants.FLYING_DRAGON_Y, Constants.ENEMY_DENSITY);

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;

    // Constructor
    EnemyDataTypes(float width, float height, float x, float y, float density){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.density = density;
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
