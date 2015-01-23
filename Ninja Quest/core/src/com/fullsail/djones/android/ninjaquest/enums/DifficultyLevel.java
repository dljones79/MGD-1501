package com.fullsail.djones.android.ninjaquest.enums;

import com.badlogic.gdx.math.Vector2;
import com.fullsail.djones.android.ninjaquest.utils.Constants;

/**
 * Created by David Jones on 1/22/15.
 * MGD 1501
 * Full Sail University
 */
public enum DifficultyLevel {

    DIFFICULTY_1(1, Constants.ENEMY_VELOCITY, Constants.GOODNINJA_GRAVITY, Constants.GOODNINJA_JUMPING, 5),
    DIFFICULTY_2(2, new Vector2(-12f, 0f), Constants.GOODNINJA_GRAVITY * 1.1F, new Vector2(0, 17f), 10),
    DIFFICULTY_3(3, new Vector2(-14f, 0f), Constants.GOODNINJA_GRAVITY * 1.1F, new Vector2(0, 17f), 20),
    DIFFICULTY_4(4, new Vector2(-16f, 0f), Constants.GOODNINJA_GRAVITY * 1.1F, new Vector2(0, 17f), 40),
    DIFFICULTY_5(5, new Vector2(-18f, 0f), Constants.GOODNINJA_GRAVITY * 1.1F, new Vector2(0, 17f), 80);

    private int level;
    private Vector2 enemyVelocity;
    private float goodNinjaGravity;
    private Vector2 goodNinjaJumpingImpulse;
    private int scoreIncrementMultiplier;

    DifficultyLevel(int level, Vector2 enemyVelocity, float goodNinjaGravity, Vector2 goodNinjaJumpingImpulse, int scoreIncrementMultiplier) {
        this.level = level;
        this.enemyVelocity = enemyVelocity;
        this.goodNinjaGravity = goodNinjaGravity;
        this.goodNinjaJumpingImpulse = goodNinjaJumpingImpulse;
        this.scoreIncrementMultiplier = scoreIncrementMultiplier;
    }

    public int getLevel() {
        return level;
    }

    public Vector2 getEnemyVelocity() {
        return enemyVelocity;
    }

    public Vector2 getGoodNinjaJumpingImpulse() {
        return goodNinjaJumpingImpulse;
    }

    public int getScoreIncrementMultiplier() {
        return scoreIncrementMultiplier;
    }

    public float getGoodNinjaGravity() {
        return goodNinjaGravity;
    }
}
