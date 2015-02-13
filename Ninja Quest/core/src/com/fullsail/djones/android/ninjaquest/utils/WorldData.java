package com.fullsail.djones.android.ninjaquest.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.fullsail.djones.android.ninjaquest.box2d.CollectionData;
import com.fullsail.djones.android.ninjaquest.box2d.EnemyData;
import com.fullsail.djones.android.ninjaquest.box2d.GoodNinjaData;
import com.fullsail.djones.android.ninjaquest.box2d.GroundData;
import com.fullsail.djones.android.ninjaquest.enums.CollectionDataTypes;
import com.fullsail.djones.android.ninjaquest.enums.EnemyDataTypes;


/**
 * Created by David Jones on 1/14/15.
 * MGD 1501
 * Full Sail University
 */
public class WorldData {

    public static World buildWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body layGround(World world){

        // Create a new body definition to hold all data to construct body
        // Set position of body
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));

        // Create a rigid body with data from bodyDef
        Body body = world.createBody(bodyDef);

        // Create a new PolygonShape and set dimensions
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH / 2, Constants.GROUND_HEIGHT / 2);

        // Create a fixture for the body and set density
        body.createFixture(shape, Constants.GROUND_DENSITY);

        // Set data type to ground
        body.setUserData(new GroundData(Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT));

        // Dispose of the polygon shape
        shape.dispose();
        return body;
    }

    public static Body createGoodNinja(World world) {

        // Create a body definition for the good ninja
        // set it's type to DynamicBody
        // Set position
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.GOODNINJA_X, Constants.GOODNINJA_Y));

        // Create new polygon shape for body
        // set size according to ninja proportions
        PolygonShape polyShape = new PolygonShape();
        polyShape.setAsBox(Constants.GOODNINJA_WIDTH / 2, Constants.GOODNINJA_HEIGHT / 2);

        // Create a new rigid body according to body definitions
        // set gravity of ninja
        // create a fixture and set density of ninja
        // reset the mass data of new ninja
        Body goodNinjaBody = world.createBody(bodyDef);
        goodNinjaBody.setGravityScale(Constants.GOODNINJA_GRAVITY);
        goodNinjaBody.createFixture(polyShape, Constants.GOODNINJA_DENSITY);
        goodNinjaBody.resetMassData();

        // Set user data type to GoodNinja
        goodNinjaBody.setUserData(new GoodNinjaData(Constants.GOODNINJA_WIDTH, Constants.GOODNINJA_HEIGHT));

        // Dispose of polygon shape
        polyShape.dispose();
        return goodNinjaBody;
    }

    public static Body createBaddie(World world) {

        // Create instance of EnemyDataTypes class
        // Run random enum generator
        EnemyDataTypes enemyDataTypes = RandomGenerator.getNewEnemy();

        // Create body definition for new enemy
        BodyDef bodyDef = new BodyDef();

        // Set body type to kinematic (doesn't respond to impact)
        // Set position
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(enemyDataTypes.getX(), enemyDataTypes.getY()));

        // Create polygon shape for enemy
        // Set size according to enemy proportions
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(enemyDataTypes.getWidth() / 2, enemyDataTypes.getHeight() / 2);

        // Create rigid enemy body according to body definitions
        // Create fixture for new enemy and set density
        // reset mass data for enemy
        Body body = world.createBody(bodyDef);
        body.createFixture(polygonShape, enemyDataTypes.getDensity());
        body.resetMassData();

        // Set data type to EnemyData
        EnemyData enemyData = new EnemyData(enemyDataTypes.getWidth(), enemyDataTypes.getHeight(),
                enemyDataTypes.getRegions());
        body.setUserData(enemyData);

        // Dispose of polygon shape
        polygonShape.dispose();
        return body;
    }

    public static Body createCollection(World world){

        // Create instance of CollectionDataTypes class
        // Run random enum generator
        CollectionDataTypes collectionDataTypes = RandomGenerator.getNewCollection();

        // Create body definition for new collection
        BodyDef bodyDef = new BodyDef();

        // Set body to kinematic
        // Set position
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(collectionDataTypes.getX(), collectionDataTypes.getY()));

        // Create polygon shape for collection
        // Set size according to collection proportions
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(collectionDataTypes.getWidth() / 2, collectionDataTypes.getHeight() / 2);

        // Create rigid collection body according to body def
        // Create fixture for new collection and set density
        // reset mass data for collection
        Body body = world.createBody(bodyDef);
        body.createFixture(polygonShape, collectionDataTypes.getDensity());
        body.resetMassData();

        // Set data type to CollectionData
        CollectionData collectionData = new CollectionData(collectionDataTypes.getWidth(), collectionDataTypes.getHeight(),
                collectionDataTypes.getRegions());
        body.setUserData(collectionData);

        // Dispose of polygon shape
        polygonShape.dispose();

        return body;
    }
}
