package com.fullsail.djones.android.ninjaquest.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.fullsail.djones.android.ninjaquest.actors.AboutButton;
import com.fullsail.djones.android.ninjaquest.actors.AboutText;
import com.fullsail.djones.android.ninjaquest.actors.Background;
import com.fullsail.djones.android.ninjaquest.actors.Baddie;
import com.fullsail.djones.android.ninjaquest.actors.GameOverLabel;
import com.fullsail.djones.android.ninjaquest.actors.GameTitle;
import com.fullsail.djones.android.ninjaquest.actors.GoodNinja;
import com.fullsail.djones.android.ninjaquest.actors.Ground;
import com.fullsail.djones.android.ninjaquest.actors.Instructions;
import com.fullsail.djones.android.ninjaquest.actors.InstructionsButton;
import com.fullsail.djones.android.ninjaquest.actors.PauseButton;
import com.fullsail.djones.android.ninjaquest.actors.Score;
import com.fullsail.djones.android.ninjaquest.actors.StartButton;
import com.fullsail.djones.android.ninjaquest.enums.DifficultyLevel;
import com.fullsail.djones.android.ninjaquest.enums.GameStates;
import com.fullsail.djones.android.ninjaquest.utils.ActorUtils;
import com.fullsail.djones.android.ninjaquest.utils.Constants;
import com.fullsail.djones.android.ninjaquest.utils.GameManagement;
import com.fullsail.djones.android.ninjaquest.utils.WorldData;

/**
 * Created by David Jones on 1/14/15.
 * MGD 1501
 * Full Sail University
 */

public class GameStage extends Stage implements ContactListener{

    // viewport measurements
    /* For Testing
    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;
    */
    // For final
    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    // Create new instances of custom Java classes
    private World world;          // box2D World object
    private Ground ground;        // Custom Java Class Object
    private GoodNinja goodNinja;  // Custom Java Class Object

    // Time for movement
    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    // Create a new camera and debug renderer to test sprites
    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    // Rectangles for controls
    private Rectangle rightSide;
    private Rectangle leftSide;

    // touch points
    private Vector3 touchPoint;

    // Sounds
    private Sound jumpSound;
    private Sound landSound;
    private Sound hitSound;
    private Music backgroundMusic;

    // For Score
    private Score score;

    // Buttons
    private StartButton startButton;
    private PauseButton pauseButton;
    private AboutButton aboutButton;
    private InstructionsButton instructionsButton;


    private float timePlayed;
    private boolean instructionsDisplayed;


    // Constructor
    public GameStage() {

        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));

        // Call custom methods to build the world and populate it with actors

        // Load sounds
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jumping.wav"));
        landSound = Gdx.audio.newSound(Gdx.files.internal("thud.wav"));
        hitSound = Gdx.audio.newSound(Gdx.files.internal("punch_response.wav"));
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"));

        // start music
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        backgroundMusic.play();


        setupCamera();
        createWorld();
        displayMainMenu();
        displayTitle();
        setupControls();
        Gdx.input.setInputProcessor(this);
        onGameOver();

        // For debugging
        /*
        renderer = new Box2DDebugRenderer();
        */

        //TODO: Handle game over.
    }

    // Display game title
    private void displayTitle() {
        Rectangle titleBounds = new Rectangle(0, getCamera().viewportHeight * 7 / 8,
                getCamera().viewportWidth, getCamera().viewportHeight / 4);
        addActor(new GameTitle(titleBounds));
    }

    // Display Game over
    private void displayGameOver() {
        Rectangle labelBounds = new Rectangle(0, getCamera().viewportHeight * 7 / 8,
                getCamera().viewportWidth, getCamera().viewportHeight / 4);
        addActor(new GameOverLabel(labelBounds));
    }

    // Show menu
    private void displayMainMenu() {
        displayStartButton();
        displayAboutButton();
        displayInstructionsButton();
    }

    // Set up Score Display
    private void setupScoreDisplay() {
        Rectangle bounds = new Rectangle(getCamera().viewportWidth * 47 / 64,
                getCamera().viewportHeight * 57 / 64, getCamera().viewportWidth / 4,
                getCamera().viewportHeight / 8);
        score = new Score(bounds);
        addActor(score);
    }


    // Set up camera
    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    // Custom method to build new world
    // Call methods to create ground and ninja
    // Make Bad Guys
    // Handle contacts
    private void createWorld() {
        world = WorldData.buildWorld();
        world.setContactListener(this);
        setBackground();
        createGround();
        //setupScoreDisplay();
        //createCharacters();
    }

    // Create the background
    private void setBackground() {
        addActor(new Background());
    }

    // Create ground actor
    private void createGround() {
        ground = new Ground(WorldData.layGround(world));
        addActor(ground);
    }

    // Create characters
    private void createCharacters(){
        createGoodNinja();
        makeBadGuy();
    }

    // Create good ninja actor
    private void createGoodNinja() {
        if (goodNinja != null) {
            goodNinja.remove();
        }
        goodNinja = new GoodNinja(WorldData.createGoodNinja(world));
        addActor(goodNinja);
    }

    // progress the game over time
    @Override
    public void act(float delta) {
        super.act(delta);

        if (GameManagement.getInstance().getCurrentState() == GameStates.PAUSED) return;

        if (GameManagement.getInstance().getCurrentState() == GameStates.RUNNING) {
            timePlayed += delta;
            increaseDifficulty();
        }

        // Create an array of bodies with count equal to bodies in world
        Array<Body> enemies = new Array<Body>(world.getBodyCount());
        world.getBodies(enemies);

        // for each body in enemy array
        // refresh body
        for (Body body : enemies) {
            refresh(body);
        }

        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
    }

    // refresh enemies on screen
    private void refresh(Body body) {
        if (!ActorUtils.bodyOnScreen(body)) {
            if (ActorUtils.bodyIsBaddie(body) && !goodNinja.didCollide()) {
                makeBadGuy();
            }
            world.destroyBody(body);
        }
    }

    // create a new bad guy
    private void makeBadGuy() {
        Baddie baddie = new Baddie(WorldData.createBaddie(world));
        baddie.getUserData().setVelocity(GameManagement.getInstance().getDifficulty().getEnemyVelocity());
        addActor(baddie);
    }

    // For debugging with rectangles
    /*
    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }
    */

    // set up touch controls
    private void setupControls() {
        touchPoint = new Vector3();
        rightSide = new Rectangle(getCamera().viewportWidth / 2, 0,
                getCamera().viewportWidth / 2, getCamera().viewportHeight);
        leftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2, getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);

    }

    // did the user touch the screen?
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        // get coordinates of touch point
        getCoordinates(x, y);

        if (buttonPressed(touchPoint.x, touchPoint.y)) {
            return super.touchDown(x, y, pointer, button);
        }

        if (GameManagement.getInstance().getCurrentState() != GameStates.RUNNING) {
            return super.touchDown(x, y, pointer, button);
        }

        // ninja jumps if screen is touched on right side
        if (screenRightTouched(touchPoint.x, touchPoint.y)){
            goodNinja.ninjaJump();
            jumpSound.play();
        }

        // ninja slides if screen is touched on left side
        if (screenLeftTouched(touchPoint.x, touchPoint.y)) {
            goodNinja.ninjaSlide();
        }

        return super.touchDown(x, y, pointer, button);
    }

    // stop sliding when user stops pressing left side of screen
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button){

        if (GameManagement.getInstance().getCurrentState() != GameStates.RUNNING) {
            return super.touchUp(screenX, screenY, pointer, button);
        }

        if (goodNinja.isNinjaSliding()) {
            goodNinja.stopSliding();
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }

    // was the right side of the screen touched?
    private boolean screenRightTouched(float x, float y) {
        return rightSide.contains(x, y);
    }

    // was the left side of the screen touched?
    private boolean screenLeftTouched(float x, float y) { return leftSide.contains(x, y); }

    // custom method to get screen coordinates
    private void getCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x,y, 0));
    }

    private boolean buttonPressed(float x, float y){
        boolean buttonPressed = false;

        switch (GameManagement.getInstance().getCurrentState()) {
            case OVER:
                buttonPressed = startButton.getButtonBounds().contains(x, y)
                    || aboutButton.getButtonBounds().contains(x, y);
                break;
            case RUNNING:
            case PAUSED:
                buttonPressed = pauseButton.getButtonBounds().contains(x, y);
                break;
        }
        return buttonPressed;
    }

    // Method to handle collisions
    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        // If good ninja runs into a bad guy
        // good ninja didCollide()
        if ((ActorUtils.bodyIsGoodNinja(bodyA) && ActorUtils.bodyIsBaddie(bodyB) ||
                (ActorUtils.bodyIsBaddie(bodyA) && ActorUtils.bodyIsGoodNinja(bodyB)))){
            if (goodNinja.didCollide()){
                return;
            }
            goodNinja.collision();
            hitSound.play();
            onGameOver();
        }

        // If good ninja hits ground
        // GoodNinja landed
        // Sound played
        if ((ActorUtils.bodyIsGoodNinja(bodyA) && ActorUtils.bodyIsGround(bodyB))
                || (ActorUtils.bodyIsGround(bodyA) && ActorUtils.bodyIsGoodNinja(bodyB))){
            goodNinja.ninjaLanded();
            landSound.play();
        }

    }

    // Display the start button
    private void displayStartButton() {
        Rectangle startButtonBounds = new Rectangle(getCamera().viewportWidth * 3 / 16,
                getCamera().viewportHeight / 4, getCamera().viewportWidth / 4, getCamera().viewportHeight / 4);
        startButton = new StartButton(startButtonBounds, new StartButton.StartButtonListener() {
            @Override
            public void onStart() {
                clear();
                createWorld();
                setupScoreDisplay();
                createCharacters();
                displayPauseButton();
                onResumeGame();
            }
        });
        addActor(startButton);
    }

    // Display the pause button
    private void displayPauseButton() {
        Rectangle pauseBounds = new Rectangle(getCamera().viewportWidth / 64, getCamera().viewportHeight * 1 / 2,
                getCamera().viewportWidth / 10, getCamera().viewportHeight / 10);
        pauseButton = new PauseButton(pauseBounds, new PauseButton.PauseListener() {
            @Override
            public void onPause() {
                onPauseGame();
            }

            @Override
            public void onResume() {
                onResumeGame();
            }
        });
        addActor(pauseButton);
    }

    // Display about button
    private void displayAboutButton() {
        Rectangle aboutBounds = new Rectangle(getCamera().viewportWidth * 23 / 25,
                getCamera().viewportHeight * 13 / 20, getCamera().viewportHeight / 10,
                getCamera().viewportHeight / 10);
        aboutButton = new AboutButton(aboutBounds, new AboutButton.AboutListener() {
            @Override
            public void onAbout() {
                if (GameManagement.getInstance().getCurrentState() == GameStates.OVER){
                    onAboutButton();
                } else {
                    clear();
                    createWorld();
                    displayTitle();
                    onGameOver();
                }
            }
        });
        addActor(aboutButton);
    }

    // Display instructions button
    private void displayInstructionsButton() {
        Rectangle instructionBounds = new Rectangle(getCamera().viewportWidth * 23 / 25,
                getCamera().viewportHeight / 2, getCamera().viewportWidth / 15, getCamera().viewportWidth / 15);
        instructionsButton = new InstructionsButton(instructionBounds, new InstructionsButton.InstructionsButtonListener() {
            @Override
            public void onInstruct() {
                if (GameManagement.getInstance().getCurrentState() == GameStates.OVER){
                    onInstructionsButton();
                } else {
                    clear();
                    createWorld();
                    displayTitle();
                    onGameOver();
                }
            }
        });
        addActor(instructionsButton);
    }

    // Set up about text
    private void createAboutInfo() {
        Rectangle labelBounds = new Rectangle(0, getCamera().viewportHeight * 5 / 8,
                getCamera().viewportWidth, getCamera().viewportHeight / 4);
        addActor(new AboutText(labelBounds));
    }

    // Set up instructions
    private void displayInstructions() {
        if (instructionsDisplayed){
            return;
        }

        createLeftInstructions();
        createRightInstructions();

        instructionsDisplayed = true;
    }

    // Left Instructions
    private void createLeftInstructions(){
        float width = getCamera().viewportHeight / 4;
        float xLeft = getCamera().viewportWidth / 4 - width / 2;
        Rectangle leftBounds = new Rectangle(xLeft, getCamera().viewportHeight * 9 / 20, width, width);
        addActor(new Instructions(leftBounds, Constants.INSTRUCTION_LEFT_NAME, Constants.INSTRUCTION_LEFT));
    }

    // Right Instructions
    private void createRightInstructions(){
        float width = getCamera().viewportHeight / 4;
        float xRight = getCamera().viewportWidth * 3 / 4 - width / 2;
        Rectangle rightBounds = new Rectangle(xRight, getCamera().viewportHeight * 9 / 20, width, width);
        addActor(new Instructions(rightBounds, Constants.INSTRUCTION_RIGHT_NAME, Constants.INSTRUCTION_RIGHT));
    }

    private void onResumeGame() {
        GameManagement.getInstance().setCurrentState(GameStates.RUNNING);
    }

    private void onPauseGame() {
        GameManagement.getInstance().setCurrentState(GameStates.PAUSED);
    }

    private void onGameOver() {
        GameManagement.getInstance().setCurrentState(GameStates.OVER);
        GameManagement.getInstance().resetDifficultyLevel();
        timePlayed = 0;
        displayMainMenu();
        displayGameOver();
    }

    private void onAboutButton() {
        GameManagement.getInstance().setCurrentState(GameStates.ABOUT);
        clear();
        createWorld();
        displayTitle();
        createAboutInfo();
        displayAboutButton();
    }

    private void onInstructionsButton() {
        GameManagement.getInstance().setCurrentState(GameStates.INSTRUCTIONS);
        clear();
        createWorld();
        displayTitle();
        displayInstructions();
        displayInstructionsButton();
    }

    // method to increase game difficulty over time
    private void increaseDifficulty() {
        if (GameManagement.getInstance().atMaxDifficulty()) {
            return;
        }

        DifficultyLevel difficultyLevel = GameManagement.getInstance().getDifficulty();

        if (timePlayed > GameManagement.getInstance().getDifficulty().getLevel() * 5) {
            int nextLevel = difficultyLevel.getLevel() + 1;
            String difficultyName = "DIFFICULTY_" + nextLevel;
            GameManagement.getInstance().setDifficulty(DifficultyLevel.valueOf(difficultyName));

            goodNinja.onLevelChange(GameManagement.getInstance().getDifficulty());
            score.setMultiplierAmount(GameManagement.getInstance().getDifficulty().getScoreIncrementMultiplier());
        }
    }

    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
