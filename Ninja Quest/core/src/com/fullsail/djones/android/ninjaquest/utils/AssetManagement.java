package com.fullsail.djones.android.ninjaquest.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by David Jones on 1/22/15.
 * MGD 1501
 * Full Sail University
 */

// TODO: Move all other assets to this asset manager

public class AssetManagement {

    private static TextureAtlas textureAtlas;

    private AssetManagement() {

    }

    public static void loadAssets() {

        textureAtlas = new TextureAtlas(Constants.ATLAS_PATH);

    }

    public static TextureAtlas getTextureAtlas() {
        loadAssets();
        return textureAtlas;
    }

    public static void dispose() {
        textureAtlas.dispose();
    }

}
