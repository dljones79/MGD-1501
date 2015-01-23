package com.fullsail.djones.android.ninjaquest.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fullsail.djones.android.ninjaquest.utils.AssetManagement;

/**
 * Created by David on 1/22/15.
 */
public abstract class GameControls extends Button {

    protected Rectangle buttonBounds;
    private Skin buttonSkin;

    public GameControls(Rectangle buttonBounds) {

        this.buttonBounds = buttonBounds;

        setWidth(buttonBounds.width);
        setHeight(buttonBounds.height);
        setBounds(buttonBounds.x, buttonBounds.y, buttonBounds.width, buttonBounds.height);

        buttonSkin = new Skin();
        buttonSkin.addRegions(AssetManagement.getTextureAtlas());

        loadTextureRegion();

        addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                touched();
                loadTextureRegion();
                return true;
            }

        });
    }

    protected void loadTextureRegion() {

        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = buttonSkin.getDrawable(getRegionName());
        setStyle(buttonStyle);

    }

    protected abstract String getRegionName();

    public abstract void touched();

    public Rectangle getButtonBounds() {

        return buttonBounds;

    }

}
