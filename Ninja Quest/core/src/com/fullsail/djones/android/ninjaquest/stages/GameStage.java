package com.fullsail.djones.android.ninjaquest.stages;

import com.badlogic.gdx.Gdx;
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
import com.fullsail.djones.android.ninjaquest.actors.Background;
import com.fullsail.djones.android.ninjaquest.actors.Baddie;
import com.fullsail.djones.android.ninjaquest.actors.GoodNinja;
import com.fullsail.djones.android.ninjaquest.actors.Ground;
import com.fullsail.djones.android.ninjaquest.utils.ActorUtils;
import com.fullsail.djones.android.ninjaquest.utils.Constants;
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

    // Constructor
    public GameStage() {

        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));

        // Call custom methods to build the world and populate it with actors

        // Load sounds
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jumping.wav"));
        landSound = Gdx.audio.newSound(Gdx.files.internal("thud.wav"));

        createWorld();
        setupCamera();
        setupControls();

        // For debugging
        /*
        renderer = new Box2DDebugRenderer();
        */
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
        makeBadGuy();
        createGoodNinja();
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

    // Create good ninja actor
    private void createGoodNinja() {
        goodNinja = new GoodNinja(WorldData.createGoodNinja(world));
        addActor(goodNinja);
    }

    // progress the game over time
    @Override
    public void act(float delta) {
        super.act(delta);

        // Create an array of bodies with count equalt to bodies in world
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

    // Method to handle collisions
    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        // If good ninja runs into a bad guy
        // good ninja didCollide()
        if ((ActorUtils.bodyIsGoodNinja(bodyA) && ActorUtils.bodyIsBaddie(bodyB) ||
                (ActorUtils.bodyIsBaddie(bodyA) && ActorUtils.bodyIsGoodNinja(bodyB)))){
            goodNinja.didCollide();
        }

        // If good ninja hits grounds
        // GoodNinja landed
        // Sound played
        if ((ActorUtils.bodyIsGoodNinja(bodyA) && ActorUtils.bodyIsGround(bodyB))
                || (ActorUtils.bodyIsGround(bodyA) && ActorUtils.bodyIsGoodNinja(bodyB))){
            goodNinja.ninjaLanded();
            landSound.play();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
