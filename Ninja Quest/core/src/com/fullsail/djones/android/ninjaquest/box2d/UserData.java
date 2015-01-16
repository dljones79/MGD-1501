package com.fullsail.djones.android.ninjaquest.box2d;

import com.fullsail.djones.android.ninjaquest.enums.DataTypes;

/**
 * Created by David Jones on 1/14/15.
 * MGD 1501
 * Full Sail University
 */
public abstract class UserData {

    // Create instance of DataTypes
    protected DataTypes dataType;

    // To get actor's width
    protected float width;
    protected float height;

    // Constructor
    public UserData() {

    }

    public UserData(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public DataTypes getDataType() {
        return dataType;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }


}
