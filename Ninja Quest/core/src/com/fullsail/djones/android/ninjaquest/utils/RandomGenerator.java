package com.fullsail.djones.android.ninjaquest.utils;

import com.fullsail.djones.android.ninjaquest.enums.EnemyDataTypes;

import java.util.Random;

/**
 * Created by David Jones on 1/15/15.
 * MGD 1501
 * Full Sail University
 */

// This custom class helps with creating a random enemy
    // It generates a random enum (Baddie Data Type)
public class RandomGenerator {

    public static EnemyDataTypes getNewEnemy() {
        RandomEnum<EnemyDataTypes> randomEnum = new RandomEnum<EnemyDataTypes>(EnemyDataTypes.class);
        return randomEnum.random();
    }

    private static class RandomEnum<E extends Enum> {

        private static final Random random = new Random();
        private final E[] values;

        public RandomEnum(Class<E> token) {
            values = token.getEnumConstants();
        }

        public E random() {
            return values[random.nextInt(values.length)];
        }
    }
}
