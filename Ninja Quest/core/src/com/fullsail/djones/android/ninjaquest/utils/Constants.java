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
    public static final float CONVERT_TO_SCREEN = 32;

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
    public static final float GOODNINJA_X = 7;
    public static final float GOODNINJA_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float GOODNINJA_WIDTH = 1.5f;
    public static final float GOODNINJA_HEIGHT = 2f;
    public static float GOODNINJA_DENSITY = 0.5f;
    public static final float GOODNINJA_GRAVITY = 2f;
    public static final Vector2 GOODNINJA_JUMPING = new Vector2(0, 20f);
    public static final float GOODNINJA_CONTACT_IMPULSE = 10F;
    public static final float GOODNINJA_SLIDE_X = 7f;
    public static final float GOODNINJA_SLIDE_Y = 1.5f;

    // Background Image
    public static final String BACKGROUND_IMG = "background.png";
    public static final String GROUND_IMG = "ground.png";

    // Constants for enemies
    public static final float ENEMY_X = 40f;
    public static final float ENEMY_DENSITY = GOODNINJA_DENSITY;
    public static final float STANDING_NINJA_Y = 1.5f;
    public static final float FLYING_EYE_Y = 4f;
    public static final float BLUE_CREATURE_Y = 2f;
    public static final Vector2 ENEMY_VELOCITY = new Vector2(-11f, 0);

    // Constants for collection item
    public static final float COLLECTION_X = 55F;
    public static final float COLLECTION_DENSITY = 0.1f;
    public static final float GROUND_COLLECTION_Y = 1.5f;
    public static final float FLOATING_COLLECTION = 4f;
    public static final Vector2 COLLECTION_VELOCITY = new Vector2(-10f, 0);

    // Textures for sprites
    public static final String ATLAS_PATH = "spritesheet.txt";
    public static final String[] NINJA_RUNNING_REGION = new String[] {
            "ninjaCat_run1", "ninjaCat_run2", "ninjaCat_run3", "ninjaCat_run4",
            "ninjaCat_run5"};
    public static final String[] NINJA_SLIDE_REGION = new String[] {
            "ninjaCat_slide1", "ninjaCat_slide2"};
    public static final String NINJA_SLIDE = "ninjaCat_slide1";
    public static final String NINJA_HIT = "ninjaCat_hit";
    public static final String[] NINJA_JUMP = new String[] {
            "ninjaCat_jump1", "ninjaCat_jump2", "ninjaCat_jump3",
            "ninjaCat_jump4", "ninjaCat_jump5", "ninjaCat_jump6"};
    public static final String[] BLACK_NINJA_RUN = new String [] {
            "black_walk1", "black_walk2", "black_walk3", "black_walk4"};
    public static final String[] BLUE_CREATURE =  new String [] {
            "blue_creature1", "blue_creature2", "blue_creature3", "blue_creature4"};
    public static final String[] FLYING_EYE = new String [] {
            "flying_eye1", "flying_eye2", "flying_eye3", "flying_eye4"};
    public static final String[] COLLECTION = new String[] {
            "sushi1", "sushi2", "sushi3"};

    // Fonts
    public static final String FONT_PATH = "shoguns_clan.ttf";

    // Buttons
    public static final String PLAY_BUTTON_NAME = "start_button";
    public static final String PAUSE_BUTTON_NAME = "pause_button";
    public static final String INSTRUCTIONS_BUTTON_NAME = "instructions";
    public static final String LEADERBOARD_BUTTON_NAME = "leaderboard";
    public static final String SHARE_BUTTON_NAME = "share";

    // Game Name
    public static final String GAME_TITLE = "Ninja Quest";
    public static final String GAME_OVER = "GAME OVER!";

    // Instructions
    public static final String INSTRUCTION_LEFT = "\nTap left to slide.";
    public static final String INSTRUCTION_RIGHT = "\nTap right to jump.";
    public static final String INSTRUCTION_LEFT_NAME = "tap";
    public static final String INSTRUCTION_RIGHT_NAME = "tap";

    // About Screen
    public static final String DISMISS_REGION_NAME = "dismiss";
    public static final String ABOUT_NAME = "about";
    public static final String ABOUT_TEXT = "Developed by: David Jones\nFor: Mobile Game Design 1501\n" +
            "Full Sail University\n Enemy Graphics: ramtam\nSound Effects: David Jones";

    // For Sharing
    public static final String SHARE_MESSAGE = "Check out this cool app!";
    public static final String SHARE_HEADING = "Ninja Quest";

}
