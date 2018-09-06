package com.unrealdinnerbone.yaum.lib.util;

import java.util.concurrent.ThreadLocalRandom;

public class MathHelper
{

    public static int getRandomInt(int min, int max) {
        return getRandom().nextInt(min, max + 1);
    }

    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

}

