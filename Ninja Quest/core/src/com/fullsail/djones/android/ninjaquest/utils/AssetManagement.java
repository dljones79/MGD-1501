package com.fullsail.djones.android.ninjaquest.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.HashMap;

/**
 * Created by David Jones on 1/22/15.
 * MGD 1501
 * Full Sail University
 */

// TODO: Move all other assets to this asset manager

public class AssetManagement {

    private static TextureAtlas textureAtlas;
    private static BitmapFont titleFont;
    private static BitmapFont instructionFont;
    private static BitmapFont aboutFont;
    private static HashMap<String, TextureRegion> textureRegionHashMap = new HashMap<String, TextureRegion>();

    private AssetManagement() {

    }

    public static void loadAssets() {

        textureAtlas = new TextureAtlas(Constants.ATLAS_PATH);

        // Instructions
        textureRegionHashMap.put(Constants.INSTRUCTION_LEFT_NAME, textureAtlas.findRegion(Constants.INSTRUCTION_LEFT_NAME));
        textureRegionHashMap.put(Constants.INSTRUCTION_RIGHT_NAME, textureAtlas.findRegion(Constants.INSTRUCTION_RIGHT_NAME));

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(Constants.FONT_PATH));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        // Font for title
        // Set size, and assign color
        fontParameter.size = 72;
        titleFont = fontGenerator.generateFont(fontParameter);
        titleFont.setColor(225f, 225f, 225f, 1f);

        // Font for instructions
        // Set size, and assign color
        fontParameter.size = 25;
        instructionFont = fontGenerator.generateFont(fontParameter);
        instructionFont.setColor(225f, 225f, 225f, 1f);

        // Font for about page
        // Set size, and assign color
        fontParameter.size = 34;
        aboutFont = fontGenerator.generateFont(fontParameter);
        aboutFont.setColor(225f, 225f, 225f, 1f);
    }

    public static TextureAtlas getTextureAtlas() {
        loadAssets();
        return textureAtlas;
    }

    public static BitmapFont getTitleFont() {
        return titleFont;
    }

    public static BitmapFont getInstructionFont() {
        return instructionFont;
    }

    public static BitmapFont getAboutFont() {
        return aboutFont;
    }


    public static TextureRegion getTextureRegion(String key) {
        return textureRegionHashMap.get(key);
    }



    public static void dispose() {
        textureAtlas.dispose();
    }

}
