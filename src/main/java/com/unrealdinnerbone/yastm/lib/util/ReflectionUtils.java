package com.unrealdinnerbone.yastm.lib.util;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class ReflectionUtils
{
    @Nullable
    public static Class<?> getClassFormName(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
//            Yaum.getInstance().getLogHelper().error(StringUtils.format("The class with the name {0} does not exist", name));
            return null;
        }
    }

    @Nullable
    public static <T> T createInstance(Class<T> classToMake) {
        try {
            return classToMake.newInstance();
        } catch (IllegalAccessException e) {
//            Yaum.getInstance().getLogHelper().error(StringUtils.format("Error creating and instance of the class {0} is the class constructor private?", classToMake.getName()));
            return null;
        } catch (InstantiationException e) {
//            Yaum.getInstance().getLogHelper().error(StringUtils.format("Error creating and instance of the class {0} it does not have and empty constructor", classToMake.getName()));
            return null;
        }
    }

    public static boolean hasEmptyConstructor(Class<?> clazz) {
        return Stream.of(clazz.getConstructors()).anyMatch((c) -> c.getParameterCount() == 0);
    }

}
