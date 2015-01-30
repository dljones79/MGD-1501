package com.fullsail.djones.android.ninjaquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.fullsail.djones.android.ninjaquest.stages.GameStage;

/**
 * David Jones
 * MGD 1501
 * Full Sail University
 */

public class GameScreen implements Screen {

    private GameStage gameStage;

    public GameScreen() {
        gameStage = new GameStage();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // here we clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // now we update the stage
        gameStage.draw();
        gameStage.act(delta);
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
