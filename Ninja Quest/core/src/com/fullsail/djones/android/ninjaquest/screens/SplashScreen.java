package com.fullsail.djones.android.ninjaquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fullsail.djones.android.ninjaquest.MyGdxGame;

/**
 * Created by David Jones on 1/29/15.
 * MGD 1501
 * Full Sail University
 */
public class SplashScreen implements Screen {

    private Texture splashTexture;
    private SpriteBatch spriteBatch;
    private MyGdxGame myGame;

    public SplashScreen (MyGdxGame myGame){
        this.myGame = myGame;
    }

    @Override
    public void show() {
        splashTexture = new Texture(Gdx.files.internal("ninja_quest_splash.jpg"));
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        handleInput();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(splashTexture, 0, 0, myGame.WIDTH, myGame.HEIGHT);
        spriteBatch.end();
    }

    private void handleInput(){
        if (Gdx.input.justTouched()){
            myGame.setScreen(new GameScreen());
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
