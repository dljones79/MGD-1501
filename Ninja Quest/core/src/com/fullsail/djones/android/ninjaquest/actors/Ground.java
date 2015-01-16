package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.fullsail.djones.android.ninjaquest.box2d.GroundData;

/**
 * Created by David Jones on 1/14/15.
 * MGD 1501
 * Full Sail University
 */
public class Ground extends GameActor {
    public Ground(Body body) {
        super(body);
    }

    @Override
    public GroundData getUserData() {
        return (GroundData) userData;
    }
}
