// David Jones
// MGD 1501
// Week 1
// Ninja Quest

package com.fullsail.djones.android.ninjaquest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.fullsail.djones.android.ninjaquest.screens.SplashScreen;
import com.fullsail.djones.android.ninjaquest.utils.AssetManagement;
import com.fullsail.djones.android.ninjaquest.utils.EventListener;
import com.fullsail.djones.android.ninjaquest.utils.GameManagement;


/**
 * David Jones
 * MGD 1501
 * Full Sail University
 */
public class MyGdxGame extends Game {

    public static int WIDTH;
    public static int HEIGHT;

    // Declarations below were used in first turn in
    // Not needed for new design

    // Declare variables being used
    /*
    private Texture goodNinja;
    private Texture badNinja;
    private Texture princess;
    private Texture background;
    private Sound punchSound;
    private Sound punchResponseSound;
    private Sound giggleSound;
    private Music backgroundMusic;
    private OrthographicCamera orthoCam;
    private SpriteBatch spriteBatch;
    private Rectangle goodNinjaRec;
    private Rectangle badNinjaRec;
    private Rectangle princessRec;
	*/

    public MyGdxGame(EventListener listener){
        GameManagement.getInstance().setEventListener(listener);
    }

	@Override
	public void create () {

        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        setScreen(new SplashScreen(this));

        // Set the screen to our new GameScreen.
        //setScreen(new GameScreen());



        /* Old Code for first turn in below:

        // load the images for the ninjas
        goodNinja = new Texture(Gdx.files.internal("ninja_01.png"));
        badNinja = new Texture(Gdx.files.internal("bad_ninja_01.png"));
        princess = new Texture(Gdx.files.internal("princess.png"));
        background = new Texture(Gdx.files.internal("bg.jpg"));

        // load the sound effects
        punchSound = Gdx.audio.newSound(Gdx.files.internal("punch.wav"));
        punchResponseSound = Gdx.audio.newSound(Gdx.files.internal("punch_response.wav"));
        giggleSound = Gdx.audio.newSound(Gdx.files.internal("giggle.wav"));

        // load background music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3"));

        // start music
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        // create a camera
        orthoCam = new OrthographicCamera();
        orthoCam.setToOrtho(false, 800, 480);

        // create a SpriteBatch
        spriteBatch = new SpriteBatch();

        // create bounding boxes for the sprites
        goodNinjaRec = new Rectangle();
        goodNinjaRec.x = 800 / 2 - 375 / 2;
        goodNinjaRec.y = 75;
        goodNinjaRec.width = 64;
        goodNinjaRec.height = 64;

        badNinjaRec = new Rectangle();
        badNinjaRec.x = 800 / 2 - 250 / 2;
        badNinjaRec.y = 75;
        badNinjaRec.width = 64;
        badNinjaRec.height = 64;

        princessRec = new Rectangle();
        princessRec.x = 800 / 2 + 55 / 2;
        princessRec.y = 75;
        princessRec.height = 64;
        princessRec.width = 64;
	}

    // Method to render sprites and handle input
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        orthoCam.update();

        spriteBatch.setProjectionMatrix(orthoCam.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.draw(goodNinja, goodNinjaRec.x, goodNinjaRec.y);
        spriteBatch.draw(badNinja, badNinjaRec.x, badNinjaRec.y);
        spriteBatch.draw(princess, princessRec.x, princessRec.y);
        spriteBatch.end();

        // handle user input
        if (Gdx.input.isTouched()){
            Vector3 touchLoc = new Vector3();
            touchLoc.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            orthoCam.unproject(touchLoc);
            goodNinjaRec.x = touchLoc.x - 64 / 2;
        }

        // make sure ninja stays in screen bounds
        if (goodNinjaRec.x < 0) goodNinjaRec.x = 0;
        if (goodNinjaRec.x > 800 - 64) goodNinjaRec.x = 800 - 64;

        // Right now...runs constantly while sprites are overlapping
        // Currently researching collision handling.
        if (goodNinjaRec.overlaps(badNinjaRec)){
            punchSound.play();
            //punchResponseSound.play();
        }

        if (goodNinjaRec.overlaps(princessRec)){
            giggleSound.play();
        }
        */
	} // end create method

    @Override
    public void dispose() {
        super.dispose();
        AssetManagement.dispose();
    }
}

