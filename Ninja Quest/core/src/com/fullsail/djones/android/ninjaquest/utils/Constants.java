package com.fullsail.djones.android.ninjaquest.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by David Jones on 1/14/15.
 * MGD 1501
 * Full Sail University
 */
public class Constants {

    // App screen dimensions
    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 480;

    // Constant for the world gravity
    // Set at negative 10 m/2^2
    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    // Constants for the ground
    public static final float GROUND_X = 0;
    public static final float GROUND_Y = 0;
    public static final float GROUND_WIDTH = 50f;
    public static final float GROUND_HEIGHT = 2f;
    public static final float GROUND_DENSITY = 0f;

    // Good Ninja Constants
    public static final float GOODNINJA_X = 2;
    public static final float GOODNINJA_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float GOODNINJA_WIDTH = 1f;
    public static final float GOODNINJA_HEIGHT = 2f;
    public static float GOODNINJA_DENSITY = 0.5f;
    public static final float GOODNINJA_GRAVITY = 3f;
    public static final Vector2 GOODNINJA_JUMPING = new Vector2(0, 13f);
    public static final float GOODNINJA_CONTACT_IMPULSE = 10F;
    public static final float GOODNINJA_SLIDE_X = 2f;
    public static final float GOODNINJA_SLIDE_Y = 1.5f;

    // Background Image
    public static final String BACKGROUND_IMG = "background.png";
    public static final String GROUND_IMG = "ground.jpg";

    // Constants for enemies
    public static final float ENEMY_X = 25f;
    public static final float ENEMY_DENSITY = GOODNINJA_DENSITY;
    public static final float STANDING_NINJA_Y = 1.5f;
    public static final float FLYING_DRAGON_Y = 3f;
    public static final Vector2 ENEMY_VELOCITY = new Vector2(-10f, 0);

}
