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
import com.fullsail.djones.android.ninjaquest.actors.Background;
import com.fullsail.djones.android.ninjaquest.actors.GoodNinja;
import com.fullsail.djones.android.ninjaquest.actors.Ground;
import com.fullsail.djones.android.ninjaquest.utils.ActorUtils;
import com.fullsail.djones.android.ninjaquest.utils.WorldData;

/**
 * Created by David on 1/14/15.
 */
public class GameStage extends Stage implements ContactListener{

    // viewport measurements
    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;

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

        /*
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        */
        // Call custom methods to build the world and populate it with actors

        // Load sounds
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jumping.wav"));
        landSound = Gdx.audio.newSound(Gdx.files.internal("thud.wav"));

        createWorld();
        setupCamera();
        setupControls();
        renderer = new Box2DDebugRenderer();
    }

    // Set up camera
    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    // Custom method to build new world
    // Call methods to create ground and ninja
    // Handle contacts
    private void createWorld() {
        world = WorldData.buildWorld();
        world.setContactListener(this);
        //setBackground();
        createGround();
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

        accumulator += delta;

        while (accumulator >= TIME_STEP) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
    }

    // Render the game scene
    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }

    // set up touch controls
    private void setupControls() {
        touchPoint = new Vector3();
        rightSide = new Rectangle(getCamera().viewportWidth / 2, 0,
                getCamera().viewportWidth / 2, getCamera().viewportHeight);
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

        return super.touchDown(x, y, pointer, button);
    }

    // was the right side of the screen touched?
    private boolean screenRightTouched(float x, float y) {
        return rightSide.contains(x, y);
    }

    // custom method to get screen coordinates
    private void getCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x,y, 0));
    }

    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

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
