package com.fullsail.djones.android.ninjaquest.box2d;

import com.badlogic.gdx.math.Vector2;
import com.fullsail.djones.android.ninjaquest.enums.DataTypes;
import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David on 2/12/15.
 */
public class CollectionData extends UserData {
    private String[] textureRegions;
    private Vector2 velocity;

    public CollectionData(float width, float height, String[] textureRegions) {
        super(width, height);
        this.textureRegions = textureRegions;
        dataType = DataTypes.COLLECTION;
        velocity = Constants.COLLECTION_VELOCITY;
    }

    public String[] getTextureRegions(){
        return textureRegions;
    }

    // Set the linear velocity for the collection item
    public void setVelocity(Vector2 velocity){
        this.velocity = velocity;
    }

    // Get the linear velocity of the collection item
    public Vector2 getVelocity() {
        return velocity;
    }
}
