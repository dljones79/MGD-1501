package com.fullsail.djones.android.ninjaquest.box2d;

import com.fullsail.djones.android.ninjaquest.enums.DataTypes;

/**
 * Created by David Jones on 1/14/15.
 * MGD 1501
 * Full Sail University
 */
public class GroundData extends UserData {

    // Constructor
    public GroundData(float width, float height) {
        super(width, height);
        super.dataType = DataTypes.GROUND;
    }
}
