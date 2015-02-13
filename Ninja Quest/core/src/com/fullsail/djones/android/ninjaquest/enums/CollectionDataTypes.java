package com.fullsail.djones.android.ninjaquest.enums;

import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David on 2/10/15.
 */
public enum CollectionDataTypes {

    GROUND_COLLECTION(1f, 1f, Constants.COLLECTION_X, Constants.GROUND_COLLECTION_Y, Constants.COLLECTION_DENSITY,
            Constants.COLLECTION),
    FLOATING_COLLECTION(1f, 1f, Constants.COLLECTION_X,Constants.FLOATING_COLLECTION, Constants.COLLECTION_DENSITY,
            Constants.COLLECTION);

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;
    private String[] regions;

    // Constructor
    CollectionDataTypes(float width, float height, float x, float y, float density, String[] regions){
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
