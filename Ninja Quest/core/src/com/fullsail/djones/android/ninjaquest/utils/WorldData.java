package com.fullsail.djones.android.ninjaquest.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.fullsail.djones.android.ninjaquest.box2d.GoodNinjaData;
import com.fullsail.djones.android.ninjaquest.box2d.GroundData;


/**
 * Created by David on 1/14/15.
 */
public class WorldData {

    public static World buildWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body layGround(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH / 2, Constants.GROUND_HEIGHT / 2);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        body.setUserData(new GroundData());
        shape.dispose();
        return body;
    }

    public static Body createGoodNinja(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.GOODNINJA_X, Constants.GOODNINJA_Y));
        PolygonShape polyShape = new PolygonShape();
        polyShape.setAsBox(Constants.GOODNINJA_WIDTH / 2, Constants.GOODNINJA_HEIGHT / 2);
        Body goodNinjaBody = world.createBody(bodyDef);
        goodNinjaBody.setGravityScale(Constants.GOODNINJA_GRAVITY);
        goodNinjaBody.createFixture(polyShape, Constants.GOODNINJA_DENSITY);
        goodNinjaBody.resetMassData();
        goodNinjaBody.setUserData(new GoodNinjaData());
        polyShape.dispose();
        return goodNinjaBody;
    }
}
