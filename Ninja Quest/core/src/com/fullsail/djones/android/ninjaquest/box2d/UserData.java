package com.fullsail.djones.android.ninjaquest.box2d;

import com.fullsail.djones.android.ninjaquest.enums.DataTypes;

/**
 * Created by David on 1/14/15.
 */
public abstract class UserData {

    // Create instance of DataTypes
    protected DataTypes dataType;

    // Constructor
    public UserData() {

    }

    public DataTypes getDataType() {
        return dataType;
    }
}
