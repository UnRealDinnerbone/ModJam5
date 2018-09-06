package com.unrealdinnerbone.yaum.lib.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class StringUtils
{
    public static <T> T getNextObjectFormList(List<T> list, T currentString) {
        boolean isNext = false;
        for (T entry : list) {
            if (isNext) {
                return entry;
            }
            if (entry.equals(currentString)) {
                isNext = true;
            }
        }
        return list.stream().findFirst().get();
    }

    public static String format(String msg, Object... replacements) {
        return MessageFormat.format(msg, replacements);
    }
    public static String format(Object object, Object... replacements) {
        return format(String.valueOf(object), replacements);
    }

    public static <T> T getRandomValueFromList(ArrayList<T> arrayList) {
        return arrayList.get(MathHelper.getRandomInt(0, arrayList.size() - 1));
    }

}
