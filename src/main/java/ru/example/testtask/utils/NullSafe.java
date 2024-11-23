package ru.example.testtask.utils;

import org.jetbrains.annotations.Nullable;

public final class NullSafe {

    public static Long id(@Nullable Integer value) {
        return value == null ? null : value.longValue();
    }

    public static Integer id(@Nullable Long value) {
        return value == null ? null : value.intValue();
    }
}
